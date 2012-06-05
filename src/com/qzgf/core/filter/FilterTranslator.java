/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.core.filter
* File	         :FilterTranslator.java
* Written by     :fjfdszj
* Created Date   :May 28, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 28, 2012
* Purpose        :描述

*/
package com.qzgf.core.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     FilterTranslator.java
 *
 */
public class FilterTranslator {
	  //几个前缀/后缀
    protected char leftToken = ' ';//'['
    protected char paramPrefixToken = ' ';//'@'
    protected char rightToken = ' ';//']'
    protected char groupLeftToken = '(';
    protected char groupRightToken = ')';
    protected char likeToken = '%';
    /// <summary>
    /// 参数计数器
    /// </summary>
    private int paramCounter = 0;

    //几个主要的属性
    public FilterGroup Group;
    public String CommandText;
    public List<FilterParam> Parms=new ArrayList<FilterParam>();;

    public FilterTranslator()
    {
    }

    public FilterTranslator(FilterGroup group)
    {
        this.Group = group;
        //this.Parms = new ArrayList<FilterParam>();
        //this.Parms = new List<FilterParam>();
    }
    public void Translate()
    {
        this.CommandText = TranslateGroup(this.Group);
    }
    
    /**
     * 
     * Purpose      :自定义翻译语句
     * @param where
     */
    public void Translate(String where)
    {
    	JSONObject jsonObj = JSONObject.fromObject(where);  
    	
    	//FilterGroup cur=(FilterGroup)JSONObject.toBean(jsonObj, FilterGroup.class);
    	Map classMap = new HashMap(); 
    	classMap.put("rules", FilterRule.class); 
    	classMap.put("groups", FilterGroup.class);
    	
    	FilterGroup cur=(FilterGroup)JSONObject.toBean(jsonObj, FilterGroup.class, classMap);
    	
    	if(!where.equals("")){
	    	this.Group=cur;
    	}	
        this.CommandText = TranslateGroup(this.Group);
        
        //2012-05-27 将变量转化为参数输出
		int i=0;
		List<FilterParam> testarry=this.Parms;
		String[] arrstr=new String[testarry.size()];
		for(FilterParam parm:testarry){
			arrstr[i++]=parm.value.toString();
		}
		String outstr=String.format(this.CommandText, arrstr);
		this.CommandText=outstr;
        
    }
    

    public String TranslateGroup(FilterGroup group)
    {
        StringBuilder bulider = new StringBuilder();
        if (group == null) return " 1=1 ";
        boolean appended = false;
        bulider.append(groupLeftToken);
        if (group.rules != null)
        {
            for (FilterRule rule : group.rules)
            {
                if (appended)
                    bulider.append(GetOperatorQueryText(group.op));
                bulider.append(TranslateRule(rule));
                appended = true;
            }
        }
        if (group.groups != null)
        {
            for (FilterGroup subgroup : group.groups)
            {
                if (appended)
                    bulider.append(GetOperatorQueryText(group.op));
                bulider.append(TranslateGroup(subgroup));
                appended = true;
            }
        }
        bulider.append(groupRightToken);
        if (appended == false) return " 1=1 ";
        return bulider.toString();
    }

    /// <summary>
    /// 注册用户匹配管理，当不方便修改ligerRM.dll时，可以通过这种方式，在外部注册
    /// </summary>
    /// <param name="match"></param>
    /*
    public static void RegCurrentParmMatch(String key,Func<int> fn)
    {
        if (!currentParmMatch.ContainsKey(key))
            currentParmMatch.Add(key, fn);
    }
	*/
    
    public static void RegCurrentParmMatch(String key,Integer fn)
    {
        if (!currentParmMatch.containsKey(key))
            currentParmMatch.put(key, fn);
    }
    
    /// <summary>
    /// 匹配当前用户信息，都是int类型
    /// 对于CurrentRoleID，只返回第一个角色
    /// </summary>
    private static HashMap<String, Integer> currentParmMatch = new HashMap<String,Integer>();
    static 
    { 
    	currentParmMatch.put("{CurrentUserID}",1); 
    	currentParmMatch.put("{CurrentRoleID}",1); 
    	currentParmMatch.put("{CurrentDeptID}",1); 
    	currentParmMatch.put("{CurrentEmployeeID}",1); 
    	currentParmMatch.put("{CurrentSupplierID}",1); 
    }
    
    public String TranslateRule(FilterRule rule)
    {
        StringBuilder bulider = new StringBuilder();
        if (rule == null) return " 1=1 ";

        //如果字段名采用了 用户信息参数
        if (currentParmMatch.containsKey(rule.field))
        {
            Integer field = currentParmMatch.get(rule.field);
            bulider.append(paramPrefixToken + CreateFilterParam(field, "int"));
        }
        else
        {
            bulider.append(leftToken + rule.field + rightToken);
        }
        //操作符
        bulider.append(GetOperatorQueryText(rule.op));

        String op = rule.op.toLowerCase();
        if (op.equals("like") || op.equals("endwith"))
        {
            String value = rule.value.toString();
            if (!value.startsWith((String.valueOf(this.likeToken))))
            {
                rule.value = this.likeToken + value;
            }
        }
        if (op.equals("like") || op.equals("startwith"))
        {
            String value = rule.value.toString();
            if (!value.endsWith((String.valueOf(this.likeToken))))
            {
                rule.value = value + this.likeToken;
            }
        }
        if (op.equals("in") || op.equals("notin"))
        {
            String[] values = rule.value.toString().split(",");
            boolean appended = false;
            bulider.append("(");
            for (String value : values)
            {
                if (appended) bulider.append(",");
                //如果值使用了 用户信息参数 比如： in ({CurrentRoleID},4)
                if (currentParmMatch.containsKey(value))
                {
                    Integer val = currentParmMatch.get(value);
                    bulider.append(paramPrefixToken + CreateFilterParam(val, "int"));
                }
                else
                {
                    bulider.append(paramPrefixToken + CreateFilterParam(value, rule.type)); 
                }
                appended = true;
            }
            bulider.append(")");
        } 
        //is null 和 is not null 不需要值
        else if (!op.equals("isnull") && !op.equals("isnotnull"))
        {
            //如果值使用了 用户信息参数 比如 [EmptID] = {CurrentEmptID}
            if (rule.value != null && currentParmMatch.containsKey(rule.value.toString()))
            {
                Integer value = currentParmMatch.get(rule.value.toString());
                bulider.append(paramPrefixToken + CreateFilterParam(value, "int"));
            }
            else
            {
                bulider.append(paramPrefixToken + CreateFilterParam(rule.value, rule.type));

            }
        } 
        return bulider.toString();
    }

    private String CreateFilterParam(Object value,String type)
    {
        String paramName = "p" + ++paramCounter;
        Object val = value;
        if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("digits"))
            val = (Integer) val;
        else if (type.equalsIgnoreCase("float")|| type.equalsIgnoreCase("number"))
            val = (Float)val;
        FilterParam param = new FilterParam(paramName, val);
        this.Parms.add(param);
        //return paramName;
        return "'%s'";//"{"+(paramCounter-1)+"}"
    }


    public  String toString()
    {
        StringBuilder bulider = new StringBuilder();
        bulider.append("CommandText:");
        bulider.append(this.CommandText);
        bulider.append("/r/n");
        bulider.append("Parms:"+"/r/n");
        for (FilterParam parm:this.Parms)
        {
            bulider.append(String.format("{0}:{1}"+"/r/n", parm.name, parm.value));
        }
        return bulider.toString();
    }


    /// <summary>
    /// 获取操作符的SQL Text
    /// </summary>
    /// <param name="op"></param>
    /// <returns></returns> 
    public static String GetOperatorQueryText(String op)
    {
    	String opval=op.toLowerCase();
    	if(opval.equals("add")){
    		return " + ";
    	}else if(opval.equals("bitwiseand")){
    		return " & ";
    	}else if(opval.equals("bitwisenot")){
    		return " ~ ";
    	}else if(opval.equals("bitwiseor")){
    		 return " | ";
    	}else if(opval.equals("bitwisexor")){
    		   return " ^ ";
    	}else if(opval.equals("divide")){
    		return " / ";
    	}else if(opval.equals("equal")){
    		 return " = ";
    	}else if(opval.equals("greater")){
    		 return " > ";
    	}else if(opval.equals("greaterorequal")){
    		 return " >= ";
    	}else if(opval.equals("isnull")){
    		return " is null ";
    	}else if(opval.equals("isnotnull")){
    		   return " is not null ";
    	}else if(opval.equals("less")){
    		 return " < ";
    	}else if(opval.equals("lessorequal")){
    		 return " <= ";
    	}else if(opval.equals("like")){
    		 return " like ";
    	}else if(opval.equals("startwith")){
    		 return " like ";
    	}else if(opval.equals("endwith")){
    		 return " like ";
    	}else if(opval.equals("modulo")){
    		 return " % ";
    	}else if(opval.equals("multiply")){
    		return " * ";
    	}else if(opval.equals("notequal")){
    		return " <> ";
    	}else if(opval.equals("subtract")){
    		  return " - ";
    	}else if(opval.equals("and")){
    		return " and ";
    	}else if(opval.equals("or")){
    		 return " or ";
    	}else if(opval.equals("in")){
    		  return " in ";
    	}else if(opval.equals("notin")){
            return " not in ";
    	}else{
    		 return " = ";
    	} 
    }
    //=============================常量定义=============================================
	/**
	 * Purpose      : 说明
	 * @return the group
	 */
	public FilterGroup getGroup() {
		return Group;
	}

	/**
	 * Purpose      : 说明
	 * @param group the group to set
	 */
	
	public void setGroup(FilterGroup group) {
		Group = group;
	}

	/**
	 * Purpose      : 说明
	 * @return the commandText
	 */
	public String getCommandText() {
		return CommandText;
	}

	/**
	 * Purpose      : 说明
	 * @param commandText the commandText to set
	 */
	
	public void setCommandText(String commandText) {
		CommandText = commandText;
	}

	/**
	 * Purpose      : 说明
	 * @return the parms
	 */
	public List<FilterParam> getParms() {
		return Parms;
	}

	/**
	 * Purpose      : 说明
	 * @param parms the parms to set
	 */
	
	public void setParms(List<FilterParam> parms) {
		Parms = parms;
	}
	
	
	
	public static void main( String xu[] ){
		//专用mssql
		//String teststr="{\"op\":\"and\",\"rules\":[{\"op\":\"equal\",\"field\":\"search.pusername\",\"value\":\"123132\",\"type\":\"string\"}]}";
		
		//String teststr="{\"groups\":[{\"op\":\"and\"}],\"rules\":[{\"field\":\"username\",\"op\":\"like\",\"value\":\"123\",\"type\":\"string\"},{\"field\":\"username\",\"op\":\"notequal\",\"value\":\"1234\",\"type\":\"string\"},{\"field\":\"nickname\",\"op\":\"equal\",\"value\":\"5656\",\"type\":\"string\"}],\"op\":\"and\"}";

		String teststr="{\"groups\":[{\"rules\":[{\"field\":\"username\",\"op\":\"equal\",\"value\":\"1\",\"type\":\"string\"}],\"op\":\"or\"}],\"rules\":[{\"field\":\"username\",\"op\":\"equal\",\"value\":\"2\",\"type\":\"string\"}],\"op\":\"and\"}";
		FilterTranslator ft= new FilterTranslator();
		ft.Translate(teststr);
		
		System.out.println(ft.CommandText);
		
		int i=0;
		List<FilterParam> testarry=ft.Parms;
		String[] arrstr=new String[testarry.size()];
		for(FilterParam parm:testarry){
			arrstr[i++]=parm.value.toString();
			System.out.println(parm.name+":"+parm.value);
		}
		//String.format("%a,  %e,  %f,  %g",floatType,floatType,floatType,floatType);
		String outstr=String.format(ft.CommandText, arrstr);
		System.out.println(outstr);
		
		System.out.println(String.format("{0:(###) ###-####}", "18005551212"));
		
		System.out.println(ft.toString());
		
	}
}

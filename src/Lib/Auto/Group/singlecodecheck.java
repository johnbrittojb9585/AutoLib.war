package Lib.Auto.Group;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class singlecodecheck  {

  private String replace(String str, String pattern, String replace) {
    if (replace == null) {
      replace = "";
    }
    int s = 0, e = 0;
    StringBuffer result = new StringBuffer((int) str.length()*2);
    while ((e = str.indexOf(pattern, s)) >= 0) {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e + pattern.length();
    }
    result.append(str.substring(s));
    return result.toString();
  }
  
  public  String getParam(javax.servlet.http.HttpServletRequest req, String paramName) {
    String param = req.getParameter(paramName);
    if ( param == null || param.equals("") ) return "";
	param = replace(param,"&amp;","&");
    param = replace(param,"&lt;","<");
    param = replace(param,"&gt;",">");
    param = replace(param,"&amp;lt;","<");
    param = replace(param,"&amp;gt;",">");
    param=  param.replace('"',' ');
    param = replace(param, "'", "''");
   java.util.StringTokenizer st=new java.util.StringTokenizer(param,"\\");
   String s="";
   while(st.hasMoreElements())
	{
	s=s+st.nextElement();
	}
    param=s;
	return param;
  }

  

}

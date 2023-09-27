<HTML>
<HEAD>
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>ErrorPage.jasp</TITLE>
</HEAD>
<BODY>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<H1>Sample Error Page</H1>

<%
Enumeration e = request.getAttributeNames();
while(e.hasMoreElements()){
   String name = (String)e.nextElement();
   Object attribute = request.getAttribute(name);
   if(attribute instanceof Throwable){
      Throwable error = (Throwable)attribute;
%>
<HR>
Exception:
<%=error.getLocalizedMessage()%>
<BR>
Stack Trace:
<PRE>
<%
   error.printStackTrace(new PrintWriter(out)); 
%>
   </PRE>
<%
   }
}
%>

</BODY>
</HTML>

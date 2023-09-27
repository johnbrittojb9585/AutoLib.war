<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.MemberReport.MemberReportBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:order_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg"  onload="focuss()" >
<form name=payment_report_find method=post action=./PaymentInfoSearchServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String SQLstr="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}
else if (flag.equals("name")){
Title="Member Name";t1="Member_Code";t2="Member_Name";t3="Enroll_Date";
}


}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Member Search</h2></div>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=sflag value="<%=flag%>">
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
	int j=1;
    %>
  <script language="JavaScript">
document.payment_report_find.name.value="<%=request.getParameter("nam")%>";
document.payment_report_find.name.focus();
</script>
<%

  if (flag.equals("name")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Member Code<th>Member Name</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberReportBean view=(MemberReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMname()%>","<%=view.getMcode()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=j++%>" +"</td>");					
		 document.write("<td>"+"<%=view.getMcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getMname()%>" +"</td>");		 
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

	

	


		}
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
%>

<script  language="javascript">
function show(vname,vcode){
if(document.payment_report_find.sflag.value=="name"){
window.opener.document.Payment.name.value=vname;
//window.opener.document.Payment.mcode.value=vcode;
}
window.close();
}
function focuss(){
document.payment_report_find.name.focus();
}
function search(){
document.payment_report_find.submit();
}
</script>

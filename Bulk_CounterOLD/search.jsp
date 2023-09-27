<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Lib.Auto.Designation.DesignationBean" import="Lib.Auto.Subject.subjectbean" import="Lib.Auto.Member.MemberBean" import="Lib.Auto.Department.DepartmentBean" import="Common.Security" import="java.util.ArrayList,java.util.Iterator"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Subject_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name="Member_Find" method="post" action=./SearchBulkCounterMember  ><!--onsubmit="return validate(this)"-->
<%
String flag=request.getParameter("flag");
//out.println(flag);
String SQLstr="";
String Title="";
String t1="",t2="";
String mainTitle="";
if (flag!=null){
if (flag.equals("Member")){
mainTitle="Member"; Title="Member&nbsp;Name";t1="Member&nbsp;Code";t2="Designation&nbsp;Code";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
}

%>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<h2><%=mainTitle%>&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>
<input type=text name=name>
<input Class="submitButton" type=submit value="Search" >
</center>
<input type=hidden name=sflag value="<%=flag%>">
</center></form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%


String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
         	 int i = 1;
             sc=(ArrayList)request.getAttribute("serarch");

	     	     %>
  <script language="JavaScript">

document.Member_Find.name.value="<%=request.getParameter("nam")%>";
document.Member_Find.name.focus();
</script>
<%
if (flag.equals("Member")){
		//sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+t1+"</th><th>"+Title+"</th></tr>");

		
		Iterator it=sc.iterator();
		while(it.hasNext()){
			MemberBean view=(MemberBean) it.next();
			  %>
					<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
					<script language=javascript>
					 document.write("<td>"+"<%=i++%>" +"</td>");	
					 document.write("<td>"+"<%=view.getCode()%>" +"</td>");
					 document.write("<td>"+"<%=view.getName()%>" +"</td>");
					 //document.write("<td>"+"&nbsp;<%=view.getDecode()%>"+"</td>");
					 document.write("</tr>");
			 		</script>
					<%

					}
					}
sc.clear();

}
 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.counter.mcode.value=val;
window.opener.document.counter.method="post";
window.opener.document.counter.flag.value="member";
window.opener.document.counter.action="./BulkCounterServlet";
window.opener.document.counter.submit();
window.close();
}
function validate()
{
if(t1())
{
alert("Please enter the Name");
return false;
}
 else
{
return true;
}

   }
function t1()
{
if(document.Member_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Member_Find.name.focus();
}
function search(){
document.Member_Find.submit();
}
</script>

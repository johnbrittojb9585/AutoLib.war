<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.Member.MemberBean" import="Lib.Auto.NoDues.NoDuesBean"%>
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
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()" >
<form name=NoDuesFind method=post action=./NoDuesSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("member")){
Title="Member Name";t1="Member_Code";t2="Member_Name";t3="Enroll_Date";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div align=center><h2>Member&nbsp;Search</h2></div>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=sflag value="<%=flag%>">
</form>
</body>
</html>
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){

  if (flag.equals("member")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Member Code<th>Member Name<th>Enroll Date</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberBean view=(MemberBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getCode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getName()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getEdate()%>"+"</td>");
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
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<script language="JavaScript">
function show(vname){
window.opener.document.nodues.txtUserId.value=vname;
window.opener.document.nodues.method="post";
window.opener.document.nodues.Flag_Search.value="search";
window.opener.document.nodues.submit();
window.close();
}
function focuss(){
document.NoDuesFind.name.focus();
}
</script>

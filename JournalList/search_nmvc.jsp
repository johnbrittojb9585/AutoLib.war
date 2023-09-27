<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.JournalList.JournalListBean"%>
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
<body background="/AutoLib/soft.jpg" onload="focuss()" >
<form name=JournalListFind method=post action=./JournalListSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="",t4="";
if (flag!=null){
if (flag.equals("dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
t4="Department";
}
if (flag.equals("sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
t4="Subject";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=t4%>&nbsp;Search</h2>
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
	int i=1;	
 %>
  <script language="JavaScript">
document.JournalListFind.name.value="<%=request.getParameter("nam")%>";
document.JournalListFind.name.focus();
</script>
<%

  		if (flag.equals("dept")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Department Name<th>Short Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				JournalListBean view=(JournalListBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getDname()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getDcode()%>" +"</td>");
 		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getDname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("sub")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Subject Name<th>Short Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				JournalListBean view=(JournalListBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getSname()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getScode()%>" +"</td>");
  		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getSname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getSdesc()%>"+"</td>");
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
function show(vname){
if(document.JournalListFind.sflag.value=="dept"){
window.opener.document.jnllist.dept_name.value=vname;
}
if(document.JournalListFind.sflag.value=="sub"){
window.opener.document.jnllist.sub_name.value=vname;
}
window.close();
}
function focuss(){
document.JournalListFind.name.focus();
}
function search(){
document.JournalListFind.submit();
}
</script>

<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.OrdInvProcessing.orderbean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<%//Security.checkSecurity(1,session,response,request);%>
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
<form name=ord_find method=post action=./OrderInvServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("Acc")){
Title="Title";t1="Access_No";t2="Author_Name";t3="Call_No";
}
else if (flag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}
else if (flag.equals("Aut")){
Title="Author Name";t1="Author_Name";t2="Short_Desc";t3="Emailid";
}
else if (flag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
}
else if (flag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("Pub")){
Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("Group")){
Title="Group Name";t1="Group_Code";t2="Group_Name";t3="Remarks";
}

else if (flag.equals("Inv")){
Title="Invoice";t1="Inv_no";t2="Sup_code";t3="inv_date";
}

else if (flag.equals("Bud")){
Title="BUDGET HEAD";t1="bud_code";t2="bud_head";t3="bud_Amount";
}

     else if(flag.equals("Slno"))
	{
	Title="Serial No   ";t1="sno";t2="Ord_No";t3="Ord_Date";
	}

	else if (flag.equals("Dept_Report")){
         Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
                   }

        else if (flag.equals("Sup_Report")){
        Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
              }

	      else if (flag.equals("Budget_Report")){
          Title="BUDGET HEAD";t1="bud_code";t2="bud_head";t3="bud_Amount";
                }



}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 > Order Invoice  Master   Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name onkeyup=search()>
<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=flag value="<%=flag%>">
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
                      %>
  <script language="JavaScript">
document.ord_find.name.value="<%=request.getParameter("name")%>";
document.ord_find.name.focus();
</script>
<%

  if (flag.equals("Dept")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Dept Code<th>Dept Name<th>Short Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIdcode()%>","<%=view.getIdname()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIdcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIdname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getShort_desc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Dept_Report")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Dept Code<th>Dept Name<th>Short Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIdname()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIdcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIdname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getShort_desc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Slno")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Slno Code<th>Ord No<th>Ord Date</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getSno()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getSno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIord()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getIordate()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}


		if (flag.equals("Sup")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>SP_Code<th>SP_Name<th>Short_Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();

				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIscode()%>","<%=view.getIsname()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIscode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIsname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getShort_desc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Sup_Report")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>SP_Code<th>SP_Name<th>Short_Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();

				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIsname()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIscode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIsname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getShort_desc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Bud")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Bud_Code<th>Bud_Head<th>Amount</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();

				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIbcode()%>","<%=view.getIbhead()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIbcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIbhead()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getIamount()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Budget_Report")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Bud_Code<th>Bud_Head<th>Amount</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();

				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIbhead()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIbcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIbhead()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getIamount()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Inv")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Inv_no<th>Sup_code<th>inv_date</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				orderbean view=(orderbean) it.next();

				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIinvno()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getIinvno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getIscode()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getIinvdate()%>"+"</td>");
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
function show(val,vname){
if(document.ord_find.flag.value=="Slno"){
window.opener.document.ordinv.slno.value=val;
window.opener.document.ordinv.flag.value="search";
window.opener.document.ordinv.submit();
}
if(document.ord_find.flag.value=="Dept"){
window.opener.document.ordinv.dcode.value=val;
window.opener.document.ordinv.dname.value=vname;
}
if(document.ord_find.flag.value=="Dept_Report"){
window.opener.document.Inv_Report.dept.value=val;
}
if(document.ord_find.flag.value=="Sub")
window.opener.document.jrl.jsub.value=val;

if(document.ord_find.flag.value=="Pub"){
window.opener.document.jrl.jpub.value=val;
}
if(document.ord_find.flag.value=="Sup"){
window.opener.document.ordinv.scode.value=val;
window.opener.document.ordinv.sname.value=vname;
}
if(document.ord_find.flag.value=="Sup_Report"){
window.opener.document.Inv_Report.supplier.value=val;
}
if(document.ord_find.flag.value=="Bud"){
window.opener.document.ordinv.bcode.value=val;
window.opener.document.ordinv.bname.value=vname;
}
if(document.ord_find.flag.value=="Budget_Report"){
window.opener.document.Inv_Report.budget.value=val;
}
window.close();
}
function focuss(){
document.ord_find.name.focus();
}
function search(){
document.ord_find.submit();
}
</script>

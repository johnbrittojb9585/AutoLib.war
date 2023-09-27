<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.Indent_Mas.IndentMasDetailsBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<%//Security.checkSecurity(1,session,response,request);%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.YEAR, 1); // to get previous year add -1
    cal.add(Calendar.DATE, -1);	
    
    java.util.Date nextYear = cal.getTime();
	String todatestring= sdf.format(nextYear);

%>
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
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<!--<body background="/AutoLib/soft.jpg"  onload="focuss()" >-->
<body background="/AutoLib/soft.jpg">
<form name=ord_find method=post action=./IndentMasServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String check=request.getParameter("check");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
	
if (flag.equals("Pub")){
Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("Member")){
	Title="Member Name";t1="Member_Code";t2="Member_Name";t3="Designation";
}
else if (flag.equals("Dept")){
	Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}
else if (flag.equals("Author")){
	Title="Author Name";t1="Author_Code";t2="Author_Name";t3="Short_Desc";
}
else if (flag.equals("Title")){
	Title="Title";t1="Author_Code";t2="Author_Name";t3="Short_Desc";
}
else if (flag.equals("IndentNo")){
	Title="Member Name";t1="Indent_No";t2="Member_Name";t3="Member_Code";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Indent Master   Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name onkeyup=search()>
<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=flag value="<%=flag%>">
<input type=hidden name="flag1" >
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=null;
if(!request.getParameter("check").equals("CommonSubsdt")){
 ck=request.getParameter("check");
}
ArrayList sc=new ArrayList();
try{
if(ck!=null){
                      %>
  <script language="JavaScript">
document.ord_find.name.value="<%=request.getParameter("name")%>";
document.ord_find.name.focus();
</script>
<%

		if (flag.equals("Pub")){

           sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>SP_Code<th>SP_Name<th>Short_Desc</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
				
				%>				
				
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMemcode()%>","<%=view.getMemname()%>")'>
		<script language=javascript>	 
		 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getFlag()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		}



		if (flag.equals("Member")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Member_Code<th>Member_Name<th>Designation</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMemcode()%>","<%=view.getMemname()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");		
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");			 
			 document.write("<td>"+"&nbsp;<%=view.getFlag()%>"+"</td>");			 	 			 
			 document.write("</tr>");		
	 		</script>
			<%
			}
			sc.clear();
			}

		
		if (flag.equals("Dept")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Dept_Code<th>Dept_Name<th>Short_Desc</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMemcode()%>","<%=view.getMemname()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");				
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");
			 document.write("<td>"+"&nbsp;<%=view.getFlag()%>"+"</td>");			 
			 document.write("</tr>");		
	 		</script>
			<%
			}
			sc.clear();
			}
		
		if (flag.equals("Author")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Author_Code<th>Author_Name<th>Short_Desc</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMemcode()%>","<%=view.getMemname()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");				
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");
			 document.write("<td>"+"&nbsp;<%=view.getFlag()%>"+"</td>");			 
			 document.write("</tr>");		
	 		</script>
			<%
			}
			sc.clear();
			}
		
		if (flag.equals("Title")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Title</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("","<%=view.getMemname()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");
			 document.write("</tr>");		
	 		</script>
			<%
			}
			sc.clear();
			}
		
		if (flag.equals("IndentNo")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Indent_No<th>Member_Name<th>Member_Code</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getFlag()%>","<%=view.getMemcode()%>")'>
			<script language=javascript>	 			 

			 document.write("<td>"+"<%=view.getFlag()%>"+"</td>");
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");				 
			 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");		
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
function test() {
    var cboxes = document.getElementsByName('selectedIds[]');
    var len = cboxes.length;
    
    var cnames = document.getElementsByName('selectednames[]');
  
   var content="";
   var res=[];
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked)
    {
        //alert(i + (cboxes[i].checked?' checked ':' unchecked ') + cboxes[i].value+" And "+cnames[i].value);
       
      content=content+","+cboxes[i].value;      

    } 
      
    }    
   
    if(content)   {    
       window.opener.document.ordinv.flag.value="selectV";
       window.opener.document.ordinv.flag1.value=content;
       window.opener.document.ordinv.csubsdate.value=document.ord_find.csubsdate.value;
       window.opener.document.ordinv.cstodate.value=document.ord_find.cstodate.value;        
       window.opener.document.ordinv.submit();    
       window.close();
    }else {
      alert("Invalid Selection !");
    }  
   
}


function show(val,vname){

if(document.ord_find.flag.value=="Pub"){
window.opener.document.ordinv.pubname.value=vname;
window.opener.document.ordinv.pubCode.value=val;
}

if(document.ord_find.flag.value=="Member"){
window.opener.document.ordinv.member.value=vname;
window.opener.document.ordinv.memberCode.value=val;
}

if(document.ord_find.flag.value=="Dept"){
window.opener.document.ordinv.department.value=vname;
window.opener.document.ordinv.deptCode.value=val;
}

if(document.ord_find.flag.value=="Author"){

if(window.opener.document.ordinv.author.value!=""){

   window.opener.document.ordinv.author.value=window.opener.document.ordinv.author.value+";"+vname;
   window.opener.document.ordinv.authorCode.value=window.opener.document.ordinv.authorCode.value+";"+val;
   
}else {

   window.opener.document.ordinv.author.value=vname;
   window.opener.document.ordinv.authorCode.value=val;
}
}

if(document.ord_find.flag.value=="Title"){
window.opener.document.ordinv.title.value=vname;
}
if(document.ord_find.flag.value=="IndentNo"){
window.opener.document.ordinv.indtno.value=val;
window.opener.document.ordinv.flag.value="Search";
window.opener.document.ordinv.submit();
}

window.close();
}
function focuss(){
document.ord_find.name.focus();
}
function search(){
//document.ord_find.submit();
}
</script>


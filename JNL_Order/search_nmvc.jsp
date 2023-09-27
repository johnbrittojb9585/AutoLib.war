<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.JNL_Order.JnlorderDetailsBean"%>
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
<form name=ord_find method=post action=./OrderJNLServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String check=request.getParameter("check");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
	
if (flag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("Journal")){
Title="Journal Name";t1="JNL_Code";t2="JNL_Name";t3="Short_Desc";
}
else if (flag.equals("OrderNo")){
	Title="Order Number";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("EnquiryNo")){
	Title="Enquiry Number";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}

}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Journal Order Master   Search</h2>
<%if (flag.equals("Journal") && check.equals("ok")){ %>
<table align="center">    
<tr>    
    <td Class="addedit">Subs. From:</td>
        <TD>
	<INPUT name=csubsdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ord_find.csubsdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
	<td Class="addedit">Subs. To:</td>
        <TD>
	<INPUT name=cstodate size=10  onfocus=this.blur(); value="<%=todatestring%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ord_find.cstodate, \"dd-mm-yyyy\",\"<%=todatestring%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>	 
    </tr>
    </table>
<%} %>    
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
	int i=1;
                      %>
  <script language="JavaScript">
document.ord_find.name.value="<%=request.getParameter("name")%>";
document.ord_find.name.focus();
</script>
<%

		if (flag.equals("Sup")){

           sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>S.No<th>SP_Name<th>Short_Desc</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				JnlorderDetailsBean view=(JnlorderDetailsBean) it.next();
				
				%>				
				
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getJournal()%>","<%=view.getJnlCode()%>")'>
		<script language=javascript>	 
		 //document.write("<td>"+"<%=view.getJnlCode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getJournal()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		}



		if (flag.equals("Journal")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>ID<th>JNL_Code<th>Journal_Name<th>Short_Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				JnlorderDetailsBean view=(JnlorderDetailsBean) it.next();

				%>
		<script language=javascript>
		 document.write("<tr>");   
 		 document.write("<td><input type='checkbox' id='selectedIds[]' name='selectedIds[]' value='<%=view.getJnlCode()%>' /></td>");
		 document.write("<td>"+"<%=view.getJnlCode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getJournal()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
		 document.write("</tr>");
		 document.write("<input type='hidden' id='selectednames[]' name='selectednames[]' value='<%=view.getJournal()%>' />");
 		</script>
		<%
		}
		sc.clear();
		%><script type="text/javascript" src="JNL_Order/index.jsp"></script>
    <script type="text/javascript">
     document.write("<center><input type='button' onclick='test()'>Go!</center>");
       
    </script><%
		}
		
		
		if (flag.equals("OrderNo")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>OrderNo<th>SP_Name</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					JnlorderDetailsBean view=(JnlorderDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAdd1()%>","<%=view.getJnlCode()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
			 document.write("<td>"+"<%=view.getJournal()%>" +"</td>");
			 //document.write("<td>"+"<%=view.getJnlCode()%>" +"</td>");			 			 
			 document.write("</tr>");		
	 		</script>
			<%
			}
			sc.clear();
			}

		if (flag.equals("EnquiryNo")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>EnquiryNo<th>SP_Name</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					JnlorderDetailsBean view=(JnlorderDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAdd1()%>","<%=view.getJnlCode()%>")'>
			<script language=javascript>	 			 
			 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
			 document.write("<td>"+"<%=view.getJournal()%>" +"</td>");
			 //document.write("<td>"+"<%=view.getJnlCode()%>" +"</td>");			 			 
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
       
      content=content+",'"+ cboxes[i].value +"'";      
      
           
      //content="-"+content; 
                                     
      //document.ord_find.flag1.value=cboxes[i].value;  
    } 
      
    }
    
   <%
    String [] a=request.getParameterValues("res[]");

   %>
   
    //document.ord_find.flag.value="selectV";
   // document.ord_find.flag1.value=content;
    //document.ord_find.submit();
    
    
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


/*
function test() {
    var cboxes = document.getElementsByName('selectedIds[]');
    var len = cboxes.length;
    
    var cnames = document.getElementsByName('selectednames[]');
   
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked)
    {
       // alert(i + (cboxes[i].checked?' checked ':' unchecked ') + cboxes[i].value);
       
       
    var combo = window.opener.document.getElementById("jdeli");
     
    var option = window.opener.document.createElement("option");
    option.text = cnames[i].value;
    option.value = cboxes[i].value;
    try {
        combo.add(option, null); //Standard
    }catch(error) {
        combo.add(option); // IE only
    }
    


    }
    window.close();
    }
}*/

function show(val,vname){
if(document.ord_find.flag.value=="Journal"){
window.opener.document.ordinv.jnlname.value=val;
}
if(document.ord_find.flag.value=="Sup"){
window.opener.document.ordinv.sname.value=val;
window.opener.document.ordinv.sup_code.value=vname;
}
if(document.ord_find.flag.value=="OrderNo"){
window.opener.document.ordinv.ordno.value=val;
window.opener.document.ordinv.sup_code.value=vname;
window.opener.document.ordinv.method="get";
window.opener.document.ordinv.flag.value="search";
window.opener.document.ordinv.submit();
}
if(document.ord_find.flag.value=="EnquiryNo"){
window.opener.document.ordinv.quoteno.value=val;
window.opener.document.ordinv.sup_code.value=vname;
window.opener.document.ordinv.method="get";
window.opener.document.ordinv.flag.value="EnquiryNoListView";
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


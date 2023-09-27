<%String URole = session.getAttribute("UserRights").toString().trim();
			if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")
					|| URole.equalsIgnoreCase("6")
					|| URole.equalsIgnoreCase("7")) {
				response.sendRedirect("sessiontimeout.jsp");
			}

			%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.util.*" session="true" buffer="12kb"
	import="Common.Security"%>


<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript"
	src="<%= request.getContextPath() %>/script/common.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/script/tamil.js"></script>

<jsp:useBean id="beanobject" scope="request"
	class="Lib.Auto.TransMas.TransMasBean"
	type="Lib.Auto.TransMas.TransMasBean">
</jsp:useBean>


<html>
<%
response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>


<body onload="load()">

<form name="TransMas" method="post" action=./TransMasServlet><br>
<P ALIGN="left"><BR>
<h2>Transaction&nbsp;Master</h2>
<table align="center" class="contentTable" width="45%">
	<td>
	<table align="center" width="90%">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td Class="addedit">Trans.&nbsp;No.
			<td><input type=text name=tcode size=10 maxlength=8
				onKeyUp="return Trans_code_val();"
				onKeydown="Javascript: if (event.keyCode==13) SearchRec();"></td>
		</tr>

		<tr>
			<td Class="addedit">Trans&nbsp;Head
			<td><select name="thead" size="1" id="thead">
				<option value="Photocopy">Photocopy</option>
				<option value="Printout">Printout</option>
				<option value="Color Print">Color Print</option>
				<option value="Strip Binding">Strip Binding</option>
				<option value="Stick Binding">Stick Binding</option>
				<option value="Spiral Binding">Spiral Binding</option>
				<option value="Recovery">Recovery</option>
				<option value="Loss of Resource">Loss of Resource</option>
				<option value="Others">Others</option>
			</select></td>
		</tr>

		<tr>
			<td Class="addedit">Amount
			<td><input type=text name=tamount size=50 maxlength="50"
				onKeyUp="return Trans_amount_val();"></td>
		</tr>

		<tr>
			<td Class="addedit">Remarks
			<td><input type=text name=tremarks size=50 maxlength="50"></td>
		</tr>

		<tr>
			<td Class="addedit">Add. Field
			<td><input type=text name=tadd1 size=50 maxlength="50"></td>
		</tr>

		<tr>
			<td colspan=3 align=center>
			<CENTER><input type=button name=New Class="submitButton" Value=New
				onclick=new_no()> <input type=button name=Save Class="submitButton"
				value=Save onclick=SaveRec()> <input type=button name=Delete
				Class="submitButton" Value=Delete onclick=DelRec()> <input
				type=submit name=search Class="submitButton" Value=Search
				onclick=SearchRec()> <input type=button name=Clear
				Class="submitButton" Value=Clear onclick=ClearRec()></CENTER>
			<input type=hidden name=flag>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	</td>
</table>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String valid = request.getParameter("check");

			if (valid != null) {
				if (request.getParameter("check") != null) {
					if (valid.equals("newTransCode")) {

						%>
<script language="JavaScript">
document.TransMas.tcode.value="<%=beanobject.getTcode()%>";
document.TransMas.tamount.focus();
</script>
<%
}

					if (valid.equals("SaveTransMas")) {

					%>
<script language="JavaScript">
				 alert("Record Inserted Successfully!");
				 document.TransMas.flag.value="new";
				 document.TransMas.submit();
			     </script>
<%}

					if (valid.equals("searchTransMas")) {

						%>
<script language="JavaScript">

					document.TransMas.tcode.value="<%=beanobject.getTcode()%>";
					document.TransMas.thead.value="<%=beanobject.getThead()%>";
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";					
					</script>
<%}

					if (valid.equals("FailTransMas")) {%>
<script language="JavaScript">
				alert("Record Not Found");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
			   	</script>
<%}

					if (valid.equals("deleteCheck")) {

						%>
<script language="JavaScript">
				    document.TransMas.tcode.value="<%=beanobject.getTcode()%>";
					document.TransMas.thead.value="<%=beanobject.getThead()%>";
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";	
				      msg=confirm("Are You Sure To Delete?");
						if(msg){
						 document.TransMas.flag.value="Confirmdete";
						 document.TransMas.submit();	
						}
				 </script>
<%}

					if (valid.equals("RecordNot")) {

						%>
<script language="JavaScript">
				    document.TransMas.tcode.value="<%=beanobject.getTcode()%>";
					document.TransMas.thead.value="<%=beanobject.getThead()%>";
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";	
					 alert("Record Not Present!!!");
				 </script>
<%}

					if (valid.equals("DeleteTransMas")) {

					%>
<script language="JavaScript">
   							alert("Record Deleted Successfully!");
   							document.TransMas.flag.value="new";
   							document.TransMas.submit();
   						   </script>
<%}

					if (valid.equals("UpdateCheck")) {

						%>
<script language="JavaScript">
   				  			document.TransMas.tcode.value="<%=beanobject.getTcode()%>";
   				            document.TransMas.thead.value="<%=beanobject.getThead()%>";
   				            document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
   				            document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
   				            document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";   				            
   					
   				            msg=confirm("TransHead name Already Exists in code:"+"<%=beanobject.getTcode()%>"+",Do You Want update other than name ?");
			
			                if(msg){
                           			 document.TransMas.flag.value="update";
		                             document.TransMas.submit();				
			                       } 						           
   				   </script>

<%}

					if (valid.equals("UpdateTransMas")) {%>
<script language="JavaScript">
			alert("Record Updated Successfully!");
			document.TransMas.flag.value="new";
			document.TransMas.submit();
		   	</script>
<%}

				}
			}

		%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
<script language=javascript>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.TransMas.method="get";
document.TransMas.flag.value="new";
document.TransMas.submit();
}


function SearchRec(){
document.TransMas.method="post";
var no=document.TransMas.tcode.value;
if(no==""){
				document.TransMas.tcode.focus();
				alert("Insufficent Data");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
		  }else{
		       document.TransMas.flag.value="search";
			   document.TransMas.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.TransMas.method="post";
			   if(!isWhitespace(document.TransMas.tamount.value)){
			   			   
			    	document.TransMas.flag.value="save";
		         	document.TransMas.submit();	
					
			}		
	else
	{	
	 alert("Insufficent Data");
	}
		}		
	else
	{	
	 alert("Insufficent Data");
	}
}

function chk_mc(){
var flag_cs;
var c;
var mc=document.TransMas.tcode.value;
if(mc=="")
{
				document.TransMas.tcode.focus();
				document.TransMas.flag.value="none";
				document.TransMas.tcode.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }








function UpdateRec(){
             document.Author.method="post";
			   if(chk() ){
			       	document.Author.flag.value="update";
		         	document.Author.submit();	
					
			}		
	else
	{
	alert("Insufficent Data");
	}
	
}

function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
		

function Trans_code_val() {
if((isNaN(document.TransMas.tcode.value))||(document.TransMas.tcode.value == ' ')) {
document.TransMas.tcode.select();
document.TransMas.tcode.value="";
    
  }
}

function Trans_amount_val() {
if((isNaN(document.TransMas.tamount.value))||(document.TransMas.tamount.value == ' ')) {
document.TransMas.tamount.select();
document.TransMas.tamount.value="";
    
  }
}

function ClearRec(){ 
  
		 document.TransMas.action="index.jsp";
		 document.TransMas.submit();

}



function DelRec(){
document.TransMas.method="post";
if (document.TransMas.tcode.value==""){
				document.TransMas.tcode.focus();
				alert("Insufficent Data");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
				}
				else{
				  document.TransMas.flag.value="delete";
				  document.TransMas.submit();
				 }                         
				
}
			


</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->


<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Login.LoginBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form password:Login
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<form name=Login method="post" action=./LoginServlet>
<br><br><br>

<h2>Login Master</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Login Id<td colspan="2" >
<input type=text name=code size=25  maxlength=20  >
<input type=button name=find Value="Find" Class="submitButton" onclick=FindCourse('Login')>
    </font>
<tr><td Class="addedit">Password<td colspan="2" >
<input type=password name=pass size=33 maxlength=20>
    <tr><td Class="addedit">Staff Name<td colspan="2">
<input type=text name=name size=33 maxlength=30>
    <tr><td Class="addedit">Rights<td colspan="2" >
<select name="rights" size="1">
        	                <OPTION VALUE="1">ADMIN-I</OPTION>
                	        <OPTION VALUE="2">ADMIN-II</OPTION>
				<OPTION VALUE="3">DATA ENTRY</OPTION>
				<OPTION VALUE="4">JOURNAL</OPTION>
				<!-- <OPTION VALUE="5">ACQUISITION</OPTION> -->
				<OPTION VALUE="6">COUNTER</OPTION>
				<OPTION VALUE="7">PUBLIC</OPTION>
				<OPTION VALUE="8">E-GATE</OPTION>				
                 </SELECT>

<tr><td Class="addedit">Valid Flag<td colspan="2" >
  <select name="flag1" size="1">
  <option selected value="YES">YES</option>
 <option value="NO">NO</option>
  </select>

<tr><td colspan=3 align=center >
<center>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<input type=submit name=search Class="submitButton" Value=Search onclick=SearchRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear >
</center>
<input type=hidden name=flag >

</table></center></td></table></center></form></body></html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("searchlogin")){
 %>
  <script language="JavaScript">
document.Login.code.value="<%=bean.getCode()%>";
document.Login.name.value="<%=bean.getName()%>";
document.Login.pass.value="<%=bean.getPass()%>";
document.Login.rights.value="<%=bean.getRights()%>";
document.Login.flag1.value="<%=bean.getFlag()%>";
</script>
<%
}
if(valid.equals("FailLogin")){%>
            <script language="JavaScript">
			alert("Record Not Found");

			</script><%
			}
	if(valid.equals("UpdateLogin")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");

		   	</script><%
			}
if(valid.equals("CodeCompareLogin")){
String  Autcode=(String)request.getAttribute("Logid");
%>
            <script>
			alert("Login name Already Exists in code:"+"<%=Autcode%>");

			</script><%
			}
if(valid.equals("SaveLogin")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");

		     </script>
			<%
			}
if(valid.equals("ReferredCourse")){
%>            <script>alert("You can't delete since the Login has been referred in other masters");

			</script>	
			<%
			}
			
			if(valid.equals("DeleteLogin")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">
	        	document.Login.code.value="<%=bean.getCode()%>";
                document.Login.name.value="<%=bean.getName()%>";
                document.Login.pass.value="<%=bean.getPass()%>";
				document.Login.rights.value="<%=bean.getRights()%>";
				document.Login.flag1.value="<%=bean.getFlag()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Login.flag.value="Confirmdete";
		   	document.Login.submit();
			
			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
			    document.Login.code.value="<%=bean.getCode()%>";
                document.Login.name.value="<%=bean.getName()%>";
                document.Login.pass.value="<%=bean.getPass()%>";
				document.Login.rights.value="<%=bean.getRights()%>";
				document.Login.flag1.value="<%=bean.getFlag()%>";

			    alert("Record Not Present!!!");
			</script>		
			<%
			}
		
			
if(valid.equals("UpdateCheck")){
%> 
                <script language="JavaScript">
				document.Login.code.value="<%=bean.getCode()%>";
                document.Login.name.value="<%=bean.getName()%>";
                document.Login.pass.value="<%=bean.getPass()%>";
				document.Login.rights.value="<%=bean.getRights()%>";
				document.Login.flag1.value="<%=bean.getFlag()%>";

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Login.flag.value="update";
		         	document.Login.submit();

		            }
			    </script>	
			 	
			<%
			}			
			
			

}
}
%>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
<script language=javascript>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function FindCourse(val)
{
winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}


function SaveRec(){

document.Login.method="post";
if (document.Login.code.value==""){
				document.Login.code.focus();
				alert("Insufficent Data");
				return false;
				}
				else if(document.Login.pass.value=="")
				{
				alert("Please Enter Password");
				}
				else
				{
				document.Login.flag.value="save";
				document.Login.submit();
				}
}
function SearchRec(){

document.Login.method="post";
if (document.Login.code.value==""){
				alert("Please Enter Login Id");
				document.Login.flag.value="new";
			    document.Login.submit();
		  }
		  else
		  {
			document.Login.flag.value="search";
			document.Login.submit();
		}

}
function DelRec(){

document.Login.method="post";

		if (document.Login.code.value==""){
				document.Login.code.focus();
				alert("Insufficent Data");
				return false;
				}
			else{
document.Login.flag.value="delete";
document.Login.submit();
						 
				              
              }
}
function chk(){
var flag_s;
var i;
var sp=document.Login.pass.value;
if(sp=="")
{
document.Login.pass.focus();
				document.Login.flag.value="none";
				document.Login.pass.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Login.pass.value.length;i++)
 	                      {
 	                       if(document.Login.pass.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Login.pass.focus();
		                   	document.Login.pass.value="";
			                return false;
		                      }
		                   else if (document.Login.code.value==""){
		                 	document.Login.code.focus();
			                return false;
		                      }
        else{
		return true;
		} 
     }
 }

function check()
{
if(updatecheck()){
  msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)    
				 {
                            document.Login.flag.value="save";
                             }
                             else
	                         {
	                        document.Login.flag.value="cancel";
	                         }
	    }
		  else
            {
              document.Login.flag.value="save";
             }
			 return true;  
}
function load()
{
 document.Login.code.focus();
}

</script>
 <!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="Lib.Auto.Account.AccountBean"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Account.AccountBean"  type="Lib.Auto.Account.AccountBean">

</jsp:useBean>



<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Author
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">


</head>
<body>

<form name="Account" method="post" action=./AccountServlet>
<br>
<!--<center>
<img border='0' src='/AutoLib/images/home.gif' onclick='home()'>
<img border='0' src='/AutoLib/images/Logout.gif' onclick='Logout()'>
</center>-->
 <P ALIGN="left"><BR><BR>
<h2>Transaction&nbsp;Details</b></h2>
 <center>
<table align="center" class="contentTable" width="55%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<tr>&nbsp;&nbsp;&nbsp;&nbsp;<td Class="addfont">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="Verdana" color="#2B1B17" size=1>ID  :&nbsp;&nbsp;&nbsp;<%=beanobject.getuid() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td><td Class="addfont">&nbsp;<font face="Verdana" color="#2B1B17" size=1>Name :<%=beanobject.getuname()%>
</font></td></tr>
<tr>
<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="AccountServlet?issueuserid=<%=beanobject.getuid() %>"><font face="Verdana" color="#0000FF" size=4>Issued (<%=beanobject.getissuecount() %>)</font></a></td>
<tr>
<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<a href="AccountServlet?returnuserid=<%=beanobject.getuid() %>"><font face="Verdana" color="#0000FF" size=4>Returned ( <%=beanobject.getreturncount() %>)</font></a></td>
<tr>
<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<a href="AccountServlet?reserveuserid=<%=beanobject.getuid() %>"><font face="Verdana" color="#0000FF" size=4>Reserved ( <%=beanobject.getreservecount() %>)</font></a></td>
</tr>
<tr><td colspan=3 align=center >
<CENTER>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>



</CENTER>
<input type=hidden name=flag>
</table>
</CENTER>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<!--
///////////////////////////////////////JAVA SCRIPT USING CLIENT SIDE///////////////////////////////////////////////// --> 
<script language=javascript>

function Logout()
{
location.href="/<%= request.getContextPath() %>/Account/index.jsp";
}
function home()
{
location.href="/AutoLib/Home.jsp";
}



function new_no(){
document.Author.method="get";
document.Author.flag.value="new";
document.Author.submit();
}

function SearchRec(){
document.Author.method="post";
var no=document.Author.code.value;
if(no==""){
				document.Author.code.focus();
				alert("Insufficent Data");
				document.Author.flag.value="new";
				document.Author.submit();
		  }else{
		       document.Author.flag.value="search";
			   document.Author.submit();
			  
		}
	
}


function SaveRec(){

            
			   if((document.Account.uid.value=="")||(document.Account.pwd.value=="")){
			    	
					alert("Insufficent Data");
			}		
	else
	{
					document.Account.flag.value="save";
		         	document.Account.submit();	
	
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
		
function chk(){
var flag_s;
var i;
var sp=document.Author.name.value;
var d=sp.replace(" ");
alert(d);

if(d=="")
{

document.Author.name.focus();
				document.Author.flag.value="none";
				document.Author.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Author.name.value.length;i++)
 	                      {
 	                       if(document.Author.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Author.name.focus();
		                   	document.Author.name.value="";
			                return false;
		                      }
		                   else if (document.Author.code.value==""){
		                 	document.Author.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindAuthor()
{
winpopup=window.open("search.jsp","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function author_code_val() {
if((isNaN(document.Author.code.value))||(document.Author.code.value == ' ')) {
document.Author.code.select();
document.Author.code.value="";
    
  }
}
function ReportRec(){
         
			    	document.Author.flag.value="Report";
		         	document.Author.submit();	
					
			
	
}
function ClearRec(){ 
  
		 document.Author.code.value="";
         document.Author.name.value="";
         document.Author.desc.value="";
         document.Author.email.value="";
		 document.Author.flag.value="";

}
function DelRec(){
document.Author.method="post";
if (document.Author.code.value==""){
				document.Author.code.focus();
				alert("Insufficent Data");
				document.Author.flag.value="new";
				document.Author.submit();

				}
				else{
				  document.Author.flag.value="delete";
				  document.Author.submit();
				 }                         
				
}
			
function load()
{
document.Author.code.focus();
}
function back()
{
	location.href="./index.jsp";
}


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

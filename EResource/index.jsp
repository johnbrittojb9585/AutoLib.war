<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"   import="java.util.*" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.EResource.EResourceBean"  type="Lib.Auto.EResource.EResourceBean">
</jsp:useBean>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Keywords
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
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()"><!-- onload="load()"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

	
	 
%>
<form name="EResource" method="get" action=./EResourceServlet>
<br><br><br>

 <P ALIGN="left">
 <h2>EResource&nbsp;Master</h2>
 <center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Code</td><td><input type=text name=code size=20  maxlength=5 onKeyUp="return keywords_code_val();"  >
<!-- <input type=button name=find  Value="Find" Class="submitButton" onclick="FindKeywords();"> --></td></tr>
<tr><td Class="addedit">Web&nbsp;Site</td><td><input type=text name=site size=100 maxlength="100" value=http://></td></tr>
<tr><td Class="addedit">Web&nbsp;Details</td><td><input type=text name=details size=100 maxlength="100"></td></tr>
<tr><td Class="addedit">Web&nbsp;Title</td><td><input type=text name=title size=100 maxlength="100"></td></tr>
<tr><td Class="addedit">Subject</td><td><input type=text name=subtitle size=100 maxlength="100"></td></tr>
<td Class="addedit">Type<td>
    <select name="type" size="1">
  	  <option value="E-journal">E-journal</option>
  	  <option value="E-Books">E-Books</option>
   	 <option value="E-journal Portal">E-journal Portal</option>
   	 <option value="E-Books Portal">E-Books Portal</option>
      <option value="Database">Database</option>
      <option value="Others">Others</option>
      
&nbsp; </select>
    </td>
<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton"  Value=Delete onclick=DelRec()>
<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>
<!--  <input type=button name=mail Class="submitButton"  Value=mail onclick=mailRec()>-->
</CENTER>
<input type=hidden name=flag>
</table>
</CENTER></td></table></center>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String cSec = Security.checkSecurity(1, session, response, request);
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newNews")){
 %>
<script language="JavaScript">
document.EResource.code.value="<%=BeanObject.getEcode()%>";
document.EResource.name.focus();
</script><%

}
if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
				document.EResource.code.value="<%=BeanObject.getEcode()%>";
                document.EResource.site.value="<%=BeanObject.getEsite()%>";
                document.EResource.details.value="<%=BeanObject.getEdetails()%>";
                document.EResource.title.value="<%=BeanObject.getEtitle()%>";
                document.EResource.subtitle.value="<%=BeanObject.getEsubtitle()%>";
                document.EResource.type.value="<%=BeanObject.getType()%>";
               
               
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.EResource.flag.value="update";
		         	document.EResource.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("searchKeywords")){

 %>
  <script language="JavaScript">


               document.EResource.code.value="<%=BeanObject.getEcode()%>";
                document.EResource.site.value="<%=BeanObject.getEsite()%>";
                document.EResource.details.value="<%=BeanObject.getEdetails()%>";
                document.EResource.title.value="<%=BeanObject.getEtitle()%>";
                document.EResource.subtitle.value="<%=BeanObject.getEsubtitle()%>";
                document.EResource.type.value="<%=BeanObject.getType()%>";

            

</script>
<%
}
if(valid.equals("FailKeywords")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.EResource.flag.value="new";
			document.EResource.submit();
		   	</script><%
			}
			if(valid.equals("UpdateKeywords")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.EResource.flag.value="new";
			document.EResource.submit();
		   	</script><%
			}
			if(valid.equals("SaveKeyword")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.EResource.flag.value="new";
			 document.EResource.submit();
		     </script>
			<%
			}
			if(valid.equals("AlreadyExists")){

%>             <script language="JavaScript">
			 alert("Record already exists in : "+<%=request.getParameter("no")%>);
			 document.EResource.flag.value="new";
			 document.EResource.submit();
		     </script>
			<%
			}
			if(valid.equals("CodeCompareKeyword")){

%>             <script language="JavaScript">
                document.EResource.code.value="<%=BeanObject.getEcode()%>";
                document.EResource.name.value="<%=BeanObject.getEsite()%>";
                
			 msg=confirm("Record already exists in : "+"<%=BeanObject.getEcode()%>"+",Do You Want update?");

			 if(msg)
                   {
				    document.EResource.flag.value="update";
		         	document.EResource.submit();

		            }
		     </script>
			<%
			}
			if(valid.equals("DeleteKeywords")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.EResource.flag.value="new";
			document.EResource.submit();
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){

%>
            <script language="JavaScript">
				 document.EResource.code.value="<%=BeanObject.getEcode()%>";
                document.EResource.site.value="<%=BeanObject.getEsite()%>";
                document.EResource.details.value="<%=BeanObject.getEdetails()%>";
                document.EResource.title.value="<%=BeanObject.getEtitle()%>";
                document.EResource.subtitle.value="<%=BeanObject.getEsubtitle()%>";
                document.EResource.type.value="<%=BeanObject.getType()%>";



			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.EResource.flag.value="Confirmdete";
		   	document.EResource.submit();

			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
		document.EResource.code.value="<%=BeanObject.getEcode()%>";
                document.EResource.name.value="<%=BeanObject.getEsite()%>";
                

			    alert("Record Not Present!!!");
				</script>
			<%
			}



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
document.EResource.method="get";
document.EResource.flag.value="new";
document.EResource.submit();
}

function SearchRec(){
document.EResource.method="post";
var no=document.EResource.code.value;
if(no==""){
				document.EResource.code.focus();
				alert("Insufficent Data");
				document.EResource.flag.value="new";
				document.EResource.submit();
		  }else{
		       document.EResource.flag.value="search";
			   document.EResource.submit();
			  
		}
	
}


function SaveRec(){

if(chk_mc()){

             document.EResource.method="post";
			   if(!isWhitespace(document.EResource.site.value)){
			       	document.EResource.flag.value="save";
		         	document.EResource.submit();
					
			}		
	else
	{
	alert("Insufficient Data");
	}
	}
	else
	{
	alert("Insufficient Data");
	}
}

 function chk_mc(){
var flag_cs;
var c;
var mc=document.EResource.code.value;
if(mc=="")
{
				document.EResource.code.focus();
				document.EResource.flag.value="none";
				document.EResource.code.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
function mailRec(){
             document.EResource.method="post";
			   
			       	document.EResource.flag.value="mail";
		         	document.EResource.submit();
					
			
	
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
var sp=document.EResource.site.value;
if(sp=="")
{
document.EResource.site.focus();
				document.EResource.flag.value="none";
				document.EResource.site.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.EResource.site.value.length;i++)
 	                      {
 	                       if(document.EResource.site.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.EResource.site.focus();
		                   	document.EResource.site.value="";
			                return false;
		                      }
		                   else if (document.EResource.code.value==""){
		                 	document.EResource.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindKeywords()
{
winpopup=window.open("search.jsp","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function keywords_code_val() {
if((isNaN(document.EResource.code.value))||(document.EResource.code.value == ' ')) {
document.EResource.code.select();
document.EResource.code.value="";
    
  }
}
function ClearRec(){ 
		 document.EResource.code.value="";
         document.EResource.title.value="";
         document.EResource.subtitle.value="";
			 document.EResource.title.value="";
			  document.EResource.details.value="";
		 document.EResource.flag.value="";

}
function DelRec(){

document.EResource.method="post";
if (document.EResource.code.value==""){
				document.EResource.code.focus();
				alert("Insufficent Data");
				document.EResource.flag.value="new";
				document.EResource.submit();

				}
				else{
				  document.EResource.flag.value="delete";
				  document.EResource.submit();
				 }                         
				
}
function load(){
	document.EResource.site.focus();

		 }
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

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
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.Keywords.KeywordsBean"  type="Lib.Auto.Keywords.KeywordsBean">
</jsp:useBean>



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
<meta charset="ISO-8859-1">
<title>AutoLib Software Systems</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchKeywordsMas.js"></script>
</head>
<body background="/AutoLib/soft.jpg" onload="load()"><!-- onload="load()"-->

<form name="Keywords" method="get" action=./KeywordsServlet>
<br><br><br>

 <P ALIGN="left"><BR>
 <h2>Keywords&nbsp;Master</h2>
 <center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Keyword&nbsp;Code<td><input type=text name=code size=20  maxlength=5 onKeyUp="return keywords_code_val();"  readOnly >-->
<!--<input type=button name=find  Value="Find" Class="submitButton" onclick="FindKeywords();"></td></tr>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td><input type=hidden name=code id="searchKeywordsCode" class="searchKeywords" size=20  maxlength=5 onKeyUp="return keywords_code_val();" ></td></tr>
<tr><td Class="addedit">Keyword<td><input type=text name=name id="searchKeywords" class="searchKeywords" onkeyup="showKeywords(this.value);" size=42 maxlength="50">&nbsp;

<input type=button name=find  Value="Find" Class="submitButton" onclick="FindKeywords();">
<FONT color=red size=4>*</FONT></td></tr>
<tr><td Class="addedit">Short&nbsp;Desc<td><input type=text name=desc id="searchKeywordsDesc" class="searchKeywords" size=50 maxlength="50">

   </div>
</div>

<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton"  Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>

</CENTER>
<input type=hidden name=flag>
<tr><td> &nbsp; </td></tr>
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
if(valid.equals("newKeywords")){
 %>
<script language="JavaScript">
document.Keywords.code.value="<%=BeanObject.getKcode()%>";
document.Keywords.name.focus();
</script><%

}
if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		document.Keywords.code.value="<%=BeanObject.getKcode()%>";
                document.Keywords.name.value="<%=BeanObject.getKname()%>";
                document.Keywords.desc.value="<%=BeanObject.getKdesc()%>";
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Keywords.flag.value="update";
		         	document.Keywords.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("searchKeywords")){
 %>
  <script language="JavaScript">


                document.Keywords.code.value="<%=BeanObject.getKcode()%>";
                document.Keywords.name.value="<%=BeanObject.getKname()%>";
                document.Keywords.desc.value="<%=BeanObject.getKdesc()%>";

</script>
<%
}
if(valid.equals("FailKeywords")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Keywords.flag.value="new";
			document.Keywords.submit();
		   	</script><%
			}
			if(valid.equals("UpdateKeywords")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Keywords.flag.value="new";
			document.Keywords.submit();
		   	</script><%
			}
			if(valid.equals("SaveKeyword")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Keywords.flag.value="new";
			 document.Keywords.submit();
		     </script>
			<%
			}
			if(valid.equals("AlreadyExists")){

%>             <script language="JavaScript">
			 alert("Record already exists in : "+<%=request.getParameter("no")%>);
			 document.Keywords.flag.value="new";
			 document.Keywords.submit();
		     </script>
			<%
			}
			if(valid.equals("CodeCompareKeyword")){

%>             <script language="JavaScript">
                document.Keywords.code.value="<%=BeanObject.getKcode()%>";
                document.Keywords.name.value="<%=BeanObject.getKname()%>";
                document.Keywords.desc.value="<%=BeanObject.getKdesc()%>";
			 msg=confirm("Keyword already exists ,Do You Want update other than keyword");

			 if(msg)
                   {
				    document.Keywords.flag.value="update";
		         	document.Keywords.submit();

		            }
		     </script>
			<%
			}
			if(valid.equals("DeleteKeywords")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Keywords.flag.value="new";
			document.Keywords.submit();
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){

%>
            <script language="JavaScript">
		document.Keywords.code.value="<%=BeanObject.getKcode()%>";
                document.Keywords.name.value="<%=BeanObject.getKname()%>";
                document.Keywords.desc.value="<%=BeanObject.getKdesc()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Keywords.flag.value="Confirmdete";
		   	document.Keywords.submit();

			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
		document.Keywords.code.value="<%=BeanObject.getKcode()%>";
                document.Keywords.name.value="<%=BeanObject.getKname()%>";
                document.Keywords.desc.value="<%=BeanObject.getKdesc()%>";

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
document.Keywords.method="get";
document.Keywords.flag.value="new";
document.Keywords.submit();
}

function SearchRec(){
document.Keywords.method="post";
var no=document.Keywords.code.value;
if(no==""){
				document.Keywords.code.focus();
				alert("Insufficent Data");
				document.Keywords.flag.value="new";
				document.Keywords.submit();
		  }else{
		       document.Keywords.flag.value="search";
			   document.Keywords.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.Keywords.method="post";
			   if(!isWhitespace(document.Keywords.name.value)){
			       	document.Keywords.flag.value="save";
		         	document.Keywords.submit();
					
			}		
	else
	{
	 alert("Insufficient Data");
	 new_no();
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
var mc=document.Keywords.code.value;
if(mc=="")
{
				document.Keywords.code.focus();
				document.Keywords.flag.value="none";
				document.Keywords.code.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
function mailRec(){
             document.Keywords.method="post";
			   
			       	document.Keywords.flag.value="mail";
		         	document.Keywords.submit();
					
			
	
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
var sp=document.Keywords.name.value;
if(sp=="")
{
document.Keywords.name.focus();
				document.Keywords.flag.value="none";
				document.Keywords.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Keywords.name.value.length;i++)
 	                      {
 	                       if(document.Keywords.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Keywords.name.focus();
		                   	document.Keywords.name.value="";
			                return false;
		                      }
		                   else if (document.Keywords.code.value==""){
		                 	document.Keywords.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindKeywords()
{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function keywords_code_val() {
if((isNaN(document.Keywords.code.value))||(document.Keywords.code.value == ' ')) {
document.Keywords.code.select();
document.Keywords.code.value="";
    
  }
}
function ClearRec(){ 
		 document.Keywords.code.value="";
         document.Keywords.name.value="";
         document.Keywords.desc.value="";
		 document.Keywords.flag.value="";
		 new_no();
}
function DelRec(){
document.Keywords.method="post";
if (document.Keywords.code.value==""){
				document.Keywords.code.focus();
				alert("Insufficent Data");
				document.Keywords.flag.value="new";
				document.Keywords.submit();
				}
				else{
				  document.Keywords.flag.value="delete";
				  document.Keywords.submit();
				 }                         
				
}
function load(){
	document.Keywords.name.focus();

		 }
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

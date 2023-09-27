<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%> 
<%@ page language="java"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.Subject.subjectbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Subject
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib Software Systems</title>
<!-- <meta http-equiv="pragma" content="no-cache"/> -->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchSubjectMas.js"></script>

</head>
<body background="/AutoLib/soft.jpg" onload="load()">
<form name=Subject method="get" action=./SubjectServlet>
<br><br><br>
<P ALIGN="left"><BR>
 <h2>Subject&nbsp;Master</h2>
<center>
<center>
<table align="center" class="contentTable" width="47%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Subject&nbsp;Code<td><input type=text name=code size=20 maxlength=20 onKeyUp="return subject_code_val();" readOnly >-->
<!--<input type=button name=find Value="Find" Class="submitButton" onclick=FindSubject('Subject')></td></tr>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td><input type=hidden name=code id="searchSubjectCode" class="searchSubject" size=20 maxlength=20 onKeyUp="return subject_code_val();" readOnly >
</td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Subject&nbsp;Name<td>
<input type=text name=name id="searchSubjectName" class="searchSubject" onkeyup="showSubject(this.value);" size=42 maxlength=50>&nbsp;

    </div>
</div>

<input type=button name=find Value="Find" Class="submitButton" onclick=FindSubject('Subject')>

<FONT color=red size=4>*</FONT></td></tr>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Call&nbsp;No<td><input type=text name=callno id="searchSubjectCallNo" class="searchSubject" size=50 maxlength=40>

    </div>
</div>

 <div class="search-container">
	<div class="ui-widget">
  
<tr><td Class="addedit">Location&nbsp;<td><input type=text name=desc id="searchSubjectDesc" class="searchSubject" size=50 maxlength=50>

</div>
</div>
   
<tr><td colspan=2 align=center>
<CENTER>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
</CENTER>
<input type=hidden name=flag>
<tr><td> &nbsp; </td></tr>
</table>
</CENTER>
</td></table></center></center>
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newSubject")){
 %>
 <script language="JavaScript">

document.Subject.code.value="<%=beanobject.getCode()%>";
document.Subject.name.focus();
</script>
<%
  }

  if(valid.equals("searchSubject")){
 %>
  <script language="JavaScript">
document.Subject.code.value="<%=beanobject.getCode()%>";
document.Subject.name.value="<%=beanobject.getName()%>";
document.Subject.desc.value="<%=beanobject.getDesc()%>";
document.Subject.callno.value="<%=beanobject.getCallno()%>";

</script>
<%
}
	if(valid.equals("FailSubject")){%>
            <script language="JavaScript">
	    alert("Record Not Found");
	      document.Subject.flag.value="new";
	    document.Subject.submit();
	    </script><%
			}

	if(valid.equals("SaveSubject")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");

			 document.Subject.flag.value="new";
			 document.Subject.submit();
		     </script>
			<%
			}


	if(valid.equals("UpdateSubject")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");

			document.Subject.flag.value="new";
			document.Subject.submit();
		   	</script><%
			}

	if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		document.Subject.code.value="<%=beanobject.getCode()%>";
                document.Subject.name.value="<%=beanobject.getName()%>";
                document.Subject.desc.value="<%=beanobject.getDesc()%>";
                document.Subject.callno.value="<%=beanobject.getCallno()%>";                
               
                msg=confirm("Subject has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				document.Subject.flag.value="update";
		         	document.Subject.submit();
		   }
			    </script>

			<%
			}

	
	if(valid.equals("Updatename")){
		%>
		                <script language="JavaScript">
				document.Subject.code.value="<%=beanobject.getCode()%>";
		                document.Subject.name.value="<%=beanobject.getName()%>";
		                document.Subject.desc.value="<%=beanobject.getDesc()%>";
		                document.Subject.callno.value="<%=beanobject.getCallno()%>";
		                		                
		                msg=confirm("Record Already Exists Are You Sure To Update?");
		               
		                 if(msg)
		                   {
						document.Subject.flag.value="update";
				         	document.Subject.submit();
				   }
					    </script>

					<%
					}

	if(valid.equals("deleteCheck")){
	%>
           	<script language="JavaScript">
		document.Subject.code.value="<%=beanobject.getCode()%>";
                document.Subject.name.value="<%=beanobject.getName()%>";
                document.Subject.desc.value="<%=beanobject.getDesc()%>";
                document.Subject.callno.value="<%=beanobject.getCallno()%>";                

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			document.Subject.flag.value="Confirmdelete";
		   	document.Subject.submit();

			}
			</script>
			<%
			}

			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
				document.Subject.code.value="<%=beanobject.getCode()%>";
                document.Subject.name.value="<%=beanobject.getName()%>";
                document.Subject.desc.value="<%=beanobject.getDesc()%>";
                document.Subject.callno.value="<%=beanobject.getCallno()%>";                
     
                alert("Record Not Present!!!");
                document.Subject.flag.value="new";
                document.Subject.submit();
                
			</script>
			<%
			}

		if(valid.equals("ReferredSubject")){
%>            <script>alert("You can't delete since the Subject has been referred in other masters");

			 document.Subject.flag.value="new";
			 document.Subject.submit();
			</script>
			<%
			}

			if(valid.equals("DeleteSubject")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");

			document.Subject.flag.value="new";
			document.Subject.submit();
		   </script>
			<%
			}
			
			if(valid.equals("DefaultSubject")){

				%>
				            <script language="JavaScript">
							alert("Default Subject Cannot be Deleted!");

							document.Subject.flag.value="new";
							document.Subject.submit();
						   </script>
							<%
				}
			
			
			
			if(valid.equals("CodeCompareSubject")){
%>
            <script>
                document.Subject.code.value="<%=beanobject.getCode()%>";
                document.Subject.name.value="<%=beanobject.getName()%>";
                document.Subject.desc.value="<%=beanobject.getDesc()%>";
                document.Subject.callno.value="<%=beanobject.getCallno()%>";
                                
			msg=confirm("Subject name Already Exists ,Do You Want update other than subject name ?");
			
			if(msg)
		                   {
						document.Subject.flag.value="update";
				         	document.Subject.submit();
				   }
			
			</script><%
			}



  }
  }
  %>

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
document.Subject.method="get";
document.Subject.flag.value="new";
document.Subject.submit();
}

function SaveRec(){
if(chk_mc()){
document.Subject.method="post";
		if(!isWhitespace(document.Subject.name.value)){
			document.Subject.flag.value="save";
		        document.Subject.submit();
			 }

	else
	{
	   alert("Insufficent Data");	
	   document.Subject.flag.value="new";
	   document.Subject.submit();
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
var mc=document.Subject.code.value;
if(mc=="")
{
				document.Subject.code.focus();
				document.Subject.flag.value="none";
				document.Subject.code.value="";
				return false;
				}
				else{
		                    return true;
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
var sp=document.Subject.name.value;
				if(sp=="")
				{
				document.Subject.name.focus();
				document.Subject.flag.value="none";
				document.Subject.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Subject.name.value.length;i++)
 	                      {
 	                       if(document.Subject.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.Subject.name.focus();
		                   	document.Subject.name.value="";
			                return false;
		                      }
		                   else if (document.Subject.code.value==""){
		                 	document.Subject.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }


function SearchRec(){
document.Subject.method="get";
var no=document.Subject.code.value;
if(no==""){
				document.Subject.code.focus();
				alert("Insufficent Data");
				document.Subject.flag.value="new";
				document.Subject.submit();

			}
		 else
		 { 
		       document.Subject.flag.value="search";
			   document.Subject.submit();
			   
		 }
		
}
		
				function DelRec(){
				document.Subject.method="get";
				if (document.Subject.code.value==""){
				document.Subject.code.focus();
				alert("Insufficent Data");
				document.Subject.flag.value="new";
				document.Subject.submit();
				}
				else{
				  document.Subject.flag.value="delete";
				  document.Subject.submit();
				 }

}

function check()
{
 if(updatecheck()){
         if(confirm("Record Already Exists Are You Sure To Update?"))
           {
           document.Subject.flag.value="save";
           }
            else
	        {
	         document.Subject.flag.value="cancel";
	         }
	 }
           else
            {
              document.Subject.flag.value="save";
             }
			 return true;
}

function subject_code_val() {
if((isNaN(document.Subject.code.value))||(document.Subject.code.value == ' ')) {
document.Subject.code.select();
document.Subject.code.value="";
return false;
   }
}
function FindSubject(val)
{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
function load(){
	document.Subject.name.focus();

		 }
</script> 


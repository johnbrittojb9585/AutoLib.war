<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Course.CourseBean" type="Lib.Auto.Course.CourseBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Course
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchCourseMas.js"></script>
</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<!-- Style Sheet -->
<form name=Course method="post" action=./CourseServlet>
<br><br><br>
<!--<center>
<img border='0' src='/AutoLib/images/home.gif' onclick='home()'>
<img border='0' src='/AutoLib/images/Logout.gif' onclick='Logout()'>
</center>-->
<h2>Course&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<!--<td Class="addedit">Course&nbsp;code<td colspan="3" >-->
<!--<input type=button name=find Value="Find" Class="submitButton" onclick=FindCourse('Course')>-->

<div class="search-container">
	<div class="ui-widget">

<!-- <td><input type=hidden name=code id="searchCourseCode" class="searchCourse" size=20  maxlength=8 onKeyUp="return Course_code_val();" ></td> -->
<td><input type="hidden" name="code" id="searchCourseCode" class="searchCourse" size="20"  maxlength="8"></td>

</tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">
  
<tr><td Class="addedit">Course&nbsp;name<td colspan="3" >
<input type="text" name="name" id="searchCourseName" class="searchCourse" onkeyup="showCourse(this.value);" size="37" maxlength="50">&nbsp;

    </div>
</div>

<input type=button name=find Value="Find" Class="submitButton" onclick=FindCourse('Course')></td></tr>

<div class="search-container">
	<div class="ui-widget">
 
<tr><td Class="addedit">Major<td colspan="3" >
<input type="text" name="major" id="searchCourseMajor" class="searchCourse" size="46" maxlength="50"></td></tr>

</div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Period</td >
<!-- <td ><input type=text name=period id="searchCoursePeriod" class="searchCourse" size=0 value="0" maxlength=5 onKeyUp="return course_period_val();" ></td> -->
<td ><input type=text name=period id="searchCoursePeriod" class="searchCourse" size=0 maxlength=5 ></td>

</div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Type</td>
  <td><select name="type" id="searchCourseType" class="searchCourse" size="1">
  <option selected value="Day">Day</option>
 <option value="Evening">Evening</option>
  <option value="PartTime">PartTime</option>
  </select>
    </td>
   </div>    
 </div>
    </tr>
    
<div class="search-container">
	<div class="ui-widget">
	    
<tr><td Class="addedit">Short&nbsp;desc<td colspan="3" ><input type=text name=desc id="searchCourseDesc" class="searchCourse" size=46 maxlength=60></td></tr>

    </div>
</div>
 
<tr><td colspan=4 align=center >
<center>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no() >
</center>
<input type=hidden name=flag >
<tr><td> &nbsp; </td></tr>
</table>
</CENTER>
</td></table></center>
</form>
</body>
</html>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newCourse")){
 %>
 <script language="JavaScript">
document.Course.code.value="<%=bean.getCode()%>";
document.Course.name.focus();
</script><%
}

if(valid.equals("searchCourse")){
 %>
  <script language="JavaScript">
document.Course.code.value="<%=bean.getCode()%>";
document.Course.name.value="<%=bean.getName()%>";
document.Course.major.value="<%=bean.getMajor()%>";
document.Course.period.value="<%=bean.getPeriod()%>";
document.Course.type.value="<%=bean.getType()%>";
document.Course.desc.value="<%=bean.getDesc()%>";

</script>
<%
}
if(valid.equals("FailCourse")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Course.flag.value="new";
			document.Course.submit();
		   	</script><%
			}
	if(valid.equals("UpdateCourse")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Course.flag.value="new";
			document.Course.submit();
		   	</script><%
			}
if(valid.equals("CodeCompareCourse")){
//String  Autcode=(String)request.getAttribute("coursecode");
%>
            <script>
            document.Course.code.value="<%=bean.getCode()%>";
			document.Course.name.value="<%=bean.getName()%>";
			document.Course.major.value="<%=bean.getMajor()%>";
			document.Course.period.value="<%=bean.getPeriod()%>";
			document.Course.type.value="<%=bean.getType()%>";
			document.Course.desc.value="<%=bean.getDesc()%>";
			msg=confirm("Course name Already Exists ,Do You Want update other than course name");
			if(msg)
                   {
				    document.Course.flag.value="update";
		         	document.Course.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SaveCourse")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Course.flag.value="new";
			 document.Course.submit();
		     </script>		
			<%
			}

if(valid.equals("DefaultCourse")){
	%>             <script language="JavaScript">
				 alert("Default Course cannot be Deleted!");
				 document.Course.flag.value="new";
				 document.Course.submit();
			     </script>		
				<%
				}

if(valid.equals("ReferredCourse")){
%>            <script>alert("You can't delete since the Course has been referred in other masters");
			 document.Course.flag.value="new";
			 document.Course.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteCourse")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Course.flag.value="new";
			document.Course.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">
	        document.Course.code.value="<%=bean.getCode()%>";
                document.Course.name.value="<%=bean.getName()%>";
                document.Course.major.value="<%=bean.getMajor()%>";
		document.Course.period.value="<%=bean.getPeriod()%>";
		document.Course.type.value="<%=bean.getType()%>";
		document.Course.desc.value="<%=bean.getDesc()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Course.flag.value="Confirmdete";
		   	document.Course.submit();
			
			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
			    
			    alert("Record Not Present!!!");
			document.Course.flag.value="new";
			document.Course.submit();
			</script>		
			<%
			}
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
				document.Course.code.value="<%=bean.getCode()%>";
                document.Course.name.value="<%=bean.getName()%>";
                document.Course.major.value="<%=bean.getMajor()%>";
				document.Course.period.value="<%=bean.getPeriod()%>";
				document.Course.type.value="<%=bean.getType()%>";
				document.Course.desc.value="<%=bean.getDesc()%>";
                 msg=confirm("Course has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				    document.Course.flag.value="update";
		         	document.Course.submit();
				    
		            }
			    </script>	
			 	
			<%
			}			
if(valid.equals("Updatename")){ 
	%> 
	                <script language="JavaScript">
					document.Course.code.value="<%=bean.getCode()%>";
	                document.Course.name.value="<%=bean.getName()%>";
	                document.Course.major.value="<%=bean.getMajor()%>";
					document.Course.period.value="<%=bean.getPeriod()%>";
					document.Course.type.value="<%=bean.getType()%>";
					document.Course.desc.value="<%=bean.getDesc()%>";
	                 msg=confirm("Record Already Exists Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.Course.flag.value="update";
			         	document.Course.submit();
					    
			            }
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
document.Course.method="get";
//document.Course.method="post";
document.Course.flag.value="new";
document.Course.submit();
}

function SearchRec(){
document.Course.method="get";
//document.Course.method="post";
var no=document.Course.code.value;
if(no==""){
				document.Course.code.focus();
				alert("Insufficent Data");
				document.Course.flag.value="new";
				document.Course.submit();
		  }else{
		       document.Course.flag.value="search";
			   document.Course.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.Course.method="get";
             //document.Course.method="post";
			   if(!isWhitespace(document.Course.name.value)){
			   if(validate()){
			       	document.Course.flag.value="save";
		         	document.Course.submit();
				}
			}
	else
	{
	     alert("Insufficent Data");
	     document.Course.flag.value="new";
		 document.Course.submit();	     
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
var mc=document.Course.code.value;
if(mc=="")
{
				document.Course.code.focus();
				document.Course.flag.value="none";
				document.Course.code.value="";
				return false;
				}
				if(document.Course.name.value=="")
                {
                 document.Course.name.focus();
	             document.Course.flag.value="none";
	             document.Course.name.value="";	 
	             return false;
                }
				if(document.Course.major.value=="")
                {
                 document.Course.major.focus();
	             document.Course.flag.value="none";
	             document.Course.major.value="";	 
	             return false;
                }
   return true;

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
var sp=document.Course.name.value;

if(sp=="")
{
document.Course.name.focus();
				document.Course.flag.value="none";
				document.Course.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Course.name.value.length;i++)
 	                      {
 	                       if(document.Course.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Course.name.focus();
		                   	document.Course.name.value="";
			                return false;
		                      }
		                   else if (document.Course.code.value==""){
		                 	document.Course.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindCourse(val)
{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function validate(){
		if (document.Course.period.value==""){
		alert(" Please Enter The period ! ");
			document.Course.period.focus();
			return false;
		     }
		else
		{
		return true;
		}
}

function Course_code_val() {
if((isNaN(document.Course.code.value))||(document.Course.code.value == ' ')) {
document.Course.code.select();
document.Course.code.value="";

  }
}

function course_period_val() {
if((isNaN(document.Course.period.value))||(document.Course.period.value == ' ')) {
document.Course.period.select();
document.Course.period.value="";

  }
}
function ClearRec(){
		 document.Course.code.value="";
         document.Course.name.value="";
         document.Course.desc.value="";
         document.Course.Email.value="";
		 document.Course.flag.value="";
}
function DelRec(){
document.Course.method="get";
if (document.Course.code.value==""){

				document.Course.code.focus();
				alert("Insufficent Data");
				document.Course.flag.value="new";
				document.Course.submit();
	
				}
				else{
				  document.Course.flag.value="delete";
				  document.Course.submit();
				 }                         
				
}
			
	function load(){
	document.Course.name.focus();

		 }	

</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

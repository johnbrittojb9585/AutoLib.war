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
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.Newsclliping.NewscllipingBean"  type="Lib.Auto.Newsclliping.NewscllipingBean">
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
<head>
<meta charset="ISO-8859-1">

<title>AutoLib Software Systems</title>
<!-- <meta http-equiv="pragma" content="no-cache"/> -->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchNewsPaper.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>

</head>
<body background="/AutoLib/soft.jpg" ><!-- onload="load()"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>
<form name="Newsclliping" method="get" action=./NewscllipingServlet>
<br><br><br>

 <P ALIGN="left">
 <h2>Newsclipping&nbsp;Master</h2>
 <center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Clipping&nbsp;No</td><td><input type=text name=code size=20  maxlength=5 onKeyUp="return keywords_code_val();"  >
<!-- <input type=button name=find  Value="Find" Class="submitButton" onclick="FindKeywords();"> --></td></tr>


<tr><td Class="addedit">NewsPaper&nbsp;Name</td>

<div class="search-container">
	<div class="ui-widget">

<!-- <td><input type=text name=name id="searchNewsPaperName" class="searchNewsPaper" onkeyup="showNewsPaperName(this.value);" size=50 maxlength="50"></td> -->
<td><input type=text name=name id="searchNewsPaperName" class="searchNewsPaper" size=50 onkeyup="showNewsPaperName(this.value);" maxlength="50"></td>

    </div>
</div>

</tr>

<tr><td Class="addedit">News&nbsp;Type</td>

<div class="search-container">
	<div class="ui-widget">

<!-- <td><input type=text name=type id="searchNewsPaperType" class="searchNewsPaper" onkeyup="showNewsPaperType(this.value);" size=50 maxlength="50"></td> -->
<td><input type=text name=type id="searchNewsPaperType" class="searchNewsPaper" onkeyup="showNewsPaperType(this.value);" size=50 maxlength="50"></td>

    </div>
</div>

</tr>


<tr><td Class="addedit">Date</td>
<td>
<%-- <INPUT name=rcDate size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
<INPUT name=rcDate size=15 id="datepicker" value="<%=dateString%>">
				

Pages<input type=text name=pageno size=10 maxlength="10">
</td></tr>
<tr><td Class="addedit">News&nbsp;Title</td><td><input type=text name=title size=50 maxlength="150"></td></tr>


<tr><td Class="addedit">News&nbsp;Subject</td>

<div class="search-container">
	<div class="ui-widget">

<!-- <td><input type=text name=subject id="searchNewsPaperSubject" class="searchNewsPaper" onkeyup="showNewsPaperSubject(this.value);" size=50 maxlength="50"></td> -->
<td><input type=text name=subject id="searchNewsPaperSubject" class="searchNewsPaper" onkeyup="showNewsPaperSubject(this.value);" size=50 maxlength="50"></td>

   </div>
</div>

</tr>


<tr><td Class="addedit">Keywords</td><td><input type=text name=keywords size=50 maxlength="50"></td></tr>
<tr><td Class="addedit">Abstract</td><td><input type=text name=abstract size=50 maxlength="50"></td></tr>
<tr><td Class="addedit">Content</td><td><input type=text name=content size=50 maxlength="50"></td></tr>

<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton"  Value=Delete onclick=DelRec()>
<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>
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
//out.print("valid  "+valid);
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newNews")){
 %>
<script language="JavaScript">
document.Newsclliping.code.value="<%=BeanObject.getNcode()%>";
document.Newsclliping.name.focus();
</script><%

}
if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
				document.Newsclliping.code.value="<%=BeanObject.getNcode()%>";
                document.Newsclliping.name.value="<%=BeanObject.getNname()%>";
                document.Newsclliping.type.value="<%=BeanObject.getNtype()%>";
                document.Newsclliping.pageno.value="<%=BeanObject.getPno()%>";
                document.Newsclliping.title.value="<%=BeanObject.getNtitle()%>";
                document.Newsclliping.subject.value="<%=BeanObject.getNsubject()%>";
                document.Newsclliping.keywords.value="<%=BeanObject.getNkey()%>";
                document.Newsclliping.abstract.value="<%=BeanObject.getNabstract()%>";
                document.Newsclliping.content.value="<%=BeanObject.getNcontent()%>";
               
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Newsclliping.flag.value="update";
		         	document.Newsclliping.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("searchKeywords")){
 // out.print("hai");
 %>
  <script language="JavaScript">


                document.Newsclliping.code.value="<%=BeanObject.getNcode()%>";
                document.Newsclliping.name.value="<%=BeanObject.getNname()%>";
                document.Newsclliping.type.value="<%=BeanObject.getNtype()%>";
                document.Newsclliping.pageno.value="<%=BeanObject.getPno()%>";
                document.Newsclliping.title.value="<%=BeanObject.getNtitle()%>";
                document.Newsclliping.subject.value="<%=BeanObject.getNsubject()%>";
                document.Newsclliping.keywords.value="<%=BeanObject.getNkey()%>";
                document.Newsclliping.abstract.value="<%=BeanObject.getNabstract()%>";
                document.Newsclliping.content.value="<%=BeanObject.getNcontent()%>";
                document.Newsclliping.rcDate.value="<%=BeanObject.getNdate()%>";

            

</script>
<%
}
if(valid.equals("FailKeywords")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Newsclliping.flag.value="new";
			document.Newsclliping.submit();
		   	</script><%
			}
			if(valid.equals("UpdateKeywords")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Newsclliping.flag.value="new";
			document.Newsclliping.submit();
		   	</script><%
			}
			if(valid.equals("SaveKeyword")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Newsclliping.flag.value="new";
			 document.Newsclliping.submit();
		     </script>
			<%
			}
			if(valid.equals("AlreadyExists")){

%>             <script language="JavaScript">
			 alert("Record already exists in : "+<%=request.getParameter("no")%>);
			 document.Newsclliping.flag.value="new";
			 document.Newsclliping.submit();
		     </script>
			<%
			}
			if(valid.equals("CodeCompareKeyword")){

%>             <script language="JavaScript">
                document.Newsclliping.code.value="<%=BeanObject.getNcode()%>";
                document.Newsclliping.name.value="<%=BeanObject.getNname()%>";
                
			 msg=confirm("Record already exists in : "+"<%=BeanObject.getNcode()%>"+",Do You Want update?");

			 if(msg)
                   {
				    document.Newsclliping.flag.value="update";
		         	document.Newsclliping.submit();

		            }
		     </script>
			<%
			}
			if(valid.equals("DeleteKeywords")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Newsclliping.flag.value="new";
			document.Newsclliping.submit();
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){

%>
            <script language="JavaScript">
				document.Newsclliping.code.value="<%=BeanObject.getNcode()%>";
                document.Newsclliping.name.value="<%=BeanObject.getNname()%>";
                document.Newsclliping.type.value="<%=BeanObject.getNtype()%>";
                document.Newsclliping.pageno.value="<%=BeanObject.getPno()%>";
                document.Newsclliping.title.value="<%=BeanObject.getNtitle()%>";
                document.Newsclliping.subject.value="<%=BeanObject.getNsubject()%>";
                document.Newsclliping.keywords.value="<%=BeanObject.getNkey()%>";
                document.Newsclliping.abstract.value="<%=BeanObject.getNabstract()%>";
                document.Newsclliping.content.value="<%=BeanObject.getNcontent()%>";
                document.Newsclliping.rcDate.value="<%=BeanObject.getNdate()%>";


			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Newsclliping.flag.value="Confirmdete";
		   	document.Newsclliping.submit();

			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
		document.Keywords.code.value="<%=BeanObject.getNcode()%>";
                document.Newsclliping.name.value="<%=BeanObject.getNname()%>";
                

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
document.Newsclliping.method="get";
document.Newsclliping.flag.value="new";
document.Newsclliping.submit();
}

function SearchRec(){
document.Newsclliping.method="post";
var no=document.Newsclliping.code.value;
if(no==""){
				document.Newsclliping.code.focus();
				alert("Insufficent Data");
				document.Newsclliping.flag.value="new";
				document.Newsclliping.submit();

		  }else{
		       document.Newsclliping.flag.value="search";
			   document.Newsclliping.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){

             document.Newsclliping.method="post";
			   if(!isWhitespace(document.Newsclliping.name.value)){
			       	document.Newsclliping.flag.value="save";
		         	document.Newsclliping.submit();
					
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
var mc=document.Newsclliping.code.value;
if(mc=="")
{
				document.Newsclliping.code.focus();
				document.Newsclliping.flag.value="none";
				document.Newsclliping.code.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
function mailRec(){
             document.Newsclliping.method="post";
			   
			       	document.Newsclliping.flag.value="mail";
		         	document.Newsclliping.submit();
					
			
	
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
var sp=document.Newsclliping.name.value;
if(sp=="")
{
document.Newsclliping.name.focus();
				document.Newsclliping.flag.value="none";
				document.Newsclliping.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Newsclliping.name.value.length;i++)
 	                      {
 	                       if(document.Newsclliping.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Newsclliping.name.focus();
		                   	document.Newsclliping.name.value="";
			                return false;
		                      }
		                   else if (document.Newsclliping.code.value==""){
		                 	document.Newsclliping.code.focus();
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
if((isNaN(document.Newsclliping.code.value))||(document.Newsclliping.code.value == ' ')) {
document.Newsclliping.code.select();
document.Newsclliping.code.value="";
    
  }
}
function ClearRec(){ 
		 document.Newsclliping.code.value="";
         document.Newsclliping.name.value="";
         document.Newsclliping.type.value="";
		 document.Newsclliping.title.value="";
		  document.Newsclliping.subject.value="";
		   document.Newsclliping.abstract.value="";
		    document.Newsclliping.keywords.value="";
		     document.Newsclliping.content.value="";
		      document.Newsclliping.pageno.value="";
		 document.Newsclliping.flag.value="";
}
function DelRec(){
document.Newsclliping.method="post";
if (document.Newsclliping.code.value==""){
				document.Newsclliping.code.focus();
				alert("Insufficent Data");
				document.Newsclliping.flag.value="new";
				document.Newsclliping.submit();
				}
				else{
				  document.Newsclliping.flag.value="delete";
				  document.Newsclliping.submit();
				 }                         
				
}
function load(){
	document.Newsclliping.name.focus();

		 }
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

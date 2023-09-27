<%
//session.setAttribute("test", "jjjjjjjjjjjjjjjjj");
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	

%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js" ></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/tamil.js" ></script>
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">
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
<meta charset="ISO-8859-1">
<!-- <meta http-equiv="pragma" content="no-cache"/> -->
<title>AutoLib Software Systems</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchAuthorMas2.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/searchAuthorMas3.js"></script> --%>

</head>
<body onload="load()">

<form name="Author" method="post" action=./AuthorServlet>
<br><br><br>
 <P ALIGN="left"><BR>
 <h2> Author&nbsp;Master </h2>
<table align="center" class="contentTable" width="47%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Author&nbsp;Code<td><input type=text name=code size=20  maxlength=5 onKeyUp="return author_code_val();"  >-->
<!--<input type=button name=find Class="submitButton" Value="Find" onclick="FindAuthor();"></td></tr>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td><input type=hidden name=code id="searchAuthorCode" class="searchAuthor" size=20  maxlength=5 onKeyUp="return author_code_val();"  readOnly>
</td></tr>

   </div>
</div>
    
<div class="search-container">
	<div class="ui-widget">
    
<tr><td Class="addedit">Author&nbsp;Name<td><font color="#800000" face="utf-8">
<input type="text" name="name" id="searchAuthorName" class="searchAuthor" onkeyup="showAuthor(this.value);" size="42" maxlength="50">&nbsp;

    </div>
</div>  

<input type=button name=find Class="submitButton" Value="Find" onclick="FindAuthor();">

</font><FONT color=red size=4>*</FONT></td></tr>

<div class="search-container">
	<div class="ui-widget">
   
<tr><td Class="addedit">Short&nbsp;Desc<td><input type=text name=desc class="searchAuthor" id="searchAuthorDesc" size=50 maxlength="50"></td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Email&nbsp;Id<td><input type=text name=email class="searchAuthor" id="searchAuthorEmail" size=50 maxlength="50"></td></tr>

   </div>
</div>
   
<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=New Class="submitButton"  Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton"  Value=Search  onclick=SearchRec()>-->
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>

</CENTER>
<input type=hidden name=flag>
<tr><td> &nbsp; </td></tr>
</table>
</td>
</table>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newAuthor")){
 %>
<script language="JavaScript">
document.Author.code.value="<%=beanobject.getCode()%>";
document.Author.name.focus();
</script><%

}
if(valid.equals("searchAuthor")){

 %>
  <script language="JavaScript">


document.Author.code.value="<%=beanobject.getCode()%>";
document.Author.name.value="<%=beanobject.getName()%>";
document.Author.desc.value="<%=beanobject.getDesc()%>";
document.Author.email.value="<%=beanobject.getEmail()%>";
</script>
<%
}
if(valid.equals("FailAuthor")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Author.flag.value="new";
			document.Author.submit();
		   	</script><%
			}
	if(valid.equals("UpdateAuthor")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Author.flag.value="new";
			document.Author.submit();
		   	</script><%
			}		
if(valid.equals("CodeCompareAuthor")){
	
%>
            <script language="JavaScript">
         
				document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
                
			msg=confirm("Author name Already Exists ,Do You Want update other than author name ?");
			
			if(msg){
			 document.Author.flag.value="update";
		   	document.Author.submit();	
			
			}

			</script><%
			}
if(valid.equals("SaveAuthor")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Author.flag.value="new";
			 document.Author.submit();
		     </script>		
			<%
			}
if(valid.equals("DefaultAuthor")){
	%>             <script language="JavaScript">
				 alert("Default Author Cannot be Deleted!");
				 document.Author.flag.value="new";
				 document.Author.submit();
			     </script>		
				<%
				}

if(valid.equals("ReferredAuthor")){
%>            <script>
				
             alert("You can't delete since the Author has been referred in other masters");
			 document.Author.flag.value="new";
			 document.Author.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteAuthor")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Author.flag.value="new";
			document.Author.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
			    document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Author.flag.value="Confirmdete";
		   	document.Author.submit();	
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			    document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
			    alert("Record Not Present!!!");
				</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
  				document.Author.code.value="<%=beanobject.getCode()%>";
                document.Author.name.value="<%=beanobject.getName()%>";
                document.Author.desc.value="<%=beanobject.getDesc()%>";
                document.Author.email.value="<%=beanobject.getEmail()%>";
	
                 msg=confirm("Author has been referred in other masters,You Can't Update !");
                if(msg)
                   {
				    document.Author.flag.value="new";
		         	document.Author.submit();	
				    
		            }
		           
			    </script>	
			 	
			<%
			}			
			
if(valid.equals("Updatename")){ 
	
	
	%> 
				    
	                <script language="JavaScript">

	  				document.Author.code.value="<%=beanobject.getCode()%>";
	                document.Author.name.value="<%=beanobject.getName()%>";
	                document.Author.desc.value="<%=beanobject.getDesc()%>";
	                document.Author.email.value="<%=beanobject.getEmail()%>";
		
	                 msg=confirm("Record already Exist, Are You Sure To Update?");
	                if(msg)
	                   {
					    document.Author.flag.value="update";
			         	document.Author.submit();	
					    
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
if(chk_mc()){
             document.Author.method="post";
			   if(!isWhitespace(document.Author.name.value)){
			   
			        document.Author.name.value=document.Author.name.value+" ";
			    	document.Author.flag.value="save";
		         	document.Author.submit();	
					
			}		
	else
	{	
	  alert("Insufficent Data");
	  document.Author.flag.value="new";
	  document.Author.submit();	
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
var mc=document.Author.code.value;
if(mc=="")
{
				document.Author.code.focus();
				document.Author.flag.value="none";
				document.Author.code.value="";
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
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
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

         new_no();
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
document.Author.name.focus();
}


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

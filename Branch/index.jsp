<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BranchBean" scope="request" class="Lib.Auto.Branch.BranchBean"/>
<%//Security.checkSecurity(1,session,response,request);%>


<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Branch
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>

<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" 
onload="load()">

<!-- Style Sheet -->
<form name=Branch method="post" action=./BranchServlet>

<br><br><br>

<h2>Branch&nbsp;Master</h2>

<center>   
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Branch&nbsp;Code<td><input type=text name=code size=20  maxlength=5>-->
<!-- <input type=button name=find Value="Find" Class="submitButton" onclick=FindBranch("Branch")>-->

<tr><td Class="addedit"><input type=hidden name=code size=20  maxlength=5></td></tr>
 
<tr><td Class="addedit">Branch&nbsp;Name<td><input type=text name=name size=36 maxlength=40>
&nbsp;<input type=button name=find Value="Find" Class="submitButton" onclick=FindBranch("Branch")></td></tr>

<tr><td Class="addedit">Short&nbsp;Desc<td><input type=text name=desc size=45 maxlength=40></td></tr>

<tr><td colspan=2 align=center>
<center>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
</center>
<input type=hidden name=flag>
<tr><td> &nbsp; </td></tr>
</table>
</CENTER></td></table></center>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");


if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newBranch")){
	
 %>
 <script language="JavaScript">

document.Branch.code.value="<%=BranchBean.getCode()%>";
document.Branch.name.focus();
</script><%
}

if(valid.equals("searchBranch")){
 %>
  <script language="JavaScript">
document.Branch.code.value="<%=BranchBean.getCode()%>";
document.Branch.name.value="<%=BranchBean.getName()%>";
document.Branch.desc.value="<%=BranchBean.getDesc()%>";

</script>
<%
}
if(valid.equals("FailBranch")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Branch.flag.value="new";
			document.Branch.submit();
		   	</script><%
			}
	if(valid.equals("UpdateBranch")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Branch.flag.value="new";
			document.Branch.submit();
		   	</script><%
			}		
if(valid.equals("CodeCompareBranch")){
//String  Autcode=(String)request.getAttribute("branchcode");
%>
            <script>
            document.Branch.code.value="<%=BranchBean.getCode()%>";
			document.Branch.name.value="<%=BranchBean.getName()%>";
			document.Branch.desc.value="<%=BranchBean.getDesc()%>";
			msg=confirm("Branch name Already Exists ,Do You Want update other than branch name");

			if(msg)
                   {
				    document.Branch.flag.value="update";
		         	document.Branch.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SaveBranch")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Branch.flag.value="new";
			 document.Branch.submit();
		     </script>		
			<%
			}
if(valid.equals("DefaultBranch")){
	%>             <script language="JavaScript">
				 alert("Default Branch cannot be Deleted !");
				 document.Branch.flag.value="new";
				 document.Branch.submit();
			     </script>		
				<%
				}

if(valid.equals("ReferredBranch")){
%>            <script>alert("You can't delete since the Branch has been referred in other masters");
			 document.Branch.flag.value="new";
			 document.Branch.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteBranch")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Branch.flag.value="new";
			document.Branch.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
			    document.Branch.code.value="<%=BranchBean.getCode()%>";
                document.Branch.name.value="<%=BranchBean.getName()%>";
                document.Branch.desc.value="<%=BranchBean.getDesc()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Branch.flag.value="Confirmdete";
		   	document.Branch.submit();
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			    document.Branch.code.value="<%=BranchBean.getCode()%>";
                document.Branch.name.value="<%=BranchBean.getName()%>";
                document.Branch.desc.value="<%=BranchBean.getDesc()%>";

			    alert("Record Not Present!!!");
			</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
				document.Branch.code.value="<%=BranchBean.getCode()%>";
                document.Branch.name.value="<%=BranchBean.getName()%>";
                document.Branch.desc.value="<%=BranchBean.getDesc()%>";

                 msg=confirm("Branch has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				    document.Branch.flag.value="update";
		         	document.Branch.submit();
				    
		            }
			    </script>	
			 	
			<%
			}			
			
if(valid.equals("Updatename")){ 
	%> 
	                <script language="JavaScript">
					document.Branch.code.value="<%=BranchBean.getCode()%>";
	                document.Branch.name.value="<%=BranchBean.getName()%>";
	                document.Branch.desc.value="<%=BranchBean.getDesc()%>";

	                 msg=confirm("Record Already Exists Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.Branch.flag.value="update";
			         	document.Branch.submit();
					    
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

document.Branch.method="get";
document.Branch.flag.value="new";
document.Branch.submit();
}

function SearchRec(){

document.Branch.method="get";
var no=document.Branch.code.value;
if(no==""){

				document.Branch.code.focus();
				alert("Insufficent Data");
				document.Branch.flag.value="new";
				document.Branch.submit();
		  }else{
		       document.Branch.flag.value="search";
			   document.Branch.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.Branch.method="get";
			   if(!isWhitespace(document.Branch.name.value)){
			        document.Branch.flag.value="save";
                    document.Branch.submit();
					
			}		
	else
	{
	 alert("Insufficent Data");
	 new_no();
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
var mc=document.Branch.code.value;
if(mc=="")
{
				document.Branch.code.focus();
				document.Branch.flag.value="none";
				document.Branch.code.value="";
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
var sp=document.Branch.name.value;
if(sp=="")
{
document.Branch.name.focus();
				document.Branch.flag.value="none";
				document.Branch.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Branch.name.value.length;i++)
 	                      {
 	                       if(document.Branch.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Branch.name.focus();
		                   	document.Branch.name.value="";
			                return false;
		                      }
		                   else if (document.Branch.code.value==""){
		                 	document.Branch.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindBranch(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function Branch_code_val() {
if((isNaN(document.Branch.code.value))||(document.Branch.code.value == ' ')) {
document.Branch.code.select();
document.Branch.code.value="";
    
  }
}
function ClearRec(){ 
		 document.Branch.code.value="";
         document.Branch.name.value="";
         document.Branch.desc.value="";
         document.Branch.Email.value="";
		 document.Branch.flag.value="";
}
function DelRec(){
document.Branch.method="get";
if (document.Branch.code.value==""){
				document.Branch.code.focus();
				alert("Insufficent Data");
				document.Branch.flag.value="new";
				document.Branch.submit();
				}
				else{
				  document.Branch.flag.value="delete";
				  document.Branch.submit();
				 }                         
				
}
			
function load(){
	document.Branch.name.focus();

		 }

</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

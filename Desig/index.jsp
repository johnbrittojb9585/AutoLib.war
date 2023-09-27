<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ pagelanguage="java" session="true" buffer="12kb" import="java.sql.*" %>

<jsp:useBean id="DesignationBean" scope="request" class="Lib.Auto.Designation.DesignationBean" type="Lib.Auto.Designation.DesignationBean"/>

<!--//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Desig
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchDesigMas.js"></script>

</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<!-- Style Sheet -->
<form name=Desig method="post" action=./DesignationServlet>

<br><br><br>

<h2>Designation&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="50%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Designation&nbsp;Code<td><input type=text name=code size=20  maxlength=5  readOnly >-->
<!--<input type=button name=find Value="Find" Class="submitButton" onclick=FindDesignation('Desg')></td></tr>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td><input type=hidden name=code size=20  maxlength=5 id="searchDesigCode" class="searchDesig" onKeyUp="return desig_code_val();">
</td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Designation&nbsp;Name<td>
<input type="text" name="name" id="searchDesigName" class="searchDesig" onkeyup="showDesig(this.value);" size=42 maxlength=50>&nbsp;

     </div>
 </div>

<input type=button name=find Value="Find" Class="submitButton" onclick=FindDesignation('Desg')><FONT color=red size=4>*</FONT></td></tr>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Short&nbsp;Desc<td><input type=text name=desc id="searchDesigDesc" class="searchDesig" size=50 maxlength=54></td></tr>

   </div>
</div>

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
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
	if(valid.equals("getJson")){
	    //request.getAttribute("searchJson");
	 %>
	  <script language="JavaScript">
	  		
	document.Desig.code.value="<%=DesignationBean.getCode()%>";
	document.Desig.name.value="<%=DesignationBean.getName()%>";
	document.Desig.desc.value="<%=DesignationBean.getDesc()%>";

	</script>
	<%
	}
if(valid.equals("newDesig")){
 %>
 <script language="JavaScript">
document.Desig.code.value="<%=DesignationBean.getCode()%>";
document.Desig.name.focus();
</script><%
}

if(valid.equals("searchDesig")){
 %>

 <script language="JavaScript">
document.Desig.code.value="<%=DesignationBean.getCode()%>";
document.Desig.name.value="<%=DesignationBean.getName()%>";
document.Desig.desc.value="<%=DesignationBean.getDesc()%>";
</script>
<%
}
if(valid.equals("FailDesig")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Desig.flag.value="new";
			document.Desig.submit();
		   	</script><%
			}
	if(valid.equals("UpdateDesig")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Desig.flag.value="new";
			document.Desig.submit();
		   	</script><%
			}
	if(valid.equals("DefaultDesig")){%>
    <script language="JavaScript">
	alert("Default Designation cannot be Deleted!");
	document.Desig.flag.value="new";
	document.Desig.submit();
   	</script><%
	}
	
if(valid.equals("CodeCompareDesig")){
%>
            <script>
            document.Desig.code.value="<%=DesignationBean.getCode()%>";
			document.Desig.name.value="<%=DesignationBean.getName()%>";
			document.Desig.desc.value="<%=DesignationBean.getDesc()%>";
			msg=confirm("Designation name Already Exists ,Do You Want update other than designation name");
			if(msg)
                   {
				    document.Desig.flag.value="update";
		         	document.Desig.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SaveDesig")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Desig.flag.value="new";
			 document.Desig.submit();
		     </script>		
			<%
			}
if(valid.equals("ReferredDesig")){
%>            <script>alert("You can't delete since the Designation has been referred in other masters");
			 document.Desig.flag.value="new";
			 document.Desig.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteDesig")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Desig.flag.value="new";
			document.Desig.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
		document.Desig.code.value="<%=DesignationBean.getCode()%>";
                document.Desig.name.value="<%=DesignationBean.getName()%>";
                document.Desig.desc.value="<%=DesignationBean.getDesc()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Desig.flag.value="Confirmdete";
		   	document.Desig.submit();
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			    document.Desig.code.value="<%=DesignationBean.getCode()%>";
                document.Desig.name.value="<%=DesignationBean.getName()%>";
                document.Desig.desc.value="<%=DesignationBean.getDesc()%>";

			    alert("Record Not Present!!!");
			</script>		
			<%
			}
	
			
if(valid.equals("UpdateCheck")){
%> 
                <script language="JavaScript">
				document.Desig.code.value="<%=DesignationBean.getCode()%>";
                document.Desig.name.value="<%=DesignationBean.getName()%>";
                document.Desig.desc.value="<%=DesignationBean.getDesc()%>";

                 msg=confirm("Designation has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				    document.Desig.flag.value="update";
		         	document.Desig.submit();
				    
		            }
			    </script>	
			 	
			<%
			}			
if(valid.equals("Updatename")){
	%> 
	                <script language="JavaScript">
					document.Desig.code.value="<%=DesignationBean.getCode()%>";
	                document.Desig.name.value="<%=DesignationBean.getName()%>";
	                document.Desig.desc.value="<%=DesignationBean.getDesc()%>";

	                 msg=confirm("Record already Exist, Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.Desig.flag.value="update";
			         	document.Desig.submit();
					  
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
document.Desig.method="get";
document.Desig.flag.value="new";
document.Desig.submit();
}

function SearchRec(){
document.Desig.method="get";
var no=document.Desig.code.value;
if(no==""){
				document.Desig.code.focus();
				alert("Insufficent Data");
				document.Desig.flag.value="new";
				document.Desig.submit();
		  }else{
		       document.Desig.flag.value="search";
			   document.Desig.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.Desig.method="get";
			   if(!isWhitespace(document.Desig.name.value)){
			       	document.Desig.flag.value="save";
		         	document.Desig.submit();
					
			}		
	else
	{
	  alert("Insufficent Data");
	  document.Desig.flag.value="new";
   	  document.Desig.submit();
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
var mc=document.Desig.code.value;
if(mc=="")
{
				document.Desig.code.focus();
				document.Desig.flag.value="none";
				document.Desig.code.value="";
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
var sp=document.Desig.name.value;
if(sp=="")
{
document.Desig.name.focus();
				document.Desig.flag.value="none";
				document.Desig.name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Desig.name.value.length;i++)
 	                      {
 	                       if(document.Desig.name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Desig.name.focus();
		                   	document.Desig.name.value="";
			                return false;
		                      }
		                   else if (document.Desig.code.value==""){
		                 	document.Desig.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindDesignation(val)
{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function Designation_code_val() {
if((isNaN(document.Desig.code.value))||(document.Desig.code.value == ' ')) {
document.Desig.code.select();
document.Desig.code.value="";
    
  }
}
function ClearRec(){ 
		 document.Desig.code.value="";
         document.Desig.name.value="";
         document.Desig.desc.value="";
         document.Desig.flag.value="";
         
         new_no();
}

function desig_code_val() {
	if((isNaN(document.Desig.code.value))||(document.Desig.code.value == ' ')) {
	document.Desig.code.select();
	document.Desig.code.value="";
	    
	  }
	}


function DelRec(){
document.Desig.method="get";
if (document.Desig.code.value==""){
				document.Desig.code.focus();
				alert("Insufficent Data");
				document.Desig.flag.value="new";
				document.Desig.submit();
				}
				else{
				  document.Desig.flag.value="delete";
				  document.Desig.submit();
				 }                         
				
}
function load(){
	document.Desig.name.focus();

		 }			


</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


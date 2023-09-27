<!DOCTYPE html>
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

<jsp:useBean id="DepartmentBean" scope="request" class="Lib.Auto.Department.DepartmentBean"/>

<%
//
//   Filename: Index.jsp
//   Form name:Department
//%>

<html>
<head>
<meta charset="ISO-8859-1">
 
  <title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-base.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-darkness.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchDeptMas.js"></script>


</head>

<body background="/AutoLib/soft.jpg" onload="load()">

<form name=Department method="post" action=./DepartmentServlet>
<br><br><br>
<!-- <div class="header"> -->
   <h2>Department&nbsp;Master</h2>
<!-- </div> -->
<center>
<table align="center" class="contentTable" width="50%">

<tr>
<td>
<table align="center" width="90%">

<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Department&nbsp;Code<td><input type=text name=code size=20 maxlength=20 onKeyUp="return Department_code_val();" readOnly >-->
<!-- <tr><td></td><td><input type=hidden name=code size=20 maxlength=20 onKeyUp="return Department_code_val();" readOnly > -->

<div class="search-container">
	<div class="ui-widget">

<tr><td></td><td><input type=hidden name=code id="searchDeptCode" class="search" size=20 maxlength=20></td></tr> 
  	
	<tr><td Class="addedit">Department&nbsp;Name 
  <td><input type=text id="searchDept" name="name" class="search" onkeyup="showDept(this.value);" size=42 maxlength="50" >
       </div>
 </div>
	
<input type=button Class="submitButton" name=find Value="Find" onclick=FindDept('Department')>
<FONT color=red size=4>*</FONT></td></tr>

<div class="search-container">
	<div class="ui-widget">
   
<tr><td Class="addedit">Short&nbsp;Desc<td><input type=text name=desc id="searchDeptDesc" class="search" size=50 maxlength="50" ></td></tr>

    </div>
</div>
 
 
 
<tr><td colspan=2 align=center>
<CENTER>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save  Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
</CENTER>	
  
<input type=hidden name=flag>
<tr><td>&nbsp;</td></tr>
</table>
</td>
</tr>
</table>
</center>
</form>
</body>
</html>

<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
	
		if(valid.equals("newDept")){
		 %>
		 <script language="JavaScript">

		document.Department.code.value="<%=DepartmentBean.getCode()%>";
		document.Department.name.focus();
		</script><%
		}
		if(valid.equals("searchDept")){
		 %>
		  <script language="JavaScript">

		document.Department.code.value="<%=DepartmentBean.getCode()%>";
		document.Department.name.value="<%=DepartmentBean.getName()%>";
		document.Department.desc.value="<%=DepartmentBean.getDesc()%>";

		</script>
		<%
		}
		if(valid.equals("FailDept")){
		%>
		            <script language="JavaScript">
					alert("Record Not Found");
					document.Department.flag.value="new";
					document.Department.submit();
				   	</script><%
					}
			if(valid.equals("UpdateAuthor")){%>
		            <script language="JavaScript">
					alert("Record Updated Successfully!");
					document.Department.flag.value="new";
					document.Department.submit();
				   	</script><%
					}		
		if(valid.equals("CodeCompareDept")){
		String  Autcode=(String)request.getAttribute("deptname");
		%>
		            <script>
		            document.Department.code.value="<%=DepartmentBean.getCode()%>";
					document.Department.name.value="<%=DepartmentBean.getName()%>";
					document.Department.desc.value="<%=DepartmentBean.getDesc()%>";
		            
					msg=confirm("Department name Already ,Do You Want update other than department name ?");
					if(msg)
		                   {
						    document.Department.flag.value="update";
				         	document.Department.submit();
						    
				            }
					</script><%
					}
		if(valid.equals("SaveDept")){
		%>             <script language="JavaScript">
					 alert("Record Inserted Successfully!");
					 document.Department.flag.value="new";
					 document.Department.submit();
				     </script>		
					<%
					}
		if(valid.equals("ReferredDept")){
		%>            <script>alert("You can't delete since the Department has been referred in other masters");
					 document.Department.flag.value="new";
					 document.Department.submit();
					</script>	
					<%
					}	
					
					if(valid.equals("DeleteDept")){
					
		%>       
		            <script language="JavaScript">
					alert("Record Deleted Successfully!");
					document.Department.flag.value="new";
					document.Department.submit();
				   </script>		
					<%
					}		
					
					
					if(valid.equals("deleteCheck")){
					
		%>       
		            <script language="JavaScript">
					    document.Department.code.value="<%=DepartmentBean.getCode()%>";
		                document.Department.name.value="<%=DepartmentBean.getName()%>";
		                document.Department.desc.value="<%=DepartmentBean.getDesc()%>";

					msg=confirm("Are You Sure To Delete?");
					if(msg){
					 document.Department.flag.value="Confirmdete";
				   	document.Department.submit();
					
					}
					</script>		
					<%
					}
					
					if(valid.equals("DefaultDepartment")){
						%>             <script language="JavaScript">
									 alert("Default Department Cannot be Deleted!");
									 document.Department.flag.value="new";
					                 document.Department.submit();
								     </script>		
									<%
									}
					
					if(valid.equals("RecordNot")){
					%>       
		            <script language="JavaScript">	  

					    alert("Record Not Present!!!");
					    document.Department.flag.value="new";
					    document.Department.submit();
					</script>		
					<%
					}				
				
					
		if(valid.equals("UpdateCheck")){ 
		%> 
		                <script language="JavaScript">
						document.Department.code.value="<%=DepartmentBean.getCode()%>";
		                document.Department.name.value="<%=DepartmentBean.getName()%>";
		                document.Department.desc.value="<%=DepartmentBean.getDesc()%>";

		                 msg=confirm("Department has been referred in other masters, Are You Sure To Update?");
		                 if(msg)
		                   {
						    document.Department.flag.value="update";
				         	document.Department.submit();
						    
				            }
					    </script>	
					 	
					<%
					}			
		if(valid.equals("Updatename")){ 
			%> 
			                <script language="JavaScript">
							document.Department.code.value="<%=DepartmentBean.getCode()%>";
			                document.Department.name.value="<%=DepartmentBean.getName()%>";
			                document.Department.desc.value="<%=DepartmentBean.getDesc()%>";

			                 msg=confirm("Record Already Exists Are You Sure To Update?");
			                 if(msg)
			                   {
							    document.Department.flag.value="update";
					         	document.Department.submit();
							    
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
		
		 function showDept(data)
		 {
		 	var data;
			
		 	data=document.Department.name.value;
				
		 } 


		function Logout()
		{
		location.href="/AutoLib/index.html";
		}


		function new_no(){

		document.Department.method="post";
		document.Department.flag.value="new";
		//document.Department.action="index.jsp";
		document.Department.submit();
		}

		function SearchRec(){

		document.Department.method="post";
		var no=document.Department.code.value;
		if(no==""){
						document.Department.code.focus();
						alert("Insufficent Data");
						document.Department.flag.value="new";
						document.Department.submit();

				  }else{
				       document.Department.flag.value="search";
					   document.Department.submit();
					  
				}
			
		}


		function SaveRec(){
		if(chk_mc()){
		             document.Department.method="post";
					  if(!isWhitespace(document.Department.name.value)){
					       	document.Department.flag.value="save";
				         	document.Department.submit();
							
					}		
			else
			{
			   alert("Insufficent Data");	
			   document.Department.flag.value="new";
			   document.Department.submit();	   
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
		var mc=document.Department.code.value;
		if(mc=="")
		{
						document.Department.code.focus();
						document.Department.flag.value="none";
						document.Department.code.value="";
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
		var sp=document.Department.name.value;

		if(sp=="")
		{
		document.Department.name.focus();
						document.Department.flag.value="none";
						document.Department.name.value="";
						return false;
						}
						else
						{
			                 for(i=0;i<sp.length;i++)
		 	                      {
		 	                       if(sp.charAt(i)=="")
		 	                          {flag_s=0; }
		 	                                else{flag_s=1;}
			                       }
				                  if (flag_s==0)
				                    {
				                   	document.Department.name.focus();
				                   	document.Department.name.value="";
					                return false;
				                      }
				                   else if (document.Department.code.value==""){
				                 	document.Department.code.focus();
					                return false;
				                      }
		        else{
				return true;
				}
		     }
		 }

		function FindDept(val)

		{
		winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
		}

		function Department_code_val() {
		if((isNaN(document.Department.code.value))||(document.Department.code.value == ' ')) {
		document.Department.code.select();
		document.Department.code.value="";
		    
		  }
		}
		function ClearRec(){ 
				 document.Department.code.value="";
		         document.Department.name.value="";
		         document.Department.desc.value="";
		         document.Department.Email.value="";
				 document.Department.flag.value="";
		}
		function DelRec(){
		document.Department.method="post";
		if (document.Department.code.value==""){
						document.Department.code.focus();
						alert("Insufficent Data");
						document.Department.flag.value="new";
						document.Department.submit();
						}
						else{
						  document.Department.flag.value="delete";
						  document.Department.submit();
						 }                         
						
		}
		function load(){
			document.Department.name.focus();

				 }			


		</script> 
		<!--
		//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

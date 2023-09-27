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
<jsp:useBean id="CityBean" scope="request" class="Lib.Auto.City.CityBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:City
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchCityMas.js"></script>


</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<!-- Style Sheet -->
<form name=City method="post" action=./CityServlet>
<br><br><br>

<h2>City&nbsp;Master</h2>

<center>
<table align="center" class="contentTable" width="45%">
<tr>
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Code<td><input type=text name=code size=20 maxlength=5 onKeyUp="return City_code_val();" readOnly >-->
<!--<input type=button Class="submitButton" name=find Value="Find" onclick=FindCity('City')></td></tr>-->
  
<div class="search-container">
	<div class="ui-widget">
  
<tr><td><input type=hidden name=code id="searchCityCode" class="searchCity" size=20 maxlength=5 onKeyUp="return City_code_val();" readOnly ></td></tr>

<tr><td Class="addedit">City&nbsp;Name<td><input type=text name=name id="searchCity" class="searchCity" onkeyup="showCity(this.value);" size=42 maxlength="30" >&nbsp;
<input type=button Class="submitButton" name=find Value="Find" onclick=FindCity('City')>
<FONT color=red size=4>*</FONT></td></tr>
<tr><td Class="addedit">State<td><input type=text name=state id="searchCityState" class="searchCity" size=50 maxlength="30" >
<tr><td Class="addedit">Country<td><input type=text name=country id="searchCityCountry" class="searchCity" size=50 maxlength="30" >
<tr><td Class="addedit">Pincode<td><input type=text name=pcode id="searchCityPincode" class="searchCity" size=50 maxlength="6" value="0" onKeyUp="return city_pin_val();" >
<tr><td Class="addedit">Remarks<td><input type=text name=desc id="searchCityRemarks" class="searchCity" size=50 maxlength="30" ></td></tr>

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
<tr><td> &nbsp; </td></tr>
</table>
</td>
</tr>
</table>
</center>
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newCity")){
 %>
 <script language="JavaScript">

document.City.code.value="<%=CityBean.getCode()%>";
document.City.name.focus();
</script><%
}

if(valid.equals("searchCity")){
 %>
  <script language="JavaScript">

document.City.code.value="<%=CityBean.getCode()%>";
document.City.name.value="<%=CityBean.getName()%>";
document.City.state.value="<%=CityBean.getState()%>";
document.City.country.value="<%=CityBean.getCountry()%>";
document.City.pcode.value="<%=CityBean.getPincode()%>";
document.City.desc.value="<%=CityBean.getDesc()%>";

</script>
<%
}
if(valid.equals("FailCity")){
%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.City.flag.value="new";
			document.City.submit();
		   	</script><%
			}
	if(valid.equals("UpdateAuthor")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.City.flag.value="new";
			document.City.submit();
		   	</script><%
			}		
if(valid.equals("CodeCompareCity")){
String  Autcode=(String)request.getAttribute("Cityname");
%>
            <script>
            document.City.code.value="<%=CityBean.getCode()%>";
			document.City.name.value="<%=CityBean.getName()%>";
			document.City.state.value="<%=CityBean.getState()%>";
            document.City.country.value="<%=CityBean.getCountry()%>";
            document.City.pcode.value="<%=CityBean.getPincode()%>";
			document.City.desc.value="<%=CityBean.getDesc()%>";
            
			msg=confirm("City name Already Exists ,Do You Want update other than city name ?");
			if(msg)
                   {
				    document.City.flag.value="update";
		         	document.City.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SaveCity")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.City.flag.value="new";
			 document.City.submit();
		     </script>		
			<%
			}
if(valid.equals("ReferredCity")){
%>            <script>alert("You can't delete since the City has been referred in other masters");
			 document.City.flag.value="new";
			 document.City.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteCity")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.City.flag.value="new";
			document.City.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
			    document.City.code.value="<%=CityBean.getCode()%>";
                document.City.name.value="<%=CityBean.getName()%>";
                document.City.state.value="<%=CityBean.getState()%>";
                document.City.country.value="<%=CityBean.getCountry()%>";
                document.City.pcode.value="<%=CityBean.getPincode()%>";
                document.City.desc.value="<%=CityBean.getDesc()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.City.flag.value="Confirmdete";
		   	document.City.submit();
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">	  

			    alert("Record Not Present!!!");
			    document.City.flag.value="new";
			    document.City.submit();
			</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
				document.City.code.value="<%=CityBean.getCode()%>";
                document.City.name.value="<%=CityBean.getName()%>";
                document.City.state.value="<%=CityBean.getState()%>";
                document.City.country.value="<%=CityBean.getCountry()%>";
                document.City.pcode.value="<%=CityBean.getPincode()%>";
                document.City.desc.value="<%=CityBean.getDesc()%>";

                 msg=confirm("City has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				    document.City.flag.value="update";
		         	document.City.submit();
				    
		            }
			    </script>	
			 	
			<%
			}			
if(valid.equals("Updatename")){ 
	%> 
	                <script language="JavaScript">
					document.City.code.value="<%=CityBean.getCode()%>";
	                document.City.name.value="<%=CityBean.getName()%>";
	                document.City.state.value="<%=CityBean.getState()%>";
                    document.City.country.value="<%=CityBean.getCountry()%>";
                    document.City.pcode.value="<%=CityBean.getPincode()%>";
	                document.City.desc.value="<%=CityBean.getDesc()%>";

	                 msg=confirm("Record Already Exists Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.City.flag.value="update";
			         	document.City.submit();
					    
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

document.City.method="post";
document.City.flag.value="new";
document.City.submit();
}

function SearchRec(){

document.City.method="post";
var no=document.City.code.value;
if(no==""){
				document.City.code.focus();
				alert("Insufficent Data");
				document.City.flag.value="new";
				document.City.submit();
		  }else{
		       document.City.flag.value="search";
			   document.City.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.City.method="post";
			  if(!isWhitespace(document.City.name.value)&&!isWhitespace(document.City.state.value)){
			       	document.City.flag.value="save";
		         	document.City.submit();
					
			}		
	else
	{
	alert("Insufficent Data. Must Enter City Name and State");
	document.City.name.focus();		  
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
var mc=document.City.code.value;
if(mc=="")
{
				document.City.code.focus();
				document.City.flag.value="none";
				document.City.code.value="";
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
var sp=document.City.name.value;

if(sp=="")
{
document.City.name.focus();
				document.City.flag.value="none";
				document.City.name.value="";
				return false;CityBean
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
		                   	document.City.name.focus();
		                   	document.City.name.value="";
			                return false;
		                      }
		                   else if (document.City.code.value==""){
		                 	document.City.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindCity(val)

{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function City_code_val() {
if((isNaN(document.City.code.value))||(document.City.code.value == ' ')) {
document.City.code.select();
document.City.code.value="";
    
  }
}
function ClearRec(){ 
		 document.City.code.value="";
         document.City.name.value="";
         document.City.desc.value="";
         document.City.state.value="";
         document.City.country.value="";
         document.City.pcode.value="";
		 document.City.flag.value="";
}
function DelRec(){
document.City.method="post";
if (document.City.code.value==""){
				document.City.code.focus();
				alert("Insufficent Data");
				document.City.flag.value="new";
				document.City.submit();
				}
				else{
				  document.City.flag.value="delete";
				  document.City.submit();
				 }                         
				
}

function city_pin_val() {
if((isNaN(document.City.pcode.value))||(document.City.pcode.value == ' ')) {
document.City.pcode.select();
document.City.pcode.value="";

  }
}

function load(){
	document.City.name.focus();

		 }			


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

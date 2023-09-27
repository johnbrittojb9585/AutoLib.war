<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="CurrencyBean" scope="request" class="Lib.Auto.Currency.CurrencyBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Currency
//%>
<!--
///////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchCurrencyMas.js"></script>
</head>
<body background="/AutoLib/soft.jpg" onload="load()">


<!-- Style Sheet -->

<form name="Currency" method="post" action=./CurrencyServlet>
<br><br><br>

<h2>Currency Master</h2>
<center>
<table align="center" class="contentTable" width="47%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Code<td><input type=text name=Code size=20  maxlength=5 onKeyUp="return Currency_code_val();" readOnly >-->
<!--<input type=button name=find Value="Find" Class="submitButton" onclick=FindCurrency('Currency')></td>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td><input type=hidden name=Code id="searchCurrencyCode" class="searchCurrency" size=20  maxlength=5 onKeyUp="return Currency_code_val();" readOnly ></td></tr>

   </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Currency<td><input type=text name=Currency id="searchCurrency" class="searchCurrency" onkeyup="showCurrency(this.value);" size=42 maxlength=20>&nbsp;

    </div>
</div>


<input type=button name=find Value="Find" Class="submitButton" onclick=FindCurrency('Currency')><FONT color=red size=4>*</FONT></td></tr>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Value<td><input type=text name=Value id="searchCurrencyVal" class="searchCurrency" size=50 maxlength=15 value="0.0" onKeyUp="return Currency_Amount_val();"></tr>

    </div>
</div>


<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Remarks<td><input type=text name=Remarks id="searchCurrencyRemarks" class="searchCurrency" size=50 maxlength=30></tr>

   </div>
</div>

<tr><td colspan=2 align=center>
<center>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save  Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
</center>
</tr>
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
if(valid.equals("newCurrency")){
 %>
 <script language="JavaScript">

document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
document.Currency.Currency.focus();
</script><%
}

if(valid.equals("searchCurrency")){
 %>
  <script language="JavaScript">

document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";

</script>
<%
}
if(valid.equals("FailCurrency")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Currency.flag.value="new";
			document.Currency.submit();
		   	</script><%
			}
	if(valid.equals("UpdateCurrency")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Currency.flag.value="new";
			document.Currency.submit();
		   	</script><%
			}		
if(valid.equals("CodeComparecurrency")){
//String  Autcode=(String)request.getAttribute("currency");
%>
            <script>
            document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
			document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
			document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
			document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";
			msg=confirm("Currency Name Already Exists ,Do You Want update other than currency name");

			if(msg)
                   {
				    document.Currency.flag.value="update";
		         	document.Currency.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SaveCurrency")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Currency.flag.value="new";
			 document.Currency.submit();
		     </script>		
			<%
			}
if(valid.equals("ReferredCurrency")){
%>            <script>alert("You can't delete since the Currency has been referred in other masters");
			 document.Currency.flag.value="new";
			 document.Currency.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteCurrency")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Currency.flag.value="new";
			document.Currency.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
			
%>       
            <script language="JavaScript">
			    document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
			    document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
                            document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
                            document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";


			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Currency.flag.value="Confirmdete";
		   	document.Currency.submit();
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			    document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
			    document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
                            document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
                            document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";


			    alert("Record Not Present!!!");
			</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";

                msg=confirm("Currency has been referred in other masters, Are You Sure To Update?");
                 
                 if(msg)
                   {
				    document.Currency.flag.value="update";
		         	document.Currency.submit();
				    
		            }
			    </script>	
			 	
			<%
			}			
if(valid.equals("Updatename")){ 
	%> 
	                <script language="JavaScript">
	document.Currency.Code.value="<%=CurrencyBean.getCode()%>";
	document.Currency.Currency.value="<%=CurrencyBean.getCurrency()%>";
	document.Currency.Value.value="<%=CurrencyBean.getIndian_value()%>";
	document.Currency.Remarks.value="<%=CurrencyBean.getRemarks()%>";

	
	                 msg=confirm("Record Already Exists Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.Currency.flag.value="update";
			         	document.Currency.submit();
					    
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

document.Currency.method="get";
document.Currency.flag.value="new";
document.Currency.submit();
}

function SearchRec(){
document.Currency.method="post";
var no=document.Currency.Code.value;
if(no==""){
				document.Currency.Code.focus();
				alert("Insufficent Data");
				document.Currency.flag.value="new";
				document.Currency.submit();
		  }else{
		  
		  
		       document.Currency.flag.value="search";
			   document.Currency.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.Currency.method="get";
			   if(!isWhitespace(document.Currency.Currency.value)){
			       	document.Currency.flag.value="save";
		         	document.Currency.submit();
					
			}		
	else
	{
	  alert("Insufficent Data");
	  document.Currency.flag.value="new";
	  document.Currency.submit();
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
var mc=document.Currency.Code.value;
if(mc=="")
{
				document.Currency.Code.focus();
				document.Currency.flag.value="none";
				document.Currency.Code.value="";
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
var sp=document.Currency.Currency.value;
if(sp=="")
{
document.Currency.Currency.focus();
				document.Currency.flag.value="none";
				document.Currency.Currency.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Currency.Currency.value.length;i++)
 	                      {
 	                       if(document.Currency.Currency.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Currency.Currency.focus();
		                   	document.Currency.Currency.value="";
			                return false;
		                      }
		                   else if (document.Currency.Code.value==""){
		                 	document.Currency.Code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindCurrency(val)
{
winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function Currency_code_val() {
if((isNaN(document.Currency.Code.value))||(document.Currency.Code.value == ' ')) {
document.Currency.Code.select();
document.Currency.Code.value="";
    
  }
}

function Currency_Amount_val() {
if((isNaN(document.Currency.Value.value))||(document.Currency.Value.value == ' ')) {
document.Currency.Value.select();
document.Currency.Value.value="";
    
  }
}

function ClearRec(){ 
		 document.Currency.Code.value="";
         document.Currency.Currency.value="";
         document.Currency.Value.value="";
         document.Currency.Remarks.value="";
		 document.Currency.flag.value="";
}
function DelRec(){
document.Currency.method="get";
if (document.Currency.Code.value==""){
				document.Currency.Code.focus();
				alert("Insufficent Data");
				document.Currency.flag.value="new";
				document.Currency.submit();
				}
				else{
				  document.Currency.flag.value="delete";
				  document.Currency.submit();
				 }                         
				
}
			
function load(){
	document.Currency.Currency.focus();

		 }

</script>

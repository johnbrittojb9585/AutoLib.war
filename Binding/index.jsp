<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ pagelanguage="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.util.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.Binding.BindingBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:binding
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <meta http-equiv="pragma" content="no-cache"/> -->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchBindingMas.js"></script>
</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<!-- Style Sheet -->
	<form name=binding method="post" action=./BindingServlet>
<br><br><br>

<h2>Binding&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Code</td><td ><input type="textbox" name="sp_no"  size=10  maxlength=5 onKeyUp="return binding_code_val();" readOnly >-->
<!--<INPUT onclick=Findbinding() Class="submitButton" type="button" value=Find></td><td colspan="2">-->
<!--</td></tr>-->

<div class="search-container">
	<div class="ui-widget">

<tr><td ><input type="hidden" name="sp_no" id="searchBindingCode" class="searchBinding" size=10  maxlength=5 onKeyUp="return binding_code_val();" readOnly >
</td></tr>

<tr><td Class="addedit">Name</td><td colspan="3" >
<input type="textbox" name="sp_name" id="searchBinding" class="searchBinding" onkeyup="showBinding(this.value);" size=41 maxlength="50" >&nbsp;
<INPUT onclick=Findbinding() Class="submitButton" type="button" value=Find>
<FONT color=red size=4>*</FONT></td></tr>
<tr><td Class="addedit">Short&nbsp;Desc</td><td colspan="3" >
<input type="textbox" name="sp_shortdesc" id="searchBindingDesc" class="searchBinding" size=51 maxlength="50" ></td></tr>
<tr ><td Class="addedit">Address1</td><td colspan="3" >
<input type="textbox" name="sp_address1" id="searchBindingAddress1" class="searchBinding" size=51 maxlength="50" > </td></tr>
<tr><td Class="addedit">Address2</td><td colspan="3" >
<input type="textbox" name="sp_address2" id="searchBindingAddress2" class="searchBinding" size=51 maxlength="25" ></td></tr>

<tr><td Class="addedit">City</td><td ><input type="textbox" name="sp_city" id="searchBindingCity" class="searchBinding" size=20 maxlength="25" ></td>

<td Class="addedit">State</td><td ><input type="textbox" name="sp_state" id="searchBindingState" class="searchBinding" size=16 maxlength="25" >  </td></tr>

<tr><td Class="addedit">Country</td><td ><input type="textbox" name="sp_country" id="searchBindingCountry" class="searchBinding" size=20 maxlength="25" ></td><td Class="addedit">Pincode</td>

<td ><input type="text" name="sp_pincode" id="searchBindingPincode" class="searchBinding" size=16 maxlength="6" onKeyUp="return pub_pin_val();">  </td></tr>

<tr><td Class="addedit">Phone</td><td ><input type="textbox" name="sp_phone" id="searchBindingPhone" class="searchBinding" size=10 maxlength="10" onKeyUp="return pub_phone_val();">

<td Class="addedit">Fax</td><td ><input type="textbox" name="sp_fax" id="searchBindingFax" class="searchBinding" size=16 maxlength="18" onKeyUp="return pub_Fax_val();"></td> </tr>

<tr><td Class="addedit">Email</td><td colspan="3" ><INPUT  type=textbox size=51 name=sp_email id="searchBindingEmail" class="searchBinding" maxlength="50"  ></td></tr>

<tr><td Class="addedit">URL</td><td colspan="3" ><input type="textbox" name="sp_url" id="searchBindingUrl" class="searchBinding" size=51 maxlength="50" ></td></tr>

   </div>
</div>
</table>
<P align=center>
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!--<input type=submit name=search Class="submitButton" Value=Search onclick=SearchRec()>-->
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
<input type=hidden name=flag>
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
if(valid.equals("newbinding")){
 %>
 <script language="JavaScript">

document.binding.sp_no.value="<%=beanobject.getCode()%>";
document.binding.sp_name.focus();
</script><%
}

if(valid.equals("searchbinding")){
 %>
  <script language="JavaScript">



document.binding.sp_no.value="<%=beanobject.getCode()%>";
document.binding.sp_name.value="<%=beanobject.getName()%>";
document.binding.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.binding.sp_address1.value="<%=beanobject.getAdd1()%>";
document.binding.sp_address2.value="<%=beanobject.getAdd2()%>";
document.binding.sp_city.value="<%=beanobject.getCity()%>";
document.binding.sp_state.value="<%=beanobject.getState()%>";
document.binding.sp_country.value="<%=beanobject.getCountry()%>";
document.binding.sp_pincode.value="<%=beanobject.getPin()%>";
document.binding.sp_phone.value="<%=beanobject.getPhone()%>";
document.binding.sp_fax.value="<%=beanobject.getFax()%>";
document.binding.sp_email.value="<%=beanobject.getEmail()%>";
document.binding.sp_url.value="<%=beanobject.getUrl()%>";


</script>
<%
}
if(valid.equals("Failbinding")){ %>
            <script language="JavaScript">
			alert("Record Not Found");
			document.binding.flag.value="new";
			document.binding.submit();
		   	</script><%
			}
	if(valid.equals("Updatebinding")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.binding.flag.value="new";
			document.binding.submit();
		   	</script><%
			}
if(valid.equals("CodeComparebinding")){
String  Autcode=(String)request.getAttribute("suppub");
%>
            <script>
            
            document.binding.sp_no.value="<%=beanobject.getCode()%>";
			document.binding.sp_name.value="<%=beanobject.getName()%>";
			document.binding.sp_shortdesc.value="<%=beanobject.getDesc()%>";
			document.binding.sp_address1.value="<%=beanobject.getAdd1()%>";
			document.binding.sp_address2.value="<%=beanobject.getAdd2()%>";
			document.binding.sp_city.value="<%=beanobject.getCity()%>";
			document.binding.sp_state.value="<%=beanobject.getState()%>";
			document.binding.sp_country.value="<%=beanobject.getCountry()%>";
			document.binding.sp_pincode.value="<%=beanobject.getPin()%>";
			document.binding.sp_phone.value="<%=beanobject.getPhone()%>";
			document.binding.sp_fax.value="<%=beanobject.getFax()%>";
			document.binding.sp_email.value="<%=beanobject.getEmail()%>";
			document.binding.sp_url.value="<%=beanobject.getUrl()%>";

			msg=confirm("Name Already Exists ,Do You Want update other than name");
			if(msg)
                   {
				    document.binding.flag.value="update";
		         	document.binding.submit();
				    
		            }
			</script><%
			}
if(valid.equals("Savebinding")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.binding.flag.value="new";
			 document.binding.submit();
		     </script>		
			<%
			}
if(valid.equals("Referredbinding")){
%>            <script>alert("You can't delete since the binding has been referred in other masters");
			 document.binding.flag.value="new";
			 document.binding.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("Deletebinding")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.binding.flag.value="new";
			document.binding.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">

document.binding.sp_no.value="<%=beanobject.getCode()%>";
document.binding.sp_name.value="<%=beanobject.getName()%>";
document.binding.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.binding.sp_address1.value="<%=beanobject.getAdd1()%>";
document.binding.sp_address2.value="<%=beanobject.getAdd2()%>";
document.binding.sp_city.value="<%=beanobject.getCity()%>";
document.binding.sp_state.value="<%=beanobject.getState()%>";
document.binding.sp_country.value="<%=beanobject.getCountry()%>";
document.binding.sp_pincode.value="<%=beanobject.getPin()%>";
document.binding.sp_phone.value="<%=beanobject.getPhone()%>";
document.binding.sp_fax.value="<%=beanobject.getFax()%>";
document.binding.sp_email.value="<%=beanobject.getEmail()%>";
document.binding.sp_url.value="<%=beanobject.getUrl()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.binding.flag.value="Confirmdete";
		   	document.binding.submit();
			
			}
			</script>
			<%
			}

			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
			    alert("Record Not Present!!!");
			    document.binding.flag.value="new";
			    document.binding.submit();
			</script>		
			<%
			}
		
			
if(valid.equals("UpdateCheck")){
%> 
                <script language="JavaScript">

document.binding.sp_no.value="<%=beanobject.getCode()%>";
document.binding.sp_name.value="<%=beanobject.getName()%>";
document.binding.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.binding.sp_address1.value="<%=beanobject.getAdd1()%>";
document.binding.sp_address2.value="<%=beanobject.getAdd2()%>";
document.binding.sp_city.value="<%=beanobject.getCity()%>";
document.binding.sp_state.value="<%=beanobject.getState()%>";
document.binding.sp_country.value="<%=beanobject.getCountry()%>";
document.binding.sp_pincode.value="<%=beanobject.getPin()%>";
document.binding.sp_phone.value="<%=beanobject.getPhone()%>";
document.binding.sp_fax.value="<%=beanobject.getFax()%>";
document.binding.sp_email.value="<%=beanobject.getEmail()%>";
document.binding.sp_url.value="<%=beanobject.getUrl()%>";

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.binding.flag.value="update";
		         	document.binding.submit();
				    
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


function change(){
document.binding.sup.checked=false;
}
function changes(){
document.binding.pub.checked=false;
}

function new_no(){
document.binding.method="get";
document.binding.flag.value="new";
document.binding.submit();
}

function SearchRec(){
document.binding.method="get";
var no=document.binding.sp_no.value;
if(no==""){
				document.binding.sp_no.focus();
				alert("Insufficent Data");
				document.binding.flag.value="new";
				document.binding.submit();
		  }else{
		       document.binding.flag.value="search";
			   document.binding.submit();
			  
		}
	
}


function SaveRec(){
             document.binding.method="get";
			   if(chk()){


				document.binding.flag.value="save";
		         	document.binding.submit();
					}
	else
	{
	  alert("Insufficent Data");
	  document.binding.flag.value="new";
	  document.binding.submit();
	}
	
}


function chk(){
var flag_s;
var i;
var sp=document.binding.sp_name.value;
if(sp=="")
{
				document.binding.sp_name.focus();
				document.binding.flag.value="none";
				document.binding.sp_name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.binding.sp_name.value.length;i++)
 	                      {
 	                       if(document.binding.sp_name.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.binding.sp_name.focus();
		                   	document.binding.sp_name.value="";
			                return false;
		                      }
		                   else if (document.binding.sp_no.value==""){
		                 	document.binding.sp_no.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function Findbinding(){

winpopup=window.open("search.jsp?flag=loadServlet","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");

}

function binding_code_val() {
if((isNaN(document.binding.sp_no.value))||(document.binding.sp_no.value == ' ')) {
document.binding.sp_no.select();
document.binding.sp_no.value="";

  }
}
function pub_pin_val() {
if((isNaN(document.binding.sp_pincode.value))||(document.binding.sp_pincode.value == ' ')) {
document.binding.sp_pincode.select();
document.binding.sp_pincode.value="";

  }
}
function pub_phone_val() {
if((isNaN(document.binding.sp_phone.value))||(document.binding.sp_phone.value == ' ')) {
document.binding.sp_phone.select();
document.binding.sp_phone.value="";

  }
}

function pub_Fax_val() {
if((isNaN(document.binding.sp_fax.value))||(document.binding.sp_fax.value == ' ')) {
document.binding.sp_fax.select();
document.binding.sp_fax.value="";

  }
}
function ClearRec(){
	 document.binding.sp_no.value="";
         document.binding.sp_name.value="";
         document.binding.sp_shortdesc.value="";
         document.binding.sp_address1.value="";
	 document.binding.sp_address2.value="";
	 document.binding.sp_city.value="";
	 document.binding.sp_state.value="";
	 document.binding.sp_country.value="";
	 document.binding.sp_pincode.value="";
	 document.binding.sp_phone.value="";
	 document.binding.sp_fax.value="";
	 document.binding.sp_email.value="";
	 document.binding.sp_url.value="";
		 document.binding.flag.value="";
}
function DelRec(){
document.binding.method="get";
if (document.binding.sp_no.value==""){
				document.binding.sp_no.focus();
				alert("Insufficent Data");
				document.binding.flag.value="new";
				document.binding.submit();
				}
				else{
				  document.binding.flag.value="delete";
				  document.binding.submit();
				 }                         
				
}
			
function load(){
	document.binding.sp_name.focus();

		 }		

</script>



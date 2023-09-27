<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ pagelanguage="java" session="true" buffer="12kb" import="java.util.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.PubSup.PubSupBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:pubsup
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchPubSupMas.js"></script>

</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<!-- Style Sheet -->
<form name=pubsup method="post" action=./PubSupServlet>
<br><br><br>

<h2>Publisher/Supplier&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Code</td><td ><input type="textbox" name="sp_no"  size=10  maxlength=5 onKeyUp="return pubsup_code_val();" readOnly >-->
<!--<INPUT onclick=Findpubsup() Class="submitButton" type="button" value=Find></td><td colspan="2" Class="addedit">-->

<tr>
<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Type<input type="hidden" name="sp_no" id="searchPubSupCode" class="searchPubSup" size=10  maxlength=5 onKeyUp="return pubsup_code_val();" readOnly ></td>

    </div>
</div>

<td colspan="2" Class="addedit">
<INPUT type=radio id="pub" CHECKED value=PUBLISHER name=pub onclick=change()>Publisher 
<INPUT type=radio id="sup" value=SUPPLIER name=sup onclick=changes()> &nbsp;Supplier</td></tr>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Name</td><td colspan="3" >
<input type="textbox" name="sp_name" id="searchPubSupName" class="searchPubSup" onkeyup="showPubSup(this.value);" size=42 maxlength="50" >&nbsp;

<INPUT onclick=Findpubsup() Class="submitButton" type="button" value=Find>

<FONT color=red size=4>*</FONT></td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Short&nbsp;Desc</td><td colspan="3" >
<input type="textbox" name="sp_shortdesc" id="searchPubSupDesc" class="searchPubSup" size=50 maxlength="50" ></td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr ><td Class="addedit">Address1</td><td colspan="3" >
<input type="textbox" name="sp_address1" id="searchPubSupAddress1" class="searchPubSup" size=50 maxlength="50" > </td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Address2</td><td colspan="3" >
<input type="textbox" name="sp_address2" id="searchPubSupAddress2" class="searchPubSup" size=50 maxlength="25" ></td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">City</td><td ><input type="textbox" name="sp_city" id="searchPubSupCity" class="searchPubSup" size=20 maxlength="25" ></td>

   </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">State</td><td ><input type="textbox" name="sp_state" id="searchPubSupState" class="searchPubSup" size=19 maxlength="25" >  </td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Country</td><td ><input type="textbox" name="sp_country" id="searchPubSupCountry" class="searchPubSup" size=20 maxlength="25" ></td>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Pincode</td><td ><input type="text" name="sp_pincode" id="searchPubSupPincode" class="searchPubSup" size=19 maxlength="6" onKeyUp="return pub_pin_val();"></td></tr>


<tr><td Class="addedit">Phone</td><td ><input type="textbox" name="sp_phone" id="searchPubSupPhone" class="searchPubSup" size=20 maxlength="12" onKeyUp="return pub_phone_val();">


<td Class="addedit">Fax</td><td ><input type="textbox" name="sp_fax" id="searchPubSupFax" class="searchPubSup" size=19 maxlength="25" onKeyUp="return pub_Fax_val();"></td> </tr>


<tr><td Class="addedit">Email</td><td colspan="3" ><INPUT type=textbox size=30 name=sp_email id="searchPubSupEmail" class="searchPubSup" size=50 maxlength="50"  >  </td></tr>


<tr><td Class="addedit">URL</td><td colspan="3" ><input type="textbox" name="sp_url" id="searchPubSupUrl" class="searchPubSup" size=50 maxlength="50" > </td></tr>

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
if(valid.equals("newPubSup")){
 %>
 <script language="JavaScript">
document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
document.pubsup.sp_name.focus();
</script><%
}

if(valid.equals("searchPubSup")){
 %>
  <script language="JavaScript">

  var s;
			s="<%=beanobject.getType()%>";

			if(s==document.pubsup.pub.value)
			{
			document.pubsup.pub.checked=true;
			}

			if(s==document.pubsup.sup.value)
			{
			document.pubsup.sup.checked=true;
   			document.pubsup.pub.checked=false;
			}

document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
document.pubsup.sp_name.value="<%=beanobject.getName()%>";
document.pubsup.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.pubsup.sp_address1.value="<%=beanobject.getAdd1()%>";
document.pubsup.sp_address2.value="<%=beanobject.getAdd2()%>";
document.pubsup.sp_city.value="<%=beanobject.getCity()%>";
document.pubsup.sp_state.value="<%=beanobject.getState()%>";
document.pubsup.sp_country.value="<%=beanobject.getCountry()%>";
document.pubsup.sp_pincode.value="<%=beanobject.getPin()%>";
document.pubsup.sp_phone.value="<%=beanobject.getPhone()%>";
document.pubsup.sp_fax.value="<%=beanobject.getFax()%>";
document.pubsup.sp_email.value="<%=beanobject.getEmail()%>";
document.pubsup.sp_url.value="<%=beanobject.getUrl()%>";


</script>
<%
}
if(valid.equals("FailPubSup")){ %>
            <script language="JavaScript">
			alert("Record Not Found");
			document.pubsup.flag.value="new";
			document.pubsup.submit();
		   	</script><%
			}
	if(valid.equals("UpdatePubSup")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.pubsup.flag.value="new";
			document.pubsup.submit();
		   	</script><%
			}
if(valid.equals("CodeCompareSupPub")){
%>
            <script>
            var s;
			s="<%=beanobject.getType()%>";

			if(s==document.pubsup.pub.value)
			{
			document.pubsup.pub.checked=true;
			}

			if(s==document.pubsup.sup.value)
			{
			document.pubsup.sup.checked=true;
   			document.pubsup.pub.checked=false;
			}

document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
document.pubsup.sp_name.value="<%=beanobject.getName()%>";
document.pubsup.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.pubsup.sp_address1.value="<%=beanobject.getAdd1()%>";
document.pubsup.sp_address2.value="<%=beanobject.getAdd2()%>";
document.pubsup.sp_city.value="<%=beanobject.getCity()%>";
document.pubsup.sp_state.value="<%=beanobject.getState()%>";
document.pubsup.sp_country.value="<%=beanobject.getCountry()%>";
document.pubsup.sp_pincode.value="<%=beanobject.getPin()%>";
document.pubsup.sp_phone.value="<%=beanobject.getPhone()%>";
document.pubsup.sp_fax.value="<%=beanobject.getFax()%>";
document.pubsup.sp_email.value="<%=beanobject.getEmail()%>";
document.pubsup.sp_url.value="<%=beanobject.getUrl()%>";

			msg=confirm("Name Already Exists ,Do You Want update other than name ?");
			if(msg)
                   {
				    document.pubsup.flag.value="update";
		         	document.pubsup.submit();
				    
		            }
			</script><%
			}
if(valid.equals("SavePubSup")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.pubsup.flag.value="new";
			 document.pubsup.submit();
		     </script>		
			<%
			}
if(valid.equals("DefaultSupPub")){
	%>             <script language="JavaScript">
				 alert("Default value cannot be Deleted!");
				 document.pubsup.flag.value="new";
				 document.pubsup.submit();
			     </script>		
				<%
				}
if(valid.equals("ReferredPubSup")){
%>            <script>alert("You can't delete since the pubsup has been referred in other masters");
			 document.pubsup.flag.value="new";
			 document.pubsup.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeletePubSup")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.pubsup.flag.value="new";
			document.pubsup.submit();
		   </script>		
			<%
			}		
			
			
			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">

document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
document.pubsup.sp_name.value="<%=beanobject.getName()%>";
document.pubsup.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.pubsup.sp_address1.value="<%=beanobject.getAdd1()%>";
document.pubsup.sp_address2.value="<%=beanobject.getAdd2()%>";
document.pubsup.sp_city.value="<%=beanobject.getCity()%>";
document.pubsup.sp_state.value="<%=beanobject.getState()%>";
document.pubsup.sp_country.value="<%=beanobject.getCountry()%>";
document.pubsup.sp_pincode.value="<%=beanobject.getPin()%>";
document.pubsup.sp_phone.value="<%=beanobject.getPhone()%>";
document.pubsup.sp_fax.value="<%=beanobject.getFax()%>";
document.pubsup.sp_email.value="<%=beanobject.getEmail()%>";
document.pubsup.sp_url.value="<%=beanobject.getUrl()%>";
			s="<%=beanobject.getType()%>";

			if(s==document.pubsup.pub.value)
			{
			document.pubsup.pub.checked=true;
			}

			if(s==document.pubsup.sup.value)
			{
			document.pubsup.sup.checked=true;
   			document.pubsup.pub.checked=false;
			}

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.pubsup.flag.value="Confirmdete";
		   	document.pubsup.submit();
			
			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
			    alert("Record Not Present!!!");
			    document.pubsup.flag.value="new";
			    document.pubsup.submit();
			</script>		
			<%
			}
		
			
if(valid.equals("UpdateCheck")){
%> 
                <script language="JavaScript">
		s="<%=beanobject.getType()%>";

			if(s==document.pubsup.pub.value)
			{
			document.pubsup.pub.checked=true;
			}

			if(s==document.pubsup.sup.value)
			{
			document.pubsup.sup.checked=true;
   			document.pubsup.pub.checked=false;
			}
document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
document.pubsup.sp_name.value="<%=beanobject.getName()%>";
document.pubsup.sp_shortdesc.value="<%=beanobject.getDesc()%>";
document.pubsup.sp_address1.value="<%=beanobject.getAdd1()%>";
document.pubsup.sp_address2.value="<%=beanobject.getAdd2()%>";
document.pubsup.sp_city.value="<%=beanobject.getCity()%>";
document.pubsup.sp_state.value="<%=beanobject.getState()%>";
document.pubsup.sp_country.value="<%=beanobject.getCountry()%>";
document.pubsup.sp_pincode.value="<%=beanobject.getPin()%>";
document.pubsup.sp_phone.value="<%=beanobject.getPhone()%>";
document.pubsup.sp_fax.value="<%=beanobject.getFax()%>";
document.pubsup.sp_email.value="<%=beanobject.getEmail()%>";
document.pubsup.sp_url.value="<%=beanobject.getUrl()%>";

                 msg=confirm("Pub/Sup Name has been referred in other masters, Are You Sure To Update?");
                
                 if(msg)
                   {
				    document.pubsup.flag.value="update";
		         	document.pubsup.submit();
				    
		            }
			    </script>	

			<%
			}
if(valid.equals("Updatename")){
	%> 
	                <script language="JavaScript">
			s="<%=beanobject.getType()%>";

				if(s==document.pubsup.pub.value)
				{
				document.pubsup.pub.checked=true;
				}

				if(s==document.pubsup.sup.value)
				{
				document.pubsup.sup.checked=true;
	   			document.pubsup.pub.checked=false;
				}
	document.pubsup.sp_no.value="<%=beanobject.getCode()%>";
	document.pubsup.sp_name.value="<%=beanobject.getName()%>";
	document.pubsup.sp_shortdesc.value="<%=beanobject.getDesc()%>";
	document.pubsup.sp_address1.value="<%=beanobject.getAdd1()%>";
	document.pubsup.sp_address2.value="<%=beanobject.getAdd2()%>";
	document.pubsup.sp_city.value="<%=beanobject.getCity()%>";
	document.pubsup.sp_state.value="<%=beanobject.getState()%>";
	document.pubsup.sp_country.value="<%=beanobject.getCountry()%>";
	document.pubsup.sp_pincode.value="<%=beanobject.getPin()%>";
	document.pubsup.sp_phone.value="<%=beanobject.getPhone()%>";
	document.pubsup.sp_fax.value="<%=beanobject.getFax()%>";
	document.pubsup.sp_email.value="<%=beanobject.getEmail()%>";
	document.pubsup.sp_url.value="<%=beanobject.getUrl()%>";

	               
	                 msg=confirm("Record Already Exists Are You Sure To Update?");
	                 if(msg)
	                   {
					    document.pubsup.flag.value="update";
			         	document.pubsup.submit();
					    
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
$(function showPubSup(data) {
	var cache = {};
    $( "#searchPubSupName").autocomplete({
    	width: 100,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: false,
        highlight: true,
            	
    	source: function (request, response) {
    		var type = document.getElementById('pub');
    		if(type.checked)
    		{
    			type = type.value;
    		}
    		else
    		{
    			type = document.getElementById('sup').value;
    		}
			var term          = request.term.toLowerCase(),
        element       = this.element,
        cache         = this.element.data('autocompleteCache') || {},
        foundInCache  = false;

    $.each(cache, function(key, data){
      if (term.indexOf(key) === 0 && data.length > 0) {
        response(data);
        foundInCache = true;
        return;
      }
    });

      if (foundInCache) return;

			
            $.ajax({
                url: "/AutoLib/PubSup/PubSupServlet?flag="+data,
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	sp_name : request.term,
                	sptype : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.sp_name);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                id: item.sp_code, value: item.sp_name, short_desc: item.short_desc,address1: item.address1, address2: item.address2, city: item.city, state: item.state, country: item.country, pincode: item.pincode, phone: item.phone, fax: item.fax, emailid: item.emailid, urlid: item.urlid, sp_type: item.sp_type };
			    //id: item.sp_code, value: item.sp_name };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
         $("#searchPubSupName").val(ui.item.value); // display the selected text
         $("#searchPubSupCode").val(ui.item.id); // save selected id to hidden input
		 $("#searchPubSupDesc").val(ui.item.short_desc);
		 $("#searchPubSupAddress1").val(ui.item.address1);
         $("#searchPubSupAddress2").val(ui.item.address2);		 
		 $("#searchPubSupCity").val(ui.item.city);
		 $("#searchPubSupState").val(ui.item.state);
		 $("#searchPubSupCountry").val(ui.item.country);
		 $("#searchPubSupPincode").val(ui.item.pincode);
		 $("#searchPubSupPhone").val(ui.item.phone);
		 $("#searchPubSupFax").val(ui.item.fax);
		 $("#searchPubSupEmail").val(ui.item.emailid);
		 $("#searchPubSupUrl").val(ui.item.urlid);
		 $("#searchPubSupType").val(ui.item.sp_type);
		 console.log($("#searchPubSupCode").val + " has faded!");
         return false;
     },
  
      });
  });


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}


function change(){
document.pubsup.sup.checked=false;
}
function changes(){
document.pubsup.pub.checked=false;
}

function new_no(){
document.pubsup.method="get";
document.pubsup.flag.value="new";
document.pubsup.submit();
}

function SearchRec(){
document.pubsup.method="get";
var no=document.pubsup.sp_no.value;
if(no==""){
				document.pubsup.sp_no.focus();
				alert("Insufficent Data");
				document.pubsup.flag.value="new";
				document.pubsup.submit();
		  }else{
		       document.pubsup.flag.value="search";
			   document.pubsup.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){
             document.pubsup.method="get";
			   if(!isWhitespace(document.pubsup.sp_name.value)){

				document.pubsup.flag.value="save";
		         	document.pubsup.submit();
					}
	else
	{
	   alert("Insufficent Data");
	   document.pubsup.flag.value="new";
       document.pubsup.submit();
	}
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

function chk_mc(){
var flag_cs;
var c;
var mc=document.pubsup.sp_no.value;
if(mc=="")
{
				document.pubsup.sp_no.focus();
				document.pubsup.flag.value="none";
				document.pubsup.sp_no.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
function chk(){
var flag_s;
var i;
var sp=document.pubsup.sp_name.value;
if(sp=="")
{
				document.pubsup.sp_name.focus();
				document.pubsup.flag.value="none";
				document.pubsup.sp_name.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.pubsup.sp_name.value.length;i++)
 	                      {
 	                       if(document.pubsup.sp_name.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.pubsup.sp_name.focus();
		                   	document.pubsup.sp_name.value="";
			                return false;
		                      }
		                   else if (document.pubsup.sp_no.value==""){
		                 	document.pubsup.sp_no.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function Findpubsup(){

if(document.pubsup.pub.checked==true)
winpopup=window.open("search.jsp?flag=Pub","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
if(document.pubsup.sup.checked==true)
winpopup=window.open("search.jsp?flag=Sup","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}

function pubsup_code_val() {
if((isNaN(document.pubsup.sp_no.value))||(document.pubsup.sp_no.value == ' ')) {
document.pubsup.sp_no.select();
document.pubsup.sp_no.value="";

  }
}
function pub_pin_val() {
if((isNaN(document.pubsup.sp_pincode.value))||(document.pubsup.sp_pincode.value == ' ')) {
document.pubsup.sp_pincode.select();
document.pubsup.sp_pincode.value="";

  }
}
function pub_phone_val() {
if((isNaN(document.pubsup.sp_phone.value))||(document.pubsup.sp_phone.value == ' ')) {
document.pubsup.sp_phone.select();
document.pubsup.sp_phone.value="";

  }
}

function pub_Fax_val() {
if((isNaN(document.pubsup.sp_fax.value))||(document.pubsup.sp_fax.value == ' ')) {
document.pubsup.sp_fax.select();
document.pubsup.sp_fax.value="";

  }
}
function ClearRec(){
	 document.pubsup.sp_no.value="";
         document.pubsup.sp_name.value="";
         document.pubsup.sp_shortdesc.value="";
         document.pubsup.sp_address1.value="";
	 document.pubsup.sp_address2.value="";
	 document.pubsup.sp_city.value="";
	 document.pubsup.sp_state.value="";
	 document.pubsup.sp_country.value="";
	 document.pubsup.sp_pincode.value="";
	 document.pubsup.sp_phone.value="";
	 document.pubsup.sp_fax.value="";
	 document.pubsup.sp_email.value="";
	 document.pubsup.sp_url.value="";
		 document.pubsup.flag.value="";
}
function DelRec(){
document.pubsup.method="get";
if (document.pubsup.sp_no.value==""){
				document.pubsup.sp_no.focus();
				alert("Insufficent Data");
				document.pubsup.flag.value="new";
				document.pubsup.submit();
				}
				else{
				  document.pubsup.flag.value="delete";
				  document.pubsup.submit();
				 }                         
				
}
			
function load(){
	document.pubsup.sp_name.focus();

		 }		

</script>

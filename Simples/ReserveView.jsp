<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security,Common.Security_Counter"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<jsp:useBean id="bean" scope="request" class="Lib.Auto.Account.AccountBean"/>

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
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">


</head>
<body>
<script language = "Javascript">
window.history.forward(0)
</script>
<form name="Reserve" method="post" action=./AccountServlet>
<br><br><br>

 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
   <h2> Reserve&nbsp;Book </h2>
<table align="center" class="contentTable" width="35%">
<td>

<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<td>&nbsp;&nbsp;
</tr>
<tr><td Class="addedit">User&nbsp;ID<td><input type=text name="ruid" size="20"  maxlength="20">

    
<tr><td Class="addedit">Password<td><input type=password name="rpwd" size="20" maxlength="20">
   
   
<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=Save Class="submitButton" value=Reserve onclick=SaveRec()>
<input type=reset name=Clear Class="submitButton"  Value=Clear >

</CENTER>

<input type=hidden name=flag>
<input type=hidden name=raccno>
<input type=hidden name=rdtype>

</table>
<tr><td> &nbsp; </td></tr>
</table>
</form>
</body>
</html>
<br>
<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("usernotfound")){
	 %>
	<script language="JavaScript">
	alert("Invalid UserId/Password!!!");
	document.Reserve.ruid.focus();
	</script><%

	}
}


String strrply=bean.getauthor();
if(strrply!=null){
	 out.println("<html>");
	out.print("<head>");
	out.print("</head>");
	out.println("<BODY>");
	out.println("<table width='25%' >");
	out.print("<font color='#800000' size='3'>");
	out.print("<div Class='icon-ok'>");
   	out.println(""+strrply);
   	out.print("</div>");
   	out.print("</font>");
   	out.println("</table>");
   	out.println("</BODY>");
   	out.println("</html>");
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

function SaveRec(){

            
			   if((document.Reserve.ruid.value=="")||(document.Reserve.rpwd.value=="")){
					
                      alert("Insufficient Data !");

			}		
	else
	{
                
	               msg=confirm("Do you want to Reserve?");
		           if(msg){

		             var cboxes = document.getElementsByName('raccno1');
                     var len = cboxes.length;

                     for (var i=0; i<len; i++) {
                           document.Reserve.raccno.value=cboxes[i].value;                          
                     }
                     
                     var cboxes1 = document.getElementsByName('rdtype1');
                     var len1 = cboxes1.length;

                     for (var j=0; j<len1; j++) {
                           document.Reserve.rdtype.value=cboxes1[j].value;                           
                     }                     
                     document.Reserve.flag.value="Reserve";
   		         	 document.Reserve.submit();	
                     
                    /* alert("Book Data No  "+document.Reserve.raccno.value);
                     alert("Document Type "+document.Reserve.rdtype.value);
                     alert("Flag Value    "+document.Reserve.flag.value);*/	
		           }
		           else
		           {
		                  return false;
		           }
	                
	}
	
}


function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}

</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.IssueArrylist" uid="rk" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Simples/ReserveView.jsp">  	    

   	    <display:column property="uid"  title="User No"  maxLength="200" >    	   
   	    </display:column>    
   	     
	    <display:column property="uname"  title="User Name"  maxLength="200" >     
	    </display:column>
	    
    	<display:column property="accno"  title="Access No"  maxLength="50">     	
		</display:column>
		
	   <display:column property="title"  title="Title"  maxLength="25" >     
	   </display:column>
	   
	   <display:column property="duedt"  title="Due Date"  maxLength="25" > 	
	   </display:column>
				
       <display:column property="dtype"  title="Document"  maxLength="25" > 	
	   </display:column>
	   
	    <display:column maxLength="50" >   
   	     	<input type="hidden" id="raccno1" name="raccno1" value='<c:out value="${rk.accno}"/>'>
      	    <input type="hidden" id="rdtype1" name="rdtype1" value='<c:out value="${rk.dtype}"/>'>       	
   	    </display:column>     	        

	   <display:setProperty name="basic.empty.showtable" value="true" />
   </display:table>		
	   

</div>
	  

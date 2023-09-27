<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css"/>

<script language="javascript" src="/AutoLib/popcalendar.js"></script>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>




<jsp:useBean id="MsgMasBean" scope="request" class="Lib.Auto.MsgMas.MsgMasBean"></jsp:useBean>

<HTML>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>

<title>AutoLib</title>

</head>
<body background="/AutoLib/soft.jpg">


<form name=MsgMas method="post" action=./MsgMasServlet>
<br><br>
<p align="center"><h2>Message&nbsp;Master</h2></p>



<table align="center" class="contentTable" width="35%">
<tr><td><table align="center" width="90%">

<tr><td> &nbsp; </td></tr>

<tr>
<td Class="addedit">msgCode<td><input type=text name=msgCode size=10 maxlength=5" onKeydown="Javascript: if (event.keyCode==13) return SearchRec();">
	
	<td>
	
<%-- 	<INPUT name=date size=10  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=date size=10 id="datepicker" value="<%=dateString%>" >
				
	</td>
	</tr>


<tr>

<td Class="addedit">LibMessage<td colspan=3>
<textarea  rows="3" cols="37" name="libMsg" maxLength=2000  onkeypress="return noenter(event)"></textarea>


</td>
</tr>

<tr>
<td Class="addedit">WhatsNew<td colspan=3><textarea  rows="5" cols="37" name="whatsNew" maxLength=2000  onkeypress="return noenter(event)"></textarea></td>


</tr>




<tr><td> &nbsp; </td></tr>
</table></td></tr></table>





<p align="center">
<input type=button name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save  Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<input type=button name=Search Class="submitButton" Value=Search onclick=SearchRec()>

<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
<input type=hidden name=flag>
</p>

</form></body>


<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newMsgCode")){
 %>
 <script language="JavaScript">

document.MsgMas.msgCode.value="<%=MsgMasBean.getMsgCode()%>";
document.MsgMas.libMsg.focus();
</script><%
}

if(valid.equals("searchMsg")){
	%>
	 <script language="JavaScript">
	
	document.MsgMas.msgCode.value="<%=MsgMasBean.getMsgCode()%>";
	document.MsgMas.libMsg.value="<%=MsgMasBean.getLibMsg()%>";
	document.MsgMas.whatsNew.value="<%=MsgMasBean.getWhatsNew()%>";
	document.MsgMas.date.value="<%=MsgMasBean.getDate()%>";
	
	</script>
	
	<%
	
	
	
}





if(valid.equals("FailMsg")){
	%>
    <script language="JavaScript">
	alert("Record Not Found");
	document.MsgMas.flag.value="new";
	document.MsgMas.submit();
   	</script><%
}


	if(valid.equals("UpdateMasMas")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.MsgMas.flag.value="new";
			document.MsgMas.submit();
		   	</script><%
			}		

	if(valid.equals("SaveCity")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.MsgMas.flag.value="new";
			 document.MsgMas.submit();
		     </script>		
			<%
			}
	
	if(valid.equals("deleteCheck")){
		
%>       
    <script language="JavaScript">
	    
    	document.MsgMas.msgCode.value="<%=MsgMasBean.getMsgCode()%>";
    	document.MsgMas.libMsg.value="<%=MsgMasBean.getLibMsg()%>";
    	document.MsgMas.whatsNew.value="<%=MsgMasBean.getWhatsNew()%>";
    	document.MsgMas.date.value="<%=MsgMasBean.getDate()%>";
        

	msg=confirm("Are You Sure To Delete?");
	if(msg){
	 document.MsgMas.flag.value="Confirmdete";
   	document.MsgMas.submit();
	
	}
	</script>		
	<%
	}
	
	
			if(valid.equals("DeleteMsg")){
				
				%>       
	            <script language="JavaScript">
				alert("Record Deleted Successfully!");
				document.MsgMas.flag.value="new";
				document.MsgMas.submit();
			   </script>		
				<%
				
			}		
						
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">	  

			    alert("Record Not Present!!!");
			    document.MsgMas.flag.value="new";
			    document.MsgMas.submit();
			</script>		
			<%
			}				
			
if(valid.equals("UpdateMsg")){
	
	
	%> 
    <script language="JavaScript">
    
    document.MsgMas.msgCode.value="<%=MsgMasBean.getMsgCode()%>";
	document.MsgMas.libMsg.value="<%=MsgMasBean.getLibMsg()%>";
	document.MsgMas.whatsNew.value="<%=MsgMasBean.getWhatsNew()%>";
	document.MsgMas.date.value="<%=MsgMasBean.getDate()%>";

     msg=confirm("Record Already Exists Are You Sure To Update?");
     if(msg)
       {
	    document.MsgMas.flag.value="update";
     	document.MsgMas.submit();
	    
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

document.MsgMas.method="post";
document.MsgMas.flag.value="new";
document.MsgMas.submit();
}

function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
 
    return key !== 13;
    
}

function RestrictEnterKey(){
	
	alert("sfsdf");
}

function SearchRec(){

document.MsgMas.method="post";
var no=document.MsgMas.msgCode.value;
if(no==""){
				document.MsgMas.msgCode.focus();
				alert("Insufficent Data");
				document.MsgMas.flag.value="new";
				document.MsgMas.submit();
		  }else{
		       document.MsgMas.flag.value="search";
			   document.MsgMas.submit();
			  
		}
	
}


function SaveRec(){
	
if(chk_mc()){
             document.MsgMas.method="post";
			  if(!isWhitespace(document.MsgMas.libMsg.value)){
			       	document.MsgMas.flag.value="save";
		         	document.MsgMas.submit();
					
			}		
	else
	{
	alert("Insufficent Data.");
	document.MsgMas.name.focus();		  
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
var mc=document.MsgMas.msgCode.value;
if(mc=="")
{
				document.MsgMas.msgCode.focus();
				document.MsgMas.flag.value="none";
				document.MsgMas.msgCode.value="";
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
var sp=document.MsgMas.libMsg.value;

if(sp=="")
{
document.MsgMas.name.focus();
				document.MsgMas.flag.value="none";
				document.MsgMas.libMsg.value="";
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
		                   	document.MsgMas.libMsg.focus();
		                   	document.MsgMas.libMsg.value="";
			                return false;
		                      }
		                   else if (document.MsgMas.msgCode.value==""){
		                 	document.MsgMas.msgCode.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindCity(val)

{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function City_code_val() {
if((isNaN(document.MsgMas.msgCode.value))||(document.MsgMas.msgCode.value == ' ')) {
document.MsgMas.msgCode.select();
document.MsgMas.msgCode.value="";
    
  }
}
function ClearRec(){ 
		 document.MsgMas.msgCode.value="";
         document.MsgMas.name.value="";
         document.MsgMas.desc.value="";
         document.MsgMas.state.value="";
         document.MsgMas.country.value="";
         document.MsgMas.pcode.value="";
		 document.MsgMas.flag.value="";
}
function DelRec(){
document.MsgMas.method="post";
if (document.MsgMas.msgCode.value==""){
				document.MsgMas.msgCode.focus();
				alert("Insufficent Data");
				document.MsgMas.flag.value="new";
				document.MsgMas.submit();
				}
				else{
				  document.MsgMas.flag.value="delete";
				  document.MsgMas.submit();
				 }                         
				
}



function load(){
	document.MsgMas.name.focus();

		 }			


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
</html>
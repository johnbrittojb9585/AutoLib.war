<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Fine.Finebean"/>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Fine
//%>
<!--
///////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib Software Systems</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchFineMas.js"></script>

</head>
<body background="/AutoLib/soft.jpg">
<form name=Fine  method="post" action=./FineServlet>
<br><br><br>

<h2>Fine&nbsp;Master</h2>
<CENTER>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<div class="search-container">
	<div class="ui-widget">

<tr><td Class="addedit">Fine&nbsp;Id</td>
<!-- <td><INPUT  size =25 name=FCODE maxlength=5 onKeyUp="return Fine_ID_val();"> -->
<td><INPUT  size =25 name=FCODE maxlength=5 id="searchFineId" class="searchFineMas" onkeyup="showFineId(this.value);">
<INPUT type=button value=Find name="FindId" Class="submitButton" onclick=FindFine('Fine')></td></tr>
<tr><td Class="addedit">Group</td><td ><INPUT  size =25 name=GROUPNAME id="searchFineGroup" class="searchFineMas" onkeyup="showFineGroup(this.value);" readonly="true">
<INPUT type=button value=Find name="FindG" Class="submitButton" onclick=FindFine('Group')></td></tr>
<tr><td Class="addedit">Fine&nbsp;Period</td><td ><INPUT   size =25 name=PERIOD maxlength=5 value="0" id="searchFinePeriod" class="searchFineMas" onKeyUp="return Fine_per_val();"></td></tr>
<tr><td Class="addedit">Fine&nbsp;Amount</td><td ><INPUT   size =25 name=AMOUNT maxlength=5 value="0" id="searchFineAmount" class="searchFineMas" onKeyUp="return Fine_Amt_val();"></td></tr>
<tr><td Class="addedit">Period&nbsp;Type</td><td ><SELECT  name=FTYPE size="1" value="DAILY" id="searchFineType" class="searchFineMas"><option selected value="DAILY">DAILY</option><option value="WEEKLY">WEEKLY</option><option value="MONTHLY">MONTHLY</option><option value="YEARLY">YEARLY</option></SELECT></P></td></tr>
<br>

    </div>
</div>

<tr><td colspan="3"><center>
<INPUT type="hidden" name=GROUP>
<INPUT type=button value=New name="New" Class="submitButton" onclick=new_no()>
<INPUT type=button value=Save name="Save" Class="submitButton" onclick=SaveRec()>
<INPUT type=button value=Delete name=Delete Class="submitButton" onclick=DelRec()>
<INPUT type=submit value=Search name=Search Class="submitButton" onclick=SearchRec()> 
<INPUT type=reset Class="submitButton" value=clear>
<INPUT type=hidden name=flag>
</center></td></tr></table>
</center></td></table></CENTER>
</form>
</body>
</html>

<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newFine")){
 %>
 <script language="JavaScript">
document.Fine.FCODE.value="<%=bean.getFcode()%>";
document.Fine.FindG.focus();
</script><%
}

if(valid.equals("searchFine")){
 %>
  <script language="JavaScript">
document.Fine.FCODE.value="<%=bean.getFcode()%>";
document.Fine.GROUPNAME.value="<%=bean.getGcode()%>";
document.Fine.PERIOD.value="<%=bean.getFperiod()%>";
document.Fine.AMOUNT.value="<%=bean.getFamount()%>";
document.Fine.FTYPE.value="<%=bean.getType()%>";
</script>
<%
}
if(valid.equals("FailFine")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Fine.flag.value="new";
			document.Fine.submit();
		   	</script><%
			}
	if(valid.equals("UpdateFine")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Fine.flag.value="new";
			document.Fine.submit();
		   	</script><%
			}		
if(valid.equals("CodeCompareFine")){
String  Autcode=(String)request.getAttribute("Author_Mas_Val_code");
%>
            <script>
			alert("Fine Name Already Exists in Code:"+"<%=Autcode%>");
	    	document.Fine.flag.value="new";
			document.Fine.submit();
			</script><%
			}
if(valid.equals("SaveFine")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Fine.flag.value="new";
			 document.Fine.submit();
		     </script>		
			<%
			}
if(valid.equals("ReferredFine")){
%>            <script>alert("You can't delete since the Fine has been referred in other masters");
			 document.Fine.flag.value="new";
			 document.Fine.submit();
			</script>	
			<%
			}	
			if(valid.equals("DeleteFine")){
			
%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Fine.flag.value="new";
			document.Fine.submit();
		   </script>		
			<%
			}		
			
			if(valid.equals("deleteCheck")){
			
%>
            <script language="JavaScript">
			document.Fine.FCODE.value="<%=bean.getFcode()%>";
			document.Fine.GROUPNAME.value="<%=bean.getGcode()%>";
			document.Fine.PERIOD.value="<%=bean.getFperiod()%>";
			document.Fine.AMOUNT.value="<%=bean.getFamount()%>";
			document.Fine.FTYPE.value="<%=bean.getType()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Fine.flag.value="Confirmdete";
		   	document.Fine.submit();	
			
			}
			</script>		
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>       
            <script language="JavaScript">
			document.Fine.FCODE.value="<%=bean.getFcode()%>";
			document.Fine.GROUPNAME.value="<%=bean.getGcode()%>";
			document.Fine.PERIOD.value="<%=bean.getFperiod()%>";
			document.Fine.AMOUNT.value="<%=bean.getFamount()%>";
			document.Fine.FTYPE.value="<%=bean.getType()%>";
			    alert("Record Not Present!!!");
			</script>		
			<%
			}				
		
			
if(valid.equals("UpdateCheck")){ 
%>
                <script language="JavaScript">
				document.Fine.FCODE.value="<%=bean.getFcode()%>";
				document.Fine.GROUPNAME.value="<%=bean.getGcode()%>";
				document.Fine.PERIOD.value="<%=bean.getFperiod()%>";
				document.Fine.AMOUNT.value="<%=bean.getFamount()%>";
				document.Fine.FTYPE.value="<%=bean.getType()%>";
                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Fine.flag.value="update";
		         	document.Fine.submit();	
				    
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
document.Fine.method="get";
document.Fine.flag.value="new";
document.Fine.submit();
}

function SearchRec(){
document.Fine.method="get";
var no=document.Fine.FCODE.value;
if(no==""){
				document.Fine.FCODE.focus();
				alert("Insufficent Data");
				document.Fine.flag.value="new";
				document.Fine.submit();

		  }else{
		       document.Fine.flag.value="search";
			   document.Fine.submit();
			  
		}
	
}


function SaveRec(){
             document.Fine.method="get";
			   if(chk()){
			       	document.Fine.flag.value="save";
		         	document.Fine.submit();	
					
			}		
	else
	{
	alert("Insufficent Data");
	}
	
}


function chk(){
var flag_s;
var i;
var sp=document.Fine.GROUPNAME.value;
if(sp=="")
{
document.Fine.GROUPNAME.focus();
				document.Fine.flag.value="none";
				document.Fine.GROUPNAME.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Fine.GROUPNAME.value.length;i++)
 	                      {
 	                       if(document.Fine.GROUPNAME.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Fine.GROUPNAME.focus();
		                   	document.Fine.GROUPNAME.value="";
			                return false;
		                      }
		                   else if (document.Fine.FCODE.value==""){
		                 	document.Fine.FCODE.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindFine(val)
{

winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function Fine_ID_val() {
if((isNaN(document.Fine.FCODE.value))||(document.Fine.FCODE.value == ' ')) {
document.Fine.FCODE.select();
document.Fine.FCODE.value="";

  }
}

function Fine_per_val() {
if((isNaN(document.Fine.PERIOD.value))||(document.Fine.PERIOD.value == ' ')) {
document.Fine.PERIOD.select();
document.Fine.PERIOD.value="";

  }
}
function Fine_Amt_val() {
if((isNaN(document.Fine.AMOUNT.value))||(document.Fine.AMOUNT.value == ' ')) {
document.Fine.AMOUNT.select();
document.Fine.AMOUNT.value="";

  }
}
function ClearRec(){ 
		 document.Fine.FCODE.value="";
         document.Fine.GROUPNAME.value="";
         document.Fine.PERIOD.value="";
         document.Fine.AMOUNT.value="";
	 document.Fine.FTYPE.value="";
		 document.Fine.flag.value="";
}
function DelRec(){
document.Fine.method="get";
if (document.Fine.FCODE.value==""){
				document.Fine.FCODE.focus();
				alert("Insufficent Data");
				document.Fine.flag.value="new";
				document.Fine.submit();
				}
				else{
				  document.Fine.flag.value="delete";
				  document.Fine.submit();
				 }                         
				
}
			
		

</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

  

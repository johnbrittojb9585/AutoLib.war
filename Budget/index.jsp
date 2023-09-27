<html>

<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>

<%@ page language="java" session="true" buffer="12kb" 
			import="java.sql.*" import="java.util.*" 
			import="Lib.Auto.Branch.BranchBean" %>
			
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Budget.BudgetBean"/>
<jsp:useBean id="bean1" scope="request" class="Lib.Auto.MemberTransfer.MemberTransRefBean" />

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg">

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<form name=Budget method="post" action=./BudgetServlet>



<br><br>
<h2>Budget&nbsp;Master</h2>

<table align="center" class="contentTable" width="60%">

<tr><td>&nbsp;</td></tr>
<tr><td><table align="center" width="80%">

<tr>
   <td Class="addedit">Budget.&nbsp;No</td>
   <td> <input name="Bud_Code" size="10" maxlength=5 onKeyUp="return Budget_code_val();">
   	<input type="button" value="Find" name="BFind" Class="submitButton" onclick="FindValue('bcode')"></td>
   	
   	
   <td Class="addedit">Amt Spent</td>
    <td><input name="Aspent" size="16" maxlength=12 value="0" onKeyUp="return Amt_Spt_val();" readOnly></td>
  
  <td> <font color="RED" size="2"><b>BalAmt</b></font></td>
  <td><input name="BalAmount" size="16" maxlength=12 value="0" readOnly></td>	
  
   
    
  </tr>
  
   
  
  

  <tr>
   
		 <td Class="addedit">Amount</td>
    <td><input name="Amount" size="18" maxlength=8 value="0" onKeyUp="return Amt_val();"></td>
    
    <td Class="addedit">From Date</td>
    <td>
<%--     <INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
    <INPUT name=fromdate size=10 id="datepicker" value="<%=dateString%>">
				
		 </td>

    <td Class="addedit">To Date</td>
    <td>
<%-- 	<INPUT name=todate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=todate size=10 id="datepicker2" value="<%=dateString%>">
				
		 </td>
		 
		
  </tr>
  
  
  
  
  
  <tr>
  
  
      <td Class="addedit">Budget&nbsp;Head</td>
    <td  colspan="2"><input name="Bud_Head" size="30" maxlength=150></td>
    
	
	
	
	
    <td Class="addedit">Department Name</td>
     <td  colspan="2">
 	



 	<input name="dname" size="50" maxlength=256 style="width: 110px" readonly>
 		<input type="button" value="Find" name="BFind" Class="submitButton" onclick="FindValue('dcode')">
	
	
	
	</td>
	
	
	
	
	
  </tr>
  
  
  
  
  
  <tr><td colspan=6>
  
  
  <table align="center" width="85%">
 
  <tr>
  <td Class="addedit">Book</td><td><input name="book" size="10" maxlength=7 value="0" onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="bSpntAmt" size="10" value="0" maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="bBalAmt" size="10" value="0" maxlength=7 readonly></td>
  </tr>
  
  <tr>
  <td Class="addedit">BookBank</td><td><input name="bookbank" size="10" value="0"  maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="bbSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="bbBalAmt" size="10" maxlength=7  value="0" readonly></td>
  </tr>
  <tr>
  <td Class="addedit">NonBook</td><td><input name="nonbook" size="10"  value="0"  maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="nbSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="nbBalAmt" size="10" maxlength=7 value="0"  readonly></td>
  </tr>
  
  <tr>
  <td Class="addedit">Report</td><td><input name="report" size="10"  value="0"  maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="rSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="rBalAmt" size="10" maxlength=7 value="0"  readonly></td>
  </tr>
  
  
  <tr>
  <td Class="addedit">Thesis</td><td><input name="thesis" size="10" maxlength=7 value="0"  onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="tSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="tBalAmt" size="10" maxlength=7  value="0" readonly></td>
  </tr>
  <tr>
  <td Class="addedit">Standard</td><td><input name="standard" size="10"  value="0" maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="sSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="sBalAmt" size="10" value="0"  maxlength=7 readonly></td>
  </tr>
  <tr>
  <td Class="addedit">Proceeding</td><td><input name="proceeding" size="10"  value="0" maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="pSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="pBalAmt" size="10"  value="0" maxlength=7 readonly></td>
  </tr>
  <tr>
  <td Class="addedit">Journal</td><td><input name="journal" size="10" maxlength=7 value="0"  onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="jSpntAmt" size="10" value="0"  maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="jBalAmt" size="10" maxlength=7  value="0" readonly></td>
  </tr>
  <tr>
  <td Class="addedit">Misc</td><td><input name="misc" size="10"  value="0" maxlength=7 onKeyUp="return Amt_val();"></td>
  <td Class="addedit">AmtSpent</td><td><input name="mSpntAmt" value="0"  size="10" maxlength=7 readonly></td>
  <td Class="addedit">BAmt</td><td><input name="mBalAmt" size="10" value="0"  maxlength=7 readonly></td>
  </tr>
  
  

  


<tr>



  <tr>
    <td Class="addedit">Remarks</td>
    <td  colspan="6" ><input name="Remarks" size="56" ></td>
  </tr>
  

  
  
  </table></td></tr>
  
  </table>
 

</table>

<p align="center">
	<input type="button" value="New"   name="New" Class="submitButton"   onclick="new_no()">
	<input type="button" value="Save"  name="Save" Class="submitButton"   onclick="SaveRec()">
	<input type="button" value="Delete"name="Delete" Class="submitButton" onclick="DelRec()">
	<input type="submit" name="search" Value="search" Class="submitButton" onclick="SearchRec()">
  	<input type="Reset"  value="Clear" Class="submitButton" name="Clear" onclick="new_no()">

	<input type=hidden   name=flag  value="search">
</p>
</form>
</body>
</html>   
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newBudget")){
 %>
 <script language="JavaScript">
document.Budget.Bud_Code.value="<%=bean.getBudCode()%>";
document.Budget.Bud_Head.focus();
</script><%
}

if(valid.equals("searchBudget")){
 %>
 <script language="JavaScript">

 document.Budget.Bud_Code.value="<%=bean.getBudCode()%>";
 document.Budget.Bud_Head.value="<%=bean.getBudHead()%>";
 document.Budget.dname.value="<%=bean.getDeptname()%>";
 document.Budget.Amount.value="<%=bean.getBudAmount()%>";
 document.Budget.Aspent.value="<%=bean.getBudSAmount()%>";
 document.Budget.fromdate.value="<%=bean.getFrom()%>";
 document.Budget.todate.value="<%=bean.getTo()%>";
 document.Budget.Remarks.value="<%=bean.getRemarks()%>";
 document.Budget.book.value="<%=bean.getbAmount()%>";
 document.Budget.bSpntAmt.value="<%=bean.getbSAmount()%>";
 document.Budget.bookbank.value="<%=bean.getBbAmount()%>";
 document.Budget.bbSpntAmt.value="<%=bean.getBbSAmount()%>";
 document.Budget.nonbook.value="<%=bean.getNbAmount()%>";
 document.Budget.nbSpntAmt.value="<%=bean.getNbSAmount()%>";
 document.Budget.report.value="<%=bean.getrAmount()%>";
 document.Budget.rSpntAmt.value="<%=bean.getrSAmount()%>";
 document.Budget.thesis.value="<%=bean.gettAmount()%>";
 document.Budget.tSpntAmt.value="<%=bean.gettSAmount()%>";
 document.Budget.standard.value="<%=bean.getsAmount()%>";
 document.Budget.sSpntAmt.value="<%=bean.getsSAmount()%>";
 document.Budget.proceeding.value="<%=bean.getpAmount()%>";
 document.Budget.pSpntAmt.value="<%=bean.getpSAmount()%>";
 document.Budget.journal.value="<%=bean.getjAmount()%>";
 document.Budget.jSpntAmt.value="<%=bean.getjSAmount()%>";
 document.Budget.misc.value="<%=bean.getmAmount()%>";
 document.Budget.mSpntAmt.value="<%=bean.getmSAmount()%>";
 b=document.Budget.bSpntAmt.value;
 bb=document.Budget.bbSpntAmt.value;
 nb=document.Budget.nbSpntAmt.value;
 r=document.Budget.rSpntAmt.value;
 t=document.Budget.tSpntAmt.value;
 s=document.Budget.sSpntAmt.value;
 p=document.Budget.pSpntAmt.value;
 j=document.Budget.jSpntAmt.value;
 m=document.Budget.mSpntAmt.value;
document.Budget.BalAmount.value = document.Budget.Amount.value-document.Budget.Aspent.value;
document.Budget.bBalAmt.value = document.Budget.book.value-document.Budget.bSpntAmt.value;
document.Budget.bbBalAmt.value = document.Budget.bookbank.value-document.Budget.bbSpntAmt.value;
document.Budget.nbBalAmt.value = document.Budget.nonbook.value-document.Budget.nbSpntAmt.value;
document.Budget.rBalAmt.value = document.Budget.report.value-document.Budget.rSpntAmt.value;
document.Budget.tBalAmt.value = document.Budget.thesis.value-document.Budget.tSpntAmt.value;
document.Budget.sBalAmt.value = document.Budget.standard.value-document.Budget.sSpntAmt.value;
document.Budget.pBalAmt.value = document.Budget.proceeding.value-document.Budget.pSpntAmt.value;
document.Budget.jBalAmt.value = document.Budget.journal.value-document.Budget.jSpntAmt.value;
document.Budget.mBalAmt.value = document.Budget.misc.value-document.Budget.mSpntAmt.value;
document.Budget.Bud_Code.focus();



</script>
<%
}
if(valid.equals("FailBudget")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Budget.flag.value="new";
			document.Budget.submit();
		   	</script><%
			}
	if(valid.equals("UpdateBudget")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Budget.flag.value="new";
			document.Budget.submit();
		   	</script><%
			}
if(valid.equals("CodeCompareBudget")){
String  Autcode=(String)request.getAttribute("Budget_Mas_Val_code");
%>
            <script>
			alert("Budget Name Already Exists in Code:"+"<%=Autcode%>");
	    	document.Budget.flag.value="new";
			document.Budget.submit();
			</script><%
			}
if(valid.equals("SaveBudget")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Budget.flag.value="new";
			 document.Budget.submit();
		     </script>		
			<%
			}
if(valid.equals("DefaultBudget")){
	%>             <script language="JavaScript">
				 alert("Default Budget cannot be Deleted!");
				 document.Budget.flag.value="new";
				 document.Budget.submit();
			     </script>		
				<%
				}

if(valid.equals("ReferredBudget")){
%>            <script>alert("You can't delete since the Budget has been referred in other masters");
			 document.Budget.flag.value="new";
			 document.Budget.submit();
			</script>	
			<%
			}	
			
			if(valid.equals("DeleteBudget")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Budget.flag.value="new";
			document.Budget.submit();
		   </script>		
			<%
			}		
			

			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">

            document.Budget.Bud_Code.value="<%=bean.getBudCode()%>";
            document.Budget.Bud_Head.value="<%=bean.getBudHead()%>";
            document.Budget.dname.value="<%=bean.getDeptCode()%>";
            document.Budget.Amount.value="<%=bean.getBudAmount()%>";
            document.Budget.Aspent.value="<%=bean.getBudSAmount()%>";
            document.Budget.fromdate.value="<%=bean.getFrom()%>";
            document.Budget.todate.value="<%=bean.getTo()%>";
            document.Budget.Remarks.value="<%=bean.getRemarks()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Budget.flag.value="Confirmdete";
		   	document.Budget.submit();
			
			}
			</script>
			<%
			}
			
			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">

            document.Budget.Bud_Code.value="<%=bean.getBudCode()%>";
            document.Budget.Bud_Head.value="<%=bean.getBudHead()%>";
            document.Budget.dname.value="<%=bean.getDeptCode()%>";
            document.Budget.Amount.value="<%=bean.getBudAmount()%>";
            document.Budget.Aspent.value="<%=bean.getBudSAmount()%>";
            document.Budget.fromdate.value="<%=bean.getFrom()%>";
            document.Budget.todate.value="<%=bean.getTo()%>";
            document.Budget.Remarks.value="<%=bean.getRemarks()%>";
			    alert("Record Not Present!!!");
			</script>
			<%
			}
		
			
if(valid.equals("UpdateCheck")){ 
%> 
                <script language="JavaScript">
                document.Budget.Bud_Code.value="<%=bean.getBudCode()%>";
                document.Budget.Bud_Head.value="<%=bean.getBudHead()%>";
                document.Budget.dname.value="<%=bean.getDeptname()%>";
                document.Budget.Amount.value="<%=bean.getBudAmount()%>";
                document.Budget.Aspent.value="<%=bean.getBudSAmount()%>";
                document.Budget.fromdate.value="<%=bean.getFrom()%>";
                document.Budget.todate.value="<%=bean.getTo()%>";
                document.Budget.Remarks.value="<%=bean.getRemarks()%>";
                document.Budget.book.value="<%=bean.getbAmount()%>";
                document.Budget.bSpntAmt.value="<%=bean.getbSAmount()%>";
                 document.Budget.bookbank.value="<%=bean.getBbAmount()%>";
                 document.Budget.bbSpntAmt.value="<%=bean.getBbSAmount()%>";
                 document.Budget.nonbook.value="<%=bean.getNbAmount()%>";
                 document.Budget.nbSpntAmt.value="<%=bean.getNbSAmount()%>";
                 document.Budget.report.value="<%=bean.getrAmount()%>";
                 document.Budget.rSpntAmt.value="<%=bean.getrSAmount()%>";
                 document.Budget.thesis.value="<%=bean.gettAmount()%>";
                 document.Budget.tSpntAmt.value="<%=bean.gettSAmount()%>";
                 document.Budget.standard.value="<%=bean.getsAmount()%>";
                 document.Budget.sSpntAmt.value="<%=bean.getsSAmount()%>";
                 document.Budget.proceeding.value="<%=bean.getpAmount()%>";
                 document.Budget.pSpntAmt.value="<%=bean.getpSAmount()%>";
                 document.Budget.journal.value="<%=bean.getjAmount()%>";
                 document.Budget.jSpntAmt.value="<%=bean.getjSAmount()%>";
                 document.Budget.misc.value="<%=bean.getmAmount()%>";
                 document.Budget.mSpntAmt.value="<%=bean.getmSAmount()%>";

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Budget.flag.value="update";
		         	document.Budget.submit();
				    
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
var b;
var bb;
var nb;
var r;
var t;
var s;
var p;
var j;
var m;
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.Budget.method="get";
document.Budget.flag.value="new";
document.Budget.submit();
}

function SearchRec(){
document.Budget.method="get";
var no=document.Budget.Bud_Code.value;
if(no==""){
				document.Budget.Bud_Code.focus();
				alert("Insufficent Data");
				document.Budget.flag.value="new";
				document.Budget.submit();
		  }else{
		       document.Budget.flag.value="search";
			   document.Budget.submit();
			  
		}
	
}


function SaveRec(){
             document.Budget.method="get";
			   if(chk()){
			   
			  
			       	document.Budget.flag.value="save";
		         	document.Budget.submit();
		      
					
			}		
	else
	{
	alert("Insufficent Data");
	}
	
}




function chk(){
var flag_s;
var i;
var sp=document.Budget.Bud_Head.value;
var sp1=document.Budget.dname.value;

//shek

if(sp=="" || sp1=='')
{
document.Budget.Bud_Head.focus();
				document.Budget.flag.value="none";
				//document.Budget.Bud_Head.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Budget.Bud_Head.value.length;i++)
 	                      {
 	                       if(document.Budget.Bud_Head.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Budget.Bud_Head.focus();
		                   	document.Budget.Bud_Head.value="";
			                return false;
		                      }
		                   else if (document.Budget.Bud_Code.value==""){
		                 	document.Budget.Bud_Code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function Budget_code_val() {
if((isNaN(document.Budget.Bud_Code.value))||(document.Budget.Bud_Code.value == ' ')) {
document.Budget.Bud_Code.select();
document.Budget.Bud_Code.value="";
    
  }
}

function DelRec(){
document.Budget.method="get";
if (document.Budget.Bud_Code.value==""){
				document.Budget.Bud_Code.focus();
				alert("Insufficent Data");
				document.Budget.flag.value="new";
				document.Budget.submit();
				}
				else{
				  document.Budget.flag.value="delete";
				  document.Budget.submit();
				 }                         
				
}



//shek

function Amt_val() {
	if((isNaN(document.Budget.Amount.value))||(document.Budget.Amount.value == ' ')) {
	document.Budget.Amount.select();
	document.Budget.Amount.value="";
	  }
	

	
	if((isNaN(document.Budget.book.value))||(document.Budget.book.value == ' ')) {
		document.Budget.book.select();
		document.Budget.book.value="";
		  }
	

	
	if((isNaN(document.Budget.bookbank.value))||(document.Budget.bookbank.value == ' ')) {
		document.Budget.bookbank.select();
		document.Budget.bookbank.value="";
		  }
	
	
	
	if((isNaN(document.Budget.book.value))||(document.Budget.book.value == ' ')) {
		document.Budget.book.select();
		document.Budget.book.value="";
		  }
	
	
	
	if((isNaN(document.Budget.nonbook.value))||(document.Budget.nonbook.value == ' ')) {
		document.Budget.nonbook.select();
		document.Budget.nonbook.value="";
		  }
	
	
	if((isNaN(document.Budget.report.value))||(document.Budget.report.value == ' ')) {
		document.Budget.report.select();
		document.Budget.report.value="";
		  }
	
	
	
	
	if((isNaN(document.Budget.thesis.value))||(document.Budget.thesis.value == ' ')) {
		document.Budget.thesis.select();
		document.Budget.thesis.value="";
		  }
	
	
	
	
	if((isNaN(document.Budget.standard.value))||(document.Budget.standard.value == ' ')) {
		document.Budget.standard.select();
		document.Budget.standard.value="";
		  }
	
	
	
	
	if((isNaN(document.Budget.proceeding.value))||(document.Budget.proceeding.value == ' ')) {
		document.Budget.proceeding.select();
		document.Budget.proceeding.value="";
		  }
	
	
	
	
	if((isNaN(document.Budget.journal.value))||(document.Budget.journal.value == ' ')) {
		document.Budget.journal.select();
		document.Budget.journal.value="";
		  }
	
	
	
	
	if((isNaN(document.Budget.misc.value))||(document.Budget.misc.value == ' ')) {
		document.Budget.misc.select();
		document.Budget.misc.value="";
		  }
	
	
	
	document.Budget.BalAmount.value = document.Budget.Amount.value-document.Budget.Aspent.value;  
	
 	document.Budget.bBalAmt.value = document.Budget.book.value-document.Budget.bSpntAmt.value;
	
 	document.Budget.bbBalAmt.value = document.Budget.bookbank.value-document.Budget.bbSpntAmt.value;
	
 	document.Budget.nbBalAmt.value = document.Budget.nonbook.value-document.Budget.nbSpntAmt.value;
 	
 	document.Budget.rBalAmt.value = document.Budget.report.value-document.Budget.rSpntAmt.value;
	
	document.Budget.tBalAmt.value = document.Budget.thesis.value-document.Budget.tSpntAmt.value;
	
	document.Budget.sBalAmt.value = document.Budget.standard.value-document.Budget.sSpntAmt.value;
	
	document.Budget.pBalAmt.value = document.Budget.proceeding.value-document.Budget.pSpntAmt.value;
	
	document.Budget.jBalAmt.value = document.Budget.journal.value-document.Budget.jSpntAmt.value;
	
	document.Budget.mBalAmt.value = document.Budget.misc.value-document.Budget.mSpntAmt.value;
	
	
	
	
	  
	
	}







function Amt_Spt_val() {
if((isNaN(document.Budget.Aspent.value))||(document.Budget.Aspent.value == ' ')) {
document.Budget.Aspent.select();
document.Budget.Aspent.value="";
  }
document.Budget.BalAmount.value = document.Budget.Amount.value-document.Budget.Aspent.value;  
}			
		

</script>

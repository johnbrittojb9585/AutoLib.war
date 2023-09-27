<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Jnl_Approval.Jnlapprovebean"/>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Jnl_Approve
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>




<!-- Style Sheet -->
<form name="Jnl_Approve" method="POST" action="/AutoLib/Jnl_Approval/JnlApprove"><!-- JnlApproveServlet-->
<br><br><br>
<center>
<img border='0' src='/AutoLib/images/home.gif' onclick='home()'>
<img border='0' src='/AutoLib/images/Logout.gif' onclick='Logout()'>
</center>
<h2>Journal&nbsp;Approval</h2>
    <div align="center">
    <center>
    <table>

      <tr>
        <td>Journal</td>
        <td colspan="2"><input type="text" name="journal"  size="50">
		<input type="button" value="Find" name="Find_Journal"  onclick="FindValue('Journal')"></font></td>
      </tr>
	  <tr>
        <td>
          <p align="left">Department</td>
        <td colspan="2"><input type="text" name="dept"  size="50">
		<input type="button" value="Find" name="Find_Dept"   onclick="FindValue('Dept')"></font></td>
      </tr>

      <tr>
        <td>
          <p align="left">Date&nbsp;</p>
        </td>
        <td>
          <p align="left">From
		  <INPUT name=Date_From size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Jnl_Approve.Date_From, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
	 </td>
        <td>
          To
		  <INPUT name=Date_To size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Jnl_Approve.Date_To, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>

        </td>
      </tr>
            <tr>
        <td>Status</td>
        <td colspan="2"><select size="1" name="Status">
    <option selected>PENDING</option>
    <option>APPROVAL</option>
    <option>APPROVED</option>
    <option>CANCELLED</option>
    </select></td>
	 </tr>
	 <tr>
        <td>Amount</td>
        <td colspan="2"><input type="text" name="Amount" size="50" onKeyUp="return valid()"></td>
	 </tr>


      <tr>
        <td colspan="3"><center>
   <input type="button" value="Save" name="Save" onclick="saveRec()">
    <input type="button" value="Delete" name="Delete">
     <input type="button" value="Report" name="Report" onclick="report('val')">
     <input type="reset" value="Clear" name="Clear">
     <!--<input type="button" value="Close" name="Close">-->
    <input type="hidden" name="flag"></center>

   </td>
      </tr>
    </table>
    </center>
  </div>

</form>

</body>

</html>
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("UpdateCheck")){
 %>
 <script language="JavaScript">
		document..journal.value="<%=bean.getJname()%>";
                document.Jnl_Approve.dept.value="<%=bean.getJdname()%>";
                document.Jnl_Approve.Date_From.value="<%=bean.getJfrom()%>";
		document.Jnl_Approve.Date_To.value="<%=bean.getJto()%>";
		document.Jnl_Approve.Status.value="<%=bean.getJstatus()%>";
		document.Jnl_Approve.Amount.value="<%=bean.getJamount()%>";

                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				document.Jnl_Approve.flag.value="update";
		         	document.Jnl_Approve.submit();
		   }
			    </script>
<%
}
if(valid.equals("SaveJournal")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 history.back();
			 new_no();
		</script>
			<%
			}
			if(valid.equals("UpdateJournal")){
%>             <script language="JavaScript">
			 alert("Record Modified Successfully!");
			 history.back();
			 new_no();
		</script>
			<%
			}

}
}
%>
<script language="JavaScript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function valid()
{
if((isNaN(document.Jnl_Approve.Amount.value)) || ((document.Jnl_Approve.Amount.value)==""))
{
document.Jnl_Approve.Amount.value="";
document.Jnl_Approve.Amount.focus();
}
}

function report(karthi)
{
winpopup=window.open("app_report.jsp?flag="+karthi,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}

function new_no(){
document.Jnl_Approve.reset();
document.Jnl_Approve.journal.focus();
}
function saveRec(){
document.Jnl_Approve.method="post";
		if(chk()){
			document.Jnl_Approve.flag.value="save";
		        document.Jnl_Approve.submit();
			 }

	else
	{
	alert("Insufficient Data");
	}

}

function chk(){
var flag_s;
var i;
var sp=document.Jnl_Approve.journal.value;
				if(sp=="")
				{
				document.Jnl_Approve.journal.focus();
				document.Jnl_Approve.flag.value="none";
				document.Jnl_Approve.journal.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Jnl_Approve.journal.value.length;i++)
 	                      {
 	                       if(document.Jnl_Approve.journal.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.Jnl_Approve.journal.focus();
		                   	document.Jnl_Approve.journal.value="";
			                return false;
		                      }

        else{
		return true;
		}
     }
 }


</script>

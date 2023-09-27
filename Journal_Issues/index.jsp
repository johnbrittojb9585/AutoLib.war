<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"
		
		import="java.text.Format"
		import="java.util.*" import="Common.Security"%>
		
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="JnlBean" scope="request" class="Lib.Auto.Journal_Issues.JnlIssBean" />
<%
	java.util.Date d =new java.util.Date();
	Calendar date = Calendar.getInstance();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	Format f = new SimpleDateFormat("dd-MM-yyyy");
	date.setTime(d);
	date.add(Calendar.DATE,365);
	  
	
	    String dateString = sdf.format(d);
	    String dateString1 = f.format(date.getTime());
	
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchJnlIssues.js"></script>

</head>
<body>

<form name="JnlIssues" method="POST" action=./JnlIssServlet>
<br>

<h2>JOURNAL&nbsp;ISSUES</h2>
<p align="center">
<table align="center" class="contentTable" width="60%">
<tr>
<td>
<table align="center" width="100%" cellspacing="0" cellpadding="5">

    <tr>
    <td>
    <table align="center">
    <tr>
<div class="search-container">
	<div class="ui-widget">
    
     <td Class="addedit">Journal&nbsp;Name</td>
<!--       <td> <input type="text" name="jname"  size="57" readonly> -->
      <td> <input type="text" name="jname" id="searchJournalName" class="searchJournal" onkeyup="showJournalName(this.value);" size="57">
      
    </div>
</div>      
      
      
	  <input type="button" value="Find" name="Find_Value" Class="submitButton" onclick="FindValue('JnlName')"></td>
    </tr>
    </table></td></tr>
  </table>
  <table align="center">
  
  <tr>
  <td Class="addedit">From&nbsp;Date</td>
<%--         <td Class="addedit"><INPUT name=subsFrom size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
        <td Class="addedit"><INPUT name=subsFrom size=10 id="datepicker" value="<%=dateString%>">
				
		 </td>
        <td Class="addedit">To&nbsp;date</td>
        <TD>
<%-- 	<INPUT name=subsTo size=10  onfocus=this.blur(); value="<%=dateString1%>"> --%>
	<INPUT name=subsTo size=10 id="datepicker2" value="<%=dateString1%>">
				
		 </td>
		 <td Class="addedit"><input type="radio" name="check" id="available" value="avail" onclick="changeRadio()">Avail.</td>
		  <td Class="addedit"><input type="radio" name="check" id="pending" value="pending" checked onclick="changeRadio()">Pending<br></td>
		   <td Class="addedit"><input type="radio" name="check" id="all" value="all" onclick="changeRadio()">All<br></td>
		 
  </tr></table>
  <hr>
       <table align="center">
       
      <tr>
        <td Class="addedit"> Issue&nbsp;AccNo.</td>
        <td><input type="text" name="iaccno" size="15" maxlength=15 onKeyUp="return access_no_val();"></td>
        <td Class="addedit">Issue&nbsp;Volume</td>
        <td><input type="text" name="ivol" size="15" maxlength=20></td>
      </tr>
      <tr>
        <td Class="addedit">Issue&nbsp;Month</td>
        <td><input type="text" name="imonth" size="15" maxlength=20></td>
        <td Class="addedit">Issue&nbsp;Year</td>
        <td ><input type="text" name="iyear" size="15" onKeyUp="return access_no_val();" maxlength=4></td>
      </tr>
      <tr>
        <td Class="addedit">Issue&nbsp;No</td>
        <td><input type="text" name="ino" maxlength=15 size="15" onKeyUp="return access_no_val();"></td>
        <td Class="addedit">Availability</td>
        <td><select size="1" name="avail" style="width: 110px">
            <option selected value="YES">YES</option>
            <option value="PENDING">PENDING</option>
          </select></td>
      </tr>
      <tr>
        <td Class="addedit">Issue&nbsp;Date</td>
        <TD>
<%-- 	<INPUT name=issuedate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
	<INPUT name=issuedate size=10 id="datepicker3" onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				
		 </td>
        <td Class="addedit">Received&nbsp;date</td>
        <TD>
<%-- 	<INPUT name=receivedate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
	<INPUT name=receivedate size=10 id="datepicker4" onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				
		 </td>
      </tr>

      <tr>
      <td Class="addedit">Content&nbsp;Page</td>
      <td><input type=text name=Content  maxlength=200 size="15" ></td>
        <td Class="addedit">Remarks</td>
        <td><input type="text" name="iremarks" size="15" maxlength=150></td>
    </tr></table></td></tr>
    <tr><td>&nbsp;</td></tr>
    </table>
    
     <p align="center">
     <input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		  <input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		  <input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
		  <input type="submit" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		  <input type="Reset" value="Clear" Class="submitButton" onclick="rsetpage()">
		  <input type="hidden" name="flag">
		  <input type="hidden" name="flagRadio">
		  <input type="hidden" name="jcode">
		  
     </p>
    
    
    </form></body></html>

  <%
	String name="";
	String details="";
	ArrayList sc=new ArrayList();
	String check=request.getParameter("check");
	 details=request.getParameter("details");
	if(check!=null){
	if(check.equals("JnlIssNew")){
  name=request.getParameter("jnlname");
	%>
	<script language="JavaScript">

	document.JnlIssues.jname.value="<%=name%>";
	document.JnlIssues.iaccno.value="<%=JnlBean.getIss_acc_no()%>";
	document.JnlIssues.ivol.value="";
	document.JnlIssues.imonth.value="";
	document.JnlIssues.iyear.value="";
	document.JnlIssues.ino.value="";
	document.JnlIssues.avail.value="YES";
	document.JnlIssues.issuedate.value="<%=Security.TodayDate()%>";
	document.JnlIssues.receivedate.value="<%=Security.TodayDate()%>";
	document.JnlIssues.Content.value="";
	document.JnlIssues.iremarks.value="";
	document.JnlIssues.jcode.value="<%=JnlBean.getJnl_code()%>";
	</script>
	<%
	}
	if(check.equals("searchJnl")){
		
		if(JnlBean.getFlag().equals("YES")){
			 %>
			 <script language="JavaScript">
				document.getElementById("available").checked = true;
			 </script>
	 
	<%
	}else if(JnlBean.getFlag().equals("PENDING")){
		 %>
		 <script language="JavaScript">
			document.getElementById("pending").checked = true;
		 </script>
 
<%
		
	}else{
		 %>
		 <script language="JavaScript">
			document.getElementById("all").checked = true;
		 </script>
 
<%
		
	}
	
	%>
		
		
 
 
  <script language="JavaScript">
  
  
  
  
  
document.JnlIssues.jname.value="<%=JnlBean.getJnl_name()%>";
document.JnlIssues.subsFrom.value="<%=Security.getdate(JnlBean.getFromDt())%>";
document.JnlIssues.subsTo.value="<%=Security.getdate(JnlBean.getToDt())%>";
document.JnlIssues.issuedate.value="<%=Security.getdate(JnlBean.getFromDt())%>";
document.JnlIssues.receivedate.value="<%=Security.getdate(JnlBean.getToDt())%>";


</script>
<%
}

	if(check.equals("supdate")){
		
		 %>
		  <script language="JavaScript">
<%-- 		document.JnlIssues.jcode.value="<%=JnlBean.getJnl_code()%>"; --%>
		document.JnlIssues.jname.value="<%=JnlBean.getJnl_name()%>";
		document.JnlIssues.subsFrom.value="<%=Security.getdate(JnlBean.getFromDt())%>";
		document.JnlIssues.subsTo.value="<%=Security.getdate(JnlBean.getToDt())%>";
		

		</script>
		<%
	}	
	
	
	
if(check.equals("searchJnlIss")){
 %>
  <script language="JavaScript">
document.JnlIssues.jname.value="<%=JnlBean.getJnl_name()%>";
document.JnlIssues.iaccno.value="<%=JnlBean.getIss_acc_no()%>";
document.JnlIssues.ivol.value="<%=JnlBean.getIss_vol()%>";
document.JnlIssues.imonth.value="<%=JnlBean.getIss_month()%>";
document.JnlIssues.iyear.value="<%=JnlBean.getIss_year()%>";
document.JnlIssues.ino.value="<%=JnlBean.getIss_no()%>";
document.JnlIssues.avail.value="<%=JnlBean.getAvail()%>";
document.JnlIssues.issuedate.value="<%=Security.getdate(JnlBean.getIss_date())%>";
document.JnlIssues.receivedate.value="<%=Security.getdate(JnlBean.getRec_date())%>";
document.JnlIssues.Content.value="<%=JnlBean.getPage()%>";
document.JnlIssues.iremarks.value="<%=JnlBean.getRemarks()%>";

</script>
<%
}
if(check.equals("FailJnl")){
name=request.getParameter("jnlname");
%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.JnlIssues.jname.value="<%=name%>";
		   	</script><%
			}

			if(check.equals("DeleteJnl")){
			  name=request.getParameter("jnlname");

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.JnlIssues.jname.value="<%=name%>";
		   </script>
			<%
			}

			if(check.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		    document.JnlIssues.jname.value="<%=JnlBean.getJnl_name()%>";
			document.JnlIssues.iaccno.value="<%=JnlBean.getIss_acc_no()%>";
			document.JnlIssues.ivol.value="<%=JnlBean.getIss_vol()%>";
			document.JnlIssues.imonth.value="<%=JnlBean.getIss_month()%>";
			document.JnlIssues.iyear.value="<%=JnlBean.getIss_year()%>";
			document.JnlIssues.ino.value="<%=JnlBean.getIss_no()%>";
			document.JnlIssues.avail.value="<%=JnlBean.getAvail()%>";
			document.JnlIssues.issuedate.value="<%=Security.getdate(JnlBean.getIss_date())%>";
			document.JnlIssues.receivedate.value="<%=Security.getdate(JnlBean.getRec_date())%>";
			document.JnlIssues.Content.value="<%=JnlBean.getPage()%>";
			document.JnlIssues.iremarks.value="<%=JnlBean.getRemarks()%>";

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.JnlIssues.flag.value="update";
		         	document.JnlIssues.submit();

		            }
			    </script>

			<%
			}


			if(check.equals("deleteCheck")){

%>
            <script language="JavaScript">
			document.JnlIssues.jname.value="<%=JnlBean.getJnl_name()%>";
			document.JnlIssues.iaccno.value="<%=JnlBean.getIss_acc_no()%>";
			document.JnlIssues.ivol.value="<%=JnlBean.getIss_vol()%>";
			document.JnlIssues.imonth.value="<%=JnlBean.getIss_month()%>";
			document.JnlIssues.iyear.value="<%=JnlBean.getIss_year()%>";
			document.JnlIssues.ino.value="<%=JnlBean.getIss_no()%>";
			document.JnlIssues.avail.value="<%=JnlBean.getAvail()%>";
			document.JnlIssues.issuedate.value="<%=Security.getdate(JnlBean.getIss_date())%>";
			document.JnlIssues.receivedate.value="<%=Security.getdate(JnlBean.getRec_date())%>";
			document.JnlIssues.Content.value="<%=JnlBean.getPage()%>";
			document.JnlIssues.iremarks.value="<%=JnlBean.getRemarks()%>";
			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.JnlIssues.flag.value="Confirmdete";
		   	document.JnlIssues.submit();

			}
			</script>
			<%
			}

			if(check.equals("SaveJnl")){
			 name=request.getParameter("jnlname");
%>             <script language="JavaScript">
			document.JnlIssues.jname.value="<%=name%>";
			 alert("Record Inserted Successfully!");

	document.JnlIssues.iaccno.value="<%=JnlBean.getIss_acc_no()%>";
	document.JnlIssues.ivol.value="";
	document.JnlIssues.imonth.value="";
	document.JnlIssues.iyear.value="";
	document.JnlIssues.ino.value="";
	document.JnlIssues.avail.value="YES";
	document.JnlIssues.issuedate.value="<%=Security.TodayDate()%>";
	document.JnlIssues.receivedate.value="<%=Security.TodayDate()%>";
	document.JnlIssues.Content.value="";
	document.JnlIssues.iremarks.value="";
	
			// document.JnlIssues.flag.value="new";
			 //document.JnlIssues.submit();
		     </script>
			<%
			}
			if(check.equals("UpdateJnl")){
			name=request.getParameter("jnlname");
			%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.JnlIssues.jname.value="<%=name%>";
			document.JnlIssues.flag.value="supdate";
			document.JnlIssues.submit();
		   	</script><%
			}

	}

	if(details!=null){
		%>
		 <script language=javascript>

		</script>
		<%
if(details.equals("Issues")){
/*---------------------------------------ISSUE DETAILS----------------------------------------------------------------------------------------------------------------------------------------------------------*/
                 	out.print("<b><center>Issue Details</center></b>");
                    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
                    out.print("<tr><th>Issue Access No.<th>Issue No.<th>Issue Volume<th>Issue Month<th>Issue Year<th>Issue Date<th>Received Date<th>Availability</tr>");

		sc=(ArrayList)JnlBean.getAl();

		if(sc!=null) {

		if(sc.size()<=0)
		{

		}
		else
		{

		}
		for(int i=0; i<sc.size();i+=10){

		%>
		 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>","<%=sc.get(i+1)%>","<%=sc.get(i+2)%>","<%=sc.get(i+3)%>","<%=sc.get(i+4)%>","<%=Security.getdate(sc.get(i+5).toString())%>","<%=Security.getdate(sc.get(i+6).toString())%>","<%=sc.get(i+7)%>","<%=sc.get(i+8)%>","<%=sc.get(i+9)%>")'>
		 <script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(i+4)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(i+3)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(i+2)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(i+1)%>" +"</td>");
		 document.write("<td>"+"<%=Security.getdate(sc.get(i+5).toString())%>"+"</td>");
		 document.write("<td>"+"<%=Security.getdate(sc.get(i+6).toString())%>"+"</td>");
		 document.write("<td>"+"<%=sc.get(i+7)%>"+"</td>");
		 document.write("</tr>");
		</script>
		<%
			}
			out.print("</table><br>");
			sc.clear();
}%><script>
		document.JnlIssues.jcode.value="<%=JnlBean.getJnl_code()%>";</script><%
}
}


  %>
<script>

function rsetpage(){
document.JnlIssues.flag.value="clear";
document.JnlIssues.submit();
}

function changeRadio(){
//shek

if(document.JnlIssues.jname.value==''){
	alert("Please Select Journal");
}else{
	if (document.getElementById('available').checked) {
		  document.JnlIssues.flagRadio.value="YES";
		}else if(document.getElementById('pending').checked){
			document.JnlIssues.flagRadio.value="PENDING";
		}else if(document.getElementById('all').checked){
			document.JnlIssues.flagRadio.value="All";
		}else{
			document.JnlIssues.flagRadio.value="Unknown";
		}

	document.JnlIssues.flag.value="find";
	
	document.JnlIssues.submit();
}


}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

  function NewRecord()
	{
	document.JnlIssues.method="post";
	if(!(document.JnlIssues.jname.value==""))
	{
	document.JnlIssues.flag.value="new";
	document.JnlIssues.submit();
	}
	else alert("Select the Journal Name");
       }
       
       
  function AddRecord()
	{
	document.JnlIssues.Content.value=document.JnlIssues.Content1.value
	
     }

function SaveRecord()
	{

var str1="Issues_Date",str2="Recieved Date";
var issues_check,recieve_check;

	if(document.JnlIssues.jname.value=="" || document.JnlIssues.iaccno.value=="" || document.JnlIssues.ivol.value=="" || document.JnlIssues.iyear.value=="" || 	 document.JnlIssues.imonth.value==""  )
  	{
	alert("Insufficiant Data");
	}
else{

	document.JnlIssues.method="post";
	if(check()){
	document.JnlIssues.submit();
	}
	}
	}

function SearchRecord()
	{
	document.JnlIssues.method="post";

//	if(document.JnlIssues.jname.value=="" && document.JnlIssues.iaccno.value=="")
	if(document.JnlIssues.jname.value=="" || document.JnlIssues.iaccno.value=="")
	{
	
	alert("Insufficiant Data");
	document.JnlIssues.action="index.jsp";
	}
	/*else if(document.JnlIssues.jname.value=="" && !document.JnlIssues.iaccno.value=="")
	{
	  alert("Select valid Journal Name !");
	  document.JnlIssues.action="index.jsp";
	}*/
	else
	{
	document.JnlIssues.flag.value="search";
	}
	document.JnlIssues.submit();
	}

function DeleteRecord()
{
	document.JnlIssues.method="post";
	if(document.JnlIssues.iaccno.value=="")
	{
	alert("Insufficiant Data");
	document.JnlIssues.SFlag.value="null";
	document.JnlIssues.FFlag.value="find";
	}
	else
	{
	document.JnlIssues.flag.value="delete";
}

    document.JnlIssues.submit();
}


function show(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10)
	{

	document.JnlIssues.iaccno.value=v1;
	document.JnlIssues.iyear.value=v2;
 	document.JnlIssues.ivol.value=v4;
	document.JnlIssues.imonth.value=v3;
	document.JnlIssues.ino.value=v5;
//	document.JnlIssues.issuedate.value=v6;
//	document.JnlIssues.receivedate.value=v7;
	document.JnlIssues.avail.value=v8;
	document.JnlIssues.iremarks.value=v9;
 	document.JnlIssues.Content.value=v10;
	document.JnlIssues.iaccno.focus();
//SHEK

}

function FindValue(val)
  {
  winpopup=window.open("search.jsp?find_flag="+val,"popup","height=400,width=600,left=100,top=100,menubar=no,status=yes,scrollbars=yes,toolbar=no");
  }
  
  function check()
{
  if(confirm("Are You Sure To Continue Save?"))
   {
    document.JnlIssues.flag.value="save";
    }
    else
	{
	  document.JnlIssues.SFlag.value="cancel";
	  document.JnlIssues.FFlag.value="find";
	}
	return true;
}



function ClearFields()
{
document.JnlIssues.iaccno.value="";
document.JnlIssues.ivol.value="";
document.JnlIssues.imonth.value="";
document.JnlIssues.iyear.value="";
document.JnlIssues.ino.value="";
document.JnlIssues.avail.selectedIndex=0;
document.JnlIssues.iremarks.value="";
document.JnlIssues.Content.value="";


}
function access_no_val()
{
/**if((isNaN(document.JnlIssues.iaccno.value))||(document.JnlIssues.iaccno.value == ' ')) {
document.JnlIssues.iaccno.select();
document.JnlIssues.iaccno.value="";
}*/
if((isNaN(document.JnlIssues.iyear.value))||(document.JnlIssues.iyear.value == ' ')) {
document.JnlIssues.iyear.select();
document.JnlIssues.iyear.value="";
}
if((isNaN(document.JnlIssues.ino.value))||(document.JnlIssues.ino.value == ' ')) {
document.JnlIssues.ino.select();
document.JnlIssues.ino.value="";
}

}
  </script>




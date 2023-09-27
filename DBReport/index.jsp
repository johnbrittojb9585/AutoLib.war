<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Common.Security"%>

<%
  String check_date=request.getParameter("check");
  if(check_date!=null)
  {
  if(check_date.equals("date"))
  {
  %>
<script language="javascript">
  alert("From date is greater than or equal to To date!...");
  </script>
<%
  }
  check_date=null;
  }
  %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchDBReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

</head>
<body>

	<form name="sreport" method="get" action=./DBReportServlet>
		<br>
		<br>
		<br>
		<h2>DataBase Report</h2>
		
			<table align="center" class="contentTable" width="600">
			<tr>
				<td>
					<table align="center" width="90%">
						<tr><td>&nbsp;</td></tr>
						
						<tr>
						
				<div class="search-container">
                    <div class="ui-widget">
						
						<td Class="addedit">Department</td>
<!-- 						<td><input type="text" name="txtdepartment" readonly="true"	size="45">  -->
						<td><input type="text" name="txtdepartment" id="searchDept" class="searchDBReport" onkeyup="showDept(this.value);" size="45"> 
						
				     </div>
				</div>		
						<input type="button" Class="submitButton" value="Find" name="Find_Dept" onclick="FindValue('Dept')">
						</td>
						</tr>
							
							
						<tr>
				<div class="search-container">
                    <div class="ui-widget">
						
						<td Class="addedit">Subject</td>
<!-- 						<td><input type="text" name="txtsubject" readonly="true" size="45"> -->
						<td><input type="text" name="txtsubject" id="searchSubject" class="searchDBReport" onkeyup="showSubject(this.value);" size="45">
						
				    </div>
				</div>		
						
						 <input type="button" Class="submitButton" value="Find" name="Find_Sub" onclick="FindValue('Sub')"></td>
						</tr>
						
						<tr>
				<div class="search-container">
                    <div class="ui-widget">
						<td Class="addedit">Publisher</td>
<!-- 						<td><input type="text" name="txtpublisher" readonly="true" size="45"> -->
						<td><input type="text" name="txtpublisher" id="searchPublisher" class="searchDBReport" onkeyup="showPublisher(this.value);" size="45">
						
				    </div>
				</div>		
						<input type="button" Class="submitButton" value="Find" name="Find_Pub" onclick="FindValue('Pub')"></td>
						</tr>

						<tr>
				<div class="search-container">
                    <div class="ui-widget">
						
						<td Class="addedit">Supplier</td>
<!-- 						<td><input type="text" name="txtsupplier" readonly="true" size="45"> -->
						<td><input type="text" name="txtsupplier" id="searchSupplier" class="searchDBReport" onkeyup="showSupplier(this.value);" size="45">
						
				    </div>
				</div>		
						
	            		<input type="button" Class="submitButton" value="Find" name="Find_Sup" onclick="FindValue('Sup')"></td>
						</tr>

						<tr>
						<td Class="addedit"><input type="radio" value="V1" checked name="R1" onclick=change()>Received&nbsp;Date&nbsp;</td>
<%-- 						<td Class="addedit">From <INPUT name=recfrom size=10 onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
						<td Class="addedit">From <INPUT name=recfrom size=10 id="datepicker" value="<%=Security.CalenderDate()%>">
								
								 To 
<%-- 								    <INPUT name=recto size=10 onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
								    <INPUT name=recto size=10 id="datepicker2" value="<%=Security.CalenderDate()%>"> 
								 
							</td>
						</tr>
						<tr>
							<td Class="addedit"><input type="radio" value="V2" name="R1" onclick=changes()>Acs.&nbsp;Number</td>
							<td Class="addedit">From <input type="text" name="txtfromacc" size="15" maxlength="10"> To <input type="text" name="txttoacc" size="15" maxlength="10"></td>

							<td>&nbsp;</td>
						

						</tr>
						<tr>
							<td>&nbsp;</td>
							<td Class="addedit">Doc&nbsp;&nbsp;&nbsp;&nbsp;<select size="1" name="doctype">
									<OPTION VALUE="BOOK">BOOK</OPTION>
									<option value="BOOK BANK">BOOK BANK</option>
									<option value="NON BOOK">NON BOOK</option>
									<option value="REPORT">REPORTS</option>
									<OPTION VALUE="THESIS">THESIS</OPTION>
									<option value="STANDARD">STANDARD</option>
									<option value="PROCEEDING">PROCEEDING</option>
									<option value="BACK VOLUME">BACK VOLUME</option>
									<option value="ALL" selected>ALL</option>

							</select></td>
						</tr>

								<tr>
      
      <br/>
      <td></td>
      <td Class="addedit">Year_Pub&nbsp;<input type=text name="year_pub" size="10" maxlength=15 ></td>
      
      </tr>	
						
						
						<tr>
							<td>&nbsp;&nbsp;</td>
						
</tr></table></td></tr>


<tr>
<td>
<table align="center">



</tr>

<td align="right"><a href="javascript:void();" onclick="javascript:checkAll('sreport', true);"><font color="#0000FF"><b>All</b></font></a></td>
<td align="right"><a href="javascript:void();" onclick="javascript:checkAll('sreport', false);"><font color="#0000FF"><b>None</b></font></a></td>


<td><input type=checkbox name="rptValue[]" value="access_no">Acs.&nbsp;No</td>
<td><input type=checkbox name="rptValue[]"	value="author_name">Author</td>
<td><input type=checkbox name="rptValue[]" value="title">Title</td>
<td><input type=checkbox name="rptValue[]" value="call_no">Call&nbsp;No</td>
<td><input type=checkbox name="rptValue[]" value="edition">Edition</td>

<tr>

<tr>
<td><input type=checkbox name="rptValue[]" value="dept_name">Dept.</td>
<td><input type=checkbox name="rptValue[]" value="sub_name">Subject</td>
<td><input type=checkbox name="rptValue[]" value="publisher">Publisher</td>
<td><input type=checkbox name="rptValue[]" value="supplier">Supplier</td>
<td><input type=checkbox name="rptValue[]" value="isbn">ISBN</td>
<td><input type=checkbox name="rptValue[]" value="year_pub">YearPub</td>
<td><input type=checkbox name="rptValue[]" value="script">Month</td>

</tr>

<tr>
<td><input type=checkbox name="rptValue[]" value="language">Language</td>
<td><input type=checkbox name="rptValue[]" value="add_field3">Issue&nbsp;No</td>
<td><input type=checkbox name="rptValue[]" value="volno">BVolume&nbsp;No</td>
<td><input type=checkbox name="rptValue[]"	value="location">Location</td>
<td><input type=checkbox name="rptValue[]" value="availability">Avail.</td>
<td><input type=checkbox name="rptValue[]" value="gift_purchase">Pur.Type</td>
<td><input type=checkbox name="rptValue[]"	value="keywords">Keywords</td>

</tr>

<tr>
<td><input type=checkbox name="rptValue[]" value="received_date">RecdDate</td>
<td><input type=checkbox name="rptValue[]" value="invoice_no">InvoiceNo</td>
<td><input type=checkbox name="rptValue[]" value="invoice_date">InvDate</td>
<td><input type=checkbox name="rptValue[]" value="bprice">BPrice</td>
<td><input type=checkbox name="rptValue[]" value="discount">Discount</td>
<td><input type=checkbox name="rptValue[]" value="accepted_price">NetPrice</td>

</tr>


</table>

</td>

</tr>






</table>
<p align="center">
<a href="#" onclick="check_status()">
 <img src="<%=request.getContextPath()%>/images/xls.gif" width="35" height="45" border="0" title="Print Excel"></a>
 <input type="Reset" Class="submitButton" value="Clear" onclick="clear_text()">
  <input type="hidden" name="flag1"> <input type="hidden" name="flagNotNumber">
</p>
</form>

		<%
			String valid = request.getParameter("check");
			if (valid != null) {
				if (request.getParameter("check") != null) {
					if (valid.equals("RecordNot")) {
		%>
		<script language="JavaScript">
alert("Record Not Found");
history.back();
</script>
		<%
			}
					if (valid.equals("NotValidRange")) {
		%>
		<script>
	alert("Not A Valid Range");
	history.back();
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

function checkAll(formname, checktoggle)
{
     var checkboxes = new Array();
      checkboxes = document[formname].getElementsByTagName('input');

      for (var i = 0; i < checkboxes.length; i++) {
          if (checkboxes[i].type === 'checkbox') {
               checkboxes[i].checked = checktoggle;
          }
      }
}

function check_status()
{
	var content="";
    var chkitem=0;	
	var cboxes = document.getElementsByName('rptValue[]');	 
    var len = cboxes.length;	
     
    for(i=0;i<len;i++)  {
    	if(cboxes[i].checked == true)
        {
    		chkitem++;
            content=content+","+cboxes[i].value;
    		//alert("From "+cboxes[i].value);
        }
    }
    if(chkitem >= 1)
 	{	   
	   //alert(content);
       document.sreport.flag1.value=content;     
 	}
    else if(chkitem == 0)
    {
	  alert('Please select atleast one !');
	  return false;
    }
    	
	

if(check())
{

 var from=document.sreport.txtfromacc.value;
 var to=document.sreport.txttoacc.value;

    if(isNaN(from)&&isNaN(to)){	
	document.sreport.flagNotNumber.value="NotNumber";
	document.sreport.method="post";
	document.sreport.submit();
	}
	else if(isNaN(from)||isNaN(to))
	{
	alert("Not a valid No");
	document.sreport.hid.value="none";
	document.sreport.action="index.jsp";
	document.sreport.submit();
	}
	else{
	 document.sreport.flagNotNumber.value="Number";
	 document.sreport.method="post";
	 document.sreport.submit();
	}

document.sreport.method="get";
document.sreport.submit();

}
else
{
alert("Insufficient Data..!");
}
}

function check()
 {
if(document.sreport.R1[1].checked==true)
{
if(document.sreport.txtfromacc.value=="" || document.sreport.txttoacc.value=="")
	return false;
	else return true;

}
else
{
return true;
}
}

function change(){
document.sreport.R1[1].checked=false;
document.sreport.txtfromacc.disabled=true;
document.sreport.txttoacc.disabled=true;
document.sreport.recfrom.disabled=false;
document.sreport.recto.disabled=false;
document.sreport.txtfromacc.value="";
document.sreport.txttoacc.value="";

}
function changes(){
document.sreport.R1[0].checked=false;
document.sreport.recfrom.readonly=true;
document.sreport.recto.readonly=true;
document.sreport.txtfromacc.disabled=false;
document.sreport.txttoacc.disabled=false;

}
function clear_text()
{
document.sreport.txtdepartment.value="";
document.sreport.txtpublisher.value="";
document.sreport.txtsupplier.value="";
document.sreport.txtsubject.value="";
document.sreport.txtfromacc.value="";
document.sreport.txttoacc.value="";

}
function FindValue(val){
winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
</script>

	
</body>

</html>

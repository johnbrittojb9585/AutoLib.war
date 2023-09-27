<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" import="Common.Security_Counter"  session="true" buffer="12kb" import="java.sql.*,java.io.*,java.util.*,java.lang.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Counter.CounterBean" type="Lib.Auto.Counter.CounterBean">
</jsp:useBean>
<jsp:useBean id="beanmember" scope="request" class="Lib.Auto.Counter.CounterMemberBean" type="Lib.Auto.Counter.CounterMemberBean">
</jsp:useBean>
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.Counter.CounterFineBean" type="Lib.Auto.Counter.CounterFineBean">
</jsp:useBean>

<%
ArrayList sc=new ArrayList();
ArrayList rc=new ArrayList();
String flag="",Message="",load="",detils="",flags="",avoid="",DisplayDate="",DisplayDate1="";
%>

<script language = "Javascript">
window.history.forward(0)
</script>
<html>

<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<!--<meta http-equiv="refresh" content="value">-->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg"  >
<form name="counter" method="post" action=./BulkCounterServlet>

<h2><b>Bulk&nbsp;Counter&nbsp;Service</b></h2>
 <%
 flag=request.getParameter("flag");
 flags=request.getParameter("flags");
 Message=request.getParameter("Message");
 detils=request.getParameter("detils");
 load=request.getParameter("load");
 avoid=request.getParameter("avoid");
 boolean notbook=true;
 boolean iss=true;
 %>
 
 
 <table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
  
    <tr>
      <td Class="addedit">User&nbsp;Id</td>
      <td><input type="text" name="mcode" size="17"  maxlength="15" onFocus="memopt()" >
       <input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('Member')></td>
      <td bgcolor="#BCD6E2" bordercolor="#FFE6BC"><input type="radio" value="V1" checked name="sel_search"><b Class="addedit">User</b></td>
      

<c:set var="str" value="<%=bean.getPhoto1()%>"/>


<c:choose>

<c:when test="${str ne null}">

<td rowspan="4">

<img src="photo.jsp" height="100px" width="140px" align=left/></td>
 
</c:when>

<c:otherwise>

<td rowspan="4">

<img src="<%=request.getContextPath() %>/images/noimage.jpg" height="100px" width="140px" align=left/></td>

</c:otherwise>

</c:choose>

      <td  bgcolor="#BCD6E2" bordercolor="#FFE6BC"><b>Resource</b></td>
      <td  bgcolor="#BCD6E2" bordercolor="#FFE6BC"><b>No's</b></td>
      <td  bgcolor="#BCD6E2" bordercolor="#FFE6BC"><b>Card</b></td>
      <td  bgcolor="#BCD6E2" bordercolor="#FFE6BC"><b>Days</b></td>
    </tr>
    <tr>
      <td Class="addedit">Name</td>
      <td  colspan="2"><input type="text" readonly="true" name="mname" size="46"></td>      
      <td Class="addedit">General</td>
      <td  align="center"><input type="text"  readonly="true" name="geligible" size="1" value="0" ></td>
      <td  align="center"><input type="text"  readonly="true" name="gissued" size="1" value="0" ></td>
      <td  align="center"><font face="Verdana" size="2"><input type="text" readonly="true" name="gperiod" size="1" value="0" ></font></td>
    </tr>
    <tr>
      <td Class="addedit">Group</td>
      <td  colspan="2"><input type="text" readonly="true" name="group" size="46"></td>
      <td Class="addedit">Book</td>
      <td  align="center"><input type="text" readonly="true" name="beligible" size="1" value="0" ></td>
      <td  align="center"><input type="text" readonly="true" name="bissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="bperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Desig</td>
      <td colspan="2"><input type="text" readonly="true" name="desig" size="46"></td>
      <td Class="addedit">BookBank</td>
      <td  align="center"><input type="text" readonly="true" name="bbeligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="bbissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="bbperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Dept</td>
      <td ><input type="text" readonly="true" name="dept" size="32"></td>
      <td Class="addedit">Valid
        Date</td>
      <td ><input type="text" readonly="true" name="validDate" size="19"></td>
      <td Class="addedit">NonBook</td>
      <td  align="center"><input type="text" readonly="true" name="nbeligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="nbissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="nbperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Course</td>
      <td ><input type="text" readonly="true" name="course" size="32"></td>
      <td Class="addedit">Year</td>
      <td ><input type="text" readonly="true" name="year" size="19"></td>
      <td Class="addedit">Journal<input type="radio" value="ISS" name="jnlissue" Class="addedit"></td>
      <td  align="center"><input type="text" readonly="true" name="jeligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="jissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="jperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td  colspan="2" >
<%
if(Message!=null){
if(Message.equals("MemberNotFound")){
	flag="";
	
out.print("<b><font face=Verdana size=2 color=#ff00ff>Member Not Found!!!</font></b>");
}
if(Message.equals("MemberLock")){
	flag="";
String mcode=String.valueOf(request.getAttribute("SLock"));	
out.print("<b><font face=Verdana size=2 color=#ff00ff>User ID '["+mcode+"]' has been Locked!!!</font></b>");
}
if(Message.equals("BookNotbook")){
flag="";
detils="";
out.print("<b><font face=Verdana size=2 color=#ff00ff>Document Not Found!!!</font></b>");
notbook=false;
//flag="member";
}
if(Message.equals("BookNotbook1")){
	//flag="";
	//detils="";
	out.print("<b><font face=Verdana size=2 color=#ff00ff>Document Not Found!!!</font></b>");
	notbook=false;
	}
if(Message.equals("valid")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Please Renew Your Membership!!!</font></b>");
flag="member";
}
if(Message.equals("validMem")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Member Not Found  !</font></b>");
flag="member";
}
if(Message.equals("alreadyissued")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Wrong Entry OR This Document Already Issued!!!</font></b>");
flag="member";
}
if(Message.equals("booknot")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>This Document Not Found!!!</font></b>");
flag="member";
}
if(Message.equals("issued")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Document issued successfully!!!</font></b>");
flag="member";
}
if(Message.equals("return")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Document returned successfully!!!</font></b>");
flag="member";
}
if(Message.equals("renew")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Document renewed successfully!!</font></b>");
flag="member";
}
if(Message.equals("nrenew")){
out.print("<b><font face=Verdana size=2 color=#ff00ff>Already reached your No.of Renewal!!!</font></b>");
flag="member";
}
if(Message.equals("issue_error")){
String name=String.valueOf(request.getAttribute("temp2"));
String code=String.valueOf(request.getAttribute("temp1"));
out.print("<b><font face=Verdana size=2 color=#ff00ff> ' "+name+" ' '("+code+")' is in Top Priority!!</font></b>");
iss=false;
flag="member";
}
}
%>
  </td>
	  <td bgcolor="#BCD6E2" bordercolor="#FFE6BC"><b><input type="radio" value="V2" name="sel_search" Class="addedit">Resource</td>
      
      <td ><input type="radio"  name="sel_search" Class="addedit"><input type="text" name="barcode" size="16" maxlength="15"  onFocus="barcodeopt()" ></td>
      <td Class="addedit">Back
        Vol</td>
      <td  align="center"><input type="text" readonly="true" name="bveligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="bvissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="bvperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Acc.No</td>
      <td ><input type="text" name="accno" size="17" maxlength="15" onFocus="bookopt()" ></td><!--onfocus="bookopt()" -->
      <td Class="addedit">Call No</td>
      <td ><input type="text" readonly="true" name="callNo" size="19"></td>
      <td Class="addedit">Thesis</td>
      <td  align="center"><input type="text" readonly="true" name="teligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="tissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="tperiod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Title</td>
      <td  colspan="3"><input type="text" readonly="true" name="title" size="68"></td>
      <td Class="addedit">Standard</td>
      <td align="center"><input type="text" readonly="true" name="seligible" size="1" value="0"></td>
      <td align="center"><input type="text" readonly="true" name="sissued" size="1" value="0"></td>
      <td align="center"><input type="text" readonly="true" name="speriod" size="1" value="0"></td>
    </tr>
    <tr>
      <td Class="addedit">Author</td>
      <td ><input type="text" readonly="true" name="author" size="32"></td>
      <td Class="addedit">Type</td>
      <td ><input type="text" readonly="true" name="doc" size="19"></td>
      <td Class="addedit">Proceeding</td>
      <td  align="center"><input type="text" readonly="true" name="peligible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="pissued" size="1" value="0"></td>
      <td align="center"><input type="text" readonly="true" name="pperiod" size="1" value="0"></td>
    </tr>
    <tr>
	
	 <td Class="addedit">Publisher</td>
      <td ><input type="text" readonly="true" name="publisher" size="32"></td>
      <td Class="addedit">Status</td>
      <td ><input type="text" readonly="true" name="avail" size="19"></td>
      <td Class="addedit">Report</td>
      <td  align="center"><input type="text" readonly="true" name="religible" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="rissued" size="1" value="0"></td>
      <td  align="center"><input type="text" readonly="true" name="rperiod" size="1" value="0"></td>
    </tr>
    <tr>
	    <td  bgcolor="#BCD6E2" colspan="8"> <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Issue Date<input type="text" readonly="true" name="idate" value="<%=Security_Counter.TodayDate_view()%>" size="12"> Due Date <input type="text" readonly="true" name="ddate" value="<%=Security_Counter.TodayDate_view()%>" size="12">
        Return Date<input type="text" readonly="true" name="rdate" value="<%=Security_Counter.TodayDate_view()%>" size="12"> Fine<input type="text" readonly="true" name="fine" value="0.0"  size="6"></td>
    </tr>
    <tr>
      <td colspan="8"><center>
        <input onclick="issue()" type="button" Class="submitButton" value="Issue">
        <input onclick="ireturn()" type="button" Class="submitButton" value="Return">
		<input onclick="renew()" type="button" Class="submitButton" value="Renew">
		<input onclick="search_record()" type="submit" Class="submitButton" value="Search">		
		<input type="Reset" value="Reset" Class="submitButton" onclick="rsetpage()">

		<input type=hidden name=flag>
		<input type=hidden name=flagAccno>
		<input type=hidden name=flagDoc>
						
		<input type="hidden" name="payflag" value="NO">
								
		</center></td>
    </tr>
  </table></table>
  
 <a href="javascript:void();" onclick="javascript:checkAll('counter', true);">All</a> | 
 <a href="javascript:void();" onclick="javascript:checkAll('counter', false);">None</a>
  <jsp:include page="CounterListdisplay.jsp" flush="true" />
    	
  </form>

</body>
</html>

<%
session.setAttribute("BulkCounterList",null);
%>

<script language="JavaScript">

if(document.counter.sel_search[0].checked)
{
  document.counter.mcode.focus();
}

</script>

<%

if(flag!=null){

 if(flag.equals("rkfine")) {
		%>
		<script>
	         alert("Document Returned Successfully");
		</script><%
flag="member";
}
 
/**  
if(flag.equals("fwreturn")){
%><script>
//document.counter.fine.value="<%=session.getAttribute("FINE")%>";
//alert("Please Collect Fine Amount Rs:"+"<%=session.getAttribute("FINE")%>");
alert("Please Collect The Fine Amount ");
alert("Document renewed successfully");
</script><%
flag="member";
}*/

if(flag.equals("noissue")){
%><script>
alert(" This Document Not Issued To You");
</script><%
flag="member";
}
if(flag.equals("reservedbook")){
	%><script>
	alert(" This Document is Reserved. Please Return the Document");
	</script><%
	flag="member";
	}


				if(flag.equals("member")){
  				if(request.getParameter("accno")!=null){
				if(notbook){

				%><script>


				</script><%}}

				if(iss){

				%>

				<script>
				
				document.counter.mcode.value="<%=bean.getMcode()%>";
				document.counter.mname.value="<%=bean.getMname()%>";
				document.counter.group.value="<%=bean.getGroup()%>";
				document.counter.desig.value="<%=bean.getDesig()%>";
				document.counter.dept.value="<%=bean.getDept()%>";
				document.counter.validDate.value="<%=Security_Counter.getdate(bean.getValidDate())%>";
				document.counter.course.value="<%=bean.getCourse()%>-<%=bean.getMajor()%>";
				document.counter.year.value="<%=bean.getYear()%>";
				document.counter.geligible.value="<%=bean.getGeligible()%>";
				document.counter.gperiod.value="<%=bean.getGperiod()%>";
				document.counter.beligible.value="<%=bean.getBeligible()%>";
				document.counter.bissued.value="<%=bean.getBissued()%>";
				document.counter.bperiod.value="<%=bean.getBperiod()%>";
				document.counter.bbeligible.value="<%=bean.getBbeligible()%>";
				document.counter.bbissued.value="<%=bean.getBbissued()%>";
				document.counter.bbperiod.value="<%=bean.getBbperiod()%>";
				document.counter.nbeligible.value="<%=bean.getNbeligible()%>";
				document.counter.nbissued.value="<%=bean.getNbissued()%>";
				document.counter.nbperiod.value="<%=bean.getNbperiod()%>";
				document.counter.jeligible.value="<%=bean.getJeligible()%>";
				document.counter.jissued.value="<%=bean.getJissued()%>";
				document.counter.jperiod.value="<%=bean.getJperiod()%>";
				document.counter.bveligible.value="<%=bean.getBveligible()%>";
				document.counter.bvissued.value="<%=bean.getBvissued()%>";
				document.counter.bvperiod.value="<%=bean.getBvperiod()%>";
				document.counter.teligible.value="<%=bean.getTeligible()%>";
				document.counter.tissued.value="<%=bean.getTissued()%>";
				document.counter.tperiod.value="<%=bean.getTperiod()%>";
				document.counter.seligible.value="<%=bean.getSeligible()%>";
				document.counter.sissued.value="<%=bean.getSissued()%>";
				document.counter.speriod.value="<%=bean.getSperiod()%>";
				document.counter.peligible.value="<%=bean.getPeligible()%>";
				document.counter.pissued.value="<%=bean.getPissued()%>";
				document.counter.pperiod.value="<%=bean.getPperiod()%>";
				document.counter.religible.value="<%=bean.getReligible()%>";
				document.counter.rissued.value="<%=bean.getRissued()%>";
				document.counter.rperiod.value="<%=bean.getRperiod()%>";
				document.counter.mcode.readOnly=false;


				if(document.counter.beligible.value==0 && document.counter.bperiod.value==0 && document.counter.nbeligible.value==0 && document.counter.nbperiod.value==0 && document.counter.bbeligible.value==0 && document.counter.bbperiod.value==0 && document.counter.seligible.value==0 && document.counter.speriod.value==0 && document.counter.bveligible.value==0 && document.counter.bvperiod.value==0 && document.counter.religible.value==0 && document.counter.rperiod.value==0 && document.counter.teligible.value==0 && document.counter.tperiod.value==0 && document.counter.peligible.value==0 && document.counter.pperiod.value==0 && document.counter.jeligible.value==0 && document.counter.jperiod.value==0 ){
				 document.counter.beligible.disabled=true;
	         	 document.counter.bperiod.disabled=true;
				 document.counter.nbeligible.disabled=true;
				 document.counter.nbperiod.disabled=true;
				 document.counter.bbeligible.disabled=true;
				 document.counter.bbperiod.disabled=true;
				 document.counter.seligible.disabled=true;
				 document.counter.speriod.disabled=true;
				 document.counter.bveligible.disabled=true;
				 document.counter.bvperiod.disabled=true;
				 document.counter.religible.disabled=true;
				 document.counter.rperiod.disabled=true;
				 document.counter.teligible.disabled=true;
				 document.counter.tperiod.disabled=true;
				 document.counter.peligible.disabled=true;
				 document.counter.pperiod.disabled=true;
				 document.counter.jeligible.disabled=true;
				 document.counter.jperiod.disabled=true;
				 document.counter.sel_search[1].checked=true;
				 document.counter.accno.focus();

				}
				else if(document.counter.geligible.value==0 && document.counter.gperiod.value==0 && document.counter.gissued.value==0)
				{
				document.counter.geligible.disabled=true;
				document.counter.gperiod.disabled=true;
				document.counter.gissued.disabled=true;
				document.counter.sel_search[1].checked=true;
 			    document.counter.accno.focus();
			
				}
				else
				{
				  document.counter.nbeligible.disabled=true;
				  document.counter.bbeligible.disabled=true;
				  document.counter.seligible.disabled=true;
				  document.counter.bveligible.disabled=true;
				  document.counter.religible.disabled=true; 
				  document.counter.teligible.disabled=true; 
				  document.counter.peligible.disabled=true;
				  document.counter.jeligible.disabled=true;
				  document.counter.beligible.disabled=true;
				  document.counter.gperiod.disabled=true;
				  document.counter.gissued.disabled=true;
				  document.counter.sel_search[1].checked=true;
				  document.counter.accno.focus();

				}

				</script>
				<%
				}
				else
				{
				%>

				<script>
				document.counter.mcode.value="<%=bean.getMcode()%>";
				document.counter.mname.value="<%=bean.getMname()%>";
				document.counter.group.value="<%=bean.getGroup()%>";
	    		document.counter.desig.value="<%=bean.getDesig()%>";
				document.counter.dept.value="<%=bean.getDept()%>";
				document.counter.validDate.value="<%=Security_Counter.getdate(bean.getValidDate())%>";
				document.counter.course.value="<%=bean.getCourse()%>";
				document.counter.year.value="<%=bean.getYear()%>";
				document.counter.geligible.value="<%=bean.getGeligible()%>";
				document.counter.gperiod.value="<%=bean.getGperiod()%>";
				document.counter.beligible.value="<%=bean.getBeligible()%>";
				document.counter.bissued.value="<%=bean.getBissued()%>";
				document.counter.bperiod.value="<%=bean.getBperiod()%>";
				document.counter.bbeligible.value="<%=bean.getBbeligible()%>";
				document.counter.bbissued.value="<%=bean.getBbissued()%>";
				document.counter.bbperiod.value="<%=bean.getBbperiod()%>";
				document.counter.nbeligible.value="<%=bean.getNbeligible()%>";
				document.counter.nbissued.value="<%=bean.getNbissued()%>";
				document.counter.nbperiod.value="<%=bean.getNbperiod()%>";
				document.counter.jeligible.value="<%=bean.getJeligible()%>";
				document.counter.jissued.value="<%=bean.getJissued()%>";
				document.counter.jperiod.value="<%=bean.getJperiod()%>";
				document.counter.bveligible.value="<%=bean.getBveligible()%>";
				document.counter.bvissued.value="<%=bean.getBvissued()%>";
				document.counter.bvperiod.value="<%=bean.getBvperiod()%>";
				document.counter.teligible.value="<%=bean.getTeligible()%>";
				document.counter.tissued.value="<%=bean.getTissued()%>";
				document.counter.tperiod.value="<%=bean.getTperiod()%>";
				document.counter.seligible.value="<%=bean.getSeligible()%>";
				document.counter.sissued.value="<%=bean.getSissued()%>";
				document.counter.speriod.value="<%=bean.getSperiod()%>";
				document.counter.peligible.value="<%=bean.getPeligible()%>";
				document.counter.pissued.value="<%=bean.getPissued()%>";
				document.counter.pperiod.value="<%=bean.getPperiod()%>";
				document.counter.religible.value="<%=bean.getReligible()%>";
				document.counter.rissued.value="<%=bean.getRissued()%>";
				document.counter.rperiod.value="<%=bean.getRperiod()%>";
				document.counter.mcode.readOnly=false;


				if(document.counter.beligible.value==0 && document.counter.bperiod.value==0 && document.counter.nbeligible.value==0 && document.counter.nbperiod.value==0 && document.counter.bbeligible.value==0 && document.counter.bbperiod.value==0 && document.counter.seligible.value==0 && document.counter.speriod.value==0 && document.counter.bveligible.value==0 && document.counter.bvperiod.value==0 && document.counter.religible.value==0 && document.counter.rperiod.value==0 && document.counter.teligible.value==0 && document.counter.tperiod.value==0 && document.counter.peligible.value==0 && document.counter.pperiod.value==0 && document.counter.jeligible.value==0 && document.counter.jperiod.value==0 ){
				 document.counter.beligible.disabled=true;
		         document.counter.bperiod.disabled=true;
				 document.counter.nbeligible.disabled=true;
				 document.counter.nbperiod.disabled=true;
				 document.counter.bbeligible.disabled=true;
				 document.counter.bbperiod.disabled=true;
				 document.counter.seligible.disabled=true;
				 document.counter.speriod.disabled=true;
				 document.counter.bveligible.disabled=true;
				 document.counter.bvperiod.disabled=true;
				 document.counter.religible.disabled=true;
				 document.counter.rperiod.disabled=true;
				 document.counter.teligible.disabled=true;
				 document.counter.tperiod.disabled=true;
				 document.counter.peligible.disabled=true;
				 document.counter.pperiod.disabled=true;
				 document.counter.jeligible.disabled=true;
				 document.counter.jperiod.disabled=true;
				 document.counter.sel_search[1].checked=true;
				 document.counter.accno.focus();
				}
				else if(document.counter.geligible.value==0 && document.counter.gperiod.value==0 && document.counter.gissued.value==0)
				{
				document.counter.geligible.disabled=true;
				document.counter.gperiod.disabled=true;
				document.counter.gissued.disabled=true;
				document.counter.sel_search[1].checked=true;
			    document.counter.accno.focus();
				}
				else
				{
				  document.counter.nbeligible.disabled=true;
				  document.counter.bbeligible.disabled=true;
				  document.counter.seligible.disabled=true;
				  document.counter.bveligible.disabled=true;
				  document.counter.religible.disabled=true; 
				  document.counter.teligible.disabled=true; 
				  document.counter.peligible.disabled=true;
				  document.counter.jeligible.disabled=true;
				  document.counter.beligible.disabled=true;
				  document.counter.gperiod.disabled=true;
				  document.counter.gissued.disabled=true;
				  document.counter.sel_search[1].checked=true;
				  document.counter.accno.focus();
				}

				</script>
				<%
				}
				
				}



 			if(flag.equals("book")){
 				
 				
 			if(request.getParameter("mcode")!=null){
			%>
				<script>
				document.counter.mcode.value="<%=request.getParameter("mcode")%>";
				document.counter.mname.value="<%=request.getParameter("mname")%>";
				document.counter.group.value="<%=request.getParameter("group")%>";
				document.counter.desig.value="<%=request.getParameter("desig")%>";
				document.counter.dept.value="<%=request.getParameter("dept")%>";
				document.counter.validDate.value="<%=request.getParameter("validDate")%>";
				document.counter.course.value="<%=request.getParameter("course")%>";
				document.counter.year.value="<%=request.getParameter("year")%>";
				document.counter.ddate.value="<%=request.getParameter("ddate")%>";
				document.counter.fine.value="<%=request.getParameter("fine")%>";
				document.counter.avail.value="<%=bean.getAvail()%>";				
				
				document.counter.geligible.value="<%=bean.getGeligible()%>";
				document.counter.gperiod.value="<%=bean.getGperiod()%>";
				document.counter.beligible.value="<%=bean.getBeligible()%>";
				document.counter.bissued.value="<%=bean.getBissued()%>";
				document.counter.bperiod.value="<%=bean.getBperiod()%>";
				document.counter.bbeligible.value="<%=bean.getBbeligible()%>";
				document.counter.bbissued.value="<%=bean.getBbissued()%>";
				document.counter.bbperiod.value="<%=bean.getBbperiod()%>";
				document.counter.nbeligible.value="<%=bean.getNbeligible()%>";
				document.counter.nbissued.value="<%=bean.getNbissued()%>";
				document.counter.nbperiod.value="<%=bean.getNbperiod()%>";
				document.counter.jeligible.value="<%=bean.getJeligible()%>";
				document.counter.jissued.value="<%=bean.getJissued()%>";
				document.counter.jperiod.value="<%=bean.getJperiod()%>";
				document.counter.bveligible.value="<%=bean.getBveligible()%>";
				document.counter.bvissued.value="<%=bean.getBvissued()%>";
				document.counter.bvperiod.value="<%=bean.getBvperiod()%>";
				document.counter.teligible.value="<%=bean.getTeligible()%>";
				document.counter.tissued.value="<%=bean.getTissued()%>";
				document.counter.tperiod.value="<%=bean.getTperiod()%>";
				document.counter.seligible.value="<%=bean.getSeligible()%>";
				document.counter.sissued.value="<%=bean.getSissued()%>";
				document.counter.speriod.value="<%=bean.getSperiod()%>";
				document.counter.peligible.value="<%=bean.getPeligible()%>";
				document.counter.pissued.value="<%=bean.getPissued()%>";
				document.counter.pperiod.value="<%=bean.getPperiod()%>";
				document.counter.religible.value="<%=bean.getReligible()%>";
				document.counter.rissued.value="<%=bean.getRissued()%>";
				document.counter.rperiod.value="<%=bean.getRperiod()%>";
							
				if(document.counter.beligible.value==0 && document.counter.bperiod.value==0 && document.counter.nbeligible.value==0 && document.counter.nbperiod.value==0 && document.counter.bbeligible.value==0 && document.counter.bbperiod.value==0 && document.counter.seligible.value==0 && document.counter.speriod.value==0 && document.counter.bveligible.value==0 && document.counter.bvperiod.value==0 && document.counter.religible.value==0 && document.counter.rperiod.value==0 && document.counter.teligible.value==0 && document.counter.tperiod.value==0 && document.counter.peligible.value==0 && document.counter.pperiod.value==0 && document.counter.jeligible.value==0 && document.counter.jperiod.value==0 ){				
				 document.counter.beligible.disabled=true;
		         document.counter.bperiod.disabled=true;
				 document.counter.nbeligible.disabled=true;
				 document.counter.nbperiod.disabled=true;
				 document.counter.bbeligible.disabled=true;
				 document.counter.bbperiod.disabled=true;
				 document.counter.seligible.disabled=true;
				 document.counter.speriod.disabled=true;
				 document.counter.bveligible.disabled=true;
				 document.counter.bvperiod.disabled=true;
				 document.counter.religible.disabled=true;
				 document.counter.rperiod.disabled=true;
				 document.counter.teligible.disabled=true;
				 document.counter.tperiod.disabled=true;
				 document.counter.peligible.disabled=true;
				 document.counter.pperiod.disabled=true;
				 document.counter.jeligible.disabled=true;
				 document.counter.jperiod.disabled=true;
				 document.counter.sel_search[1].checked=true;				
				 document.counter.accno.focus();				
				}
				else if(document.counter.geligible.value==0 && document.counter.gperiod.value==0 && document.counter.gissued.value==0)
				{
				document.counter.geligible.disabled=true;
				document.counter.gperiod.disabled=true;
				document.counter.gissued.disabled=true;
				document.counter.sel_search[1].checked=true;				
				document.counter.accno.focus();				
				}
				else
				{
				  document.counter.nbeligible.disabled=true;
				  document.counter.bbeligible.disabled=true;
				  document.counter.seligible.disabled=true;
				  document.counter.bveligible.disabled=true;
				  document.counter.religible.disabled=true; 
				  document.counter.teligible.disabled=true; 
				  document.counter.peligible.disabled=true;
				  document.counter.jeligible.disabled=true;
				  document.counter.beligible.disabled=true;
				  document.counter.gperiod.disabled=true;
				  document.counter.gissued.disabled=true;
				
				}
				</script><%

		}


             	 %><script>
 				document.counter.accno.value="<%=beanmember.getAccno()%>";
				document.counter.title.value="<%=beanmember.getTitle()%>";
    			document.counter.author.value="<%=beanmember.getAuthor()%>";
				document.counter.avail.value="<%=beanmember.getAvail()%>";
				document.counter.doc.value="<%=beanmember.getDoc()%>";
				document.counter.callNo.value="<%=beanmember.getCallNo()%>";
				document.counter.publisher.value="<%=beanmember.getPublisher()%>";





// For Bulk Counter
document.counter.sel_search[1].checked=true   	 
document.counter.accno.focus();
                document.counter.accno.value="";
				document.counter.title.value="";
   				document.counter.author.value="";
				document.counter.avail.value="";
				document.counter.doc.value="";
				document.counter.callNo.value="";
				document.counter.publisher.value="";

				</script>		
				
				<%
 		}


//------------------------------------------------------------flag end--------------------------------//

		}


				if(load!=null){
				if(load.equals("loadbook")){

  				%><script>
                document.counter.accno.value="<%=beanmember.getAccno()%>";
				document.counter.title.value="<%=beanmember.getTitle()%>";
   				document.counter.author.value="<%=beanmember.getAuthor()%>";
				document.counter.avail.value="<%=beanmember.getAvail()%>";
				document.counter.doc.value="<%=beanmember.getDoc()%>";
				document.counter.callNo.value="<%=beanmember.getCallNo()%>";
				document.counter.publisher.value="<%=beanmember.getPublisher()%>";

				if(document.counter.avail.value=="YES"){

				document.counter.idate.value="<%=Security_Counter.TodayDate_view()%>";
				document.counter.ddate.value="<%=bean.getCalldate()%>";
				document.counter.rdate.value="<%=Security_Counter.TodayDate_view()%>";
    	    	document.counter.fine.value="0.0";
				
				}
				
               	if(document.counter.mcode.value!=""){
			    document.counter.ddate.value="<%=bean.getCalldate()%>";
			   	}

               if(document.counter.avail.value=="ISSUED"){
				<%
				String date=bean.getCallissdate();
				String date1=bean.getCallduedate();
                                 %>
			  	 document.counter.idate.value="<%=date%>";
				 document.counter.ddate.value="<%=date1%>";
				 document.counter.fine.value="<%=bean.getTemp()%>";
				 document.counter.mcode.readOnly=false;
   	           }
   	           
document.counter.sel_search[1].checked=true   	 
document.counter.accno.focus();


// For Bulk Counter

                document.counter.accno.value="";
				document.counter.title.value="";
   				document.counter.author.value="";
				document.counter.avail.value="";
				document.counter.doc.value="";
				document.counter.callNo.value="";
				document.counter.publisher.value="";




				</script><%



				}




    }

if(detils!=null){
if(detils.equals("ISSUEDEATILS")){
/*---------------------------------------ISSUE DETAILS----------------------------------------------------------------------------------------------------------------------------------------------------------*/
                    sc=(ArrayList)bean.getCounterList();
                    
                    if(sc.size() > 0) {
                    out.print("<b><center>Issue Details</center></b>");
                    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
                    out.print("<tr><th>Member Code<th>Access No<th>Issue Date<th>Due Date<th>Staff Code<th>Doc Type</tr>");
            		
            		for(int i=0; i<sc.size();i+=10){
            		int countac=String.valueOf(sc.get(i)).toString().length();

            		%>
            		 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i+4)%>","<%=sc.get(i)%>","<%=sc.get(i+1)%>","<%=sc.get(i+2)%>","<%=sc.get(i+3)%>","<%=Security_Counter.getdate(sc.get(i+5).toString())%>","<%=Security_Counter.getdate(sc.get(i+6).toString())%>","<%=sc.get(i+9)%>","<%=countac%>")'>
            		 <script language=javascript>
            		 document.write("<td>"+"<%=sc.get(i+4)%>" +"</td>");
            		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
            		 document.write("<td>"+"<%=Security_Counter.getdate(sc.get(i+5).toString())%>"+"</td>");
            		 document.write("<td>"+"<%=Security_Counter.getdate(sc.get(i+6).toString())%>"+"</td>");
            		 document.write("<td>"+"<%=sc.get(i+8)%>"+"</td>");
            		 document.write("<td>"+"<%=sc.get(i+9)%>"+"</td>");
            		 document.write("</tr>");
            		</script>
            		<%
            			}
                    }
            			out.print("</table><br>");
            			sc.clear();	
			
}

 
}


%>


<script language="JavaScript">
function Receipt()
{
winpopup=window.open("receipt.jsp","popup","height=300,width=300,left=400,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=yes")
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function memopt(){
document.counter.sel_search[0].checked=true;
document.counter.flag.value="member";
}

function bookopt(){

if(document.counter.jnlissue.checked==true)
{
//alert("hai");  // 04-03-2014
  document.counter.doc.value="JOURNAL";
}

document.counter.sel_search[1].checked=true;
document.counter.flag.value="book";
		}
function barcodeopt(){

document.counter.sel_search[2].checked=true;
document.counter.flag.value="barcode";
		}		
		
function search_record(){

if((document.counter.mcode.value!="")|| (document.counter.accno.value!="")) {
	if(document.counter.sel_search[0].checked==true){
	document.counter.flag.value="member";
	}
	if(document.counter.sel_search[1].checked==true){
	document.counter.flag.value="book";
	}
	if(document.counter.sel_search[2].checked==true){
	alert("Insufficient Document");
	document.counter.flag.value="clear";
	}
	}
	else{	
	alert("Insufficient Document");
	document.counter.flag.value="clear";
	document.counter.submit();
	}
}

function rsetpage(){

document.counter.flag.value="clear";
document.counter.submit();
}		




function issue(){

if(chk()){
        
    	var ids = document.getElementsByName('selectedIds[]');
		var docs = document.getElementsByName('bdocument[]');	
		var blstatus = document.getElementsByName('bavailability[]');	
		var bduedate = document.getElementsByName('bduedate[]');		
				
		var chkitem=0;
		var errmsg=0;
		var content="";
		var chkdoc="";
		var chkstatus="";
		
		var mbcount=0,mbbcount=0,mscount=0,mbvcount=0,mrcount=0,mtcount=0,mpcount=0,mnbcount=0,mjcount=0,mgcount=0;		
		mbcount = document.counter.bissued.value;		
		mbbcount = document.counter.bbissued.value;
		mscount = document.counter.sissued.value;
		mbvcount = document.counter.bvissued.value;
		mrcount = document.counter.rissued.value;
		mtcount = document.counter.tissued.value;
		mpcount = document.counter.pissued.value;
		mnbcount = document.counter.nbissued.value;
		mjcount = document.counter.jissued.value;
		
		
						
		for(i=0;i<ids.length;i++)
		{
			if(ids[i].checked == true)
				{
				 chkitem++;
				 content = content + "," + ids[i].value; 
				 chkdoc = chkdoc + "," + docs[i].value;				 				 
				 //chkstatus = chkstatus+",'" + blstatus[i].value + "'"; 
				 			 				 
		document.counter.avail.value = blstatus[i].value;	// For Availability	
		document.counter.ddate.value = bduedate[i].value;   // For Due Date	
		document.counter.doc.value = docs[i].value;  // For Document Type		
		
		document.counter.flagAccno.value = content;
		document.counter.flagDoc.value = chkdoc;			
		
		document.counter.method="get";
		document.counter.flag.value="issue";
		
		
		
		if(docs[i].value=='BOOK')
		 mbcount++;
		else if(docs[i].value=='BOOK BANK') 
		 mbbcount++;
		else if(docs[i].value=='STANDARD') 
		 mscount++;
		else if(docs[i].value=='BACK VOLUME') 
		 mbvcount++;
		else if(docs[i].value=='REPORT') 		 
		 mrcount++;
		else if(docs[i].value=='THESIS') 			
		 mtcount++;
		else if(docs[i].value=='PROCEEDING') 			
		 mpcount++;
		else if(docs[i].value=='NON BOOK') 		
 		 mnbcount++;
 		else if(docs[i].value=='JOURNAL') 		
 		 mjcount++; 		
 		 
 		mgcount++;  // For General Eligibility		 
 		 
 		 
		//alert("MAXCount:"+mgcount);
		
		
		if(document.counter.beligible.value==0 && document.counter.bperiod.value==0 && document.counter.nbeligible.value==0 && document.counter.nbperiod.value==0 && document.counter.bbeligible.value==0 && document.counter.bbperiod.value==0 && document.counter.seligible.value==0 && document.counter.speriod.value==0 && document.counter.bveligible.value==0 && document.counter.bvperiod.value==0 && document.counter.religible.value==0 && document.counter.rperiod.value==0 && document.counter.teligible.value==0 && document.counter.tperiod.value==0 && document.counter.peligible.value==0 && document.counter.pperiod.value==0 && document.counter.jeligible.value==0 && document.counter.jperiod.value==0 ){
		
		var count=parseInt(document.counter.bissued.value)+parseInt(document.counter.nbissued.value)+parseInt(document.counter.bbissued.value)+parseInt(document.counter.sissued.value)+parseInt(document.counter.bvissued.value)+parseInt(document.counter.rissued.value)+parseInt(document.counter.tissued.value)+parseInt(document.counter.pissued.value)+parseInt(document.counter.jissued.value);
       
        count = parseInt(count) + mgcount;
     
     
  		if(document.counter.avail.value=="ISSUED")
  		{
		 alert("Resource Already In Issue");
		 errmsg = 1;
		 break;
		}
  		else if((document.counter.idate.value) == (document.counter.ddate.value))
  		{
		 //alert("Issue Date and Due Date should not be same. This document can not be issued to you");
		 alert("Kindly check your Eligibility");		  		
  		 errmsg = 1;
		 break;
		}
  		else if(document.counter.avail.value=="YES" && parseInt(document.counter.geligible.value) >= count)
	    {
		 //document.counter.submit();
		 //alert("RK general");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}		
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}		 
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}		 
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}		 
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}		 
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");		
		 errmsg = 1;
		 break;
		}
		else if(document.counter.geligible.value < count)
		{
		 alert("Can't Issue Resource - Max Resource Already Issued ");
		 errmsg = 1;
		 break;
		}
		
		
		}
		else if(document.counter.geligible.value==0 && document.counter.gperiod.value==0 && document.counter.gissued.value==0)
		{

		if((document.counter.idate.value) == (document.counter.ddate.value))
		{
		 //alert("Issue Date and Due Date should not be same. This document can not be issued to you");
		 alert("Kindly check your Eligibility");		  		
  		 errmsg = 1;
		 break;
		}		
		//alert("Cursor here");
		else
		{
		if(document.counter.doc.value=="BOOK"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.beligible.value) >= parseInt(mbcount))
		{
		 //document.counter.submit();
		 //alert("RK Book");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE Document Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("Document Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.beligible.value<=parseInt(document.counter.bissued.value))
		{
		 alert("Can't Issue BOOK - Max BOOK Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.beligible.value < parseInt(mbcount))
		{
		 alert("Can't Issue BOOK more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		if(document.counter.doc.value=="BOOK BANK"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.bbeligible.value) >= parseInt(mbbcount))
		{
		 //document.counter.submit();
		 //alert("RK DONE BOOK BANK");
		}  
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE BOOK BANK Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("BOOK BANK Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.bbeligible.value<=parseInt(document.counter.bbissued.value))
		{
		 alert("Can't Issue BOOK BANK - Max BOOK BANK Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.bbeligible.value < parseInt(mbbcount))
		{
		 alert("Can't Issue BOOK BANK more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		if(document.counter.doc.value=="STANDARD"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.seligible.value) >= parseInt(mscount) )
		{ 
		 //document.counter.submit();
		 //alert("RK DONE STANDARD");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE STANDARD Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("STANDARD Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.seligible.value<=parseInt(document.counter.sissued.value))
		{
		 alert("Can't Issue STANDARD - Max STANDARD Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.seligible.value < parseInt(mscount))
		{
		 alert("Can't Issue STANDARD more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		if(document.counter.doc.value=="BACK VOLUME"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.bveligible.value) >= parseInt(mbvcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE BACK VOLUME");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE BACK VOLUME Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("BACK VOLUME Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.bveligible.value<=parseInt(document.counter.bvissued.value))
		{
		 alert("Can't Issue BACK VOLUME - Max BACK VOLUME Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.bveligible.value < parseInt(mbvcount))
		{
		 alert("Can't Issue BACK VOLUME more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		
		if(document.counter.doc.value=="REPORT"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.religible.value) >= parseInt(mrcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE REPORT");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE REPORT Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{ 
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("REPORT Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.religible.value<=parseInt(document.counter.rissued.value))
		{
		 alert("Can't Issue REPORT - Max REPORT Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.religible.value < parseInt(mrcount))
		{
		 alert("Can't Issue REPORT more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		
		if(document.counter.doc.value=="THESIS"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.teligible.value) >= parseInt(mtcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE THESIS");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE THESIS Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{ 
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("THESIS Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.teligible.value<=parseInt(document.counter.tissued.value))
		{
		 alert("Can't Issue THESIS - Max THESIS Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.teligible.value < parseInt(mtcount))
		{
		 alert("Can't Issue THESIS more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		
		if(document.counter.doc.value=="PROCEEDING"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.peligible.value) >= parseInt(mpcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE PROCEEDING");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE PROCEEDING Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("PROCEEDING Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.peligible.value<=parseInt(document.counter.pissued.value))
		{
		 alert("Can't Issue PROCEEDING - Max PROCEEDING Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.peligible.value < parseInt(mpcount))
		{
		 alert("Can't Issue PROCEEDING more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		
		if(document.counter.doc.value=="NON BOOK"){
		if(document.counter.avail.value=="YES" && parseInt(document.counter.nbeligible.value) >= parseInt(mnbcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE NON BOOK");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE NON BOOK Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("NON BOOK Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.nbeligible.value<=parseInt(document.counter.nbissued.value))
		{
		 alert("Can't Issue NON BOOK - Max NON BOOK Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.nbeligible.value < parseInt(mnbcount))
		{
		 alert("Can't Issue NON BOOK more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		
		
		if(document.counter.doc.value=="JOURNAL"){  // Added on 10-03-2014 by RK
		if(document.counter.avail.value=="YES" && parseInt(document.counter.jeligible.value) >= parseInt(mjcount) )
		{
		 //document.counter.submit();
		 //alert("RK DONE");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE JOURNELS Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("JOURNELS  Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.jeligible.value<=parseInt(document.counter.jissued.value))
		{ 
		 alert("Can't Issue JOURNELS  - Max JOURNALS  Already Issued ");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.jeligible.value < parseInt(mjcount))
		{
		 alert("Can't Issue JOURNELS more than your eligibility ");
		 errmsg = 1;
		 break;
		}
		
		}
		}
		}

		else
		{
		var count_gs=parseInt(document.counter.bissued.value)+parseInt(document.counter.nbissued.value)+parseInt(document.counter.bbissued.value)+parseInt(document.counter.sissued.value)+parseInt(document.counter.bvissued.value)+parseInt(document.counter.rissued.value)+parseInt(document.counter.tissued.value)+parseInt(document.counter.pissued.value)+parseInt(document.counter.jissued.value);

		if((document.counter.idate.value) == (document.counter.ddate.value))
		{
		 alert("Issue Date and Due Date should not be same. This document can not be issued to you");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="YES" && parseInt(document.counter.geligible.value)> count_gs)
		{
		 //document.counter.submit();
		 //alert("RK DONE");
		}
		else if(document.counter.avail.value=="REFERENCE")
		{
		 alert("REFERENCE Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="MISSING")
		{
		 alert("MISSING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="LOST")
		{
		 alert("LOST Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="WITHDRAWN")
		{
		 alert("WITHDRAWN Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="TRANSFERED")
		{
		 alert("TRANSFERED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DISPLAY")
		{
		 alert("DISPLAY Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="DAMAGED")
		{
		 alert("DAMAGED Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="BINDING")
		{
		 alert("BINDING Resource Can't Be Issued");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.avail.value=="ISSUED")
		{
		 alert("Resource Already In Issue");
		 errmsg = 1;
		 break;
		}
		else if(document.counter.geligible.value<=count_gs)
		{
		 alert("Can't Issue Resource - Max Resource Already Issued ");
		 errmsg = 1;
		 break;
		}
				
		}
		
		
		// BY RK

              }				
		}
		
		if(chkitem >= 1 && errmsg==0)
		{		
            //alert("Tot:"+content+" Doc:"+chkdoc);
            document.counter.submit();			
		}
		else if(errmsg!=0)
		{
			alert('Error occured RK !...');		
		}else
		{
		    alert('Please select atleast one !...');	
		    document.counter.sel_search[1].checked=true   	 
            document.counter.accno.focus();		    			
		}

		}
else
		alert("Insufficient Document");
}

function chk(){
if(document.counter.mcode.value==""){
return false;
}
else
{
return true;
}
}


function ireturn(){
if(chk()){

		var ids = document.getElementsByName('selectedIds[]');
		var docs = document.getElementsByName('bdocument[]');	
		var blstatus = document.getElementsByName('bavailability[]');	
				
		var blfine = document.getElementsByName('blfine[]');
		var chkfine=0;
				
		var chkitem=0;
		var errmsg=0;
		var content="";
		var chkdoc="";
						
		for(i=0;i<ids.length;i++)
		{
			if(ids[i].checked == true)
				{
				 chkitem++;
				 content = content + "," + ids[i].value; 
				 chkdoc = chkdoc + "," + docs[i].value;				 				 
				 			 				 
		document.counter.avail.value = blstatus[i].value;	// For Availability	
		
		document.counter.flagAccno.value = content;
		document.counter.flagDoc.value = chkdoc;	
		chkfine = parseFloat(chkfine) + parseFloat(blfine[i].value);				
    
		document.counter.method="get";
		document.counter.flag.value="return";
		
        if(document.counter.avail.value=="ISSUED") 
        {
		  //document.counter.submit();
		  //alert("RK Done");
		}
		else 
		{
		  alert("One of this Document Was Not Borrowed By This Member!");
 		  errmsg = 1;
		  break;		  
		} 
		
		       }		
		}
		
		if(parseFloat(chkfine) > 0 )  // For fine amount Checking
		{
     	  if(confirm("Do you want to collect fine amount of Rs."+chkfine))
		  {
		   document.counter.payflag.value = "YES";
		  }else
		  {
		   alert("Fine amount will be added in your account.You can pay later!");
		  }
		}
		
		if(chkitem >= 1 && errmsg==0)
		{		
            //alert("Tot:"+content+" Doc:"+chkdoc);
            document.counter.submit();			
		}
		else if(errmsg!=0)
		{
			alert('Error occured RK !...');		
		}else
		{
		    alert('Please select atleast one !...');	
		    document.counter.sel_search[1].checked=true   	 
            document.counter.accno.focus();		    			
		}
		
		
		
		}
else
		alert("Insufficient Document");

}

function renew(){
if(chk()){

        var ids = document.getElementsByName('selectedIds[]');
		var docs = document.getElementsByName('bdocument[]');	
		var blstatus = document.getElementsByName('bavailability[]');	
		var blfine = document.getElementsByName('blfine[]');	
				
		var chkitem=0;
		var errmsg=0;
		var chkfine=0;
		var blchkfine=0;
		var content="";
		var chkdoc="";
						
		for(i=0;i<ids.length;i++)
		{
			if(ids[i].checked == true)
				{
				 chkitem++;
				 content = content + "," + ids[i].value; 
				 chkdoc = chkdoc + "," + docs[i].value;				 				 
				 			 				 
		document.counter.avail.value = blstatus[i].value;	// For Availability	
		
		document.counter.flagAccno.value = content;
		document.counter.flagDoc.value = chkdoc;
		document.counter.fine.value = blfine[i].value;	
		
		chkfine = parseFloat(chkfine) + parseFloat(blfine[i].value);				
    
		document.counter.method="get";
		document.counter.flag.value="renew";
		
		
		MFine=document.counter.fine.value;
		if(MFine!=0.0){
		  blchkfine++;		
		}
		
		if(document.counter.avail.value=="ISSUED")
		{
		  //document.counter.submit();
		  //alert("RK DONE");
		  
		}
		else
		{
		  alert("This Document is Not Issued, Please Check the availability");
		  errmsg = 1;
		  break;
		}
		
		       }		
		}
		
		
		if(chkitem >= 1 && errmsg==0)
		{		
		 if(blchkfine >= 1){
		  msg=confirm("Do u Want to Renew the Document with Fine?");
		  
		  if(msg){	 
		   
		    if(parseFloat(chkfine) > 0 )  // For fine amount Checking
		    {
     	     if(confirm("Do you want to collect fine amount of Rs."+chkfine))
		     {
		       document.counter.payflag.value = "YES";
		     }else
		     {
		       alert("Fine amount will be added in your account.You can pay later!");
		     }
		    }
		  
            //alert("Tot:"+content+" Doc:"+chkdoc);
            document.counter.submit();	
          }
		  else
		  {
		    return false;
		  }
		 }
		 
		 //alert("Tot:"+content+" Doc:"+chkdoc);
         document.counter.submit();
		 	
		}
		else if(errmsg!=0)
		{
			alert('Error occured RK !...');		
		}else
		{
		    alert('Please select atleast one !...');	
		    document.counter.sel_search[1].checked=true   	 
            document.counter.accno.focus();		    			
		}
		
	 }
	 else
	   alert("Insufficient Document");
}



function show(rmem,val,tt,an,al,it,dd,dt,len){

		var i=0;
		document.counter.accno.value=val;
		document.counter.title.value=tt;
		document.counter.author.value=an;
		document.counter.avail.value=al;
		document.counter.idate.value=it;
		document.counter.ddate.value=dd;
		document.counter.doc.value=dt;
		var nlen=document.counter.accno.value.length;
					if(len>nlen){
						for(i=0;i<(len-nlen);i++)
					document.counter.accno.value="0"+document.counter.accno.value;
					}
		document.counter.method="get";
		document.counter.mcode.value=rmem;
		document.counter.sel_search[1].checked=true;
		if(true)
		{
		document.counter.flag.value="book";
		document.counter.method="get";
		document.counter.submit();
		}
		}


function rshow(rmem){
		    document.counter.accno.value=rmem;
		    
		    document.counter.sel_search[1].checked=true;
		    document.counter.method="get";
			
		   document.counter.sel_search[1].checked=true;
		   

		if(document.counter.sel_search[1].checked==true){
	         document.counter.flag.value="book";
			 document.counter.submit();
			}				
		}
function load(){
	document.counter.mcode.focus();

		 }
		 
function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
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
		
</script>
		

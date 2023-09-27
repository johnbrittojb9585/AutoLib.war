<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Group.GroupBean"/>
<head>
<title></title>
</head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body background="/AutoLib/soft.jpg"> <!--onload="opt(1)"   If is Enable then when search a group it is not showing data in appropriate text boxes --> 
<form name=Grp method=post action=./GroupServlet >
<br><br><br>

<h2>Group&nbsp;Master</h2>
<center><table align="center" class="contentTable" width="65%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Group&nbsp;Id<td><input type=text name=GCode size=7 onKeyUp="return grp_code_val();" >&nbsp;<input type=button Class="submitButton" name=find Value="Find" onclick=FindGroup()>
<td Class="addedit">
	<input type="radio" value="V1"  name="ra1" onclick="opt(1)">General
    <input type="radio" value="V2"  name="ra2" onclick="opt(2)">Specified
	<input type="radio" value="V3"  name="ra3" onclick="opt(3)">General&amp;Specified
<tr><td Class="addedit">Group&nbsp;Name<td  colspan="2" >
<input type=text name=GName size=62>
</table><center>
   <center><b>Eligibility</b></center>
<table>
<tr><td Class="addedit">General<td ><input type=text name=Geligible size=7 value="0" onKeyUp="return grp_code_val1();">
  <td Class="addedit">Period<td ><input type=text name=Gperiod size=7 value="0" onKeyUp="return grp_code_val2();">
  <td Class="addedit">Renew<td ><input type=text name=GRperiod size=7 value="0" onKeyUp="return grp_code_val21();">
    
  <td Class="addedit">Book<td ><input type=text name=Beligible size=7 value="0" onKeyUp="return grp_code_val3();">
  <td Class="addedit">Period<td ><input type=text name=Bperiod size=7 value="0" onKeyUp="return grp_code_val4();">
  <td Class="addedit">Renew<td ><input type=text name=BRperiod size=7 value="0" onKeyUp="return grp_code_val22();">
</td></tr>
  
<tr><td Class="addedit">Book&nbsp;Bank<td ><input type=text name=BBeligible size=7 value="0" onKeyUp="return grp_code_val5();">
  <td Class="addedit">Period<td ><input type=text name=BBperiod size=7 value="0" onKeyUp="return grp_code_val6();">
  <td Class="addedit">Renew<td ><input type=text name=BBRperiod size=7 value="0" onKeyUp="return grp_code_val23();">
  
   <td Class="addedit">Back&nbsp;Volume<td ><input type=text name=BVeligible size=7 value="0" onKeyUp="return grp_code_val7();">
   <td Class="addedit">Period<td ><input type=text name=BVperiod size=7 value="0" onKeyUp="return grp_code_val8();">
   <td Class="addedit">Renew<td ><input type=text name=BVRperiod size=7 value="0" onKeyUp="return grp_code_val24();">
</td></tr>
   
<tr><td Class="addedit">Non Book<td ><input type=text name=NBeligible size=7 value="0" onKeyUp="return grp_code_val9();">
   <td Class="addedit">Period<td ><input type=text name=NBperiod size=7 value="0" onKeyUp="return grp_code_val10();">
   <td Class="addedit">Renew<td ><input type=text name=NBRperiod size=7 value="0" onKeyUp="return grp_code_val25();">
   
   <td Class="addedit">Journal Issue<td ><input type=text name=JLeligible size=7 value="0" onKeyUp="return grp_code_val11();">
   <td Class="addedit">Period<td ><input type=text name=JLperiod size=7 value="0" onKeyUp="return grp_code_val12();">
   <td Class="addedit">Renew<td ><input type=text name=JRperiod size=7 value="0" onKeyUp="return grp_code_val26();">
</td></tr>
   
<tr><td Class="addedit">Standard<td ><input type=text name=Seligible size=7 value="0" onKeyUp="return grp_code_val13();">
  <td Class="addedit">Period<td ><input type=text name=SLperiod size=7 value="0" onKeyUp="return grp_code_val14();">
  <td Class="addedit">Renew<td ><input type=text name=SRperiod size=7 value="0" onKeyUp="return grp_code_val27();">
  
  <td Class="addedit">Report<td ><input type=text name=Religible size=7 value="0" onKeyUp="return grp_code_val15();">
  <td Class="addedit">Period<td ><input type=text name=RLperiod size=7 value="0" onKeyUp="return grp_code_val16();">
  <td Class="addedit">Renew<td ><input type=text name=RRperiod size=7 value="0" onKeyUp="return grp_code_val28();">
</td></tr>

<tr><td Class="addedit">Proceedings<td ><input type=text name=Peligible size=7 value="0" onKeyUp="return grp_code_val17();">
    <td Class="addedit">Period<td ><input type=text name=PLperiod size=7 value="0" onKeyUp="return grp_code_val18();">
    <td Class="addedit">Renew<td ><input type=text name=PRperiod size=7 value="0" onKeyUp="return grp_code_val29();">

    <td Class="addedit">Thesis<td ><input type=text name=Teligible size=7 value="0" onKeyUp="return grp_code_val19();">
    <td Class="addedit">Period<td ><input type=text name=TLperiod size=7 value="0" onKeyUp="return grp_code_val20();">
    <td Class="addedit">Renew<td ><input type=text name=TRperiod size=7 value="0" onKeyUp="return grp_code_val30();">
</td></tr>

<tr><td Class="addedit">
Remarks<td colspan=5 >
    <input type=text name=Remarks size=44></td>
    <td Class="addedit">
    Renewal Times</td><td colspan=2>
    <input  type=text name=Renewal size=7  value="0" onKeyUp="return group_code_val();">
    <td Class="addedit" colspan=2>
    Reserve Book Max</td><td>
    <input  type=text name=BReserve size=7  value="0" onKeyUp="return breserve_code_val();">
    </td>    
    </tr>
    
    <tr>
    <td colspan="8" ><center>
     <input type=button name=New Value=New Class="submitButton" onclick=new_no()>
	<input type=button name=Save value=Save Class="submitButton" onclick=SaveRec()>
	<input type=button name=Delete Value=Delete Class="submitButton" onclick=DelRec()>
	<input type=submit name=search Value=Search Class="submitButton" onclick=SearchRec()>
	<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=new_no()>
     <input type=hidden name=flag></center>
 </td>
</tr>
</table></center></td></table></center>
</form>
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newGroup")){
 %>
 <script language="JavaScript">
document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.focus();
document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;
document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;
document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;
document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;
document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;
document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;
document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;
document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;
document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;
document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
document.Grp.ra1.checked=true;
</script><%
}


if(valid.equals("searchGroup")){
 %>
  <script language="JavaScript">
document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.value="<%=bean.getName()%>";

document.Grp.Geligible.value="<%=bean.getGeli()%>";
document.Grp.Gperiod.value="<%=bean.getGlper()%>";
document.Grp.GRperiod.value="<%=bean.getGrper()%>";

document.Grp.Beligible.value="<%=bean.getBeli()%>";
document.Grp.Bperiod.value="<%=bean.getBlper()%>";
document.Grp.BRperiod.value="<%=bean.getBrper()%>";

document.Grp.BBeligible.value="<%=bean.getBbeli()%>";
document.Grp.BBperiod.value="<%=bean.getBblper()%>";
document.Grp.BBRperiod.value="<%=bean.getBbrper()%>";

document.Grp.BVeligible.value="<%=bean.getBveli()%>";
document.Grp.BVperiod.value="<%=bean.getBvlper()%>";
document.Grp.BVRperiod.value="<%=bean.getBvrper()%>";

document.Grp.NBeligible.value="<%=bean.getNbeli()%>";
document.Grp.NBperiod.value="<%=bean.getNblper()%>";
document.Grp.NBRperiod.value="<%=bean.getNbrper()%>";

document.Grp.JLeligible.value="<%=bean.getJeli()%>";
document.Grp.JLperiod.value="<%=bean.getJlper()%>";
document.Grp.JRperiod.value="<%=bean.getJrper()%>";

document.Grp.Seligible.value="<%=bean.getSeli()%>";
document.Grp.SLperiod.value="<%=bean.getSlper()%>";
document.Grp.SRperiod.value="<%=bean.getSrper()%>";

document.Grp.Religible.value="<%=bean.getReli()%>";
document.Grp.RLperiod.value="<%=bean.getRlper()%>";
document.Grp.RRperiod.value="<%=bean.getRrper()%>";

document.Grp.Peligible.value="<%=bean.getPeli()%>";
document.Grp.PLperiod.value="<%=bean.getPlper()%>";
document.Grp.PRperiod.value="<%=bean.getPrper()%>";

document.Grp.Teligible.value="<%=bean.getTeli()%>";
document.Grp.TLperiod.value="<%=bean.getTlper()%>";
document.Grp.TRperiod.value="<%=bean.getTrper()%>";

document.Grp.Remarks.value="<%=bean.getRemarks()%>";
document.Grp.Renewal.value="<%=bean.getRenewal()%>";
document.Grp.BReserve.value="<%=bean.getReserve()%>";
var s;
s="<%=bean.getStatus()%>";

if(s==document.Grp.ra1.value)
{

document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
}
if(s==document.Grp.ra2.value)
{

document.Grp.ra2.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=true;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=false;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=false;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=false;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=false;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=false;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=false;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=false;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=false;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=false;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}
if(s==document.Grp.ra3.value)
{
document.Grp.ra3.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}

</script>
<%
}
if(valid.equals("FailGroup")){ %>
            <script language="JavaScript">
			alert("Record Not Found");
			document.Grp.flag.value="new";
			document.Grp.submit();
		   	</script><%
			}
	if(valid.equals("UpdateGroup")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.Grp.flag.value="new";
			document.Grp.submit();
		   	</script><%
			}
	if(valid.equals("DefaultGroup")){
		%>             <script language="JavaScript">
					 alert("Default  Group cannot be Deleted!");
					 document.Grp.flag.value="new";
					 document.Grp.submit();
				     </script>		
					<%
					}
	
if(valid.equals("CodeCompareGroup")){
%>
<script>
document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.value="<%=bean.getName()%>";

document.Grp.Geligible.value="<%=bean.getGeli()%>";
document.Grp.Gperiod.value="<%=bean.getGlper()%>";
document.Grp.GRperiod.value="<%=bean.getGrper()%>";

document.Grp.Beligible.value="<%=bean.getBeli()%>";
document.Grp.Bperiod.value="<%=bean.getBlper()%>";
document.Grp.BRperiod.value="<%=bean.getBrper()%>";

document.Grp.BBeligible.value="<%=bean.getBbeli()%>";
document.Grp.BBperiod.value="<%=bean.getBblper()%>";
document.Grp.BBRperiod.value="<%=bean.getBbrper()%>";

document.Grp.BVeligible.value="<%=bean.getBveli()%>";
document.Grp.BVperiod.value="<%=bean.getBvlper()%>";
document.Grp.BVRperiod.value="<%=bean.getBvrper()%>";

document.Grp.NBeligible.value="<%=bean.getNbeli()%>";
document.Grp.NBperiod.value="<%=bean.getNblper()%>";
document.Grp.NBRperiod.value="<%=bean.getNbrper()%>";

document.Grp.JLeligible.value="<%=bean.getJeli()%>";
document.Grp.JLperiod.value="<%=bean.getJlper()%>";
document.Grp.JRperiod.value="<%=bean.getJrper()%>";

document.Grp.Seligible.value="<%=bean.getSeli()%>";
document.Grp.SLperiod.value="<%=bean.getSlper()%>";
document.Grp.SRperiod.value="<%=bean.getSrper()%>";

document.Grp.Religible.value="<%=bean.getReli()%>";
document.Grp.RLperiod.value="<%=bean.getRlper()%>";
document.Grp.RRperiod.value="<%=bean.getRrper()%>";

document.Grp.Peligible.value="<%=bean.getPeli()%>";
document.Grp.PLperiod.value="<%=bean.getPlper()%>";
document.Grp.PRperiod.value="<%=bean.getPrper()%>";

document.Grp.Teligible.value="<%=bean.getTeli()%>";
document.Grp.TLperiod.value="<%=bean.getTlper()%>";
document.Grp.TRperiod.value="<%=bean.getTrper()%>";

document.Grp.Remarks.value="<%=bean.getRemarks()%>";
document.Grp.Renewal.value="<%=bean.getRenewal()%>";
document.Grp.BReserve.value="<%=bean.getReserve()%>";

 var s;
s="<%=bean.getStatus()%>";

if(s==document.Grp.ra1.value)
{

document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
}
if(s==document.Grp.ra2.value)
{

document.Grp.ra2.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=true;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=false;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=false;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=false;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=false;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=false;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=false;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=false;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=false;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=false;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}
if(s==document.Grp.ra3.value)
{
document.Grp.ra3.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}


			alert("Group Name Already Exists !");
			</script><%
			}
if(valid.equals("SaveGroup")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.Grp.flag.value="new";
			document.Grp.submit();
		     </script>
			<%
			}
if(valid.equals("ReferredGroup")){
%>            <script>alert("You can't delete since the Grp has been referred in other masters");
			</script>
			<%
			}

			if(valid.equals("DeleteGroup")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.Grp.flag.value="new";
			document.Grp.submit();
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">

document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.value="<%=bean.getName()%>";

document.Grp.Geligible.value="<%=bean.getGeli()%>";
document.Grp.Gperiod.value="<%=bean.getGlper()%>";
document.Grp.GRperiod.value="<%=bean.getGrper()%>";

document.Grp.Beligible.value="<%=bean.getBeli()%>";
document.Grp.Bperiod.value="<%=bean.getBlper()%>";
document.Grp.BRperiod.value="<%=bean.getBrper()%>";

document.Grp.BBeligible.value="<%=bean.getBbeli()%>";
document.Grp.BBperiod.value="<%=bean.getBblper()%>";
document.Grp.BBRperiod.value="<%=bean.getBbrper()%>";

document.Grp.BVeligible.value="<%=bean.getBveli()%>";
document.Grp.BVperiod.value="<%=bean.getBvlper()%>";
document.Grp.BVRperiod.value="<%=bean.getBvrper()%>";

document.Grp.NBeligible.value="<%=bean.getNbeli()%>";
document.Grp.NBperiod.value="<%=bean.getNblper()%>";
document.Grp.NBRperiod.value="<%=bean.getNbrper()%>";

document.Grp.JLeligible.value="<%=bean.getJeli()%>";
document.Grp.JLperiod.value="<%=bean.getJlper()%>";
document.Grp.JRperiod.value="<%=bean.getJrper()%>";

document.Grp.Seligible.value="<%=bean.getSeli()%>";
document.Grp.SLperiod.value="<%=bean.getSlper()%>";
document.Grp.SRperiod.value="<%=bean.getSrper()%>";

document.Grp.Religible.value="<%=bean.getReli()%>";
document.Grp.RLperiod.value="<%=bean.getRlper()%>";
document.Grp.RRperiod.value="<%=bean.getRrper()%>";

document.Grp.Peligible.value="<%=bean.getPeli()%>";
document.Grp.PLperiod.value="<%=bean.getPlper()%>";
document.Grp.PRperiod.value="<%=bean.getPrper()%>";

document.Grp.Teligible.value="<%=bean.getTeli()%>";
document.Grp.TLperiod.value="<%=bean.getTlper()%>";
document.Grp.TRperiod.value="<%=bean.getTrper()%>";

document.Grp.Remarks.value="<%=bean.getRemarks()%>";
document.Grp.Renewal.value="<%=bean.getRenewal()%>";
document.Grp.BReserve.value="<%=bean.getReserve()%>";

var s;
s="<%=bean.getStatus()%>";

if(s==document.Grp.ra1.value)
{

document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
}
if(s==document.Grp.ra2.value)
{

document.Grp.ra2.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=true;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=false;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=false;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=false;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=false;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=false;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=false;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=false;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=false;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=false;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}
if(s==document.Grp.ra3.value)
{
document.Grp.ra3.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}


			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.Grp.flag.value="Confirmdete";
		   	document.Grp.submit();

			}
			</script>
			<%
			}

			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.value="<%=bean.getName()%>";

document.Grp.Geligible.value="<%=bean.getGeli()%>";
document.Grp.Gperiod.value="<%=bean.getGlper()%>";
document.Grp.GRperiod.value="<%=bean.getGrper()%>";

document.Grp.Beligible.value="<%=bean.getBeli()%>";
document.Grp.Bperiod.value="<%=bean.getBlper()%>";
document.Grp.BRperiod.value="<%=bean.getBrper()%>";

document.Grp.BBeligible.value="<%=bean.getBbeli()%>";
document.Grp.BBperiod.value="<%=bean.getBblper()%>";
document.Grp.BBRperiod.value="<%=bean.getBbrper()%>";

document.Grp.BVeligible.value="<%=bean.getBveli()%>";
document.Grp.BVperiod.value="<%=bean.getBvlper()%>";
document.Grp.BVRperiod.value="<%=bean.getBvrper()%>";

document.Grp.NBeligible.value="<%=bean.getNbeli()%>";
document.Grp.NBperiod.value="<%=bean.getNblper()%>";
document.Grp.NBRperiod.value="<%=bean.getNbrper()%>";

document.Grp.JLeligible.value="<%=bean.getJeli()%>";
document.Grp.JLperiod.value="<%=bean.getJlper()%>";
document.Grp.JRperiod.value="<%=bean.getJrper()%>";

document.Grp.Seligible.value="<%=bean.getSeli()%>";
document.Grp.SLperiod.value="<%=bean.getSlper()%>";
document.Grp.SRperiod.value="<%=bean.getSrper()%>";

document.Grp.Religible.value="<%=bean.getReli()%>";
document.Grp.RLperiod.value="<%=bean.getRlper()%>";
document.Grp.RRperiod.value="<%=bean.getRrper()%>";

document.Grp.Peligible.value="<%=bean.getPeli()%>";
document.Grp.PLperiod.value="<%=bean.getPlper()%>";
document.Grp.PRperiod.value="<%=bean.getPrper()%>";

document.Grp.Teligible.value="<%=bean.getTeli()%>";
document.Grp.TLperiod.value="<%=bean.getTlper()%>";
document.Grp.TRperiod.value="<%=bean.getTrper()%>";

document.Grp.Remarks.value="<%=bean.getRemarks()%>";
document.Grp.Renewal.value="<%=bean.getRenewal()%>";
document.Grp.BReserve.value="<%=bean.getReserve()%>";

var s;
s="<%=bean.getStatus()%>";

if(s==document.Grp.ra1.value)
{

document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
}
if(s==document.Grp.ra2.value)
{

document.Grp.ra2.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=true;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=false;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=false;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=false;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=false;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=false;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=false;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=false;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=false;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=false;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}
if(s==document.Grp.ra3.value)
{
document.Grp.ra3.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}


			    alert("Record Not Present!!!");
			    document.Grp.flag.value="new";
			    document.Grp.submit();
			</script>
			<%
			}


if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
document.Grp.GCode.value="<%=bean.getCode()%>";
document.Grp.GName.value="<%=bean.getName()%>";

document.Grp.Geligible.value="<%=bean.getGeli()%>";
document.Grp.Gperiod.value="<%=bean.getGlper()%>";
document.Grp.GRperiod.value="<%=bean.getGrper()%>";

document.Grp.Beligible.value="<%=bean.getBeli()%>";
document.Grp.Bperiod.value="<%=bean.getBlper()%>";
document.Grp.BRperiod.value="<%=bean.getBrper()%>";

document.Grp.BBeligible.value="<%=bean.getBbeli()%>";
document.Grp.BBperiod.value="<%=bean.getBblper()%>";
document.Grp.BBRperiod.value="<%=bean.getBbrper()%>";

document.Grp.BVeligible.value="<%=bean.getBveli()%>";
document.Grp.BVperiod.value="<%=bean.getBvlper()%>";
document.Grp.BVRperiod.value="<%=bean.getBvrper()%>";

document.Grp.NBeligible.value="<%=bean.getNbeli()%>";
document.Grp.NBperiod.value="<%=bean.getNblper()%>";
document.Grp.NBRperiod.value="<%=bean.getNbrper()%>";

document.Grp.JLeligible.value="<%=bean.getJeli()%>";
document.Grp.JLperiod.value="<%=bean.getJlper()%>";
document.Grp.JRperiod.value="<%=bean.getJrper()%>";

document.Grp.Seligible.value="<%=bean.getSeli()%>";
document.Grp.SLperiod.value="<%=bean.getSlper()%>";
document.Grp.SRperiod.value="<%=bean.getSrper()%>";

document.Grp.Religible.value="<%=bean.getReli()%>";
document.Grp.RLperiod.value="<%=bean.getRlper()%>";
document.Grp.RRperiod.value="<%=bean.getRrper()%>";

document.Grp.Peligible.value="<%=bean.getPeli()%>";
document.Grp.PLperiod.value="<%=bean.getPlper()%>";
document.Grp.PRperiod.value="<%=bean.getPrper()%>";

document.Grp.Teligible.value="<%=bean.getTeli()%>";
document.Grp.TLperiod.value="<%=bean.getTlper()%>";
document.Grp.TRperiod.value="<%=bean.getTrper()%>";

document.Grp.Remarks.value="<%=bean.getRemarks()%>";
document.Grp.Renewal.value="<%=bean.getRenewal()%>";
document.Grp.BReserve.value="<%=bean.getReserve()%>";

var s;
s="<%=bean.getStatus()%>";

if(s==document.Grp.ra1.value)
{

document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=false;
document.Grp.GRperiod.readOnly=false;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=true;
document.Grp.BBRperiod.readOnly=true;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=true;
document.Grp.NBRperiod.readOnly=true;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=true;
document.Grp.SRperiod.readOnly=true;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=true;
document.Grp.PRperiod.readOnly=true;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=true;
document.Grp.BRperiod.readOnly=true;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=true;
document.Grp.BVRperiod.readOnly=true;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=true;
document.Grp.JRperiod.readOnly=true;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=true;
document.Grp.RRperiod.readOnly=true;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=true;
document.Grp.TRperiod.readOnly=true;
}
if(s==document.Grp.ra2.value)
{

document.Grp.ra2.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra3.checked=false;

document.Grp.Geligible.readOnly=true;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=false;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=false;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=false;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=false;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=false;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=false;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=false;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=false;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=false;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}
if(s==document.Grp.ra3.value)
{
document.Grp.ra3.checked=true;
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;

document.Grp.Geligible.readOnly=false;
document.Grp.Gperiod.readOnly=true;
document.Grp.GRperiod.readOnly=true;

document.Grp.BBeligible.readOnly=true;
document.Grp.BBperiod.readOnly=false;
document.Grp.BBRperiod.readOnly=false;

document.Grp.NBeligible.readOnly=true;
document.Grp.NBperiod.readOnly=false;
document.Grp.NBRperiod.readOnly=false;

document.Grp.Seligible.readOnly=true;
document.Grp.SLperiod.readOnly=false;
document.Grp.SRperiod.readOnly=false;

document.Grp.Peligible.readOnly=true;
document.Grp.PLperiod.readOnly=false;
document.Grp.PRperiod.readOnly=false;

document.Grp.Beligible.readOnly=true;
document.Grp.Bperiod.readOnly=false;
document.Grp.BRperiod.readOnly=false;

document.Grp.BVeligible.readOnly=true;
document.Grp.BVperiod.readOnly=false;
document.Grp.BVRperiod.readOnly=false;

document.Grp.JLeligible.readOnly=true;
document.Grp.JLperiod.readOnly=false;
document.Grp.JRperiod.readOnly=false;

document.Grp.Religible.readOnly=true;
document.Grp.RLperiod.readOnly=false;
document.Grp.RRperiod.readOnly=false;

document.Grp.Teligible.readOnly=true;
document.Grp.TLperiod.readOnly=false;
document.Grp.TRperiod.readOnly=false;
}

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.Grp.flag.value="update";
		         	document.Grp.submit();

		            }
			    </script>

			<%
			}



}

}
%>

<script language="javaScript">


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function opt(val){
if((val==2)||(val==3)||(val==1)){
for(var i=1;i<document.Grp.elements.length;i++){

	if(document.Grp.elements[i].type=="text" && document.Grp.elements[i].name!="GName" && document.Grp.elements[i].name!="GCode" && document.Grp.elements[i].name!="Remarks" && document.Grp.elements[i].name!="Renewal" && document.Grp.elements[i].name!="BReserve")
	document.Grp.elements[i].value=0;
	}
}
if(val==1){
document.Grp.ra1.checked=true;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=false;
	for(var i=1;i<document.Grp.elements.length;i++){
	if(document.Grp.elements[i].type=="text" && document.Grp.elements[i].name!="Geligible" && document.Grp.elements[i].name!="Gperiod" && document.Grp.elements[i].name!="GRperiod" && document.Grp.elements[i].name!="GName" && document.Grp.elements[i].name!="GCode" && document.Grp.elements[i].name!="Remarks" && document.Grp.elements[i].name!="Renewal" && document.Grp.elements[i].name!="BReserve")

	document.Grp.elements[i].readOnly=true;
	else
	document.Grp.elements[i].readOnly=false;
	}
}
if(val==2){
document.Grp.ra1.checked=false;
document.Grp.ra2.checked=true;
document.Grp.ra3.checked=false;
	for(var i=1;i<document.Grp.elements.length;i++){
	if(document.Grp.elements[i].type=="text")
	if(document.Grp.elements[i].name=="Geligible" || document.Grp.elements[i].name=="Gperiod" || document.Grp.elements[i].name=="GRperiod")
	document.Grp.elements[i].readOnly=true;
	else
	document.Grp.elements[i].readOnly=false;
	}
}
if(val==3){

document.Grp.ra1.checked=false;
document.Grp.ra2.checked=false;
document.Grp.ra3.checked=true;
document.Grp.Geligible.readOnly=false;
	for(var i=1;i<document.Grp.elements.length;i++){
	if(document.Grp.elements[i].type=="text" && document.Grp.elements[i].name!="Geligible" && document.Grp.elements[i].name!="GName" && document.Grp.elements[i].name!="GCode" && document.Grp.elements[i].name!="Remarks" && document.Grp.elements[i].name!="Renewal" && document.Grp.elements[i].name!="BReserve")
	if(document.Grp.elements[i].name.indexOf("period")==-1 || document.Grp.elements[i].name=="Gperiod" || document.Grp.elements[i].name=="GRperiod")
		document.Grp.elements[i].readOnly=true;
    else
	document.Grp.elements[i].readOnly=false;
    }
}
}


function new_no(){
document.Grp.method="post";
document.Grp.flag.value="new";
//document.Grp.action="index.jsp";
document.Grp.submit();
}


function SaveRec(){
if((document.Grp.ra1.checked) || (document.Grp.ra2.checked) || (document.Grp.ra3.checked))
{
document.Grp.method="post";
		if(chk()){
		if(check()){
				if(validate()){
			document.Grp.flag.value="save";
			document.Grp.submit();
			}
			}
			else
			{
			alert("Insufficient Data");
			}
		}
	else
	{
	alert("Insufficient Data");
	document.Grp.flag.value="new";
	document.Grp.submit();
	}
}else{
alert("Choose anyone type of Category");
}
}

function SearchRec(){
document.Grp.method="post";
var no=document.Grp.GCode.value;
		if (no==""){
				document.Grp.GCode.focus();
				alert("Insufficent Data");
				document.Grp.flag.value="new";
				document.Grp.submit();
				return false;
				}
			else{
				 document.Grp.flag.value="search";
			     	 document.Grp.submit();

				 }
}
function DelRec(){
document.Grp.method="post";
document.Grp.flag.value="delete";
		if (document.Grp.GCode.value==""){
				alert("Insufficent Data");
				return false;
				}
			else{
						document.Grp.submit();

				}
}
function check(){
 if (document.Grp.GName.value==""){
            //alert("Group Name is not Empty !");
			document.Grp.GName.focus();
			return false;
		     }
		     else
		     {
		     return true;
		     }
}
function chk(){
var i
i=0;
		if (document.Grp.GCode.value==""){
			document.Grp.GCode.focus();
			return false;
		    }
		    else
			{
	                 for(i=0;i<document.Grp.GName.value.length;i++)   // Error occur here by RK. find on 13-08-2013 (Pending)
 	                      {
 	                       if(document.Grp.GName.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.Grp.GName.focus();
		                   	document.Grp.GName.value="";
			                return false;
		                      }
		                   else if (document.Grp.GCode.value==""){
		                 	document.Grp.GCode.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }


}
function validate(){

		    if (document.Grp.Geligible.value==""){
		    alert(" Please Enter The General Eligible ");
			document.Grp.Geligible.focus();
			return false;
		    }
		    else if (document.Grp.Gperiod.value==""){
		    alert(" Please Enter The General Period ");
			document.Grp.Gperiod.focus();
			return false;
		    }
		    else if (document.Grp.GRperiod.value==""){
		    alert(" Please Enter The General Renew Period ");
			document.Grp.GRperiod.focus();
			return false;
		    }
		    else if (document.Grp.Beligible.value==""){
		    alert(" Please Enter The Book Eligible ");
			document.Grp.Beligible.focus();
			return false;
		    }
		    else if (document.Grp.Bperiod.value==""){
		    alert(" Please Enter The Book Period ");
			document.Grp.Bperiod.focus();
			return false;
		    }
		    else if (document.Grp.BRperiod.value==""){
		    alert(" Please Enter The Book Renew Period ");
			document.Grp.BRperiod.focus();
			return false;
		    }
		    else if (document.Grp.BBeligible.value==""){
		    alert(" Please Enter The Book Bank Eligible ");
			document.Grp.BBeligible.focus();
			return false;
		    }
		    else if (document.Grp.BBperiod.value==""){
		    alert(" Please Enter The Book Bank Period ");
			document.Grp.BBperiod.focus();
			return false;
		    }
		    else if (document.Grp.BBRperiod.value==""){
		    alert(" Please Enter The Book Bank Renew Period ");
			document.Grp.BBRperiod.focus();
			return false;
		    }
		    else if (document.Grp.BVeligible.value==""){
		    alert(" Please Enter The Back Volume Eligible ");
			document.Grp.Geligible.focus();
			return false;
		    }
		    else if (document.Grp.BVperiod.value==""){
		    alert(" Please Enter The Back Volume Period ");
			document.Grp.BVperiod.focus();
			return false;
		    }
		    else if (document.Grp.BVRperiod.value==""){
		    alert(" Please Enter The Back Volume Renew Period ");
			document.Grp.BVRperiod.focus();
			return false;
		    }
		    else if (document.Grp.NBeligible.value==""){
		    alert(" Please Enter The Non Book Eligible ");
			document.Grp.NBeligible.focus();
			return false;
		    }
		    else if (document.Grp.NBperiod.value==""){
		    alert(" Please Enter The Non Book Period ");
			document.Grp.NBperiod.focus();
			return false;
		    }
		    else if (document.Grp.NBRperiod.value==""){
		    alert(" Please Enter The Non Book Renew Period ");
			document.Grp.NBRperiod.focus();
			return false;
		    }
		    else if (document.Grp.JLeligible.value==""){
		    alert(" Please Enter The Journal Eligible ");
			document.Grp.JLeligible.focus();
			return false;
		    }
		    else if (document.Grp.JLperiod.value==""){
		    alert(" Please Enter The Journal Period ");
			document.Grp.JLperiod.focus();
			return false;
		    }
		    else if (document.Grp.JRperiod.value==""){
		    alert(" Please Enter The Journal Renew Period ");
			document.Grp.JRperiod.focus();
			return false;
		    }
		    else if (document.Grp.Seligible.value==""){
		    alert(" Please Enter The Standard Eligible ");
			document.Grp.Seligible.focus();
			return false;
		    }
		    else if (document.Grp.SLperiod.value==""){
		    alert(" Please Enter The Standard Period ");
			document.Grp.SLperiod.focus();
			return false;
		    }
		    else if (document.Grp.SRperiod.value==""){
		    alert(" Please Enter The Standard Renew Period ");
			document.Grp.SRperiod.focus();
			return false;
		    }
		    else if (document.Grp.Religible.value==""){
		    alert(" Please Enter The Report Eligible ");
			document.Grp.Religible.focus();
			return false;
		    }
		    else if (document.Grp.RLperiod.value==""){
		    alert(" Please Enter The Report Period ");
			document.Grp.RLperiod.focus();
			return false;
		    }
		    else if (document.Grp.RRperiod.value==""){
		    alert(" Please Enter The Report Renew Period ");
			document.Grp.RRperiod.focus();
			return false;
		    }
		    else if (document.Grp.Peligible.value==""){
		    alert(" Please Enter The Proceeding Eligible ");
			document.Grp.Peligible.focus();
			return false;
		    }
		    else if (document.Grp.PLperiod.value==""){
		    alert(" Please Enter The Proceeding Period ");
			document.Grp.PLperiod.focus();
			return false;
		    }
		    else if (document.Grp.PRperiod.value==""){
		    alert(" Please Enter The Proceeding Renew Period ");
			document.Grp.PRperiod.focus();
			return false;
		    }
		    else if (document.Grp.Teligible.value==""){
		    alert(" Please Enter The Thesis Eligible ");
			document.Grp.Teligible.focus();
			return false;
		    }
		    else if (document.Grp.TLperiod.value==""){
		    alert(" Please Enter The Thesis Period ");
			document.Grp.TLperiod.focus();
			return false;
		    }
		    else if (document.Grp.TRperiod.value==""){
		    alert(" Please Enter The Thesis Renew Period ");
			document.Grp.TRperiod.focus();
			return false;
		    }
        else{
		return true;
		}
}
function FindGroup()
{
if(document.Grp.ra1.checked==true)
winpopup=window.open("search.jsp?flag=Gen","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
if(document.Grp.ra2.checked==true)
winpopup=window.open("search.jsp?flag=Spe","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
if(document.Grp.ra3.checked==true)
winpopup=window.open("search.jsp?flag=Gs","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}


function group_code_val(){
if((isNaN(document.Grp.Renewal.value))||(document.Grp.Renewal.value == ' ')) {
document.Grp.Renewal.select();
document.Grp.Renewal.value="";
}
}

function breserve_code_val(){
if((isNaN(document.Grp.BReserve.value))||(document.Grp.BReserve.value == ' ')) {
document.Grp.BReserve.select();
document.Grp.BReserve.value="";
}
}

function grp_code_val(){
if((isNaN(document.Grp.GCode.value))||(document.Grp.GCode.value == ' ')) {
document.Grp.GCode.select();
document.Grp.GCode.value="";
}
}

function grp_code_val1(){
if((isNaN(document.Grp.Geligible.value))||(document.Grp.Geligible.value == ' ')) {
document.Grp.Geligible.select();
document.Grp.Geligible.value="";
}

}
function grp_code_val2(){
if((isNaN(document.Grp.Gperiod.value))||(document.Grp.Gperiod.value == ' ')) {
document.Grp.Gperiod.select();
document.Grp.Gperiod.value="";
}

}
function grp_code_val3(){
if((isNaN(document.Grp.Beligible.value))||(document.Grp.Beligible.value == ' ')) {
document.Grp.Beligible.select();
document.Grp.Beligible.value="";
}

}
function grp_code_val4(){
if((isNaN(document.Grp.Bperiod.value))||(document.Grp.Bperiod.value == ' ')) {
document.Grp.Bperiod.select();
document.Grp.Bperiod.value="";
}

}
function grp_code_val5(){
if((isNaN(document.Grp.BBeligible.value))||(document.Grp.BBeligible.value == ' ')) {
document.Grp.BBeligible.select();
document.Grp.BBeligible.value="";
}

}
function grp_code_val6(){
if((isNaN(document.Grp.BBperiod.value))||(document.Grp.BBperiod.value == ' ')) {
document.Grp.BBperiod.select();
document.Grp.BBperiod.value="";
}

}
function grp_code_val7(){
if((isNaN(document.Grp.BVeligible.value))||(document.Grp.BVeligible.value == ' ')) {
document.Grp.BVeligible.select();
document.Grp.BVeligible.value="";
}

}
function grp_code_val8(){
if((isNaN(document.Grp.BVperiod.value))||(document.Grp.BVperiod.value == ' ')) {
document.Grp.BVperiod.select();
document.Grp.BVperiod.value="";
}

}
function grp_code_val9(){
if((isNaN(document.Grp.NBeligible.value))||(document.Grp.NBeligible.value == ' ')) {
document.Grp.NBeligible.select();
document.Grp.NBeligible.value="";
}

}
function grp_code_val10(){
if((isNaN(document.Grp.NBperiod.value))||(document.Grp.NBperiod.value == ' ')) {
document.Grp.NBperiod.select();
document.Grp.NBperiod.value="";
}

}
function grp_code_val11(){
if((isNaN(document.Grp.JLeligible.value))||(document.Grp.JLeligible.value == ' ')) {
document.Grp.JLeligible.select();
document.Grp.JLeligible.value="";
}

}
function grp_code_val12(){
if((isNaN(document.Grp.JLperiod.value))||(document.Grp.JLperiod.value == ' ')) {
document.Grp.JLperiod.select();
document.Grp.JLperiod.value="";
}

}
function grp_code_val13(){
if((isNaN(document.Grp.Seligible.value))||(document.Grp.Seligible.value == ' ')) {
document.Grp.Seligible.select();
document.Grp.Seligible.value="";
}

}
function grp_code_val14(){
if((isNaN(document.Grp.SLperiod.value))||(document.Grp.SLperiod.value == ' ')) {
document.Grp.SLperiod.select();
document.Grp.SLperiod.value="";
}

}
function grp_code_val15(){
if((isNaN(document.Grp.Religible.value))||(document.Grp.Religible.value == ' ')) {
document.Grp.Religible.select();
document.Grp.Religible.value="";
}

}
function grp_code_val16(){
if((isNaN(document.Grp.RLperiod.value))||(document.Grp.RLperiod.value == ' ')) {
document.Grp.RLperiod.select();
document.Grp.RLperiod.value="";
}

}
function grp_code_val17(){
if((isNaN(document.Grp.Peligible.value))||(document.Grp.Peligible.value == ' ')) {
document.Grp.Peligible.select();
document.Grp.Peligible.value="";
}

}
function grp_code_val18(){
if((isNaN(document.Grp.PLperiod.value))||(document.Grp.PLperiod.value == ' ')) {
document.Grp.PLperiod.select();
document.Grp.PLperiod.value="";
}

}
function grp_code_val19(){
if((isNaN(document.Grp.Teligible.value))||(document.Grp.Teligible.value == ' ')) {
document.Grp.Teligible.select();
document.Grp.Teligible.value="";
}

}
function grp_code_val20(){
if((isNaN(document.Grp.TLperiod.value))||(document.Grp.TLperiod.value == ' ')) {
document.Grp.TLperiod.select();
document.Grp.TLperiod.value="";
}

}
function grp_code_val21(){
if((isNaN(document.Grp.GRperiod.value))||(document.Grp.GRperiod.value == ' ')) {
document.Grp.GRperiod.select();
document.Grp.GRperiod.value="";
}

}
function grp_code_val22(){
if((isNaN(document.Grp.BRperiod.value))||(document.Grp.BRperiod.value == ' ')) {
document.Grp.BRperiod.select();
document.Grp.BRperiod.value="";
}

}
function grp_code_val23(){
if((isNaN(document.Grp.BBRperiod.value))||(document.Grp.BBRperiod.value == ' ')) {
document.Grp.BBRperiod.select();
document.Grp.BBRperiod.value="";
}

}
function grp_code_val24(){
if((isNaN(document.Grp.BVRperiod.value))||(document.Grp.BVRperiod.value == ' ')) {
document.Grp.BVRperiod.select();
document.Grp.BVRperiod.value="";
}

}
function grp_code_val25(){
if((isNaN(document.Grp.NBRperiod.value))||(document.Grp.NBRperiod.value == ' ')) {
document.Grp.NBRperiod.select();
document.Grp.NBRperiod.value="";
}

}
function grp_code_val26(){
if((isNaN(document.Grp.JRperiod.value))||(document.Grp.JRperiod.value == ' ')) {
document.Grp.JRperiod.select();
document.Grp.JRperiod.value="";
}

}
function grp_code_val27(){
if((isNaN(document.Grp.SRperiod.value))||(document.Grp.SRperiod.value == ' ')) {
document.Grp.SRperiod.select();
document.Grp.SRperiod.value="";
}

}
function grp_code_val28(){
if((isNaN(document.Grp.RRperiod.value))||(document.Grp.RRperiod.value == ' ')) {
document.Grp.RRperiod.select();
document.Grp.RRperiod.value="";
}

}
function grp_code_val29(){
if((isNaN(document.Grp.PRperiod.value))||(document.Grp.PRperiod.value == ' ')) {
document.Grp.PRperiod.select();
document.Grp.PRperiod.value="";
}

}
function grp_code_val30(){
if((isNaN(document.Grp.TRperiod.value))||(document.Grp.TRperiod.value == ' ')) {
document.Grp.TRperiod.select();
document.Grp.TRperiod.value="";
}
}

</script>




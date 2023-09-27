<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="ss" scope="request" class="Lib.Auto.Member.MemberBean" type="Lib.Auto.Member.MemberBean">
</jsp:useBean>



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
<meta charset="ISO-8859-1">
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-base-yellow.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchMemberAll.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker3.js"></script>

</head>
<body background="/AutoLib/soft.jpg" onkeydown="myFunction(event)" onload="load()"><!-- onload="load()"-->

<form name="member" enctype="multipart/form-data" method="post" action=./MemberServlet>

<br><br><br>
<p align="center">
 <h2>Member Master</h2>
<table align="center" class="contentTable" width="75%">
<tr>
<td>
<table align="center" width="95%">

<tr><td> &nbsp; </td></tr>

<tr>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">User&nbsp;Id<FONT color=red size=2>*</FONT></td>
<td> <input type=text name=Code size=20 id="searchMemberCode" class="searchMember" onkeyup="showMemberCode(this.value);" maxlength=15 onKeydown="Javascript: if (event.keyCode==13) SearchRec();" onKeyUp="return valid_space()">  

<input type=button name=Find_Member Class="submitButton" Value="Find" onclick="FindValue('Member')">

</td>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">User&nbsp;Name<FONT color=red size=2>*</FONT></td>
<!-- <td> <input type=text name=Name  size=28 onKeyUp="return valid_space1()"></td> -->
<!-- <td> <input type=text name=Name  size=28 id="searchMemberName" class="searchMember" onkeyup="showMemberName(this.value);"></td> -->
<td> <input type=text name=Name  size=28 ></td>

    </div>
</div>
<td rowspan="6">
<table >    


<c:set var="str" value="<%=ss.getPhoto1()%>"/>
<c:choose>
<c:when test="${str ne null}">

<img id="output" src="<%=request.getContextPath() %>/Member/photo.jsp" height="100px" width="95px" align=left/></td>
 
</c:when>
<c:otherwise>

<img id="output" src="<%=request.getContextPath() %>/images/noimage.jpg" height="100px" width="95px" align=left/></td>

</c:otherwise>
</c:choose> 
<td><input type="file" id="photofile" name=memberphoto  size=20? accept="image/*" onchange="loadFile(event)"></td>
<tr><td><input type="button" value="Clear" onclick="reloadimage()"></td></tr>
</table> 
 </td>
 </tr>
 
 <tr> 
 
 <div class="search-container">
	<div class="ui-widget">
 
 <td Class="addedit"> Designation<FONT color=red size=2>*</FONT></td>
<!--  <td><input type=text name=Desig size=20 id="searchMemberDesig" class="searchMember" onkeyup="showMemberDesig(this.value);" readonly="true" value="NIL"> -->
 <td><input type=text name=Desig size=20 id="searchMemberDesig" class="searchMember" onkeyup="showMemberDesig(this.value);" value="NIL"> 
 
    </div>
 </div>
 <input type=button name=Find_Member Class="submitButton" Value="Find" onclick="FindValue('Desig')">
 
 </td>
 
 
<div class="search-container">
	<div class="ui-widget">
 
<td Class="addedit">Department<FONT color=red size=2>*</FONT></td>
<!-- <td> <input type=text name=Dname id="searchMemberDept" class="searchMember" onkeyup="showMemberDept(this.value);" size=20 value="NIL" readonly="true"> -->
<td> <input type=text name=Dname id="searchMemberDept" class="searchMember" onkeyup="showMemberDept(this.value);" size=20 value="NIL"> 

    </div>
</div>
<input type=button name=Find_Dept Value="Find" Class="submitButton" onclick="FindValue('Department')">

</td>
 
 </tr>
 
 <tr>
 
 <div class="search-container">
	<div class="ui-widget">
 
  <td Class="addedit">Group<FONT color=red size=2>*</FONT></td>
<!--     <td><input type=text name=Gname id="searchMemberGroup" class="searchMember" onkeyup="showMemberGroup(this.value);" size=20 readonly="true" value="NIL"> -->
    <td><input type=text name=Gname id="searchMemberGroup" class="searchMember" onkeyup="showMemberGroup(this.value);" size=20 value="NIL">
    
    </div>
</div>    
	<input type=button name=Find_Group Class="submitButton" Value="Find" onclick="FindValue('Group')">
	
	</td>

<div class="search-container">
	<div class="ui-widget">

<td  Class="addedit">Course</td>
<!--    <td><input type=text name=Cname id="searchMemberCourse" class="searchMember" onkeyup="showMemberCourse(this.value);" size=20 value="NIL-NIL" readonly="true"> -->
   <td><input type=text name=Cname id="searchMemberCourse" class="searchMember" onkeyup="showMemberCourse(this.value);" size=20 value="NIL-NIL"> 
   
   </div>
</div>   
   <input type=button name=Find_Course Value="Find" Class="submitButton" onclick="FindValue('Course')">
   
   </td>
   </tr>
 
 
 <tr>
 <td Class="addedit">Birth Date</td>
<td> 
<%-- <INPUT name=Birthdate size=14  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
<INPUT name=Birthdate size=14 id="datepicker" value="<%=Security.CalenderDate()%>">
				
		 </td>
		 
 	<td Class="addedit">Sex<td>
    <select name="Sex">
<option value=MALE>MALE</option>
<option value=FEMALE>FEMALE</option>
</select><b>&nbsp;&nbsp;&nbsp;SLock&nbsp;&nbsp;&nbsp;</b><select name="Security" size="1"  style="width: 60px">
<option value="NO" selected >NO</option>
<option value="YES" >YES</option>
<option value="CLEARANCE">CLEARANCE</option>
</select>
</td>
 </tr>
 
 
 
 <tr> 
<td Class="addedit">Year</td>
<td><select name="Year" size="1">
<option value="NO" selected >.....</option>
<option value="1" >I</option>
<option value="2">II</option>
<option value="3">III</option>
<option value="4">IV</option>
<option value="5">V</option>
<option value="pass out">pass out</option>
</select>
<b>Deposit</b><input name=Deposit value=0 size=5 maxlength=4 onKeyUp="return Deposit_val();"></td>
		 
<td  Class="addedit">Enroll Date</td>
<TD>
<%-- <INPUT name=enroldate size=14  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
<INPUT name=enroldate size=14 id="datepicker2" value="<%=Security.CalenderDate()%>">
				
	</TD>	
 </tr> 
 
 
 <tr>	 
<td Class="addedit">Validity Date<FONT color=red size=2>*</FONT></td>
  <td>
<%--   <INPUT name=validdate size=14  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
  <INPUT name=validdate size=14 id="datepicker3" value="<%=Security.CalenderDate()%>">
						 
</td>

<td Class="addedit">Address </td><td> <input name=Add1 size=28 maxlength=80>

</tr>

<tr>

<td Class="addedit">Address# </td><td> <input name=Add2 size=28 maxlength=80>

<div class="search-container">
	<div class="ui-widget">

 <td Class="addedit">City</td>
 <td><input name=City id="searchMemberCity" class="searchMember" onkeyup="showMemberCity(this.value);" size=20 maxlength=25>
 
   </div>
</div> 
   <input type=button name=Find_City Class="submitButton" Value="Find" onclick="FindValue('City')">
   
   
</tr>
 
 <tr>
 
 
	<td Class="addedit"> State</td><td><input name=State size=28 maxlength=25></td>
	
	
<td Class="addedit">PinCode</td>
<td><input name=Pin size=20 maxlength=6 onKeyUp="return member_pin_val();">
 </tr>
 
 <tr>
 
 
   <td Class="addedit">Email</td><td><input name=Email size=28 maxlength=50></td>
	<td Class="addedit">Phone</td><td><input name=Phone size=20 maxlength=12 onKeyUp="return Phone_val();"></td>
	 
 </tr>
 
 <tr>
 <td Class="addedit">Remarks</td><td colspan=6><input name=Rem size=74 maxlength=80></td>
 </tr>
 
<tr>
<td Class="addedit">Profile</td><td colspan=6><input name=Pro size=74 maxlength=80></td>
</tr>
 
 <tr><td>&nbsp;</td></tr>
 
 
 </table>
 
 </td></tr>
 </table>
 <p align="center">
<input type=button name=Save Class="submitButton" value=Save-F9 onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete-F7 onclick=DelRec()>

<input type=button name=search Class="submitButton" Value=Search  onclick=SearchRec()>

<input type=button name=ChangeUserID Class="submitButton" value=ChangeUserID onclick=ChangeUserId()>
<input type=Reset name=Clear Class="submitButton" Value=Clear-F3 onclick=ClearRec()>
<font color=red id="field"> Required Field *</font>
</p>

<input type=hidden name=flag>
<input type=hidden name=changeUserId>
<input type="hidden" name=Dept value=1>
<input type="hidden" name=Group value="1">
<input type="hidden" name=Course value="1">
<input type="hidden" name=Desigg value="1">
  
 
 
 
 
 
 </form></body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
if(valid!=null){

if(request.getParameter("check")!=null){
	
	
		
	
	if(valid.equals("failChangeMember")){
		  
		%>
		<script language="JavaScript">
		alert("UserId Already Exist! / Same UserId.");
		</script>
		<%  
		
	}
	
	
	if(valid.equals("SuccessChangeMember")){
		  
		%>
		<script language="JavaScript">
		alert("User Id has been Changed Successfully!");
		</script>
		<%  
		
	}

if(valid.equals("searchMember")){
 %>
  <script language="JavaScript">

document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=ss.getBdate()%>";
document.member.enroldate.value="<%=ss.getEdate()%>";
document.member.validdate.value="<%=ss.getExdate()%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>-<%=ss.getPhoto()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";


</script>
<%
}
if(valid.equals("FailMember")){ 

%>
            <script language="JavaScript">
            alert("Record Not Found");
			document.member.Code.focus();
			document.member.flag.value="none";
			location.href="index.jsp";
		   	</script><%
			}
	if(valid.equals("UpdateMember")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.member.flag.value="none";
			location.href="index.jsp";
		   	</script><%
			}
if(valid.equals("CodeCompareMember")){
String  Autcode=(String)request.getAttribute("membercode");
%>
            <script>
			alert("Name Already Exists in Code:"+"<%=Autcode%>");
			document.member.flag.value="none";
			location.href="index.jsp";
			</script><%
			}
if(valid.equals("SaveMember")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			document.member.flag.value="none";
			location.href="index.jsp";
		     </script>
			<%
		
			}

if(valid.equals("ReferredMember")){
%>            <script>alert("You can't delete since the member has been referred in other masters");
			</script>
			<%
			}

			if(valid.equals("DeleteMember")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.member.flag.value="none";
			location.href="index.jsp";
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){
%>
            <script language="JavaScript">

document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=ss.getBdate()%>";
document.member.enroldate.value="<%=ss.getEdate()%>";
document.member.validdate.value="<%=ss.getExdate()%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.member.flag.value="Confirmdete";
		   	document.member.submit();

			}
			</script>
			<%
			}
			if(valid.equals("enroll")){
%>
            <script language="JavaScript">

document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=ss.getBdate()%>";
document.member.enroldate.value="<%=ss.getEdate()%>";
document.member.validdate.value="<%=ss.getExdate()%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";

		alert("Birth Date is greater than or equal to Joining Date");
		</script>
			<%
			}

			if(valid.equals("expiry")){
%>
            <script language="JavaScript">

document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=ss.getBdate()%>";
document.member.enroldate.value="<%=ss.getEdate()%>";
document.member.validdate.value="<%=ss.getExdate()%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";

			alert("Joining Date is greater than or equal to  Validity Date");
		</script>
			<%
			}

			if(valid.equals("RecordNot")){
			%>
            <script language="JavaScript">
document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=ss.getBdate()%>";
document.member.enroldate.value="<%=ss.getEdate()%>";
document.member.validdate.value="<%=ss.getExdate()%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";

			    alert("Record Not Present!!!");
			    history.back();
			    document.member.flag.value="none";
			    location.href="index.jsp";
			</script>
			<%
			}


if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
document.member.Code.value="<%=ss.getCode()%>";
document.member.Name.value="<%=ss.getName()%>";
document.member.Birthdate.value="<%=Security.getdate(ss.getBdate())%>";
document.member.enroldate.value="<%=Security.getdate(ss.getEdate())%>";
document.member.validdate.value="<%=Security.getdate(ss.getExdate())%>";
document.member.Deposit.value="<%=ss.getDamount()%>";
document.member.Desig.value="<%=ss.getDecode()%>";
document.member.Sex.value="<%=ss.getSex()%>";
document.member.Add1.value="<%=ss.getMadd1()%>";
document.member.Add2.value="<%=ss.getMadd2()%>";
document.member.City.value="<%=ss.getMcity()%>";
document.member.State.value="<%=ss.getMstate()%>";
document.member.Pin.value="<%=ss.getMpincode()%>";
document.member.Phone.value="<%=ss.getMphone()%>";
document.member.Email.value="<%=ss.getMemail()%>";
document.member.Dname.value="<%=ss.getDeptcode()%>";
document.member.Cname.value="<%=ss.getCoursecode()%>";
document.member.Gname.value="<%=ss.getGroupcode()%>";
document.member.Rem.value="<%=ss.getRemarks()%>";
document.member.Pro.value="<%=ss.getProfile()%>";
document.member.Security.value="<%=ss.getSecurity()%>";
document.member.Year.value="<%=ss.getCyear()%>";

                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.member.flag.value="update";
		         	document.member.submit();

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
var loadFile = function(event) 
{
  var reader = new FileReader();
  reader.onload = function(){
    var output = document.getElementById('output');
    output.src = reader.result;
  };
  reader.readAsDataURL(event.target.files[0]);
};
function reloadimage()
{
	if(document.member.Code.value == ''){
		alert('Select Member');
		return;
	}
	var reader = new FileReader();
	var output = document.getElementById('output');
	output.src = "<%=request.getContextPath() %>/images/noimage.jpg";
	document.getElementById("photofile").value = '' ;
	document.member.flag.value='clearphoto';
	document.member.submit();
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}


function SearchRec(){

			document.member.method="post";
			var no=document.member.Code.value;
if(no==""){
				document.member.Code.focus();
				alert("Insufficent Data ");
				document.member.flag.value="none";
				document.member.action="index.jsp";
			   	document.member.submit();
			}
		 else
		      {
		      
			   document.member.flag.value="search";
			   document.member.submit();
			   }

}


function SaveRec(){
             document.member.method="post";
             
      if(chk_mc()){
			   if(chk()){
				if(check_date()){
				
				document.member.flag.value="save";
		         	document.member.submit();

				}
				else
	{
	alert("Does not equal to Birthdate and Enrolldate and Expirydate");
	}
					}
					else
	{
	alert("Insufficent Data ");
	}
					}
					else
	{
	alert("Insufficent Data ");
	}

}





function ChangeUserId(){

document.member.method="post";
if (document.member.Code.value==""){
				document.member.Code.focus();
				alert("Please select Used Id");
				}
				else{
				 var person=prompt("Enter the  New UserId to Change","<%=ss.getCode()%>"); 
				  
				  if (person!=null){
				  document.member.changeUserId.value=person;
				  document.member.flag.value="changeUserId";
     			  document.member.submit();
				  }
				  
			}
		}
		
		
		
function myFunction(event) 
{
    var x = event.keyCode;
    
    if(x=="13")
    {
    	event.preventDefault();
    	SearchRec();	
    }
    else if(x=="114")
	{
    	event.preventDefault();
    	ClearRec();
	}
    else if(x=="118")
	{
    	event.preventDefault();
    	DelRec();
	}
     else if(x=="120")
 	{
     	event.preventDefault();
     	SaveRec();
 	}
    else if(x=="9")
	{
    	return event();
//     	event.preventDefault();
//     	document.counter.accno.focus();
	}
    
}	



function check()
{
  if(confirm("Are You Sure To Continue Save?"))
   {
     document.member.flag.value="save";
	 return true;
    }

}

function chk(){
var flag_s;
var i;
var sp=document.member.Name.value;
if(sp=="")
{
				document.member.Name.focus();
				document.member.flag.value="none";
				document.member.Name.value="";
				return false;
				}
				else
				{
					return true;
 				}
 }
 function chk_mc(){
var flag_cs;
var c;
var mc=document.member.Code.value;
if(mc=="")
{
				document.member.Code.focus();
				document.member.flag.value="none";
				document.member.Code.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
 
 function chk_grp(){
var flag_cs;
var c;
var mc=document.member.Gname.value;
if(mc=="")
{
				document.member.Gname.focus();
				document.member.flag.value="none";
				document.member.Gname.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }

 function check_date()
{
if((document.member.enroldate.value)==(document.member.validdate.value))
{
return false;
}
return true;
}

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}

function Member_code() {
if((isNaN(document.member.Code.value))||(document.member.Code.value == ' ')) {
document.member.Code.select();
document.member.Code.value="";

  }
}

function DelRec(){

document.member.method="post";
if (document.member.Code.value==""){
				document.member.Code.focus();
				alert("Insufficent Data");
				}
				else{
				
				  document.member.flag.value="delete";
				  document.member.submit();
				 }

}

function ClearRec(){ 

				document.member.flag.value="none";
			   	document.member.submit();	   	


}
function isWhitespace(str)
	{
		var re = /[\S]/g
		if (re.test(str)) return false;
		return true;
	}

function valid_space() { 
     if (isWhitespace(document.member.Code.value)) {
        document.member.Code.select();
        document.member.Code.value="";      
    }
}
function valid_space1() { 
     if (isWhitespace(document.member.Code.value)) {
        document.member.Code.select();
        document.member.Code.value="";      
    }
}    
    
function member_pin_val() {
if((isNaN(document.member.Pin.value))||(document.member.Pin.value == ' ')) {
document.member.Pin.select();
document.member.Pin.value="";

  }
}

function Phone_val() {
if((isNaN(document.member.Phone.value))||(document.member.Phone.value == ' ')) {
document.member.Phone.select();
document.member.Phone.value="";

  }
}

function Deposit_val() {
if((isNaN(document.member.Deposit.value))||(document.member.Deposit.value == ' ')) {
document.member.Deposit.select();
document.member.Deposit.value="";

  }
}
function focuss()
{
document.member.Code.focus();
}

function load(){
	if(document.member.Code.value==''){
		var output = document.getElementById('output');
	    output.src = "<%=request.getContextPath() %>/images/noimage.jpg";
		
	}
	document.member.Code.focus();
		 }
</script>

<script type="text/javascript">
function openDialog() {
    var result = window.showModalDialog("http://www.java2s.com", form, "dialogWidth:300px; dialogHeight:201px; center:yes");
}
</script>
<!--
///////////////////////////////////////////////Java Script//////////////////////////////////////////////
-->
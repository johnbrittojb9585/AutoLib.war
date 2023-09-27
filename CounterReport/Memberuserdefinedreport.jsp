<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<%

int iLevel=0;
      Object o1 = session.getAttribute("UserID");
      Object o2 = session.getAttribute("UserRights");
      boolean bRedirect = false;
      if ( o1 == null || o2 == null ) { bRedirect = true; }
      if ( ! bRedirect ) {
        if ( (o1).equals("")) { bRedirect = true; }
        else if ( (new Integer(o2.toString())).intValue() < iLevel) { bRedirect = true; }
      }
      if ( bRedirect ) {
	   response.sendRedirect("/AutoLib/Login.jsp");
  }
String txtreporttype="",txtaccessno,txtmembercode,txtdepartment,txtfdate,txttdate,txtgroup;
	String order1,order2,order3,str,strsql="",order,strsqlq="",condition="";
	String deptname="",groupname="",staff="",Reptfrom="",txtcourse,txtdoctype,coursename="";
	int sno=0,subcount=0,fine=0;
	 java.util.Calendar calendar = new java.util.GregorianCalendar();				
				java.util.Date r=new java.util.Date();
				calendar.setTime(r);
				int ry=calendar.get(java.util.Calendar.YEAR);
				int rd=calendar.get(java.util.Calendar.DAY_OF_MONTH);
				int rm=calendar.get(java.util.Calendar.MONTH)+1;
				String temp_rm=new Integer(rm).toString();
				    if(temp_rm.length()==1)
				    temp_rm="0"+temp_rm;
					String temp_rr=new Integer(rd).toString();
				    if(temp_rr.length()==1)
				    temp_rr="0"+temp_rr;
					String rr=temp_rr+"/"+temp_rm+"/"+ry;
	%>
<body onload=invisible()>
<FORM NAME="user" METHOD="POST" ACTION="Memberuserdefined.jsp" ONSUBMIT="return validate()">
   <P ALIGN="center"><B><FONT COLOR="#0000FF" SIZE="5">&nbsp;USER DEFINED REPORT</FONT></B></P>
  <CENTER>
  <TABLE BORDER="1" WIDTH="691" HEIGHT="93" BORDERCOLOR="#FFFFFF" CELLSPACING="0">
    <TR>
      <TD WIDTH="300" HEIGHT="47" VALIGN="top"  bgcolor="#808080">
        <P ALIGN="center">&nbsp;&nbsp;&nbsp;&nbsp; <FONT COLOR="#0000FF"><B>Select
        Fields For Report</B></FONT></P>
        <DIV ALIGN="center">
          <CENTER>
          <TABLE BORDER="1" HEIGHT="253" BORDERCOLOR="#808080" CELLSPACING="0">
            <TR>
              <TD WIDTH="97" HEIGHT="31"><B>&nbsp;Memcode</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Memcode" VALUE="Member_Code" ONCLICK="Membercode()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>PinCode&nbsp;</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Pcode" VALUE="Member_Pincode" ONCLICK="Pincode()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Mem Name</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Memname" VALUE="Member_Name" ONCLICK="Membername()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Email</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Email" VALUE="Email" ONCLICK="Memail()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Department</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Dept" VALUE="Dept_Name" ONCLICK="Department()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Profile</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Profile" VALUE="Profile" ONCLICK="Mprofile()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Course</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Course" VALUE="Course_Name" ONCLICK="Mcourse()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Accessno</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Accno" VALUE="Access_No" ONCLICK="Accessno()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Designation</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Designation" VALUE="Desgnation" ONCLICK="Desig()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Title</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Title" VALUE="Title" ONCLICK="Btitle()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31"><B>&nbsp;Enrolldate</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Edate" VALUE="Enroll_Date" ONCLICK="Enrolldate()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Doc Type</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Doctype" VALUE="Document" ONCLICK="Dtype()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31"><B>&nbsp;Expirydate</B>&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Xdate" VALUE="Expiry_Date" ONCLICK="Expirydate()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Issuedate</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Issuedate" VALUE="Issue_Date" ONCLICK="Idate()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Address1</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="add1" VALUE="Member_Add1" ONCLICK="Madd1()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Returndate</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Returndate" VALUE="Return_Date" ONCLICK="Rdate()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>Address2</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="add2" VALUE="Member_Add2" ONCLICK="Madd2()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>State</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="State" VALUE="Member_State" ONCLICK="Mstate()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31"><B>&nbsp;DepositAmount</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Damount" VALUE="Deposit_Amount" ONCLICK="Depositamount()"></TD>
              <TD WIDTH="97" HEIGHT="31">&nbsp;<B>City</B></TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="City" VALUE="Member_City" ONCLICK="Place()"></TD>
            </TR>
            <TR>
              <TD WIDTH="97" HEIGHT="31"><B>&nbsp;Fine</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Fine" VALUE="Fine_Amount" ONCLICK="Famount()"></TD>
            </TR>
          </TABLE>
          </CENTER>
        </DIV>
        &nbsp;</TD>
    </CENTER>
    <TD WIDTH="298" HEIGHT="47" BGCOLOR="#808080" ALIGN="center"><FONT COLOR="#0000FF"><B>Selected
      Fields Order</B></FONT><BR>
      <BR>
      <SELECT SIZE="22" NAME="selectedfields" STYLE="font-weight: bold; " multiple>
        <OPTION VALUE="first">----------------------------------------</OPTION>
      </SELECT></TD>
  </TR>
  <TR>
    <TD COLSPAN="2" HEIGHT="42" WIDTH="685" BGCOLOR="#808080">
      <P ALIGN="center"><FONT COLOR="#0000FF"><B>Select All</B>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE="checkbox" NAME="C3" VALUE="ON" ONCLICK="forall()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="value">
	  <INPUT TYPE="submit" VALUE="Report" NAME="B1" STYLE="color: #FF00FF; font-size: 12pt; border-style: solid; border-color: #000000">&nbsp;&nbsp;&nbsp;
      <INPUT TYPE="reset" VALUE="Clear" NAME="B2" STYLE="color: #FF00FF; font-size: 12pt; border-style: solid; border-color: #000000" ONCLICK="clear1()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <B>Report Format&nbsp; <SELECT SIZE="1" NAME="RType">
        <OPTION SELECTED VALUE="VERTICAL">VERTICAL</OPTION>
        <OPTION VALUE="HORIZONTAL">HORIZONTAL</OPTION>
      </SELECT></B></P>
      </FONT></TD>
  </TR>
  </TABLE>
   </FORM>
</body>
<%
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
try
{
String sErr = loadDriver();
con = cn();
st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}

	txtreporttype = request.getParameter("reporttype");
	txtaccessno=request.getParameter("txtaccessno");
	txtmembercode =request.getParameter("txtmembercode");
	txtdepartment =request.getParameter("txtdepartment");
	txtgroup =request.getParameter("txtgroup");
	txtcourse =request.getParameter("txtcourse");
	txtdoctype=request.getParameter("txtdoctype");
	txtfdate =request.getParameter("txtfdate");
	txttdate =request.getParameter("txttdate");

	session.setAttribute("txtreporttype",txtreporttype);
	session.setAttribute("txtdepartment",txtdepartment);
	
	order1 =request.getParameter("order1");
	order2 =request.getParameter("order2");
	order3 =request.getParameter("order3");	
	
if(!txtdepartment.equals("NO")){
	str = "select Dept_Name from Department_Mas where Dept_Code =" + txtdepartment;
	rs=st.executeQuery(str);
	while (rs.next()){deptname = rs.getString("Dept_Name");
	  }
	
	}
	
	if(!txtgroup.equals("NO")){
	str = "select Group_Name from Group_Mas where Group_Code ="+txtgroup;
	rs=st.executeQuery(str);
	while (rs.next()) groupname = rs.getString("Group_Name");
	}
	
	if(!txtcourse.equals("NO")){
	str = "select course_Name from course_Mas where course_code="+txtcourse;
	rs=st.executeQuery(str);
	while (rs.next()) coursename = rs.getString("Course_Name");
	}
	strsql = "";
	if (!txtmembercode.equals(""))
	{
	strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
	}
	
	if(!txtaccessno.equals("") ){
	strsql = strsql + " and access_no='"+txtaccessno+"'";
	}
	if (!txtdepartment.equals("NO")) 
	strsql = strsql + "  and Dept_Code = '" +txtdepartment+"'";
	
	if (!txtgroup.equals("NO")) 
	strsql = strsql + "  and Group_Code = '" +txtgroup+"'";
	
	if (!txtcourse.equals("NO")) 
	strsql = strsql + "  and course_code = '" +txtcourse+"'";
	
	if((!txtfdate.equals("")) && (!txttdate.equals("")))
	{
	if((txtreporttype.equals("Issue")) || (txtreporttype.equals("Renewal")))
	strsql = strsql + " and Issue_Date  like '" +txtfdate+"'";
	if (txtreporttype.equals("Duereminder"))
	strsql = strsql + " and Due_Date like '" +txtfdate+"'";	
	if((txtreporttype.equals("Fine")) || (txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date like '" +txtfdate+"'";
	}
	
	
	if ((!txttdate.equals("")) &&  (!txtfdate.equals(""))) 
	{
	if ((txtreporttype.equals("Issue")) || (txtreporttype.equals("Renewal"))) 
	strsql = strsql + " and Issue_Date like '" +txttdate+"'";
	if (txtreporttype.equals("Duereminder"))	
	strsql = strsql + " and Due_Date like '" +txttdate+"'";
	if((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return"))) 
	strsql = strsql + " and Return_Date like '" +txttdate+"'";
	}
	
	if ((!txttdate.equals("")) &&  (!txtfdate.equals(""))) 
	{
	if ((txtreporttype.equals("Issue")) ||(txtreporttype.equals("Renewal")))
	strsql = strsql + " and Issue_Date between '"+txtfdate+"' and '" +txttdate+"'";
	if (txtreporttype.equals("Duereminder"))
	strsql = strsql + " and  Due_Date between '"+txtfdate+"' and '" +txttdate+"'";
	if ((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return"))) 
	strsql = strsql + " and Return_Date between '"+txtfdate+"' and '" +txttdate+"'";
	}                 
	if(!txtgroup.equals("NO")) strsql = strsql + " and Group_Code = '" +txtgroup+"'";
	order = "";
	if (!order1.equals("NO" )) order = order+order1;
	if (!order2.equals("NO" )) order = order+","+order2;
	if (!order3.equals("NO")) order = order+","+order3;
	
	condition =  strsql +"  order by  "+order;
	session.setAttribute("condition",condition);
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
	if ( st != null ) st.close();
    if ( con != null ) con.close();
}  catch (Exception e) {
   out.println(e.toString());
  }%>
  
<script language="javascript">
function invisible()
{
 var rpt_type,selected_Fields;
 
 rpt_type="<%=txtreporttype%>";
 
 if ((rpt_type=="Fine")||(rpt_type=="Return"))
 {
  user.Returndate.style.visibility="visible";
   }
  else
  {
   user.Returndate.style.visibility="hidden";
   }

   
if(rpt_type=="Issue" || rpt_type=="Renewal" || rpt_type=="Duereminder" )
{
user.Fine.style.visibility = "hidden";
user.Returndate.style.visibility = "hidden";
	}
len = user.selectedfields.length ;


if(user.Accno.checked) { 
len = user.selectedfields.length ;
newoption = new Option('Accessno','Access_No','','selected');
user.selectedfields.options[len] = newoption;
 }

if(user.Memcode.checked) {
len = user.selectedfields.length ;
newoption = new Option('Membercode','Member_Code','','selected'); 
user.selectedfields.options[len] = newoption;  }

 if(user.Memname.checked) {
len = user.selectedfields.length ;
newoption = new Option('Membername','Member_Name','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.Edate.checked) {
len = user.selectedfields.length ;
newoption = new Option('Enrolldate','Enroll_Date','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.Xdate.checked) {
len = user.selectedfields.length ;
newoption = new Option('Expirydate','Expiry_Date','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.Damount.checked) {
len = user.selectedfields.length ;
newoption = new Option('Depositamount','Deposit_Amount','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.Designation.checked) {
len = user.selectedfields.length ;
newoption = new Option('Designation','Designation','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.add1.checked) {
len = user.selectedfields.length ;
newoption = new Option('Address1','Member_Add1','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.add2.checked) {
len = user.selectedfields.length ;
newoption = new Option('Address2','Member_Add2','','selected');
user.selectedfields.options[len] = newoption; }

 if(user.City.checked) {
len = user.selectedfields.length ;
newoption = new Option('City','Member_City','','selected');
user.selectedfields.options[len] = newoption; }

if(user.State.checked) {
len = user.selectedfields.length ;
newoption = new Option('State','Member_State','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Pcode.checked) {
len = user.selectedfields.length ;
newoption = new Option('Pincode','Member_Pincode','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Email.checked) {
len = user.selectedfields.length ;
newoption = new Option('Email','Member_Email','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Profile.checked) {
len = user.selectedfields.length ;
newoption = new Option('Profile','Profile','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Issuedate.checked) {
len = user.selectedfields.length ;
newoption = new Option('Issuedate','Issue_Date','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Returndate.checked) {
len = user.selectedfields.length ;
newoption = new Option('Returndate','Return_Date','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Fine.checked) {
len = user.selectedfields.length ;
newoption = new Option('Fine','Fine_Amount','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Doctype.checked) {
len = user.selectedfields.length ;
newoption = new Option('Document','txtdoctype','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Dept.checked) {
len = user.selectedfields.length ;
newoption = new Option('Department','Dept_Name','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Course.checked) {
len = user.selectedfields.length ;
newoption = new Option('Course','Course_Name','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Title.checked) {
len = user.selectedfields.length ;
newoption = new Option('Title','Title','','selected');
user.selectedfields.options[len] = newoption; }

} 


function Accessno(){ 
var test = user.selectedfields.checked ,m=0;
var len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
var val = user.selectedfields.options[i].value;
if(val=='Access_No')
{
m=i;
}
}
if(m>0){
user.selectedfields.options[m] =null;m=0;}
else{
var len = user.selectedfields.length;
newoption = new Option('Accessno','Access_No','','selected');
user.selectedfields.options[len] = newoption;
}}
function Membercode()
{
test = user.selectedfields.checked;
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Code')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Membercode','Member_Code','','selected'); 
user.selectedfields.options[len] = newoption; 
}}
function Membername()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Membername','Member_Name','','selected');
user.selectedfields.options[len] = newoption;
}}
function Enrolldate()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Enroll_Date')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('EnrollDate','Enroll_Date','','selected');
user.selectedfields.options[len] = newoption;
}}
function Expirydate()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Expiry_Date')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Expirydate','Expiry_Date','','selected');
user.selectedfields.options[len] = newoption;
}}
function Depositamount()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Deposit_Amount')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Depositamount','Deposit_Amount','','selected');
user.selectedfields.options[len] = newoption;
}}
function Desig()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Designation')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Designation','Designation','','selected');
user.selectedfields.options[len] = newoption;
}}
function Madd1()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Add1')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Address1','Member_Add1','','selected');
user.selectedfields.options[len] = newoption;
}}
function Madd2()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Add2')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Address2','Member_Add2','','selected');
user.selectedfields.options[len] = newoption;
}}
function Place()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_City')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('City','Member_City','','selected');
user.selectedfields.options[len] = newoption;
}}
function Mstate()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_State')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('State','Member_State','','selected');
user.selectedfields.options[len] = newoption;
}}
function Pincode()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Pincode')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Pincode','Member_Pincode','','selected');
user.selectedfields.options[len] = newoption;
}}
function Memail()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Member_Email')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Email','Member_Email','','selected');
user.selectedfields.options[len] = newoption;
}}
function Mprofile()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Profile')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Profile','Profile','','selected');
user.selectedfields.options[len] = newoption;
}}
function Idate()
{
test = user.selectedfields.checked;
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Issue_Date')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Issuedate','Issue_Date','','selected');
user.selectedfields.options[len] = newoption;
}}
function Rdate()
{

if(user.Returndate.style.visibility!="hidden")
{
test = user.selectedfields.checked;
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Return_Date')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Returndate','Return_Date','','selected');
user.selectedfields.options[len] = newoption;
}
}
}

function Famount()
{
if(user.Returndate.style.visibility!="hidden")
{
test = user.selectedfields.checked;
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Fine_Amount')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Fineamount','Fine_Amount','','selected');
user.selectedfields.options[len] = newoption;
}}
}
function Dtype()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='txtdoctype')
{var m=i;
}
}

if(m>0){user.selectedfields.options[m]=null;m=0;}else{
newoption = new Option('Document','txtdoctype','','selected');
user.selectedfields.options[len] = newoption;
}}
function Department()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Dept_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m]=null;m=0;}else{
newoption = new Option('Department','Dept_Name','','selected');
user.selectedfields.options[len] = newoption;
}}
function Mcourse()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Course_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m]=null;m=0;}else{
newoption = new Option('Course','Course_Name','','selected');
user.selectedfields.options[len] = newoption;
}}
function Btitle()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Title')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m]=null;m=0;}else{
newoption = new Option('Title','Title','','selected');
user.selectedfields.options[len] = newoption;
}}






function clear1()
{ 
len = user.selectedfields.length ;
j=1;
for(i=1;i<=len;i++)
{
user.selectedfields.options[j] = null;
}
}

function validate()
{
len = user.selectedfields.length ;
 if(len <= 1 ) { alert('Please Select Fields For Report'); return false;} else { return true; } 
}


function forall() {
len = user.selectedfields.length ;
 if((user.C3.checked)) {
clear1();
Accessno();
user.Accno.checked = 'true'
Btitle();
user.Title.checked = 'true' 
Dtype();
user.Doctype.checked = 'true' 
Idate();
user.Issuedate.checked = 'true'
Rdate();
user.Returndate.checked = 'true'
Membercode();
user.Memcode.checked = 'true'
Membername();
user.Memname.checked = 'true'
Department();
user.Dept.checked = 'true' 
Mcourse();
user.Course.checked = 'true' 

Enrolldate();
user.Edate.checked = 'true'
Expirydate();
user.Xdate.checked = 'true'
Depositamount();
user.Damount.checked = 'true'
Desig();
user.Designation.checked = 'true'
Madd1();
user.add1.checked = 'true'
Madd2();
user.add2.checked = 'true'
Place();
user.City.checked = 'true'
Mstate();
user.State.checked = 'true'
Pincode();
user.Pcode.checked = 'true'
Memail();
user.Email.checked = 'true'
Mprofile();
user.Profile.checked = 'true'
Famount();
user.Fine.checked = 'true'
 }
  else { 
clear1(); user.reset();
          }
 
 
 }
</script>




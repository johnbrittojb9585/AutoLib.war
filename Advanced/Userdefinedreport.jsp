<%@ pagelanguage="java" session="true" buffer="12kb"  import="java.sql.*" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename:view.jsp
//   Form name:vfrm
//%>
<!--
///////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>smple Search</title>
</head>
<body >
<FORM NAME="user" METHOD="get" ACTION=./UserReport ONSUBMIT="return validate()">
  <P ALIGN="center"><B><FONT COLOR="#000000" SIZE="5">&nbsp;USER DEFINED REPORT</FONT></B></P>
  <DIV ALIGN="center">
    <CENTER>
    <TABLE BORDER="1" WIDTH="580" HEIGHT="93" BORDERCOLOR="#FFF000" CELLSPACING="0">
      <TR>
        <TD WIDTH="254" HEIGHT="47" VALIGN="top" ALIGN="center" >
          &nbsp;&nbsp;&nbsp;&nbsp; <B><font color="#FF00FF" size="4">Select Fields For
          Report</font></B>
          <DIV ALIGN="center">
            <p align="center">
            &nbsp;
      </CENTER>
            <TABLE BORDER="1" HEIGHT="253" BORDERCOLOR="#0000FF" CELLSPACING="0" ALIGN="center">
              <TR>
                <TD WIDTH="97" HEIGHT="31"><B><font size="3">&nbsp; Access No</font></B></TD>
    <CENTER>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="C1" VALUE="Access_No" ONCLICK="Accessno()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Year&nbsp;</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Year_Pub" VALUE="Year_Pub" ONCLICK="Year()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Call Number</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Call_No" VALUE="Call_No" ONCLICK="Callno()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Rec. Date</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Received_Date" VALUE="Received_Date" ONCLICK="Rdate()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp;<B> Title</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Title" VALUE="Title" ONCLICK="Title1()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp;<B> Dept</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Dept_Name" VALUE="Dept_Name" ONCLICK="Dept()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Author</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Author_Name" VALUE="Author_Name" ONCLICK="Author()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Edition</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Edition" VALUE="Edition" ONCLICK="Edition1()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><B><font size="3">&nbsp; Subject</font></B></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Sub_Name" VALUE="Sub_Name" ONCLICK="Subject()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Availability</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Availability" VALUE="Availability" ONCLICK="Avail()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><B><font size="3">&nbsp; Publisher</font></B><font size="3">&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="SP_Name" VALUE="SP_Name" ONCLICK="Publish()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Place</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="City" VALUE="City" ONCLICK="Place()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Keyword</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Keywords" VALUE="Keywords" ONCLICK="Keyword()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Location</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Location" VALUE="Location" ONCLICK="Locat()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>ISBN</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="ISBN" VALUE="ISBN" ONCLICK="Isbn()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Price</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="BPrice" VALUE="BPrice" ONCLICK="Price()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><B><font size="3">&nbsp; Type</font></B><font size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Document" VALUE="Document" ONCLICK="Type()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Supplier</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Supplier" VALUE="Supplier" ONCLICK="spplr()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><B><font size="3">&nbsp; Invoice No</font></B><font size="3">&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="Invoice_No" VALUE="Invoice_No" ONCLICK="Invoice()"></font></TD>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>Budget Head</B></font></TD>
                <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Budg_Code" VALUE="Budg_Code" ONCLICK="Budget()"></TD>
              </TR>
              <TR>
                <TD WIDTH="97" HEIGHT="31"><font size="3">&nbsp; <B>pages</B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
                <TD WIDTH="20" HEIGHT="31"><font size="3"><INPUT TYPE="checkbox" NAME="pages" VALUE="pages" ONCLICK="Colat()"></font></TD>
              <!--  <TD WIDTH="97" HEIGHT="31"><b><font size="3">Pages</font></b></td>
			  <!--  <TD WIDTH="20" HEIGHT="31"><INPUT TYPE="checkbox" NAME="Pages" VALUE="Pages" ONCLICK="Pages1()"></TD>
<!--				<td width='20' height='31'><input type="checkbox" name="Pages" value="Pages" onclick = "Pages()"></TD> -->
              </TR>
            </TABLE>
          </DIV>
          &nbsp;</TD>
      </CENTER>
      <TD WIDTH="233" HEIGHT="47" ALIGN="center">
        <P ALIGN="center"><FONT COLOR="#FF00FF"><B>&nbsp;<font size="4">Selected Fields Order</font></B></FONT><BR>
         <SELECT SIZE="20" NAME="selectedfields" MULTIPLE STYLE="font-weight: bold; ">
          <OPTION VALUE="first">----------------------------------------</OPTION>
        </SELECT></TD>
    </TR>
    <TR>
      <TD COLSPAN="2" HEIGHT="42" WIDTH="574" BGCOLOR="#DEBCBC">
        <P ALIGN="center"><FONT COLOR="#0000FF"><B>Select All</B>
        &nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="checkbox" NAME="C3" VALUE="ON" ONCLICK="forall()">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="submit" VALUE="Report" NAME="B1" STYLE="color: #FF00FF; font-size: 12pt; border-style: solid; border-color: #000000"onclick="validate()">
		&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="reset" VALUE="Clear" NAME="B2" STYLE="color: #FF00FF; font-size: 12pt; border-style: solid; border-color: #000000" ONCLICK="clear1()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <B>Report Format&nbsp; <SELECT SIZE="1" NAME="RType">
          <OPTION SELECTED VALUE="VERTICAL">VERTICAL</OPTION>
          <OPTION VALUE="HORIZONTAL">HORIZONTAL</OPTION>
        </SELECT></B></P>
        </FONT></TD>
    </TR>
    </TABLE>
  </DIV>
 <script language='javascript'>
function Maintain()
{
len = user.selectedfields.length ;
 if(user.Access_No.checked) { 
len = user.selectedfields.length ;
alert("fdssdfsdfd");
newoption = new Option('Accessno','Access_No','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Call_No.checked) {
len = user.selectedfields.length ;
newoption = new Option('Callno','Call_No','','selected'); 
user.selectedfields.options[len] = newoption;  }
 if(user.Title.checked) {
len = user.selectedfields.length ;
newoption = new Option('Title','Title','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Year_Pub.checked) {
len = user.selectedfields.length ;
newoption = new Option('Published Year','Year_Pub','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Received_Date.checked) {
len = user.selectedfields.length ;
newoption = new Option('ReceivedDate','Received_Date','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Dept_Name.checked) {
len = user.selectedfields.length ;
newoption = new Option('Department','Department','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Author_Name.checked) {
len = user.selectedfields.length ;
newoption = new Option('AuthorName','Author','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Edition.checked) {
len = user.selectedfields.length ;
newoption = new Option('Edition','Edition','','selected');
user.selectedfields.options[len] = newoption; }
 if(user.Sub_Name.checked) {
len = user.selectedfields.length ;
newoption = new Option('Subject','Subject','','selected');
user.selectedfields.options[len] = newoption; }
if(user.Availability.checked) {
len = user.selectedfields.length ;
newoption = new Option('Availability','Availability','','selected');
user.selectedfields.options[len] = newoption; }
if(user.SP_Name.checked) {
len = user.selectedfields.length ;
newoption = new Option('Publisher','publisher','','selected');
user.selectedfields.options[len] = newoption; }
if(user.City.checked) {
len = user.selectedfields.length ;
newoption = new Option('Place','City','','selected');
user.selectedfields.options[len] = newoption; }
if(user.keywords.checked) {
len = user.selectedfields.length ;
newoption = new Option('Keywords','keywords','','selected');
user.selectedfields.options[len] = newoption; }
if(user.Location.checked) {
len = user.selectedfields.length ;
newoption = new Option('Location','Location','','selected');
user.selectedfields.options[len] = newoption; }
if(user.SP_Name.checked) {
len = user.selectedfields.length ;
newoption = new Option('Supplier','supplier','','selected');
user.selectedfields.options[len] = newoption; }
if(user.ISBN.checked) {
len = user.selectedfields.length ;
newoption = new Option('ISBN','ISBN','','selected');
user.selectedfields.options[len] = newoption; }
if(user.BPrice.checked) {
len = user.selectedfields.length ;
newoption = new Option('Bookprice','Price','','selected');
user.selectedfields.options[len] = newoption; }
if(user.type.checked ) {
len = user.selectedfields.length ;
newoption = new Option('Type','type','','selected');
user.selectedfields.options[len] = newoption; }
if(user.Invoice_No.checked) {
len = user.selectedfields.length ;
newoption = new Option('Invoiceno','Invoice_No','','selected');
user.selectedfields.options[len] = newoption; }
if(user.Budg_Code.checked) {
len = user.selectedfields.length ;
newoption = new Option('Budget','Budg_Code','','selected');
user.selectedfields.options[len] = newoption; }
if(user.pages.checked) {
len = user.selectedfields.length ;
newoption = new Option('pages','pages','','selected');
user.selectedfields.options[len] = newoption; }

if(user.Pages.checked) {
len = user.selectedfields.length ;
newoption = new Option('Pages','Pages','','selected');
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
function Callno()
{
test = user.selectedfields.checked;
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Call_No')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Callno','Call_No','','selected'); 
user.selectedfields.options[len] = newoption; 
}}
function Year()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Year_Pub')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('PublishedYear','Year_Pub','','selected');
user.selectedfields.options[len] = newoption;
}}
function Rdate()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Received_Date')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('ReceivedDate','Received_Date','','selected');
user.selectedfields.options[len] = newoption;
}}
function Title1()
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
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Title','Title','','selected');
user.selectedfields.options[len] = newoption;
}}
function Dept()
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
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Department','Department','','selected');
user.selectedfields.options[len] = newoption;
}}
function Author()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Author_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('AuthorName','Author','','selected');
user.selectedfields.options[len] = newoption;
}}
function Edition1()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Edition')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Edition','Edition','','selected');
user.selectedfields.options[len] = newoption;
}}
function Subject()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Sub_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Subject','Subject','','selected');
user.selectedfields.options[len] = newoption;
}}
function Avail()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Availability')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Availability','Availability','','selected');
user.selectedfields.options[len] = newoption;
}}
function Publish()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='SP_Name')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Publisher','Publisher','','selected');
user.selectedfields.options[len] = newoption;
}}


function Place()
{

test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='City')
{var m=i;
}
}


if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Place','City','','selected');
user.selectedfields.options[len] = newoption;
}}
function Keyword()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Keywords')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Keywords','Keywords','','selected');
user.selectedfields.options[len] = newoption;
}}
function Locat()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Location')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Location','Location','','selected');
user.selectedfields.options[len] = newoption;
}}
function Isbn()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='ISBN')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('ISBN','ISBN','','selected');
user.selectedfields.options[len] = newoption;
}}
function Price()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='BPrice')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Bookprice','Price','','selected');
user.selectedfields.options[len] = newoption;
}}
function Invoice()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Invoice_No')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m]=null;m=0;}else{
newoption = new Option('Invoiceno','Invoice_No','','selected');
user.selectedfields.options[len] = newoption;
}}
function spplr()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Supplier')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Supplier','Supplier','','selected');
user.selectedfields.options[len] = newoption;
}}
function Type()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Document')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Type','Document','','selected');
user.selectedfields.options[len] = newoption;
}}
function Colat()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='pages')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('pages','pages','','selected');
user.selectedfields.options[len] = newoption;
}}
function Budget()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Budg_Code')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Budget','Budg_Code','','selected');
user.selectedfields.options[len] = newoption;
}}

/*function Pages1()
{
test = user.selectedfields.checked
len = user.selectedfields.length ;
for(i=0;i<len;i++)
{
val = user.selectedfields.options[i].value;
if(val=='Pages')
{var m=i;
}
}
if(m>0){user.selectedfields.options[m] = null;m=0;}else{
newoption = new Option('Peges','Pages','','selected');
user.selectedfields.options[len] = newoption;
}
}*/

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
user.C1.checked = 'true'
Callno();
user.Call_No.checked = 'true'
Title1();
user.Title.checked = 'true'
Year();
user.Year_Pub.checked = 'true'
Rdate();
user.Received_Date.checked = 'true'
Dept();
user.Dept_Name.checked = 'true'
Author();
user.Author_Name.checked = 'true'
Edition1();
user.Edition.checked = 'true'
Subject();
user.Sub_Name.checked = 'true'
Avail();
user.Availability.checked = 'true'
Publish();
user.SP_Name.checked = 'true'
Place();
user.City.checked = 'true'
Keyword();
user.Keywords.checked = 'true'
Locat();
user.Location.checked = 'true'
spplr();
user.Supplier.checked = 'true'
Isbn();
user.ISBN.checked = 'true'
Price();
user.BPrice.checked = 'true'
Type();
user.Document.checked = 'true'
Invoice();
user.Invoice_No.checked = 'true'
Budget();
user.Budg_Code.checked = 'true'
Colat();
user.pages.checked = 'true'
 }
else { 
clear1(); user.reset();
 }}
 
</script>

</FORM>
</BODY>
</HTML>

<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Holiday
//%>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String dateString = sdf.format(d);
%>

<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>

</head>
<body bgcolor="#d3d3d3" text=blue>
<align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href='<%= response.encodeURL("/AutoLib/Home.jsp") %>'>HOME</a></font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<align=left style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href='<%= response.encodeURL("/AutoLib/Login.jsp") %>'>LOGIN HOME</a></font></b>
<!-- Style Sheet -->
<h2 align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000">Holiday Master</font></b> </h2>
<!-- Style Sheet -->
<FORM name="holiday" method=post  action='<%=response.encodeURL("index.jsp")%>'> 
<DIV align=center>
<CENTER>

<table border=2 WIDTH="543" HEIGHT="72" CELLSPACING="0"  bordercolor="#000080" bgcolor="#C0C0C0"   align=center>
 <TBODY>
  <TR>
    <TD width=100 height=1><FONT color=#993300><B>Leave From</B></FONT></TD>
    <TD width="180" height=1>
	<INPUT name=leavefrom size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,holiday.leavefrom, \"yyyy/mm/dd\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>	 
		 </td>
		 </tr>


    <TD width="66" height=1><B><FONT color=#993300>Leave To</FONT></B></TD>
    <TD width="187" height=1><INPUT name="leaveto" size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,holiday.leaveto, \"yyyy/mm/dd\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
}
</SCRIPT>	  </TD>
	</TR>
  <TR>
    <TD width="100" height=23><FONT color=#993300><B>Remarks</B></FONT></TD>
    <TD width="436" colSpan=3 height=23><INPUT  type="text" size=56 name=remarks></TD></TR>
  <TR>
    <TD width="538" colSpan=4 height=25>
      <P align=center>
	  <INPUT type=button value=Save name=save onclick=SaveRecord()>
	  <INPUT  type=button value="List Of Holidays" name=list onclick=ListRecord()> 
      <INPUT type=button value=Clear name=clear onclick=refresh()> 
      <INPUT  type=button value=Delete name=delete onclick=DeleteRecord()> 
	  <INPUT   type=hidden name=flag> 
	  <INPUT type=hidden name=all> 
	  <FONT color=#993300><B>
	   <INPUT onclick=check() type=checkbox value=""   name=alldelete>Delete All</B></FONT></P></TD></TR></CENTER>
  <td width="100">
  <DIV></DIV></TBODY></TABLE>
<%
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
try
{
String sErr = loadDriver();
con = cn();
st = con.createStatement();

if ( ! sErr.equals("") )
   {
 try {
   out.println(sErr);
     }
 catch (Exception e) {}
   }

String flag=request.getParameter("flag");
String all=request.getParameter("all");

if(flag!=null)
{

if(flag.equals("save"))
{
 String Ddate="";
 String ddt=request.getParameter("leavefrom");
 String idt=request.getParameter("leaveto");
 %>
 <script>
 alert("<%=ddt%>");
  alert("<%=idt%>");
 </script>
 <%
 String remark=getParam(request, "remarks");

		String no_of_days="";
		String sql="";
		int no=1;
		sql="select no_of_days=datediff(day,'"+ddt+"', '"+idt+"')";
		rs=st.executeQuery(sql);
		
		if(rs.next())
		{
		no_of_days=rs.getString("no_of_days");
		int n=Integer.parseInt(no_of_days);
		if(n<0)
		{
		%>
		<script>
			
			alert("Insufficient data");
			</script>
			<%
			}
			else
			{
		for(int i=0;i<=n;i++)
		{
		
			sql="select days=dateadd(day, "+i+", '"+ddt+"')";
			rs=st.executeQuery(sql);
					if(rs.next())
					{
						 Ddate=rs.getString("days");
						 
			
					sql="select * from holiday_mas where leave_date='"+Ddate+"'";
					out.print(sql);
    				rs=st.executeQuery(sql);
    						if(rs.next())
							{
								sql="update holiday_mas set leave_date='"+Ddate+"',add_day="+no+",remarks='"+remark.trim()+"' where leave_date='"+Ddate+"'";
								st.execute(sql);
							}
							else
							{
								sql="insert into holiday_mas values('"+Ddate+"',"+no+",'"+remark.trim()+"')";
								st.execute(sql);
							}
	
					}

}

%><SCRIPT>alert("Record Inserted Successfully");
            history.back();
		    location.href="index.jsp";
			</SCRIPT>
<%
}
}
}

if(flag.equals("list"))
  {
out.print("<br>");
String sql="select leave_date,Add_day,Remarks from holiday_mas ";
rs=st.executeQuery(sql);
String holiday_date, holiday_day,holiday_remarks;
out.print("<table border=1 cellpadding=0  cellspacing=0 >"+"<tr>"+"<th width=100>"+"Leave Date"+"</th>"+"<th width=75>"+"Add Day"+"</th>"+"<th width=200>"+"Remarks"+"</th>"+"</tr>");

while(rs.next())
 {
holiday_date=getholiday(rs.getString("Leave_Date"));
holiday_day=rs.getString("Add_Day");
holiday_remarks=rs.getString("Remarks");
if(holiday_remarks.equals("")){holiday_remarks=" ";}
%>
<tr onmouseover="this.style.color='#ff9900'" onclick='show("<%=holiday_date%>","<%=holiday_remarks%>")' onmouseout="this.style.color='black'">
<%
out.print("<td>"+holiday_date+"</td>"+"<td>"+holiday_day+"</td>"+"<td>"+holiday_remarks+"</td>");
out.print("</tr>");
}
out.print("</table>");
}

if(all.equals("delete"))
{
String delete_date=request.getParameter("leavefrom");

%>
<script>
alert("<%=delete_date%>");
</script>
<%
//String sql="delete from holiday_mas where Leave_date='"+delete_date+"'";
String sql="set dateformat dmy;delete from holiday_mas where Leave_date=convert(char(11),'"+delete_date+"',103)";
	out.print(sql);
	st.execute(sql);
	%><SCRIPT>alert("Record is Deleted Successfully");
	history.back();
		    location.href="index.jsp";
			</SCRIPT><%
}


if(all.equals("all"))
{
	String sql="delete  from holiday_mas"; 
	st.execute(sql);
	%> <SCRIPT>alert("All Records Deleted");
	history.back();
		    location.href="index.jsp";
			</SCRIPT><%
}
%><%
}

  }catch(Exception e){out.print(e);}
  finally{
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
if ( st != null ) st.close();
try{
if ( con != null ) con.close();
}catch(SQLException sl){out.println(sl.toString());}
  }
  %>
  <SCRIPT>
function SaveRecord()
{
if(document.holiday.leavefrom.value=="" && document.holiday.leaveto.value=="" && document.holiday.remarks.value=="")
{alert("Insuffician Data");}
else{document.holiday.flag.value="save";}
document.holiday.method="post";
document.holiday.action="index.jsp";
document.holiday.submit();
}


function DeleteRecord()
{
check();
document.holiday.method="post";
document.holiday.flag.value="delete";
document.holiday.action="index.jsp";
document.holiday.submit();
}

function ListRecord()
{
document.holiday.all.value="";
//document.holiday.deleteall.value="";
document.holiday.method="post";
document.holiday.flag.value="list";
document.holiday.action="index.jsp";
document.holiday.submit();
}


function show(val1,val2)
{
document.holiday.remarks.value=val2;
document.holiday.leavefrom.value=val1;
document.holiday.leaveto.value=val1;
}

function check()
{
if(document.holiday.alldelete.checked==true)
{document.holiday.all.value="all";}
else{document.holiday.all.value="delete";}
}

function refresh()
{
document.holiday.submit();
}
</SCRIPT>




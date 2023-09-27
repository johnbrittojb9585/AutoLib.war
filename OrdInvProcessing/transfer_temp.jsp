<%
String Transfer_Invoice="";
int iLevel=0; String list="";
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
String flag=request.getParameter("flag");
if(flag!=null){
String Flag_Status="", invoice_no="",invoice_date="",invoice_day="",invoice_month="",invoice_year="",Accto="",Accfrom="",sql="";
String inv_no_trans=request.getParameter("invno");
String inv_date_trans=request.getParameter("invdate");
String slno=request.getParameter("slno");
String ordno=request.getParameter("ordno");
String orddate=request.getParameter("orddate");
String invamount=request.getParameter("invamount");
String year=request.getParameter("year");
String docname=request.getParameter("docname");
String R1=request.getParameter("R1");
String paid=request.getParameter("paid");
String paiddate=request.getParameter("paiddate");
String mode=request.getParameter("mode");
String paydetails=request.getParameter("paydetails");
String add1=request.getParameter("add1");
String add2=request.getParameter("add2");
Accto=request.getParameter("AccTo");
Accfrom=request.getParameter("AccFrom");
Transfer_Invoice=inv_no_trans +";"+inv_date_trans;
//session.setAttribute("sess",slno);
%><script>document.Trans.InvNo.value="<%=Transfer_Invoice%>";</script><%
if(flag.equals("transfer_f")){
String invtext=request.getParameter("InvNo");
java.util.StringTokenizer stz1=new java.util.StringTokenizer(invtext,";");
						String in_no=stz1.nextToken();
						String in_date=stz1.nextToken();
						Accto=request.getParameter("AccTo");
//String sess1=session.getAttribute("sess").toString();

sql="select inv_no,inv_date from or_inv_pay where inv_no='" +in_no+ "' and inv_date='" +in_date+ "'" ;
rs=st.executeQuery(sql);
if(rs.next())
     {
	/*
        String sno=rs.getString("sno");
	String ord_no=rs.getString("ord_no");
	String ord_date=rs.getString("ord_date");
	String inv_no=rs.getString("inv_no");
	String inv_date=rs.getString("inv_date");
	String amount=rs.getString("amount");
	String year1=rs.getString("year");
	String doc_type=rs.getString("doc_type");
	String cr_deb=rs.getString("cr_deb");
	String paid1=rs.getString("paid");
	String pay_date=rs.getString("pay_date");
	String mode1=rs.getString("mode");
	String pay_details=rs.getString("pay_details");
	String addf=rs.getString("add1");
	String adds=rs.getString("add2");
	if(sno==slno)
	{ */
         sql="update book_mas set  invoice_no='" +in_no+ "',Invoice_Date='" +in_date+ "' where access_no  between '"+  Accfrom +"' and '"+ Accto+"'";
//out.print(sql);
st.execute(sql);
%><script language="JavaScript">
      alert("Record Transfered Successfully!");
       window.history.back();
	//location.href="transfer.jsp";
</script><%

   }
else
{%>
<script>
 alert("The Invoice Number or Invoice Date is not saved. Please Save that before proceed....");
window.history.back();
location.href="index.jsp";
</script>
<%
}

}

if(flag.equals("list"))
{
String inv=request.getParameter("InvNo");
%><script>document.Trans.InvNo.value="<%=inv%>";</script><%
sql="select * from book_mas";
rs=st.executeQuery(sql);
out.print("<table  border=1 bordercolor=#ff0000 cellpadding=0 cellspacing=0 align=center>");
			  out.print("<tr><th>Access Number</th><th>Title</th><th>Author Name</th><th>Language</th><th>Year Of Publication</th><th>Cost</th><br>");
			 while(rs.next())
                         {
String access_no=rs.getString("Access_no");
String title=rs.getString("title");
String author_name=rs.getString("author_name");
String language=rs.getString("language");
String year_pub=rs.getString("year_pub");
String bcost=rs.getString("bcost");
%>
<tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' onclick='show("<%=access_no%>")'>
<script>
document.write("<td>"+"<%=access_no%>"+"</td>");
document.write("<td>"+"<%=title%>"+"</td>");
document.write("<td>"+"<%=author_name%>"+"</td>");
document.write("<td>"+"<%=language%>"+"</td>");
document.write("<td>"+"<%=year_pub%>"+"</td>");
document.write("<td>"+"<%=bcost%>"+"</td>");
document.write("</tr>");
</script><%
}
out.print("</table>");
}
}

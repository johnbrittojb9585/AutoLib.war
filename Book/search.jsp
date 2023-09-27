<%@ page language="java"  session="true" buffer="12kb" import="java.sql.*" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="bean.DbCon"/>
<jsp:setProperty name="bean" property="*"/>
<%
//
//   Filename:  Search.jsp
//   Form name: Book_Find
//
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name=Book_Find method=post action="search.jsp">
<input type=hidden name=sflag>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
      java.sql.Connection con=null;
      java.sql.Statement st=null;
      java.sql.ResultSet rs=null;
      out.print("hai "+con);
try{
String flag=null,sflag=null,SQLstr="",Title="",t1="",t2="",t3="",nm="",f1="",f2="",f3="",mainTitle="";
con = bean.getConnection();
st = con.createStatement();
flag=request.getParameter("flag");
sflag=request.getParameter("sflag");
nm=request.getParameter("name");

/*-----------------------------------BOOK_SEARCH_FLAG-----------------------------------------------------------------------------------*/
if (flag!=null){
if (flag.equals("accessNo")){
mainTitle="Book Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";
SQLstr="select * from book_mas where 2>1 and title like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Dept")){
 mainTitle="Department Search";Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
SQLstr="select * from department_mas where 2>1 and Dept_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Aut")){
 mainTitle="Author Search";Title="Author Name";t2="Author_Name";t1="Author_Code";t3="Short_Desc";
SQLstr="select * from author_mas where 2>1 and author_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Sub")){
 mainTitle="Subject Search";Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
SQLstr="select * from subject_mas where 2>1 and sub_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Sup")){
 mainTitle="Supplier Search";Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
SQLstr="select * from sup_pub_mas where 2>1 and SP_name like ('" + nm + "%') and sp_type='SUPPLIER'";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Pub")){
 mainTitle="Publisher Search";Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
SQLstr="select * from sup_pub_mas where 2>1 and SP_name like '" + nm + "%' and  sp_type='PUBLISHER' ";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Group")){
 mainTitle="Group Search";Title="Group Name";t1="Group_Code";t2="Group_Name";t3="Remarks";
SQLstr="select * from group_mas where 2>1 and Group_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Inv")){
 mainTitle="Invoice Search";Title="Invoice";t1="Inv_no";t2="inv_date";t3="inv_amount";
SQLstr="select * from invoice_mas where 2>1 and inv_no like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Bud")){
 mainTitle="Budget Search";Title="Budget Head ";t1="bud_code";t2="bud_head";t3="bud_Amount";
SQLstr="select * from budget_mas where 2>1 and bud_head like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Branch")){
 mainTitle="Branch Search";Title="Branch Head ";t1="branch_code";t2="branch_name";t3="short_desc";
SQLstr="select * from branch_mas where 2>1 and branch_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}


}
else if (sflag!=null){
if (sflag.equals("accessNo")){
mainTitle="Book Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";
SQLstr="select * from book_mas where 2>1 and title like ('" + nm + "%')";
}
else if (sflag.equals("Dept")){
 mainTitle="Department Search";Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
SQLstr="select * from department_mas where 2>1 and dept_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Aut")){
 mainTitle="Author Search";Title="Author Name";t2="Author_Name";t1="Author_Code";t3="Short_Desc";
SQLstr="select * from author_mas where 2>1 and author_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Sub")){
 mainTitle="Subject Search";Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
SQLstr="select * from subject_mas where 2>1 and sub_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Sup")){
 mainTitle="Supplier Search";Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
SQLstr="select * from sup_pub_mas where 2>1 and SP_name like ('" + nm + "%') and sp_type='SUPPLIER' " ;
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Pub")){
 mainTitle="Publisher Search";Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
SQLstr="select * from sup_pub_mas where 2>1 and SP_name like '" + nm + "%' and sp_type='PUBLISHER'";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Group")){
 mainTitle="Group Search";Title="Group Name";t1="Group_Code";t2="Group_Name";t3="Remarks";
SQLstr="select * from group_mas where 2>1 and Group_name ilike ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Inv")){
 mainTitle="Invoice Search";Title="Invoice";t1="Inv_no";t2="inv_date";t3="inv_amount";
SQLstr="select * from invoice_mas where 2>1 and inv_no like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Bud")){
 mainTitle="Budget Search";Title="Budget Head";t1="bud_code";t2="bud_head";t3="bud_Amount";
SQLstr="select * from budget_mas where 2>1 and bud_head like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}
else if (sflag.equals("Branch")){
 mainTitle="Branch Search";Title="Branch Head ";t1="branch_code";t2="branch_name";t3="short_desc";
SQLstr="select * from branch_mas where 2>1 and branch_name like ('" + nm + "%')";
%>
<script>document.Book_Find.sflag.value="<%=sflag%>";</script>
<%
}

}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

<h2> <%=mainTitle%></h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center>
</form>
</body>
</html>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%

rs=st.executeQuery(SQLstr);
out.print("<center>");
out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
out.print("<tr><th>" +t1+"<th>"+t2+"<th>"+t3+"</tr>");
  while(rs.next()){
     f1=rs.getString(t1);
     f2=String.valueOf(bean.search(rs.getString(t2)));
	 f3=String.valueOf(bean.search(rs.getString(t3)));
	if(f3.equals("")){f3="Nil";}
   %>
	<!--
      //////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE////////////////////////////////////////////////////////////// --> 
  <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=f1%>","<%=f2%>")'>
  
  <script language=javascript>
 document.write("<td>"+"<%=f1%>" +"</td>");
 document.write("<td>"+"<%=f2%>" +"</td>");
 document.write("<td>"+"<%=f3%>"+"</td>");
 document.write("</tr></center>");
 </script>	
<%							   
   }
     }catch (Exception e) {
	 //out.println(e.toString());
	  }
	   finally{
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
if ( st != null ) st.close();
try{
if ( con != null ) con.close();
}catch(SQLException sl){out.println(sl.toString());}
  }

%>

<!--
//////////////////////////////////////JAVA SCRIPT USING CLIENT SIDE///////////////////////////////////////////////// --> 
<script  language="javascript">
function show(val,vall){
//document.Book_Find.data.value=val;
if(document.Book_Find.sflag.value=="accessNo"){
window.opener.document.Book.accessNo.value=val;
window.opener.document.Book.method="post";
window.opener.document.Book.flag.value="search";
//window.opener.document.Book.action="index.jsp";
window.opener.document.Book.submit();
}
if(document.Book_Find.sflag.value=="Aut")
{
if(window.opener.document.Book.author.value!=""){
window.opener.document.Book.author.value=window.opener.document.Book.author.value+";"+vall;
window.opener.document.Book.Author_value.value=window.opener.document.Book.Author_value.value+";"+val;
}
else
{
window.opener.document.Book.author.value=vall;
window.opener.document.Book.Author_value.value=val;
}
}
if(document.Book_Find.sflag.value=="Dept")
{
window.opener.document.Book.Dept.value=val;
window.opener.document.Book.deptName.value=vall;
}
if(document.Book_Find.sflag.value=="Sub")
{
window.opener.document.Book.Sub.value=val;
window.opener.document.Book.subName.value=vall;
}
if(document.Book_Find.sflag.value=="Pub"){
window.opener.document.Book.Pub.value=val;
window.opener.document.Book.Publisher.value=val;
window.opener.document.Book.pubName.value=vall;
window.opener.document.Book.publisherName.value=vall;
}
if(document.Book_Find.sflag.value=="Inv"){
window.opener.document.Book.invNo.value=val;
window.opener.document.Book.invoiceDate.value=val;
window.opener.document.Book.InvDateVal.value=vall;
}
if(document.Book_Find.sflag.value=="Bud"){
window.opener.document.Book.Bud.value=val;
window.opener.document.Book.budName.value=vall;
}
if(document.Book_Find.sflag.value=="Sup"){
window.opener.document.Book.Sup.value=val;
window.opener.document.Book.supName.value=vall;
}
if(document.Book_Find.sflag.value=="Branch"){
window.opener.document.Book.Branch.value=val;
window.opener.document.Book.branchName.value=vall;
}

if(document.Book_Find.sflag.value=="Cur"){
window.opener.document.Book.currency.value=vall;
}
window.close();
}
function focuss(){
document.Book_Find.name.focus();
}
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USING CLIENT SIDE///////////////////////////////////////////////// --> 

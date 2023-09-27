<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.Stock.StockSearchBean"%>
<%@ include file="/Common.jsp" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:book_find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" >
<form name=Stock_Book_Find method=post action=./StockSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="",mainTitle="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("Find_Access")){
mainTitle="Book Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";

}

}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div align=center><h2><%=mainTitle%></h2></div>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name><input type=submit value="Search"></center>
<input type=hidden name=sflag value="<%=flag%>">
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){

  if (flag.equals("Find_Access")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Access&nbsp;No<th>Title<th>Author</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				StockSearchBean view=(StockSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAccess_no()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getAccess_no()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAuthor()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}
		}
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.stock.access.value=val;
window.close();
}

function focuss(){
document.Stock_Book_Find.name.focus();
}
</script>


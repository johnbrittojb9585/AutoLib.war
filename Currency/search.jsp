<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Lib.Auto.Currency.CurrencyBean" import="Common.Security" import="java.util.*"%>
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Currency_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Currency_Find" method="get" action=./CurrencyServlet ><!--onsubmit="return validate()"-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<input type=hidden name=flag value=currency>
<!-//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////// --> 

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Currency Search</h2></div>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Currency Name</b></font><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center></form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// --> 
<%
String ck=request.getParameter("check");
String loadServlet=request.getParameter("flag");
ArrayList sc=new ArrayList();
try{
	
	 if(loadServlet.equals("loadServlet"))
		{
	    %>
	         <script language="JavaScript">		
	       
			   document.Currency_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
	int i=1;
	sc=(ArrayList)request.getAttribute("serarch");
		%>
		<script language="javascript">
		document.Currency_Find.name.value="<%=request.getParameter("nam")%>";
		document.Currency_Find.name.focus();
		</script>
		<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>Currency Name<th>AED Value<th>Short Desc</tr>");
		Iterator it=sc.iterator();

		while(it.hasNext()){
			CurrencyBean view=(CurrencyBean) it.next();
			
			
			%>
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
	<script language=javascript>
	 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
	 document.write("<td>"+"<%=i++%>" +"</td>");		 
	 document.write("<td>"+"<%=view.getCurrency()%>" +"</td>");
 	 document.write("<td>"+"<%=view.getIndian_value()%>" +"</td>");
	 document.write("<td>"+"&nbsp;<%=view.getRemarks()%>"+"</td>");
	 document.write("</tr>");      
 		</script>	
		<%
		}

	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
   sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.Currency.Code.value=val;
window.opener.document.Currency.method="get";
window.opener.document.Currency.flag.value="search";
window.opener.document.Currency.action="./CurrencyServlet";
window.opener.document.Currency.submit();
window.close();
}
function focuss(){
document.Currency_Find.name.focus();
}

function validate()
{
if(t1())
{
alert("Please enter the CurrencyName");
return false;
}
 else
{
return true;
}

   }

function t1()
{
if(document.Currency_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Currency_Find.submit();
}


</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<%@ page language="java"  session="true" buffer="12kb" import="Common.Security"  import="java.util.*"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Counter.CounterBean" >
</jsp:useBean>




<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//file:/opt/tomcat/webapps/AutoLib
//   Filename: Receipt.jsp
//   Form name:receipt
//
%>


<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>Auto Lib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>

<body  onload="prints()"><!-- background="/AutoLib/soft.jpg"onload='javascript:window.print();'-->
<form name="Author_Find" method="post"  >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

<div><A href="" onclick="window.close()">Back</A></div>

</form>

</body>
</html>
<script>
function prints()
{
window.print();
window.close();
}
</script>
<%
ArrayList sc=new ArrayList();
 boolean slip=true;
String fine="";
 try{
if(true){
		sc=(ArrayList)bean.getAl();
		if(sc.size()>0)
		{

		out.print("<table  border=0 align=center cellspacing=0 >");
		int j=1;
		for(int i=0; i<sc.size();i++){
		fine=String.valueOf(sc.get(i+=3));
		i=i+sc.size();

		}

		 boolean isNumFound=false;
		          if(Character.isDigit(fine.charAt(0)))
		           isNumFound=true;
			   if(isNumFound)
			   {

		out.print("<tr><center>Autolib Software Systems</center></tr>");
		out.print("<tr><center>Return Slip</center></tr>");
		for(int i=0; i<sc.size();i++){
		if(slip)
		{

		 out.print("<tr><td>Member&nbsp;Code&nbsp;:</td>");
		 out.print("<td>"+sc.get(i)+"</td></tr>");
		  out.print("<tr><td>Member&nbsp;Name&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td colspan=2><center>------------</center><td></tr>");
		  slip=false;
		   i=i+1;
		 }
		 else
		 {
		  i=i+2;
		  }
		  out.print("<tr><td>"+j+".Access&nbsp;No&nbsp;:</td>");
		  out.print("<td>"+sc.get(i)+"</td></tr>");
		  out.print("<tr><td>Fine&nbsp;&nbsp;&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td>Due&nbsp;Date&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td>Return&nbsp;Date&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td>");
		  out.print("</tr>");
		  j++;
		  }
			   }
			   else
			   {
		out.print("<tr><center>Autolib Software Systems</center></tr>");
		out.print("<tr><center>Issue Slip</center></tr>");
		for(int i=0; i<sc.size();i++){
		if(slip)
		{

		 out.print("<tr><td>Member&nbsp;Code&nbsp;:</td>");
		 out.print("<td>"+sc.get(i)+"</td></tr>");
		  out.print("<tr><td>Member&nbsp;Name&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td colspan=2><center>------------</center><td></tr>");
		  slip=false;
		   i=i+1;
		 }
		 else
		 {
		  i=i+2;
		  }
		  out.print("<tr><td>"+j+".Access&nbsp;No&nbsp;:</td>");
		  out.print("<td>"+sc.get(i)+"</td></tr>");
		  out.print("<tr><td>Title&nbsp;&nbsp;&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td>Issue&nbsp;Date&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td></tr>");
		  out.print("<tr><td>Due&nbsp;Date&nbsp;:</td>");
		  out.print("<td>"+sc.get(++i)+"</td>");
		  out.print("</tr>");
		  j++;
		  }
			   }





		}
		else
		{
		out.print("<tr><center>Resource Not Issued/Returned !!</center></tr>");
		}
	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
    sc.clear();
    }

%>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 




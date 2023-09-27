<%@page import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js" ></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/tamil.js" ></script>
<%//Security.checkSecurity(1,session,response,request);%>


<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Author_Find
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
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Author_Find" method="post" action=./AuthorServlet >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div><H2>Check&nbsp;Column&nbsp;Order</h2></div>
<div>    <A href="" onclick="window.close()">Back</A></div>
<center>

</center></form>

</body>
</html>

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Column Header<th>Column Name</tr>");
           

			
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
	
		 document.write("<td>"+"A" +"</td>");	
		 document.write("<td>"+"Access No" +"</td>");	
		 document.write("</tr>");    
 		</script>
 			
 		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"B" +"</td>");	
		 document.write("<td>"+"Call No" +"</td>");	
		 document.write("</tr>");    
 		</script>	
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"C" +"</td>");	
		 document.write("<td>"+"Title" +"</td>");	
		 document.write("</tr>");    
 		</script>	
		
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"D" +"</td>");	
		 document.write("<td>"+"Author Name" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"E" +"</td>");	
		 document.write("<td>"+"Received_date" +"</td>");	
		 document.write("</tr>");    
 		</script>
 			
 		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"F" +"</td>");	
		 document.write("<td>"+"Pubisher" +"</td>");	
		 document.write("</tr>");    
 		</script>	

        <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"G" +"</td>");	
		 document.write("<td>"+"Publisher Year" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"H" +"</td>");	
		 document.write("<td>"+"Supplier" +"</td>");	
		 document.write("</tr>");    
 		</script>	

        <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"I" +"</td>");	
		 document.write("<td>"+"Subject" +"</td>");	
		 document.write("</tr>");    
 		</script>

		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"J" +"</td>");	
		 document.write("<td>"+"Department" +"</td>");	
		 document.write("</tr>");    
 		</script>
		
		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"K" +"</td>");	
		 document.write("<td>"+"Document Type" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		 
		 document.write("<td>"+"L" +"</td>");	
		 document.write("<td>"+"Language" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"M" +"</td>");	
		 document.write("<td>"+"Location" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		 
		 document.write("<td>"+"N" +"</td>");	
		 document.write("<td>"+"Price" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"O" +"</td>");	
		 document.write("<td>"+"Discount" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"P" +"</td>");	
		 document.write("<td>"+"Net Price" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"Q" +"</td>");	
		 document.write("<td>"+"Invoice No" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"R" +"</td>");	
		 document.write("<td>"+"Invoice Date" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		 
		 document.write("<td>"+"S" +"</td>");	
		 document.write("<td>"+"Edition" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		 
		 document.write("<td>"+"T" +"</td>");	
		 document.write("<td>"+"Volume No" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"U" +"</td>");	
		 document.write("<td>"+"Pages" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"V" +"</td>");	
		 document.write("<td>"+"Keywords" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"W" +"</td>");	
		 document.write("<td>"+"ISBN" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"X" +"</td>");	
		 document.write("<td>"+"Purchase" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		 
		 document.write("<td>"+"Y" +"</td>");	
		 document.write("<td>"+"Availability" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"Z" +"</td>");	
		 document.write("<td>"+"Notes" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"AA" +"</td>");	
		 document.write("<td>"+"Add Field1" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"AB" +"</td>");	
		 document.write("<td>"+"Add Field2" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"AC" +"</td>");	
		 document.write("<td>"+"Division" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		
 		 <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' >
		<script language=javascript>		 
		
		 document.write("<td>"+"AD" +"</td>");	
		 document.write("<td>"+"Budget" +"</td>");	
		 document.write("</tr>");    
 		</script>
 		note:dont delete/remove coumns in excel file
 
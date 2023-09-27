<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.NewsClipSearch.NewsSearchbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">

</jsp:useBean>

<!-- #include file="logo.html" -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body >

<%

ArrayList sc=new ArrayList();

sc=(ArrayList)request.getAttribute("SearchArrylist");

Iterator it=sc.iterator();
%>




  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

 <center>
    <h2 >Full View</h2>
</center>
    
    <%
    while(it.hasNext()){
    	NewsSearchbean view=(NewsSearchbean) it.next();
    
    %>
    <center>
    <table>
            <tr>
              <td Class="addedit" align="left">Code</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getNcode()%>     </td>
            </tr>           
            <tr>
           <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getNtitle()%>
              </td>
            </tr>
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getNname()%> </td>
            </tr>
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Subject</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getNsubject()%></td>
	         </tr>
            <tr>
           </tr> 
            <tr>
              <td Class="addedit" align="left">Type</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getNtype()%></td>
            </tr>
            <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Keywords</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getNkey()%></td>
            </tr>
           
       <%
            String cont=view.getNcontent();
            if((!cont.equals(""))&&(!cont.equals(null))){
            %>     
            
            <tr>
            </tr>
            <tr>
            <td Class="addedit" align="left">Content</td>
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/NEWS CLIPPING/<%=view.getNcontent()%> " target=_base>Click to Contents</a></td>            
            </tr>
            <% 
    } 
               }			
			sc.clear();

			%>


</table>
 </center>
 <p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
 
    </form>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</body>
<script>
function back()
{
	location.href="./index.jsp"
}
</script>
</html>







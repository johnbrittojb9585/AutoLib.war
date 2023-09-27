<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.EBook.EBookBean" import="Common.Security" import="java.util.*"%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.EBook.EBookBean"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

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

  <form method="get" name="query" action="./EBookSearchServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

    <h2>Full View</h2>
    
    <%
    while(it.hasNext()){
    	EBookBean view=(EBookBean) it.next();
  
    %>
   
    <table align="center">
            
            <%if(!view.getAccessNo().equalsIgnoreCase("") && !view.getAccessNo().isEmpty() && !view.getAccessNo().equalsIgnoreCase(null)){%>
            <tr>
              <td Class="addedit" align="left">Access No</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getAccessNo() %>     </td>
            </tr>
            <%}%>           
            
           <%if(!view.getCallNo().equalsIgnoreCase("") && !view.getCallNo().isEmpty() && !view.getCallNo().equalsIgnoreCase(null)){%> 
           <tr>
              <td Class="addedit" align="left">Call No</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getCallNo() %></td>
            </tr>
            <%}%>
           
           <%if(!view.getTitle().equalsIgnoreCase("") && !view.getTitle().isEmpty() && !view.getTitle().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getTitle() %></td>
            </tr>
            <%}%>
            
            <%if(!view.getEdition().equalsIgnoreCase("") && !view.getEdition().isEmpty() && !view.getEdition().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Edition</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getEdition() %></td>
            </tr>
            <%}%>
            
            <%if(!view.getUrl().equalsIgnoreCase("") && !view.getUrl().isEmpty() && !view.getUrl().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">ISBN</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getUrl() %></td>
            </tr>
            <%}%>
            
            <%if(!view.getIsbn().equalsIgnoreCase("") && !view.getIsbn().isEmpty() && !view.getIsbn().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">E-Book URL</td>
             <td Class="addedit" align="left">:&nbsp;<a href="<%=view.getIsbn() %>" target="_blank"><%=view.getIsbn() %></a></td>
            </tr>
            <%}%>
            
            <%if(!view.getAuthorName().equalsIgnoreCase("") && !view.getAuthorName().isEmpty() && !view.getAuthorName().equalsIgnoreCase(null)){%>           
            <tr>            
              <td Class="addedit" align="left">Author Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAuthorName() %></td>
            </tr>
            <%}%>
            
            <%if(!view.getPubName().equalsIgnoreCase("") && !view.getPubName().isEmpty() && !view.getPubName().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Publisher</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getPubName() %></td>
            </tr>
            <%}%>
            
            <%if(!view.getYop().equalsIgnoreCase("") && !view.getYop().isEmpty() && !view.getYop().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Year</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getYop() %></td>
            </tr>
            <%}%>
            
       <%if(!view.getPages().equalsIgnoreCase("") && !view.getPages().isEmpty() && !view.getPages().equalsIgnoreCase(null)){%>
            	<tr>   
                 <td Class="addedit" align="left">Pages</td>
                 <td Class="addedit" align="left">:&nbsp;<%=view.getPages() %></td>
               </tr>
         <%}%> 
             
            <%if(!view.getSubName().equalsIgnoreCase("") && !view.getSubName().isEmpty() && !view.getSubName().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Subject</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSubName() %></td>
            </tr>
            <%}%>
            
            <%-- <%if(!view.getDeptName().equalsIgnoreCase("") && !view.getDeptName().isEmpty() && !view.getDeptName().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Department</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDeptName() %></td>
            </tr>
            <%}%> --%>
            
            <%if(!view.getType().equalsIgnoreCase("") && !view.getType().isEmpty() && !view.getType().equalsIgnoreCase(null)){%>
            <tr>            
              <td Class="addedit" align="left">Course</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getType() %></td>
            </tr>
            <%}%>
            
            
<%
            String cont=view.getContent();
            if((!cont.equals(""))&&(!cont.equals(null))){
            %>
			<tr>
           </tr>
			<tr>
            <td Class="addedit" align="left">Content</td>
            <td Class="addedit" align="left">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/EBOOK/<%=view.getContent()%> " target=_base>Click for Contents</a></td>            
            </tr>
            	
            <%
            }
                        
}		
			sc.clear();

			%>

</table>

    </form>
    
</body>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	history.go(-1);
}
</script>

</html>








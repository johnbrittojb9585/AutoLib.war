<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean"
 import="Lib.Auto.Review.ReviewBean" import="Common.Security" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/starrating3.css"/>

    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
 
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Review.ReviewBean"  type="Lib.Auto.Review.ReviewBean">
</jsp:useBean>
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>

<%
	session.getAttribute("AccessNo");
	%>	
	
	<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("reviewSearchList");
%>
<%
Iterator it=sc.iterator();
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body>

  <form method="post" name="query" action="./ReviewDisplayServlet"  ONSUBMIT="return validate(this)">
<table align=center>
<tr><td>  
 <center>
    <h2 >Book Reviews</h2>
</center></td>

<%
	int members =1;
	if(it.hasNext())
	{
    while(it.hasNext()){
    	ReviewBean view=(ReviewBean) it.next();
    	
    %>
  <table>
  
  <tr>
              <td Class="addedit" align="left">Access No</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getAccessNo()%>     </td>
            </tr>
          
            <tr>
              <td Class="addedit" align="left">Member Code</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getMemberCode()%></td>
            </tr>
           
            <tr>            
              <td Class="addedit" align="left">Review Date</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getRcDate()%></td>
            </tr>
            
	        <tr>
			<td Class="addedit" align="left">Review Title</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getTitle()%></td>
	        </tr>
            
            <tr>
              <td Class="addedit" align="left">Comments</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDesc()%></td>
              
            </tr>
            
<!--             <tr> -->
<!--               <td Class="addedit" align="left">Rating</td> -->
<%--               <td Class="addedit" align="left">:&nbsp;<%=view.getRating()%></td> --%>
              
<!--             </tr> -->
  <tr>
<td Class="addedit" align="left">Rating</td>
<td Class="addedit" align="left">:&nbsp;
 <span class="rating">

 <%
   
  for (int noofStars = 5;noofStars>=1;noofStars--) {
	String rating_CSS_ID = "rating-input-1-"+noofStars;
 %> 
  
	 <input type="radio" class="rating-input"
			 value="<%=view.getRating()%>" name="rating<%=members%>" id="<%=rating_CSS_ID%>"  disabled="disabled"
		
<%=(view.getRating() == noofStars )?"checked":""%> >
	<label for="<%=rating_CSS_ID%>" class="rating-star"></label>
	  
<% 	
  }
 members++;  
 %>
</span> 
 </td>
 
 </tr>
 
    	   <tr>
              <hr/>
           </tr>
            
           <%        
}	
	}
	else
	{
		%>
		<tr><td Class="addedit" align="left"><font color=red size=3><b>This Document is not having Review</b></font></td></tr>
		<%}
 	sc.clear();

			%>
	 
</table>
</form>
</body>
</html>


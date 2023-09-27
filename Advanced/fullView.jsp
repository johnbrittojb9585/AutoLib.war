<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean" import="Lib.Auto.Review.ReviewBean" import="Common.Security" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/starrating.css"/>

<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">
</jsp:useBean>
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
String accNo="";
sc=(ArrayList)request.getAttribute("AdsearchArrylist");

Iterator it=sc.iterator();
%>
<%
ReviewBean view3 = new ReviewBean();
%>

  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br>
<input type=hidden name=accNo>
 <table align="center">
 <tr><td>
 <center>
    <h2 >Full View</h2>
</center>
</td>
 </tr>
 </table>
   
    <%
    while(it.hasNext()){
    	Adsearchbean view=(Adsearchbean) it.next();
    %>
     
    <center>
   
    <table>
    
     <%
           if(!view.getdocument().equalsIgnoreCase("Article"))
           {
%>
            <tr>
              <td Class="addedit" align="left">Access No</td>
              <td colspan="4" Class="addedit" align="left">:&nbsp;<%=view.getAccno()%></td>
              <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
         
            </tr>
           
            <tr>
           <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getTitle()%></td>
            </tr>
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Author</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAuthor()%> &nbsp; </td>
            </tr>
            <tr>
           </tr>
           
           <%
           String doc=view.getdocument();
           String call=view.getCallno();
           if((doc.equals("BOOK"))||(doc.equals("BACK BANK")) || (doc.equals("NON BOOK")))
        		   {
        	   if((!call.equals("")) && (!call.equals(null)))
        	   {
        	   %>
        		   
           <tr>
			<td Class="addedit" align="left">Call.No</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getCallno()%></td>
	         </tr>
         <% 
 			   }
   		 }
         %> 
           
          
	         
            <tr>
           </tr>
         <%
         	doc=view.getdocument();
         
         if((doc.equals("BOOK"))||(doc.equals("BOOK BANK")) || (doc.equals("NON BOOK")) || (doc.equals("STANDARD")))
      		   {
        	 %>
        	 
        	  <td Class="addedit" align="left">Publisher</td>

<!--              <td Class="addedit" align="left">:&nbsp;<%=view.getPlace()%>:&nbsp;<%=view.getPublisher()%>,&nbsp;<%=view.getYear()%></td>-->
              
              <td Class="addedit" align="left">:&nbsp;<%=view.getPublisher()%>,&nbsp;<%=view.getYear()%></td>
              
            </tr>
        	 
        	 <% 
      		 }
        	 %>
      		   
            <tr>

               <tr>
           </tr>
                
                <%
         String sub=view.getSubject();
           if(!sub.equalsIgnoreCase("NIL"))
        		   {
        	   %>
        	<tr>
              <td Class="addedit" align="left">Subject</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSubject()%></td>
            </tr>
         <% 
 			   }
         %>              
            <tr></tr>
             <%
            doc=view.getdocument();
           if((doc.equals("THESIS"))||(doc.equals("BACK VOLUME")))
        		   {
        	   %>
        		   
           <tr>
			<td Class="addedit" align="left">Year</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getYear()%></td>
	         </tr>
         <% 
 			   }
         %>   
            <tr>
           </tr>
           
            <%
            doc=view.getdocument();
           if((doc.equals("BACK VOLUME")))
        		   {
        	   %>
        		   
           <tr>
			<td Class="addedit" align="left">Volume NO</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getVolno()%></td>
	         </tr>
	         <tr>
			<td Class="addedit" align="left">Issues</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getIssues()%></td>
	         </tr>
	         
         <% 
 			   }
         %>   
           <%
           if(!view.getdepartment().equals(""))
           {
%>
               <tr>
                 <td Class="addedit" align="left">Department</td>
               <td Class="addedit" align="left">:&nbsp;<%=view.getdepartment()%></td>
               </tr>
               <tr>
              </tr>
              <%
           }
           %>
           
            <tr>
              <td Class="addedit" align="left">Document</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getdocument()%></td>
            </tr>
            <tr><td><br></td>
            </tr>

          
		<!-- 	<tr>
              <td Class="addedit" align="left">Price</td>
		      <td Class="addedit" align="left">:&nbsp;<%=view.getprice()%></td>
            </tr> -->
            
<%
            String cont=view.getcontent();
            if((!cont.equals(""))&&(!cont.equals(null))){ 
            %>
			<tr>
           </tr>
			<tr>
              <td Class="addedit" align="left">Content</td>
<!--            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/Contents/Book/<%=view.getcontent()%> " target=_base>Click to Contents</a></td>-->
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/<%= view.getdocument() %>/<%=view.getcontent()%> " target=_base>Click to Contents</a></td>                        
            </tr>
            
            <%
            }
            
            String res=view.getavailability();  
            if(res.equals("ISSUED")){ 
            %>
            <tr><td><br><br></td></tr>
			<tr> 	
			<td></td>
			<td Class="addedit" align="left"><a href="AccountServlet?reservecheck=<%=view.getAccno()%>&document=<%=view.getdocument()%>">Reserve</a></td>
			        
            </tr>
            <tr><td><br><br></td></tr>   
                    
            <%
            }
            %>
            
            
             <%
           }
            %>


				<!-- Article Full View Starts -->
						<%
           if(view.getdocument().equalsIgnoreCase("Article"))
           {
%>
            <tr>
              <td Class="addedit" align="left">Atl No</td>
              <td colspan="4" Class="addedit" align="left">:&nbsp;<%=view.getAccno()%></td>
              <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
         
            </tr>
           
            <tr>
           <tr>
           </tr>
           <tr>
              <td Class="addedit" align="left">Jnl. Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getJournal()%></td>
            </tr>
           <tr>
              <td Class="addedit" align="left">Atl. Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getTitle()%></td>
            </tr>
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Atl. Author</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAuthor()%> &nbsp;/&nbsp;<%=view.getSRes()%>  </td>
            </tr>
            <tr>
           </tr>
                          		   
            
                
                <%
         String sub=view.getSubject();
           if(!sub.equalsIgnoreCase("NIL"))
        		   {
        	   %>
        	<tr>
              <td Class="addedit" align="left">Subject</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSubject()%></td>
            </tr>
         <% 
 			   }
         %>              
            <tr></tr>
            <tr>            
              <td Class="addedit" align="left">Volume No</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getVolno()%> </td>
            </tr>
            <tr>
           </tr>
           

			<tr>            
              <td Class="addedit" align="left">Issue Year</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getYear()%> </td>
            </tr>
            <tr>
           </tr>
           
           <tr>            
              <td Class="addedit" align="left">Issue Month</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getIssueMonth()%> </td>
            </tr>
            <tr>
           </tr>
           
           <tr>            
              <td Class="addedit" align="left">Page No</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getPageNo()%> </td>
            </tr>
            <tr>
           </tr>
           
           
            <tr>
              <td Class="addedit" align="left">Document</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getdocument()%></td>
            </tr>
            <tr><td><br></td>
            </tr>

       
<%
            String cont=view.getcontent();
            if((!cont.equals(""))&&(!cont.equals(null))){ 
            %>
			<tr>
           </tr>
			<tr>
              <td Class="addedit" align="left">Content</td>
<!--            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/Contents/Book/<%=view.getcontent()%> " target=_base>Click to Contents</a></td>-->
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/<%= view.getdocument() %>/<%=view.getcontent()%> " target=_base>Click to Contents</a></td>                        
            </tr>
            
            <%
            }
            
           
            %>
                       
            
             <%
           }
            %>

				<!-- Article Full View Ends -->

				<%
					session.setAttribute("AccessNo", view.getAccno());
						session.setAttribute("Title", view.getTitle());
				%>
            
            
         
            <%        
}		
   
			sc.clear();

			%>
<!-- <tr><td> -->
<%-- <b><a href="<%= request.getContextPath() %>/Review/ReviewServlet?flag=new" style="color: #FF0000">Add Review</a></b> --%>
<!--     </td> -->
<!-- </tr> -->
</table>
 </center>
 
<%
   Adsearchbean view = new Adsearchbean();
   String s = view.getAccno();
   if(s != null)
%> 

<%-- <p align="center"><b><a  href="<%= request.getContextPath() %>/Review/ReviewDisplayServlet" target="filterFrame" style="color: #006400" >See  Reviews</a></b></p> --%>
<!-- <IFRAME frameborder="0" style="width: 90%; height: 300px;" marginwidth="0" marginheight="0" scrolling="yes" vspace="0" hspace="0" name="filterFrame" src="/AutoLib/Review/ReviewContents.html"> -->
<!--  </IFRAME> -->

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
<%
if(request.getParameter("Photo")!=null){
out.print("<table height=50 width=50><tr><td><img align=top src=/AutoLib/images/"+request.getParameter("Photo")+" height=75 width=75 border=0></td></tr></table>");
}
%>
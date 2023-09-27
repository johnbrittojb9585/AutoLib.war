<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Author.AuthorBean" import="Common.Security" import="java.util.*"%>
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
<div><H2>Author&nbsp;Search</h2></div>
<div><A href="" onclick="window.close()">Back</A></div>
<center>
<td>Author Name:<input type=text name=name  size=25></td>
<input type=button Class="submitButton" value="Search" onclick=SaveRec()>
<input type=hidden name=flag  value="Author">
</center></form>

</body>
</html>

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<%


String ck=request.getParameter("check");
String load=request.getParameter("flag");

ArrayList sc=new ArrayList();
try{
	if(load.equals("loadServlet"))
	{
    %>
         <script language="JavaScript">		
		   document.Author_Find.submit();		
		</script>
		<%
		
	}
		
if(ck!=null){

	         int i=1;
             sc=(ArrayList)request.getAttribute("serarch");
      %>
  <script language="JavaScript">
document.Author_Find.name.value="<%=request.getParameter("nam")%>";
document.Author_Find.name.focus();
</script>
<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Author Name<th>Short Desc</tr>");
           Iterator it=sc.iterator();

			while(it.hasNext()){
				AuthorBean view=(AuthorBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");		 
		 document.write("<td>"+"<%=view.getName()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view. getDesc()%>"+"</td>");
		 document.write("</tr>");    
 		</script>	
		<%
			}
			
			sc.clear();








}


 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.Author.code.value=val;
window.opener.document.Author.method="get";
window.opener.document.Author.flag.value="search";
window.opener.document.Author.action="./AuthorServlet";
window.opener.document.Author.submit();
window.close();
}

function SaveRec(){
             document.Author_Find.method="post";
			   if(!isWhitespace(document.Author_Find.name.value)){		   
			        document.Author_Find.name.value=document.Author_Find.name.value+" ";
		         	document.Author_Find.submit();	
					
			}		
	else
	{
	document.Author_Find.submit();
	//alert("Insufficent Data");
	}
	
}
function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
function validate()
{
if(t1())
{
alert("Please enter the AuthorName");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.Author_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Author_Find.submit();
}
function focuss(){
	document.Author_Find.name.focus();
}
</script>
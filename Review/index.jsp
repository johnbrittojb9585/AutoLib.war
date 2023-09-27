<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean" import="Lib.Auto.Review.ReviewBean" import="Login.User" import="Common.Security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
 buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/starrating3.css"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	
%>
<%
  session.getAttribute("AccessNo");
  session.getAttribute("visitMember");
%>

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Review.ReviewBean"  type="Lib.Auto.Review.ReviewBean">
</jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>AutoLib</title>
</head>

<body>

<form method="post" name="review" action="./ReviewServlet">
<br><br>
    <h2>Book Review</h2>
    <%
     Adsearchbean view = new Adsearchbean();
    %>
<table align="center" class="contentTable" width="45%">
  <tr>
<td>
<table align="center" width="83%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">Review No</td>
<td><input type=text name="reviewNo" size=15  maxlength=10 onKeydown="Javascript: if (event.keyCode==13) return SearchRec();">
</td>
<td Class="addedit">Access&nbsp;No</td><td> <input type=text name=accessNo value="<c:out value="${AccessNo}"/>" size=15 maxlength=15 readonly></td>
</tr>

<tr>
<td Class="addedit">Member&nbsp;Id</td><td> <input type=text name=memberCode value="<c:out value="${UserID}"/>" size=15 maxlength=15 readonly></td>
<%-- <td Class="addedit">Member&nbsp;Name</td><td> <input type=text name=memberName value="<c:out value="${visitMember}"/>" size=15 maxlength=15 readonly></td> --%>
  
  <td Class="addedit">Date</td>
<td>
<INPUT name="rcDate" size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,review.rcDate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
				}
				
				</SCRIPT></td>
    </tr>
    
    <tr>
       <td Class="addedit">Review Title</td> <td colspan="4"><input type="text" name="title" value="<c:out value="${Title}"/>" size="55" maxlength=255 readonly></td>
    </tr>
    
<tr>            
<td Class="addedit">Comments<td colspan=3>
<textarea  rows="6" cols="43" name="desc" maxLength=2000  onkeypress="return noenter(event)"></textarea>
</td>
</tr>


<tr>
<td Class="addedit" align="left">Rating</td>
<td>
<span class="rating">
        <input type="radio" class="rating-input"
                id="rating-input-1-5" name="rating" value="5">
        <label for="rating-input-1-5" class="rating-star"></label>
        <input type="radio" class="rating-input"
                id="rating-input-1-4" name="rating" value="4">
        <label for="rating-input-1-4" class="rating-star"></label>
        <input type="radio" class="rating-input"
                id="rating-input-1-3" name="rating" value="3">
        <label for="rating-input-1-3" class="rating-star"></label>
        <input type="radio" class="rating-input"
                id="rating-input-1-2" name="rating" value="2">
        <label for="rating-input-1-2" class="rating-star"></label>
        <input type="radio" class="rating-input"
                id="rating-input-1-1" name="rating" value="1">
        <label for="rating-input-1-1" class="rating-star"></label>
</span>


</td></tr>



<tr><td>&nbsp;</td>
</tr>

</table></td></tr></table><br>
<p align="center">
<input type=button name="New" Class="submitButton" Value="New" onclick=new_no() >
<input type="submit" Class="submitButton" value="Save" name="add" onclick=SaveRec()>
<input type=button name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type="reset" Class="submitButton" value="Clear" name="clear" onclick=ClearRec()>
<input type="hidden" name="flag">
</p>

</form>

<%-- <%@ include file="/Tree/back.jsp"%> --%>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	location.href="../Simples/index.jsp";
}
</script>

<%
     String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newReview")){
	 %>
	<script language="JavaScript">
	document.review.reviewNo.value="<%=beanobject.getReviewNo()%>";
	
	document.review.desc.focus();
	</script><%
}

if(valid.equals("searchReview")){

    %>
     <script language="JavaScript">
     
     document.review.reviewNo.value="<%=beanobject.getReviewNo()%>";
     document.review.accessNo.value="<%=beanobject.getAccessNo()%>";
     document.review.memberCode.value="<%=beanobject.getMemberCode()%>";
     document.review.rcDate.value="<%=beanobject.getRcDate()%>";
     document.review.title.value="<%=beanobject.getTitle()%>";
     document.review.desc.value="<%=beanobject.getDesc()%>";
     document.review.rating.value="<%=beanobject.getRating()%>";
                    

   </script>
   <%
   }
   if(valid.equals("FailReview")){%>
               <script language="JavaScript">
   			alert("Record Not Found");
   			document.review.flag.value="new";
   			document.review.submit();
   		   	</script><%
   			}
   	if(valid.equals("UpdateReview")){%>
               <script language="JavaScript">
   			alert("Record Updated Successfully!");
   			document.review.flag.value="new";
   			document.review.submit();
   		   	</script><%
   			}
   	
   	if(valid.equals("UpdateCheck")){
		
		%>
		                <script language="JavaScript">
		                
		                document.review.reviewNo.value="<%=beanobject.getReviewNo()%>";
		                document.review.accessNo.value="<%=beanobject.getAccessNo()%>";
		                document.review.memberCode.value="<%=beanobject.getMemberCode()%>";
		                document.review.rcDate.value="<%=beanobject.getRcDate()%>";
		                document.review.title.value="<%=beanobject.getTitle()%>";
		                document.review.desc.value="<%=beanobject.getDesc()%>";
		                document.review.rating.value="<%=beanobject.getRating()%>";
		                      	                            
						   
		                msg=confirm("Record Already Exists Are You Sure To Update?");
		                 if(msg)
		                   {
						    document.review.flag.value="update";
				         	document.review.submit();

				            }
					    </script>
      <%
	}
 
 if(valid.equals("SaveReview")){
     %>             <script language="JavaScript">
     			 alert("Record Inserted Successfully!");
     			document.review.flag.value="new";
     			 document.review.submit();
     		     </script>		
     			<%
     			}
}
}
%>

 <script language=javascript>

 function home()
 {
 location.href="/AutoLib/Home.jsp";
 }

 function Logout()
 {
 location.href="/AutoLib/index.html";
 }
 
 function new_no(){

	 document.review.method="post";
	 document.review.flag.value="new";
	 document.review.submit();
	 }

	 function noenter(e) {
	     e = e || window.event;
	     var key = e.keyCode || e.charCode;
	  
	     return key !== 13;
	     
	 }

// 	 function RestrictEnterKey(){
	 	
// 	 	alert("sfsdf");
// 	 }

	 function ClearRec(){ 
		  
		 document.review.reviewNo.value="";
		 document.review.accessNo.value="";
		 document.review.memberCode.value="";
		 document.review.rcDate.value="";
		 document.review.title.value="";
		 document.review.desc.value="";
         document.review.rating.value="";
         
		 document.review.flag.value="";

         new_no();
}

	 function review_code_val() {
		 if((isNaN(document.review.reviewNo.value))||(document.review.reviewNo.value == ' ')) {
		 document.review.reviewNo.select();
		 document.review.reviewNo.value="";
		     
		   }
		 }
	 
	 function SearchRec(){

		 document.review.method="post";
		 var no=document.review.reviewNo.value;
		 if(no==""){		 				
		 				document.review.flag.value="new";
		 				document.review.submit();
		 		  }else{
		 		       document.review.flag.value="search";
		 			   document.review.submit();
		 			  
		 		}
		 	
		 }

	 
	 function UpdateRec(){
         document.review.method="post";
		   if(chk() ){
		       	document.review.flag.value="update";
	         	document.review.submit();	
				
		}		
else
{
alert("Insufficent Data");
}

}
	  
function SaveRec(){
if(chk_mc()){
document.review.method="post";
 if((!isWhitespace(document.review.desc.value)) && (!isWhitespace(document.review.rating.value))){
	 document.review.desc.value=document.review.desc.value+" ";		        
			    	document.review.flag.value="save";
		         	document.review.submit();	
		}		
	else
	{	
	  alert("Insufficent Data");
	  document.review.flag.value="new";
	  document.review.submit();	
	}
		}	
else {		
      alert("Insufficient Data");
	}
}	
 function chk_mc(){
var flag_cs;
var c;
var mc=document.review.reviewNo.value;
if(mc=="")
{
				document.review.desc.focus();
				document.review.flag.value="none";
				document.review.reviewNo.value="";
				return false;
			}
				else{
		                    return true;
	                } 
 }
 
 function isWhitespace(str)
	{

		var re = /[\S]/g
		if (re.test(str)) return false;
		return true;
		
	}
	
function chk(){
var flag_s;
var i;
var sp=document.review.desc.value;
var d=sp.replace(" ");
alert(d);

if(d=="")
{

document.review.desc.focus();
			document.review.flag.value="none";
			document.review.desc.value="";
			return false;
			}
			else
			{
              for(i=0;i<document.review.desc.value.length;i++)
                    {
                     if(document.review.desc.value.charAt(i)=="")
                        {flag_s=0; }
                              else{flag_s=1;}
                    }
	                  if (flag_s==0)   
	                    {
	                   	document.review.desc.focus();
	                   	document.review.desc.value="";
		                return false;
	                      }
	                   else if (document.review.desc.value==""){
	                 	document.review.reviewNo.focus();
		                return false;
	                      }
 else{
	return true;
	}
}
}

function load()
{
document.review.desc.focus();

}

</script>

</body>


</html>

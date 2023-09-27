<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security" %>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
 buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css"/>

<script language="javascript" src="/AutoLib/popcalendar.js"></script>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="Login.User" %>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>
<%
String userId = (String)session.getAttribute("UserID");
session.setAttribute("UserID", userId);
%>

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Suggestion.SuggestionBean"  type="Lib.Auto.Suggestion.SuggestionBean">
</jsp:useBean>
<HTML>
<head>
<title>AutoLib</title>

</head>
<body background="/AutoLib/soft.jpg">



<form method="post" name="suggestion" action="./SuggestionServlet">

<br><br><br><br>

    <h2>Request for a Service/Suggestion</h2>
    <table align="center" class="contentTable" width="45%">
  <tr>
<td>
<table align="center" width="83%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">Req.Serial No</td>
<td><input type=text name="sugNo" size=15  maxlength=10 onKeydown="Javascript: if (event.keyCode==13) return SearchRec();">
</td>
<td Class="addedit">Member&nbsp;Id</td><td> <input type=text name=memberCode value="<c:out value="${UserID}"/>" size=15 maxlength=15 readonly></td>
</tr>


            <tr>
            <td Class="addedit">Request For</td>
<td><select name="doc" size="1" id="alldoctype" style="width: 110px">
     <option value="BOOK">BOOK</option>
	 <option value="JOURNAL">JOURNAL</option>
	 <option value="PHOTO COPY">PHOTO COPY</option>
	 <option value="SERVICE">SERVICE</option>
	 <option value="OTHERS">OTHERS</option>
	 <option value="SUGGESTION">SUGGESTION</option>
	 
  </select></td>
  
  <td Class="addedit">Date</td>
<td>
<INPUT name="rcDate" size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,suggestion.rcDate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
				}
				
				</SCRIPT></td>
  
    </tr>
            
<td Class="addedit">Requested Details<td colspan=3>
<textarea  rows="6" cols="43" name="sugName" maxLength=2000  onkeypress="return noenter(event)"></textarea>
</td>
</tr>

<tr>
  <td Class="addedit">Remarks</td> <td colspan="4"><input type="text" name="remarks" size="55" maxlength=255></td>
</tr>
   
<tr><td>&nbsp;</td></tr>

</table></td></tr></table>
<p align="center">
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type="submit" Class="submitButton" value="Submit" name="add" onclick=SaveRec()>
<input type=button name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type="reset" Class="submitButton" value="Clear" name="clear" onclick=ClearRec()>
<input type="hidden" name="flag">
</p>

</form>

<%
     String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newSuggestion")){
	 %>
	<script language="JavaScript">
	document.suggestion.sugNo.value="<%=beanobject.getSugNo()%>";
	document.suggestion.sugName.focus();
	</script><%
}

if(valid.equals("searchSuggestion")){

    %>
     <script language="JavaScript">

     document.suggestion.sugNo.value="<%=beanobject.getSugNo()%>";
     document.suggestion.memberCode.value="<%=beanobject.getMemberCode()%>";
     document.suggestion.doc.value="<%=beanobject.getDoc()%>";
     document.suggestion.rcDate.value="<%=beanobject.getRcDate()%>";
     document.suggestion.sugName.value="<%=beanobject.getSugName()%>";
     document.suggestion.remarks.value="<%=beanobject.getRemarks()%>";
                    

   </script>
   <%
   }
   if(valid.equals("FailSuggestion")){%>
               <script language="JavaScript">
   			alert("Record Not Found");
   			document.suggestion.flag.value="new";
   			document.suggestion.submit();
   		   	</script><%
   			}
   	if(valid.equals("UpdateSuggestion")){%>
               <script language="JavaScript">
   			alert("Record Updated Successfully!");
   			document.suggestion.flag.value="new";
   			document.suggestion.submit();
   		   	</script><%
   			}
   	
   	if(valid.equals("UpdateCheck")){
		
		%>
		                <script language="JavaScript">
		                document.suggestion.sugNo.value="<%=beanobject.getSugNo()%>";
		                document.suggestion.memberCode.value="<%=beanobject.getMemberCode()%>";
		                document.suggestion.doc.value="<%=beanobject.getDoc()%>";
		                document.suggestion.rcDate.value="<%=beanobject.getRcDate()%>";
		                document.suggestion.sugName.value="<%=beanobject.getSugName()%>";
		                document.suggestion.remarks.value="<%=beanobject.getRemarks()%>";
		                                                         	                            
						         
		                msg=confirm("Record Already Exists Are You Sure To Update?");
		                 if(msg)
		                   {
						    document.suggestion.flag.value="update";
				         	document.suggestion.submit();

				            }
					    </script>
      <%
	}
 
 if(valid.equals("SaveSuggestion")){
     %>             <script language="JavaScript">
     			 alert("Record Inserted Successfully!");
     			document.suggestion.flag.value="new";
     			 document.suggestion.submit();
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

	 document.suggestion.method="post";
	 document.suggestion.flag.value="new";
	 document.suggestion.submit();
	 }

	 function noenter(e) {
	     e = e || window.event;
	     var key = e.keyCode || e.charCode;
	  
	     return key !== 13;
	     
	 }

	 function RestrictEnterKey(){
	 	
	 	alert("sfsdf");
	 }

	 function ClearRec(){ 
		  
		 document.suggestion.sugNo.value="";
		 document.suggestion.memberCode.value="";
		 document.suggestion.doc.value="";
		 document.suggestion.rcDate.value="";
         document.suggestion.remarks.value="";
         document.suggestion.sugName.value="";
         
		 document.suggestion.flag.value="";

         new_no();
}

	 function suggestion_code_val() {
		 if((isNaN(document.suggestion.sugNo.value))||(document.suggestion.sugNo.value == ' ')) {
		 document.suggestion.sugNo.select();
		 document.suggestion.sugNo.value="";
		     
		   }
		 }
	 
	 function SearchRec(){
		// alert("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
		 

		 document.suggestion.method="post";
		 var no=document.suggestion.sugNo.value;
		 
		 if(no==""){
		 				//document.suggestion.sugNo.focus();
		 				document.suggestion.flag.value="new";
		 				document.suggestion.submit();
		 		  }else{

		 		       document.suggestion.flag.value="search";
		 			   document.suggestion.submit();
		 			  
		 		}
		 	
		 }

	 
	 function UpdateRec(){
         document.suggestion.method="post";
		   if(chk() ){
		       	document.suggestion.flag.value="update";
	         	document.suggestion.submit();	
				
		}		
else
{
alert("Insufficent Data");
}

}

	  
function SaveRec(){
if(chk_mc()){
document.suggestion.method="post";
 if(!isWhitespace(document.suggestion.sugName.value)){
	 document.suggestion.sugName.value=document.suggestion.sugName.value+" ";		        
			    	document.suggestion.flag.value="save";
		         	document.suggestion.submit();	
		}		
	else
	{	
	  //alert("Insufficedddddddddddddddddddddddddddddddddddddddddddddddddddddddddnt Data");
	  document.suggestion.flag.value="new";
	  document.suggestion.submit();	
	}
		}	
else {		
      alert("Insufficient Data");
	}
}




 function chk_mc(){
var flag_cs;
var c;
var mc=document.suggestion.sugNo.value;
if(mc=="")
{
				//document.suggestion.sugName.focus();
				document.suggestion.flag.value="none";
				document.suggestion.sugNo.value="";
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
var sp=document.suggestion.sugName.value;
var d=sp.replace(" ");
alert(d);

if(d=="")
{

document.suggestion.sugName.focus();
			document.suggestion.flag.value="none";
			document.suggestion.sugame.value="";
			return false;
			}
			else
			{
              for(i=0;i<document.suggestion.sugName.value.length;i++)
                    {
                     if(document.suggestion.sugName.value.charAt(i)=="")
                        {flag_s=0; }
                              else{flag_s=1;}
                    }
	                  if (flag_s==0)   
	                    {
	                   	document.suggestion.sugName.focus();
	                   	document.suggestion.sugName.value="";
		                return false;
	                      }
	                   else if (document.suggestion.sugName.value==""){
	                 	document.suggestion.sugNo.focus();
		                return false;
	                      }
 else{
	return true;
	}
}
}

function load()
{
document.suggestion.sugName.focus();

}

</script>

</body>

</html>


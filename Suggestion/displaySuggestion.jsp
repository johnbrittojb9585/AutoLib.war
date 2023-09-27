<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean"
 import="Lib.Auto.Suggestion.SuggestionBean" import="Common.Security" import="java.util.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
 
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Suggestion.SuggestionBean"  type="Lib.Auto.Suggestion.SuggestionBean">
</jsp:useBean>
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
	
	<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("suggestionSearchList");
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
	
  <form name="query" method="post"  action=./SuggestionDisplayServlet>
  <input type="hidden" name="flag">
  <input type="hidden" name="sugNo">
  <input type="hidden" name="commentText">
  <c:set var="i" value="0"/>
 <CENTER> <h2 >Suggestions</h2> </CENTER>
 <%	
    while(it.hasNext()){
    	SuggestionBean view=(SuggestionBean) it.next();
    	
    %>
<table border='2px' id="table1" align="center" style="width:100%" >
<tr>
<td style=width:100%>
<table>
            <tr>
              <td Class="addedit" align="left">Member Code</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getMemberCode()%></td>
            </tr>
            
            <tr>
			<td Class="addedit" align="left">Request Date</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getRcDate()%></td>
	        </tr>
           
            <tr>            
              <td Class="addedit" align="left">Request For</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDoc()%></td>
            </tr>
                        
            <tr>
              <td Class="addedit" align="left">Request Details</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSugName()%></td>
              
            </tr>
            
            <tr>
              <td Class="addedit" align="left">Action Taken</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getActionTaken()%></td>
              
            </tr>
            <tr>
              <td Class="addedit" align="left">Action Taken Date</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getActionTakenDate()%></td>
              
            </tr>

	</table>
	</td>
	
	<td style=width:100%>
	<table><tr><td>comment</td></tr>
	<tr><td><textarea  rows="3" cols="43" name="comment[]" maxLength=2000  onkeypress="return noenter(event)"></textarea></td></tr>
	<tr><td><input type="button" value="Reply"  Class="submitButton" size="5" onclick='Reply("<%=view.getSugNo()%>",${i})'>
	
	<input type="button" value="remove" Class="submitButton" size="5" onclick='Del_rec("<%=view.getSugNo()%>")'></td>
	</tr>
	<c:set var="i" value="${i+1}"/>
	</table>
	

</td>


</tr>

<tr>

</tr>

           <%        
}		
 	sc.clear();

			%>
</table>

</form>
<%
     String valid=request.getParameter("check");
if(valid!=null){	
if(request.getParameter("check")!=null){

   if(valid.equals("DeleteSuggestion")){%>
               <script language="JavaScript">
   			alert("Record Deleted Successfully");
   		   	</script><%
   			}
   	if(valid.equals("UpdateSuggestion")){%>
               <script language="JavaScript">
   			alert("Record Updated Successfully!");
   		   	</script><%
   			}
   	if(valid.equals("UpdationFailed")){%>
    <script language="JavaScript">
	alert("Updation Failed!");
   	</script><%
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

function Reply(val,index){
	
	document.query.sugNo.value = val;
	document.query.commentText.value = document.getElementsByName('comment[]')[index].value;
	document.query.flag.value="reply";
	document.query.action="./SuggestionDisplayServlet";
	document.query.submit();
	}
	
function Del_rec(val){
	
	document.query.sugNo.value = val;
	document.query.flag.value="delete";
	document.query.action="./SuggestionDisplayServlet";
	document.query.submit();
	}

</script>
</body>
</html>

<%@ include file="/Tree/back.jsp"%>







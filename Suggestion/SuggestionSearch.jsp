<%@ include file="/Tree/Searchdemoframeset.jsp"%>


<div style="padding: 0px 0px 0px 0px;">
<%-- <% alert(SuggestionBean.getMemberCode()); %> --%>
		<jsp:include page="SuggestionSearchdisplay.jsp" flush="true" />
	</div>
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
	
	
<%-- 	<%@ include file="/Tree/back.jsp"%> --%>
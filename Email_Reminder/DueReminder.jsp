<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Email_Reminder.ReminderBean"  type="Lib.Auto.Email_Reminder.ReminderBean">
</jsp:useBean>

<form name="dueremider" action="./MailReminder" method="post">

<div style="padding: 0px 0px 0px 0px;">

 <a href="javascript:void();" onclick="javascript:checkAll('dueremider', true);">All</a> | 
 <a href="javascript:void();" onclick="javascript:checkAll('dueremider', false);">None</a>
 <br>

 <center>	
	<input type="button" Class="submitButton" value="Send E-Mail" onclick=showNextPage()>
<!--     <input type="button" Class="submitButton" value="Send SMS" onclick=sendSMSPage()>	
 -->    <input type=button name=New Class="submitButton"  Value=Back onclick=back() >
    <input type="hidden" name="flag">	  
    <input type="hidden" name="flag1">	    
</center>
		<jsp:include page="DueReminderdisplay.jsp" flush="true" />
	</div>
	
		

</form>



<script language="javascript">

function back()
{
 document.dueremider.action = '<%=request.getContextPath()%>/Email_Reminder/';
 document.dueremider.submit();
 //history.back();
}

function showNextPage()
	{
		//var ids=document.all.item('selectedIds');
		var ids = document.getElementsByName('selectedIds[]');		
		var chkitem=0;
		var content="";
		
		for(i=0;i<ids.length;i++)
		{
			if(ids[i].checked == true)
				{
				 chkitem++;
				 content=content+",'" + ids[i].value + "'";    
				}				
		}

		if(chkitem >= 1)
		{
			document.dueremider.flag.value="DueReminderMail";
            document.dueremider.method="get";	
            document.dueremider.flag1.value=content;
            //alert("Tot:"+content);		
			document.dueremider.submit();
		}
		else
		{
			alert('Please select atleast one !...');		
		}
	}
	
function checkAll(formname, checktoggle)
{
     var checkboxes = new Array();
      checkboxes = document[formname].getElementsByTagName('input');

      for (var i = 0; i < checkboxes.length; i++) {
          if (checkboxes[i].type === 'checkbox') {
               checkboxes[i].checked = checktoggle;
          }
      }
}	



function sendSMSPage()
{
		//var ids=document.all.item('selectedIds');
		var ids = document.getElementsByName('selectedIds[]');		
		var chkitem=0;
		var content="";

		for(i=0;i<ids.length;i++)
		{
			if(ids[i].checked == true)
				{
				 chkitem++;
				 content=content+",'" + ids[i].value + "'";    
				}				
		}

		if(chkitem >= 1)
		{
			document.dueremider.flag.value="DueReminderSMS";
            document.dueremider.method="get";	
            document.dueremider.flag1.value=content;
            //alert("Tot:"+content);		
			document.dueremider.submit();
		}
		else
		{
			alert('Please select atleast one !...');		
		}
}	

</script>
	
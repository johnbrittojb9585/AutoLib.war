<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<form name="dueremider" action="./MailReminder" method="post">

<div style="padding: 0px 0px 0px 0px;">

 <a href="javascript:void();" onclick="javascript:checkAll('dueremider', true);">All</a> | 
 <a href="javascript:void();" onclick="javascript:checkAll('dueremider', false);">None</a>
 
		<jsp:include page="CounterListdisplay.jsp" flush="true" />
	</div>
	
<center>	
	<input type="button" Class="submitButton" value="Send E-Mail" onclick=showNextPage()>
    <input type=button name=New Class="submitButton"  Value=Back onclick=back() >
    <input type="hidden" name="flag">	  
    <input type="hidden" name="flag1">	    
</center>		

</form>



<script language="javascript">

function back()
{
 history.back();
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

</script>
	


<script language="javascript">

	function TimeTick()
	{
		time = time+1000;
		if(rTimer) 
		{
			clearTimeout(rTimer);
		}
		rTimer = setTimeout('TimeTick()', 1000);
	}
	function updateClock()
	{
		var date = new Date();
		//date.setTime(time);
		var min = date.getMinutes();
		var hrs = date.getHours();
		var sec = date.getSeconds();
		if(min <= 9) 
		{
			min = "0" + min;
		}
		if(hrs <= 9) 
		{
			hrs = "0" + hrs;
		}
		if(sec <= 9) 
		{
			sec = "0" + sec;
		}
		document.getElementById('Timer').innerHTML=date.getDate() + " " + monthMap[date.getMonth()] + " " + date.getFullYear() + ", "+ hrs+" : "+ min + " : "+sec;
		if(uTimer)
		{
			clearTimeout(uTimer);
		}
		uTimer = setTimeout('updateClock()', 1000);
	}
	
	var time = 0;
	var rTimer,uTimer;
	var monthMap = new Object();
	
	function displayClock(curTime) 
	{
		time = curTime;
		monthMap["0"] = "Jan";
		monthMap["1"] = "Feb";
		monthMap["2"] = "Mar";
		monthMap["3"] = "Apr";
		monthMap["4"] = "May";
		monthMap["5"] = "Jun";
		monthMap["6"] = "Jul";
		monthMap["7"] = "Aug";
		monthMap["8"] = "Sep";
		monthMap["9"] = "Oct";
		monthMap["10"] = "Nov";
		monthMap["11"] = "Dec";
		TimeTick();
		updateClock();
	}
</script>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bgcolor="#ffffff" width="100%" id="AutoNumber1">
	<tr>
		<td width="58%" align="center" id="clgtitle">Panimalar College Of Nursing</td>
		<td width="33%" id="titleDateTime" align="right"> Date&Time: &nbsp; 
			<span id="Timer"></span> 
			<script>displayClock();</script>&nbsp;&nbsp; 
			<div id="titleAutolib" >Powered by AutoLib Software Systems &nbsp; </div>
	  	</td>
  </tr>
</table>
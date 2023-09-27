<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title></title>
</head>

<body >
<form name ="top" action="./JournalSearch" onsubmit="return validate()">

<br>
<br>
<br>
<br><h2 align="center">SERIAL&nbsp;SEARCH</h2>
<table align="center" class="contentTable" width="65%">
<td>

<table align="center" width="40%">
<tr><td> &nbsp; </td></tr>
<tr>
<td>&nbsp;&nbsp;

  
<tr ><td align=center><font face="Tahoma" size="2" color="#000080" class=disc ><b>Journal<b></font></td>
<td><input tabindex="0" name="name" size="50" maxlength="100">
</td>
<td Class="addedit">Doc&nbsp;Type:</td><td><select name="document" style="width: 120px">
          <option selected value="ALL">ALL</option>
          <option value="JOURNAL">JOURNAL</option>
          <option value="MAGAZINE">MAGAZINE</option>
          <option value="NEWSLETTER">NEWS LETTER</option>
          <option value="OTHERS">OTHERS</option>
          </select>
</td>
<!-- <td><b>&nbsp;Country:<b><select size="1" name="jcountry">
          <option selected value="ALL">ALL</option>
          <option value="INDIA">INDIA</option>
          <option value="USA">USA</option>
          <option value="UK">UK</option>
          <option value="GERMANY">GERMANY</option>
          <option value="KOREA">KOREA</option>
          <option value="JAPAN">JAPAN</option>
          <option value="SWEDEN">SWEDEN</option>
          <option value="HOLAND">HOLAND</option>
          <option value="NORWAY">NORWAY</option>
          <option value="CHINA">CHINA</option>
          <option value="FRANCE">FRANCE</option>
          <option value="ITALY">ITALY</option>
          <option value="OTHERS">OTHERS</option>
        </select></td> -->

</tr>

<tr>

		<td Class="addedit">Publisher</td> 
		
			<td colspan="1"><input type="text" size="45"  name="pname" value="NIL"></td>
			<td Class="addedit"><input type="button" value="Find" name="find_pub" Class="submitButton" onclick='Find_Value("Pub")'></td>
			<td Class="addedit">Frequency</td><td><select name="jfreq" style="width: 130px">
			                        <option value="ALL">ALL</option>
									<option value="DAILY">DAILY</option>
									<option value="SEMIWEEKLY">SEMIWEEKLY</option>
									<option value="WEEKLY">WEEKLY</option>
									<option vlue="FORTNIGHTLY">FORTNIGHTLY</option>
									<option value="MONTHLY">MONTHLY</option>
									<option value="BIMONTHLY">BIMONTHLY</option>
									<option value="QUARTERLY">QUARTERLY</option>
									<option value="TRIANNUAL">TRIANNUAL</option>
									<option value="HALF YEARLY">HALF&nbsp;YEARLY</option>									
									<option value="ANNUAL">ANNUAL</option>
									<option value="OTHERS">OTHERS</option>
							</select></td>
			 
			</tr>

<tr>
			
<td Class="addedit">Department</td>

<td Class="addedit" colspan=1><input type="text" name="dname" value="NIL"  size="45"></td>
<td Class="addedit"><input type="button" value="Find" name="find_dept" Class="submitButton"	onclick='Find_Value("Dept")'></td>

	<td Class="addedit">Country</td> 
	<td colspan="2">
	<select size="1" name="jcountry" style="width: 130px">
	<option selected value="ALL">ALL</option>
	<option value="NATIONAL">NATIONAL</option>
	<option value="INTERNATIONAL">INTERNATIONAL</option>
     
          <!-- <option value="USA">USA</option>
          <option value="UK">UK</option>
          <option value="GERMANY">GERMANY</option>
          <option value="KOREA">KOREA</option>
          <option value="JAPAN">JAPAN</option>
          <option value="SWEDEN">SWEDEN</option>
          <option value="HOLAND">HOLAND</option>
          <option value="NORWAY">NORWAY</option>
          <option value="CHINA">CHINA</option>
          <option value="FRANCE">FRANCE</option>
          <option value="ITALY">ITALY</option>
          <option value="OTHERS">OTHERS</option>	 -->						
							</select></td>

</tr>
<tr>

<td Class="addedit">Subject</td> 

<td colspan="1"><input type="text" size="45" name="sname" value="NIL" ></td>
<td Class="addedit"><input type="button" value="Find" name="find_sub" Class="submitButton" onclick='Find_Value("Sub")'></td>

<td Class="addedit">Language</td> 
<!-- <td width="42%" colspan="3"><input name="jlang" style="width: 115px" maxlength=50></td> -->
<td>
<select name="jlang" size="1" style="width: 115px">
      <option value="">ALL</option>
      <option value="ENGLISH">ENGLISH</option>
      <option value="TAMIL">TAMIL</option>
      <option value="MALAYALAM">MALAYALAM</option>
      <option value="TELUGU">TELUGU</option>
      <option value="KANADA">KANADA</option>
      <option value="HINDI">HINDI</option>
      <option value="MARATHI">MARATHI</option>
      <option value="URDU">URDU</option>
      <option value="PUNJABI">PUNJABI</option>
      <option value="GUJARATI">GUJARATI</option>
      <option value="ORRIA">ORRIA</option>
      <option value="BENGALI">BENGALI</option>
      <option value="ASSAME">ASSAME</option>
&nbsp; </select></td>

</tr>
<tr>
<td  Class="addedit">ISSN</td><td colspan=2><input name="jissn" size="15" maxlength=50></td></tr>

<tr>
<center>
<td width="100%" colspan="5" align="center"><center>
<input type="submit" Class="submitButton" value="Search" >
<input type="reset" Class="submitButton" value="Clear" ></center></td>
<td><input type="hidden" name="hid" value="search">
</td>
<td><input type="hidden" name="flag">
</td>


</tr>
</table>
</td>
</table>
</form>

</body>
</html>

<script >
function validate()
{
document.top.flag.value="search";
document.top.submit();
}


function Find_Value(val)
{

winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,toolbar=no,status=yes,menubar=no,scrollbars=yes");
}
</script>





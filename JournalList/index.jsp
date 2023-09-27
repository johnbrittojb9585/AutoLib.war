
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>

<head>
<meta charset="ISO-8859-1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"> -->
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Journal List</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchJournalList.js"></script>

</head>

<body background="/AutoLib/soft.jpg">

<form method="POST" action="./JournalListServlet" name=jnllist>
<br><br><br>
  <h2 >Journal&nbsp;List</h2>
<table align="center" class="contentTable" width="46%">
<td>
<table border="0" width="60%" cellspacing="0" cellpadding="5" align="center">
  <tr>
    <tr>
      <td>J/M/O</td>
      <td><select size="1" name="jmo" style="width: 45mm">
           <option value="NO">-------------------------------</option>
          <option value="JOURNAL">JOURNAL</option>
          <option value="MAGAZINE">MAGAZINE</option>
          <option value="NEWSLETTER">NEWS LETTER</option>          
          <option value="OTHERS">OTHERS</option>
        </select></td>
    </tr>
    <tr>
      <td>Country</td>
      <td><select size="1" name="country" style="width: 45mm">
          <option value="NO">-------------------------------</option>
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
        </select></td>
    </tr>
    <tr>
      <td>Frequency</td>
      <td><select size="1" name="frequency" style="width: 45mm">
          <option value="NO">-------------------------------</option>
          <option value="DAILY">DAILY</option>
          <option value="WEEKLY TWO">WEEKLY TWO</option>
          <option value="WEEKLY">WEEKLY</option>
          <option vlue="FORTNIGHTLY">FORTNIGHTLY</option>
          <option value="MONTHLY">MONTHLY</option>
          <option value="BIMONTHLY">BIMONTHLY</option>
          <option value="QUARTERLY">QUARTERLY</option>
          <option value="HALF YEARLY">HALF&nbsp;YEARLY</option>
          <option value="ANNUAL">ANNUAL</option>
          <option value="OTHERS">OTHERS</option>
        </select></td>
    </tr>
    <tr>
      <td>Department</td>
<!--       <td><input type="text" name="dept_name" size="20" readonly=true> -->
      <td><input type="text" name="dept_name" size="20" id="searchDept" class="searchJournalList" onkeyup="showDept(this.value);">
      
      <input type="button" Class="submitButton" value="Find" name="dept" onclick=FindValue(name)></td>
    </tr>
    <tr>
      <td>Subject</td>
<!--       <td><input type="text" name="sub_name" size="20" readonly=true> -->
      <td><input type="text" name="sub_name" size="20" id="searchSubject" class="searchJournalList" onkeyup="showSubject(this.value);">
      
      <input type="button" Class="submitButton" value="Find" name="sub" onclick=FindValue(name)></td>
    </tr>
    <tr>
	<td></td>
	
	<td colspan="3">
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
            <td><input type="submit" Class="submitButton" value="Print" name="print">&nbsp;<input type="reset" Class="submitButton" value="Clear" name="clear"></td>
    </tr>
  </table></td></table>
</form>

</body>

</html>
<script language="JavaScript">

function FindValue(val){
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
</script>

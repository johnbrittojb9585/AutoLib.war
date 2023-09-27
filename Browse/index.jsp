<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>

<title></title>
</head>

<body >
<form name ="top" action="./BrowseSearch" onsubmit="return validate()">

<br>
<br>
<br>
<br>
<h2>Quick&nbsp;Search</h2>
<table align="center" class="contentTable" width="75%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<td>&nbsp;&nbsp;
<tr ><td align=center><font face="Tahoma" size="2" color="#000080" class=disc ><b>Browse:<b></font></td>
<div class="search-container">
	<div class="ui-widget">

<td><input tabindex="0" name="name" id="searchQuick" class="search" size="40" maxlength="100">
</td>

   </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td><b>&nbsp;Field:<b><select name="type" id="searchList">
<option value="title">Title</option>
<option value="author_name">Author</option>
<option value="sub_name">Subject</option>
<option value="sp_name">Publisher</option>
<option value="Supplier">Supplier</option>
<option value="isbn">ISBN</option>
<option value="Dept_name">Department</option>
<option value="Language">Language</option>
<option value="availability">Status</option>
</select>
</td>

   </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<td><b>&nbsp;Doc Type:<b><select name="doc_type" id="docType">
<option  value="ALL">ALL</option>
<option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  <option value="ARTICLE">ARTICLE</option>		 
</select>
</td>

    </div>
</div>

<tr><center>
<td width="100%" colspan="5" align="center"><center>
<input type="submit" Class="submitButton" value="Search" >
<input type="reset" Class="submitButton" value="Clear" ></center></td>
<td><input type="hidden" name="hid" value="search">
    <input type="hidden" name="flag">
    
</td>
</center>

</tr>
</table>
</td>
</table>
</form>

</body>
</html>

<script language='javascript'>

    $( "#searchQuick" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('searchList').value;
    		
            $.ajax({
                url: "/AutoLib/Browse/BrowseSearch",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	name : request.term ,
                	flag  : type
                	
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
            });
            
            	
        }  

    	
      });

</script>
<script >
function validate()
{
if(document.top.name.value=="")
{
alert("Enter Any Values");
document.top.name.focus();
return false;
}
else
{
if((document.top.name.value.length)<3)
{
alert("Atleast three letters Should Be Entered ");
return false;
}
return true;
}
}
</script>

</body>

</html>

<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Journal_Artical.journalArtbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:journal_Artical
//%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focusText()"  background="/AutoLib/soft.jpg" >
<br><br><br>
<h2>Journal&nbsp;Articles</h2>
<form  name="jrl" method="get" action=./JournalArtServlet>
<br><br>

<center>
<table align="center" class="contentTable" width="50%">
<td>
<table align="center" cellspacing="0" cellpadding="2" width="90%">
<tr><td> &nbsp; </td></tr>

    <tr>
      <td Class="addedit">Journal</td>
   <td colspan="6"><input name="jcode" size="12" maxlength=12 readonly>
   <input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("Nam")'>
   <input name="jname" size="46" readonly>
	</td>
	
    </tr>
    <tr><td  Class="addedit">Atl.No.</td>
   <td Class="addedit" colspan="6"><input name="atlno" size="12" maxlength=8 onKeyUp="return journal_code_val();" >
   <input  type="button" Value="Find" Class="submitButton" name="find_name" maxlength=150 onclick='Find_Value("Atl")' >

Issue&nbsp;Acc.No.
  <input name="issueaccessno" size="12" maxlength=8 onKeyUp="return journal_code_val();" >
<input type="button" Value="Find" Class="submitButton" name="find_name" maxlength=150 onclick='Find_Value("Iss")' ></td>
</tr>
<tr><td Class="addedit">Title</td>
   <td colspan="5"><input name="title" size="72" >
</td>
</tr>
<tr><td Class="addedit">Author</td>
   <td colspan="5"><input name="author" size="72" >
</td>
</tr>

<tr>
   <td Class="addedit">BVol.No.</td>
   <td Class="addedit" colspan="5"><input name="bvolno" size="12"  maxlength=11 >
 J.Vol.No.<input name="jvolno" size="14"  maxlength=11 >
Issue Nos.<input name="issueno" size="16"  maxlength=11></td>

</tr>
    <tr>
   <td Class="addedit">Year</td>
   <td Class="addedit" colspan="5"><input name="year" size="12"  maxlength=4 >
    Month&nbsp;&nbsp;&nbsp;&nbsp;<input name="month" size="14">
    Page Nos.<input name="page" size="16" ></td>
    </tr>
    <tr>
    <td Class="addedit">Subject</td><td colspan="5"><input name="subject" size="31" readonly>
   <input type="button" Value="Find" Class="submitButton" name="find_name1" maxlength=150 onclick='Find_Value("Sub")' >
  </td>
    </tr>
   
    <tr>            
<td Class="addedit">Abstract</td><td colspan=5>
<textarea  rows="4" cols="54" name="abstract" maxLength=1000  onkeypress="return noenter(event)"></textarea>
</td>
</tr>
<tr>
<td Class="addedit"> Keywords</td><td colspan=5><input name="key" size="71" ></td>
</tr>
 <tr>
      <td Class="addedit"> Content&nbsp;Page</td>
      <td colspan=5><input type=text name=content  maxlength=200 size="21" ></td>
	</tr>

</table>

</CENTER></td>
</table></center>
<table align=center>
<tr>
      
        	<td><input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()"></td>
		<td><input type="button" value="Save"  Class="submitButton" name="save" onclick="SaveRecord()"></td>
		<td><input type="button" value="Delete"  Class="submitButton" name="delete" onclick="DeleteRecord()"></td>
		<td><input type="submit" value="Search" Class="submitButton" name="search" onclick="SearchRecord()"></td>
		<td><input type="reset" value="Clear" Class="submitButton" name="clear"></td>
		<td><input type="hidden" name="flag" value="search"></td>
		<td><input type="hidden" name="flag2" value="search2"></td>
              <td><input type="hidden" name="jnlflag"></td>
    </tr>
</table>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");
if(valid!=null){
String jcode=request.getParameter("jcode");
String jname=request.getParameter("jname");
String issueaccessno=request.getParameter("issueaccessno");
String jvolno=request.getParameter("jvolno");
String issueno=request.getParameter("issueno");
String year=request.getParameter("year");
String month=request.getParameter("month");
if(request.getParameter("check")!=null){
if(valid.equals("newJournal")){

 %>
 <script language="JavaScript">

document.jrl.atlno.value="<%=bean.getAtlno()%>";
document.jrl.jcode.value="<%=jcode%>";
document.jrl.jname.value="<%=jname%>";
document.jrl.issueaccessno.value="<%=issueaccessno%>";
document.jrl.jvolno.value="<%=jvolno%>";
document.jrl.issueno.value="<%=issueno%>";
document.jrl.year.value="<%=year%>";
document.jrl.month.value="<%=month%>";
document.jrl.title.focus();

</script>
<%
  }

  if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";


		document.jrl.atlno.value="<%=bean.getAtlno()%>";
		document.jrl.bvolno.value="<%=bean.getBvolno()%>";
		document.jrl.title.value="<%=bean.getTitle()%>";
		document.jrl.author.value="<%=bean.getAuthor()%>";
		document.jrl.jvolno.value="<%=bean.getVolno()%>";
		document.jrl.issueno.value="<%=bean.getIssueno()%>";
		document.jrl.year.value="<%=bean.getIssueyear()%>";
		document.jrl.month.value="<%=bean.getIssuemonth()%>";
		document.jrl.page.value="<%=bean.getPages()%>";
		document.jrl.subject.value="<%=bean.getSubject()%>";
		document.jrl.key.value="<%=bean.getKeywords()%>";
		document.jrl.content.value="<%=bean.getAbstracts()%>";
		document.jrl.issueaccessno.value="<%=bean.getIssue_acc_no()%>";
		document.jrl.abstract.value="<%=bean.getAtl_abstracts()%>";
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				document.jrl.flag.value="update";

		         	document.jrl.submit();
		   }
			    </script>

			<%
			}


	if(valid.equals("UpdateJournal")){%>
            <script language="JavaScript">
            	document.jrl.jcode.value="<%=jcode%>";
				document.jrl.jname.value="<%=jname%>";
                document.jrl.issueaccessno.value="<%=issueaccessno%>";
                document.jrl.jvolno.value="<%=jvolno%>";
                document.jrl.issueno.value="<%=issueno%>";
                document.jrl.year.value="<%=year%>";
                document.jrl.month.value="<%=month%>";
			alert("Record Updated Successfully!");
			document.jrl.flag.value="new";
			document.jrl.submit();
		   	</script><%
			}

			if(valid.equals("DeleteJournal"))
	{
	%>
            <script language="JavaScript">
	    document.jrl.jcode.value="<%=jcode%>";
	    document.jrl.jname.value="<%=jname%>";
	    document.jrl.issueaccessno.value="<%=issueaccessno%>";
	    document.jrl.jvolno.value="<%=jvolno%>";
	    document.jrl.issueno.value="<%=issueno%>";
	    document.jrl.year.value="<%=year%>";
	    document.jrl.month.value="<%=month%>";
	    alert("Record Deleted Successfully");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
	}

			if(valid.equals("SaveJournal")){
%>             <script language="JavaScript">
				document.jrl.jcode.value="<%=jcode%>";
				document.jrl.jname.value="<%=jname%>";
				document.jrl.issueaccessno.value="<%=issueaccessno%>";
				document.jrl.jvolno.value="<%=jvolno%>";
				document.jrl.issueno.value="<%=issueno%>";
				document.jrl.year.value="<%=year%>";
				document.jrl.month.value="<%=month%>";
			  alert("Record Inserted Successfully!");
			 document.jrl.flag.value="new";
			document.jrl.submit();
		     </script>
			<%
			}
if(valid.equals("SuccessJournal")){
 %>
  <script language="JavaScript">

		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
		document.jrl.atlno.focus();

 </script>
<%
}
if(valid.equals("SuccessJournalAtl")){
%>
  <script language="JavaScript">

		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
		document.jrl.atlno.value="<%=bean.getAtlno()%>";
		document.jrl.bvolno.value="<%=bean.getBvolno()%>";
		document.jrl.title.value="<%=bean.getTitle()%>";
		document.jrl.author.value="<%=bean.getAuthor()%>";
		document.jrl.jvolno.value="<%=bean.getVolno()%>";
		document.jrl.issueno.value="<%=bean.getIssueno()%>";
		document.jrl.year.value="<%=bean.getIssueyear()%>";
		document.jrl.month.value="<%=bean.getIssuemonth()%>";
		document.jrl.page.value="<%=bean.getPages()%>";
		document.jrl.subject.value="<%=bean.getSubject()%>";
		document.jrl.key.value="<%=bean.getKeywords()%>";
		document.jrl.content.value="<%=bean.getAbstracts()%>";
		document.jrl.issueaccessno.value="<%=bean.getIssue_acc_no()%>";
		document.jrl.abstract.value="<%=bean.getAtl_abstracts()%>";
		document.jrl.atlno.focus();

 </script>
<%
}
	if(valid.equals("FailureJournal")){%>
            <script language="JavaScript">
	    alert("Record Not Found");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
			}





  }
  }
%>
<script>
function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
 
    return key !== 13;
    
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function NewRecord()
{
if(document.jrl.jcode.value=="" || document.jrl.jname.value=="" || document.jrl.issueno.value=="")
		 {
		 alert("Please Select The Journal and IssueAcc.No!");
		 document.jrl.flag.value="null";
		 }
		 else
		 {
	document.jrl.method="get";
	document.jrl.flag.value="new";
	document.jrl.submit();
	}

}

function SaveRecord(){
document.jrl.method="get";
		if((!isWhitespace(document.jrl.jname.value))&&(!isWhitespace(document.jrl.title.value))&&(!isWhitespace(document.jrl.atlno.value))&&(!isWhitespace(document.jrl.issueaccessno.value))){
		
			document.jrl.flag.value="save";
		        document.jrl.submit();
			 }

	else
	{
	alert("Insufficent Data");
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
var atl=document.jrl.title.value;
var sp=document.jrl.jname.value;
				if(sp=="" || atl=="")
				{
				document.jrl.jname.focus();
				document.jrl.flag.value="none";
				document.jrl.jname.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.jrl.jname.value.length;i++)
 	                      {
 	                       if(document.jrl.jname.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.jrl.jname.focus();
		                   	document.jrl.jname.value="";
			                return false;
		                      }
		                   else if (document.jrl.jcode.value==""){
		                 	document.jrl.jcode.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }



function DeleteRecord(){
document.jrl.method="get";
		if (document.jrl.jcode.value==""){
				document.jrl.jcode.focus();
				alert("Insufficent Data");
				document.jrl.flag.value="new";
				document.jrl.submit();
				}
			else{
				msg=confirm("Are You Sure To Delete");
					if(msg){
						document.jrl.flag.value="delete";
						document.jrl.submit();
						}
						else
						{
						//alert("Operation Cancelled..!");
						//document.jrl.flag.value="new";                         							                        	//document.jrl.action="index.jsp";
						//document.jrl.submit();
						}
				}
}

function SearchRecord()
{
document.jrl.method="get";
var no=document.jrl.atlno.value;
	if(no=="")
	{
				document.jrl.atlno.focus();
				alert("Insufficent Data");
				document.jrl.flag.value="new";
				document.jrl.submit();
	}
	else
        {
	document.jrl.flag.value="searchAtl";
	document.jrl.submit();
	}
}
function journal_code_val() {
if((isNaN(document.jrl.jcode.value))||(document.jrl.jcode.value == ' ')) {
document.jrl.jcode.select();
document.jrl.jcode.value="";

  }
}
function Find_Value(val)
{
var jcode=document.jrl.jcode.value;
winpopup=window.open("search.jsp?flag="+val+"&flag2="+jcode,"popup","height=400,width=600,top=100,left=100,toolbar=no,status=yes,menubar=no,scrollbars=yes");
}

 function AddRecord()
	{
	document.jrl.content.value=document.jrl.Content1.value

        }

</script>



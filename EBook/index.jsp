<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"   import="java.util.*" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.EBook.EBookBean"  type="Lib.Auto.EBook.EBookBean">
</jsp:useBean>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:ebook
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta charset="ISO-8859-1">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchEBookMasAll.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchBookAll2.js"></script>

</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>
<form name="ebook" method="post" action="./EBookServlet">
<br><br><br>

 <P ALIGN="left">
 </P>
 <h2>EBook&nbsp;Master</h2>
<CENTER>
<table align="center" class="contentTable" width="1000" height="300">
<td>
<table align="center" width="100%">
<tr><td> &nbsp; </td></tr>

<tr>

<div class="search-container">
<div class="ui-widget">

<td Class="addedit">Access&nbsp;No<FONT color=red size=2>*</FONT></td>
<td colspan=2><input type="text" name="accessNo" id="searchEBookAccessNo" class="searchEbookMas" onkeyup="showEBookAccessNo(this.value);" size="15" maxlength=15 onKeydown="Javascript: if (event.keyCode==13) SearchRec();" >

</div>
</div>

<input type=button name="Find_AccNo" Class="submitButton" Value="Find" onclick="FindValue('accessNo')"></td>

<div class="search-container">
<div class="ui-widget">

<td Class="addedit">Call&nbsp;No</td><td><input type="text" name="callNo" id="searchEBookCallNo" class="searchEbookMas" onkeyup="showEBookCallNo(this.value);" size="14" maxlength=20>

</div>
</div>

<input type=button name="FindCallNo" Class="submitButton" Value="Find" onclick="FindValue('callNo')"></td>
 
<td Class="addedit">Rec. Date</td>
<td>
<INPUT name="rcDate" size=10 id="datepicker" value="<%=dateString%>">					
</td>
</tr>

<tr>
<div class="search-container">
<div class="ui-widget">

<td Class="addedit">Title<FONT color=red size=2>*</FONT></td>
<td colspan=2><input type=text name="title" id="searchEBookTitle" class="searchEbookMas" onkeyup="showEBookTitle(this.value);" size=50 maxlength=255 >

</div>
</div>

<td Class="addedit">Edition</td><td><input type="text" name="edition" size="15" maxlength=20></td>

<td Class="addedit">Year Pub</td><td><input name="yop" size="15" maxlength=10 onKeyUp="return Year_val();">
</td>
</tr>

<tr>
<div class="search-container">
<div class="ui-widget">

<td Class="addedit">Author&nbsp;Name<FONT color=red size=2>*</FONT></td>
<td colspan=2><input type="text" name="authorName" id="searchEBookAuthor" class="searchEbookMas" onkeyup="showEBookAuthor(this.value);" size="42" maxlength=150>

</div>
</div>

<input type=button name="Find_Aut" Value="Find" Class="submitButton"  onclick="FindValue('Aut')"></td>

<td Class="addedit">Role</td>
<td><select name="role" size="1"  style="width: 115px" >
       <option value="AUTHOR">AUTHOR</option>
      <option value="EDITOR">EDITOR</option>
      <option value="COMP">COMP</option>
      <option value="TRANS">TRANS</option>
      <option value="ET AL">ET AL</option>
 </select></td>
 
 <td Class="addedit">Price</td><td><input type="text" name="price" size="15" maxlength=20 value='0'></td>
 </tr>
 
<tr>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Supplier</td>
<td colspan=2><input type="text" name="supName" id="searchSupplier" class="searchBook" onkeyup="showSuppler(this.value);" size="42" value="NIL"> 

    </div>
</div>

<input type=button name="Find_Supplier" Class="submitButton" Value="Find" onclick="FindValue('Sup')"></td>
 
<td Class="addedit">Invoice&nbsp;No</td><td><input type=text name="inv_no" size="25"></td>
<td Class="addedit">Inv.Date</td>
<td>
<INPUT name="invDate" size=10 id="datepicker2" value="<%=dateString%>">					
</td>
</tr>
  
 <tr>
 
<div class="search-container">
<div class="ui-widget">
 
<td Class="addedit">Publisher</td>
<td colspan=2><input name="pubName" value="NIL" id="searchEBookPublisher" class="searchEbookMas" onkeyup="showEBookPublisher(this.value);" size="42" maxlength=150>

</div>
</div>

<input type=button name="Find_Publisher" Class="submitButton" Value="Find" onclick="FindValue('Pub')"></td>
<td Class="addedit">ISBN</td><td><input type="text" name="isbn" size="25" maxlength=25></td>	 
 	   
<td Class="addedit">Purchase&nbsp;Type</td>
<td><select name="ptype" size="1"  style="width: 110px">
       <option value="Purchase">Purchase</option>
       <option value="Gift">Gift</option>
 </select></td>  
</font></td>	  	
 </tr>
 
 <tr>
 
<div class="search-container">
<div class="ui-widget">
 
<td Class="addedit">Subject</FONT></td>
<td colspan=2> <input type="text" name="subName" id="searchEBookSubject" class="searchEbookMas" onkeyup="showEBookSubject(this.value);" size="42" value="NIL">

</div>
</div>
<input type=button name="FindSub" Class="submitButton" Value="Find" onclick="FindValue('Sub')"></td>
 

<td Class="addedit">Content</td><td><input type=text name="content" size=25 maxlength="50"></td>

<td Class="addedit">Course</td>
<td><select name="type" size="1" style="width: 110px">
 		<option value="UG">UG</option>
      	<option value="PG">PG</option>
      	</select></td></tr>
  
<tr>
<div class="search-container">
<div class="ui-widget">
 
<td Class="addedit">Department</FONT></td>
<td colspan=2><input type="text" name="deptName" id="searchEBookDept" class="searchEbookMas" onkeyup="showEBookDept(this.value);" size="42" value="NIL">

</div>
</div>
<input type=button name="Find_Dept" value="Find" Class="submitButton" onclick="FindValue('Dept')"></td>
 
     <td Class="addedit">DocType</td>
     <td><select name="doc" size="1" id="alldoctype" style="width: 110px">
	 <option value="EBOOK">EBOOK</option></select>
     </td>
      <td Class="addedit">Pages</td><td><input type="text" name="pages" size="15" maxlength=15></td>
</tr>

   
<tr>
    <td Class="addedit">URL</td><td colspan=2><input type=text name="url" size="60" maxlength="100"></td>
    <td Class="addedit">Keywords</td><td colspan=2><input type=text name="keywords" size="50" maxlength="50"></td>
</tr>
      
<tr><td> &nbsp; </td></tr>
   
<input type=hidden name=flag>
<input type=hidden name=name>
<input type=hidden name=Sub id="searchSub" value="1" size=20  maxlength=5>
<input type=hidden name=Dept id="searchDept" value="1" size=20>
<input type=hidden name=Pub id="searchpub" value="1" size=20 >
<input type=hidden name=Sup id="searchSup" value="2" size=20>
<input type=hidden name=Publisher value="1">
<input type=hidden name=Branch value="1">
<input type=hidden name=CallNo value="1">
<input type=hidden name=Author_value value="1">
<input type=hidden name=doc_type>


	
 </table>
 </td>
 </tr>
 </table>
 </CENTER>
 <BR>

 <p align="center"> 
 
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton"  Value=Delete onclick=DelRec()>
<input type=button name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>
</p>
</form>
</body>
</html>

                <%
                String valid=request.getParameter("check");

                if(valid!=null){
                if(request.getParameter("check")!=null){
                if(valid.equals("newEBook")){
                 %>
                <script language="JavaScript">
                document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                document.ebook.title.focus();
                </script><%

                }
                if(valid.equals("searchEBook")){

                 %>
                  <script language="JavaScript">

                  document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                  document.ebook.title.value="<%=BeanObject.getTitle()%>";
                  document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                  document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                  document.ebook.role.value="<%=BeanObject.getRole()%>";
                  document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                  
                  document.ebook.yop.value="<%=BeanObject.getYop()%>";
                  document.ebook.pages.value="<%=BeanObject.getPages()%>";
                  
                  document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                  document.ebook.Sub.value="<%=BeanObject.getSubCode()%>";
                  
                  
                  document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                  document.ebook.Pub.value="<%=BeanObject.getPubCode()%>";
                  
                  document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                  document.ebook.Dept.value="<%=BeanObject.getDeptCode()%>";
                  
                  document.ebook.Branch.value="<%=BeanObject.getBranchCode()%>";
                  document.ebook.url.value="<%=BeanObject.getUrl()%>";
                  document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                  document.ebook.price.value="<%=BeanObject.getPrice()%>";
                  document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                  document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                  document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                  document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                  document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                  document.ebook.type.value="<%=BeanObject.getType()%>";
                  document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                  document.ebook.content.value="<%=BeanObject.getContent()%>";
                  document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                  document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";
                            
  
                </script>
                <%
                }
                if(valid.equals("FailEBook")){%>
                            <script language="JavaScript">
                			alert("Record Not Found");
                			document.ebook.flag.value="new";
                			document.ebook.submit();
                		   	</script><%
                			}
                	if(valid.equals("UpdateEBook")){%>
                            <script language="JavaScript">
                			alert("Record Updated Successfully!");
                			document.ebook.flag.value="new";
                			document.ebook.submit();
                		   	</script><%
                			}		
                if(valid.equals("CodeCompareEBook")){
                	
                %>
                            <script language="JavaScript">
                            
                            document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                            document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                            document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                            document.ebook.title.value="<%=BeanObject.getTitle()%>";
                            document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                            document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                            document.ebook.role.value="<%=BeanObject.getRole()%>";
                            document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                            document.ebook.yop.value="<%=BeanObject.getYop()%>";
                            document.ebook.pages.value="<%=BeanObject.getPages()%>";
                            document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                            document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                            document.ebook.type.value="<%=BeanObject.getType()%>";
                            document.ebook.content.value="<%=BeanObject.getContent()%>";
                            document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                            document.ebook.url.value="<%=BeanObject.getUrl()%>";
                            document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                            document.ebook.price.value="<%=BeanObject.getPrice()%>";
                            document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                            document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                            document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                            document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                            document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                            document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";
            
                				                                
                			msg=confirm("EBook name Already Exists ,Do You Want update other than EBook name ?");
                			
                			if(msg){
                			 document.ebook.flag.value="update";
                		   	document.ebook.submit();	
                			
                			}

                			</script><%
                			}
                if(valid.equals("SaveEBook")){
                %>             <script language="JavaScript">
                			 alert("Record Inserted Successfully!");
                			 document.ebook.flag.value="new";
                			 document.ebook.submit();
                		     </script>		
                			<%
                			}
                              			
                			if(valid.equals("DeleteEBook")){
                			
                %>       
                            <script language="JavaScript">
                			alert("Record Deleted Successfully!");
                			document.ebook.flag.value="new";
                			document.ebook.submit();
                		   </script>		
                			<%
                			}		
                			
                			
                			if(valid.equals("deleteCheck")){
                			
                %>       
                            <script language="JavaScript">
                            
                            document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                            document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                            document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                            document.ebook.title.value="<%=BeanObject.getTitle()%>";
                            document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                            document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                            document.ebook.role.value="<%=BeanObject.getRole()%>";
                            document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                            document.ebook.yop.value="<%=BeanObject.getYop()%>";
                            document.ebook.pages.value="<%=BeanObject.getPages()%>";
                            document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                            document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                            document.ebook.type.value="<%=BeanObject.getType()%>";
                            document.ebook.content.value="<%=BeanObject.getContent()%>";
                            document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                            document.ebook.url.value="<%=BeanObject.getUrl()%>";
                            document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                            document.ebook.price.value="<%=BeanObject.getPrice()%>";
                            document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                            document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                            document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                            document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                            document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                            document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";
              			    
                            
                			msg=confirm("Are You Sure To Delete?");
                			if(msg){
                			 document.ebook.flag.value="Confirmdelete";
                		   	document.ebook.submit();	
                			
                			}
                			</script>		
                			<%
                			}
                			
                			if(valid.equals("RecordNot")){
                			%>       
                            <script language="JavaScript">
                            document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                            document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                            document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                            document.ebook.title.value="<%=BeanObject.getTitle()%>";
                            document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                            document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                            document.ebook.role.value="<%=BeanObject.getRole()%>";
                            document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                            document.ebook.yop.value="<%=BeanObject.getYop()%>";
                            document.ebook.pages.value="<%=BeanObject.getPages()%>";
                            document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                            document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                            document.ebook.type.value="<%=BeanObject.getType()%>";
                            document.ebook.content.value="<%=BeanObject.getContent()%>";
                            document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                            document.ebook.url.value="<%=BeanObject.getUrl()%>";
                            document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                            document.ebook.price.value="<%=BeanObject.getPrice()%>";
                            document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                            document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                            document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                            document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                            document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                            document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";

                			    alert("Record Not Present!!!");
                			    document.ebook.flag.value="new";
                    			document.ebook.submit();
                				</script>		
                			<%
                			}				
                		
                			if(valid.equals("UpdateCheck")){
                				
                				%>
                				                <script language="JavaScript">
                				                document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                				                document.ebook.title.value="<%=BeanObject.getTitle()%>";
                				                document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                	                            document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                	                            document.ebook.role.value="<%=BeanObject.getRole()%>";
                	                            document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                	                            document.ebook.yop.value="<%=BeanObject.getYop()%>";
                	                            document.ebook.pages.value="<%=BeanObject.getPages()%>";
                	                            
                	                            document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                	                            document.ebook.Sub.value="<%=BeanObject.getSubCode()%>";
                	                            document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                	                            
                	                            document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                	                            document.ebook.Pub.value="<%=BeanObject.getPubCode()%>";
                	                            document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                	                            document.ebook.Dept.value="<%=BeanObject.getDeptCode()%>";
                	                            
                	                            document.ebook.Branch.value="<%=BeanObject.getBranchCode()%>";
                	                            
                	                            
                	                            document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                	                            document.ebook.type.value="<%=BeanObject.getType()%>";

												document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                	                            document.ebook.content.value="<%=BeanObject.getContent()%>"; 
                	                            document.ebook.url.value="<%=BeanObject.getUrl()%>";
                	                            document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                	                            document.ebook.price.value="<%=BeanObject.getPrice()%>";
                	                            document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                	                            document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                	                            document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                	                            document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                	                            document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                	                            document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";
                                                 
            	                                          	                            
                								                				               
                				                msg=confirm("Record Already Exists Are You Sure To Update?");
                				                 if(msg)
                				                   {
                								    document.ebook.flag.value="update";
                						         	document.ebook.submit();

                						            }
                							    </script>
                              <%
                			}
                                			
                if(valid.equals("Updatename")){ 
                                	
                	%> 
                				    
                	                <script language="JavaScript">

                	                document.ebook.accessNo.value="<%=BeanObject.getAccessNo()%>";
                                    document.ebook.callNo.value="<%=BeanObject.getCallNo()%>";
                                    document.ebook.rcDate.value="<%=BeanObject.getRcDate()%>";
                                    document.ebook.title.value="<%=BeanObject.getTitle()%>";
                                    document.ebook.edition.value="<%=BeanObject.getEdition()%>";
                                    document.ebook.authorName.value="<%=BeanObject.getAuthorName()%>";
                                    document.ebook.role.value="<%=BeanObject.getRole()%>";
                                    document.ebook.pubName.value="<%=BeanObject.getPubName()%>";
                                    document.ebook.yop.value="<%=BeanObject.getYop()%>";
                                    document.ebook.pages.value="<%=BeanObject.getPages()%>";
                                    document.ebook.subName.value="<%=BeanObject.getSubName()%>";
                                    document.ebook.deptName.value="<%=BeanObject.getDeptName()%>";
                                    document.ebook.type.value="<%=BeanObject.getType()%>";
                                    document.ebook.content.value="<%=BeanObject.getContent()%>";
                                    document.ebook.doc.value="<%=BeanObject.getDoc()%>";
                                    document.ebook.url.value="<%=BeanObject.getUrl()%>";
                                    document.ebook.isbn.value="<%=BeanObject.getIsbn()%>";
                                    document.ebook.price.value="<%=BeanObject.getPrice()%>";
                                    document.ebook.supName.value="<%=BeanObject.getSupName()%>";
                                    document.ebook.Sup.value="<%=BeanObject.getSupCode()%>" ;
                                    document.ebook.keywords.value="<%=BeanObject.getKeywords()%>";
                                    document.ebook.inv_no.value="<%=BeanObject.getInvoiceNo()%>";
                                    document.ebook.ptype.value="<%=BeanObject.getPtype()%>";
                                    document.ebook.invDate.value="<%=BeanObject.getInvoiceDate()%>";

                		
                	                 msg=confirm("Record already Exist, Are You Sure To Update?");
                	                if(msg)
                	                   {
                					    document.ebook.flag.value="update";
                			         	document.ebook.submit();	
                					    
                			            }
                			           
                				    </script>	
                				 	
                				<%
                				}					

                }
                }

                %>
                

<script language=javascript>
function split(val) 
{
    return val.split(/;\s*/);
}
function extractLast(term) 
{
    return split(term).pop();
}
$(document).ready(function() {

$(function showEBookAuthor(data) {	  
	  $( "#searchEBookAuthor" ).autocomplete({
  	width: 500,
      max: 20,
      delay: 100,
      minLength: 1,
      autoFocus: true,
      cacheLength: 1,
      scroll: true,
      highlight: false,
          	
  	source: function (request, response) {
          $.ajax({
              url: "/AutoLib/EBook/EBookServlet?flag="+data,
              type: "GET",
              async : true,
              dataType: "json",
              data : {
              	authorName : extractLast(request.term)
				 },
              success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
          {
          console.log (item.authorName);
                          // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                          //       THE FOLLOWING LINE
          return {
              id: item.code, value: item.name };
          }));
              	
			     },   
				  
          }); 
      },
      
      search: function () 
	    {
	        // custom minLength
	        var term = extractLast(this.value);
	        if (term.length < 1) 
	        {
	            return false;
	        }
	    },
	   
	    select: function (event, ui) 
	    {
	    	var terms = split(this.value);
	        // remove the current input
	        terms.pop();
	        // add the selected item
	        terms.push(ui.item.value);
	        // add placeholder to get the comma-and-space at the end
	        terms.push("");
	        this.value = terms.join(";");
	        return false;
	    }
  });	  

    });	  
	  
});

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function new_no(){
	document.ebook.method="get";
	document.ebook.flag.value="new";
	document.ebook.submit();
	}


function SearchRec(){
document.ebook.method="post";
var no=document.ebook.accessNo.value;
if(no==""){
				document.ebook.accessNo.focus();
				alert("Insufficent Data");
				document.ebook.flag.value="new";
				document.ebook.submit();
		  }else{
		       document.ebook.flag.value="search";
			   document.ebook.submit();
			  
		}
	
}

function Auth_Select(){
	//alert("Select Author from Find option");
	}
	
function validTitle_space() { 
    if (isWhitespace(document.ebook.title.value)) {
       document.ebook.title.select();
       document.ebook.title.value="";      
   }
}
	

function checkDivision()
{
  
 if(document.ebook.deptName.value=='NIL')
{
 alert("Choose right department name");
 return false;         
}else if(document.ebook.subName.value=='NIL')
{
 alert("Choose right subject name");
 return false;         
}else if(document.ebook.pubName.value=='NIL')
{
 alert("Choose right publisher name");
 return false;         
}
else if(document.ebook.supName.value=='NIL')
{
 alert("Choose right Supplier name");
 return false;
}else {
  return true;         
}

}


function SaveRec(){
if(chk_mc()){
             document.ebook.method="post";
			   if(!isWhitespace(document.ebook.accessNo.value) && (document.ebook.title.value != "") && (document.ebook.authorName.value != "")){
			   			        
			    	document.ebook.flag.value="save";
		         	document.ebook.submit();	
					
			}		
	else
	{	
	  alert("Insufficent Data");
	  document.ebook.flag.value="new";
	  document.ebook.submit();	
	}
		}		
	else
	{
	
	alert("Insufficent Data");
	}
}
 function chk_mc(){
var flag_cs;
var c;
var mc=document.ebook.accessNo.value;
if(mc=="")
{
				document.ebook.accessNo.focus();
				document.ebook.flag.value="none";
				document.ebook.accessNo.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }

function UpdateRec(){
             document.ebook.method="post";
			   if(chk() ){
			       	document.ebook.flag.value="update";
		         	document.ebook.submit();	
					
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
var sp=document.ebook.title.value;
var d=sp.replace(" ");
alert(d);

if(d=="")
{

document.ebook.title.focus();
				document.ebook.flag.value="none";
				document.ebook.title.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.ebook.title.value.length;i++)
 	                      {
 	                       if(document.ebook.title.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)   
		                    {
		                   	document.ebook.title.focus();
		                   	document.ebook.title.value="";
			                return false;
		                      }
		                   else if (document.ebook.accessNo.value==""){
		                 	document.ebook.accessNo.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }
 
function FindValue(val){
		
	winpopup=window.open("search_all.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
	}


function ReportRec(){
			    	document.ebook.flag.value="Report";
		         	document.ebook.submit();	
					
	} 
function ClearRec(){ 
  
		 document.ebook.accessNo.value="";
		 document.ebook.callNo.value="";
		 document.ebook.rcDate.value="";
		 document.ebook.title.value="";
         document.ebook.edition.value="";
         document.ebook.authorName.value="";
         document.ebook.role.value="";
         document.ebook.pubName.value="";
         document.ebook.supName.value="";
         document.ebook.yop.value="";
         document.ebook.pages.value="";
         document.ebook.subName.value="";
		 document.ebook.deptName.value="";
		 document.ebook.type.value="";
		 document.ebook.content.value="";
         document.ebook.doc.value="";
         
         new_no();
}
function DelRec(){
document.ebook.method="post";
if (document.ebook.accessNo.value==""){
				document.ebook.accessNo.focus();
				alert("Insufficent Data");
				document.ebook.flag.value="new";
				document.ebook.submit();
				}
				else{
				  document.ebook.flag.value="delete";
				  document.ebook.submit();
				 }                         
				
}
			
function load()
{
document.ebook.accessNo.focus();

}


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

      
      


 
 

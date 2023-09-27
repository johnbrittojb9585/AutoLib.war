<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*" import="Lib.Auto.Transfer_Books.BookTransferBean" import="Lib.Auto.Binding.BindingBean" import="Common.Security" import="Lib.Auto.Binding_Books.BookBindingBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="BeanObject" scope="request" class="Lib.Auto.Transfer_Books.BookTransferBean"  type="Lib.Auto.Transfer_Books.BookTransferBean">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Transfer&nbsp;Books</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>

</head>
<body background="/AutoLib/soft.jpg"> <!--onload="load()"-->

<form method="POST" name="Book_Bindings" action="../Transfer_Books/TransferAction">
 <br><br><br>

<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("binding");
%>
<h2 >Transfer&nbsp;Books</h2>
<table align="center" class="contentTable" width="70%">
<tr>
<td>
<table align="center" width="85%">

<tr><td> &nbsp; </td></tr>

  <tr>
  
  
      <td Class="addedit">Trans No<td><input type="text" name="ordno" size="10" readonly>
      <!-- <input type="button" name="find" value="Find" onclick=Find_Access("accessNo")>--></td>
   
      
      
      <td Class="addedit">Doc. Type<td><select name="doc" size="1" style="width:115px">
        	  	<option  value="BOOK">BOOK</option>
          	  	<option value="BOOK BANK">BOOK BANK</option>
	  		  	<option value="NON BOOK">NON BOOK </option>          
	  			<option value="REPORT">REPORT</option>
	  			<option value="THESIS">THESIS</option>          
	  			<option value="STANDARD">STANDARD</option>
	  			<option value="PROCEEDING">PROCEEDING</option>	  
	  			<option value="BACK VOLUME">BACK VOLUME</option></select></td>
	  			
      <td Class="addedit">Trans&nbsp;Date<td> 
      
<%--       <INPUT name=SendDate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
      <INPUT name=SendDate size=10 id="datepicker" value="<%=Security.CalenderDate()%>">
      				
		 </td></tr>
		 
		
    
    <tr>
      
      <td Class="addedit">Dept Name<td colspan=3> <SELECT SIZE="1" NAME="txtBinder" style="width:270px">
                <OPTION SELECTED VALUE="NO">--------------------------------------------------------</OPTION>
                                        <% 
			                           Iterator it=sc.iterator();
                                        while(it.hasNext()){
                                        	                                        	
                                        	BindingBean view=(BindingBean) it.next();                                        	
				                     
				                        int dept_code=view.getCode();
				                        String dept_name=view.getName();
				                       
				                       out.print("<option  value="+dept_code+">" +dept_name+"</option>");
                                       }
				                        %>

              </SELECT></td>
              
   <td Class="addedit">Acc. No<td> <input type="text" name="AccessNo" size="10" maxlength="15" onKeydown="Javascript: if (event.keyCode==13) Save_Bind();">
   <!-- <input type="button" name="find" value="Find" onclick=Find_Access("accessNo")>--></td>
    </tr>
 
    
    <tr>
    <td >&nbsp;</td>
    </tr>


    <tr>
      <td colspan="5" >
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="New" name="New" Class="submitButton" onclick="New_Binding()">
      <input type="button" value="Display" name="display" Class="submitButton" onclick="Display_Binding()">
      <input type="button" value="Transfer" name="save" Class="submitButton" onclick="Save_Bind()">
      <input type="button" value="Re-Transfer" name="return" Class="submitButton" onclick="Return_Binding()">
      <input type="button" value="Clear" Class="submitButton" name="clear" onclick="ClearRec()"></td>
    </tr>

<tr><td>&nbsp;</td></tr>
    
    
  </table>
  <input type="hidden" name="flag">
  <input type="hidden" name="index">
  </td></table>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		var i="<%=request.getParameter("ind")%>";
		document.Book_Bindings.txtBinder.options[i].selected=true;

		document.Book_Bindings.AccessNo.value="<%=BeanObject.getAccess_no()%>";
        document.Book_Bindings.doc.value="<%=BeanObject.getDocument()%>";
    	document.Book_Bindings.SendDate.value="<%=BeanObject.getDate()%>";
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
                                document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;                                     	document.Book_Bindings.flag.value="update";
				document.Book_Bindings.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("display")){
			
			try{

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    
		    out.print(".");//for display space--shek
		    
		    out.print("<tr><th>Trans&nbsp;No<th>Access&nbsp;No<th>Document<th>Date<th>Dept Name</tr>");
                    Iterator it1=sc.iterator();
			while(it1.hasNext()){
				BookTransferBean view=(BookTransferBean) it1.next();
				
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getordno()%>","<%=view.getAccess_no()%>","<%=view.getDocument()%>","<%=view.getDate()%>","<%=view.getdeptName()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getordno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getAccess_no()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDocument()%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDate()%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getdeptName()%>"+"</td>");
		// alert("<%=view.getdeptName()%>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();

 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
    }

			if(valid.equals("updated")){
          %>
                <script language="JavaScript">

		alert("Record Updated Successfully......");
		clear();
			    </script>

			<%
			}
			if(valid.equals("deleted")){
          %>
                <script language="JavaScript">

		alert("Record Re-Transfered Successfully......");
		clear();
			    </script>

			<%
			}
			if(valid.equals("saved")){
%>
                <script language="JavaScript">

		alert("Record Transfered Successfully......");
					document.Book_Bindings.flag.value="new";
 				    document.Book_Bindings.submit();
			    </script>

			<%
			}
			if(valid.equals("Issuedbook")){
						%>
				        <script language="JavaScript">
						alert("Document is ISSUED......");
						document.Book_Bindings.flag.value="new";
 				        document.Book_Bindings.submit();
						//clear();
					    </script>
						<%
							             }	
			if(valid.equals("Availbable")){
				
				
				%>
		        <script language="JavaScript">
		        
		       alert("Document is "+"<%=BeanObject.getAvail()%>"+"");
				document.Book_Bindings.flag.value="new";
			        document.Book_Bindings.submit();
				//clear();
			    </script>
				<%
					             }	
			
			if(valid.equals("TransferBooks")){
				        %>
				        <script language="JavaScript">
						alert("Document is Already TRANSFERED......");
						document.Book_Bindings.flag.value="new";
 				        document.Book_Bindings.submit();

						</script>
						<%
							                 }	
			if(valid.equals("newordno")){
		        %>
		        <script language="JavaScript">
				 document.Book_Bindings.ordno.value="<%=BeanObject.getordno()%>";
				
				</script>
				<%
			
				

				
					                 }	
			if(valid.equals("notPresent")){
                       %>
                      <script language="JavaScript">
		              alert("Record Not Present......");		
		              clear();
			          </script>

			<%
			}




}
}

%>
<script language="JavaScript">
 function show(val1,val2,val3,val4,val5){
 document.Book_Bindings.ordno.value=val1;
 document.Book_Bindings.AccessNo.value=val2;
 document.Book_Bindings.doc.value=val3;
 document.Book_Bindings.SendDate.value=val4;
 document.Book_Bindings.txtBinder.options[document.Book_Bindings.txtBinder.selectedIndex].text=val5;
 }
  function Find_Access(val){
  winpopup=window.open("../BindingBooks/search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
  }
  function Save_Bind(){
  
  if(document.Book_Bindings.txtBinder.value=="NO" || document.Book_Bindings.AccessNo.value=="" || document.Book_Bindings.ordno.value==""){
  alert("Dept Name/Access No/Order No can not be left blank");
  }else{
  document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;
  document.Book_Bindings.flag.value="save";
  document.Book_Bindings.submit();
  }
  }
  function New_Binding(){
  document.Book_Bindings.flag.value="new";
  document.Book_Bindings.submit();
 
  }
  
  function ClearRec(){
  document.Book_Bindings.flag.value="new";
  document.Book_Bindings.submit();
  }
  function Display_Binding(){
  
  document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;
  document.Book_Bindings.flag.value="display";
  document.Book_Bindings.submit();
 
  }
  function Return_Binding(){
  if(document.Book_Bindings.AccessNo.value==""){
  alert("Please Specify Access No...");
  }else{
   document.Book_Bindings.flag.value="return";
  document.Book_Bindings.submit();
  }
  }
  function load(){
	document.Book_Bindings.ordno.focus();

		 }
</script>



<%@page import="javax.print.Doc"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.io.*" import="java.util.*"
 import="Lib.Auto.Currency.CurrencyBean" session="true" buffer="12kb" import="Common.Security"%>
 <%@ page import="java.text.SimpleDateFormat"%>
 
<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("currency");
%>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

	
	 
%>
<center>
<table  id="table1"  style="display:inline">



<!-- <tr> -->

<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit">Access&nbsp;No<FONT color=red size=2>*</FONT></td>

<!-- <td><input type="text" name="accessNo" id="searchBookCode" class="searchBook" size="15" maxlength=15 onKeyUp="return valid_space()"> -->
<td><input type="text" name="accessNo" id="searchBookCode" class="searchBook" onkeyup="showAccessNo(this.value);" size="15" maxlength=15 >

   </div>
</div>

<input type=button name="Find_AccNo" Class="submitButton" Value="Find" onclick="FindValueaccno('accessNoAuth','BOOK')">

</td>

  
<div class="search-container">
	<div class="ui-widget">
  
<td Class="addedit">Call&nbsp;No</td>
<td><input type="text" name="callNo" id="searchCallNo" class="searchBook" onkeyup="showCallNo(this.value);" size="15" maxlength=20>

    </div>
</div>

<input type=button name="FindSub" Class="submitButton" Value="Find" onclick="FindValue('callNo')">
 </td>
 
    

<td Class="addedit">Rec. Date</td>
<td>
<%-- <INPUT name=rcDate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
<INPUT name=rcDate size=10 id="datepicker" value="<%=dateString%>">
				</td>
</tr>
  
  <div class="search-container">
	<div class="ui-widget">
  
<tr>
<td Class="addedit">Author&nbsp;Name</td>
<!-- <td colspan=3><input type="text" name="author" id="searchAuthorName" class="searchBook" size="50" maxlength=150  onkeyup="Auth_Select()"> -->
<td colspan=3><input type="text" name="author" id="searchAuthor" class="searchBook" onkeyup="showAuthor(this.value);" size="50" maxlength=150 > 
<input type=button name="Find_Aut" Value="Find" Class="submitButton"  onclick="FindValue('Aut')">
</td>

    </div>
</div>

<td Class="addedit">Author&nbsp;Role<td><select name="role" size="1"  style="width: 110px">
       <option value="AUTHOR">AUTHOR</option>
      <option value="EDITOR">EDITOR</option>
      <option value="COMP">COMP</option>
      <option value="TRANS">TRANS</option>
      <option value="ET AL">ET AL</option>
 </select></td>
 
</tr>
  
    <tr>
<div class="search-container">
	<div class="ui-widget">
  
<tr>
<td Class="addedit">Title<FONT color=red size=2>*</FONT></td>
<!-- <td colspan=3><font color="#800000" face="Times New Roman"> <input type=text name=title id="searchTitle" class="searchBook" size=58 maxlength=255 onKeyUp="return validTitle_space()"></font></td> -->
<td colspan=3><font color="#800000" face="Times New Roman"> 
<input type=text name=title contenteditable="true" spellcheck="true" id="searchTitle" class="searchBook" onkeyup="showTitle(this.value);" size=58 maxlength=255 ></font></td>

    </div>
</div>

<!-- <td Class="addedit">Edition</td><td><input type="text" name="edition" size="15" maxlength=20></td> -->

</tr>


    <tr>
<div class="search-container">
	<div class="ui-widget">
  
<tr>
<td Class="addedit">Sub Title</td>
<!-- <td colspan=3><font color="#800000" face="Times New Roman"> <input type=text name=title id="searchTitle" class="searchBook" size=58 maxlength=255 onKeyUp="return validTitle_space()"></font></td> -->
<td colspan=3><font color="#800000" face="Times New Roman"> 
<input type=text name=otherTitle contenteditable="true" spellcheck="true" id="searchTitle" class="searchBook" onkeyup="showTitle(this.value);" size=58 maxlength=255 ></font></td>

    </div>
</div>

<td Class="addedit">Edition</td><td><input type="text" name="edition" size="15" maxlength=20></td>
</tr>

  
  
<div class="search-container">
	<div class="ui-widget">




<tr>
<td Class="addedit">Publisher<FONT color=red size=2>*</FONT></td>
<td colspan=2><input name="pubName" value="NIL" id="searchPublisher" class="searchBook" onkeyup="showPublisher(this.value);" size="23" maxlength=150>
 
 <input type=button name="Find_Publisher" Class="submitButton" Value="Find" onclick="FindValue('Pub')">
 
 </td>

    </div>
</div> 
 
 	 <td Class="addedit">Place&nbsp;&nbsp;&nbsp;&nbsp;<font color="#800000" face="Times New Roman"><input name="place" size="15" maxlength=50></font></td>
 	 
 	 <td Class="addedit">Year Pub</td><td><input name="yop" size="15" maxlength=4 onKeyUp="return Year_val();"></td>
 </tr>

<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit">Subject<FONT color=red size=2>*</FONT></td>
<td colspan=2> <input type="text" name="subName" id="searchSubject" class="searchBook" onkeyup="showSubject(this.value);" size="23" value="NIL">
 
 <input type=button name="FindSub" Class="submitButton" Value="Find" onclick="FindValue('Sub')">
 
 </td>
 
    </div>
</div> 

<td Class="addedit">ISBN&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="isbn" size="15" maxlength=25></td>
<td Class="addedit">Pages<td> <input type="text" name="pages" size="15" maxlength=15></td>


<!-- <td Class="addedit">Pages&nbsp;&nbsp;&nbsp;<input type="text" name="pages" size="15" maxlength=15></td> -->
<!-- <td Class="addedit">ISBN<td> <input type="text" name="isbn" size="15" maxlength=13></td> -->


</tr>

<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit">Department<FONT color=red size=2>*</FONT></td>
<td colspan=3><input type="text" name="deptName" id="searchDept" class="searchBook" onkeyup="showDept(this.value);" size="50" value="NIL"> 

<input type=button name="Find_Dept" value="Find" Class="submitButton" onclick="FindValue('Dept')">

</td>

   </div>
</div>

<td Class="addedit">Course<td>
<select name="type" size="1" style="width: 110px">
 		<option value="UG">UG</option>
      	<option value="PG">PG</option>
        <option value="DIPLOMA">DIPLOMA</option>
      
      </select></td>
</tr> 




<tr>
<!-- <td Class="addedit">Division<FONT color=red size=2>*</FONT></td> -->

<input type="hidden" name="branchName" size="23" value='NIL' readOnly>
 

<!--<input type=button name="FindBranch" Value="Find"  Class="submitButton" onclick="FindValue('Branch')">-->



<td Class="addedit">Location</td><td colspan=3><input type="text" name="location" size="22" maxlength=20>
<b>Trans.Dept</b>&nbsp;<input type="text" name="serTitle" size="22"></td>


 <td Class="addedit">Availability</td>
  <td><select name="avail" size="1" style="width: 110px">
      <option  value="YES">YES</option>
      <option value="REFERENCE">REFERENCE</option>
      <option value="DISPLAY">DISPLAY</option>            
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="LOST">LOST</option>            
      <option value="DAMAGED">DAMAGED</option>
      <option value="ISSUED">ISSUED</option>
      <option value="BINDING">BINDING</option>
	  <option value="TRANSFERED">TRANSFERED</option>
      </select></td>

</tr>
<tr><td>&nbsp;</td></tr>
</TBODY>
</table >
</center>

<center>
<table  id="table2"  style="display:none">
 <TBODY>




<tr>

<td Class="addedit">Purchase Type<td> <select size="1" name="purchaseType" style="width: 110px">
      <option  value="Purchase" >Purchase</option>
      <option value="Gift" >Gift</option>
      <option value="Others" >Others</option>
    </select>
</td>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Supplier<FONT color=red size=2>*</FONT></td>
<td colspan=3><input type="text" name="supName" id="searchSupplier" class="searchBook" onkeyup="showSuppler(this.value);" size="40" value="NIL"> 

    </div>
</div>

<input type=button name="Findsup" Class="submitButton" Value="Find" onclick="FindValue('Sup')">

</td>
</tr>

<tr>
<td Class="addedit">Invoice No<td ><input type=text name=invNo size=15 maxlength=30></td>
<td Class="addedit">Invoice Date<td> 
<%-- <INPUT name=invoiceDate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
<INPUT name=invoiceDate size=10 id="datepicker2" value="<%=dateString%>">
				
</td>

<td Class="addedit">Copies</td><td>
<input type="text" name="copies" size="15" value="1" maxlength=3 onKeyUp="return numValidate();">
</td>
</tr>

<tr>
<td Class="addedit">B-Cost<td>
<input type="text" name="bcost" size="15" value="0" maxlength=5 onkeyup="return BCost_val();"></td>
<td Class="addedit">B-Currency<td><SELECT SIZE="1" NAME="currency" style="width: 110px" onchange="find_amount()">
<!-- 							    <OPTION  VALUE="1"></OPTION> -->
			                            <% 
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                     
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                       
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
				                        %>

	 </SELECT></td>
	 <td Class="addedit">B&nbsp;Price<td><input type="text" name="bprice" size="15" value="0" maxlength=12 onkeyup="accpt_amt()"></td>
	 </tr>
	 <tr>
	<td Class="addedit">Discount<td><input type="text" name="discount" size="15" value="0" maxlength=5 onkeyup="net_amt()">
	
	<td Class="addedit">Shipment</td><td><input type="text" name="addfield4" size="15"  value="0" maxlength=5 onkeyup="shipment_amt()"></td><!-- for add shipment charages -->
    
    <td Class="addedit">Net&nbsp;Price<td><input type="text"  name="acceptPrice" size="15" value="0"  maxlength=9 onkeyup="chkAP_amt()"></td>
    
    
    </tr>
    
    <tr>
<div class="search-container">
	<div class="ui-widget">
    
    <td Class="addedit">BudName<FONT color=red size=2>*</FONT></td>
<!-- <td colspan=6><input type="text" name="budName" id="searchBudget" class="searchBook" onkeyup="showBudget(this.value);" size="75" value="NIL"> -->
<td colspan=6><input type="text" name="budName" id="searchBudget" class="searchBook" onkeyup="showBudget(this.value);" size="75" value="NIL">

   </div>
</div>
 
 <input type=button name="FindBud" Value="Find" Class="submitButton" onclick="FindValue('Bud')">
 
 </td>
    </tr>
    
    
    <tr>
    <td Class="addedit">Av.Resource</td> <td Class="addedit" >
<select name="size" id="size" style="width: 110px">
    <option  value="NO">NO</option> 
    <option  value="YES">YES</option>
    </select></td>
    
	
	
    <td Class="addedit">Av.ResourceNo.</td><td ><input type="text" name="addfield1" size="15"></td> <!-- add_field1 -->
    
<td Class="addedit">DocType</td>
<td><select name="doc" size="1" id="alldoctype" style="width: 110px">
	 <option  value="BOOK">BOOK</option>
      
<!--       <option value="BOOK BANK">BOOK BANK</option> -->
<!-- 	  <option value="NON BOOK">NON BOOK </option> -->
<!-- 	  <option value="REPORT">REPORT</option> -->
<!-- 	  <option value="THESIS">THESIS</option> -->
<!-- 	  <option value="STANDARD">STANDARD</option> -->
<!-- 	  <option value="PROCEEDING">PROCEEDING</option> -->
<!-- 	  <option value="BACK VOLUME">BACK VOLUME</option> -->
	  
	  
</select></td>
    </tr>
    
    
<tr>

		
  <td Class="addedit">Phy&nbsp;Medium<td>
    <select name="physical" size="1" style="width: 110px">
      <option value="PAPER PACK">PAPER PACK</option>
      <option value="AUDIO TAPE">AUDIO TAPE</option>
      <option value="COMPACT DISC">COMPACT DISC</option>
      <option value="FLOPPY DISC">FLOPPY DISC</option>
      <option value="MICRO FLIM">MICRO FLIM</option>
      <option value="MICRO FICHE">MICRO FICHE</option>      
      <option value="VIDEO TAPE">VIDEO TAPE</option>
	  </select></td>
	  
<td Class="addedit">Binding<td ><select name="binding" size="1" style="width: 110px">
		<option value="PAPER PACK">PAPER PACK</option>
		<option value="HARD BINDING">HARD BINDING</option>
      	<option value="LOOSE SHEETS">LOOSE SHEETS</option>
      	<option value="SPIRAL BINDING">SPIRAL BINDING</option>
		</select></td>
	
	 
		<td Class="addedit">Phycal Desc<td ><input type="text" name="script" size="15" maxlength=100>
</tr>



<tr>

<td Class="addedit">Volume No<td><input type="text" name="volumeNo" size="15" maxlength=20></td>
<td Class="addedit">Part No<td><input type="text" name="partNo" size="15" maxlength=20></td>

	
<td Class="addedit">Language<td>
      <select name="language" size="1" style="width: 110px">
      <option value="ENGLISH">ENGLISH</option>
      <option value="TAMIL">TAMIL</option>
      <option value="MALAYALAM">MALAYALAM</option>
      <option value="TELUGU">TELUGU</option>
      <option value="KANADAM">KANADAM</option>
      <option value="HINDI">HINDI</option>
      <option value="MARATHI">MARATHI</option>
      <option value="URUDU">URUDU</option>
      <option value="PUNJABI">PUNJABI</option>
      <option value="GUJARATHI">GUJARATHI</option>
      <option value="ORRIA">ORRIA</option>
      <option value="BENGALI">BENGALI</option>
      <option value="ASSAME">ASSAME</option>
	  </select></td>
		
</tr>
    
    

<tr><td>&nbsp;</td></tr>

  </TBODY>
</TABLE>

</center>


<center>

<table id="table3" style="display:none">

<tbody>


<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit">Keywords<td><input type="text" name="keywords" id="searchKeywords" class="searchBook" onkeyup="showKeywords(this.value);" size="70">

<input type="button" name="FindKey" Value="Find" Class="submitButton" onclick=FindValue("Key")>

</td>
</tr>

   </div>
</div>   

<!-- <tr> -->
<!-- <td Class="addedit">Sub&nbsp;Title<td><input type="text" name="otherTitle" size="80" maxlength=50>  </td> -->
<!-- </tr> -->


<tr>
<td Class="addedit">StatmntOfRespon<td><input type="text" name="stateRes" size="80" maxlength=50></td>
</tr>

<tr>
<td Class="addedit">Bibliography&nbsp;Notes<td><input type="text" name="bibliography" size="80" maxlength=80>
</tr>

<tr>
<td Class="addedit">Summary<td><input type="text" name="summary" size="80" maxlength=50>
</tr>







<tr>
<td Class="addedit">Contents Page<td ><input type="text" name="contents" size="80" maxlength=100></td>
</tr>

<tr><td>&nbsp;</td></tr>


</tbody></table>


<table  id="table4"  style="display:none">

<tr>



<td Class="addedit">Add_Field2</td><td><input type="text" name="addfield2" size="15" maxlength=30></td>


</tr>

<tr>
<td Class="addedit">ISSN<td><input type="text" name="issn" size="80" maxlength=20>
</tr>

<tr>
<td Class="addedit">Notes<td><input type="text" name="notes" size="80" maxlength=80></td>
</tr>

<tr>
<td Class="addedit">Volume Title<td>
<input type="text" name="volumeTitle" size="27" maxlength=20></td>
<td Class="addedit">Volume Res<td > <input type="text" name="volumeRes" size="30" maxlength=50></td>
</tr>

<tr>
<td Class="addedit">Meeting Name<td> <input type="text" name="meeting" size="27" maxlength=50></td>
<td Class="addedit">Sponsor<td> <input type="text" name="sponsor" size="30" maxlength=20></td>
</tr>

<tr>
<td Class="addedit">Venue<td><input type="text" name="venue" size="27" maxlength=20></td>

<td Class="addedit">Meeting&nbsp;Date<td> 
<%-- <INPUT name=otherDate size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
<INPUT name=otherDate size=15 id="datepicker3" value="<%=dateString%>">
						
</td>
</tr>

<tr>
<td Class="addedit">Corporate&nbsp;Author<td> <input type="text" name="corAut" size="27" maxlength=100></td>
<td Class="addedit">Corporate&nbsp;Address<td> <input type="text" name="corAdd" size="30" maxlength=100></td>
</tr>  

<tr>
<td Class="addedit">Series&nbsp;Author<td> <input type="text" name="serAut" size="27" maxlength=100></td>
<td Class="addedit">Series&nbsp;Name<td> <input type="text" name="serName" size="30" maxlength=100></td>
<tr>


<tr>
<td Class="addedit">Illustration<td ><input type="text" name="illustration" size="30" maxlength=50></td> 

</tr>

<tr>
<td Class="addedit">Add_Field3</td><td ><input type="text" name="addfield3" size="27"></td>

</tr>



</table>



</center>




<input type=hidden name=flag>
<input type=hidden name=name>
<input type=hidden name=Sub value="1">
<input type=hidden name=Dept value="1">
<input type=hidden name=Pub value="1">
<input type=hidden name=Publisher value="1">
<input type=hidden name=Sup value="2">
<input type=hidden name=Branch value="1">
<!-- <input type=hidden name=Bud value="1"> SHEK-->
<input type=hidden name=Bud value="1">
<input type=hidden name=Author_value value="1">
<input type=hidden name=doc_type>



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

<tr>
<td Class="addedit">Access&nbsp;No<FONT color=red size=2>*</FONT></td>
<td>
<!-- <input type="text" name="accessNo" size="10" maxlength=10 onKeyUp="return valid_space()"> -->
<input type="text" name="accessNo" id="searchBookCode" class="searchBook" onkeyup="showAccessNo(this.value);" size="10" maxlength=10 >
<input type=button name="Find_AccNo" Class="submitButton" Value="Find" onclick="FindValue('accessNo','STANDARD')">
 </td>
 
 
 <td Class="addedit">Code No</td><td ><input type="text" name="addfield1" size="12"></td><!-- Addfield1 -->
 
 
<td Class="addedit">Rec. Date</td>
<td>
<%-- <INPUT name=rcDate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
<INPUT name=rcDate size=10 id="datepicker" value="<%=dateString%>">
								
</td>
</tr>

<tr>
<td Class="addedit">Title<FONT color=red size=2>*</FONT></td>
<td colspan=3><font color="#800000" face="Times New Roman"> 
<!-- <input type=text name=title size=44 onKeyUp="return validTitle_space()"> -->
<input type=text name=title id="searchTitle" class="searchBook" onkeyup="showTitle(this.value);" size=44 >
</font></td>
<td Class="addedit">Call&nbsp;No</td><td><input type="text" name="callNo" id="searchCallNo" class="searchBook" onkeyup="showCallNo(this.value);" size="10" maxlength=20></td>
</tr>



<tr>
<td Class="addedit">Sub&nbsp;Title</td><td colspan=3> <input type="text" name="otherTitle" size="44" maxlength=50></td>
<td Class="addedit">Place<td ><font color="#800000" face="Times New Roman"><input name="place" size="10"></font></td>
</tr>

<tr>
<td Class="addedit">Edition</td><td><input type="text" name="edition" size="15" maxlength=20></td>
<td Class="addedit">YearPub</td><td><input name="yop" size="12" maxlength=4 onKeyUp="return Year_val();"></td>
 <td Class="addedit">Availability</td><td><select name="avail" size="1" style="width: 82px">
 	  <option  value="YES">YES</option>
      <option value="REFERENCE">REFERENCE</option>
      <option value="DISPLAY">DISPLAY</option>            
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="LOST">LOST</option>            
      <option value="DAMAGED">DAMAGED</option>
      <option value="ISSUED">ISSUED</option>
      <option value="BINDING">BINDING</option>
</select></td>
</tr>


<tr>
<td Class="addedit">Pages<td> <input type="text" name="pages" size="15" maxlength=10></td>
<td Class="addedit"> B-Cost<td> <input type="text" name="bcost" size="12" value="0" maxlength=5 onkeyup="return BCost_val();"></td>
	<td Class="addedit">B-Currency<td> <SELECT SIZE="1" style="width: 82px" NAME="currency" onchange="find_amount()" >
    <OPTION  VALUE="1">Rupees</OPTION>
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
	 
    
    </tr>
    
    
    <tr>
    <td Class="addedit">Book&nbsp;Price<td ><input type="text" name="bprice" size="15" value="0" maxlength=12 onkeyup="accpt_amt()"></td>
    <td Class="addedit">Discount<td ><input type="text" name="discount" size="12" value="0" maxlength=5 onkeyup="net_amt()"></td>
	<td Class="addedit">Net&nbsp;Price<td > <input type="text"  name="acceptPrice" size="10" value="0"  maxlength=9 onkeyup="chkAP_amt()"></td>
</tr>


<tr>
<td Class="addedit">Subject<FONT color=red size=2>*</FONT></td>
<td colspan=3><input type="text" name="subName" id="searchSubject" class="searchBook" onkeyup="showSubject(this.value);" size="36" value="NIL">
<input type=button name="FindSub" Class="submitButton" Value="Find" onclick=FindValue("Sub")></td>

<td Class="addedit">Invoice No<td colspan=3><input type=text name=invNo size=10 maxlength=30></td>
</tr>

<tr>
<td Class="addedit">Publisher<FONT color=red size=2>*</FONT></td>
<td colspan=5><input name="pubName" id="searchPublisher" class="searchBook" onkeyup="showPublisher(this.value);" value="NIL" size="65">
<input type=button name="Find_Publisher" Class="submitButton" Value="Find" onclick=FindValue("Pub")></td>
</tr>

<tr>
<td Class="addedit">Supplier<FONT color=red size=2>*</FONT></td>
<td colspan=5><input type="text" name="supName" id="searchSupplier" class="searchBook" onkeyup="showSuppler(this.value);" size="65" value="NIL">
<input type=button name="Findsup" Class="submitButton" Value="Find" onclick=FindValue('Sup')></td>
</tr>

<tr>
</tr>


  </TBODY>
</TABLE>

</center>



<center>
<table  id="table2"  style="display:none">


  <TBODY>


<tr>
<td Class="addedit">Department<FONT color=red size=2>*</FONT></td>
<td><input type="text" name="deptName" id="searchDept" class="searchBook" onkeyup="showDept(this.value);" size="70" value="NIL">
<input type=button name="Find_Dept" value="Find" Class="submitButton" onclick="FindValue('Dept')"></td>
</tr>

<tr>
<td Class="addedit">Budget&nbsp;Name<FONT color=red size=2>*</FONT></td><td><input type="text" name="budName" id="searchBudget" class="searchBook" onkeyup="showBudget(this.value);" size="70" value="NIL">
<input type=button name="FindBud" Value="Find" Class="submitButton" onclick=FindValue("Bud")></td>
</tr>

<tr>
<!-- <td Class="addedit">Division<FONT color=red size=2>*</FONT></td> -->
 <input type="hidden" name="branchName" size="78" value='NIL' readOnly> 
<!-- <input type=button name="FindBranch" Value="Find"  Class="submitButton" onclick="FindValue('Branch')"> -->


</tr>

<tr>
<td Class="addedit">Keywords <td><input type="text" name="keywords" id="searchKeywords" class="searchBook" onkeyup="showKeywords(this.value);" size="70">
<input type="button" name="FindKey" Value="Find" Class="submitButton" onclick=FindValue("Key")></td>
<tr>




<tr>
<td Class="addedit">Notes<td ><input type="text" name="notes" size="78" maxlength=80></td>
</tr>

<tr> 
<td Class="addedit">Location<td> <input type="text" name="location" size="78" maxlength=20></td>
</tr>



</TBODY>



<center>


<table  id="table3" style="display:none">







<tr>
<td Class="addedit">Type
<td >
<select size="1" name="purchaseType" >
      <option  value="Purchase" >Purchase&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      <option value="Gift" >Gift&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      <option value="Others" >Others&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
    </select></font>
</td ></tr>    



<tr>




</tr>
<tr>
<td Class="addedit">Invoice Date<td >
	<INPUT name=invoiceDate size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Book.invoiceDate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>

</tr>
<tr>
<td Class="addedit">Volume No
<td ><input type="text" name="volumeNo" size="24" maxlength=20>
<td Class="addedit">Part No
<td><input type="text" name="partNo" size="24" maxlength=20>
</td></tr>
<tr>

<td Class="addedit">Language<td>
    <select name="language" size="1">
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
&nbsp; </select>
   </tr>
<tr>
<td Class="addedit">ISBN<td >
    <input type="text" name="isbn" size="24" maxlength=13></td>
<td Class="addedit">ISSN<td >
   <input type="text" name="issn" size="24" maxlength=20> </font>
</td></tr>
<tr><td Class="addedit">Physical Desc
<td>
  <input type="text" name="size" size="24" maxlength=5 >&nbsp;&nbsp;&nbsp;
</td>
<td Class="addedit">AV&nbsp;Resource<td ><select name="type" size="1">
      <option value="NO">NO</option>    
      <option value="YES">YES</option>        
&nbsp; </select>
</td></tr>  
</tr>
  <td Class="addedit">Physical&nbsp;Medium<td >
    <select name="physical" size="1">
      <option value="PAPER PACK">PAPER PACK</option>
      <option value="AUDIO TAPE">AUDIO TAPE</option>
      <option value="COMPACT DISC">COMPACT DISC</option>
      <option value="FLOPPY DISC">FLOPPY DISC</option>
      <option value="MICRO FLIM">MICRO FLIM</option>
      <option value="MICRO FICHE">MICRO FICHE</option>      
      <option value="VIDEO TAPE">VIDEO TAPE</option>
&nbsp; </select>
</td>


<td Class="addedit">Binding<td ><select name="binding" size="1">
<option value="HARD BINDING">HARD BINDING</option>
      <option value="LOOSE SHEETS">LOOSE SHEETS</option>
      <option value="PAPER PACK">PAPER PACK</option>
      <option value="SPIRAL BINDING">SPIRAL BINDING</option>
&nbsp; </select>
</td>
</tr>



<tr><td Class="addedit">Summary<td ><input type="text" name="summary" size="75" maxlength=80>
<tr><td Class="addedit">Bibliography&nbsp;Notes<td ><input type="text" name="bibliography" size="75" maxlength=80>
<tr><td Class="addedit">Contents Page<td ><input type="text" name="contents" size="19" maxlength=80><!-- <input type=file name="contents" readonly="true" Value="contents" onclick="contents()">-->




<tr>
<td Class="addedit">Author&nbsp;Name</td>
<td><input type="text" name="author"  size="20"  onkeyup="Auth_Select()">
	<input type=button name="Find_Aut" Value="Find" Class="submitButton"  onclick="FindValue('Aut')">
</td>
<td Class="addedit">Author&nbsp;Role<td ><select name="role" size="1">
       <option value="AUTHOR">AUTHOR</option>
      <option value="EDITOR">EDITOR</option>
      <option value="COMP">COMP</option>
      <option value="TRANS">TRANS</option>
      <option value="ET AL">ET AL</option>
 </select></td>
</tr>


<tr>
<td Class="addedit">StatmntOfRespon</td>
<td> <input type="text" name="stateRes" size="20" maxlength=50></td>

</tr>

<tr>


</tr> 

<tr>



</tr> 


<tr><td Class="addedit">Copies</td><td >
<input type="text" name="copies" size="5" value="1" maxlength=3 onKeyUp="return numValidate();">
</td>
<td Class="addedit">Document&nbsp;type</td><td >
  <select name="doc" size="1" id="alldoctype" onchange="leaveChange()">
<option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>
	  <option value="BACK VOLUME">BACK VOLUME</option>
</select>
</td>
</tr>

</TBODY>
</table >
</center>

<center>
<table  id="table4"  style="display:none">



</tr>
<tr>
<td Class="addedit">Volume Title&nbsp;
<td><input type="text" name="volumeTitle" size="27" maxlength=20 >&nbsp;&nbsp;&nbsp;</td>
<td Class="addedit">Volume Res<td ><input type="text" name="volumeRes" size="30" maxlength=50>
 <tr>
<td Class="addedit">Meeting Name&nbsp;
<td ><input type="text" name="meeting" size="27" maxlength=50>
<td Class="addedit">Sponsor
<td >
 <input type="text" name="sponsor" size="30" maxlength=20>
<tr><td Class="addedit">Venue<td ><input type="text" name="venue" size="27" maxlength=20>
<td Class="addedit">Meeting&nbsp;Date<td >
<INPUT name=otherDate size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Book.otherDate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
</SCRIPT>
<tr>
<tr>
<td Class="addedit">Corporate&nbsp;Author</font>
<td ><input type="text" name="corAut" size="27" maxlength=100></font>
<td Class="addedit">Corporate&nbsp;Address</font>
<td >
<input type="text" name="corAdd" size="30" maxlength=100>
</td></tr>  
  
<tr><td Class="addedit">Series&nbsp;Author
<td >
<input type="text" name="serAut" size="27" maxlength=100>
	<td Class="addedit">Series&nbsp;Name<td>
<input type="text" name="serName" size="30" maxlength=100>
<tr><td Class="addedit">Series&nbsp;Title</font>
<td >
<input type="text" name="serTitle" size="27"></td>



</tr>
<tr>
<td Class="addedit">Script<td ><input type="text" name="script" size="27" maxlength=20>
</td>
<td Class="addedit">Illustration<td ><input type="text" name="illustration" size="30" maxlength=50></td> 
</tr>
<!--<td >Publisher
<td >
  <input type="text" name="pubName" size="23" value="NIL" readonly><input type=button name="FindPub" Value="Find" onclick=FindValue("Pub")>-->

<tr>



<td Class="addedit">Add_Field2
  </td><td ><input type="text" name="addfield2" size="30" maxlength=20>
  </td></tr>
<tr><td Class="addedit">Add_Field3</td><td ><input type="text" name="addfield3" size="27">
  </td><td Class="addedit">Add_Field4
  </td><td ><input type="text" name="addfield4" size="30" maxlength=20>
     </td></tr>
<input type=hidden name=flag>
<input type=hidden name=name>
<input type=hidden name=Sub value="1">
<input type=hidden name=Dept value="1">
<input type=hidden name=Pub value="1">
<input type=hidden name=Publisher value="1">
<input type=hidden name=Sup value="2">
<input type=hidden name=Branch value="1">
<input type=hidden name=Bud value="1">
<input type=hidden name=Author_value value="1">
<input type=hidden name=doc_type>
</table>
</center>

</table>
</td></tr>
</table>
<jsp:useBean id="ss" scope="request" class="Lib.Auto.Miscellaneous.MiscellaneousBean" >
</jsp:useBean>

<%
if(ss.getPhoto1()!= null) {

	response.reset();
	response.setContentType("image/jpeg");
    response.addHeader("Cache-Control", "no-transform, max-age=0");  

try{
	response.getOutputStream().write(ss.getPhoto1());
	response.getOutputStream().flush();	
    response.getOutputStream().close();
    out.clear(); 
    ss.setPhoto1(null);	
}catch(Exception e){
	e.printStackTrace();
 	ss.setPhoto1(null);
}
    return;
}
%>			 
		
 
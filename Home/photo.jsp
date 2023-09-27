<%
byte[] UserPhoto=null;
UserPhoto=(byte[])session.getAttribute("userImage");

if(UserPhoto!= null) {
	response.reset();
	response.setContentType("image/jpeg");
    response.addHeader("Cache-Control", "no-transform, max-age=0");  
try{
	response.getOutputStream().write(UserPhoto);
	response.getOutputStream().flush();	
    response.getOutputStream().close();
    out.clear(); 
    UserPhoto=null;	
}catch(Exception e){
	e.printStackTrace();
	UserPhoto=null;
}
    return;
}
%>			 
		
 
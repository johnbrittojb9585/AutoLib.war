package Lib.Auto.Photo;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
 
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import org.apache.commons.io.output.*;


public class Photo extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(Photo.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 800000 * 1024 ;   // File Size should be __KB
   private int maxMemSize = 400 * 1024 ;     // Memory Size should be __KB
   private File file ;
   
   private static final int IMG_WIDTH = 683;
   private static final int IMG_HEIGHT = 1024;

/**public void init( ){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 
   }*/
   
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
	   
	   performTask(request, response);
	   
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
       performTask(request, response);
       throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
   }
    
     
   
   public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
   
   try {
	   
	   HttpSession session = request.getSession(true);
	   String res = Security.checkSecurity(1, session, response, request);		
		if(res.equalsIgnoreCase("Failure"))
		{
			response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
			return;
		}
		
		
	   CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
	   
       //		 get Path from Property file 
	   Properties properties=new Properties();
   	   properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
   		
   	   filePath = properties.getProperty("contents.upload.filepath");		
   	   
   	   File tempdir = new File(filePath+"/temp");
	   
       if(tempdir.exists())
	   {
		   tempPath = tempdir + "/";
		   //log4jLogger.info(">>>>>>>>>>> Directory Exists >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
	   }else {
		   if(tempdir.mkdir()){
			   tempPath = tempdir + "/";
		       log4jLogger.info(">>>>>>>>>>> Directory Created  >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
		   }
	   }
   	
	   
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out;

		out = response.getWriter( );	
      
           
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }      
            
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(tempPath));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
  
      List fileItems = upload.parseRequest(request);
      // Process the uploaded file items
      Iterator i = fileItems.iterator();
     
      String member_code = "",flag = "";
      byte[] fileInBytes = null;
      
      
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            /**  String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();            
            
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }           
            fi.write( file ) ;        */   // This code is write the input File to D:\Java Librarry 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin 
           
        	fileInBytes=fi.get();           
                       
            
         }else if(fi.isFormField())
         {       	 
        	 if("member".equalsIgnoreCase(fi.getFieldName())){        		
        		 member_code = fi.getString();
        	 }
        	 if("flag".equalsIgnoreCase(fi.getFieldName())){
        		 flag = fi.getString();
        	 }	
         }        
      }    
      
      
      PhotoBean bean = new PhotoBean();
      
      if(flag.equalsIgnoreCase("SavePhoto"))
      {
        byte[] stutentImageInByte={0xa, 0x2, (byte) 0xff};
      
        BufferedImage studentImage = ImageIO.read(new ByteArrayInputStream(fileInBytes));
		int studentType = studentImage.getType() == 0? BufferedImage.TYPE_BYTE_BINARY : studentImage.getType();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage resizeImageJpg  = resizeImage(studentImage, studentType);
		ImageIO.write(resizeImageJpg , "jpg",baos);
//		ImageIO.write(resizeImageJpg, "jpg",new File( ("C:\\Documents and Settings\\selvapa\\Desktop\\test\\akkk.jpg"))); 
		baos.flush();
		stutentImageInByte = baos.toByteArray();
		baos.close();	
		
      
		bean = new PhotoBean();
        bean.setMemberid(member_code);
        //bean.setPhoto(fileInBytes);       
        bean.setPhoto(stutentImageInByte);
      }
      else    // Clear Member Photo
      {
    	bean = new PhotoBean();
        bean.setMemberid(member_code);
        bean.setPhoto(null);    	  
      }
      
      
      int member_check=ss.getMemberCheck(member_code);
      if (member_check >0)				
      {		
	      int count=ss.getMemberPhotoSave(bean);
		  indexPage ="/Photo/index.jsp?check=success";		
	  }
	  else		{
		  indexPage ="/Photo/index.jsp?check=FailMember";
	  }     
      
    dispatch(request, response, indexPage);
      
   }catch(Exception ex) {
	   indexPage ="/Photo/index.jsp?check=LargeFile";
	   log4jLogger.info("AutoLibError:"+ex);
	   dispatch(request, response, indexPage);
   }
   
   }catch (IOException e) {
		// TODO Auto-generated catch block	   
		e.printStackTrace();
   }   
   }
   
   public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
   
   
   
   
   
   
   private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
	    }
   
   
   
//Added By Karthikeyan A For Automatically Convert Photo Pixcel and Save 28/10/2013	 
	    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
	 
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);
	 
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	 
		return resizedImage;
	   }	

   
   
   
   
   
   
   
    
}
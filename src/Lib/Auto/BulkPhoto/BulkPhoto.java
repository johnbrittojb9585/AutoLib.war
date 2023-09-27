package Lib.Auto.BulkPhoto;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Photo.PhotoBean;

public class BulkPhoto extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(BulkPhoto.class);

	private boolean isMultipart;
	private String filePath = "", tempPath = "", indexPage = null;
	private int maxFileSize = 800000 * 1024; // File Size should be __KB
	private int maxMemSize = 400 * 1024; // Memory Size should be __KB
	 private static final int BUFFER_SIZE = 10000000;
	private static final int IMG_WIDTH = 683;
	private static final int IMG_HEIGHT = 1024;
	   private File file ; 
	   String flag="";

	   private  String OUTPUT_FOLDER = "";
	 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		performTask(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		performTask(request, response);
		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
	  
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
		
			log4jLogger.info(":::::::::::::::::::::inside Bulk update::::::::::::::");
			try {
				Properties properties=new Properties();
			   	properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			   	filePath = properties.getProperty("userphoto.upload.filepath");		
			   	File tempdir = new File(filePath+"/temp");
				   if(tempdir.exists())
				   {
					   tempPath = tempdir + "/";
				   }else {
					   if(tempdir.mkdir()){
						   tempPath = tempdir + "/";
					   }
				   }

				   		ServletFileUpload.isMultipartContent(request);         
				 			     
				      DiskFileItemFactory factory = new DiskFileItemFactory();
				      // maximum size that will be stored in memory
				      factory.setSizeThreshold(maxMemSize);
				      // Location to save data that is larger than maxMemSize.
				      factory.setRepository(new File(tempPath)); //  by RK

				      // Create a new file upload handler
				      ServletFileUpload upload = new ServletFileUpload(factory);
				      // maximum file size to be uploaded.
				      upload.setSizeMax( maxFileSize );
				      
				    
				    	  String fileName="";
				    	  
				          // Parse the request to get file items.
				      
				          List<?> fileItems = upload.parseRequest(request);
				          // Process the uploaded file items
				         
				          Date date = new Date();
				          String filename = date.toString().substring(date.toString().length()-4,date.toString().length())+date.toString().substring(4,7)+date.toString().substring(8,10)+"_"+date.toString().substring(11,13)+date.toString().substring(14,16)+date.toString().substring(17,19); //file name with datetime creation
						   	  
				        	File dir = new File(filePath+"/UserPhoto_"+filename); //filename
				        	
				        	if(dir.exists())
				        	{
				        		filePath = dir + "/";
				        	}else {
				        		if(dir.mkdir()){
				        		   filePath = dir + "/";
				        		}
				        	}
				        	  
				        	Iterator<?> k = fileItems.iterator();  
				        	  
				            while ( k.hasNext () ) 
				            {       	 
				              FileItem fi = (FileItem)k.next();    	  
				              if ( !fi.isFormField () )	
				              {
				                 fileName = "UserPhoto_"+filename+".Zip";

				                 // Write the file
				                 if( fileName.lastIndexOf("\\") >= 0 )   {           	 
				             
				                	file = new File( filePath + 
				                    fileName.substring( fileName.lastIndexOf("\\"))) ;
				                 }
				                 else{
				                 
				                    file = new File( filePath + 
				                    fileName.substring(fileName.lastIndexOf("\\")+1)) ;
				                 }  
				                 
				                 fi.write( file ) ;
				                 
					              }              	  
				            }
				            
				            String zipFile=filePath.replace(filePath.substring(filePath.length()-1), ""+"\\"+fileName);//get zip file path
				            BulkPhoto unZip = new BulkPhoto();
				            unZip.unZipIt(zipFile,filePath);
				        	  
				PhotoBean bean = new PhotoBean();
				
				File f = null;
				String[] paths;
				f = new File(filePath);
				paths = f.list();	  	
				int success=0,failed=0;//for images count dispaly in jsp
				
				ss.getBulkPhotoUploadDelete();

				         for(String path:paths)
				         {
				        	 
				        	 byte[] stutentImageInByte = { 0xa, 0x2, (byte) 0xff };
				        	BufferedImage studentImage = ImageIO.read(new File(filePath+path));
				        	
				        	
				        	if(studentImage!=null){
				        		log4jLogger.info("StudentImage::::::::::"+studentImage);
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(studentImage, "jpg", baos);
								stutentImageInByte=baos.toByteArray();
								
								int studentType = studentImage.getType() == 0 ? BufferedImage.TYPE_BYTE_BINARY : studentImage.getType();

					        	ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
					           	BufferedImage resizeImageJpg = resizeImage(studentImage,studentType);
					        	ImageIO.write(resizeImageJpg, "jpg", baos1);
					        	baos1.flush();
					        	stutentImageInByte = baos1.toByteArray();
					        	baos1.close();

								bean = new PhotoBean();
								//bean.setMemberid(path.replace(".jpg", ""));// for get userId
								bean.setMemberid(path.split("\\.")[0]);// for get userId
								bean.setPhoto(stutentImageInByte);
								log4jLogger.info("Member_code : "+bean.getMemberid().toUpperCase());
								
								int count=ss.getMemberCheck(bean.getMemberid());
								if(count==1){
									ss.getBulkPhotoMemberCode(bean.getMemberid().toUpperCase(),1);
									ss.getMemberPhotoSave(bean);
									success=success+1;
								}else{
									ss.getBulkPhotoMemberCode(bean.getMemberid().toUpperCase(),0);
									failed=failed+1;
								}
								
								
				        	}
							
							
				      	    System.out.println(path); 
				      	   
				         }
				         log4jLogger.info(":::::::::::Success::::::"+success);
				         log4jLogger.info(":::::::::::failed::::::"+failed);
				         indexPage = "/BulkPhoto/index.jsp?check=success";
				         request.setAttribute("successPhotoCount", success);
				         request.setAttribute("failedPhotoCount", failed);
				         dispatch(request, response, indexPage);    
				         
			} catch (Exception ex) {
				indexPage = "/BulkPhoto/index.jsp?check=LargeFile";
				log4jLogger.info("AutoLibError:" + ex);
				dispatch(request, response, indexPage);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	private static BufferedImage resizeImage(BufferedImage originalImage,
			int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}
	
	
	
	 public void unZipIt(String zipFile, String outputFolder){
		 
	     byte[] buffer = new byte[1024];
	 log4jLogger.info("From Path"+zipFile);
	 log4jLogger.info("to Path"+outputFolder);
	 
	     try{
	 
	    	//create output directory is not exists
	    	File folder = new File(OUTPUT_FOLDER);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	 
	    	//get the zip file content
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(zipFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	 
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	    	System.out.println("Done");
	 
	    }catch(IOException ex){
	       ex.printStackTrace(); 
	    }
	   }
	
	
	
	
	
	
	
}

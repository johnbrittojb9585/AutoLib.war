package Lib.Auto.BackVolume_Import;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
 
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member_Import.MemberDataMessage;

import org.apache.commons.io.output.*;


public class ImportBvolume extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportBvolume.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
   private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
   private File file ;   
 
   
   
   
   
   
 /**  public void init( ){
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
		
		
	   AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
	   /*int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));*/  // For Titan
	   //CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();

       //	 get Path from Property file 
	   Properties properties=new Properties();
	   properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
  		
	   filePath = properties.getProperty("book.import.filepath");		
   	   
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
	   
	   
	   BvolumeDataMessage message =new BvolumeDataMessage();
	   List<Object> saveDetail = new ArrayList<Object>();
	   int errorCode=0;
	   String errorException = "";
	   
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);         
            
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
      Iterator j = fileItems.iterator();
     
      String member_code = "",flag = "";
      byte[] fileInBytes = null;
      
   /**   
      Properties properties=new Properties();
	  properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
		
	  filePath = properties.getProperty("book.import.filepath");	*/
      
      
      while ( j.hasNext () ) 
      {
         FileItem fi = (FileItem)j.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
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
            fi.write( file ) ;         // This code is write the input File to D:\Java Librarry 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin.  when you are not set filepath from properties file. 
           
        	fileInBytes=fi.get();           
                       
        	log4jLogger.info("BookVolumeImport:File Path: "+filePath+" AbsolutePath:"+file.getAbsolutePath()+" File:"+file);
        	
            
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
      
      Set set = new HashSet();
      
      try{   	  
    	  
          FileInputStream input = new FileInputStream(file.getAbsolutePath());  
          POIFSFileSystem fs = new POIFSFileSystem( input );  
          HSSFWorkbook wb = new HSSFWorkbook(fs);  
          HSSFSheet sheet = wb.getSheetAt(0);  
          HSSFRow row;
          HSSFCell cell;          
          
          
          for(int i=1; i<=sheet.getLastRowNum(); i++)
          {
        	          	  
        	  BvolumeDataBean bookData = new BvolumeDataBean();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new BvolumeDataMessage();
        		  message = ValidateError("Access_No",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  bookData.setAccessNo(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
        		  
        		  message = new BvolumeDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new BvolumeDataMessage();
            		  message = ValidateError("B.V-Access_No Already Exists in Table",i);
            		  break;        			  
        		  }

        		  if(!set.add(bookData.getAccessNo())) {     // For Checking Duplicate B.V-Access_No in Excel
        			  message = new BvolumeDataMessage();
            		  message = ValidateError("B.V-Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAccessNo(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		  message = new BvolumeDataMessage();
        		  message = ss.getCheckAccessNo(bookData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new BvolumeDataMessage();
            		  message = ValidateError("B.V-Access_No Already Exists in Table",i);
            		  break;        			  
        		  }
        		  
        		  if(!set.add(bookData.getAccessNo())) {
        			  message = new BvolumeDataMessage();
            		  message = ValidateError("B.V-Access_No Already Exists in Excel",i);
            		  break; 
        		  }
        	  }
        	  else	  {  
        		  log4jLogger.info(">>>>>>>>>>>>>Error in B.V-AccessNo:"+i);
        	  }
        	  
      	  
        	  
              cell=row.getCell((short) 1);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  message = new BvolumeDataMessage();
        		  message = ValidateError("Title",i);
        		  break;
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setTitle(String.valueOf(row.getCell((short) 1).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setTitle(String.valueOf((long)row.getCell((short) 1).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Journal Name:"+i);
        	  }
        	  
        	  
        	  
                 cell=row.getCell((short) 2);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setVolumeNo("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setVolumeNo(String.valueOf(row.getCell((short) 2).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setVolumeNo(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in VolumeNo:"+i);
        	  } 
        	  
        	  
              cell=row.getCell((short) 3);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAddfield3("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAddfield3(String.valueOf(row.getCell((short) 3).getStringCellValue()));          		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAddfield3(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Issue No:"+i);
        	  }   
            
              
                 cell=row.getCell((short) 4);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setScript("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setScript(String.valueOf(row.getCell((short) 4).getStringCellValue()));          		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setScript(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Script:"+i);
        	  } 
        	       	  
              cell=row.getCell((short) 5);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setPubyear("");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setPubyear(String.valueOf(row.getCell((short) 5).getStringCellValue())); 
        		  /*message = new BvolumeDataMessage();
        		  message = ValidateError("Publisher Year is Invalid",i);
        		  break;*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setPubyear(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in PubYear:"+i);
        	  }
        	  
      	  
//              cell=row.getCell((short) 10);       	  
//        	  
//        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
//              {
//        		  message = new BvolumeDataMessage();
//        		  message = ValidateError("Document",i);
//        		  break;
//              }              
//        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
//        	  {
//        		  bookData.setDocument(String.valueOf(row.getCell((short) 10).getStringCellValue()));
//        		  
//        		  if(bookData.getDocument().equalsIgnoreCase("BOOK"))
//        		  {        	
//        			  bookData.setDocument("BOOK");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("BOOK BANK"))
//        		  {   
//        			  bookData.setDocument("BOOK BANK");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("NON BOOK"))
//        		  {   
//        			  bookData.setDocument("NON BOOK");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("REPORT"))
//        		  {     
//        			  bookData.setDocument("REPORT");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("THESIS"))
//        		  {
//        			  bookData.setDocument("THESIS");     			  
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("STANDARD"))
//        		  {
//        			  bookData.setDocument("STANDARD");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("PROCEEDING"))
//        		  { 
//        			  bookData.setDocument("PROCEEDING");
//        		  }
//        		  else if(bookData.getDocument().equalsIgnoreCase("BACK VOLUME"))
//        		  {
//        			  bookData.setDocument("BACK VOLUME");
//        		  }
//        		  else  {
//        			  message = new BvolumeDataMessage();
//            		  message = ValidateError("Document  is invalid",i);
//            		  break;        			  
//        		  }        		  
//        	  }
//        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
//        	  {
//        		  bookData.setDocument(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));
//        		  message = new BvolumeDataMessage();
//        		  message = ValidateError("Document is invalid",i);
//        		  break;        		  
//        	  }
//        	  else	  {
//        		  log4jLogger.info(">>>>>>>>>>>>>Error in Document:"+i);
//        	  }             
        	  
              cell=row.getCell((short) 6);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setAvailability("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setAvailability(String.valueOf(row.getCell((short) 6).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setAvailability(String.valueOf((long)row.getCell((short) 6).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Availability:"+i);
        	  } 
        	  
        	  
              cell=row.getCell((short) 7);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setLocation("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setLocation(String.valueOf(row.getCell((short) 7).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setLocation(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Location:"+i);
        	  }             	  

              /*cell=row.getCell((short) 8);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  //bookData.setBranch("NIL");
        		  message = new BvolumeDataMessage();
        		  message = ValidateError("Division Name is Invalid",i);
        		  break; 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setBranch(String.valueOf(row.getCell((short) 8).getStringCellValue()));  
        		  *//**int bcode = cs.getBookBranchCode(bookData.getBranch());
        		  bookData.setBranchCode(bcode);*//*
                  int count = ss.getBranchCode(bookData.getBranch());
        		  
        		  if(count == 0)
        		  {
        			  message = new BvolumeDataMessage();
            		  message = ValidateError("Division Not Exists in Table. Create First.",i);
            		  break;        			  
        		  }
        		  
        		  if(branchID > 0)
        		  {
        			  if(branchID!=count)
        			  {
        				  message = new BvolumeDataMessage();
                		  message = ValidateError("You can't upload other division book details.",i);
                		  break;
        			  }
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setBranch(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
        		  message = new BvolumeDataMessage();
        		  message = ValidateError("Division Name is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Division:"+i);
        	  }*/
        	  
        	  
        	  
              cell=row.getCell((short) 8);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  bookData.setSummary("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  bookData.setSummary(String.valueOf(row.getCell((short) 8).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  bookData.setSummary(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Summary:"+i);
        	  }  
        	  
        	  bookData.setAuthor("NIL");
        	  bookData.setDepartmentCode(1);
        	  bookData.setBudgetCode(1);
        	  bookData.setPublisherCode(1);
        	  bookData.setBranchCode(1);
        	  bookData.setSuplierCode(2);
        	  bookData.setSubjectCode(1);
        	  bookData.setDocument("BACK VOLUME");
        	  bookData.setLanguage("ENGLISH");
        	  bookData.setEdition("");
        	  bookData.setCallNo("");
        	  bookData.setPrice(0.0);
        	  bookData.setReceiveDate("1800-01-01");
        	  bookData.setPages("");
        	  bookData.setInvoiceno("");
        	  bookData.setInvoiceDate("1800-01-01");
        	  bookData.setNetprice(0.0);
        	  bookData.setDiscount(0.0);
        	  bookData.setAddfield1("");
        	  bookData.setAddfield2("");
        	  bookData.setKeywords("");
        	  bookData.setNotes("");
        	 
      	  
             
        	  
              
        	 
        	  
        	  
        	  saveDetail.add(bookData);
        	  
        	  //employeeService(bookData);       // call to spring service layer	  

          } 
          
          
          
} catch (FileNotFoundException ec) {	
	log4jLogger.info("AutoLibError:FileNotFound:"+ec);
    ec.printStackTrace();
} catch (IOException e) {
	log4jLogger.info("AutoLibError:IOException:"+e);
	errorException = "InvalidFile";	
	//e.printStackTrace();
} catch (NumberFormatException e) {
	log4jLogger.info("AutoLibError:NumberFormat:"+e);
    e.printStackTrace();
} catch (NullPointerException e) {
	log4jLogger.info("AutoLibError:NullPointer:"+e);
	  message = new BvolumeDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/BackVolume_Import/index.jsp?check=InvalidFile";
  }else {
        	
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {    	
    	request.setAttribute("bean",message);
    	indexPage ="/BackVolume_Import/index.jsp?check=dataerror";
    	
    }else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getImportBvolumeData(saveDetail);
    		indexPage ="/BackVolume_Import/index.jsp?check=success";
    	}else
    	{
    		indexPage ="/BackVolume_Import/index.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/BackVolume_Import/index.jsp?check=LargeFile";
	   log4jLogger.info("AutoLibError:FileException:"+ex);
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
   
   
   
   
   
   private void employeeService(BvolumeDataBean employee)
   {
	   log4jLogger.info("=======Inside employeeService=========="+employee.getAccessNo());
   	
   	try{               	  
   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
   	       Statement stat=con.createStatement();       
   	 
  	       
   	       int k=stat.executeUpdate("insert into excel values('"+employee.getAccessNo()+"','"+employee.getTitle()+"','"+employee.getAuthor()+"')");
   	       log4jLogger.info("Data is inserted");
   	       stat.close();
   	       con.close();
   	   }
   	   catch(Exception e){	  
   		log4jLogger.info("AutoLibError:DBConnect:"+e);
   	   }   	
   }
   
   
   public BvolumeDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   BvolumeDataMessage message = new BvolumeDataMessage();	   
	   
	   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
	   return message;
	   
   }
   
   
   
    
}
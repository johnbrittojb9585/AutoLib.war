package Lib.Auto.EBookImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
import Lib.Auto.EBookImport.EBookDataMessage;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBookImport.ImportEBook;


public class ImportEBook extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(ImportEBook.class);

	   private boolean isMultipart;
	   private String filePath="",tempPath="",indexPage=null;
	   private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
	   private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
	   private File file ;   
	 
	   
	   
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
			   CalalogingService cs=BusinessServiceFactory.INSTANCE.getCalalogingService();

		       //  get Path from Property file 
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
		       
			   
			   EBookDataMessage message =new EBookDataMessage();
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
		      
		      
		     /**   Properties properties=new Properties();
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
		            fi.write( file ) ;         // This code is write the input File to D:\Java Library 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin.  when you are not set filepath from properties file. 
		           
		        	fileInBytes=fi.get();           
		                       
		        	log4jLogger.info("BookImport:File Path: "+filePath+" AbsolutePath:"+file.getAbsolutePath()+" File:"+file);
		        	
		            
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
		        	          	  
		        	  EBookDataBean bookData = new EBookDataBean();
		        	  row = sheet.getRow(i);       	  
		        	  
		        	  errorCode = i;
		        	  // Checking Cell Type and Value
		        	  
		        	  cell=row.getCell((short) 0);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {   
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Access_No",i);
		        		  break;
		              }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  bookData.setAccessNo(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
		        		  
		        		  message = new EBookDataMessage();
		        		  message = ss.getCheckEBookAccessNo(bookData);
		        		  
		        		  if(message.getCount() > 0)
		        		  {
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Access_No Already Exists in Table",i);
		            		  break;        			  
		        		  }

		        		  if(!set.add(bookData.getAccessNo())) {     // For Checking Duplicate Access_No in Excel
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Access_No Already Exists in Excel",i);
		            		  break; 
		        		  }
		        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setAccessNo(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
		        		  
		        		  message = new EBookDataMessage();
		        		  message = ss.getCheckEBookAccessNo(bookData);
		        		  
		        		  if(message.getCount() > 0)
		        		  {
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Access_No Already Exists in Table",i);
		            		  break;        			  
		        		  }
		        		  if(!set.add(bookData.getAccessNo())) {
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Access_No Already Exists in Excel",i);
		            		  break; 
		        		  }
		        	  }
		        	  else	  {  
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in AccessNo:"+i);
		        	  }
		        	  
		        	  
		              cell=row.getCell((short) 1);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  message = new EBookDataMessage();
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
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Title:"+i);
		        	  }
		        	  
	        	  
		        	  
		              cell=row.getCell((short) 2);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  /**message = new EBookDataMessage();
		        		  message = ValidateError("Author",i);
		        		  break; */
		        		  bookData.setAuthorName("NIL");
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		          		  
		        		  bookData.setAuthorName(String.valueOf(row.getCell((short) 2).getStringCellValue()));        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setAuthorName(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Author is Invalid",i);
		        		  break;
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Author:"+i);
		        	  }
		        	  

		        	  
		        	  cell=row.getCell((short) 3);        	    
	        		  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		 /** message = new BookDataMessage();
		        		  message = ValidateError("Call_No",i);
		        		  break;*/        		  
		        		  bookData.setCallNo("");
		              }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setCallNo(String.valueOf(row.getCell((short) 3).getStringCellValue()));        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setCallNo(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in CallNo:"+i);
		        	  }
		        	  
		        	  
		        	  
		              cell=row.getCell((short) 4);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setEdition("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setEdition(String.valueOf(row.getCell((short) 4).getStringCellValue()));          		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setEdition(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Edition:"+i);
		        	  }     
		        	         
		        	
		        	  
		        	  
		              cell=row.getCell((short) 5);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setYop("");
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setYop(String.valueOf(row.getCell((short) 5).getStringCellValue())); 
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Publisher Year is Invalid",i);
		        		  break;
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setYop(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in PubYear:"+i);
		        	  }
		        	  
		        	  
		        	  
		              cell=row.getCell((short) 6);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		 /** message = new BookDataMessage();
		        		  message = ValidateError("Subject",i);
		        		  break;*/
		        		  bookData.setSubName("NIL");
		        		  bookData.setSubCode(1);
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  bookData.setSubName(String.valueOf(row.getCell((short) 6).getStringCellValue()));
		        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
		        		  bookData.setSubjectCode(sub);*/    		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setSubName(String.valueOf((long)row.getCell((short) 6).getNumericCellValue()));
		        		  /**int sub = cs.getBookSubCode(bookData.getSubject());
		        		  bookData.setSubjectCode(sub);*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Subject is Invalid",i);
		        		  break;        		  
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Subject:"+i);
		        	  }        	  
		        	  
		        	  
		        	  cell=row.getCell((short) 7);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  /**message = new BookDataMessage();
		        		  message = ValidateError("Publisher",i);
		        		  break;*/
		        		  bookData.setPubName("NIL");
		        		  bookData.setPubCode(1);
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setPubName(String.valueOf(row.getCell((short) 7).getStringCellValue()));
		        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
		        		  bookData.setPublisherCode(pub);*/
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setPubName(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
		        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
		        		  bookData.setPublisherCode(pub);*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Publisher is Invalid",i);
		        		  break;
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Publisher:"+i);
		        	  }

                            cell=row.getCell((short) 8);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  /**message = new BookDataMessage();
		        		  message = ValidateError("Publisher",i);
		        		  break;*/
		        		  bookData.setSupName("NIL");
		        		  bookData.setSupCode(2);
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setSupName(String.valueOf(row.getCell((short) 8).getStringCellValue()));
		        		  System.out.println("::::::::Supplier:::::"+bookData.getSupName());
		        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
		        		  bookData.setPublisherCode(pub);*/
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setSupName(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
		        		  /**int pub = cs.getBookPubCode(bookData.getPublisher());
		        		  bookData.setPublisherCode(pub);*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Supplier is Invalid",i);
		        		  break;
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Supplier:"+i);
		        	  }
		        	  
		        	  
		              cell=row.getCell((short) 9);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  /**message = new BookDataMessage();
		        		  message = ValidateError("Department",i);
		        		  break;*/
		        		  bookData.setDeptName("NIL");
		        		  bookData.setDeptCode(1);
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setDeptName(String.valueOf(row.getCell((short) 9).getStringCellValue()));  
		        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
		        		  bookData.setDepartmentCode(dept);*/
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setDeptName(String.valueOf((long)row.getCell((short) 9).getNumericCellValue()));
		        		  /**int dept = cs.getBookDeptCode(bookData.getDepartment());
		        		  bookData.setDepartmentCode(dept);*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Department is Invalid",i);
		        		  break; 
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Department:"+i);
		        	  }     
		            	  
		        	  
		        	  
		        	  
		        	  /*cell=row.getCell((short) 10);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setBranchName("NIL");
		        		  bookData.setBranchCode(1);
//		        		  message = new EBookDataMessage();
//		        		  message = ValidateError("Branch Name is Invalid",i);
//		        		  break; 
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setBranchName(String.valueOf(row.getCell((short) 10).getStringCellValue()));  
		        		  message = ss.CheckBranchAvailable(bookData);
		        		  System.out.println("++++++++"+message.getCount());
		        		  if(message.getCount() > 0)
		        		  {
		        		  int bcode = cs.getEBookBranchCode(bookData.getBranchName());
		        		  System.out.println(":::::bcode:::::"+bcode);
		        		  bookData.setBranchCode(bcode);
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Branch:"+i);
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Branch is Invalid",i);
		        		  break; 
		        	  } 
		        	  }*/
		        	  

		          	  
		              cell=row.getCell((short) 10);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setDoc("EBOOK");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setDoc(String.valueOf(row.getCell((short) 10).getStringCellValue()));
		        		  
		        		  if(bookData.getDoc().equalsIgnoreCase("EBOOK"))
		        		  {        	
		        			  bookData.setDoc("EBOOK");
		        		  }
		        		  else  {
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Document  is invalid,EBOOK is the Document",i);
		            		  break;        			  
		        		  }        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setDoc(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Document is invalid,EBOOK is the Document",i);
		        		  break;        		  
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Document:"+i);
		        	  }             
		        	  
		        	  
		        	  cell=row.getCell((short) 11);       	  // For Checking Date
		              
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {        		  
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Received_Date",i);
		        		  break;        		  
		              }        	  
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  /**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 11).getStringCellValue()));*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
		        		  break;        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		 /** bookData.setReceiveDate(String.valueOf((long)row.getCell((short) 11).getNumericCellValue())); */
		        		  
		        		  try  {
		        		  if (HSSFDateUtil.isCellDateFormatted(cell))
		            	  {
		        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				    
		        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
		        			    String rdate = receivedDate.format(date);
		        			    bookData.setRcDate(rdate);  
		        			    
		            	  }else {
		            		  message = new EBookDataMessage();
		            		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		            	  } 
		        		  }catch(Exception e)		  {
		        			  log4jLogger.info("^^^ AutoLib ReceivedDate Error ^^^"+e);
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Received_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		        		  }
		        		  
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in ReceivedDate:"+i);
		        	  }

		        	  cell=row.getCell((short) 12);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setInvoiceno("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setInvoiceno(String.valueOf(row.getCell((short) 12).getStringCellValue()));         		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setInvoiceno(String.valueOf((long)row.getCell((short) 12).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in InvoiceNo:"+i);
		        	  }       	  
		        	  
                        cell=row.getCell((short) 13);       	  // For Checking Date
		              
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {        		  
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Invoice_Date",i);
		        		  break;        		  
		              }        	  
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  /**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 11).getStringCellValue()));*/
		        		  message = new EBookDataMessage();
		        		  message = ValidateError("Invoice_Date is Invalid.The Format is dd/mm/yyyy",i);
		        		  break;        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		 /** bookData.setReceiveDate(String.valueOf((long)row.getCell((short) 11).getNumericCellValue())); */
		        		  
		        		  try  {
		        		  if (HSSFDateUtil.isCellDateFormatted(cell))
		            	  {
		        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				    
		        			    SimpleDateFormat invoieDate = new SimpleDateFormat("yyyy-MM-dd");
		        			    String idate = invoieDate.format(date);
		        			    bookData.setIdate(idate);  
		        			    
		            	  }else {
		            		  message = new EBookDataMessage();
		            		  message = ValidateError("Invoice_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		            	  } 
		        		  }catch(Exception e)		  {
		        			  log4jLogger.info("^^^ AutoLib InvoiceDate Error ^^^"+e);
		        			  message = new EBookDataMessage();
		            		  message = ValidateError("Invoice_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		        		  }
		        		  
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in InvoiceDate:"+i);
		        	  }
		        	  
		        	  
                   cell=row.getCell((short) 14);       	  
		        	  
                   if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
                   {
             		  bookData.setPurchaseType("");
                   }              
             	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
             	  {
             		  bookData.setPurchaseType(String.valueOf(row.getCell((short) 14).getStringCellValue()));         		  
             	  }
             	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
             	  {
             		  bookData.setPurchaseType(String.valueOf((long)row.getCell((short) 14).getNumericCellValue()));
             	  }
             	  else	  {
             		  log4jLogger.info(">>>>>>>>>>>>>Error in Purcahse_Type:"+i);
             	  } 
                   
                   
                   cell=row.getCell((short) 15);       	  // For Checking Price
             	  
             	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
                   {
             		  bookData.setPrice(0.0); 
                   }              
             	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
             	  {
             		  bookData.setPrice(0.0); 
             		  message = new EBookDataMessage();
             		  message = ValidateError("Price is invalid",i);
             		  break;
             	  }
             	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
             	  {
             		  bookData.setPrice((double)row.getCell((short) 15).getNumericCellValue());
             	  }
             	  else	  {
             		  log4jLogger.info(">>>>>>>>>>>>>Error in Price:"+i);
             	  }  
             	  
             	 cell=row.getCell((short) 16);       	  
           	  
           	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
                 {
           		  bookData.setIsbn("");
                 }              
           	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
           	  {
           		  bookData.setIsbn(String.valueOf(row.getCell((short) 16).getStringCellValue()));         		  
           	  }
           	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
           	  {
           		  bookData.setIsbn(String.valueOf((long)row.getCell((short) 16).getNumericCellValue()));
           	  }
           	  else	  {
           		  log4jLogger.info(">>>>>>>>>>>>>Error in ISBN:"+i);
           	  } 

           	cell=row.getCell((short) 17);       	  
      	  
      	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
            {
      		  bookData.setPages("");
            }              
      	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
      	  {
      		  bookData.setPages(String.valueOf(row.getCell((short) 17).getStringCellValue()));        		  
      	  }
      	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
      	  {
      		  bookData.setPages(String.valueOf((long)row.getCell((short) 17).getNumericCellValue()));
      	  }
      	  else	  {
      		  log4jLogger.info(">>>>>>>>>>>>>Error in Pages:"+i);
      	  }  
           	  
           	  
		        	  cell=row.getCell((short) 18);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setKeywords("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setKeywords(String.valueOf(row.getCell((short) 18).getStringCellValue()));         		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setKeywords(String.valueOf((long)row.getCell((short) 18).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Keywords:"+i);
		        	  }  
		        	  
		        	  
		        	  
		        	  
		        	  cell=row.getCell((short) 19);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setContent("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setContent(String.valueOf(row.getCell((short) 19).getStringCellValue()));        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setContent(String.valueOf((long)row.getCell((short) 19).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Content:"+i);
		        	  }
		        	  
		        	  
		        	  
		              cell=row.getCell((short) 20);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setUrl("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setUrl(String.valueOf(row.getCell((short) 20).getStringCellValue()));         		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setUrl(String.valueOf((long)row.getCell((short) 20).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in URL:"+i);
		        	  } 
		        	  
		        	  

		        	  
		        	  bookData.setBranchCode(1);
		        	  
		        	  
		        	  
		        	  
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
			  message = new EBookDataMessage();
			  message = ValidateError("Invalid Row ",errorCode);
		} catch (Exception e) { 
			log4jLogger.info("AutoLibError:OtherException:"+e);
		}
		finally{
			set.clear();
		}
		      
		  if(errorException.equals("InvalidFile"))
		  {
			    indexPage ="/EBookImport/index.jsp?check=InvalidFile";
		  }else {
		        	
		    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
		    {    	
		    	request.setAttribute("bean",message);
		    	indexPage ="/EBookImport/index.jsp?check=dataerror";
		    	
		    }else
		    {
		    	if(saveDetail != null && !saveDetail.isEmpty())
		    	{
		    		int save = ss.getImportEBookData(saveDetail);
		    		indexPage ="/EBookImport/index.jsp?check=success";
		    	}else
		    	{
		    		indexPage ="/EBookImport/index.jsp?check=ErrorToSave";
		    	}
		    }
		  }   
		    
		    dispatch(request, response, indexPage);
		    
		    
		      
		   }catch(Exception ex) {
			   indexPage ="/EBookImport/index.jsp?check=LargeFile";
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
		   

		   
		   private void employeeService(EBookBean employee)
		   {
			   log4jLogger.info("=======Inside employeeService=========="+employee.getAccessNo());
		   	
		   	try{               	  
		   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
		   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
		   	       Statement stat=con.createStatement();       
		   	 
		  	       
		   	       int k=stat.executeUpdate("insert into excel values('"+employee.getAccessNo()+"','"+employee.getTitle()+"','"+employee.getAuthorName()+"')");
		   	       log4jLogger.info("Data is inserted");
		   	       stat.close();
		   	       con.close();
		   	   }
		   	   catch(Exception e){	  
		   		log4jLogger.info("AutoLibError:DBConnect:"+e);
		   	   }   	
		   }

		   
	   



	public EBookDataMessage ValidateError(String error, int i) {
		// TODO Auto-generated method stub
			log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
		   EBookDataMessage message = new EBookDataMessage();	   
		   
		   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
		   return message;
	}

}

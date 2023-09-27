package Lib.Auto.QB_Import;

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
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.EBookImport.EBookDataMessage;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBookImport.ImportEBook;
import Lib.Auto.Member_Import.MemberDataMessage;


public class ImportQB extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(ImportQB.class);

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
		       
		       
			   QBDataMessage message =new QBDataMessage();
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
		        	          	  
		        	  QBDataBean bookData = new QBDataBean();
		        	  row = sheet.getRow(i);       	  
		        	  
		        	  errorCode = i;
		        	  // Checking Cell Type and Value
		        	  
		        	  cell=row.getCell((short) 0);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {   
		        		  message = new QBDataMessage();
		        		  message = ValidateError("QB_COde",i);
		        		  break;
		              }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  bookData.setQbCode(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
		        		  
		        		  message = new QBDataMessage();
		        		  message = ss.getCheckQBCode(bookData);
		        		  
		        		  if(message.getCount() > 0)
		        		  {
		        			  message = new QBDataMessage();
		            		  message = ValidateError("QB_code Already Exists in Table",i);
		            		  break;        			  
		        		  }

		        		  if(!set.add(bookData.getQbCode())) {     // For Checking Duplicate Access_No in Excel
		        			  message = new QBDataMessage();
		            		  message = ValidateError("QB_code Already Exists in Excel",i);
		            		  break; 
		        		  }
		        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setQbCode(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
		        		  System.out.println(":::::::::::Qb code::::"+bookData.getQbCode());
		        		  message = new QBDataMessage();
		        		  message = ss.getCheckQBCode(bookData);
		        		  
		        		  if(message.getCount() > 0)
		        		  {
		        			  message = new QBDataMessage();
		            		  message = ValidateError("QB_code Already Exists in Table",i);
		            		  break;        			  
		        		  }
		        		  
		        		  if(!set.add(bookData.getQbCode())) {
		        			  message = new QBDataMessage();
		            		  message = ValidateError("QB_code Already Exists in Excel",i);
		            		  break; 
		        		  }
		        	  }
		        	  else	  {  
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in QB_code:"+i);
		        	  }
		        	  
		        	  
		        	  
		        	  cell=row.getCell((short) 1);       	  // For Checking Date
		              
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {        		  
		        		  message = new QBDataMessage();
		        		  message = ValidateError("Date",i);
		        		  break;        		  
		              }        	  
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {        		  
		        		  /**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 4).getStringCellValue()));*/
		        		  message = new QBDataMessage();
		        		  message = ValidateError("QB_Date is Invalid.The Format is dd/mm/yyyy",i);
		        		  break;        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		 /** bookData.setReceiveDate(String.valueOf((long)row.getCell((short) 4).getNumericCellValue())); */
		        		  
		        		  try  {
		        		  if (HSSFDateUtil.isCellDateFormatted(cell))
		            	  {
		        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				    
		        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
		        			    String Qdate = receivedDate.format(date);
		        			    bookData.setDate(Qdate); 
		        			    System.out.println("::::::Date::::::"+bookData.getDate());
		        			    
		            	  }else {
		            		  message = new QBDataMessage();
		            		  message = ValidateError("QB_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		            	  } 
		        		  }catch(Exception e)		  {
		        			  log4jLogger.info("^^^ AutoLib QB_Date Error ^^^"+e);
		        			  message = new QBDataMessage();
		            		  message = ValidateError("QB_Date is Invalid.The Format is dd/mm/yyyy",i);
		            		  break;
		        		  }
		        		  
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in QB_Date:"+i);
		        	  }

		        	  

		        	  
		              cell=row.getCell((short) 2);       	  
		        	  
		              if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setUniversity("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setUniversity(String.valueOf(row.getCell((short) 2).getStringCellValue()));          		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setUniversity(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in University:"+i);
		        	  } 
		        	  
	        	  
		              
                         cell=row.getCell((short) 3);       	  
		        	  
		              if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		            	  bookData.setSubName("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setSubName(String.valueOf(row.getCell((short) 3).getStringCellValue()));
		        		  System.out.println("::::::Subject Name::::::"+bookData.getSubName());
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setSubName(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Subject Name:"+i);
		        	  } 
		        	  
		              
                     cell=row.getCell((short) 4);       	  
		        	  
		              if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		            	  
		        		  bookData.setSubjectCode("");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setSubjectCode(String.valueOf(row.getCell((short) 4).getStringCellValue()));     
		        		  System.out.println("::::::Subject code::::::"+bookData.getSubjectCode());
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setSubjectCode(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Subject Code:"+i);
		        	  } 
		              
		              
                       cell=row.getCell((short) 5);       	  
		        	  
		              if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		            	  bookData.setDepartment("NIL");
		        		  bookData.setDepartmentCode(1);
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setDepartment(String.valueOf(row.getCell((short) 5).getStringCellValue()));     
		        		  System.out.println("::::::Department ::::::"+bookData.getDepartment());
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setDepartment(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Department:"+i);
		        	  } 
		              
		              
		              cell=row.getCell((short) 6);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setCourseCode(1);
		        		  bookData.setCourse("NIL-NIL");
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setCourse(String.valueOf(row.getCell((short) 6).getStringCellValue())); 
		        		  System.out.println("::::::Course::::::"+bookData.getCourse());
		        		  
		        		    String[] parts = bookData.getCourse().split("-");
		        		    if(parts.length < 2)
		        		    {
		        		      message = new QBDataMessage();
		              		  message = ValidateError("Course Name is Invalid. For Example:BE-CSE",i);
		              		  break;
		        		    }
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setCourse(String.valueOf((long)row.getCell((short) 6).getNumericCellValue()));

		        		  message = new QBDataMessage();
		        		  message = ValidateError("Course Name is Invalid",i);
		        		  break; 
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Course Name:"+i);
		        	  }   
		                   
		        	  
		        	  cell=row.getCell((short) 7);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setPubyear("");
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setPubyear(String.valueOf(row.getCell((short) 7).getStringCellValue())); 
		        		  
		        		  message = new QBDataMessage();
		        		  message = ValidateError("Year is Invalid",i);
		        		  break;
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setPubyear(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
		        		  System.out.println("::::::Year::::::"+bookData.getPubyear());
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Year:"+i);
		        	  }
		              
		        	  
		        	  
                       cell=row.getCell((short) 8);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setMonth("");
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setMonth(String.valueOf(row.getCell((short) 8).getStringCellValue())); 
		        		  System.out.println("::::::Month::::::"+bookData.getMonth());
//		        		  message = new QBDataMessage();
//		        		  System.out.println("=========="+bookData.getMonth());
//		        		  message = ValidateError("Month is Invalid",i);
//		        		  break;
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  System.out.println("++++++++");
		        		  bookData.setMonth(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));
		        		  message = new QBDataMessage();
		        		  message = ValidateError("Month is Invalid",i);
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Month:"+i);
		        	  }
		        	  
		        	  
		        	  
                       cell=row.getCell((short) 9);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setSemester("");
		              }
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setSemester(String.valueOf(row.getCell((short) 9).getStringCellValue())); 
		        		  System.out.println("::::::Semester::::::"+bookData.getSemester());
		        		  
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setSemester(String.valueOf((long)row.getCell((short) 9).getNumericCellValue()));
		        		  message = new QBDataMessage();
		        		  message = ValidateError("Semester is Invalid",i);
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Semester:"+i);
		        	  }
		        	  
		        	  
                     
		        	  
                        cell=row.getCell((short)  10);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setAddfield1("");
		        		  
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setAddfield1(String.valueOf(row.getCell((short) 10).getStringCellValue()));  
		        		  System.out.println("::::::Add Field1::::::"+bookData.getAddfield1());
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setAddfield1(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield1:"+i);
		        	  }
		        	  
		        	  
		        	  
		              cell=row.getCell((short) 11);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setAddfield2("");
		        		  System.out.println("::::::Add Field2::::::"+bookData.getAddfield2());
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setAddfield2(String.valueOf(row.getCell((short) 11).getStringCellValue()));  
		        		 
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setAddfield2(String.valueOf((long)row.getCell((short) 11).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Addfield2:"+i);
		        	  }  
		        	  
                  cell=row.getCell((short) 12);       	  
		        	  
		        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
		              {
		        		  bookData.setContent("");
		        		  System.out.println("::::::Content::::::"+bookData.getContent());
		              }              
		        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		        	  {
		        		  bookData.setContent(String.valueOf(row.getCell((short) 12).getStringCellValue()));  
		        		 
		        	  }
		        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		        	  {
		        		  bookData.setContent(String.valueOf((long)row.getCell((short) 12).getNumericCellValue()));
		        	  }
		        	  else	  {
		        		  log4jLogger.info(">>>>>>>>>>>>>Error in Content:"+i);
		        	  }  
		        	  
		        	  
		              System.out.println(":::::::::::::::::::::"+bookData.toString());
		        	  saveDetail.add(bookData);
		        	  System.out.println("<<<<<<<<<<<>>>>>>>>>>>>>>"+saveDetail);
		        	  
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
			  message = new QBDataMessage();
			  message = ValidateError("Invalid Row ",errorCode);
		} catch (Exception e) { 
			log4jLogger.info("AutoLibError:OtherException:"+e);
		}
		finally{
			set.clear();
		}
		      
		  if(errorException.equals("InvalidFile"))
		  {
			  System.out.println(":::::Invlid file::::::");
			  
			    indexPage ="/QB_Import/index.jsp?check=InvalidFile";
		  }else {
		        	
		    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
		    {    	
		    	System.out.println(":::::::::::Errror:::::::::");
		    	request.setAttribute("bean",message);
		    	indexPage ="/QB_Import/index.jsp?check=dataerror";
		    	
		    }else
		    {
		    	System.out.println("+=+=+=+=+=+=+="+saveDetail);
		    	
		    	if(saveDetail != null && !saveDetail.isEmpty())
		    	{
		    		
		    	System.out.println("===========");
		    		int save = ss.getImportQBData(saveDetail);
		    		indexPage ="/QB_Import/index.jsp?check=success";
		    	}else
		    	{
		    		System.out.println("++++++++++else++++++++++");
		    		indexPage ="/QB_Import/index.jsp?check=ErrorToSave";
		    	}
		    }
		  }   
		    
		    dispatch(request, response, indexPage);
		    
		    
		      
		   }catch(Exception ex) {
			   indexPage ="/QBImport/index.jsp?check=LargeFile";
			   log4jLogger.info("AutoLibError:FileException:"+ex);
			   ex.printStackTrace();
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
		   

		   
		   private void employeeService(QBDataBean employee)
		   {
			   log4jLogger.info("=======Inside employeeService=========="+employee.getQbCode());
		   	
		   	try{               	  
		   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
		   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
		   	       Statement stat=con.createStatement();       
		   	 
		  	       
		   	       int k=stat.executeUpdate("insert into excel values('"+employee.getQbCode()+"','"+employee.getDate()+"','"+employee.getSubName()+"')");
		   	       log4jLogger.info("Data is inserted");
		   	       stat.close();
		   	       con.close();
		   	   }
		   	   catch(Exception e){	  
		   		log4jLogger.info("AutoLibError:DBConnect:"+e);
		   	   }   	
		   }

		   
	   



	public QBDataMessage ValidateError(String error, int i) {
		// TODO Auto-generated method stub
			log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
		   QBDataMessage message = new QBDataMessage();	   
		   
		   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
		   return message;
	}

}

package Lib.Auto.GateRegister_Import;

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

import org.apache.commons.io.output.*;


public class ImportGateRegister extends HttpServlet implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(ImportGateRegister.class);

	private boolean isMultipart;
	private String filePath="",tempPath="",indexPage=null;
	private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
	private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
	private File file ;   
	int minUsedCalc =0;






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


			GateDataMessage message =new GateDataMessage();
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

						GateDataBean gateData = new GateDataBean();
						row = sheet.getRow(i);       	  

						errorCode = i;
						// Checking Cell Type and Value

						cell=row.getCell((short) 0);       	  

						if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
						{   
							message = new GateDataMessage();
							message = ValidateError("Member Code",i);
							break;
						}
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{        		  
							gateData.setMemberCode(String.valueOf(row.getCell((short) 0).getStringCellValue()));  

							message = new GateDataMessage();

						}
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
						{
							gateData.setMemberCode(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));

							message = new GateDataMessage();

						}
						else	  {  
							log4jLogger.info(">>>>>>>>>>>>>Error in Member Code:"+i);
						}






						cell=row.getCell((short) 1);       	  // For Checking Date

						if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
						{        		  
							message = new GateDataMessage();
							message = ValidateError("Entry_Date",i);
							break;        		  
						}        	  
						else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{        		  
							/**  bookData.setReceiveDate(String.valueOf(row.getCell((short) 4).getStringCellValue()));*/
							message = new GateDataMessage();
							message = ValidateError("Entry_Date is Invalid.The Format is dd/mm/yyyy",i);
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
									String rdate = receivedDate.format(date);
									gateData.setEntryDate(rdate);
									gateData.setOutDate(rdate);


								}else {
									message = new GateDataMessage();
									message = ValidateError("Entry_Date is Invalid.The Format is dd/mm/yyyy",i);
									break;
								} 
							}catch(Exception e)		  {
								log4jLogger.info("^^^ AutoLib Entry_Date Error ^^^"+e);
								message = new GateDataMessage();
								message = ValidateError("Entry_Date is Invalid.The Format is dd/mm/yyyy",i);
								break;
							}

						}
						else	  {
							log4jLogger.info(">>>>>>>>>>>>>Error in Entry_Date:"+i);
						}



						cell=row.getCell((short) 2);       	  

						if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
						{
							message = new GateDataMessage();
							message = ValidateError("InTime",i);
							break;
						}              
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{        		  
							gateData.setInTime(String.valueOf(row.getCell((short) 2).getStringCellValue()));  

							log4jLogger.info(">>>>>>>>>>>>> in getInTime:"+gateData.getInTime());

							message = new GateDataMessage();

						}
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
						{
							gateData.setInTime(String.valueOf((long)row.getCell((short) 2).getNumericCellValue()));

							log4jLogger.info(">>>>>>>>>>>>> in getInTime:"+gateData.getInTime());

							message = new GateDataMessage();

						}
						else	  {  
							log4jLogger.info(">>>>>>>>>>>>>Error InTime:"+i);
						}


						cell=row.getCell((short) 3);       	  

						if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
						{
							message = new GateDataMessage();
							message = ValidateError("outTime",i);
							break;
						}              
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{        		  
							gateData.setOutTime(String.valueOf(row.getCell((short) 3).getStringCellValue()));  

							log4jLogger.info("CELL_TYPE_STRING >>>>>>>>>>>>> in outTime:"+gateData.getOutTime());

							message = new GateDataMessage();

						}
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
						{
							gateData.setOutTime(String.valueOf((long)row.getCell((short) 3).getNumericCellValue()));

							log4jLogger.info("CELL_TYPE_NUMERIC >>>>>>>>>>>>> in OutTime:"+gateData.getOutTime());

							message = new GateDataMessage();

						}
						else	  {  
							log4jLogger.info(">>>>>>>>>>>>>Error OutTime:"+i);
						}




						/*  cell=row.getCell((short) 4);       	  // For Checking Date

        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  gateData.setMins(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));  	  		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  gateData.setMins(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));  		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  gateData.setMins(String.valueOf((long)row.getCell((short) 4).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Entry_Date:"+i);
        	  }*/




						 minUsedCalc  = minsUsedCalculate(gateData.getInTime(), gateData.getOutTime());


						System.out.println(minUsedCalc+"::::::::::minUsedCalc:::::::::::");
						gateData.setMins(minUsedCalc);

						saveDetail.add(gateData);

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
					message = new GateDataMessage();
					message = ValidateError("Invalid Row ",errorCode);
				} catch (Exception e) { 
					log4jLogger.info("AutoLibError:OtherException:"+e);
				}
				finally{
					set.clear();
				}

				if(errorException.equals("InvalidFile"))
				{
					indexPage ="/GateRegisterImport/index.jsp?check=InvalidFile";
				}else {

					if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
					{    	
						request.setAttribute("bean",message);
						indexPage ="/GateRegisterImport/index.jsp?check=dataerror";

					}else
					{
						if(saveDetail != null && !saveDetail.isEmpty())
						{
							int save = ss.getImportGateData(saveDetail);
							indexPage ="/GateRegisterImport/index.jsp?check=success";
						}else
						{
							indexPage ="/GateRegisterImport/index.jsp?check=ErrorToSave";
						}
					}
				}   

				dispatch(request, response, indexPage);



			}catch(Exception ex) {
				indexPage ="/GateRegisterImport/index.jsp?check=LargeFile";
				log4jLogger.info("AutoLibError:FileException:"+ex);
				dispatch(request, response, indexPage);
			}
			
			System.out.println("=========================="+indexPage);

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





	private void employeeService(GateDataBean employee)
	{
		log4jLogger.info("=======Inside employeeService=========="+employee.getMemberCode());

		try{               	  
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
			Statement stat=con.createStatement();       


			int k=stat.executeUpdate("insert into excel values('"+employee.getMemberCode()+"','"+employee.getMemberCode()+"','"+employee.getMemberCode()+"')");
			log4jLogger.info("Data is inserted");
			stat.close();
			con.close();
		}
		catch(Exception e){	  
			log4jLogger.info("AutoLibError:DBConnect:"+e);
		}   	
	}


	public GateDataMessage ValidateError(String error, int i)
	{	   
		log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
		GateDataMessage message = new GateDataMessage();	   

		message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
		return message;

	}

	public int minsUsedCalculate(String inTime,String outTime)
	{

		int total=0;

		try{ 

			String time3 = inTime.concat(":00");
			String time4 = outTime.concat(":00");

			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date date1 = format.parse(time3);
			Date date2 = format.parse(time4);

			log4jLogger.info("date1"+date1);
			log4jLogger.info("date2"+date2);

			long diffInMs = date2.getTime() - date1.getTime();
			log4jLogger.info("diffInMs::::::"+diffInMs);
			long diffMinutes = diffInMs / (60 * 1000) % 60;
			log4jLogger.info("diffMinutes::::::"+diffMinutes);
			long diffHours = diffInMs / (60 * 60 * 1000) % 24;
			log4jLogger.info("diffHours::::::"+diffHours);

			total = (int) (diffHours*60+diffMinutes);
			System.out.println("final output is "+total);

		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		return total;
	}




}
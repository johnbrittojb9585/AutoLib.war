package Lib.Auto.DBReport;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.PubSup.PubSupBean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/DBReport/DBReportServlet")

public class DBReport extends HttpServlet implements Serializable {
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(DBReport.class);
	private static final long serialVersionUID = 8672487184590862910L;

	String sql1 = "", sql2 = "", from_v = "", to_v = "", strsql = "",sql3="",
			SQLDOCTYPE, doc, fromdate = "", todate = "";

	singlecodecheck chkcode = new singlecodecheck();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			log4jLogger
					.info("=================: Inside DataBase Report :================== ");

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			String res2 = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();
			
			ReportService rs = BusinessServiceFactory.INSTANCE.getReportService();
					
			String commaSeparated = request.getParameter("flag1");
			String txtsubject = request.getParameter("txtsubject");
			String txtdepartment = request.getParameter("txtdepartment");
			String txtpublisher = request.getParameter("txtpublisher");
			String txtsupplier = request.getParameter("txtsupplier");
			String R1 = request.getParameter("R1");
			String doctype = request.getParameter("doctype");
			
			Object obj = commaSeparated ;
			if(obj==null)
			{
				commaSeparated = "";
				txtsubject = "";
				txtdepartment = "";
				txtpublisher = "";
				txtsupplier = "";
				R1 = "";
				doctype = "";
			}
			
			String[] items = commaSeparated.split(",");
						
			int titleCount = 0;
			Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
			excelTitleMap.put("rptFlag", items);
			excelTitleMap.put("rptTitle",ReportQueryUtill.Database_Reports_RPTTitle);
					

			//if (!request.getParameter("txtdepartment").equals("")) {
			if(!txtdepartment.equals("")){
				sql1 = sql1 + " and dept_name = '"+ chkcode.getParam(request, "txtdepartment") + "'";

						
				excelTitleMap.put("selectDepartment",chkcode.getParam(request, "txtdepartment"));
						
				titleCount++;
			}

			//if (!request.getParameter("txtsubject").equals("")) {
			if(!txtsubject.equals("")){
				sql1 = sql1 + " and sub_name ='"+ chkcode.getParam(request, "txtsubject") + "'";
				excelTitleMap.put("selectSubject",chkcode.getParam(request, "txtsubject"));						
				titleCount++;
			}

			//if (!request.getParameter("txtpublisher").equals("")) {
			if(!txtpublisher.equals("")){
				sql1 = sql1 + " and publisher ='"	+ chkcode.getParam(request, "txtpublisher") + "'";					
				excelTitleMap.put("selectPublisher",chkcode.getParam(request, "txtpublisher"));						
				titleCount++;
			}

			//if (!request.getParameter("txtsupplier").equals("")) {
			if(!txtsupplier.equals("")){
				sql1 = sql1 + " and supplier='"+ chkcode.getParam(request, "txtsupplier") + "'";
         		excelTitleMap.put("selectSupplier",chkcode.getParam(request, "txtsupplier"));	
				titleCount++;
			}
			if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
				sql1 = sql1+" and year_pub='"+request.getParameter("year_pub")+"'";
			
            if(!R1.equals("")) {
			//if (request.getParameter("R1").equals("V1")) {
			if(R1.equals("V1")) {

				String from_date = request.getParameter("recfrom");
				String to_date = request.getParameter("recto");

				excelTitleMap.put("fromSelectDate", from_date);
				titleCount++;
				excelTitleMap.put("toSelectDate", to_date);
				titleCount++;

				SimpleDateFormat F_DATE = new SimpleDateFormat("dd-MM-yyyy");
				Date f_date = new Date(F_DATE.parse(from_date).getTime());

				SimpleDateFormat T_DATE = new SimpleDateFormat("dd-MM-yyyy");
				Date t_date = new Date(T_DATE.parse(to_date).getTime());

				if (f_date.after(t_date)) {
					String temp;
					temp = from_date;
					from_date = to_date;
					to_date = temp;

				}
				sql2 = "where  Received_Date Between '"+ Security.TextDate_member(from_date) + "' and '" + Security.TextDate_member(to_date) + "'";	
				from_v = Security.TextDate_Update(from_date);
				to_v = Security.TextDate_Update(to_date);
			}
            }	
            sql3=" order by convert(access_no,signed) asc";
			
			//if (request.getParameter("R1").equals("V2")) {
              if(!R1.equals("")) {
            	  if(R1.equals("V2")) {
				from_v = request.getParameter("txtfromacc");
				to_v = request.getParameter("txttoacc");
				doc = request.getParameter("doctype");

				excelTitleMap.put("fromSelectAccNo", from_v);
				titleCount++;
				excelTitleMap.put("toSelectAccNo", to_v);
				titleCount++;

				fromdate = from_v;
				todate = to_v;
				String NoNumber = request.getParameter("flagNotNumber");

				if (!NoNumber.equalsIgnoreCase("NotNumber")) {
					int a = Integer.parseInt(from_v);
					int b = Integer.parseInt(to_v);
					if (a > b) {
						String temp;
						temp = from_v;
						from_v = to_v;
						to_v = temp;
					}
					fromdate = from_v;
					todate = to_v;
					if (doc.equalsIgnoreCase("ALL")) 
					{
						sql2 = " where access_no between " + from_v + " and "+ to_v + "";		
					}
					else 
					{
						sql2 = " where access_no between " + from_v + " and "+ to_v + "";			
					}

				} else {

					String fromtempNo = "";
					String fromtempString = "";
					String totempNo = "";
					String totempString = "";
					int count = 0;
					for (int i = 0; i < from_v.length(); i++) {
						if (Character.isDigit(from_v.charAt(i))) {
							fromtempNo = fromtempNo + from_v.charAt(i);

						} else {
							count = count + 1;
							fromtempString = fromtempString + from_v.charAt(i);
						}
					}

					for (int i = 0; i < to_v.length(); i++) {
						if (Character.isDigit(to_v.charAt(i))) {
							totempNo = totempNo + to_v.charAt(i);

						} else {
							totempString = totempString + to_v.charAt(i);
						}
					}

					if (fromtempString.equalsIgnoreCase(totempString)) {
						sql2 = "where access_no like '"
								+ fromtempString
								+ "%' and convert(right(access_no,length(access_no)-"
								+ count + ") using utf8) between " + fromtempNo
								+ " and " + totempNo + "";

					} else {
						getServletConfig()
								.getServletContext()
								.getRequestDispatcher(
										"/DBReport/index.jsp?check=NotValidRange")
								.forward(request, response);
					}

				}

			}
        }
			StringBuffer sb = new StringBuffer();

			for (int i = 1; i < items.length; i++) {

				if (items[i].equalsIgnoreCase("access_no")) {
					sb.append(",access_no");
				} else if (items[i].equalsIgnoreCase("author_name")) {
					sb.append(",author_name");
				} else if (items[i].equalsIgnoreCase("title")) {
					sb.append(",title");
				} else if (items[i].equalsIgnoreCase("call_no")) {
					sb.append(",call_no");
				}

				else if (items[i].equalsIgnoreCase("dept_name")) {
					sb.append(",dept_name");
				} else if (items[i].equalsIgnoreCase("sub_name")) {
					sb.append(",sub_name");
				} else if (items[i].equalsIgnoreCase("publisher")) {
					sb.append(",publisher");
				} else if (items[i].equalsIgnoreCase("availability")) {
					sb.append(",availability");
				}

				else if (items[i].equalsIgnoreCase("isbn")) {
					sb.append(",isbn");
				} else if (items[i].equalsIgnoreCase("year_pub")) {
					sb.append(",year_pub");
				} else if (items[i].equalsIgnoreCase("bprice")) {
					sb.append(",bprice");
				} else if (items[i].equalsIgnoreCase("received_date")) {
					sb.append(",received_date");
				}

				else if (items[i].equalsIgnoreCase("edition")) {
					sb.append(",edition");
				} else if (items[i].equalsIgnoreCase("location")) {
					sb.append(",location");
				} else if (items[i].equalsIgnoreCase("invoice_no")) {
					sb.append(",invoice_no");
				} else if (items[i].equalsIgnoreCase("language")) {
					sb.append(",language");
					
				} 
				else if (items[i].equalsIgnoreCase("supplier")) {
					sb.append(",supplier");
					
				}
				else if (items[i].equalsIgnoreCase("add_field3")) {
					sb.append(",add_field3");
					
				}
				else if (items[i].equalsIgnoreCase("volno")) {
					sb.append(",volno");
					
				}
				else if (items[i].equalsIgnoreCase("script")) {
					sb.append(",script");
					
				}
				else if (items[i].equalsIgnoreCase("issue_no")) {
					sb.append(",issue_no");
					
				}
				
				else if (items[i].equalsIgnoreCase("invoice_date")) {
					sb.append(",invoice_date");
					
				}
				else if (items[i].equalsIgnoreCase("discount")) {
					sb.append(",discount");
					
				}
				else if (items[i].equalsIgnoreCase("accepted_price")) {
					sb.append(",accepted_price");
					
				}
				else if (items[i].equalsIgnoreCase("gift_purchase")) {
					sb.append(",gift_purchase");
					
				}
				else if (items[i].equalsIgnoreCase("keywords")) {
					sb.append(",keywords");
					
				}
				else if (items[i].equalsIgnoreCase("")) {
					sb.append("");
				}

			}

			//if (!request.getParameter("doctype").equals("ALL")) {
			if(!doctype.equals("")) {
			  if(!doctype.equals("ALL")) {
				SQLDOCTYPE = "Select 0" + sb.toString()
						+ " from accession_reg  " + sql2 + sql1
						+ " and document='" + request.getParameter("doctype")
						+ "'" + sql3;

				excelTitleMap.put("selectDocumentType",
						request.getParameter("doctype"));
				titleCount++;

			} else {
				SQLDOCTYPE = "Select  0" + sb.toString()
						+ " from accession_reg  " + sql2 + sql1 + sql3;
			}		

			List prepareSearchCriteriaLst = importExportXMLService
					.getCustomReportList(SQLDOCTYPE, items);
			
			Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
			excelTitleMap.put("totCount", titleCount);

			DBReportExportRecord recordProcessor = new DBReportExportRecord(
					excelTitleMap);
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ recordProcessor.getExcelFileName());

			csvImportExportService.Export(studentDataItr, recordProcessor,
					response.getOutputStream());

		} 
			
							
	          response.setContentType("application/json");
				
				try{
					String term = request.getParameter("txtdepartment");
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		            {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<DepartmentBean> list = rs.getDBReportDeptAutoSearch(term);
				       for(DepartmentBean user: list){
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		            }	
			}catch(Exception e){
				//e.printStackTrace();
			}  		 


			try{
					String term = request.getParameter("txtsubject");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<subjectbean> list = rs.getDBReportSubjectAutoSearch(term);
				       for(subjectbean user: list){
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}    		 

			try{
				String term = request.getParameter("txtpublisher");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<PubSupBean> list = rs.getDBReportPubAutoSearch(term);
			       for(PubSupBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

			try{
				String term = request.getParameter("txtsupplier");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<PubSupBean> list = rs.getDBReportSupAutoSearch(term);
			       for(PubSupBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 
	
			
		}	catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql1 = "";
			sql2 = "";
			strsql = "";
		}
	}

}

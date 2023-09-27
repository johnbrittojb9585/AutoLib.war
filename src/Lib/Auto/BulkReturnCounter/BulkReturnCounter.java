package Lib.Auto.BulkReturnCounter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Bulk_Counter.CounterList;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterFineBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;

/**
 * @author Counter
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BulkReturnCounter extends HttpServlet implements Serializable,
		COUNTER_QUERY {

	private static Logger log4jLogger = Logger.getLogger(BulkReturnCounter.class);
	private static final long serialVersionUID = 1L;

	CounterList listBulk = new CounterList();
	String availability = "", document = "";
	boolean iss = true;

	String indexPage = null;
	String protocol = "", min = "", flag = "", temp1 = "", pri = "",
			no_days = "", temp2 = "", valid = "";
	String flag1 = "", flag2 = "";

	public BulkReturnCounter() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

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

			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();

			int Groups = 0;
			CounterBean counterbeanobject = new CounterBean();
			CounterMemberBean beanobject = new CounterMemberBean();
			CounterFineBean beanobject1 = new CounterFineBean();
			ReserveBean resbean = new ReserveBean();
			
		//	int branchID = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));

			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");
			flag2 = request.getParameter("flag2");
			String TID=request.getParameter("antennaid");
			request.setAttribute("AID",TID);
			if (!flag.equalsIgnoreCase("clear")) {
				if (request.getParameter("barcode").equals("ISSUE")) {
					flag = "issue";
				} else if (request.getParameter("barcode").equals("RETURN")) {
					flag = "return";
				} else if (request.getParameter("barcode").equals("RENEW")) {
					flag = "renew";
				}
			}

			if (flag.equals("") || flag.isEmpty()) {
				log4jLogger.info("===========Empty Flag=====" + flag);

				counterbeanobject.setPhoto1(null);
				indexPage = "//index.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("clear")) {
				log4jLogger.info("clear===========flag=====" + flag);

				counterbeanobject.setPhoto1(null);
				indexPage = "/BulkReturnCounter/index.jsp";
				dispatch(request, response, indexPage);
			}

			// --------------------------------------member-------------------------------------------------------------*/
			if (flag.equals("member")) {
				log4jLogger.info("member===========flag====="
						+ request.getParameter("mcode"));
System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				counterbeanobject = new CounterBean();
				counterbeanobject = ss.getCounterMember(request
						.getParameter("mcode").toString().trim());

				if (!counterbeanobject.getGroup().trim().isEmpty()
						&& counterbeanobject.getGroup().trim() != null) { // For
																			// Titan


					Groups = ss.getCounterGroup(counterbeanobject
							.getGroup().trim());

					String SLock = "";
					SLock = counterbeanobject.getSLock().trim();

					if (SLock.trim().equals("YES")) {
						indexPage = "/BulkReturnCounter/index.jsp?Message=MemberLock";
						request.setAttribute("SLock", counterbeanobject
								.getMcode().trim());

					} else {
System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
						ArrayList issue_details = (ArrayList) ss
								.getIssueDetailsMember(request
										.getParameter("mcode").toString()
										.trim());
						counterbeanobject.setCounterList(issue_details);

						/**
						 * ArrayList reserve_details1= (ArrayList)
						 * ss.getReserveDetailsMember
						 * (request.getParameter("mcode").toString().trim());
						 * int reserve_details=reserve_details1.size();
						 * counterbeanobject
						 * .setCunterList_RESERVE(reserve_details1);
						 */

						request.setAttribute("bean", counterbeanobject);

						/**
						 * if((Groups>0)&& (reserve_details>0)){
						 * 
						 * indexPage=
						 * "/BulkReturnCounter/index.jsp?flag=member&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS"
						 * ;
						 * 
						 * } else
						 */
						if (Groups > 0) {
							indexPage = "/BulkReturnCounter/index.jsp?flag=member&detils=ISSUEDEATILS";
						} else {
							indexPage = "/BulkReturnCounter/index.jsp?Message=MemberNotFound";
						}
					}

				
//					if (counterbeanobject.getBranchID() == branchID || branchID == 0 || branchID == 2 || counterbeanobject.getDeptBranch() == branchID) {} else {
//						counterbeanobject.setPhoto1(null);
//						indexPage = "/BulkReturnCounter/index.jsp?Message=OtherBranchMember";
//					}
				} else {
					indexPage = "/BulkReturnCounter/index.jsp?Message=MemberNotFound";
				}

				dispatch(request, response, indexPage);
			}

			// --------------------------------------BOOK-------------------------------------------------------------*/
			if (flag.equals("book")) {
				log4jLogger.info("book===========flag====="
						+ request.getParameter("accno"));

				beanobject = new CounterMemberBean();
				counterbeanobject = new CounterBean();

				beanobject = ss.getCounterBook(request.getParameter("accno")
						.toString().trim(), request.getParameter("doc")
						.toString().trim(),request.getParameter("mcode"));
				String acc_no = beanobject.getAccno();

				String Mem_code = request.getParameter("mcode").toString()
						.trim();
				String doc_type = beanobject.getDoc().toString().trim();

				if (acc_no != "" && acc_no != null) {
					System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
					if (doc_type.equalsIgnoreCase("JOURNAL") || doc_type.equalsIgnoreCase("BOOK")) {

						if (beanobject.getAvail().equals("ISSUED")) {

								counterbeanobject = ss
										.getCounterIssueCheck(request
												.getParameter("accno")
												.toString().trim());
								String issue_check = counterbeanobject
										.getIssue_Check();
								String memberCode = counterbeanobject
										.getMcode();

								request.setAttribute("bean", counterbeanobject);

								if (issue_check == "ITRUE") {
									counterbeanobject = new CounterBean();
									counterbeanobject = ss
											.getCounterMember(memberCode
													.toString().trim());

									if (counterbeanobject.getSLock().trim()
											.equals("YES")) {
										indexPage = "/BulkReturnCounter/index.jsp?Message=MemberLock";
										beanobject.setAccno("");
										request.setAttribute("SLock",
												counterbeanobject.getMcode()
														.trim());
									} else {
										indexPage = "/BulkReturnCounter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook";
									}
								} else {
									indexPage = "/BulkReturnCounter/index.jsp?flag=book";
								}
							
						}

						else {
							if ((request.getParameter("mcode").equals(""))
									&& (counterbeanobject.getAvail()
											.equals("YES"))) {
								// indexPage
								// ="/BulkReturnCounter/index.jsp?load=loadbook";
								indexPage = "/BulkReturnCounter/index.jsp?flag=NotIssued";
								beanobject.setAccno("");
							} else if (request.getParameter("mcode").equals("")) {
								// indexPage =
								// "/BulkReturnCounter/index.jsp?flag=book";
								indexPage = "/BulkReturnCounter/index.jsp?flag=NotIssued";
								beanobject.setAccno("");
							}

							else { // Checked rk.

								counterbeanobject = ss.getMemberLoad(request
										.getParameter("mcode").toString()
										.trim(), doc_type, "");

								ArrayList issue_details = (ArrayList) ss
										.getIssueDetailsMember(request
												.getParameter("mcode")
												.toString().trim());
								counterbeanobject.setCounterList(issue_details);

								/**
								 * ArrayList reerve_details1= (ArrayList)
								 * ss.getReserveDetailsMember
								 * (request.getParameter
								 * ("mcode").toString().trim());
								 * counterbeanobject
								 * .setCunterList_RESERVE(reerve_details1); int
								 * reserve_details=reerve_details1.size();
								 */

								request.setAttribute("bean", counterbeanobject);
								indexPage = "/BulkReturnCounter/index.jsp?flag=NotIssued&load=loadbook&detils=ISSUEDEATILS";

								/**
								 * if (reserve_details>0){ indexPage =
								 * "/BulkReturnCounter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS"
								 * ; } else{ indexPage =
								 * "/BulkReturnCounter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS"
								 * ; }
								 */
							}
						}
					} 
					
//					else {
//						log4jLogger
//								.info("Inside ===========Wrong Branch Book ================ ");
//
//						if (Mem_code != null && !Mem_code.isEmpty()) // For
//																		// Titan
//						{
//							counterbeanobject = new CounterBean();
//							counterbeanobject = ss.getMemberLoad(request
//									.getParameter("mcode").toString().trim(),
//									doc_type, "");
//
//							ArrayList issue_details = (ArrayList) ss
//									.getIssueDetailsMember(request
//											.getParameter("mcode").toString()
//											.trim());
//							counterbeanobject.setCounterList(issue_details);
//
//							/**
//							 * ArrayList reerve_details1= (ArrayList)
//							 * ss.getReserveDetailsMember
//							 * (request.getParameter("mcode").toString().trim());
//							 * counterbeanobject
//							 * .setCunterList_RESERVE(reerve_details1); int
//							 * reserve_details=reerve_details1.size();
//							 */
//
//							request.setAttribute("bean", counterbeanobject);
//							beanobject.setAccno("");
//							indexPage = "/BulkReturnCounter/index.jsp?flag=member&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS&Message=OtherBranchBook";
//
//						} else {
//							beanobject.setAccno("");
//							indexPage = "/BulkReturnCounter/index.jsp?Message=OtherBranchBook1";
//						}
//					}
				} 
				
				else {
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					if (Mem_code != null && !Mem_code.isEmpty()) {
						counterbeanobject = ss.getMemberLoad(request
								.getParameter("mcode").toString().trim(),
								doc_type, "");
						int issue_details1 = counterbeanobject.getCountperiod();

						if (counterbeanobject.getMcode() != ""
								&& counterbeanobject.getMcode() != null) {

							ArrayList Issue_Dtls = (ArrayList) ss
									.getIssueDetailsMember(request
											.getParameter("mcode").toString()
											.trim());
							counterbeanobject.setCounterList(Issue_Dtls);
						}
						request.setAttribute("bean", counterbeanobject);

//						if (issue_details1 > 0) {
//							
//							if (counterbeanobject.getBranchID() == branchID
//									|| branchID == 0 || branchID == 2 || counterbeanobject.getDeptBranch() == branchID) {
//								indexPage = "/BulkReturnCounter/index.jsp?flag=member&Message=BookNotbook1&detils=ISSUEDEATILS";
//							} else {
//								counterbeanobject.setPhoto1(null);
//								indexPage = "/BulkReturnCounter/index.jsp?Message=OtherBranchMember";
//							}
//						} else {
//							indexPage = "/BulkReturnCounter/index.jsp?Message=BookNotbook1";
//						}

					} else {
						indexPage = "/BulkReturnCounter/index.jsp?Message=BookNotbook";
					}

				}

				request.setAttribute("beanmember", beanobject);

				String[] baccno = null, btitle = null, bauthor = null, bpublisher = null, bdocument = null, bavailability = null, bissdate = null, bduedate = null, blfine = null;

				baccno = request.getParameterValues("baccno[]");
				btitle = request.getParameterValues("btitle[]");
				bauthor = request.getParameterValues("bauthor[]");
				bpublisher = request.getParameterValues("bpublisher[]");
				bavailability = request.getParameterValues("bavailability[]");
				bdocument = request.getParameterValues("bdocument[]");

				bissdate = request.getParameterValues("bissdate[]");
				bduedate = request.getParameterValues("bduedate[]");
				blfine = request.getParameterValues("blfine[]");
System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"+bdocument+bissdate+bduedate+blfine);
				int len = 0;
				if (baccno != null)
					len = baccno.length;

				List<Object> saveDetail = new ArrayList<Object>();

				for (int i = 0; i < len; i++) {

					listBulk = new CounterList();

					listBulk.setAccessNo(baccno[i]);
					listBulk.setTitle(btitle[i]);
					listBulk.setAuthorName(bauthor[i]);
					listBulk.setDocument(bdocument[i]);
					listBulk.setPublisher(bpublisher[i]);
					listBulk.setAvailability(bavailability[i]);

					listBulk.setIssueDate(bissdate[i]);
					listBulk.setDueDate(bduedate[i]);
					listBulk.setTemp(Double.parseDouble(blfine[i]));

					saveDetail.add(listBulk);
				}

				if (beanobject.getAccno() != null
						&& !beanobject.getAccno().isEmpty()) {
					listBulk = new CounterList();

					if (len > 0) {
						int count = 0;
						for (int i = 0; i < len; i++) {

							if (baccno[i].equalsIgnoreCase(beanobject
									.getAccno())
									&& bdocument[i].equalsIgnoreCase(beanobject
											.getDoc())) {
								count++;
								break;
							}
						}

						if (count == 0 && beanobject.getAvail().equalsIgnoreCase("ISSUED")) {
						System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
							listBulk.setAccessNo(beanobject.getAccno());
							listBulk.setTitle(beanobject.getTitle());
							listBulk.setAuthorName(counterbeanobject.getMcode());
							listBulk.setDocument(beanobject.getDoc());
							listBulk.setPublisher(counterbeanobject.getMname());
							listBulk.setAvailability(beanobject.getAvail());
							listBulk.setTemp(counterbeanobject.getTemp());

							if (counterbeanobject.getCallissdate() != null
									&& !counterbeanobject.getCallissdate()
											.isEmpty()) // For Issue Date
							{
								listBulk.setIssueDate(counterbeanobject
										.getCallissdate()); // For Existing
															// Issue Date
								listBulk.setDueDate(counterbeanobject
										.getCallduedate()); // For Existing Due
															// Date
							} else {
								listBulk.setIssueDate(Security_Counter
										.TodayDate_view()); // For Issue Date as
															// Today Date
								listBulk.setDueDate(counterbeanobject
										.getCalldate()); // For New Due date
							}

							saveDetail.add(listBulk);
						}
					} else if(beanobject.getAvail().equalsIgnoreCase("ISSUED")){
System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
						listBulk.setAccessNo(beanobject.getAccno());
						listBulk.setTitle(beanobject.getTitle());
						listBulk.setAuthorName(counterbeanobject.getMcode());
						listBulk.setDocument(beanobject.getDoc());
						listBulk.setPublisher(counterbeanobject.getMname());
						listBulk.setAvailability(beanobject.getAvail());
						listBulk.setTemp(counterbeanobject.getTemp());
						System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"+listBulk.getPublisher()+listBulk.getAuthorName());
						if (counterbeanobject.getCallissdate() != null
								
								&& !counterbeanobject.getCallissdate()
										.isEmpty()) // For Issue Date
							
						{
							System.out.println("mmmmmmmmmmmmmmmm");
							listBulk.setIssueDate(counterbeanobject
									.getCallissdate()); // For Existing Issue
														// Date
							listBulk.setDueDate(counterbeanobject
									.getCallduedate()); // For Existing Due Date
						} else {
							System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
							listBulk.setIssueDate(Security_Counter
									.TodayDate_view()); // For Issue Date as
														// Today Date
							listBulk.setDueDate(counterbeanobject.getCalldate()); // For
																					// New
																					// Due
																					// date
						}

						saveDetail.add(listBulk);
					}

					// log4jLogger.info("+++++++++++++++AccNo:"+beanobject.getAccno()+" IssueDate:"+counterbeanobject.getCallissdate()+" Issue DueDate:"+counterbeanobject.getCallduedate()+" NEW DueDate:"+counterbeanobject.getCalldate());

				}

				request.setAttribute("BulkCounterList", saveDetail);
				// if(saveDetail!=null)
				// session.setAttribute("BulkCounterSize", saveDetail.size());

				dispatch(request, response, indexPage);
			}

			// BOOK
			// ISSUE-----------------------------------------------------------------------------------//

			

			// --------------------------------------RETURN------------------------------------------------------------*/

			if (flag.equals("return")) {
				log4jLogger.info("book return===========flag=====" + flag);

				String[] baccno = null, blfine=null, btitle = null, bdocument = null, bissdate = null, bduedate = null ,bauthor=null, bpublisher=null;
				String breturndate = null;

				baccno = request.getParameterValues("baccno[]");
				btitle = request.getParameterValues("btitle[]");
				bdocument = request.getParameterValues("bdocument[]");
				bauthor= request.getParameterValues("bauthor[]");
				bpublisher = request.getParameterValues("bpublisher[]");
				bissdate = request.getParameterValues("bissdate[]");
				bduedate = request.getParameterValues("bduedate[]");
				breturndate = Security_Counter.TodayDate_view();
				blfine = request.getParameterValues("blfine[]");
				String commaSeparated = request.getParameter("flagAccno");
				String[] sAccno = commaSeparated.split(",");

				String commaSeparated1 = request.getParameter("flagDoc");
				String[] sDocument = commaSeparated1.split(",");

				int len = baccno.length;
				
				// int sumEligiple = 0,sumIssued = 0;

				for (int j = 1; j < sAccno.length; j++) {

					for (int i = 0; i < len; i++) {

						if (baccno[i].equals(sAccno[j])) {

							counterbeanobject = new CounterBean();

							beanobject = new CounterMemberBean();
							beanobject.setMcode(bauthor[i]);
							beanobject.setAccno(baccno[i]);
							beanobject.setIdate(Security_Counter
									.TextDate_Insert(bissdate[i]));
							beanobject.setDdate(Security_Counter
									.TextDate_Insert(bduedate[i]));
							beanobject.setRdate(Security_Counter
									.TextDate_Insert(breturndate));
							beanobject.setTitle(btitle[i]);
							beanobject.setAuthor(String.valueOf(session
									.getAttribute("UserID")));
							beanobject.setDoc(bdocument[i]);
							beanobject.setMname(bpublisher[i]);
							beanobject.setTemp(Double.parseDouble(blfine[i]));
							counterbeanobject.setTemp(Double.parseDouble(blfine[i]));
							counterbeanobject = ss.getDocmentReturn(beanobject);

							int doc_return = counterbeanobject.getDoc_Return();

							if (doc_return > 0) {

								Double Temp = Double.parseDouble(blfine[i]);
								
								
								if (Temp.doubleValue() == 0.0) {

									indexPage = "/BulkReturnCounter/index.jsp?Message=return&count="+len+"&detils=ISSUEDEATILS";
								} else {

									session.setAttribute("FINE", Temp);

									beanobject = new CounterMemberBean();
									beanobject.setMcode(bauthor[i]);
									beanobject.setAccno(baccno[i]);
									beanobject.setIdate(Security_Counter
											.TextDate_Insert(bissdate[i]));
									beanobject.setDdate(Security_Counter
											.TextDate_Insert(bduedate[i]));
									beanobject.setRdate(Security_Counter
											.TextDate_Insert(breturndate));
									beanobject.setTitle(btitle[i]);
									beanobject.setAuthor(String.valueOf(session
											.getAttribute("UserID")));
									beanobject.setDoc(bdocument[i]);
									beanobject.setMname(bpublisher[i]);
									beanobject.setTemp(Double.parseDouble(blfine[i]));
					//				beanobject.setBranchID(branchID);
									// For Add Payment to the user
									if (request.getParameter("payflag")
											.equalsIgnoreCase("YES")) {
										int fineDetails = ss
												.getFineDetail(beanobject);
									}

									beanobject1 = new CounterFineBean();

									beanobject1.setMcode(bauthor[i]);
									beanobject1.setAccno(baccno[i]);
									beanobject1.setIdate(Security_Counter
											.TextDate_Insert(bissdate[i]));
									beanobject1.setDdate(Security_Counter
											.TextDate_Insert(bduedate[i]));
									beanobject1.setRdate(Security_Counter
											.TextDate_Insert(breturndate));
									beanobject1.setTitle(btitle[i]);
									beanobject1.setAuthor(String
											.valueOf(session
													.getAttribute("UserID")));
									beanobject1.setDoc(bdocument[i]);
									beanobject1.setMname(bpublisher[i]);
									beanobject1.setTemp(Double.parseDouble(blfine[i]));
							//		beanobject1.setBranchID(branchID);
									request.setAttribute("beanobject",
											beanobject1);

									int doc_fine = ss
											.getDocmentFine(beanobject);

									// indexPage="/BulkReturnCounter/index.jsp?flag=freturn&detils=ISSUEDEATILS";
									indexPage = "/BulkReturnCounter/index.jsp?Message=return&count="+len+"&detils=ISSUEDEATILS";
								}
							} else {
								indexPage = "/BulkReturnCounter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
							}

							counterbeanobject = ss.getCounterMember(request
									.getParameter("mcode").toString().trim());

							ArrayList issue_details = (ArrayList) ss
									.getIssueDetailsMember(request
											.getParameter("mcode").toString()
											.trim());
							counterbeanobject.setCounterList(issue_details);

							request.setAttribute("bean", counterbeanobject);

						} else {
							log4jLogger.info("----Unselected value is-------"
									+ baccno[i]);
						}
					}

				}

				dispatch(request, response, indexPage);

			}

			// -------------------------- Payment Clearance
			// ----------------------------------------------------//

			if (flag.equals("savefine_payment")) {
				log4jLogger.info("Payment ===========flag=====" + flag);

				counterbeanobject = new CounterBean();
				beanobject = new CounterMemberBean();
				beanobject1 = new CounterFineBean();

				beanobject1.setMcode(request.getParameter("mcode"));
				beanobject1.setAccno(request.getParameter("accno"));
				beanobject1.setRdate(request.getParameter("rdate"));
				beanobject1.setDoc(request.getParameter("doc"));
				beanobject1.setTemp(Double.parseDouble(request
						.getParameter("fine")));

				beanobject.setMcode(beanobject1.getMcode());
				beanobject.setAccno(beanobject1.getAccno());
				beanobject.setRdate(beanobject1.getRdate());
				beanobject.setDoc(beanobject1.getDoc());
				beanobject.setTemp(beanobject1.getTemp());
				beanobject.setAuthor(String.valueOf(session
						.getAttribute("UserID")));

				int issue_details1 = ss.getFineDetail(beanobject);

				if (issue_details1 > 0) {

					counterbeanobject = ss.getCounterMember(beanobject
							.getMcode().toString().trim());
					ArrayList issue_details = (ArrayList) ss
							.getIssueDetailsMember(request
									.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);
					request.setAttribute("bean", counterbeanobject);

					indexPage = "/BulkReturnCounter/index.jsp?flag=rkfine&detils=ISSUEDEATILS";
				} else {
					indexPage = "/BulkReturnCounter/index.jsp?";
					// return;
				}

				dispatch(request, response, indexPage);
			}

			// --------------------------RENEW BOOK
			// ----------------------------------------------------//

			
			// -----------------------------------------------------------------END
			// TRY CATCH----------------//
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/**
	 * public static String getClientIpAddr(HttpServletRequest request) { String
	 * ip = request.getHeader("X-FORWARDED-FOR"); if (ip == null || ip.length()
	 * == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("Proxy-Client-IP"); } if (ip == null || ip.length() ==
	 * 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("WL-Proxy-Client-IP"); } if (ip == null || ip.length()
	 * == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("HTTP_CLIENT_IP"); } if (ip == null || ip.length() == 0
	 * || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("HTTP_X_FORWARDED_FOR"); } if (ip == null ||
	 * ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getRemoteAddr(); } return ip; }
	 * 
	 * String rk = getClientIpAddr(request);
	 * System.out.print("IPPPPPPPIIIIIIIIIIPPPPPPPPPPPPPPP:"+rk);
	 */

}


package Common.businessutil;

import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.mail.MailService;
import Common.businessutil.reports.ReportService;
import Common.businessutil.search.SearchService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Common.businessutil.sms.SmsService;

import com.library.autolib.portal.prototype.LibraryServiceFactory;


public interface BusinessServiceFactory extends LibraryServiceFactory {
	
	/** The Constant INSTANCE. */
	public static final BusinessServiceFactory INSTANCE = (BusinessServiceFactory)LibraryServiceFactory.INSTANCE;


	public CalalogingService getCalalogingService();
	public CirculationService getCirculationService();
	public AdminService getAdminService();
	public SearchService getSearchService();
	public SerialcontrolService getSerialcontrolService();
	public AcquisitionService getAcquisitionService();
	public ReportService getReportService();
	public MailService getMailService();
	public SmsService getSmsService();
	public ExceImportExportService getExceImportExportService();
	public ImportExportXMLService getImportExportXMLService();
			
}

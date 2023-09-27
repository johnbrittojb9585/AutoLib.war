
package Common.businessutil;

import Common.businessutil.calaloging.CalalogingDao;
import Common.businessutil.circulation.CirculationDao;
import Common.businessutil.admin.AdminDao;
import Common.businessutil.search.SearchDao;
import Common.businessutil.serialcontrol.SerialcontrolDao;
import Common.businessutil.sms.SmsDao;
import Common.businessutil.acquisition.AcquisitionDao;
import Common.businessutil.importexportxml.ImportExportXMLDao;
import Common.businessutil.mail.MailDao;
import Common.businessutil.reports.ReportDao;
import com.library.autolib.portal.prototype.LibraryDaoFactory;

public interface BusinessDaoFactory extends LibraryDaoFactory {
	
	/** The Constant INSTANCE. */
	public static final BusinessDaoFactory INSTANCE = (BusinessDaoFactory)LibraryDaoFactory.INSTANCE;
	
	public CalalogingDao getCalalogingDao();
	public CirculationDao getCirculationDao();
		
	public AdminDao getAdminDao();
	public SearchDao getSearchDao();
	public SerialcontrolDao getSerialcontrolDao();
	public AcquisitionDao getAcquisitionDao();
	public ReportDao getReportDao();
	public MailDao getMailDao();
	public SmsDao getSmsDao(); 
	public ImportExportXMLDao getImportExportXMLDao();
	
}

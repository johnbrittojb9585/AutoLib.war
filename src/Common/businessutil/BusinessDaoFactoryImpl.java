
package Common.businessutil;

import Common.businessutil.calaloging.CalalogingDao;

import Common.businessutil.calaloging.CalalogingDaoImpl;

import Common.businessutil.circulation.CirculationDao;
import Common.businessutil.circulation.CirculationDaoImpl;

import Common.businessutil.admin.AdminDao;
import Common.businessutil.admin.AdminDaoImpl;

import Common.businessutil.search.SearchDao;
import Common.businessutil.search.SearchDaoImpl;

import Common.businessutil.serialcontrol.SerialcontrolDao;
import Common.businessutil.serialcontrol.SerialcontrolDaoImpl;
import Common.businessutil.sms.SmsDao;
import Common.businessutil.sms.SmsDaoImpl;

import Common.businessutil.acquisition.AcquisitionDao;
import Common.businessutil.acquisition.AcquisitionDaoImpl;

import Common.businessutil.importexportxml.ImportExportXMLDao;
import Common.businessutil.importexportxml.ImportExportXMLDaoImpl;
import Common.businessutil.mail.MailDao;
import Common.businessutil.mail.MailDaoImpl;

import Common.businessutil.reports.ReportDao;
import Common.businessutil.reports.ReportDaoImpl;

import com.library.autolib.portal.prototype.LibraryHibernateDaoFactory;


public class BusinessDaoFactoryImpl extends LibraryHibernateDaoFactory
		implements BusinessDaoFactory {
	
	public CalalogingDao getCalalogingDao() {
		return (CalalogingDao) getDaoInstance(CalalogingDaoImpl.class);
	}
	public CirculationDao getCirculationDao() {
		return (CirculationDao) getDaoInstance(CirculationDaoImpl.class);
	}
	
	public AdminDao getAdminDao() {
		return (AdminDao) getDaoInstance(AdminDaoImpl.class);
	}
	public SearchDao getSearchDao() {
		return (SearchDao) getDaoInstance(SearchDaoImpl.class);
	}
	public SerialcontrolDao getSerialcontrolDao() {
		return (SerialcontrolDao) getDaoInstance(SerialcontrolDaoImpl.class);
	}
	public AcquisitionDao getAcquisitionDao() {
		return (AcquisitionDao) getDaoInstance(AcquisitionDaoImpl.class);
	}
	
	public ReportDao getReportDao() {
		return (ReportDao) getDaoInstance(ReportDaoImpl.class);
	}
	
	public MailDao getMailDao() {		
		return (MailDao) getDaoInstance(MailDaoImpl.class); 
	}
	public SmsDao getSmsDao() {		
		return (SmsDao) getDaoInstance(SmsDaoImpl.class); 
	}
	
	public ImportExportXMLDao getImportExportXMLDao() {		
		return (ImportExportXMLDao) getDaoInstance(ImportExportXMLDaoImpl.class); 
	}
	
}

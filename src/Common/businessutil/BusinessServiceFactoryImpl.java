
package Common.businessutil;

import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.calaloging.CalalogingServiceImpl;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.circulation.CirculationServiceImpl;
import Common.businessutil.admin.AdminService;
import Common.businessutil.admin.AdminServiceImpl;

import Common.businessutil.search.SearchService;
import Common.businessutil.search.SearchServiceImpl;

import Common.businessutil.serialcontrol.SerialcontrolService;
import Common.businessutil.serialcontrol.SerialcontrolServiceImpl;
import Common.businessutil.sms.SmsService;
import Common.businessutil.sms.SmsServiceImpl;

import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.acquisition.AcquisitionServiceImpl;

import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportexcel.ExceImportExportServiceUtils;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.importexportxml.ImportExportXMLServiceImpl;
import Common.businessutil.mail.MailService;
import Common.businessutil.mail.MailServiceImpl;
import Common.businessutil.reports.ReportService;
import Common.businessutil.reports.ReportServiceImpl;

import com.library.autolib.portal.prototype.LibraryServiceFactoryImpl;


public class BusinessServiceFactoryImpl extends LibraryServiceFactoryImpl 
		implements BusinessServiceFactory {
	
	public CalalogingService getCalalogingService() {
		CalalogingServiceImpl s = new CalalogingServiceImpl();
		s.setCalalogingDao(BusinessDaoFactory.INSTANCE.getCalalogingDao());
		return s;
	}
	public CirculationService getCirculationService() {
		CirculationServiceImpl s = new CirculationServiceImpl();
		s.setCirculationDao(BusinessDaoFactory.INSTANCE.getCirculationDao());
		return s;
	}
	
	public AdminService getAdminService() {
		AdminServiceImpl s = new AdminServiceImpl();
		s.setAdminDao(BusinessDaoFactory.INSTANCE.getAdminDao());
		return s;
	}
	public SearchService getSearchService() {
		SearchServiceImpl s = new SearchServiceImpl();
		s.setSearchDao(BusinessDaoFactory.INSTANCE.getSearchDao());
		return s;
	}
	
	public SerialcontrolService getSerialcontrolService() {
		SerialcontrolServiceImpl s = new SerialcontrolServiceImpl();
		s.setSerialcontrolDao(BusinessDaoFactory.INSTANCE.getSerialcontrolDao());
		return s;
	}
	public AcquisitionService getAcquisitionService() {
		AcquisitionServiceImpl s = new AcquisitionServiceImpl();
		s.setAcquisitionDao(BusinessDaoFactory.INSTANCE.getAcquisitionDao());
		return s;
	}
	
	public ReportService getReportService() {
		ReportServiceImpl s = new ReportServiceImpl();
		s.setReportDao(BusinessDaoFactory.INSTANCE.getReportDao());
		return s;
	}
	
	public MailService getMailService()  {
		MailServiceImpl s=new MailServiceImpl();
		s.setMailDao(BusinessDaoFactory.INSTANCE.getMailDao());
		return s;		
	}
	
	public SmsService getSmsService()  {
		SmsServiceImpl s=new SmsServiceImpl();
		s.setSmsDao(BusinessDaoFactory.INSTANCE.getSmsDao());
		return s;		
	}
	
	public ExceImportExportService getExceImportExportService()   {
		ExceImportExportServiceUtils s = new ExceImportExportServiceUtils();
		return s;		
	}
	
	public ImportExportXMLService getImportExportXMLService()   {
		ImportExportXMLServiceImpl s=new ImportExportXMLServiceImpl();
		s.setImportExportXMLDao(BusinessDaoFactory.INSTANCE.getImportExportXMLDao());
		return s;		
	}
}

package Common.businessutil.importexportexcel;

import java.util.Map;

public abstract interface ExportProcessor extends ExcelFieldCountProcessor
{
  
	public abstract String getExcelFileName();

 
	public abstract String[] getExcelHeader();

  
	public abstract String[] setExportExcelDataMap(Object paramObject);

 
	public abstract Map getTitleDetails();
	
	
}

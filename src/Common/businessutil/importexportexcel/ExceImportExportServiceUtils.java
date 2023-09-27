package Common.businessutil.importexportexcel;


import com.Ostermiller.util.ExcelCSVPrinter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;


public class ExceImportExportServiceUtils
  implements ExceImportExportService
{
  
  
	public void Export(Iterator paramIterator, ExportProcessor paramExportProcessor, OutputStream paramOutputStream)  
	{    
		M550437005(this, paramIterator, paramExportProcessor, paramOutputStream);
	} 

  
	protected static void M550437005(ExceImportExportServiceUtils thi, Iterator arg0, ExportProcessor arg1, OutputStream arg2)  
	{    
		try    
		{     
			ExcelCSVPrinter localExcelCSVPrinter = new ExcelCSVPrinter(arg2);
      
			Map localMap = arg1.getTitleDetails();      
			Object localObject1;
      		Object localObject2;
      
      		if (localMap != null)      
      		{
      			localObject1 = (String)localMap.get("title");        
      			localObject2 = (String)localMap.get("titleValue");        
      			Integer localInteger = (Integer)localMap.get("count");       
      			if (localInteger != null)        
      			{          
      				localExcelCSVPrinter.print((String)localObject1);          
      				localExcelCSVPrinter.println((String)localObject2);
          
      				for (int i = 0; i <= localInteger.intValue(); i++)          
      				{            
      					localExcelCSVPrinter.print((String)localMap.get("subTitle" + i));            
      					localExcelCSVPrinter.println((String)localMap.get("value" + i));          
      				}       
      			}      
      		}
      
      		localExcelCSVPrinter.println();      
      		localExcelCSVPrinter.println(arg1.getExcelHeader());      
      		while (arg0.hasNext())     
      		{        
      			localObject2 = arg0.next();        
      			localObject1 = arg1.setExportExcelDataMap(localObject2);        
      			localExcelCSVPrinter.println((String[])localObject1);      
      		}     
      		localExcelCSVPrinter.flush();      
      		localExcelCSVPrinter.close();    
		}    
		catch (IOException localIOException1)    
		{      
			throw new RuntimeException(localIOException1);    
		}  
	}
  

 
}


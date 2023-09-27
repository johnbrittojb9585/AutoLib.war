package Common.businessutil.importexportexcel;

import java.io.OutputStream;
import java.util.Iterator;


public abstract interface ExceImportExportService
{
 
	public abstract void Export(Iterator paramIterator, ExportProcessor paramExportProcessor, OutputStream paramOutputStream);



}

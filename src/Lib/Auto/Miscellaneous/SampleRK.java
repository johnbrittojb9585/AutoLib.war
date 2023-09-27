package Lib.Auto.Miscellaneous;

import java.awt.print.PrinterJob;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class SampleRK {

	
	public static void main(String[] args) {
        DocFlavor myFormat = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services =PrintServiceLookup.lookupPrintServices(myFormat, aset);
        System.out.println("The following printers are available");
        for (int i=0;i<services.length;i++) {
            System.out.println("  service name: "+services[i].getName());
        }
        
        
        //
        // Lookup for the available print services.
        //
        PrintService[] printServices = PrinterJob.lookupPrintServices();

        //
        // Iterates the print services and print out its name.
        //
        for (PrintService printService : printServices) {
            String name = printService.getName();
            System.out.println("Name = " + name);
        }
        
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();

        AttributeSet att = printer.getAttributes();

        for (Attribute a : att.toArray()) {

        String attributeName;

        String attributeValue;

        attributeName = a.getName();

        attributeValue = att.get(a.getClass()).toString();

        System.out.println(attributeName + " : " + attributeValue);

        }



    }
	
	
	
	
/**	
public static void main(String[] args) {
    //
    // Lookup for the available print services.
    //
    PrintService[] printServices = PrinterJob.lookupPrintServices();

    //
    // Iterates the print services and print out its name.
    //
    for (PrintService printService : printServices) {
        String name = printService.getName();
        System.out.println("Name = " + name);
    }
}  */

}
package Lib.Auto.Miscellaneous;

import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  

import java.awt.print.Paper;
import java.awt.print.PrinterJob;  
import java.awt.print.PageFormat;  
import java.awt.print.Printable;  
import java.awt.print.PrinterException;  
import java.awt.Color;  
import java.awt.Dialog;  
import java.awt.Dimension;  
import java.awt.Graphics;  
import java.awt.TextArea;  
import javax.swing.JTextArea;  
import javax.swing.text.JTextComponent;  

//import sun.net.www.content.text.plain;  
import java.awt.Font;  
import java.awt.FontMetrics;  
import java.awt.Insets;  
import java.awt.PrintJob;  
import java.awt.Toolkit;  
  
  
/** 
 * This class provides a means to print a text or graphics message to  
 * a receipt printer. It includes an option to pop up a JOptionPane  
 * with a configurable message that will cause the program to wait for  
 * the print to finish before continuing.  
 * <p> 
 */  
public class Receipt implements Printable {  
  
    private JFrame printFrame;  
    //private String waitMsg;  
  
    /** Inner class for the actual printed object */  
    class PrintFrame extends JFrame {  
  
        PrintFrame(String messageString) {  
              
            Insets newInsets = null;  
            setBackground(new Color(255, 255, 255, 0));  
  
            JTextArea jt = new JTextArea();  
            jt.setAlignmentY(JTextArea.CENTER_ALIGNMENT);  
            Insets in = jt.getMargin();  
            System.out.println("in before "+in);  
            if (in != null) {  
                newInsets = new Insets(2, 2, 0, 1);  
            } else {  
                newInsets = new Insets(0, 0, 0, 0);  
            }  
  
            jt.setMargin(newInsets);  
              
            System.out.println("in after"+jt.getMargin());  
  
            //System.out.println("i am getting font"+jt.getFont());  
            //jt.setFont(new java.awt.Font("Dialog", Font.BOLD, 9));SansSerif  
            jt.setFont(new java.awt.Font("Arial",1, 9));  
              
  
            //System.out.println("i am getting font after"+jt.getFont());  
  
            jt.setSize(200, 700);  
            Dimension dm1 = jt.getSize();  
            System.out.print("width....." + dm1.width);  
  
            jt.setText(messageString);  
            Dimension dm = jt.getSize();  
            System.out.print("width....." + dm.width);  
  
            System.out.print("Message....." + messageString);  
              
            add(jt);  
            pack();  
  
            setVisible(true);  // To Visible in viewer to save the document  
        }  
    }  
  
    /** Creates a new instance of ReceiptPrinter with a default wait message */  
    public Receipt() {  
        //waitMsg = "Wait for the printer to finish\nClick the OK button when done";  
    }  
  
    /** 
     * Creates a new instance of ReceiptPrinter with a wait message. 
     * 
     * @param   msg     the wait message 
     */  
    public Receipt(String msg) {  
        //waitMsg = msg;  
    }  
  
    /**  
     * Sends the actual message to the receipt printer - does not wait. 
     * 
     * @param   msg     the actual message to be printed 
     */  
    public void printIt(String messageString) {  
        printIt(messageString, false);  
    }  
  
    
    protected static double fromCMToPPI(double cm) {
        return toPPI(cm * 0.393700787);
    }
    
    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    
    /**  
     * Sends the actual message to the receipt printer. 
     * 
     * @param   msg     the actual message to be printed 
     * @param   wait    show JOptionPane to wait for print to finish 
     */  
    public void printIt(String messageString, boolean wait) {  
        //JOptionPane.showMessageDialog(null, "asdf", "Information", JOptionPane.INFORMATION_MESSAGE);  
        printFrame = new PrintFrame(messageString);  
        PrinterJob job = PrinterJob.getPrinterJob();  
        
        // For Paper Size by RK
        
        Paper paper = new Paper();
        
        double width = fromCMToPPI(8.6);
        double height = fromCMToPPI(5.4);
        paper.setSize(width, height);        
        
        
        PageFormat format = job.defaultPage();  
        format.setOrientation(PageFormat.PORTRAIT);  
        format.setPaper(paper);
        
        //job.setPrintable(this, format);   
        
        // For Page Validation by RK
        
        PageFormat validatePage = job.validatePage(format);
        System.out.println("\nValid- " + validatePage);
        job.setPrintable(this, validatePage);
        
        
        
        try {  
        	System.out.println("Ready to Print >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            job.print();  
            System.out.println("Done to Print >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //printFrame.show(false);  
        /*if (wait) 
        JOptionPane.showMessageDialog(null, waitMsg, "Information", 
        JOptionPane.INFORMATION_MESSAGE);*/  
        } catch (PrinterException e) {  
            e.printStackTrace();  
        }  
       
        printFrame.dispose();  
        
    }  
  
    /** 
     * Print method required by Printable interface. 
     * 
     * @param   g       the graphics context 
     * @param   format  the page format 
     * @param   pagenum the page number requested to print 
     * @return  int     flag indicating page existance 
     */  
    public int print(Graphics g, PageFormat format, int pagenum) {  
    	System.out.println("//////////////////////// Step 111111111 //////////////////////");
        if (pagenum > 0) {  
            return Printable.NO_SUCH_PAGE;  
        }  
        g.translate((int) format.getImageableX(),  
                (int) format.getImageableY());  
        printFrame.print(g);  
        return Printable.PAGE_EXISTS;  
    }  
  
    public static void main(String args[]) {  
  
        //Receipt r = new Receipt();  
        //r.printIt("hello world gfdgfdgfd prashant \r\n ritesh gfdgdfgfdgfdgdfgd \n fdsfdsfdsfds", true);
        
        
        StringBuffer sb = new StringBuffer();
				
		sb.append("                 Issue Receipt \n");
		sb.append("Member Code:11BE585    Name:SaravanaKaruppaiah \n");
		sb.append("Access No:B0001  \nTitle:Java 2 Enterprise Edition - by James Walker\n");
		sb.append("Issue Date: 24-03-2014   Due Date: 23-05-2014 \n");
		sb.append("\n\n");
		sb.append(" Lib Staff                            Borrower");
		
		Receipt rk = new Receipt();
		rk.printIt(sb.toString(), true);
		

        
        
        
    }  
}  
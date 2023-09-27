/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2010.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Lib.Auto.Report;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


/**
 * The Class DateWiseExportRecord.
 */

public class ReportAllExportRecord implements ExportProcessor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String rptFlag;
	
	String report_type;
	
	String rptName;
	
	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	ReportAllExportRecord(Map recordProcessorMap)
	{
		rptFlag = (String) recordProcessorMap.get("rptFlag");
		report_type=(String) recordProcessorMap.get("report_type");
		rptName = (String) recordProcessorMap.get("rptTitle");
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		documentType = (String)recordProcessorMap.get("documentType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",rptName);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","Document Type :");
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",documentType);
		title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return rptName + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{

System.out.println("flag::::::::::"+rptFlag);




           if(rptFlag.equalsIgnoreCase("curIssue"))
           {
               if(report_type.equalsIgnoreCase("listing"))
               {
               return new String[]
	           { "Member Code", "Member Name", "Access No", "Title", "Issue Date", "Due Date", "Document", "Staff Code" };	
               }
          else if(report_type.equalsIgnoreCase("cumulative"))
          {
        	     return new String[]
        		   {"Total Books Issued"};	
          }
          else if(report_type.equalsIgnoreCase("breakup"))
          {
               return new String[]
          	    {"Issue Date", "Total Books"};	
          }
          } 

       else if(rptFlag.equalsIgnoreCase("Issue"))
		{
					if(report_type.equalsIgnoreCase("listing")){
   			 return new String[]
   					{ "Member Code", "Member Name", "Access No", "Title", "Issue Date", "Due Date", "Return Date", "Document", "Staff Code" };	
   		}
   		else if(report_type.equalsIgnoreCase("cumulative")){
   			return new String[]
		    			{ "Total Books Returned"};	
   		}
   		
   		else if(report_type.equalsIgnoreCase("breakup")){
   			return new String[]
		    			{  "Return Date", "Total Books"};	
   		}
		}
		else if(rptFlag.equalsIgnoreCase("Return"))
		{
		   
		    		if(report_type.equalsIgnoreCase("listing")){
		    			 return new String[]
		    			{ "Member Code","Member Name", "Access No", "Title", "Issue Date", "Due Date", "Return Date", "Document","Staff Code"};	
		    		}
		    		else if(report_type.equalsIgnoreCase("cumulative")){
		    			return new String[]
				    			{ "Total Books Returned"};	
		    		}
		    		
		    		else if(report_type.equalsIgnoreCase("breakup")){
		    			return new String[]
				    			{  "Return Date", "Total Books"};	
		    		}
		    
		}
		
		else if(rptFlag.equalsIgnoreCase("Renewal"))
		{
			if(report_type.equalsIgnoreCase("listing")){
			return new String[]
			{ "Member Code","Member Name", "Access No", "Title", "Issue Date", "Due Date", "Document","Staff Code" };
			}
			else if(report_type.equalsIgnoreCase("cumulative")){
    			return new String[]
		    			{ "Total Books Returned"};	
    		}
    		
    		else if(report_type.equalsIgnoreCase("breakup")){
    			return new String[]
		    			{  "Return Date", "Total Books"};	
    		}
		}
		
		
		else if(rptFlag.equalsIgnoreCase("Reserve"))
		{
			        return new String[]
			     	{ "Member Code","Access No", "Title", "res_date", "Document" };			      		
		}
		
		else if(rptFlag.equalsIgnoreCase("Resreminder"))
		{
			        return new String[]
			       	{ "Member Code","Access No", "Title", "res_date", "Document" };			      		
   		}
		
		else if(rptFlag.equalsIgnoreCase("Duereminder"))
		{
			
			if(report_type.equalsIgnoreCase("listing"))
			{
			      return new String[]
	    	     { "Member Code","Member Name", "Access No", "Title", "Due Date" };
			}
			
		   else if(report_type.equalsIgnoreCase("cumulative"))
			    {
				        return new String[]
		    			{ "Total Due Books"};	
			    }
			
		 	else if(report_type.equalsIgnoreCase("breakup"))
			    {
				        return new String[]
		                {  "Due Date", "Total Books"};	
			    }
			
 		}
		
		else if(rptFlag.equalsIgnoreCase("Fine"))
		{
			System.out.println("::::rpttype++::::"+report_type);
			if(report_type.equalsIgnoreCase("listing"))
			{
			        return new String[]
					{ "Trans_No", "Member Code","Member Name","Access No","Trans_Date", "Due_Date", "Fine_Amount" };     	
		    }
			
		    else if(report_type.equalsIgnoreCase("cumulative"))
		    {
			        return new String[]
	    			{ "Total Amount collected"};	
		    }
		
		    else if(report_type.equalsIgnoreCase("breakup"))
		    {
			        return new String[]
	                {  "Trans Date", "Total Amount"};	
		    }
		}
		
		
		else
		{
			
			return new String[]
					{ "Trans_No","Member_Name", "Member_Code","Return_Date","Access_No","Due_Date","Trans_Amount" };
			
		}
		return  new String[]
				{ "Trans_No", "Member Code","Member Name","Access No","Trans_Date", "Due_Date", "Fine_Amount" };
	
	}
		

	public String[] setExportExcelDataMap(Object entity)
	{

		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		
		if(rptFlag.equalsIgnoreCase("CurIssue"))
		{	
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getAccessNo().toString();
			fields[3] = std.getTitle().toString();	
			fields[4] = std.getIssueDate().toString();
			fields[5] = std.getDueDate().toString();	
			fields[6] = std.getDocument().toString();		
			fields[7] = std.getStaffCode().toString();
			}
			if(report_type.equalsIgnoreCase("breakup"))
			{
				fields[0] =std.getIssueDate().toString();
				fields[1] =std.getNoOfBooks().toString();
			}
			if(report_type.equalsIgnoreCase("cumulative"))
			{
				fields[0] =std.getNoOfBooks().toString();
			}
			
		}
		
		
		else if(rptFlag.equalsIgnoreCase("Issue"))
		{	
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getAccessNo().toString();
			fields[3] = std.getTitle().toString();	
			fields[4] = std.getIssueDate().toString();
			fields[5] = std.getDueDate().toString();
			fields[6] = std.getReturnDate().toString();		
			fields[7] = std.getDocument().toString();		
			fields[8] = std.getStaffCode().toString();
			}
			if(report_type.equalsIgnoreCase("breakup"))
			{
				fields[0] =std.getIssueDate().toString();
				fields[1] =std.getNoOfBooks().toString();
			}
			if(report_type.equalsIgnoreCase("cumulative"))
			{
				fields[0] =std.getNoOfBooks().toString();
			}
			
		}else if(rptFlag.equalsIgnoreCase("Return"))
		{
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getAccessNo().toString();
			fields[3] = std.getTitle().toString();	
			fields[4] = std.getIssueDate().toString();
			fields[5] = std.getDueDate().toString();
			fields[6] = std.getReturnDate().toString();		
			fields[7] = std.getDocument().toString();
			fields[8] = std.getStaffCode().toString();
			}
			if(report_type.equalsIgnoreCase("breakup"))
			{
				fields[0] =std.getReturnDate().toString();
				fields[1] =std.getNoOfBooks().toString();
			}
			if(report_type.equalsIgnoreCase("cumulative"))
			{
				fields[0] =std.getNoOfBooks().toString();
			}
			
		}else if(rptFlag.equalsIgnoreCase("Renewal"))
		{
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getAccessNo().toString();
			fields[3] = std.getTitle().toString();	
			fields[4] = std.getIssueDate().toString();
			fields[5] = std.getDueDate().toString();
			fields[6] = std.getDocument().toString();	
			fields[7] = std.getStaffCode().toString();
		}
		if(report_type.equalsIgnoreCase("breakup"))
		{
			fields[0] =std.getIssueDate().toString();
			fields[1] =std.getNoOfBooks().toString();
		}
		if(report_type.equalsIgnoreCase("cumulative"))
		{
			fields[0] =std.getNoOfBooks().toString();
		}
		}
		else if(rptFlag.equalsIgnoreCase("Duereminder"))
		{
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getAccessNo().toString();
			fields[3] = std.getTitle().toString();	
			fields[4] = std.getDueDate().toString();
			}
			if(report_type.equalsIgnoreCase("breakup"))
			{
				fields[0] =std.getDueDate().toString();
				fields[1] =std.getNoOfBooks().toString();
			}
			if(report_type.equalsIgnoreCase("cumulative"))
			{
				fields[0] =std.getNoOfBooks().toString();
			}
			
		}
		else if(rptFlag.equalsIgnoreCase("Resreminder"))
		{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getAccessNo().toString();
			fields[2] = std.getTitle().toString();
			fields[3] = std.getResdate().toString();	
			fields[4] = std.getDocument().toString();
		}
		else if(rptFlag.equalsIgnoreCase("Reserve"))
		{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getAccessNo().toString();
			fields[2] = std.getTitle().toString();
			fields[3] = std.getResdate().toString();	
			fields[4] = std.getDocument().toString();
		}

		else if(rptFlag.equalsIgnoreCase("Fine"))
		{
			if(report_type.equalsIgnoreCase("listing"))
			{
			fields[0] = std.getTrans_no().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getMemberCode().toString();	
			fields[3] = std.getAccessNo().toString();
			fields[4] = std.getTrans_date().toString();
			fields[5] = std.getDueDate().toString();
			fields[6] = std.getTrans_amount().toString();
			}
			if(report_type.equalsIgnoreCase("breakup"))
			{
				fields[0] =std.getTrans_date().toString();
				fields[1] =std.getTrans_amount().toString();
			}
			if(report_type.equalsIgnoreCase("cumulative"))
			{
				fields[0] =std.getTrans_amount().toString();
			}
		}
		
		
		return fields;
	
	
	
	
	}
}

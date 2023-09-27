package Lib.Auto.Simples;
//import java.io.*;
import java.util.*;

import Lib.Auto.Advanced.Adsearchbean;

public  class Searchbean   {

    // ----------------------------------------------------- Instance Variables

    private String title = null;
    private String callno = null;
    private String publisher = " ";
    private String keywords = null;
    private String author = null;
    private String edition = null;
    private String subject = null;
    private String year = null;
    private String accno = null;
    private String isbn = null;
    private String flag = null;
    private String orderby = null;
    private String tag = null;
    private String hid = null;
    private String availability = null;
    private String location = null;
    private String document = null;
    private String Bprice = null;
    private String script= null;
    
    private String deptname= null;
  
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}



	private String supplier= null;
    
    private String volno = "";
    private String journal = "";
    private String format = "";  
    private String place = "";  
    private String pages = "";  
    private String issueNo = "";  
    private String sres = "";  
    private String phyDesc = ""; 
    public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}



	
    public String getPurchase() {
		return Purchase;
	}
	public void setPurchase(String purchase) {
		Purchase = purchase;
	}
	public String getInvoiceno() {
		return Invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		Invoiceno = invoiceno;
	}
	public String getInvoicedate() {
		return Invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		Invoicedate = invoicedate;
	}
	public String getBinding() {
		return Binding;
	}
	public void setBinding(String binding) {
		Binding = binding;
	}
	public String getMedia() {
		return Media;
	}
	public void setMedia(String media) {
		Media = media;
	}
	public String getRecdate() {
		return Recdate;
	}
	public void setRecdate(String recdate) {
		Recdate = recdate;
	}


    private String subtitle="";
	private String Purchase="";
    private String Invoiceno="";
    private String Invoicedate="";
    private String Binding="";
    private String Media="";
    private String Recdate="";
    public String getAddfield1() {
		return addfield1;
	}
	public void setAddfield1(String addfield1) {
		this.addfield1 = addfield1;
	}
	public String getAddfield2() {
		return addfield2;
	}
	public void setAddfield2(String addfield2) {
		this.addfield2 = addfield2;
	}
	public String getAddfield3() {
		return addfield3;
	}
	public void setAddfield3(String addfield3) {
		this.addfield3 = addfield3;
	}



	private String addfield1="";
    private String addfield2="";
    private String addfield3=""; 
    
    
	public String getVolno() {
		return volno;
	}
	public void setVolno(String volno) {
		this.volno = volno;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getSres() {
		return sres;
	}
	public void setSres(String sres) {
		this.sres = sres;
	}
	public String getPhyDesc() {
		return phyDesc;
	}
	public void setPhyDesc(String phyDesc) {
		this.phyDesc = phyDesc;
	}



	private java.util.ArrayList al=null;
    private static String searchstring = "";
    //private String searchstring = "";
    
    
	 public String getSearchstring() 
	 {
			return searchstring;
	 }
	public void setSearchstring(String searchstring) 
	{
		this.searchstring = searchstring;
	}



//private static String spanvalue = ""; 
//    
//    
//    public static String getSpanvalue() 
//    {
//		return spanvalue;
//	}
//
//
//
//	public static void setSpanvalue(String spanvalue) 
//	{
//		Searchbean.spanvalue = spanvalue;
//	}

	//-------------------------------------------
    public String getavailability() {
    	
    	return availability;
        }


        
        public void setavailability(String availability) {

            this.availability = availability;

        }
//      ----------------------------------------------------------- Properties

        public String getlocation() {
    	
    	return location;
        }


        
        public void setlocation(String location) {

            this.location = location;

        }

        // ----------------------------------------------------------- Properties

        
        
        public String getBprice() {
    		return Bprice;
    	}
    	public void setBprice(String bprice) {
    		Bprice = bprice;
    	}

        public String getdocument() {
    	
    	return document;
        }

        // ----------------------------------------------------------- Properties
        
        public void setdocument(String document) {

            this.document = document;

        }

        // ----------------------------------------------------------- Properties



    public String getTitle() {
	
	return title;
    }


    
    public void setTitle(String title) {

        this.title = title;

    }

    // ----------------------------------------------------------- Properties

    public String getCallno() {
	
	return callno;
    }


    
    public void setCallno(String callno) {

        this.callno = callno;

    }

//-------------------------------------
    
    public String getPublisher() {

	return publisher;

    }



    public void setPublisher(String publisher) {

        this.publisher = publisher;

    }
//------------------------------------------------

    
    public String getKeywords() {

	return keywords;

    }


    
    public void setKeywords(String keywords) {

        this.keywords = keywords;

    }


 //--------------------------------------   
    public String getAuthor() {

	return author;

    }


    public void setAuthor(String author) {

        this.author = author;

    }
	
	//----------------------------------
	
    
    public String getSubject() {

    	return subject;

        }


        public void setSubject(String subject) {

            this.subject = subject;

        }
    	//--------------------------------------
    
        public String getYear() {

        	return year;

            }


            public void setYear(String year) {

                this.year = year;

            }
    //-----------------------------------------    	
    
            public String getAccno() {

            	return accno;

                }


                public void setAccno(String accno) {

                    this.accno = accno;

                }
    //----------------------------------
                public String getIsbn() {

                	return isbn;

                    }


                    public void setIsbn(String isbn) {

                        this.isbn = isbn;

                    }
   //-------------------------------- 
                   
                    public String getFlag() {

                    	return flag;

                        }


                        public void setFlag(String flag) {

                            this.flag = flag;

                        }
       //-------------------------------- 
                    
                        public String getOderby() {

                        	return orderby;

                            }


                            public void setOderby(String orderby) {

                                this.orderby = orderby;

                            }
           //-------------------------------- 
                            public String getTag() {

                            	return tag;

                                }


                                public void setTag(String tag) {

                                    this.tag = tag;

                                }
               //-------------------------------- 
                                         
                                public String getHid() {

                                	return hid;

                                    }


                                    public void setHid(String hid) {

                                        this.hid = hid;

                                    }
                   //-------------------------------- 
                        
                        
                        
                    
	 public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }



	public String getEdition() {
		return edition;
	}



	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	

}

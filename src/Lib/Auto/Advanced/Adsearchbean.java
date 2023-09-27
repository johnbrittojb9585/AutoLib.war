package Lib.Auto.Advanced;

import java.util.*;

public  class Adsearchbean   {

    // ----------------------------------------------------- Instance Variables

    private String title = null;
    private String callno = null;
    private String publisher = null;
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
    private String department = null;
    private String supplier = null;
    private double price = 0;
    private int bcount = 0;
    private String docgroup = null;
    static String sqlquery = null;
    private String content = null;
    private String journal = ""; 
    private String issueMonth = "";
    private String pageNo = "";
    
    
    public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getIssueMonth() {
		return issueMonth;
	}

	public void setIssueMonth(String issueMonth) {
		this.issueMonth = issueMonth;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}



	




	static java.util.ArrayList al=null;
    private String sres = null;
    private String place = null;
    private String volno = null;
    private String issues = null;
   



	private static String searchstring = "";
   
	public String getSearchstring() {
		return searchstring;
	}

	public void setSearchstring(String searchstring) {
		this.searchstring = searchstring;
	}



	//  -------------------------------------------
    public String getcontent() {
    	
    	return content;
        }


        
        public void setcontent(String content) {

            this.content = content;

        }
//  -------------------------------------------
    public String getsqlquery() {
    	
    	return sqlquery;
        }


        
        public void setsqlquery(String sqlquery) {

            this.sqlquery = sqlquery;

        }
//  -------------------------------------------
    public String getdocgroup() {
    	
    	return docgroup;
        }


        
        public void setdocgroup(String docgroup) {

            this.docgroup = docgroup;

        }
//  -------------------------------------------
    public int getbcount() {
    	
    	return bcount;
        }


        
        public void setbcount(int bcount) {

            this.bcount = bcount;

        }
//  -------------------------------------------
    public double getprice() {
    	
    	return price;
        }


        
        public void setprice(double price) {

            this.price = price;

        }
        //----------------------------------
        
        public String getVolno() {
    		return volno;
    	}

    	public void setVolno(String volno) {
    		this.volno = volno;
    	}
//  -------------------------------------------
    	 public String getIssues() {
    			return issues;
    		}

    		public void setIssues(String issues) {
    			this.issues = issues;
    		}
  //--------------------------------------
    public String getsupplier() {
    	
    	return supplier;
        }


        
        public void setsupplier(String supplier) {

            this.supplier = supplier;

        }
//  -------------------------------------------
    public String getdepartment() {
    	
    	return department;
        }


        
        public void setdepartment(String department) {

            this.department = department;

        }
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


        public String getdocument() {
    	
    	return document;
        }


        
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
//  -------------------------------------------
    public String getSRes() {
    	
    	return sres;
        }


        
        public void setSRes(String sres) {

            this.sres = sres;

        }
//      -------------------------------------------
        public String getPlace() {
        	
        	return place;
            }


            
            public void setPlace(String place) {

                this.place = place;

            }



			public String getEdition() {
				return edition;
			}



			public void setEdition(String edition) {
				this.edition = edition;
			}


}

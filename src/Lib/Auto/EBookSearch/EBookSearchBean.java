package Lib.Auto.EBookSearch;

import java.util.*;

public  class EBookSearchBean   {

  // ----------------------------------------------------- Instance Variables

  private String title = null;
  private String callNo = null;
  private String pubName = null;
  private String deptName = null;
  private String authorName = null;
  private String edition = null; 
  private String subName = null;
  private String yop = null;
  private String accessNo = null;
  private String type = null;
  private String flag = null;
  private String orderby = null;
  private String tag = null;
  private String hid = null;
  private String pages = null;
  static String sqlquery = null;
  
  private String url=null;
  public String getUrl() {
	return url;
}



public void setUrl(String url) {
	this.url = url;
}



public String getIsbn() {
	return isbn;
}



public void setIsbn(String isbn) {
	this.isbn = isbn;
}



public String getSupName() {
	return supName;
}



public void setSupName(String supName) {
	this.supName = supName;
}



public String getKeywords() {
	return keywords;
}



public void setKeywords(String keywords) {
	this.keywords = keywords;
}



private String isbn=null;
  private String supName=null;
  private String keywords=null;
  
  	//-------------------------------------------
  


  public String getsqlquery() {
  	
  	return sqlquery;
      }


      
      public void setsqlquery(String sqlquery) {

          this.sqlquery = sqlquery;

      }

  
  public String getTitle() {
	
	return title;
  }

  
  public void setTitle(String title) {

      this.title = title;
  }

  public String getDeptName() {
		
		return deptName;
	  }

	  
	  public void setDeptName(String deptName) {

	      this.deptName = deptName;
	  }

  
  // ----------------------------------------------------------- Properties

  public String getCallNo() {
	
	return callNo;
  }


  
  public void setCallNo(String callNo) {

      this.callNo = callNo;

  }
  
  
//-------------------------------------
  
  public String getPubName() {

	return pubName;

  }


  public void setPubName(String pubName) {

      this.pubName = pubName;

  }


//--------------------------------------   
  public String getAuthorName() {

	return authorName;

  }


  public void setAuthorName(String authorName) {

      this.authorName = authorName;

  }
	
	//----------------------------------
	
  
  public String getSubName() {

  	return subName;

      }


      public void setSubName(String subName) {

          this.subName = subName;

      }
  	//--------------------------------------
  
      public String getYop() {

      	return yop;

          }


          public void setYop(String yop) {

              this.yop = yop;

          }
          
        	//--------------------------------------
          
          public String getType() {

          	return type;

              }


              public void setType(String type) {

                  this.type = type;

              }
         
  //-----------------------------------------    	
  
          public String getAccessNo() {

          	return accessNo;

              }


              public void setAccessNo(String accessNo) {

                  this.accessNo = accessNo;

              }
  //----------------------------------
              public String getPages() {

              	return pages;

                  }


                  public void setPages(String pages) {

                      this.pages = pages;

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
                      
    
	public String getEdition() {
		return edition;
	}



	public void setEdition(String edition) {
		this.edition = edition;
	}

}

package Lib.Auto.Jnl_Approval;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class Jnlapprovebean   {

    // ----------------------------------------------------- Instance Variables
    
    private String journal = null;
    private String dept = null;
    private String Date_From = null;
    private String Date_To = null;
    private String Status = null;
    private String Amount = null;
    private java.util.ArrayList al=null;



    // ----------------------------------------------------------- Properties

    public String getJname() {

	return journal;
    }



    public void setJname(String journal) {

        this.journal = journal;

    }

//-------------------------------------

    public String getJdname() {

	return dept;

    }



    public void setJdname(String dept) {

        this.dept = dept;

    }

     public String getJfrom() {

	return Date_From;

    }



    public void setJfrom(String Date_From) {

        this.Date_From = Date_From;

    }

 public String getJto() {

	return Date_To;

    }



    public void setJto(String Date_To) {

        this.Date_To = Date_To;

    }
 public String getJstatus() {

	return Status;

    }



    public void setJstatus(String Status) {

        this.Status = Status;

    }

    public String getJamount() {

	return Amount;

    }



    public void setJamount(String Amount) {

        this.Amount = Amount;

    }


  public ArrayList getAl() {

	return al;
    }



    public void setAl(ArrayList al) {

        this.al = al;

    }

}

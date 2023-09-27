package Common;

import java.sql.*;
import java.util.*;

public class connectionpool
{
 String dburl;
 String dsnurl;
 String uid;
 String pwd;
 int initial;
 int incre;
 private Hashtable ht;

//creating constructor.

public connectionpool(String dburl,String dsnurl,String uid,String pwd,int initial,int incre)
{
 this.dburl=dburl;
 this.dsnurl=dsnurl;
 this.uid=uid;
 this.pwd=pwd;
 this.initial=initial;
 this.incre=incre;
 ht=new Hashtable();

try
 {
 Class.forName(dburl);

 for(int i=0;i<initial;i++)
 {
 Connection temp=DriverManager.getConnection(dsnurl,uid,pwd);
 ht.put(temp,Boolean.FALSE);
 }
 }catch(Exception e)
 {e.printStackTrace();
 }
}

public Connection giveconnection()
 {
 try
 {
 Enumeration e=ht.keys();
 while(e.hasMoreElements())
 {
 Connection co=(Connection)e.nextElement();
 Boolean status=(Boolean) ht.get(co);
 if(status==Boolean.FALSE)
 {
  ht.put(co,Boolean.TRUE);
  return co;
 }

}
for(int i=1;i<incre;i++)
 {
 Connection temp=DriverManager.getConnection(dsnurl,uid,pwd);
 ht.put(temp,Boolean.FALSE);
 }

}
catch(Exception e)
{
  e.printStackTrace();
}

return giveconnection();
}

public void returnconnection(Connection c)
{
Enumeration e=ht.keys();
while(e.hasMoreElements())
{
Connection co=(Connection) e.nextElement();

if(co==c)
{
 ht.put(co,Boolean.FALSE);
 break;
}
}
}
}






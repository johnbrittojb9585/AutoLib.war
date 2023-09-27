package Common.businessutil.serialcontrol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class SessionHibernateUtil
{

    private static Log log;
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static final ThreadLocal threadSession = new ThreadLocal();
    private static final ThreadLocal threadTransaction = new ThreadLocal();

    public SessionHibernateUtil()
    {
    }

    private static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void initSessionFactory()
    {
        log.debug("Initialized SessionFactory.");
    }

    public static void closeSessionFactory()
    {
        if(sessionFactory != null && !sessionFactory.isClosed())
        {
            log.debug("Closing SessionFactory.");
            sessionFactory.close();
        }
    }

    public static Session getSession()
    {
        Session s = (Session)threadSession.get();
        if(s == null)
        {
        	
            log.debug("Opening new Session for this thread.");
            s = getSessionFactory().openSession();
            threadSession.set(s);
        }
        return s;
    }

    public static void closeSession()
    {
        Session s = (Session)threadSession.get();
        threadSession.set(null);
        if(s != null && s.isOpen())
        {
            log.debug("Closing Session of this thread.");
            s.close();
        }
    }

    public static void beginTransaction()
    {
        Transaction tx = (Transaction)threadTransaction.get();
        if(tx == null)
        {
            log.debug("Starting new database transaction in this thread.");
            tx = getSession().beginTransaction();
            threadTransaction.set(tx);
        }
    }

    public static void commitTransaction()
    {
        Transaction tx = (Transaction)threadTransaction.get();
        if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
        {
            log.debug("Committing database transaction of this thread.");
            tx.commit();
        }
        threadTransaction.set(null);
    }

    public static void rollbackTransaction()
    {
        Transaction tx = (Transaction)threadTransaction.get();
        try
        {
            threadTransaction.set(null);
            if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
            {
                log.debug("Tyring to rollback database transaction of this thread.");
                tx.rollback();
            }
        }
        finally
        {
            closeSession();
        }
        return;
    }

    static 
    {
        log = LogFactory.getLog(com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil.class);
        try
        { 
        	
            log.debug("Building SessionFactory.");
            configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        catch(Throwable t)
        {
            log.error("Building SessionFactory failed.", t);
            throw new ExceptionInInitializerError(t);
        }
    }
}

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] arg) {
        SessionFactory sessionFactory = new Configuration()
                .buildSessionFactory();

        sessionFactory.close();
    }
}

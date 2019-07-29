import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = createUser("Pam", "java", "c++");
        User user2 = createUser("Tot", "c", "python");
        hibernate(user1);
        hibernate(user2);
    }

    public static User createUser(String username, String language1, String language2) {
        List<Lang> langs = new ArrayList<>();
        Lang l1 = new Lang();
        Lang l2 = new Lang();
        l1.setLanguage(language1);
        l2.setLanguage(language2);
        langs.add(l1);
        langs.add(l2);
        User user1 = new User();
        user1.setFio(username);
        user1.setLang(langs);
        return user1;
    }

    public static void hibernate(User user1) {
        final StandardServiceRegistry registery = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registery).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user1);
        session.getTransaction().commit();
        session.close();
    }
}

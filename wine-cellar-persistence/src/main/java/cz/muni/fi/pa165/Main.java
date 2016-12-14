package cz.muni.fi.pa165;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Michaela Bamburová on 31.10.2016.
 */
public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

      //  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistenceApplicationContext.class);
        emf = Persistence.createEntityManagerFactory("default");

        emf.close();
    }
}

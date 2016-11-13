package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.InMemoryDatabaseSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Michaela Bamburová on 31.10.2016.
 */
public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
        emf = Persistence.createEntityManagerFactory("default");

        emf.close();
    }
}

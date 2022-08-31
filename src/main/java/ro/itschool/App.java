package ro.itschool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.itschool.dao.ActorDAO;
import ro.itschool.dao.ActorDAOImpl;
import ro.itschool.entity.Actor;
import ro.itschool.entity.Kids;
import ro.itschool.entity.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        ActorDAO actorDAO = new ActorDAOImpl();
        Actor actor = new Actor();
        actor.setName("Johnny Depp");
        actor.setAge(50);


        Actor actor1 = new Actor();
        actor1.setName("Denzel Washington");
        actor1.setAge(60);

        Actor actor2 = new Actor();
        actor2.setName("Leonardo DiCaprio");
        actor2.setAge(47);


   /*         LOGGER.info(actorDAO.getActorById(100).toString());
        } catch (Exception e) {
            LOGGER.error("GETBYID: ********id not found********");
        }*/

        //one to one
        Manager manager = new Manager();
        manager.setName("Depp's Manager");
        manager.setPrice(10000);
        manager.setActor(actor);
        actor.setManager(manager);
        actorDAO.createActor(actor);

        Manager manager1 = new Manager();
        manager1.setName("Denzel's Manager");
        manager1.setPrice(20000);
        manager1.setActor(actor1);
        actor1.setManager(manager1);
        actorDAO.createActor(actor1);

        Manager manager2 = new Manager();
        manager2.setName("DiCaprio's Manager");
        manager2.setPrice(10000);
        manager2.setActor(actor2);
        actor.setManager(manager2);
        actorDAO.createActor(actor2);

        //one to many
        List<Kids> kids= Arrays.asList(
                new Kids("Depp's son",actor),
                new Kids("Depp's daughter", actor),
                new Kids("Denzel's son", actor1),
                new Kids("Denzel's daughter", actor1),
                new Kids("DiCaprio's daughter", actor2));

        actor.setKids(kids);
        actorDAO.createActor(actor);

        try {
            Actor actor3 = actorDAO.getActorById(3);
            System.out.println(actor3);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


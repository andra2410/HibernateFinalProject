package ro.itschool.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.itschool.App;
import ro.itschool.entity.Actor;
import ro.itschool.util.HibernateUtil;

import java.util.List;

public class ActorDAOImpl implements ActorDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(ActorDAOImpl.class);

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;
    private Transaction transaction;

    @Override
    public void createActor(Actor actor) {
        LOGGER.info("*****Creating actor..{}*****", actor);
        openSessionAndTransaction();
        session.save(actor);
        closeSessionAndTransaction();
        LOGGER.info("******Actor {} was created*****", actor);

    }

    @Override
    public Actor createActorAndReturnIt(Actor actor) {
        LOGGER.info("********Creating and returing actor...{}*********", actor);
        openSessionAndTransaction();
        int id = (int) session.save(actor);
        closeSessionAndTransaction();
        actor.setId(id);
        return actor;
    }

    @Override
    public List<Actor> getAllActors() {
        LOGGER.info("***********Getting all actors***********");
        String hql = "from actors";
        session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List<Actor> actors = query.list();
        return actors;
    }

    @Override
    public Actor getActorById(Integer id) throws Exception {
        session = sessionFactory.openSession();
        LOGGER.info("***Getting actor with id {}.............***", id);
        Actor actor = new Actor();
        Actor a = session.get(Actor.class, id);
        session.close();
        if(a==null) throw new Exception("Actor id not found");
        LOGGER.info("Found actor: {}", a);
        return a;
    }

    @Override
    public Actor updateActorById(Integer id, Actor actor) {
        openSessionAndTransaction();
        session.update(actor);
        closeSessionAndTransaction();
        return actor;
    }

    @Override
    public void deleteActor(Integer id) {
        LOGGER.info("Deleting actor with id {}", id);
        openSessionAndTransaction();
        try {
            session.delete(getActorById(id));
        } catch (Exception e) {
            LOGGER.error("DELETE : ID NOT FOUND");
        }
        closeSessionAndTransaction();

    }

    private void openSessionAndTransaction() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }
}

package ro.itschool.dao;

import ro.itschool.entity.Actor;

import java.util.List;

public interface ActorDAO {

    void createActor(Actor actor);

    Actor createActorAndReturnIt(Actor actor);

    List<Actor> getAllActors();

    Actor getActorById(Integer id) throws Exception;

    Actor updateActorById(Integer id, Actor actor);

    void deleteActor(Integer id);
}

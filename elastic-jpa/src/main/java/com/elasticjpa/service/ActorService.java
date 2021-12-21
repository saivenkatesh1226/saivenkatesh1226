package com.elasticjpa.service;

import com.elasticjpa.repository.ActorRepository;
import com.elasticjpa.repository.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public ActorRepository getActorRepository() {
        return actorRepository;
    }

    public void setActorRepository(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public long updateAll() {
        List<Actor> actors = findAll();
        actors.stream().forEach( actor -> {
           actor.setConFirstName(actor.getFirstName());
        });
        List<Actor> updatedActors = actorRepository.saveAll(actors);
        actorRepository.flush();
        return updatedActors.stream().count();
    }
}

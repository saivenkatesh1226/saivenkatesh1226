package com.elasticjpa.controller;

import com.elasticjpa.repository.entity.Actor;
import com.elasticjpa.service.ActorService;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestMapping("/actors")
@RestController
public class ActorController {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ActorService actorService;

    @GetMapping("/all")
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/updateAll")
    public long updateAll() {
        return actorService.updateAll();
    }

    @GetMapping("index")
    public String index() throws Exception {
        SearchSession session = Search.session(entityManager);
        session.massIndexer(Actor.class).startAndWait();
        return "done";
    }

    @GetMapping("search")
    public List<Actor> Search(@RequestParam String name, @RequestParam String fieldName) throws Exception {
        SearchSession session = Search.session(entityManager);
        SearchResult<Actor> result =  session.search(Actor.class)
                .where (f -> f.match()
                        .field(fieldName)
                        .matching(name))
                .fetchAll();
        return result.hits();

    }

    public ActorService getActorService() {
        return actorService;
    }

    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

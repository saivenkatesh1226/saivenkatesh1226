package com.elasticjpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Indexed
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id", nullable = false)
    private Long actorId;
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    @FullTextField(analyzer = "english", projectable = Projectable.YES)
    @KeywordField(name = "firstName_sort",  sortable = Sortable.YES)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    @FullTextField
    private String lastName;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @Basic
    @Column (name = "con_first_nm", nullable = false)
    @Convert(converter = NameToIntHifenConverter.class)
    @KeywordField(
            valueBridge = @ValueBridgeRef(type = NameToIntHifenBridge.class),
            projectable = Projectable.NO,
            sortable = Sortable.YES
    )
    private String conFirstName;

    @JsonIgnore
    @OneToMany(mappedBy = "actorByActorId", fetch = FetchType.LAZY)
    private Collection<FilmActor> filmActorsByActorId;

    public String getConFirstName() {
        return conFirstName;
    }

    public void setConFirstName(String conFirstName) {
        this.conFirstName = conFirstName;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actorId != null ? !actorId.equals(actor.actorId) : actor.actorId != null) return false;
        if (firstName != null ? !firstName.equals(actor.firstName) : actor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(actor.lastName) : actor.lastName != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(actor.lastUpdate) : actor.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actorId != null ? actorId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Collection<FilmActor> getFilmActorsByActorId() {
        return filmActorsByActorId;
    }

    public void setFilmActorsByActorId(Collection<FilmActor> filmActorsByActorId) {
        this.filmActorsByActorId = filmActorsByActorId;
    }
}

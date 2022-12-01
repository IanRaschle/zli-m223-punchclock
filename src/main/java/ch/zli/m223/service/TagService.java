package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Tag createEntry(Tag entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Entry", Tag.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(entityManager.find(Tag.class, id));
    }

    @Transactional
    public void updateEntity(Tag entry) {
        entityManager.merge(entry);
    }

    public Tag find(long id) {
        return entityManager.find(Tag.class, id);
    }

}

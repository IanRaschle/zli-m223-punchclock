package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createEntry(Category entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(entityManager.find(Category.class, id));
    }

    @Transactional
    public void updateEntity(Category entry) {
        entityManager.merge(entry);
    }

    public Category find(long id) {
        return entityManager.find(Category.class, id);
    }

}

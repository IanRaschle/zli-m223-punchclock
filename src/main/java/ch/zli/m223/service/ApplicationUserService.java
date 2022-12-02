package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;

@ApplicationScoped
@NamedQueries({
    @NamedQuery(name = "ApplicationUser.findByUserName", query = "SELECT u FROM u WHERE u.username = :userName")
})
public class ApplicationUserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public ApplicationUser createApplicationUser(ApplicationUser applicationUser) {
        entityManager.persist(applicationUser);
        return applicationUser;
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();
    }

}

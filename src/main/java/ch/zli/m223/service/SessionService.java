package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Credentials;
import io.smallrye.jwt.build.Jwt;


@ApplicationScoped
public class SessionService {
    @Inject
    private EntityManager entityManager;

    public boolean checkCredentials(Credentials credentials) {
        var user = findUser(credentials.getUserName());
        if (user.isPresent()) {
            return user.get().getPwd().equals(credentials.getPwd());
        }
        return false;
    }

    private Optional<ApplicationUser> findUser(String userName) {
        return entityManager.createNamedQuery("ApplicationUser.findByUserName", ApplicationUser.class).setParameter("userName", userName).getResultStream().findFirst();
    }
}

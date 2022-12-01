package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.*;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {
    @Inject
    EntityManager entityManager;

    @Transactional
    void generateTestData(@Observes StartupEvent startupEvent) {

        entityManager.createNativeQuery("DELETE FROM entry_tag").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM entry").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM category").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM tag").executeUpdate();

        //categories
        var categoryA = new Category();
        categoryA.setTitle("Project A");
        entityManager.persist(categoryA);

        var categoryB = new Category();
        categoryB.setTitle("Project B");
        entityManager.persist(categoryB);


        var categoryC = new Category();
        categoryC.setTitle("Project C");
        entityManager.persist(categoryC);

        // Tags
        var tagA = new Tag();
        tagA.setTitle("tag A");
        entityManager.persist(tagA);

        var tagB = new Tag();
        tagB.setTitle("tag B");
        entityManager.persist(tagB);

        var tagC = new Tag();
        tagC.setTitle("tag C");
        entityManager.persist(tagC);

        //entries
        var firstEntry = new Entry();
        firstEntry.setCategory(categoryC);
        firstEntry.setTags(new HashSet<>(Arrays.asList(tagA, tagC)));
        firstEntry.setCheckIn(LocalDateTime.now().minusHours(2));
        firstEntry.setCheckOut(LocalDateTime.now().plusHours(1));
        entityManager.persist(firstEntry);

        var secondEntry = new Entry();
        secondEntry.setCategory(categoryA);
        secondEntry.setTags(Set.of(tagA));
        secondEntry.setCheckIn(LocalDateTime.now().minusHours(4));
        secondEntry.setCheckOut(LocalDateTime.now());
        entityManager.persist(secondEntry);
    }

}   

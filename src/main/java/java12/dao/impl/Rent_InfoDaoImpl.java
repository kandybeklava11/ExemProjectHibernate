package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.Rent_InfoDao;
import java12.entity.Owner;
import java12.entity.Rent_Info;

import java.util.Collections;
import java.util.List;

public class Rent_InfoDaoImpl implements Rent_InfoDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();

    @Override
    public List<Rent_Info> getAllRent_Infos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            List<Rent_Info> rent_infos = entityManager.createQuery("select r from Rent_Info r", Rent_Info.class).getResultList();
            entityManager.getTransaction().commit();
            return rent_infos;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Rent_Info getRent_InfoById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Rent_Info rentInfo = entityManager.find(Rent_Info.class,id);
            entityManager.getTransaction().commit();
            return rentInfo;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}

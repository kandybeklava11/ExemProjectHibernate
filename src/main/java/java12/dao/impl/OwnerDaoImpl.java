package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.OwnerDao;
import java12.entity.House;
import java12.entity.Owner;

import java.util.Collections;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();


    @Override
    public String saveOwner(Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newOwner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        }finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }
        }
        return "Owner saved successfully !";
    }

    @Override
    public String removeOwnerById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            entityManager.remove(owner);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        }finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }
        }
        return "Owner deleted successfully !";
    }

    @Override
    public List<Owner> getAllOwners() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Owner> owners = entityManager.createQuery("select o from Owner o", Owner.class).getResultList();
            entityManager.getTransaction().commit();
            return owners;
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
    public Owner getOwnerById(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            entityManager.getTransaction().commit();
            return owner;
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

    @Override
    public String updateOwnerById(Long id, Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner1 = entityManager.find(Owner.class, id);
            owner1.setFirstName(owner.getFirstName());
            owner1.setLastName(owner.getLastName());
            owner1.setEmail(owner.getEmail());
            owner1.setGender(owner.getGender());
            owner1.setDateOfBirth(owner.getDateOfBirth());
            owner1.setAgencies(owner.getAgencies());
            owner1.setHouses(owner.getHouses());
            owner1.setRent_infos(owner.getRent_infos());
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return "Owner updated successfully !";
    }
}


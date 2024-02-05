package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.AgencyDao;
import java12.entity.Address;
import java12.entity.Agency;

import java.util.Collections;
import java.util.List;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();

    @Override
    public String saveAgency(Agency newAgency) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAgency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
                entityManager.close();

        }
        return "Agency saved successfully !";
    }

    @Override
    public String removeAgencyById(long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
            return "Agency deleted successfully!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
                entityManager.close();

        }
    }

    @Override
    public List<Agency> getAllAgency() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
            entityManager.getTransaction().commit();
            return agencies;
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }e.printStackTrace();
            return Collections.emptyList();
        }finally {
            entityManager.close();
        }
        }





    @Override
    public Agency getAgencyById(Long agencyId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, agencyId);
            entityManager.getTransaction().commit();
            return agency;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
                entityManager.close();
            }

    }


    @Override
    public String updateAgencyById(Long id, Agency agency) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency agency1 = entityManager.find(Agency.class, id);
            if (agency1 != null) {
                agency1.setName(agency.getName());
                agency1.setPhoneNumber(agency.getPhoneNumber());
                Address updatedAddress = agency.getAddress();
                if (updatedAddress != null) {
                    Address existingAddress = entityManager.find(Address.class, updatedAddress.getId());
                    if (existingAddress != null) {
                        existingAddress.setCity(updatedAddress.getCity());
                        existingAddress.setRegion(updatedAddress.getRegion());
                        existingAddress.setStreet(updatedAddress.getStreet());
                    } else {
                        entityManager.persist(updatedAddress);
                    }
                    agency1.setAddress(updatedAddress);
                }
                entityManager.getTransaction().commit();
                return "Agency updated successfully!";
            } else {
                return "Agency with id " + id + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update agency: " + e.getMessage();
        }
    }

}


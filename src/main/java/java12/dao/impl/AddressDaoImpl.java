package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.AddressDao;
import java12.entity.Address;
import java12.entity.Agency;

import java.util.*;

public class AddressDaoImpl implements AddressDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();

    @Override
    public List<Address> getAllAddress() {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Address> addresses = entityManager.createQuery("select a from Address a", Address.class).getResultList();
            entityManager.getTransaction().commit();
            return addresses;
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
    public Address getAddressById(Long addressId) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
            return address;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }

        }

    }

    @Override
    public String updateAddressById(Long id, Address address) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address1 = entityManager.find(Address.class, id);
            address1.setStreet(address.getStreet());
            address1.setCity(address.getCity());
            address1.setRegion(address.getRegion());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }

        }
        return "Address updated successfully !";
    }

    @Override
    public List<Address> GetAddressWithAgency() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            List<Address>addresses=entityManager.
                    createQuery("select a from Address a inner join a.agency ",Address.class).getResultList();
            entityManager.getTransaction().commit();
            return addresses;
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();

        }finally {
                entityManager.close();
            }
        }

    @Override
    public Long countOfAgencyFromCity(String nameOfCity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Long count = entityManager.createQuery(
                            "select count(aa.id) from Address a inner join a.agency aa where a.city = :nameOfCity", Long.class)
                    .setParameter("nameOfCity", nameOfCity)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return count;
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
    public Map<String, List<Agency>> groupByRegion() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            List<Object[]> results = entityManager.createQuery(
                            "select a.region, a.agency from Address a group by a.region, a.agency", Object[].class)
                    .getResultList();

            Map<String, List<Agency>> regionAgencyMap = new HashMap<>();

            for (Object[] result : results) {
                String region = (String) result[0];
                Agency agency = (Agency) result[1];

                regionAgencyMap.computeIfAbsent(region, k -> new ArrayList<>()).add(agency);
            }

            entityManager.getTransaction().commit();

            return regionAgencyMap;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyMap();
        } finally {
            entityManager.close();
        }
    }



}


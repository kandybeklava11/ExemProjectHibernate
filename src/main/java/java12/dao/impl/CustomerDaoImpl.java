package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.CustomerDao;
import java12.entity.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();

    @Override
    public String saveCustomer(Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCustomer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return "Customer saved successfully !";
    }

    @Override
    public String removeCustomerById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            return "Customer deleted successfully!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
            return customers;
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
    public Customer getCustomerById(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            entityManager.getTransaction().commit();
            return customer;
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
    public String updateCustomerById(Long id, Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer1 = entityManager.find(Customer.class, id);
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setEmail(customer.getEmail());
            customer1.setNationality(customer.getNationality());
            customer1.setDateOfBirth(customer.getDateOfBirth());
            customer1.setGender(customer.getGender());
            customer1.setFamilyStatus(customer.getFamilyStatus());
            customer1.setRent_infos(customer.getRent_infos());
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
        return "Update successfully !";
    }
}

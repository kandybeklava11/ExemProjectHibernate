package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.HouseDao;
import java12.entity.House;

import java.util.Collections;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private final EntityManagerFactory entityManagerFactory = Config.getEntity();


    @Override
    public String saveHouse(House newHouse) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newHouse);
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
        return "House saved successfully !";
    }

    @Override
    public String removeHouseById(long id) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            entityManager.remove(house);
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
        return "House deleted successfully !";
    }

    @Override
    public List<House> getAllHouses() {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h", House.class).getResultList();
            entityManager.getTransaction().commit();
            return houses;
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
    public House getHouseById(Long houseId) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);
            entityManager.getTransaction().commit();
            return house;
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
    public String updateHouseById(Long id, House house) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house1 = entityManager.find(House.class, id);
            house1.setDescription(house.getDescription());
            house1.setAddress(house.getAddress());
            house1.setHouseType(house.getHouseType());
            house1.setFurniture(house.isFurniture());
            house1.setRoom(house.getRoom());
            house1.setPrice(house.getPrice());
            house1.setOwner(house.getOwner());
            house1.setRating(house.getRating());
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
        return "House updated successfully !";
    }
}

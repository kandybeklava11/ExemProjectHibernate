package java12.dao;

import java12.entity.Agency;
import java12.entity.House;

import java.util.List;

public interface HouseDao {
    String saveHouse(House newHouse);
    String removeHouseById(long id);
    List<House> getAllHouses();
    House getHouseById(Long houseId);
    String updateHouseById(Long id, House house);
}

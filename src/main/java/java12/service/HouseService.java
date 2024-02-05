package java12.service;

import java12.entity.House;

import java.util.List;

public interface HouseService {
    String saveHouse(House newHouse);
    String removeHouseById(long id);
    List<House> getAllHouses();
    House getHouseById(Long houseId);
    String updateHouseById(Long id, House house);
}

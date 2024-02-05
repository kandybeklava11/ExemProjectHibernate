package java12.service.impl;

import java12.dao.HouseDao;
import java12.dao.impl.HouseDaoImpl;
import java12.entity.House;
import java12.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {
    private final HouseDao houseDao=new HouseDaoImpl();
    @Override
    public String saveHouse(House newHouse) {
        return houseDao.saveHouse(newHouse);
    }

    @Override
    public String removeHouseById(long id) {
        return houseDao.removeHouseById(id);
    }

    @Override
    public List<House> getAllHouses() {
        return houseDao.getAllHouses();
    }

    @Override
    public House getHouseById(Long houseId) {
        return houseDao.getHouseById(houseId);
    }

    @Override
    public String updateHouseById(Long id, House house) {
        return houseDao.updateHouseById(id,house);
    }
}

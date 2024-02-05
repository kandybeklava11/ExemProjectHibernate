package java12.service.impl;

import java12.dao.Rent_InfoDao;
import java12.dao.impl.Rent_InfoDaoImpl;
import java12.entity.Rent_Info;
import java12.service.Rent_InfoService;

import java.util.List;

public class Rent_InfoServiceImpl implements Rent_InfoService {
    private final Rent_InfoDao rentInfoDao = new Rent_InfoDaoImpl();
        @Override
        public List<Rent_Info> getAllRent_Infos() {
            return rentInfoDao.getAllRent_Infos();
        }

        @Override
        public Rent_Info getRent_InfoById(Long id) {
            return rentInfoDao.getRent_InfoById(id);
        }
    }


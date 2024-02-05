package java12.service.impl;

import java12.dao.OwnerDao;
import java12.dao.impl.OwnerDaoImpl;
import java12.entity.Owner;
import java12.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao=new OwnerDaoImpl();
    @Override
    public String saveOwner(Owner newOwner) {
        return ownerDao.saveOwner(newOwner);
    }

    @Override
    public String removeOwnerById(long id) {
        return ownerDao.removeOwnerById(id);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.getAllOwners();
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        return ownerDao.getOwnerById(ownerId);
    }

    @Override
    public String updateOwnerById(Long id, Owner owner) {
        return ownerDao.updateOwnerById(id,owner);
    }
}

package java12.dao;

import java12.entity.Agency;
import java12.entity.Owner;

import java.util.List;

public interface OwnerDao {
    String saveOwner(Owner newOwner);
    String removeOwnerById(long id);
    List<Owner> getAllOwners();
    Owner getOwnerById(Long ownerId);
    String updateOwnerById(Long id, Owner owner);
}

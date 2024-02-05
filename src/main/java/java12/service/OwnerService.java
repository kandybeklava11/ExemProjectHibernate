package java12.service;

import java12.entity.Owner;

import java.util.List;

public interface OwnerService {
    String saveOwner(Owner newOwner);
    String removeOwnerById(long id);
    List<Owner> getAllOwners();
    Owner getOwnerById(Long ownerId);
    String updateOwnerById(Long id, Owner owner);
}

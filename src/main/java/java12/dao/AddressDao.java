package java12.dao;

import java12.entity.Address;
import java12.entity.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    List<Address> getAllAddress();
    Address getAddressById(Long addressId);
    String updateAddressById(Long id, Address address);
    List<Address>GetAddressWithAgency();
    Long countOfAgencyFromCity(String nameOfCity);
    Map<String, List<Agency>> groupByRegion();
}

package java12.service.impl;

import java12.dao.AddressDao;
import java12.dao.impl.AddressDaoImpl;
import java12.entity.Address;
import java12.entity.Agency;
import java12.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao=new AddressDaoImpl();
    @Override
    public List<Address> getAllAddress() {
        return addressDao.getAllAddress();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressDao.getAddressById(addressId);
    }

    @Override
    public String updateAddressById(Long id, Address address) {
        return addressDao.updateAddressById(id,address);
    }

    @Override
    public List<Address> GetAddressWithAgency() {
        return addressDao.GetAddressWithAgency();
    }

    @Override
    public Long countOfAgencyFromCity(String nameOfCity) {
        return addressDao.countOfAgencyFromCity(nameOfCity);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return addressDao.groupByRegion();
    }
}

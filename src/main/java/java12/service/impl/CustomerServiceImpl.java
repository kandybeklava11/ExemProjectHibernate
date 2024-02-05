package java12.service.impl;

import java12.dao.CustomerDao;
import java12.dao.impl.CustomerDaoImpl;
import java12.entity.Customer;
import java12.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao=new CustomerDaoImpl();
    @Override
    public String saveCustomer(Customer newCustomer) {
        return customerDao.saveCustomer(newCustomer);
    }

    @Override
    public String removeCustomerById(long id) {
        return customerDao.removeCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public String updateCustomerById(Long id, Customer customer) {
        return customerDao.updateCustomerById(id,customer);
    }
}

package java12.dao;

import java12.entity.Agency;
import java12.entity.Customer;

import java.util.List;

public interface CustomerDao {
    String saveCustomer(Customer newCustomer);
    String removeCustomerById(long id);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    String updateCustomerById(Long id, Customer customer);
}

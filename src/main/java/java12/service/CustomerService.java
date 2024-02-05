package java12.service;

import java12.entity.Customer;

import java.util.List;

public interface CustomerService {
    String saveCustomer(Customer newCustomer);
    String removeCustomerById(long id);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    String updateCustomerById(Long id, Customer customer);
}

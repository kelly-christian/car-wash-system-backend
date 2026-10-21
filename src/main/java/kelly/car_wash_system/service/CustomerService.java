package kelly.car_wash_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kelly.car_wash_system.model.Customer;
import kelly.car_wash_system.repository.CustomerRepository;

@Service 
public class CustomerService {

    @Autowired 
    private CustomerRepository customerRepo;

    public String saveCustomer(Customer customer){

        customerRepo.save(customer);
        return "customer saved successfull";
    }

    public String updateCustomer(Customer customer,UUID id){

        Optional<Customer>custo=customerRepo.findById(id);

        if(custo.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Id to update doesnt exist");
        }

        Customer existingCustomer=custo.get();
         existingCustomer.setFirstName(customer.getFirstName());
         existingCustomer.setLastName(customer.getLastName());
         existingCustomer.setPlateNumber(customer.getPlateNumber());
         existingCustomer.setPhone(customer.getPhone());
         existingCustomer.setCarModel(customer.getCarModel());

         customerRepo.save(existingCustomer);
         return "customer updated successfully";
    }

    public String deleteCustomer(UUID id){

        Optional<Customer>custo=customerRepo.findById(id);

        if(custo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer id to delete doesnt exist");
        }

        customerRepo.deleteById(id);
        return "customerd deleted successfull";
    }

    public List<Customer>displayCustomer(){
        return customerRepo.findAll();
    }

    public Optional<Customer>displayOneCustomer(UUID id){
        Optional<Customer> custo=customerRepo.findById(id);

        if(custo.isEmpty()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"customer id not found to search");
        }
        return customerRepo.findById(id);
    }

    public List<Customer>displayLastName(String lastName){
       return customerRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

}

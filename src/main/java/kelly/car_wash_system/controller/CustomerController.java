package kelly.car_wash_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kelly.car_wash_system.model.Customer;
import kelly.car_wash_system.service.CustomerService;

@RestController 
@RequestMapping (value = "/api/customer")
public class CustomerController {

    @Autowired 
    private CustomerService customerService;

    @PostMapping (value = "/save")

    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){

        String message=customerService.saveCustomer(customer);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping (value = "/update/{id}")

    public ResponseEntity<String>updateCustomer(@RequestBody Customer customer,@PathVariable UUID id){

        String message=customerService.updateCustomer(customer, id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id){

        String message=customerService.deleteCustomer(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/all")

    public ResponseEntity<List<Customer>>displayCustomer(){

        List<Customer>message=customerService.displayCustomer();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Optional<Customer>>displayOneCustomer(@PathVariable UUID id){

        Optional<Customer>message=customerService.displayOneCustomer(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/by-last-name")

    public ResponseEntity<List<Customer>>displayLastName(@RequestParam String lastName){

        List<Customer> message=customerService.displayLastName(lastName);
        return new ResponseEntity<>(message,HttpStatus.OK);

    }

    @GetMapping (value = "/of-washer/{washerId}")

    public ResponseEntity<List<Customer>>findCustomersOfWasher(@PathVariable UUID washerId){

        List<Customer>message=customerService.findCustomersOfWasher(washerId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}

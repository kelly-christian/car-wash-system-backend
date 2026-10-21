package kelly.car_wash_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kelly.car_wash_system.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,UUID>{

    Optional<Customer>findByFirstName(String firstName);
    Optional<Customer>findByLastName(String lastName);
    Optional<Customer>findByPlateNumber(String plateNumber);
    Optional<Customer>findByPhone(String phone);
    Optional<Customer>findByCarModel(String carModel);
    List<Customer>findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

}

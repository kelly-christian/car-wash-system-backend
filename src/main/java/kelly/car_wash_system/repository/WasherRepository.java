package kelly.car_wash_system.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.Washer;

@Repository 
public interface WasherRepository extends JpaRepository<Washer,UUID>{
    Optional<Washer>findByFirstName(String firstName);
    Optional<Washer>findByLastName(String lastName);
    Optional<Washer>findByPhone(String phone);
    Optional<Washer>findByWashBays_Id(String id);

}

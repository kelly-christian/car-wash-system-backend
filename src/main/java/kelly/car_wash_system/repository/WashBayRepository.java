package kelly.car_wash_system.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.WashBay;

@Repository 
public interface WashBayRepository extends JpaRepository<WashBay,UUID>{

    Optional<WashBay>findByBayNumber(int bayNumber);
    Optional<WashBay>findByName(String name);


}

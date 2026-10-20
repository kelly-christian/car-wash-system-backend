package kelly.car_wash_system.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kelly.car_wash_system.model.WashPackage;

public interface WashPackageRepository extends JpaRepository<WashPackage,UUID>{

    Optional<WashPackage>findByName(String name);

}

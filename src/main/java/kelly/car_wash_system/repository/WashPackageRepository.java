package kelly.car_wash_system.repository;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kelly.car_wash_system.model.WashPackage;

public interface WashPackageRepository extends JpaRepository<WashPackage,UUID>{

    Optional<WashPackage>findByName(String name);
    @Query("""

        SELECT wp from WashPackage wp
        WHERE wp.washers IS EMPTY

    """)
    List<WashPackage>findByUnUsedWashPackage();
}

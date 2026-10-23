package kelly.car_wash_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.Washer;

@Repository 
public interface WasherRepository extends JpaRepository<Washer,UUID>{
    Optional<Washer>findByFirstName(String firstName);
    Optional<Washer>findByLastName(String lastName);
    Optional<Washer>findByPhone(String phone);
    Optional<Washer>findByWashBays_Id(String id);
    @Query ("""
        
        SELECT DISTINCT w from Washer w
        join w.washPackages wp
        where lower(wp.name)=lower(:name)
        
    """)
    List<Washer>findByWashPackageNameIgnoreCase(@Param ("name") String name);

}

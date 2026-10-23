package kelly.car_wash_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.WashBay;

@Repository 
public interface WashBayRepository extends JpaRepository<WashBay,UUID>{

    Optional<WashBay>findByBayNumber(int bayNumber);
    Optional<WashBay>findByName(String name);
    @Query ("""
         
        SELECT wb FROM WashBay wb
        WHERE NOT EXISTS(
        SELECT w from Washer w
        WHERE w.washBays=wb)
        ORDER BY wb.bayNumber ASC
        
        """)
    List<WashBay>findByFreeWashBay();    

}

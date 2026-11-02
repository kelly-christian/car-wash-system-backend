package kelly.car_wash_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.JobStatus;
import kelly.car_wash_system.model.WashJob;
import kelly.car_wash_system.model.Washer;

@Repository 
public interface WashJobRepository extends JpaRepository<WashJob,UUID>{

    Optional<WashJob>findByJobDate(LocalDate jobDate);
    Optional<WashJob>findByStatus(JobStatus status);
    Optional<WashJob>findByVehicleType(String  vehicleType);
    Optional<WashJob>findByCustomers_Id(UUID id);
    Optional<WashJob>findByWashers_Id(UUID id);
    List<WashJob>findByStatusOrderByJobDateAsc(JobStatus status);
    List<WashJob>findByJobDateBetweenOrderByJobDateAsc(LocalDate start,LocalDate end);
    Boolean existsByWashers_IdAndJobDateAndStatusNot(UUID id,LocalDate jobDate,JobStatus status);

    @Query ("""
        
        select wj.status,count(wj.id) from WashJob wj
        group by wj.status
        """)
    List<Object[]> findByWashJobPerStatus();  
    
    @Modifying 
    @Query ("""

        UPDATE WashJob wj
        SET wj.status=:status
        WHERE
        wj.washers.id=:washerId
        AND wj.jobDate=:date
        AND wj.status <>:finishedStatus

        """)

    int cancelJobsByWasherAndDate(

        @Param ("status") JobStatus status,
        @Param ("washerId") UUID washerId,
        @Param ("date") LocalDate date,
        @Param ("finishedStatus") JobStatus finishedStatus

    )  ;
}

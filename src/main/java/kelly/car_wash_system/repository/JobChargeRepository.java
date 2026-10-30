package kelly.car_wash_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.JobCharge;
import kelly.car_wash_system.model.WashJob;

@Repository 
public interface JobChargeRepository extends JpaRepository<JobCharge,UUID>{

    Optional<JobCharge>findByServiceName(String serviceName);
    Optional<JobCharge>findByRemarks(String remarks);
    Optional<JobCharge>findByAmountRwf(int amountRwf);
    Optional<JobCharge>findByChargedDate(LocalDate chargedDate);
    Optional<JobCharge>findByWashJobs(WashJob washJobs);
    Boolean existsByWashJobs(WashJob washJob);
    @Query ("""
        
        SELECT jc from JobCharge jc
        WHERE jc.washJobs.customers.plateNumber=:plateNumber
        Order By jc.washJobs.jobDate Asc


    """)
    List<JobCharge>findByCustomer(@Param ("plateNumber") String plateNumber);
}

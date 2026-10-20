package kelly.car_wash_system.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kelly.car_wash_system.model.JobStatus;
import kelly.car_wash_system.model.WashJob;

@Repository 
public interface WashJobRepository extends JpaRepository<WashJob,UUID>{

    Optional<WashJob>findByJobDate(LocalDate jobDate);
    Optional<WashJob>findByStatus(JobStatus status);
    Optional<WashJob>findByVehicleType(String  vehicleType);
    Optional<WashJob>findByCustomers_Id(UUID id);
    Optional<WashJob>findByWashers_Id(UUID id);

}

package kelly.car_wash_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
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
}

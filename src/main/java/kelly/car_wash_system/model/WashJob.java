package kelly.car_wash_system.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class WashJob {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID id;
    private LocalDate jobDate;
    @Enumerated (EnumType.STRING)
    private JobStatus status;
    private String vehicleType;
    
  @ManyToOne 
  @JoinColumn (name = "customer_id")
  private Customer customers;

  @ManyToOne 
  @JoinColumn (name = "washer_id")
  private Washer washers;

  public WashJob() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDate getJobDate() {
    return jobDate;
  }

  public void setJobDate(LocalDate jobDate) {
    this.jobDate = jobDate;
  }

  public JobStatus getStatus() {
    return status;
  }

  public void setStatus(JobStatus status) {
    this.status = status;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }

  public Customer getCustomers() {
    return customers;
  }

  public void setCustomers(Customer customers) {
    this.customers = customers;
  }

  public Washer getWashers() {
    return washers;
  }

  public void setWashers(Washer washers) {
    this.washers = washers;
  }

  

}

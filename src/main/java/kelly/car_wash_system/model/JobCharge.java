package kelly.car_wash_system.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class JobCharge {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID id;
    private String serviceName;
    private String remarks;
    private int amountRwf;
    private LocalDate chargedDate;

    @ManyToOne 
    @JoinColumn (name = "wash_job_id")
    private WashJob washJobs;

    public JobCharge() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getChargedDate() {
        return chargedDate;
    }

    public void setChargedDate(LocalDate chargedDate) {
        this.chargedDate = chargedDate;
    }

    public WashJob getWashJobs() {
        return washJobs;
    }

    public void setWashJobs(WashJob washJobs) {
        this.washJobs = washJobs;
    }

    


}

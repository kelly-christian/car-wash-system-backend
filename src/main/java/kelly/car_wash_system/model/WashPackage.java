package kelly.car_wash_system.model;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity 
public class WashPackage {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID id;
    private String name;

    @ManyToMany (mappedBy = "washPackages")
    @JsonIgnore 
    private List<Washer>washers=new ArrayList<>();

    public WashPackage() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Washer> getWashers() {
        return washers;
    }

    public void setWashers(List<Washer> washers) {
        this.washers = washers;
    }

    
}

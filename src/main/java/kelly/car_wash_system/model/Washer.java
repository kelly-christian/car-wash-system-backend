package kelly.car_wash_system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity 
public class Washer {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;

    @ManyToMany 
    @JsonIgnore 
    @JoinTable (name = "washer_wash_package",
        joinColumns = @JoinColumn (name="washer_id"),
        inverseJoinColumns=@JoinColumn (name="wash_package_id")
    )
    private List<WashPackage> washPackages =new ArrayList<>();
    
   @ManyToOne 
   @JoinColumn (name = "wash_bay_id",nullable = true)
   private WashBay washBays;

   public Washer() {
   }

   public UUID getId() {
    return id;
   }

   public void setId(UUID id) {
    this.id = id;
   }

   public String getFirstName() {
    return firstName;
   }

   public void setFirstName(String firstName) {
    this.firstName = firstName;
   }

   public String getLastName() {
    return lastName;
   }

   public void setLastName(String lastName) {
    this.lastName = lastName;
   }

   public String getPhone() {
    return phone;
   }

   public void setPhone(String phone) {
    this.phone = phone;
   }

   public List<WashPackage> getWashPackages() {
    return washPackages;
   }

   public void setWashPackages(List<WashPackage> washPackages) {
    this.washPackages = washPackages;
   }

   public WashBay getWashBays() {
    return washBays;
   }

   public void setWashBays(WashBay washBays) {
    this.washBays = washBays;
   }


   

}

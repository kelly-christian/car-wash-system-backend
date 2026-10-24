package kelly.car_wash_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kelly.car_wash_system.model.WashBay;
import kelly.car_wash_system.model.WashPackage;
import kelly.car_wash_system.model.Washer;
import kelly.car_wash_system.repository.WashBayRepository;
import kelly.car_wash_system.repository.WashPackageRepository;
import kelly.car_wash_system.repository.WasherRepository;

@Service 
public class WasherService {

    @Autowired 
    private WasherRepository washerRepo;
    @Autowired 
    private WashBayRepository washBayRepo;
    @Autowired 
    private WashPackageRepository washPackageRepo;

    public String saveWasher(Washer washer,UUID washBayId,List<UUID>washPackageIds){
        
         Optional<WashBay>bay=washBayRepo.findById(washBayId);

         if(bay.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"bayId provided doesnt exist");
         }

         for(UUID washPackageId:washPackageIds){
            Optional<WashPackage>washP=washPackageRepo.findById(washPackageId);
            if(washP.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washPackages id provided doesnt exist");
            }
            washer.getWashPackages().add(washP.get());
         }
         washer.setWashBays(bay.get());
         washerRepo.save(washer);
         return "washer saved successfull";
     
    }

    public String updateWasher(Washer washer,UUID id,UUID washBayId,List<UUID>washPackageIds){

        Optional<WashBay>bay=washBayRepo.findById(washBayId);
        Optional<Washer>washeR=washerRepo.findById(id);

         if(bay.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"bayId provided to update doesnt exist");
         }
         

         List<WashPackage>packageList=new ArrayList<>();
         for(UUID washPackageId:washPackageIds){
            Optional<WashPackage>washP=washPackageRepo.findById(washPackageId);

            if(washP.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washPackages Ids to be updated doesnt exist");
            }
            packageList.add(washP.get());
         }

         Washer existingWasher=washeR.get();
          existingWasher.setFirstName(washer.getFirstName());
          existingWasher.setLastName(washer.getLastName());
          existingWasher.setPhone(washer.getPhone());
          existingWasher.setWashPackages(packageList);

          if(bay.isPresent()){
            WashBay newWashBay=bay.get();
            existingWasher.setWashBays(newWashBay);

          }

          washerRepo.save(existingWasher);
          return "washer updated successfull";

    }

    public String deleteWasher(UUID id){

       Optional<Washer>washeR=washerRepo.findById(id);

       if(washeR.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washer id to be deleted doesnt exist");
       }

       washerRepo.deleteById(id);
       return "washer deleted successful";
    }

    public List<Washer>displayAllWasher(){

      return washerRepo.findAll();
    }

    public Optional<Washer>displayOneWasher(UUID id){

      Optional<Washer>wash=washerRepo.findById(id);

      if(wash.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washer  Id to displayed doesnt exist");

      }

      return washerRepo.findById(id);
    }

    public List<Washer>displayWasherByWashPackageName(String name){

         return washerRepo.findByWashPackageNameIgnoreCase(name);

    }

   public String addWashPackageToWasher(UUID id,UUID washPackageId){

      Optional<Washer>washer=washerRepo.findById(id);

      if(washer.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Washer Id doesnt exist");
      }
      
      Optional<WashPackage>washP=washPackageRepo.findById(washPackageId);

      if(washP.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"WashPackage Id doesnt exist");
      }
      
      Washer washeR=washer.get();
      WashPackage washPa=washP.get();

      if(washeR.getWashPackages().contains(washPa)){
         throw new ResponseStatusException(HttpStatus.CONFLICT,"washer already has that washpackage");
      }

      washeR.getWashPackages().add(washPa);
      washerRepo.save(washeR);
      return "washPackage added to a washer successfull";

   }

   public String changeWashBay(UUID id,int bayNumber){

      Optional<Washer>washer=washerRepo.findById(id);

      if(washer.isEmpty()){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Washer Id doesnt exists");
      }

      WashBay newBay=washBayRepo.findByBayNumber(bayNumber)
      .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"bayNumber doesnt exist"));

      Boolean taken=washerRepo.existsByWashBays(newBay);

      if(taken){
         throw new ResponseStatusException(HttpStatus.CONFLICT,"bayNumber is taken by another washer");
      }

      Washer washeR=washer.get();
      washeR.setWashBays(newBay);
      return "Washbay changed successfull";
   }


}

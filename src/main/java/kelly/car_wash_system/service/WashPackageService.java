package kelly.car_wash_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kelly.car_wash_system.model.WashPackage;
import kelly.car_wash_system.repository.WashPackageRepository;

@Service 
public class WashPackageService {

    @Autowired 
    private WashPackageRepository washPackageRepo;

    public String saveWashPackage(WashPackage washPackage){

        washPackageRepo.save(washPackage);
        return "washPackage saved successfull";
    }

    public String updateWashPackage(WashPackage washPackage,UUID id){

        Optional<WashPackage>wash=washPackageRepo.findById(id);

        if(wash.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washPackage Id to update doesnt exist");
        }

        WashPackage existingWashPackage=wash.get();
         existingWashPackage.setName(washPackage.getName());

         washPackageRepo.save(existingWashPackage);
         return "washPackage updated successfull";
    }

    public String deleteWashPackage(UUID id){
        
        Optional<WashPackage>wash=washPackageRepo.findById(id);

        if(wash.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washPackage Id to delete doesnt exist");
        }

        washPackageRepo.deleteById(id);
        return "washPackage deleted successfull";

    }

    public List<WashPackage>displayWashPackage(){
        return washPackageRepo.findAll();
    }

    public Optional<WashPackage>displayOneWashPackage(UUID id){

        Optional<WashPackage>wash=washPackageRepo.findById(id);

        if(wash.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washPackage Id to be displayed doesnt exist");
        }

        return washPackageRepo.findById(id);
    }

}

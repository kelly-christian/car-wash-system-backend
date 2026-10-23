package kelly.car_wash_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kelly.car_wash_system.model.WashPackage;
import kelly.car_wash_system.service.WashPackageService;

@RestController 
@RequestMapping (value = "/api/washPackage")
public class WashPackageController {

    @Autowired 
    private WashPackageService washPackageService;

    @PostMapping (value = "/save")

    public ResponseEntity<String>saveWashPackage(@RequestBody WashPackage washPackage){

        String message=washPackageService.saveWashPackage(washPackage);
        return new ResponseEntity<>(message,HttpStatus.CREATED);

    }


    @PutMapping (value = "/update/{id}")

    public ResponseEntity<String>updateWashPackage(@RequestBody WashPackage washPackage,@PathVariable UUID id){

       String message=washPackageService.updateWashPackage(washPackage, id);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String>deleteWashPackage(@PathVariable UUID id){

        String message=washPackageService.deleteWashPackage(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/all")

    public ResponseEntity<List<WashPackage>>displayWashPackage(){

        List<WashPackage>message=washPackageService.displayWashPackage();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Optional<WashPackage>>displayOneWashPackage(@PathVariable UUID id){

        Optional<WashPackage>message=washPackageService.displayOneWashPackage(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/unused")

    public ResponseEntity<List<WashPackage>>findUnUsedWashPackage(){

        List<WashPackage>message=washPackageService.findUsedWashPackage();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
 }

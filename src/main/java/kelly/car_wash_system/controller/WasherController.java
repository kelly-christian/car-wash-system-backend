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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kelly.car_wash_system.model.Washer;
import kelly.car_wash_system.service.WasherService;

@RestController 
@RequestMapping (value = "api/washer")
public class WasherController {

    @Autowired 
    private WasherService washerService;

    @PostMapping (value = "/save")

    public ResponseEntity<String>saveWasher(@RequestBody Washer washer,@RequestParam UUID washBayId,@RequestParam List<UUID>washPackageIds){

        String message=washerService.saveWasher(washer, washBayId, washPackageIds);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping (value = "/update/{id}")

    public ResponseEntity<String>updateWasher(@RequestBody Washer washer,@PathVariable UUID id,@RequestParam UUID washBayId,@RequestParam List<UUID>washPackageIds){
         
        String message=washerService.updateWasher(washer, id, washBayId, washPackageIds);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String>deleteWasher(@PathVariable UUID id){

        String message=washerService.deleteWasher(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/all")

    public ResponseEntity<List<Washer>>displayAllWasher(){

        List<Washer>message=washerService.displayAllWasher();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Optional<Washer>>displayOneWasher(@PathVariable UUID id){

        Optional<Washer>message=washerService.displayOneWasher(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/by-wash-package")

    public ResponseEntity<List<Washer>>displayWasherByWashPackageName(@RequestParam String name){

        List<Washer>message=washerService.displayWasherByWashPackageName(name);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}

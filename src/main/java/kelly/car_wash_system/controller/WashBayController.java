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

import kelly.car_wash_system.model.WashBay;
import kelly.car_wash_system.service.WashBayService;

@RestController 
@RequestMapping (value = "/api/washBay")
public class WashBayController {

    @Autowired 
    private WashBayService washBayService;

    @PostMapping (value = "/save")

    public ResponseEntity<String>saveBay(@RequestBody WashBay washBay){

        String message=washBayService.saveBay(washBay);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping ("/update/{id}")

    public ResponseEntity<String>updateBay(@RequestBody WashBay washBay,@PathVariable UUID id){

        String message=washBayService.updateBay(washBay, id)  ;        
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String>deleteBay(@PathVariable UUID id){

        String mesage=washBayService.deleteBay(id);
        return new ResponseEntity<>(mesage,HttpStatus.OK);
    }

    @GetMapping (value = "/all")

    public ResponseEntity<List<WashBay>>displayWashBay(){
        List<WashBay>message=washBayService.displayWashBay();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Optional<WashBay>>displayOneWashBay(@PathVariable UUID id){

        Optional<WashBay>message=washBayService.displayOneWashBay(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}

package kelly.car_wash_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kelly.car_wash_system.model.JobStatus;
import kelly.car_wash_system.model.WashJob;
import kelly.car_wash_system.service.WashJobService;

@RestController 
@RequestMapping (value = "/api/washJob")
public class WashJobController {

    @Autowired 
    private WashJobService washJobService;

    @PostMapping (value = "/save")

    public ResponseEntity<String>saveWashJob(@RequestBody WashJob washJob,@RequestParam UUID customerId,@RequestParam UUID washerId){

        String message=washJobService.saveWashJob(washJob, customerId, washerId);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping (value = "/update/{id}")

    public ResponseEntity<String>updateWashJob(@RequestBody WashJob washJob,@PathVariable UUID id,@RequestParam UUID customerId,@RequestParam UUID washerId){
         
        String message=washJobService.updateWashJob(washJob, id, customerId, washerId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String>deleteWashJob(@PathVariable UUID id){

        String message=washJobService.deleteWashJob(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/all")

    public ResponseEntity<List<WashJob>>displayAllWashJob(){

        List<WashJob>message=washJobService.displayAllWashJob();
        return new ResponseEntity<>(message,HttpStatus.OK);
 
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Optional<WashJob>>displayOneWashJob(@PathVariable UUID id){

        Optional<WashJob>message=washJobService.displayOneWashJob(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/by-status")

    public ResponseEntity<List<WashJob>>displayWashJobStatus(@RequestParam JobStatus status){

        List<WashJob>message=washJobService.displayWashJobStatus(status);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping (value = "/between")

    public ResponseEntity<List<WashJob>>displayJobDateBetween(@RequestParam String start,@RequestParam String end){

        LocalDate startDate=LocalDate.parse(start);
        LocalDate endDate=LocalDate.parse(end);

        List<WashJob>message=washJobService.displaywashJobDateBetween(startDate, endDate);
        return new ResponseEntity<>(message,HttpStatus.OK);

    }

    @PatchMapping (value = "/{id}/status")

    public ResponseEntity<String>updateWashJobStatus(@PathVariable UUID id,@RequestParam JobStatus newStatus){

        String message=washJobService.updateWashJobStatus(id, newStatus);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}

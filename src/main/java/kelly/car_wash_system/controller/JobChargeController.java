package kelly.car_wash_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kelly.car_wash_system.model.JobCharge;
import kelly.car_wash_system.service.JobChargeService;

@RestController 
@RequestMapping (value = "api/job-charges")
public class JobChargeController {

    @Autowired 
    private JobChargeService jobChargeService;

    @PostMapping (value = "/save")

    public ResponseEntity<String>saveJobCharge(@RequestBody JobCharge jobCharge,@RequestParam UUID washJobId){

        String message=jobChargeService.saveJobCharge(jobCharge, washJobId);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @GetMapping (value = "/customer/{plateNumber}")

    public ResponseEntity<List<JobCharge>>findByCustomerCharges(@PathVariable String plateNumber){
        List<JobCharge>message=jobChargeService.findByCustomerCharges(plateNumber);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")

    public ResponseEntity<String>deleteWashJob(@PathVariable UUID id){

        String message=jobChargeService.deleteJobCharge(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


}

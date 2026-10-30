package kelly.car_wash_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kelly.car_wash_system.model.Customer;
import kelly.car_wash_system.model.JobCharge;
import kelly.car_wash_system.model.JobStatus;
import kelly.car_wash_system.model.WashJob;
import kelly.car_wash_system.repository.CustomerRepository;
import kelly.car_wash_system.repository.JobChargeRepository;
import kelly.car_wash_system.repository.WashJobRepository;

@Service 
public class JobChargeService {
    @Autowired 
    private JobChargeRepository jobChargeRepo;
    @Autowired 
    private WashJobRepository washJobRepo;
    @Autowired 
    private CustomerRepository customerRepo;

    public String saveJobCharge(JobCharge jobCharge, UUID washJobId){

        Optional<WashJob>washJ=washJobRepo.findById(washJobId);

        if(washJ.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washJob Id doesnt not exist"); 
        }
        JobStatus status=washJ.get().getStatus();

        if(!status.equals(JobStatus.FINISHED)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Job Charges are only for FINISHED washJob yet yours is "+washJ.get().getStatus());
        }

        jobCharge.setWashJobs(washJ.get());
        jobCharge.setChargedDate(LocalDate.now());
        jobChargeRepo.save(jobCharge);
        return "Job Charge is save successfull";
    }

    public List<JobCharge>findByCustomerCharges(String PlateNumber){

        //Optional<Customer>custo=customerRepo.findByPlateNumber(PlateNumber);

       // if(custo.isEmpty()){
       //     throw new ResponseStatusException(HttpStatus.NOT_FOUND,"plateNumber doesnt exist");
       // }

        return jobChargeRepo.findByCustomer(PlateNumber);
    }

    public String deleteJobCharge(UUID id){

        Optional<JobCharge>jobC=jobChargeRepo.findById(id);

        if(jobC.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"jobCharge Id provided doesnt exist");
        }

        jobChargeRepo.deleteById(id);
        return "jobCharge deleted successfull";
    }

}

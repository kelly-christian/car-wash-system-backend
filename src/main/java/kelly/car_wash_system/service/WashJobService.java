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
import kelly.car_wash_system.model.WashJob;
import kelly.car_wash_system.model.Washer;
import kelly.car_wash_system.repository.CustomerRepository;
import kelly.car_wash_system.repository.WashJobRepository;
import kelly.car_wash_system.repository.WasherRepository;
import tools.jackson.databind.deser.jdk.UUIDDeserializer;

@Service 
public class WashJobService {

    @Autowired 
    private WashJobRepository washJobRepo;
    @Autowired 
    private WasherRepository washerRepo;
    @Autowired 
    private CustomerRepository customerRepo;

    public String saveWashJob(WashJob washJob,UUID customerId,UUID washerId){


        Optional<Customer>custo=customerRepo.findById(customerId);

        if(custo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"customer ID provided doesnt exist");
        }
    
        Optional<Washer>washeR=washerRepo.findById(washerId);

        if(washeR.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washer ID provided doesnt exist");
        }
      
        washJob.setCustomers(custo.get());
        washJob.setWashers(washeR.get());

        washJobRepo.save(washJob);
        return "washJOb saved successfull";


    }

    public String updateWashJob(WashJob washJob,UUID id,UUID customerId,UUID washerId){

        Optional<WashJob> washJ=washJobRepo.findById(id);

        if(washJ.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washJob ID provided doesnt exist");
        }

        Optional<Customer>custo=customerRepo.findById(customerId);

        if(custo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"customer ID provided doesnt exist");
        }
    
        Optional<Washer>washeR=washerRepo.findById(washerId);

        if(washeR.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washer ID provided doesnt exist");
        }
        

        WashJob existingWashJob=washJ.get();
         existingWashJob.setJobDate(washJob.getJobDate());
         existingWashJob.setStatus(washJob.getStatus());
         existingWashJob.setVehicleType(washJob.getVehicleType());

         Customer newCustomer=custo.get();
         existingWashJob.setCustomers(newCustomer);

         Washer newWasher=washeR.get();
         existingWashJob.setWashers(newWasher);

         washJobRepo.save(existingWashJob);
         return "washJob updated successfull";
    }

    public String deleteWashJob(UUID id){
       
        Optional<WashJob> washJ=washJobRepo.findById(id);

        if(washJ.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washJob ID provided tobe deleted doesnt exist");
        }

        washJobRepo.deleteById(id);
        return "washJob deleted successfull";

    }

    public List<WashJob>displayAllWashJob(){
        return washJobRepo.findAll();
    }

    public Optional<WashJob>displayOneWashJob(UUID id){

        Optional<WashJob> washJ=washJobRepo.findById(id);

        if(washJ.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washJob ID provided to displayed doesnt exist");
        }

        return washJobRepo.findById(id);
    }

}

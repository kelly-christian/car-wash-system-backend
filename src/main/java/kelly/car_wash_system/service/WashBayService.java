package kelly.car_wash_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kelly.car_wash_system.model.WashBay;
import kelly.car_wash_system.repository.WashBayRepository;

@Service 
public class WashBayService {

    @Autowired 
    private WashBayRepository washBayRepo;

    public String saveBay(WashBay washBay){

        washBayRepo.save(washBay);
        return "washBay saved successfull";
    }

    public String updateBay(WashBay washBay,UUID id){

        Optional<WashBay> bay=washBayRepo.findById(id);

        if(bay.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washBay Id to update doesnt exist");

        }

        WashBay existingBay=bay.get();
         existingBay.setBayNumber(washBay.getBayNumber());
         existingBay.setName(washBay.getName());

         washBayRepo.save(existingBay);
         return "washBay updated successfull";
    }

    public String deleteBay(UUID id){

        Optional<WashBay>bay=washBayRepo.findById(id);

        if (bay.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washBay Id to delete doesnt exist"); 
        }

        washBayRepo.deleteById(id);
        return "washBay deleted successfull";
     
    }

    public List<WashBay>displayWashBay(){
        return washBayRepo.findAll();
    }

    public Optional<WashBay>displayOneWashBay(UUID id){

        Optional<WashBay>bay=washBayRepo.findById(id);

        if(bay.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"washBay Id to display doesnt exist");
        }

        return washBayRepo.findById(id);
    }


}

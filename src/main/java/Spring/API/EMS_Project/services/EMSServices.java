package Spring.API.EMS_Project.services;

import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.repository.EMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EMSServices {

    @Autowired
    private EMSRepository emsRepository;

    public void saveEmployee(Employee employee){
        emsRepository.save(employee);
    }
}

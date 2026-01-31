package Spring.API.EMS_Project.services;

import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.repository.EMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EMSServices {

    @Autowired
    private EMSRepository emsRepository;

    public void saveEmployee(Employee employee){
        emsRepository.save(employee);
    }

    public List<Employee> getAll(){
        return emsRepository.findAll();
    }

    public Optional<Employee> getById(Long id){
        return emsRepository.findById(id);
    }

    public void deleteById(Long id){
        emsRepository.deleteById(id);
    }

    public Employee updateById(Long id, Employee updatedEmployee){
        Employee existingEmployee= emsRepository.findById(id).orElse(null);
        if(existingEmployee!=null){
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
        }
        emsRepository.save(existingEmployee);

        return existingEmployee;
    }

}

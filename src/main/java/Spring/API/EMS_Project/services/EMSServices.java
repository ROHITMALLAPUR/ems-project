package Spring.API.EMS_Project.services;

import Spring.API.EMS_Project.dto.EmployeeRequestDTO;
import Spring.API.EMS_Project.dto.EmployeeResponseDTO;
import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.repository.EMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EMSServices {

    private Employee mapToEntity(EmployeeRequestDTO dto){
        Employee emp=new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());
        emp.setRole(dto.getRole());
        emp.setStatus(dto.getStatus());
        return emp;
    }

    private EmployeeResponseDTO mapToResponse(Employee emp){
        return new EmployeeResponseDTO(
                emp.getId(),
                emp.getName(),
                emp.getEmail(),
                emp.getDepartment(),
                emp.getSalary(),
                emp.getDateofJoining(),
                emp.getRole(),
                emp.getStatus()
        );
    }

    @Autowired
    private EMSRepository emsRepository;

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeReqdto){
        Employee emp= mapToEntity(employeeReqdto);
        Employee employee=emsRepository.save(emp);
        return mapToResponse(employee);
    }

    public List<EmployeeResponseDTO> getAll(){
        return emsRepository.findAll().stream().map(this::mapToResponse).toList();

    }

    public EmployeeResponseDTO getById(Long id){
        Employee emp=emsRepository.findById(id).orElse(null);
        return mapToResponse(emp);
    }

    public void deleteById(Long id){
        Employee emp=emsRepository.findById(id).orElse(null);
        emsRepository.delete(emp);
    }

    public EmployeeResponseDTO updateById(Long id, EmployeeRequestDTO updatedEmployee){
        Employee existingEmployee= emsRepository.findById(id).orElse(null);
        if(existingEmployee!=null){
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setRole(updatedEmployee.getRole());
            existingEmployee.setStatus(updatedEmployee.getStatus());
        }
        Employee updatedData=emsRepository.save(existingEmployee);

        return mapToResponse(updatedData);
    }

}

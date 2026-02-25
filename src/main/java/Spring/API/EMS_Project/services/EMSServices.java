package Spring.API.EMS_Project.services;

import Spring.API.EMS_Project.dto.EmployeePageResponseDTO;
import Spring.API.EMS_Project.dto.EmployeeRequestDTO;
import Spring.API.EMS_Project.dto.EmployeeResponseDTO;
import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import Spring.API.EMS_Project.exception.ResourceNotFoundException;
import Spring.API.EMS_Project.repository.EMSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EMSServices {

    private static final Logger logger=LoggerFactory.getLogger(EMSServices.class);

    private Employee mapToEntity(EmployeeRequestDTO dto){
        Employee emp=new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());
        emp.setRole(Role.EMPLOYEE);
        emp.setStatus(Status.ACTIVE);
        emp.setDateofJoining(LocalDate.now());
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

    private EmployeeResponseDTO maptoDTO(Employee employee){
        EmployeeResponseDTO dto=new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());
        dto.setRole(employee.getRole());
        dto.setStatus(employee.getStatus());
        dto.setDateofJoining(employee.getDateofJoining());
        return dto;
    }

    @Autowired
    private EMSRepository emsRepository;

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeReqdto){
        logger.info("Creating Employee with email : {}", employeeReqdto.getEmail());
        Employee emp= mapToEntity(employeeReqdto);
        Employee employee=emsRepository.save(emp);
        logger.info("Created Employee with ID : {}", employee.getId());
        return mapToResponse(employee);
    }

    public List<EmployeeResponseDTO> getAll(){
        return emsRepository.findAll().stream().map(this::mapToResponse).toList();

    }

    public EmployeeResponseDTO getById(Long id){
        logger.info("Fetching Employee with id : {}", id);
        Employee emp=emsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not found with id: " + id ));
        return mapToResponse(emp);
    }

    public void deleteById(Long id){
        logger.info("Deleting Employee with id : {}", id);
        Employee emp=emsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not found with id: " + id ));
        emsRepository.delete(emp);
    }

    public EmployeeResponseDTO updateById(Long id, EmployeeRequestDTO updatedEmployee){
        logger.info("Updating Employee with id : {}", id);
        Employee existingEmployee= emsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not found with id: " + id ));
        if(existingEmployee!=null){
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
        }
        Employee updatedData=emsRepository.save(existingEmployee);

        return mapToResponse(updatedData);
    }


    public Page<EmployeeResponseDTO> getEmployeewithPaginationandSorting(
            String department,
            Double minSalary,
            Double maxSalary,
            Role role,
            String search,
            Status status,
            int page,
            int pageSize,
            String sortBy,
            String direction)
    {

        Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable=PageRequest.of(page,pageSize,sort);
        Page<Employee> employeePage;

        boolean hasSearch=search!=null && !search.isBlank();
        boolean hasDepartment=department != null && !department.isBlank();
        boolean hasSalary=minSalary!=null && maxSalary!=null;
        boolean hasRole=role!=null;
        boolean hasStatus=status!=null;


        if(hasSearch){
            employeePage=emsRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search,search,pageable);
        }

        else if (hasDepartment && hasSalary && hasRole && hasStatus){
            employeePage=emsRepository.findByDepartmentIgnoreCaseAndSalaryBetweenAndRoleAndStatus(department,minSalary,maxSalary,role,status,pageable);
        }
        else if(hasDepartment){
            employeePage=emsRepository.findByDepartmentIgnoreCase(department,pageable);
        }
        else if (hasSalary){
            employeePage=emsRepository.findBySalaryBetween(minSalary,maxSalary,pageable);
        }
        else if(hasRole){
            employeePage=emsRepository.findByRole(role,pageable);
        }
        else if(hasStatus){
            employeePage=emsRepository.findByStatus(status,pageable);
        }
        else{
            employeePage=emsRepository.findAll(pageable);
        }

        return employeePage.map(this::maptoDTO);
    }
}

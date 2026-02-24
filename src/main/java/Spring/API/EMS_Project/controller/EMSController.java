package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.dto.EmployeePageResponseDTO;
import Spring.API.EMS_Project.dto.EmployeeRequestDTO;
import Spring.API.EMS_Project.dto.EmployeeResponseDTO;
import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import Spring.API.EMS_Project.services.EMSServices;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Employee APIs", description = "operations related to employees")
@RestController
@RequestMapping("/Employee_Details")
public class EMSController {

    @Autowired
    private EMSServices emsServices;


    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeEntry) {
        try{
            EmployeeResponseDTO responseDTO= emsServices.saveEmployee(employeeEntry);
            return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        return ResponseEntity.ok(emsServices.getAll());
    }


    @GetMapping({"id/{myId}"})
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long myId) {
        return ResponseEntity.ok(emsServices.getById(myId));
    }

    @DeleteMapping({"id/{myId}"})
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long myId) {
        emsServices.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"id/{myId}"})
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById( @PathVariable Long myId,@Valid @RequestBody EmployeeRequestDTO employeeEntryDTO) {
        EmployeeResponseDTO updatedEmployee=emsServices.updateById(myId,employeeEntryDTO);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<EmployeePageResponseDTO> getEmployees(
            @Parameter(description = "Department name (optional)")
            @RequestParam(required = false) String department,

            @Parameter(description = "Minimum Salary")
            @RequestParam(required = false ) Double minSalary,

            @Parameter(description = "Maximum Salary")
            @RequestParam(required = false ) Double maxSalary,

            @Parameter(description = "Employee Role(Employee/Admin)")
            @RequestParam(required = false) Role role,

            @Parameter(description = "Employee Status(Active/Inactive)")
            @RequestParam(required = false) Status status,

            @Parameter(description = "Search By name or email")
            @RequestParam(required = false) String search,

            @Parameter(description = "Page number (default=0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size (default=5)")
            @RequestParam(defaultValue = "5") int size,

            @Parameter(description = "sort field")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction(asc/desc)")
            @RequestParam(defaultValue = "asc") String direction){

        Page<EmployeeResponseDTO> employeePage=emsServices.getEmployeewithPaginationandSorting(department,minSalary,maxSalary,role,search,status,page,size,sortBy,direction);

        EmployeePageResponseDTO response=new EmployeePageResponseDTO();
        response.setContent(employeePage.getContent());
        response.setPageNumber(employeePage.getNumber());
        response.setPageSize(employeePage.getSize());
        response.setTotalElements(employeePage.getTotalElements());
        response.setTotalPages(employeePage.getTotalPages());
        response.setLast(employeePage.isLast());

        return ResponseEntity.ok(response);
    }



}


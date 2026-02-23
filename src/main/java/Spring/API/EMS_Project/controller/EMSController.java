package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.dto.EmployeePageResponseDTO;
import Spring.API.EMS_Project.dto.EmployeeRequestDTO;
import Spring.API.EMS_Project.dto.EmployeeResponseDTO;
import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import Spring.API.EMS_Project.services.EMSServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            @RequestParam(required = false) String department,
            @RequestParam(required = false ) Double minSalary,
            @RequestParam(required = false ) Double maxSalary,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
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


package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.dto.EmployeeRequestDTO;
import Spring.API.EMS_Project.dto.EmployeeResponseDTO;
import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.services.EMSServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeEntry) {
        try{
            EmployeeResponseDTO responseDTO= emsServices.saveEmployee(employeeEntry);
            return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
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
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@PathVariable Long myId, @RequestBody EmployeeRequestDTO employeeEntryDTO) {
        EmployeeResponseDTO updatedEmployee=emsServices.updateById(myId,employeeEntryDTO);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }
}


package Spring.API.EMS_Project.controller;



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
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employeeEntry) {
        try{emsServices.saveEmployee(employeeEntry);
            return new ResponseEntity<>(employeeEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Employee> allEmpList= emsServices.getAll();
        if(allEmpList!=null && !allEmpList.isEmpty()){
            return new ResponseEntity<>(allEmpList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping({"id/{myId}"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long myId) {
       Optional<Employee> emp= emsServices.getById(myId);
        return emp.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping({"id/{myId}"})
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long myId) {
        emsServices.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"id/{myId}"})
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long myId, @RequestBody Employee employeeEntry) {
        Employee updatedEmployee=emsServices.updateById(myId,employeeEntry);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }
}


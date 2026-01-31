package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.services.EMSServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee_Details")
public class EMSController {

    @Autowired
    private EMSServices emsServices;


    @PostMapping
    public boolean createEmployee(@RequestBody Employee employeeEntry) {
        emsServices.saveEmployee(employeeEntry);
        return true;
    }

    @GetMapping
    public List<Employee> getAll() {
        return emsServices.getAll();
    }


    @GetMapping({"id/{myId}"})
    public Employee getEmployeeById(@PathVariable Long myId) {
        return  emsServices.getById(myId).orElse(null);
    }

    @DeleteMapping({"id/{myId}"})
    public boolean deleteEmployeeById(@PathVariable Long myId) {
        emsServices.deleteById(myId);
        return true;
    }

    @PutMapping({"id/{myId}"})
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long myId, @RequestBody Employee employeeEntry) {
        Employee updatedEmployee=emsServices.updateById(myId,employeeEntry);
        return ResponseEntity.ok(updatedEmployee);
    }
}


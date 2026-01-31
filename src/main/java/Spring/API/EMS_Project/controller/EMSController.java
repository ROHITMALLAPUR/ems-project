package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.services.EMSServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee_Details")
public class EMSController {

    @Autowired
    private EMSServices emsServices;

    @GetMapping
    public List<Employee> getAll() {
        return null;
    }

    @PostMapping
    public boolean createEmployee(@RequestBody Employee employeeEntry) {
        emsServices.saveEmployee(employeeEntry);
        return true;
    }

    @GetMapping({"/Id/{myId}"})
    public Employee getEmployeeById(@PathVariable Long myId) {
        return  null;
    }

    @DeleteMapping({"/Id/{myId}"})
    public Employee deleteEmployeeById(@PathVariable Long myId) {
        return null;
    }

    @PutMapping({"/Id/{myId}"})
    public Employee updateEmployeeById(@PathVariable Long myId, @RequestBody Employee employeeEntry) {
        return null;
    }
}


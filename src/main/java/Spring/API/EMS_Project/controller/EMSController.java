package Spring.API.EMS_Project.controller;



import Spring.API.EMS_Project.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee-DB")
public class EMSController {


    @GetMapping
    public List<Employee> getAll() {
        return null;
    }

    @PostMapping
    public boolean createEmployee(@RequestBody Employee employeeEntry) {

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


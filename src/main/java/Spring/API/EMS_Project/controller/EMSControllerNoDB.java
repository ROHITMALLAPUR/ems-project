package Spring.API.EMS_Project.controller;

import Spring.API.EMS_Project.entity.Employee;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Employee")
public class EMSControllerNoDB {
    private Map<Long, Employee> employeeData = new HashMap();

    @GetMapping
    public List<Employee> getAll() {
        return new ArrayList(this.employeeData.values());
    }

    @PostMapping
    public boolean createEmployee(@RequestBody Employee employeeEntry) {
        this.employeeData.put(employeeEntry.getId(), employeeEntry);
        return true;
    }

    @GetMapping({"/Id/{myId}"})
    public Employee getEmployeeById(@PathVariable Long myId) {
        return (Employee)this.employeeData.get(myId);
    }

    @DeleteMapping({"/Id/{myId}"})
    public Employee deleteEmployeeById(@PathVariable Long myId) {
        return (Employee)this.employeeData.remove(myId);
    }

    @PutMapping({"/Id/{myId}"})
    public Employee updateEmployeeById(@PathVariable Long myId, @RequestBody Employee employeeEntry) {
        return (Employee)this.employeeData.put(myId, employeeEntry);
    }
}

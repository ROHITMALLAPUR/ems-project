package Spring.API.EMS_Project.repository;

import Spring.API.EMS_Project.entity.Employee;
import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMSRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartmentIgnoreCase(
        String department,
        Pageable pageable
    );

    Page<Employee> findBySalaryBetween(
         Double minSalary,
         Double maxSalary,
         Pageable pageable
    );

    Page<Employee> findByRole(
      Role role,
      Pageable pageable
    );

    Page<Employee> findByStatus(
            Status status,
            Pageable pageable
    );

    Page<Employee> findByDepartmentIgnoreCaseAndSalaryBetweenAndRoleAndStatus(
            String department,
            Double minSalary,
            Double maxSalary,
            Role role,
            Status status,
            Pageable pageable
    );

    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable
    );
}

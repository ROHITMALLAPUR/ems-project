package Spring.API.EMS_Project.repository;

import Spring.API.EMS_Project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMSRepository extends JpaRepository<Employee, Long> {

}

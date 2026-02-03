package Spring.API.EMS_Project.dto;

import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private long id;
    private String name;
    private String email;
    private String department;
    private long salary;
    private LocalDate dateofJoining;
    private Role role;
    private Status status;

}

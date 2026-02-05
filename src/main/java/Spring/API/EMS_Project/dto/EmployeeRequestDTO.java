package Spring.API.EMS_Project.dto;

import Spring.API.EMS_Project.entity.Role;
import Spring.API.EMS_Project.entity.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

   @NotBlank
   @Size(min = 3,max = 50)
    private String name;

   @Email
   @NotBlank
    private String email;

   @NotBlank
    private String department;

   @NotNull
   @Min(1000)
    private long salary;


    private LocalDate dateofJoining;
    private Role role;
    private Status status;
}

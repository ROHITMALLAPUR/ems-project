package Spring.API.EMS_Project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Management System API",
                description = "Rest APIs for managing Employee Data",
                contact = @Contact(
                        name = "Rohit",
                        email = "rohitmallapur167@gmail.com"
                ),
                license = @License(name = "Apache 2.0")
        )
)

public class SwaggerConfig {
}

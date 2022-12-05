package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FresherCreateRequest {

    @NotBlank(message = "Name cannot be Empty!")
    private String name;

    @NotNull(message = "Date of Birth cannot be Empty!")
    private LocalDate dob;

    private String address;

    private String phone;

    @NotBlank(message = "Email cannot be Empty!")
    @Email
    private String email;
}

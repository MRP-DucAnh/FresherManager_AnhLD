package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FresherReturnResponse {
    private Long id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;


}

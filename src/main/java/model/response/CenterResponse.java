package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CenterResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate dob;
    private String address;

}

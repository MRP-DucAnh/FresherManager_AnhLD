package model.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
public record FresherResponse(Long fresherId, String fresherName, LocalDate dob
        , String address, String phone, String email) {

    @Override
    public Long fresherId() {
        return fresherId;
    }

    @Override
    public String fresherName() {
        return fresherName;
    }

    @Override
    public LocalDate dob() {
        return dob;
    }

    @Override
    public String address() {
        return address;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String email() {
        return email;
    }
}
package model.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FresherResponse(Long fresherId, String fresherName, LocalDate dob
        , String address, String phone, String email) {

}

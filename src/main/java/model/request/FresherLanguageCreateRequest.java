package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FresherLanguageCreateRequest {

    @NotBlank(message = "FresherID cannot be empty!")
    private Long fresherID;

    @NotBlank(message = "LanguageID cannot be empty!")
    private Long programmingLanguageID;

}

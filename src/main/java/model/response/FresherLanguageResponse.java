package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FresherLanguageResponse {
    private Long languageId;
    private String languageName;
    private Long fresherId;
    private String fresherName;
}
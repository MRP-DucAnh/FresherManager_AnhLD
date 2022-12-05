package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CenterFresherResponse {
    private Long centerId;
    private String centerName;
    private Long fresherId;
    private String fresherName;
}

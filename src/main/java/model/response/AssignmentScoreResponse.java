package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AssignmentScoreResponse {
    private  Long fresherId;
    private String fresherName;
    private Integer avgScore;
    private List<ScoreResponse> scoreResponseList;

    @Getter
    @Setter
    @Builder
    public static  class ScoreResponse{
        private  Long assignmentId;
        private Integer score;
        private Integer percentage;

    }

}

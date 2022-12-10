package com.vmo.fresher.FresherManager_AnhLD.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private  String status;
    private String message;
    private Object data;

   // public  ResponseObject(){}

}

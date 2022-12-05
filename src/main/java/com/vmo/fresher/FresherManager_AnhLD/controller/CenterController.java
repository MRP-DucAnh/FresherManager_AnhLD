package com.vmo.fresher.FresherManager_AnhLD.controller;


import com.vmo.fresher.FresherManager_AnhLD.entity.Center;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.service.CenterFresherService;
import com.vmo.fresher.FresherManager_AnhLD.service.CenterService;
import lombok.RequiredArgsConstructor;
import model.request.CenterCreateRequest;
import model.response.CenterFresherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;
    private final CenterFresherService centerFresherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Center> createCenter(@RequestBody @Valid CenterCreateRequest centerCreateRequest) {
        return ResponseEntity.ok(centerService.createCenter(centerCreateRequest));
    }

    //Get all Center
    @GetMapping("/all") //localhost:8080/fm/api/v1/freshers/all
    public ResponseEntity<List<String>> findAllCenter(){
        return ResponseEntity.ok(centerService.findAllCenters());
    }
    //Get detail Center
    @GetMapping("/{id}")
    public  ResponseEntity<ResponseObject> findById(@PathVariable Long id){
       return  ResponseEntity.status(HttpStatus.OK).body(centerService.findByID(id));

   //     return  ResponseEntity.ok((centerService.findByID(id)));


    }

    //Update Center
    @PutMapping("/{id}")
    public  ResponseEntity<ResponseObject> updateCenter(@RequestBody Center newCenter, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(centerService.updateCenter(newCenter, id));
    }
    //Delete Center by Id
    @DeleteMapping("/{id}")
    public  ResponseEntity<ResponseObject> deleteCenter(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(centerService.deleteCenter(id));
    }

    @GetMapping("/{centerId}/freshers")
    public ResponseEntity<List<CenterFresherResponse>> getAllFresherByCenter(@PathVariable Long centerId) {
        return ResponseEntity.ok(centerFresherService.findAllFreshersByCenterId(centerId));
    }
}

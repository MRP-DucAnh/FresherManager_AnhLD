package com.vmo.fresher.FresherManager_AnhLD.controller;


import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.service.AssignmentScoreService;
import com.vmo.fresher.FresherManager_AnhLD.service.FresherLanguageService;
import com.vmo.fresher.FresherManager_AnhLD.service.FresherService;
import lombok.RequiredArgsConstructor;
import model.request.FresherCreateRequest;
import model.response.AssignmentScoreResponse;
import model.response.FresherLanguageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/freshers")
@RequiredArgsConstructor
public class FresherController {

    private final FresherService fresherService;
    private final AssignmentScoreService assignmentScoreService;
    private  final FresherLanguageService fresherLanguageService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fresher> createFresher(@RequestBody @Valid FresherCreateRequest fresherCreateRequest) {
        Fresher savedFresher = fresherService.createFresher(fresherCreateRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFresher.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @GetMapping("/countAll") //localhost:8080/fm/api/v1/freshers/all
    public ResponseEntity<Integer> countAllFreshers(){
        return ResponseEntity.ok(fresherService.countAllFresher());
    }
    @GetMapping("/all") //localhost:8080/fm/api/v1/freshers/all
    public ResponseEntity<List<String>> findAllFreshers(){
        return ResponseEntity.ok(fresherService.findAllFresher());
    }

    @GetMapping("/nameHave/{name}")
    public  ResponseEntity<ResponseObject> findFresherByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(fresherService.findByName(name));
    }

    @GetMapping("/emailHave/{email}")
    public  ResponseEntity<ResponseObject> findFresherByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(fresherService.findByEmail(email));
    }


    @PutMapping("/{id}")
    public  ResponseEntity<ResponseObject> UpdateFresher (@RequestBody Fresher newFresher,@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(fresherService.UpdateFresher(newFresher,id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(fresherService.deleteFresher(id));
    }

    @GetMapping("/{id}/freshermarks")
    public ResponseEntity<List<AssignmentScoreResponse.ScoreResponse>> findAssignmentScoreByFrehserid(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(assignmentScoreService.findAllAssignmentByFresherId(id));
    }

    @GetMapping("/{id}/freshersavgmark")
    public  ResponseEntity<ResponseObject> findFresherAverageMark (@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(assignmentScoreService.averageScore(id));
    }

    @GetMapping("/scoreGreater/{score}")
    public ResponseEntity<ResponseObject> findAssignmentScoreByFrehsermark(@PathVariable int score){
        return ResponseEntity.status(HttpStatus.OK).body(assignmentScoreService.findAllFresherByScore(score));
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<FresherLanguageResponse>> findByLanguageName(@PathVariable String language){
        return ResponseEntity.ok(fresherLanguageService.findAllFresherByLanguage(language));
    }

    @GetMapping("/languageID/{languageID}")
    public ResponseEntity<List<FresherLanguageResponse>> findByLanguage(@PathVariable Long languageID){
        return ResponseEntity.ok(fresherLanguageService.findAllFresherByLanguageID(languageID));
    }
}
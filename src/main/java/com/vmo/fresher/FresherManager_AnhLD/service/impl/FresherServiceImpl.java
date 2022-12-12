package com.vmo.fresher.FresherManager_AnhLD.service.impl;


import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.FresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherCreateRequest;
import model.response.FresherResponse;
import model.response.FresherReturnResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final FresherRepository fresherRepository;

    @Override
    @Transactional
    public Fresher createFresher(FresherCreateRequest fresherCreateRequest) {
        var fresher = Fresher.builder()
                .name(fresherCreateRequest.getName())
                .phone(fresherCreateRequest.getPhone())
                .email(fresherCreateRequest.getEmail())
                .dob(fresherCreateRequest.getDob())
                .address(fresherCreateRequest.getAddress())
                .build();
        return fresherRepository.save(fresher);
    }


    @Override
    public int countAllFresher() {
        return fresherRepository.findAll().size();
    }


    @Override
    public FresherResponse findById(Long fresherId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("Fresher Not Found!")
                        .entityName("Fresher")
                        .fieldName("Id")
                        .fieldValue(fresherId)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()));


        return new FresherResponse(fresher.getId(),fresher.getName(),fresher.getDob(),fresher.getAddress(),fresher.getPhone(),fresher.getEmail());

    }



    @Override
    public List<String> findAllFresher() {
        if(fresherRepository.findAll().stream().map(Fresher::getName).collect(Collectors.toList()).isEmpty())throw
                new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("your database is empty No Fresher found")
                        .entityName("Fresher")
                        .fieldName("fresher")

                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
        return fresherRepository.findAll().stream().map(Fresher::getName).collect(Collectors.toList());
    }

    @Override
    public ResponseObject findByName(String name) {
     //   List<Fresher> foundFresher = fresherRepository.findFresherByNameContaining(name);
        List<FresherReturnResponse> list = fresherRepository.findFresherByNameContaining(name).stream()
                .map(fresher -> FresherReturnResponse.builder()
                        .name(fresher.getName())
                        .id(fresher.getId())
                        .email(fresher.getEmail())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .phone(fresher.getPhone())
                .build()).collect(Collectors.toList());

        if(list.isEmpty()){
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ name +"' did not match any freshser.")
                            .entityName("Fresher")
                            .fieldName("name")
                            .fieldValue(name)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
           // return  new ResponseObject("Failed","not found any fresher !","");
        }
        else return  new ResponseObject(HttpStatus.FOUND.toString()/*"found "+list.size()*/,"Found "+list.size()+" freshers contain \"" + name+"\"",list.stream().collect(Collectors.toList()));

    }
    @Override
    public ResponseObject findByEmail(String email) {
        List<FresherReturnResponse> list = fresherRepository.findFresherByEmailContaining(email).stream()
                .map(fresher -> FresherReturnResponse.builder()
                        .name(fresher.getName())
                        .id(fresher.getId())
                        .email(fresher.getEmail())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .phone(fresher.getPhone())
                        .build()).collect(Collectors.toList());

        if(list.isEmpty()){
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ email +"' did not match any freshser.")
                            .entityName("Fresher")
                            .fieldName("name")
                            .fieldValue(email)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
            // return  new ResponseObject("Failed","not found any fresher !","");
        }
        else return  new ResponseObject(HttpStatus.FOUND.toString()/*"found "+list.size()*/,"Found "+list.size()+" fresher's emails contain \"" + email+"\"",list.stream().collect(Collectors.toList()));
    }

//    @Override
//    public FresherResponse updateFresher(Fresher newFresher, Long fresherId) {
//        Fresher updatedFresher = fresherRepository.findById(fresherId).map(fresher -> {
//            fresher.setName(newFresher.getName());
//            fresher.setDob(newFresher.getDob());
//            fresher.setAddress(newFresher.getAddress());
//            fresher.setEmail(newFresher.getEmail());
//            fresher.setPhone(newFresher.getPhone());
//            return fresherRepository.save(fresher);
//        })
//                .orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
//                        .message("Fresher not found")
//                        .entityName("Fresher")
//                        .fieldName("Id")
//                        .fieldValue(fresherId)
//                        .httpStatus(HttpStatus.NOT_FOUND)
//                        .build()));
//        FresherResponse f =FresherResponse.builder().build().fresherId(updatedFresher.getId())
//                .fresherName(updatedFresher.getName())
//                .address(updatedFresher.getAddress())
//                .phone(fresher.getPhone())
//                .email(fresher.getEmail())
//                .dob(fresher.getDob())
//        return f;
//    }

    @Override
    public ResponseObject UpdateFresher(Fresher newFresher,Long id) {
        Fresher updatedFresher = fresherRepository.findById(id)
                .map(fresher -> {
                    fresher.setName(newFresher.getName());
                    fresher.setDob(newFresher.getDob());
                    fresher.setAddress(newFresher.getAddress());
                    fresher.setPhone(newFresher.getPhone());
                    fresher.setEmail(newFresher.getEmail());
                 //   fresher.setCenterFresherList(newFresher.getCenterFresherList());
                    return  fresherRepository.save(fresher);
                }).orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("Fresher does not exist! Update Failed !")
                        .entityName("Fresher")
                        .fieldName("Id")
                        .fieldValue(id)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()));
//                .orElseGet(()->{
//                    newFresher.setId(id);
//                    return fresherRepository.save(newFresher);
//                });

        FresherReturnResponse f =  FresherReturnResponse.builder().id(id)
                .name(updatedFresher.getName())
                .dob(updatedFresher.getDob())
                .email(updatedFresher.getEmail())
                .phone(updatedFresher.getPhone())
                .address(updatedFresher.getAddress())
                .build();
        return new ResponseObject(HttpStatus.OK.toString(), "Update Fresher successfully",f);
    }

    @Override
    public ResponseObject deleteFresher(Long id) {
        boolean exists = fresherRepository.existsById(id);
        if(exists){
            fresherRepository.deleteById(id);
            return new ResponseObject("deleted","Fresher Id does exist !","");
        }else
            throw new EntityNotFoundException(ApiErrorDetail.builder()
                    .message("Cannot find fresher to delete!")
                    .entityName("Fresher")
                    .fieldName("Id")
                    .fieldValue("Id")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build());
//            return  new
//                ResponseObject("failed","Cannot find fresher to delete","");

    }



//    @Override
//    public ResponseObject findByEmail(String email) {
//        List<Fresher> a = fresherRepository.findFresherByEmailContaining(email);
//        return null;
//    }


}

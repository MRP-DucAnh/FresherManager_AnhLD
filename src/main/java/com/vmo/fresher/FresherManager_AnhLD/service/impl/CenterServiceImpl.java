package com.vmo.fresher.FresherManager_AnhLD.service.impl;


import com.vmo.fresher.FresherManager_AnhLD.entity.Center;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    @Transactional
    @Override
    public Center createCenter(CenterCreateRequest centerCreateRequest) {
        var center = Center.builder()
                .name(centerCreateRequest.getName())
                .code(centerCreateRequest.getCode())
                .address(centerCreateRequest.getAddress())
                .dob(centerCreateRequest.getDob())
                .build();
        return centerRepository.save(center);
    }

    @Override
    public List<String> findAllCenters() {
        return centerRepository.findAll().stream().map(Center::getName).collect(Collectors.toList());
    }

    @Override
    public ResponseObject findByID(Long id) {
        Optional<Center> foundCenter = centerRepository.findById(id);
        if(foundCenter.isPresent())
        {return  new ResponseObject("found","Center does exist !",foundCenter);}
        else
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ id +"' did not match any center.")
                            .entityName("Fresher")
                            .fieldName("id")
                            .fieldValue(id)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
            //return  new ResponseObject("not found","Center doesn't exist !","");
    }

    @Override
    public ResponseObject updateCenter(Center newCenter, Long id) {
        Center updatedCenter = centerRepository.findById(id)
                .map(center -> {
                  //  center.setId(newCenter.getId());
                    center.setName(newCenter.getName());
                    center.setCode(newCenter.getCode());
                    center.setDob(newCenter.getDob());
                    center.setAddress(newCenter.getAddress());
                    center.setCenterFresherList(newCenter.getCenterFresherList());
                    return centerRepository.save(center);
                }).orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("Center id '"+id+ "'does not exist! Update Failed !")
                        .entityName("Center")
                        .fieldName("Id")
                        .fieldValue(id)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()));
//                .orElseGet(()->{
//                    newCenter.setId(id);
//                    return  centerRepository.save(newCenter);
    //            });

        return new ResponseObject("Updated", "Update Center succesfully",updatedCenter);
    }

    @Override
    public ResponseObject deleteCenter(Long id) {
        boolean exists = centerRepository.existsById(id);
        if (exists){
            centerRepository.deleteById(id);
            return  new ResponseObject("deleted","Delete Center has id "+id+" succesfully","");

        }else throw new EntityNotFoundException(ApiErrorDetail.builder()
                .message("Cannot find center with id: '"+id+"' to delete!")
                .entityName("Center")
                .fieldName("Id")
                .fieldValue("Id")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
            //return new ResponseObject("failed","Cannot find Fresher with ID: "+id,"");
    }


}

package com.vmo.fresher.FresherManager_AnhLD.service;


import com.vmo.fresher.FresherManager_AnhLD.entity.Center;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import model.request.CenterCreateRequest;
import model.response.CenterResponse;

import java.util.List;

public interface CenterService {

    Center createCenter(CenterCreateRequest centerCreateRequest);

    //13/11
    List<String> findAllCenters();
    ResponseObject findByID(Long id);

    //ResponseObject updateCenter(Center newCenter, Long id);
    CenterResponse updateCenter(Center newCenter, Long id);
    ResponseObject deleteCenter(Long id);
}

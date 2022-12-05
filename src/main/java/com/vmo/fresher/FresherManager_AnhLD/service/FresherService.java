package com.vmo.fresher.FresherManager_AnhLD.service;


import com.vmo.fresher.FresherManager_AnhLD.entity.Fresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import model.request.FresherCreateRequest;

import java.util.List;

public interface FresherService {

    Fresher createFresher(FresherCreateRequest fresherCreateRequest);

    List<String> findAllFresher();

    ResponseObject findByName(String name);
 //   List<Fresher>findByEmail(String email);
    ResponseObject UpdateFresher(Fresher newFresher,Long id);

    ResponseObject deleteFresher(Long id);

    ResponseObject findByEmail(String email);

    int countAllFresher();


}

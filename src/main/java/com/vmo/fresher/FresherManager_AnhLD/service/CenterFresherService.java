package com.vmo.fresher.FresherManager_AnhLD.service;

import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;

import java.util.List;

public interface CenterFresherService {

    List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId);
    ResponseObject addFresherToCenter(CenterFresherCreateRequest centerFresherCreateRequest);
}

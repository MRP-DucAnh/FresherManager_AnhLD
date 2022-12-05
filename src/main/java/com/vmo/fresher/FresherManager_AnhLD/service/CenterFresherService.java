package com.vmo.fresher.FresherManager_AnhLD.service;

import model.response.CenterFresherResponse;

import java.util.List;

public interface CenterFresherService {

    List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId);
}

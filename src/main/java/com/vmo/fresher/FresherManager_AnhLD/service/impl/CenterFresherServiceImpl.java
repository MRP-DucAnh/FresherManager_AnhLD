package com.vmo.fresher.FresherManager_AnhLD.service.impl;

import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterFreshRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.CenterFresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.response.CenterFresherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterFresherServiceImpl implements CenterFresherService {

    private final CenterFreshRepository centerFreshRepository;

    @Override
    public List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId) {
        List<CenterFresherResponse> list =
         centerFreshRepository.findAllByCenterId(centerId).stream()
                .map(centerFresher -> CenterFresherResponse.builder()
                        .centerId(centerFresher.getCenter().getId())
                        .centerName(centerFresher.getCenter().getName())
                        .fresherId(centerFresher.getFresher().getId())
                        .fresherName(centerFresher.getFresher().getName()).build())
                .collect(Collectors.toList());
        if(list.isEmpty()) throw new  EntityNotFoundException(ApiErrorDetail.builder()
                .message("No Center found !")
                .entityName("CenterFresher")
                .fieldName("Id")
                .fieldValue(centerId)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build());
        else  return list;
    }

}

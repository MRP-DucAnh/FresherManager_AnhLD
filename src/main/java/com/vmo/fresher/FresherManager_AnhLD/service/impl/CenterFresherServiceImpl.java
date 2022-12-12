package com.vmo.fresher.FresherManager_AnhLD.service.impl;

import com.vmo.fresher.FresherManager_AnhLD.entity.CenterFresher;
import com.vmo.fresher.FresherManager_AnhLD.entity.ResponseObject;
import com.vmo.fresher.FresherManager_AnhLD.exception.ApiErrorDetail;
import com.vmo.fresher.FresherManager_AnhLD.exception.EntityNotFoundException;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterFreshRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.CenterRepository;
import com.vmo.fresher.FresherManager_AnhLD.repository.FresherRepository;
import com.vmo.fresher.FresherManager_AnhLD.service.CenterFresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterFresherServiceImpl implements CenterFresherService {

    private final CenterFreshRepository centerFreshRepository;
    private final CenterRepository centerRepository;
    private final FresherRepository fresherRepository;

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

    @Transactional
    @Override
    public ResponseObject addFresherToCenter(CenterFresherCreateRequest centerFresherCreateRequest) {
        boolean fresherExist = fresherRepository.existsById(centerFresherCreateRequest.getFresherID());
        boolean centerExist = centerRepository.existsById(centerFresherCreateRequest.getCenterID());

        if (!fresherExist) {
            return new ResponseObject("Failed","Cannot find fresher has id = "+centerFresherCreateRequest.getFresherID(),"");
        } else if (!centerExist) {
            return new ResponseObject("Failed","Cannot find center has id = "+centerFresherCreateRequest.getCenterID(),"");
        } else {
            CenterFresher centerFresher = CenterFresher.builder()
                    .center(centerRepository.findById(centerFresherCreateRequest.getCenterID()).get())
                    .fresher(fresherRepository.findById(centerFresherCreateRequest.getFresherID()).get())
                    .startDate(centerFresherCreateRequest.getStartDate())
                    .endDate(centerFresherCreateRequest.getEndDate())
                    .build();
            centerFreshRepository.save(centerFresher);
            return new ResponseObject("Successed",
                    "Fresher has id = \'" + centerFresherCreateRequest.getFresherID()+"\' has been added to center has id = \'"+centerFresherCreateRequest.getCenterID()+"\'"
                    ,fresherRepository.findById(centerFresherCreateRequest.getFresherID()).get().getName() +" has been added");
        }
    }

}

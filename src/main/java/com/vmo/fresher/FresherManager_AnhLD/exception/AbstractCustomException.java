package com.vmo.fresher.FresherManager_AnhLD.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AbstractCustomException extends RuntimeException {

    protected final ApiErrorDetail apiErrorDetail;
}

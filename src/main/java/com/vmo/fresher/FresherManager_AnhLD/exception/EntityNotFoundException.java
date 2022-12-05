package com.vmo.fresher.FresherManager_AnhLD.exception;

public class EntityNotFoundException extends AbstractCustomException{

    public EntityNotFoundException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}

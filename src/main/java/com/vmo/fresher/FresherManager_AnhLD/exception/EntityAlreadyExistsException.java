package com.vmo.fresher.FresherManager_AnhLD.exception;

public class EntityAlreadyExistsException  extends AbstractCustomException {
    public EntityAlreadyExistsException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}

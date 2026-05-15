package com.project.score_service.expection;

public class CreditScoreNotFound  extends RuntimeException{
    public CreditScoreNotFound(String message) {

        super(message);

    }
}

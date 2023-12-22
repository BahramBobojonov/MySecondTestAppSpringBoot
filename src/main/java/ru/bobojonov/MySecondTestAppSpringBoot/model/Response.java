package ru.bobojonov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    // Уникальный идентификатор сообщения
    private String uid;
    // Уникальный идентификатор операции
    private String operationUid;
    // Время создания сообщения
    private String systemTime;
    // Имя системы отправителя
    private Codes code;
    private Double annualBonus;
    // Наименование ресурса
    private ErrorCodes errorCode;
    // Сообщение об ошибке
    private ErrorMessages errorMessage;
}
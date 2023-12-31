package ru.bobojonov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bobojonov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.bobojonov.MySecondTestAppSpringBoot.model.*;
import ru.bobojonov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.bobojonov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.bobojonov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.bobojonov.MySecondTestAppSpringBoot.util.DateTimeUtil;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("modifySystemTimeResponseService") ModifyResponseService modifyResponseService, @Qualifier("modifySystemNameRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("request: {}", request);
        long startTime = System.currentTimeMillis();
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        try {
            request.checkUid();
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Произошла неизвестная ошибка: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("Ответ: {}", response);
        modifyRequestService.modify(request);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("Время выполнения в миллисекундах: {}", executionTime);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}
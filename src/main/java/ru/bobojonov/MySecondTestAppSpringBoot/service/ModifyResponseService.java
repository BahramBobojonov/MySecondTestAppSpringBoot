package ru.bobojonov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.bobojonov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
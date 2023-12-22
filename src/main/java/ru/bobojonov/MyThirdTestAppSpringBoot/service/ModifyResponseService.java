package ru.bobojonov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.bobojonov.MyThirdTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
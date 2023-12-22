package ru.bobojonov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.bobojonov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
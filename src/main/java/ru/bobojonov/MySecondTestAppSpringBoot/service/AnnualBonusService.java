package ru.bobojonov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.bobojonov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.bobojonov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus) throws UnsupportedCodeException;
}
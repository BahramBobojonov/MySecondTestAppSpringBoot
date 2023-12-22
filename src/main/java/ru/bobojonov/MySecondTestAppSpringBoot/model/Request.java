package ru.bobojonov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bobojonov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /***
     * Уникальный индетификатор сообщения
     */
    @NotBlank(message = "uid не может быть пустым!")
    @Size(max = 32)
    private String uid;
    @NotBlank
    @Size(max = 32)
    private String operationUid; // Уникальный идентификатор операции
    private Systems systemName; // Имя системы отправителя
    @NotBlank
    private String systemTime; // Время создания сообщения
    private String source; // Наименование ресурса
    private Positions positions; // Должность
    private Double salary; // Зарплата
    private Double bonus; // Премия
    private Integer workDays; // Рабочие дни
    @Min(1)
    @Max(100000)
    private Integer communicationId; // Уникальный идентификатор коммуникации
    private Integer templateId; // Уникальный идентификатор шаблона
    private Integer productCode; // Код продукта
    private Integer smsCode; // Смс код

    public void checkUid() throws UnsupportedCodeException {
        if ("123".equals(uid)) {
            throw new UnsupportedCodeException("Unsupported uid: " + uid);
        }
    }
    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                "operationUid='" + operationUid + '\'' +
                "systemName='" + systemName + '\'' +
                "systemTime='" + systemTime + '\'' +
                "source'" + source + '\'' +
                "positions'" + positions + '\'' +
                "salary'" + salary + '\'' +
                "bonus'" + bonus + '\'' +
                "workDays'" + workDays + '\'' +
                "communicationId=" + communicationId +
                "templateId=" + templateId +
                "productCode=" + productCode +
                "smsCode" + smsCode +
                "}";
    }
}
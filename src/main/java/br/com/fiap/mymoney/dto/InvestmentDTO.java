package br.com.fiap.mymoney.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class InvestmentDTO {
    @NotBlank
    private String name;

    @NotNull
    private float amount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private int percentagePerMonth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPercentagePerMonth() {
        return percentagePerMonth;
    }

    public void setPercentagePerMonth(int percentagePerMonth) {
        this.percentagePerMonth = percentagePerMonth;
    }
}

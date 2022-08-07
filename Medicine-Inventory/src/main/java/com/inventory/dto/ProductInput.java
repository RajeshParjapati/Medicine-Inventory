package com.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {

    private String code;
    private String name;
    private String batch;
    private String stock;
    private String deal;
    private String free;
    private String mrp;
    private String rate;
    private String exp;
    private String company;
    private String supplier;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "ProductInput{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                ", stock='" + stock + '\'' +
                ", deal='" + deal + '\'' +
                ", free='" + free + '\'' +
                ", mrp='" + mrp + '\'' +
                ", rate='" + rate + '\'' +
                ", exp='" + exp + '\'' +
                ", company='" + company + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}

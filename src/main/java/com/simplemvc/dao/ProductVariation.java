package com.simplemvc.dao;

public class ProductVariation {
    private String variationName;

    private Long remainingStock;

    public ProductVariation() {
    }

    public ProductVariation(String variationName, Long remainingStock) {
        this.variationName = variationName;
        this.remainingStock = remainingStock;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public Long getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(Long remainingStock) {
        this.remainingStock = remainingStock;
    }
}

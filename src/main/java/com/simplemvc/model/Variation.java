package com.simplemvc.model;

import javax.persistence.*;

@Entity
@Table(name = "variation_table")
public class Variation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variationId;

    private String variationName;

    private Long remainingStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Variation() {
    }

    public Variation(String variationName, Long remainingStock, Product product) {
        this.variationName = variationName;
        this.remainingStock = remainingStock;
        this.product = product;
    }

    public Long getVariationId() {
        return variationId;
    }

    public void setVariationId(Long variationId) {
        this.variationId = variationId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

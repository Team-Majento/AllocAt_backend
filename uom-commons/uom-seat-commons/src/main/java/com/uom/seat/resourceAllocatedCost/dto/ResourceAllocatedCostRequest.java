package com.uom.seat.resourceAllocatedCost.dto;

public class ResourceAllocatedCostRequest {
    private Double calclated_cost;
    private Double final_cost;
    private Double discount;

    public Double getCalclated_cost() {
        return calclated_cost;
    }

    public void setCalclated_cost(Double calclated_cost) {
        this.calclated_cost = calclated_cost;
    }

    public Double getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(Double final_cost) {
        this.final_cost = final_cost;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}

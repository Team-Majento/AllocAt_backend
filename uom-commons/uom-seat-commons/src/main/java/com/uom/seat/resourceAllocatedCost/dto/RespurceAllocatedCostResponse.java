package com.uom.seat.resourceAllocatedCost.dto;

public class RespurceAllocatedCostResponse {


    private Integer id;
    private Double calclated_cost;
    private Double final_cost;
    private Double discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

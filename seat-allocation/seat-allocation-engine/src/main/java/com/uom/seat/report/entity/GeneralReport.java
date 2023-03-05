package com.uom.seat.report.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "general_report")
public class GeneralReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer company_id;
    private Integer resource_id;
    private Integer requester_id;
    private LocalDate required_date;
    private String usage_in_hours_and_minutes;
    private Double units;
    private Double allocated_cost;
    private Double discount_rate;
    private Double discount;
    private Double final_cost;
    private Double total_allocated_cost;
    private String company_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Integer getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(Integer requester_id) {
        this.requester_id = requester_id;
    }

    public LocalDate getRequired_date() {
        return required_date;
    }

    public void setRequired_date(LocalDate required_date) {
        this.required_date = required_date;
    }

    public String getUsage_in_hours_and_minutes() {
        return usage_in_hours_and_minutes;
    }

    public void setUsage_in_hours_and_minutes(String usage_in_hours_and_minutes) {
        this.usage_in_hours_and_minutes = usage_in_hours_and_minutes;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Double getAllocated_cost() {
        return allocated_cost;
    }

    public void setAllocated_cost(Double allocated_cost) {
        this.allocated_cost = allocated_cost;
    }

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinal_cost() {
        return final_cost;
    }

    public void setFinal_cost(Double final_cost) {
        this.final_cost = final_cost;
    }

    public Double getTotal_allocated_cost() {
        return total_allocated_cost;
    }

    public void setTotal_allocated_cost(Double total_allocated_cost) {
        this.total_allocated_cost = total_allocated_cost;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}

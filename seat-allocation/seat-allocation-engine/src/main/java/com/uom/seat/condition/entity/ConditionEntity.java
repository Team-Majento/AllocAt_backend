package com.uom.seat.condition.entity;


import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "condition_tbl")
public class ConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "condition_name")
    private String condition_name;

    @Column(name = "discount_rate")
    private Double discount_rate;

    @Column(name = "date")
    private LocalDate date;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "conditionEntity")
    private List<ResourceAllocationEntity> resourceAllocationEntity;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ResourceAllocationEntity> getResourceAllocationEntity() {
        return resourceAllocationEntity;
    }

    public void setResourceAllocationEntity(List<ResourceAllocationEntity> resourceAllocationEntity) {
        this.resourceAllocationEntity = resourceAllocationEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCondition_name() {
        return condition_name;
    }

    public void setCondition_name(String condition_name) {
        this.condition_name = condition_name;
    }

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }
}

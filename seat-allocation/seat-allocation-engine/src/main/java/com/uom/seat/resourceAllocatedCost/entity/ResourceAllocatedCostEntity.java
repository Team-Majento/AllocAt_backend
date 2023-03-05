package com.uom.seat.resourceAllocatedCost.entity;
import com.uom.seat.resourceAllocation.entity.ResourceAllocationEntity;
import javax.persistence.*;

@Entity
@Table(name = "resource_allocated_cost")
public class ResourceAllocatedCostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "calculated_cost")
    private Double calculated_cost;

    @Column(name = "final_cost")
    private Double final_cost;

    @Column(name = "discount")
    private Double discount;

   @Column(name="discount_rate")
   private Double discount_rate;


    @OneToOne(cascade = CascadeType.ALL,targetEntity = ResourceAllocationEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_allocation_FK",referencedColumnName = "id",nullable = false)
    private ResourceAllocationEntity resourceAllocationEntity;


    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public ResourceAllocationEntity getResourceAllocationEntity() {
        return resourceAllocationEntity;
    }

    public void setResourceAllocationEntity(ResourceAllocationEntity resourceAllocationEntity) {
        this.resourceAllocationEntity = resourceAllocationEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCalculated_cost() {
        return calculated_cost;
    }

    public void setCalculated_cost(Double calculated_cost) {
        this.calculated_cost = calculated_cost;
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

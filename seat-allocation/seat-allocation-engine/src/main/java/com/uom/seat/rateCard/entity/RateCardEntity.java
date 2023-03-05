package com.uom.seat.rateCard.entity;

import com.uom.seat.resource.entity.ResourceEntity;
import javax.persistence.*;

@Entity
@Table(name = "rate_card_details")
public class RateCardEntity {

    @Id
    @Column(name = "rate_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rateCardId;

    @Column(name = "unit", nullable = false)
    private Double unit;

    @Column(name = "hour_rate", nullable = false)
    private Double hourRate;

    @Column(name = "extra_hour_rate", nullable = false)
    private Double extraHourRate;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = ResourceEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id_fk",referencedColumnName = "id",nullable = true)
    private ResourceEntity resourceEntity;

    public Integer getRateCardId() {
        return rateCardId;
    }

    public void setRateCardId(Integer rateCardId) {
        this.rateCardId = rateCardId;
    }

    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    public Integer getId() {
        return rateCardId;
    }

    public void setId(Integer id) {
        this.rateCardId = rateCardId;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getHourRate() {
        return hourRate;
    }

    public void setHourRate(Double hourRate) {
        this.hourRate = hourRate;
    }

    public Double getExtraHourRate() {
        return extraHourRate;
    }

    public void setExtraHourRate(Double extraHourRate) {
        this.extraHourRate = extraHourRate;
    }
}

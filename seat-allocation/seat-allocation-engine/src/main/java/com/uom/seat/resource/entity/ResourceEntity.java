package com.uom.seat.resource.entity;


import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.rateCard.entity.RateCardEntity;
import com.uom.seat.review.entity.ReviewEntity;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//test
//test
@Entity
@Table(name = "resource")
public class ResourceEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    @Column(name = "xid", nullable = false, unique = true)
    private String xid;
    @Column(name = "resource_type_id", nullable = false)
    private Integer resourceType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "resourceEntity")
    private List<ReviewEntity> reviews = new ArrayList<>();

//    @Column(name = "rate_card_id", nullable = true)
//    private Integer rateCardId;

    @Column(name = "building_id", nullable = false)
    private String buildingId;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "is_active", nullable = false)
    private Boolean activeStatus;

    @Column(name = "maximum_capacity", nullable = false)
    private Integer maximumCapacity;
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "img_Url", nullable = true)
    private String imgUrl;

    @ManyToOne(targetEntity = CompanyEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyEntity company;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "resourceEntity")
    private RateCardEntity rateCardEntity;

    public RateCardEntity getRateCardEntity() {
        return rateCardEntity;
    }

    public void setRateCardEntity(RateCardEntity rateCardEntity) {
        this.rateCardEntity = rateCardEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

//    public Integer getRateCardId() {
//        return rateCardId;
//    }
//
//    public void setRateCardId(Integer rateCardId) {
//        this.rateCardId = rateCardId;
//    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Boolean getAvailability() {
        return isAvailable;
    }

    public void setAvailability(Boolean available) {
        isAvailable = available;
    }

    public Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }


    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }



}

//test
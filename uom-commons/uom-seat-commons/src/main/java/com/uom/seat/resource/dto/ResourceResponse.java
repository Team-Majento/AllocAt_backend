package com.uom.seat.resource.dto;

import java.io.Serializable;

public class ResourceResponse  implements Serializable {

    private static final long serialVersionUID = 5620757555467396010L;

    private Integer id;
    private Integer resourceType;
    private Integer rateCardId;
    private String buildingId;
    private Integer floor;
    private Boolean isAvailable;

    private Boolean activeStatus;
    private Integer maximumCapacity;
    private String description;
    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getRateCardId() {
        return rateCardId;
    }

    public void setRateCardId(Integer rateCardId) {
        this.rateCardId = rateCardId;
    }

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

    @Override
    public String toString() {
        return "ResourceResponse{" +
                "id=" + id +
                ", resourceType=" + resourceType +
                ", rateCardId=" + rateCardId +
                ", buildingId='" + buildingId + '\'' +
                ", floor=" + floor +
                ", isAvailable=" + isAvailable +
                ", activeStatus=" + activeStatus +
                ", maximumCapacity=" + maximumCapacity +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

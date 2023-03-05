package com.uom.seat.rateCard.dto;

public class RateCardResponse {

Integer rateCardId;
    Double unit;
    Double hourRate;
    Double extraHourRate;

    public Integer getRateCardId() {
        return rateCardId;
    }

    public void setRateCardId(Integer rateCardId) {
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


    @Override
    public String toString() {
        return "RateCardResponse{" +
                "rateCardId=" + rateCardId +
                ", unit=" + unit +
                ", hourRate=" + hourRate +
                ", extraHourRate=" + extraHourRate +
                '}';
    }
}

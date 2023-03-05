package com.uom.seat.report.entity;

public class GeneralReportSubReport {

    private Integer company_id;
    private String company_name;
    private Double total_cost_allocation;

    public GeneralReportSubReport(Integer company_id, String company_name, Double total_cost_allocation) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.total_cost_allocation = total_cost_allocation;
    }

    public Integer getCompany_id() {
        return company_id;

    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Double getTotal_cost_allocation() {
        return total_cost_allocation;
    }

    public void setTotal_cost_allocation(Double total_cost_allocation) {
        this.total_cost_allocation = total_cost_allocation;
    }
}

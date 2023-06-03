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
    private String company_name;
    private Double total_cost_allocation;

    private LocalDate from_date;

    private LocalDate to_date;

    private Integer no_of_reservations;

    public GeneralReport() {

    }

    public GeneralReport(Integer company_id, String company_name, Double total_cost_allocation) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.total_cost_allocation = total_cost_allocation;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public Integer getNo_of_reservations() {
        return no_of_reservations;
    }

    public void setNo_of_reservations(Integer no_of_reservations) {
        this.no_of_reservations = no_of_reservations;
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

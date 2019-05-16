package com.diviso.appointment.client.doctor.model;

import java.util.Objects;
import com.diviso.appointment.client.doctor.model.Doctor;
import com.diviso.appointment.client.doctor.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReservedSlot
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-15T12:28:37.199+05:30[Asia/Kolkata]")

public class ReservedSlot   {
  @JsonProperty("date")
  private LocalDate date = null;

  @JsonProperty("doctor")
  private Doctor doctor = null;

  @JsonProperty("endTime")
  private Double endTime = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("startTime")
  private Double startTime = null;

  @JsonProperty("statuses")
  @Valid
  private List<Status> statuses = null;

  @JsonProperty("tokenNumber")
  private Integer tokenNumber = null;

  public ReservedSlot date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public ReservedSlot doctor(Doctor doctor) {
    this.doctor = doctor;
    return this;
  }

  /**
   * Get doctor
   * @return doctor
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public ReservedSlot endTime(Double endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")


  public Double getEndTime() {
    return endTime;
  }

  public void setEndTime(Double endTime) {
    this.endTime = endTime;
  }

  public ReservedSlot id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ReservedSlot startTime(Double startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")


  public Double getStartTime() {
    return startTime;
  }

  public void setStartTime(Double startTime) {
    this.startTime = startTime;
  }

  public ReservedSlot statuses(List<Status> statuses) {
    this.statuses = statuses;
    return this;
  }

  public ReservedSlot addStatusesItem(Status statusesItem) {
    if (this.statuses == null) {
      this.statuses = new ArrayList<Status>();
    }
    this.statuses.add(statusesItem);
    return this;
  }

  /**
   * Get statuses
   * @return statuses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Status> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<Status> statuses) {
    this.statuses = statuses;
  }

  public ReservedSlot tokenNumber(Integer tokenNumber) {
    this.tokenNumber = tokenNumber;
    return this;
  }

  /**
   * Get tokenNumber
   * @return tokenNumber
  **/
  @ApiModelProperty(value = "")


  public Integer getTokenNumber() {
    return tokenNumber;
  }

  public void setTokenNumber(Integer tokenNumber) {
    this.tokenNumber = tokenNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservedSlot reservedSlot = (ReservedSlot) o;
    return Objects.equals(this.date, reservedSlot.date) &&
        Objects.equals(this.doctor, reservedSlot.doctor) &&
        Objects.equals(this.endTime, reservedSlot.endTime) &&
        Objects.equals(this.id, reservedSlot.id) &&
        Objects.equals(this.startTime, reservedSlot.startTime) &&
        Objects.equals(this.statuses, reservedSlot.statuses) &&
        Objects.equals(this.tokenNumber, reservedSlot.tokenNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, doctor, endTime, id, startTime, statuses, tokenNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservedSlot {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    doctor: ").append(toIndentedString(doctor)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    statuses: ").append(toIndentedString(statuses)).append("\n");
    sb.append("    tokenNumber: ").append(toIndentedString(tokenNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


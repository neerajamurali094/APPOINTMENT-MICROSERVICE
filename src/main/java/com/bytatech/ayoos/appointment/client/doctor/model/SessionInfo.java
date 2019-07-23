package com.bytatech.ayoos.appointment.client.doctor.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bytatech.ayoos.appointment.client.doctor.model.WorkPlace;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SessionInfo
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class SessionInfo   {
  @JsonProperty("date")
  private LocalDate date = null;

  @JsonProperty("fromTime")
  private Double fromTime = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("interval")
  private Double interval = null;

  @JsonProperty("sessionName")
  private String sessionName = null;

  @JsonProperty("toTime")
  private Double toTime = null;

  @JsonProperty("weekDay")
  private Integer weekDay = null;

  @JsonProperty("workPlace")
  private WorkPlace workPlace = null;

  public SessionInfo date(LocalDate date) {
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

  public SessionInfo fromTime(Double fromTime) {
    this.fromTime = fromTime;
    return this;
  }

  /**
   * Get fromTime
   * minimum: 0
   * @return fromTime
  **/
  @ApiModelProperty(value = "")

@DecimalMin("0")
  public Double getFromTime() {
    return fromTime;
  }

  public void setFromTime(Double fromTime) {
    this.fromTime = fromTime;
  }

  public SessionInfo id(Long id) {
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

  public SessionInfo interval(Double interval) {
    this.interval = interval;
    return this;
  }

  /**
   * Get interval
   * @return interval
  **/
  @ApiModelProperty(value = "")


  public Double getInterval() {
    return interval;
  }

  public void setInterval(Double interval) {
    this.interval = interval;
  }

  public SessionInfo sessionName(String sessionName) {
    this.sessionName = sessionName;
    return this;
  }

  /**
   * Get sessionName
   * @return sessionName
  **/
  @ApiModelProperty(value = "")


  public String getSessionName() {
    return sessionName;
  }

  public void setSessionName(String sessionName) {
    this.sessionName = sessionName;
  }

  public SessionInfo toTime(Double toTime) {
    this.toTime = toTime;
    return this;
  }

  /**
   * Get toTime
   * maximum: 23
   * @return toTime
  **/
  @ApiModelProperty(value = "")

 @DecimalMax("23") 
  public Double getToTime() {
    return toTime;
  }

  public void setToTime(Double toTime) {
    this.toTime = toTime;
  }

  public SessionInfo weekDay(Integer weekDay) {
    this.weekDay = weekDay;
    return this;
  }

  /**
   * Get weekDay
   * @return weekDay
  **/
  @ApiModelProperty(value = "")


  public Integer getWeekDay() {
    return weekDay;
  }

  public void setWeekDay(Integer weekDay) {
    this.weekDay = weekDay;
  }

  public SessionInfo workPlace(WorkPlace workPlace) {
    this.workPlace = workPlace;
    return this;
  }

  /**
   * Get workPlace
   * @return workPlace
  **/
  @ApiModelProperty(value = "")

  @Valid

  public WorkPlace getWorkPlace() {
    return workPlace;
  }

  public void setWorkPlace(WorkPlace workPlace) {
    this.workPlace = workPlace;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionInfo sessionInfo = (SessionInfo) o;
    return Objects.equals(this.date, sessionInfo.date) &&
        Objects.equals(this.fromTime, sessionInfo.fromTime) &&
        Objects.equals(this.id, sessionInfo.id) &&
        Objects.equals(this.interval, sessionInfo.interval) &&
        Objects.equals(this.sessionName, sessionInfo.sessionName) &&
        Objects.equals(this.toTime, sessionInfo.toTime) &&
        Objects.equals(this.weekDay, sessionInfo.weekDay) &&
        Objects.equals(this.workPlace, sessionInfo.workPlace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, fromTime, id, interval, sessionName, toTime, weekDay, workPlace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionInfo {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("    sessionName: ").append(toIndentedString(sessionName)).append("\n");
    sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
    sb.append("    weekDay: ").append(toIndentedString(weekDay)).append("\n");
    sb.append("    workPlace: ").append(toIndentedString(workPlace)).append("\n");
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


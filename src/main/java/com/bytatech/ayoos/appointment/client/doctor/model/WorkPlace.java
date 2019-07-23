package com.bytatech.ayoos.appointment.client.doctor.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bytatech.ayoos.appointment.client.doctor.model.Doctor;
import com.bytatech.ayoos.appointment.client.doctor.model.SessionInfo;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * WorkPlace
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class WorkPlace   {
  @JsonProperty("doctor")
  private Doctor doctor = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("locationName")
  private String locationName = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("sessionInfos")
  @Valid
  private List<SessionInfo> sessionInfos = null;

  public WorkPlace doctor(Doctor doctor) {
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

  public WorkPlace id(Long id) {
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

  public WorkPlace location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public WorkPlace locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }

  /**
   * Get locationName
   * @return locationName
  **/
  @ApiModelProperty(value = "")


  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public WorkPlace name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WorkPlace sessionInfos(List<SessionInfo> sessionInfos) {
    this.sessionInfos = sessionInfos;
    return this;
  }

  public WorkPlace addSessionInfosItem(SessionInfo sessionInfosItem) {
    if (this.sessionInfos == null) {
      this.sessionInfos = new ArrayList<SessionInfo>();
    }
    this.sessionInfos.add(sessionInfosItem);
    return this;
  }

  /**
   * Get sessionInfos
   * @return sessionInfos
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<SessionInfo> getSessionInfos() {
    return sessionInfos;
  }

  public void setSessionInfos(List<SessionInfo> sessionInfos) {
    this.sessionInfos = sessionInfos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkPlace workPlace = (WorkPlace) o;
    return Objects.equals(this.doctor, workPlace.doctor) &&
        Objects.equals(this.id, workPlace.id) &&
        Objects.equals(this.location, workPlace.location) &&
        Objects.equals(this.locationName, workPlace.locationName) &&
        Objects.equals(this.name, workPlace.name) &&
        Objects.equals(this.sessionInfos, workPlace.sessionInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doctor, id, location, locationName, name, sessionInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkPlace {\n");
    
    sb.append("    doctor: ").append(toIndentedString(doctor)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    sessionInfos: ").append(toIndentedString(sessionInfos)).append("\n");
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


package com.bytatech.ayoos.appointment.client.doctor.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DoctorSettings
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class DoctorSettings   {
  @JsonProperty("approvalType")
  private String approvalType = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("isMailNotificationsEnabled")
  private Boolean isMailNotificationsEnabled = null;

  @JsonProperty("isSMSNotificationsEnabled")
  private Boolean isSMSNotificationsEnabled = null;

  public DoctorSettings approvalType(String approvalType) {
    this.approvalType = approvalType;
    return this;
  }

  /**
   * Get approvalType
   * @return approvalType
  **/
  @ApiModelProperty(value = "")


  public String getApprovalType() {
    return approvalType;
  }

  public void setApprovalType(String approvalType) {
    this.approvalType = approvalType;
  }

  public DoctorSettings id(Long id) {
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

  public DoctorSettings isMailNotificationsEnabled(Boolean isMailNotificationsEnabled) {
    this.isMailNotificationsEnabled = isMailNotificationsEnabled;
    return this;
  }

  /**
   * Get isMailNotificationsEnabled
   * @return isMailNotificationsEnabled
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsMailNotificationsEnabled() {
    return isMailNotificationsEnabled;
  }

  public void setIsMailNotificationsEnabled(Boolean isMailNotificationsEnabled) {
    this.isMailNotificationsEnabled = isMailNotificationsEnabled;
  }

  public DoctorSettings isSMSNotificationsEnabled(Boolean isSMSNotificationsEnabled) {
    this.isSMSNotificationsEnabled = isSMSNotificationsEnabled;
    return this;
  }

  /**
   * Get isSMSNotificationsEnabled
   * @return isSMSNotificationsEnabled
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsSMSNotificationsEnabled() {
    return isSMSNotificationsEnabled;
  }

  public void setIsSMSNotificationsEnabled(Boolean isSMSNotificationsEnabled) {
    this.isSMSNotificationsEnabled = isSMSNotificationsEnabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DoctorSettings doctorSettings = (DoctorSettings) o;
    return Objects.equals(this.approvalType, doctorSettings.approvalType) &&
        Objects.equals(this.id, doctorSettings.id) &&
        Objects.equals(this.isMailNotificationsEnabled, doctorSettings.isMailNotificationsEnabled) &&
        Objects.equals(this.isSMSNotificationsEnabled, doctorSettings.isSMSNotificationsEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalType, id, isMailNotificationsEnabled, isSMSNotificationsEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DoctorSettings {\n");
    
    sb.append("    approvalType: ").append(toIndentedString(approvalType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isMailNotificationsEnabled: ").append(toIndentedString(isMailNotificationsEnabled)).append("\n");
    sb.append("    isSMSNotificationsEnabled: ").append(toIndentedString(isSMSNotificationsEnabled)).append("\n");
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


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
 * DoctorSettingsDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class DoctorSettingsDTO   {
  @JsonProperty("approvalType")
  private String approvalType = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("isMailNotificationsEnabled")
  private Boolean isMailNotificationsEnabled = null;

  @JsonProperty("isSMSNotificationsEnabled")
  private Boolean isSMSNotificationsEnabled = null;

  public DoctorSettingsDTO approvalType(String approvalType) {
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

  public DoctorSettingsDTO id(Long id) {
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

  public DoctorSettingsDTO isMailNotificationsEnabled(Boolean isMailNotificationsEnabled) {
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

  public DoctorSettingsDTO isSMSNotificationsEnabled(Boolean isSMSNotificationsEnabled) {
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
    DoctorSettingsDTO doctorSettingsDTO = (DoctorSettingsDTO) o;
    return Objects.equals(this.approvalType, doctorSettingsDTO.approvalType) &&
        Objects.equals(this.id, doctorSettingsDTO.id) &&
        Objects.equals(this.isMailNotificationsEnabled, doctorSettingsDTO.isMailNotificationsEnabled) &&
        Objects.equals(this.isSMSNotificationsEnabled, doctorSettingsDTO.isSMSNotificationsEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approvalType, id, isMailNotificationsEnabled, isSMSNotificationsEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DoctorSettingsDTO {\n");
    
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


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
 * StatusDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class StatusDTO   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("reservedSlotId")
  private Long reservedSlotId = null;

  @JsonProperty("status")
  private String status = null;

  public StatusDTO id(Long id) {
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

  public StatusDTO reservedSlotId(Long reservedSlotId) {
    this.reservedSlotId = reservedSlotId;
    return this;
  }

  /**
   * Get reservedSlotId
   * @return reservedSlotId
  **/
  @ApiModelProperty(value = "")


  public Long getReservedSlotId() {
    return reservedSlotId;
  }

  public void setReservedSlotId(Long reservedSlotId) {
    this.reservedSlotId = reservedSlotId;
  }

  public StatusDTO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusDTO statusDTO = (StatusDTO) o;
    return Objects.equals(this.id, statusDTO.id) &&
        Objects.equals(this.reservedSlotId, statusDTO.reservedSlotId) &&
        Objects.equals(this.status, statusDTO.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, reservedSlotId, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatusDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    reservedSlotId: ").append(toIndentedString(reservedSlotId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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


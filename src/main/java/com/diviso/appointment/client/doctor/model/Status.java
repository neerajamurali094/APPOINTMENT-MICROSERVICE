package com.diviso.appointment.client.doctor.model;

import java.util.Objects;
import com.diviso.appointment.client.doctor.model.ReservedSlot;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Status
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-15T12:28:37.199+05:30[Asia/Kolkata]")

public class Status   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("reservedSlot")
  private ReservedSlot reservedSlot = null;

  @JsonProperty("status")
  private String status = null;

  public Status id(Long id) {
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

  public Status reservedSlot(ReservedSlot reservedSlot) {
    this.reservedSlot = reservedSlot;
    return this;
  }

  /**
   * Get reservedSlot
   * @return reservedSlot
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ReservedSlot getReservedSlot() {
    return reservedSlot;
  }

  public void setReservedSlot(ReservedSlot reservedSlot) {
    this.reservedSlot = reservedSlot;
  }

  public Status status(String status) {
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
    Status status = (Status) o;
    return Objects.equals(this.id, status.id) &&
        Objects.equals(this.reservedSlot, status.reservedSlot) &&
        Objects.equals(this.status, status.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, reservedSlot, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Status {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    reservedSlot: ").append(toIndentedString(reservedSlot)).append("\n");
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


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
 * ContactInfo
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class ContactInfo   {
  @JsonProperty("facebookURL")
  private String facebookURL = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("twitterURL")
  private String twitterURL = null;

  public ContactInfo facebookURL(String facebookURL) {
    this.facebookURL = facebookURL;
    return this;
  }

  /**
   * Get facebookURL
   * @return facebookURL
  **/
  @ApiModelProperty(value = "")


  public String getFacebookURL() {
    return facebookURL;
  }

  public void setFacebookURL(String facebookURL) {
    this.facebookURL = facebookURL;
  }

  public ContactInfo id(Long id) {
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

  public ContactInfo twitterURL(String twitterURL) {
    this.twitterURL = twitterURL;
    return this;
  }

  /**
   * Get twitterURL
   * @return twitterURL
  **/
  @ApiModelProperty(value = "")


  public String getTwitterURL() {
    return twitterURL;
  }

  public void setTwitterURL(String twitterURL) {
    this.twitterURL = twitterURL;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactInfo contactInfo = (ContactInfo) o;
    return Objects.equals(this.facebookURL, contactInfo.facebookURL) &&
        Objects.equals(this.id, contactInfo.id) &&
        Objects.equals(this.twitterURL, contactInfo.twitterURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(facebookURL, id, twitterURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactInfo {\n");
    
    sb.append("    facebookURL: ").append(toIndentedString(facebookURL)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    twitterURL: ").append(toIndentedString(twitterURL)).append("\n");
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


package com.bytatech.ayoos.appointment.client.doctor.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bytatech.ayoos.appointment.client.doctor.model.ContactInfo;
import com.bytatech.ayoos.appointment.client.doctor.model.DoctorSettings;
import com.bytatech.ayoos.appointment.client.doctor.model.PaymentSettings;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DoctorAggregateDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class DoctorAggregateDTO   {
  @JsonProperty("contactInfo")
  private ContactInfo contactInfo = null;

  @JsonProperty("doctorId")
  private String doctorId = null;

  @JsonProperty("doctorSettings")
  private DoctorSettings doctorSettings = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("image")
  private byte[] image = null;

  @JsonProperty("imageContentType")
  private String imageContentType = null;

  @JsonProperty("paymentSettings")
  private PaymentSettings paymentSettings = null;

  @JsonProperty("phoneNumber")
  private Long phoneNumber = null;

  @JsonProperty("practiceSince")
  private OffsetDateTime practiceSince = null;

  @JsonProperty("registerNumber")
  private String registerNumber = null;

  @JsonProperty("specialization")
  private String specialization = null;

  @JsonProperty("totalRating")
  private Double totalRating = null;

  public DoctorAggregateDTO contactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
    return this;
  }

  /**
   * Get contactInfo
   * @return contactInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

  public DoctorAggregateDTO doctorId(String doctorId) {
    this.doctorId = doctorId;
    return this;
  }

  /**
   * Get doctorId
   * @return doctorId
  **/
  @ApiModelProperty(value = "")


  public String getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(String doctorId) {
    this.doctorId = doctorId;
  }

  public DoctorAggregateDTO doctorSettings(DoctorSettings doctorSettings) {
    this.doctorSettings = doctorSettings;
    return this;
  }

  /**
   * Get doctorSettings
   * @return doctorSettings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DoctorSettings getDoctorSettings() {
    return doctorSettings;
  }

  public void setDoctorSettings(DoctorSettings doctorSettings) {
    this.doctorSettings = doctorSettings;
  }

  public DoctorAggregateDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public DoctorAggregateDTO firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public DoctorAggregateDTO id(Long id) {
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

  public DoctorAggregateDTO image(byte[] image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public DoctorAggregateDTO imageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
    return this;
  }

  /**
   * Get imageContentType
   * @return imageContentType
  **/
  @ApiModelProperty(value = "")


  public String getImageContentType() {
    return imageContentType;
  }

  public void setImageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
  }

  public DoctorAggregateDTO paymentSettings(PaymentSettings paymentSettings) {
    this.paymentSettings = paymentSettings;
    return this;
  }

  /**
   * Get paymentSettings
   * @return paymentSettings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PaymentSettings getPaymentSettings() {
    return paymentSettings;
  }

  public void setPaymentSettings(PaymentSettings paymentSettings) {
    this.paymentSettings = paymentSettings;
  }

  public DoctorAggregateDTO phoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")


  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public DoctorAggregateDTO practiceSince(OffsetDateTime practiceSince) {
    this.practiceSince = practiceSince;
    return this;
  }

  /**
   * Get practiceSince
   * @return practiceSince
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getPracticeSince() {
    return practiceSince;
  }

  public void setPracticeSince(OffsetDateTime practiceSince) {
    this.practiceSince = practiceSince;
  }

  public DoctorAggregateDTO registerNumber(String registerNumber) {
    this.registerNumber = registerNumber;
    return this;
  }

  /**
   * Get registerNumber
   * @return registerNumber
  **/
  @ApiModelProperty(value = "")


  public String getRegisterNumber() {
    return registerNumber;
  }

  public void setRegisterNumber(String registerNumber) {
    this.registerNumber = registerNumber;
  }

  public DoctorAggregateDTO specialization(String specialization) {
    this.specialization = specialization;
    return this;
  }

  /**
   * Get specialization
   * @return specialization
  **/
  @ApiModelProperty(value = "")


  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public DoctorAggregateDTO totalRating(Double totalRating) {
    this.totalRating = totalRating;
    return this;
  }

  /**
   * Get totalRating
   * @return totalRating
  **/
  @ApiModelProperty(value = "")


  public Double getTotalRating() {
    return totalRating;
  }

  public void setTotalRating(Double totalRating) {
    this.totalRating = totalRating;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DoctorAggregateDTO doctorAggregateDTO = (DoctorAggregateDTO) o;
    return Objects.equals(this.contactInfo, doctorAggregateDTO.contactInfo) &&
        Objects.equals(this.doctorId, doctorAggregateDTO.doctorId) &&
        Objects.equals(this.doctorSettings, doctorAggregateDTO.doctorSettings) &&
        Objects.equals(this.email, doctorAggregateDTO.email) &&
        Objects.equals(this.firstName, doctorAggregateDTO.firstName) &&
        Objects.equals(this.id, doctorAggregateDTO.id) &&
        Objects.equals(this.image, doctorAggregateDTO.image) &&
        Objects.equals(this.imageContentType, doctorAggregateDTO.imageContentType) &&
        Objects.equals(this.paymentSettings, doctorAggregateDTO.paymentSettings) &&
        Objects.equals(this.phoneNumber, doctorAggregateDTO.phoneNumber) &&
        Objects.equals(this.practiceSince, doctorAggregateDTO.practiceSince) &&
        Objects.equals(this.registerNumber, doctorAggregateDTO.registerNumber) &&
        Objects.equals(this.specialization, doctorAggregateDTO.specialization) &&
        Objects.equals(this.totalRating, doctorAggregateDTO.totalRating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactInfo, doctorId, doctorSettings, email, firstName, id, image, imageContentType, paymentSettings, phoneNumber, practiceSince, registerNumber, specialization, totalRating);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DoctorAggregateDTO {\n");
    
    sb.append("    contactInfo: ").append(toIndentedString(contactInfo)).append("\n");
    sb.append("    doctorId: ").append(toIndentedString(doctorId)).append("\n");
    sb.append("    doctorSettings: ").append(toIndentedString(doctorSettings)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    imageContentType: ").append(toIndentedString(imageContentType)).append("\n");
    sb.append("    paymentSettings: ").append(toIndentedString(paymentSettings)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    practiceSince: ").append(toIndentedString(practiceSince)).append("\n");
    sb.append("    registerNumber: ").append(toIndentedString(registerNumber)).append("\n");
    sb.append("    specialization: ").append(toIndentedString(specialization)).append("\n");
    sb.append("    totalRating: ").append(toIndentedString(totalRating)).append("\n");
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


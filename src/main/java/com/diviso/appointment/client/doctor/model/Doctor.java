package com.diviso.appointment.client.doctor.model;

import java.util.Objects;
import com.diviso.appointment.client.doctor.model.ContactInfo;
import com.diviso.appointment.client.doctor.model.DoctorSettings;
import com.diviso.appointment.client.doctor.model.PaymentSettings;
import com.diviso.appointment.client.doctor.model.Qualification;
import com.diviso.appointment.client.doctor.model.ReservedSlot;
import com.diviso.appointment.client.doctor.model.Review;
import com.diviso.appointment.client.doctor.model.UserRating;
import com.diviso.appointment.client.doctor.model.WorkPlace;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Doctor
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-15T12:28:37.199+05:30[Asia/Kolkata]")

public class Doctor   {
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

  @JsonProperty("qualifications")
  @Valid
  private List<Qualification> qualifications = null;

  @JsonProperty("registerNumber")
  private String registerNumber = null;

  @JsonProperty("reservedSlots")
  @Valid
  private List<ReservedSlot> reservedSlots = null;

  @JsonProperty("reviews")
  @Valid
  private List<Review> reviews = null;

  @JsonProperty("specialization")
  private String specialization = null;

  @JsonProperty("totalRating")
  private Double totalRating = null;

  @JsonProperty("userRatings")
  @Valid
  private List<UserRating> userRatings = null;

  @JsonProperty("workPlaces")
  @Valid
  private List<WorkPlace> workPlaces = null;

  public Doctor contactInfo(ContactInfo contactInfo) {
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

  public Doctor doctorId(String doctorId) {
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

  public Doctor doctorSettings(DoctorSettings doctorSettings) {
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

  public Doctor email(String email) {
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

  public Doctor firstName(String firstName) {
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

  public Doctor id(Long id) {
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

  public Doctor image(byte[] image) {
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

  public Doctor imageContentType(String imageContentType) {
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

  public Doctor paymentSettings(PaymentSettings paymentSettings) {
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

  public Doctor phoneNumber(Long phoneNumber) {
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

  public Doctor practiceSince(OffsetDateTime practiceSince) {
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

  public Doctor qualifications(List<Qualification> qualifications) {
    this.qualifications = qualifications;
    return this;
  }

  public Doctor addQualificationsItem(Qualification qualificationsItem) {
    if (this.qualifications == null) {
      this.qualifications = new ArrayList<Qualification>();
    }
    this.qualifications.add(qualificationsItem);
    return this;
  }

  /**
   * Get qualifications
   * @return qualifications
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Qualification> getQualifications() {
    return qualifications;
  }

  public void setQualifications(List<Qualification> qualifications) {
    this.qualifications = qualifications;
  }

  public Doctor registerNumber(String registerNumber) {
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

  public Doctor reservedSlots(List<ReservedSlot> reservedSlots) {
    this.reservedSlots = reservedSlots;
    return this;
  }

  public Doctor addReservedSlotsItem(ReservedSlot reservedSlotsItem) {
    if (this.reservedSlots == null) {
      this.reservedSlots = new ArrayList<ReservedSlot>();
    }
    this.reservedSlots.add(reservedSlotsItem);
    return this;
  }

  /**
   * Get reservedSlots
   * @return reservedSlots
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ReservedSlot> getReservedSlots() {
    return reservedSlots;
  }

  public void setReservedSlots(List<ReservedSlot> reservedSlots) {
    this.reservedSlots = reservedSlots;
  }

  public Doctor reviews(List<Review> reviews) {
    this.reviews = reviews;
    return this;
  }

  public Doctor addReviewsItem(Review reviewsItem) {
    if (this.reviews == null) {
      this.reviews = new ArrayList<Review>();
    }
    this.reviews.add(reviewsItem);
    return this;
  }

  /**
   * Get reviews
   * @return reviews
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Doctor specialization(String specialization) {
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

  public Doctor totalRating(Double totalRating) {
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

  public Doctor userRatings(List<UserRating> userRatings) {
    this.userRatings = userRatings;
    return this;
  }

  public Doctor addUserRatingsItem(UserRating userRatingsItem) {
    if (this.userRatings == null) {
      this.userRatings = new ArrayList<UserRating>();
    }
    this.userRatings.add(userRatingsItem);
    return this;
  }

  /**
   * Get userRatings
   * @return userRatings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UserRating> getUserRatings() {
    return userRatings;
  }

  public void setUserRatings(List<UserRating> userRatings) {
    this.userRatings = userRatings;
  }

  public Doctor workPlaces(List<WorkPlace> workPlaces) {
    this.workPlaces = workPlaces;
    return this;
  }

  public Doctor addWorkPlacesItem(WorkPlace workPlacesItem) {
    if (this.workPlaces == null) {
      this.workPlaces = new ArrayList<WorkPlace>();
    }
    this.workPlaces.add(workPlacesItem);
    return this;
  }

  /**
   * Get workPlaces
   * @return workPlaces
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<WorkPlace> getWorkPlaces() {
    return workPlaces;
  }

  public void setWorkPlaces(List<WorkPlace> workPlaces) {
    this.workPlaces = workPlaces;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Doctor doctor = (Doctor) o;
    return Objects.equals(this.contactInfo, doctor.contactInfo) &&
        Objects.equals(this.doctorId, doctor.doctorId) &&
        Objects.equals(this.doctorSettings, doctor.doctorSettings) &&
        Objects.equals(this.email, doctor.email) &&
        Objects.equals(this.firstName, doctor.firstName) &&
        Objects.equals(this.id, doctor.id) &&
        Objects.equals(this.image, doctor.image) &&
        Objects.equals(this.imageContentType, doctor.imageContentType) &&
        Objects.equals(this.paymentSettings, doctor.paymentSettings) &&
        Objects.equals(this.phoneNumber, doctor.phoneNumber) &&
        Objects.equals(this.practiceSince, doctor.practiceSince) &&
        Objects.equals(this.qualifications, doctor.qualifications) &&
        Objects.equals(this.registerNumber, doctor.registerNumber) &&
        Objects.equals(this.reservedSlots, doctor.reservedSlots) &&
        Objects.equals(this.reviews, doctor.reviews) &&
        Objects.equals(this.specialization, doctor.specialization) &&
        Objects.equals(this.totalRating, doctor.totalRating) &&
        Objects.equals(this.userRatings, doctor.userRatings) &&
        Objects.equals(this.workPlaces, doctor.workPlaces);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactInfo, doctorId, doctorSettings, email, firstName, id, image, imageContentType, paymentSettings, phoneNumber, practiceSince, qualifications, registerNumber, reservedSlots, reviews, specialization, totalRating, userRatings, workPlaces);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Doctor {\n");
    
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
    sb.append("    qualifications: ").append(toIndentedString(qualifications)).append("\n");
    sb.append("    registerNumber: ").append(toIndentedString(registerNumber)).append("\n");
    sb.append("    reservedSlots: ").append(toIndentedString(reservedSlots)).append("\n");
    sb.append("    reviews: ").append(toIndentedString(reviews)).append("\n");
    sb.append("    specialization: ").append(toIndentedString(specialization)).append("\n");
    sb.append("    totalRating: ").append(toIndentedString(totalRating)).append("\n");
    sb.append("    userRatings: ").append(toIndentedString(userRatings)).append("\n");
    sb.append("    workPlaces: ").append(toIndentedString(workPlaces)).append("\n");
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


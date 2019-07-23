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
 * PaymentSettings
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-18T10:32:46.727+05:30[Asia/Kolkata]")

public class PaymentSettings   {
  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("currency")
  private String currency = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("intent")
  private String intent = null;

  @JsonProperty("isPaymentEnabled")
  private Boolean isPaymentEnabled = null;

  @JsonProperty("noteToPayer")
  private String noteToPayer = null;

  @JsonProperty("paymentGatewayCredentials")
  private String paymentGatewayCredentials = null;

  @JsonProperty("paymentGatewayProvider")
  private String paymentGatewayProvider = null;

  @JsonProperty("paymentMethod")
  private String paymentMethod = null;

  public PaymentSettings amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(value = "")


  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public PaymentSettings currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  **/
  @ApiModelProperty(value = "")


  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PaymentSettings id(Long id) {
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

  public PaymentSettings intent(String intent) {
    this.intent = intent;
    return this;
  }

  /**
   * Get intent
   * @return intent
  **/
  @ApiModelProperty(value = "")


  public String getIntent() {
    return intent;
  }

  public void setIntent(String intent) {
    this.intent = intent;
  }

  public PaymentSettings isPaymentEnabled(Boolean isPaymentEnabled) {
    this.isPaymentEnabled = isPaymentEnabled;
    return this;
  }

  /**
   * Get isPaymentEnabled
   * @return isPaymentEnabled
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsPaymentEnabled() {
    return isPaymentEnabled;
  }

  public void setIsPaymentEnabled(Boolean isPaymentEnabled) {
    this.isPaymentEnabled = isPaymentEnabled;
  }

  public PaymentSettings noteToPayer(String noteToPayer) {
    this.noteToPayer = noteToPayer;
    return this;
  }

  /**
   * Get noteToPayer
   * @return noteToPayer
  **/
  @ApiModelProperty(value = "")


  public String getNoteToPayer() {
    return noteToPayer;
  }

  public void setNoteToPayer(String noteToPayer) {
    this.noteToPayer = noteToPayer;
  }

  public PaymentSettings paymentGatewayCredentials(String paymentGatewayCredentials) {
    this.paymentGatewayCredentials = paymentGatewayCredentials;
    return this;
  }

  /**
   * Get paymentGatewayCredentials
   * @return paymentGatewayCredentials
  **/
  @ApiModelProperty(value = "")


  public String getPaymentGatewayCredentials() {
    return paymentGatewayCredentials;
  }

  public void setPaymentGatewayCredentials(String paymentGatewayCredentials) {
    this.paymentGatewayCredentials = paymentGatewayCredentials;
  }

  public PaymentSettings paymentGatewayProvider(String paymentGatewayProvider) {
    this.paymentGatewayProvider = paymentGatewayProvider;
    return this;
  }

  /**
   * Get paymentGatewayProvider
   * @return paymentGatewayProvider
  **/
  @ApiModelProperty(value = "")


  public String getPaymentGatewayProvider() {
    return paymentGatewayProvider;
  }

  public void setPaymentGatewayProvider(String paymentGatewayProvider) {
    this.paymentGatewayProvider = paymentGatewayProvider;
  }

  public PaymentSettings paymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  /**
   * Get paymentMethod
   * @return paymentMethod
  **/
  @ApiModelProperty(value = "")


  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentSettings paymentSettings = (PaymentSettings) o;
    return Objects.equals(this.amount, paymentSettings.amount) &&
        Objects.equals(this.currency, paymentSettings.currency) &&
        Objects.equals(this.id, paymentSettings.id) &&
        Objects.equals(this.intent, paymentSettings.intent) &&
        Objects.equals(this.isPaymentEnabled, paymentSettings.isPaymentEnabled) &&
        Objects.equals(this.noteToPayer, paymentSettings.noteToPayer) &&
        Objects.equals(this.paymentGatewayCredentials, paymentSettings.paymentGatewayCredentials) &&
        Objects.equals(this.paymentGatewayProvider, paymentSettings.paymentGatewayProvider) &&
        Objects.equals(this.paymentMethod, paymentSettings.paymentMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency, id, intent, isPaymentEnabled, noteToPayer, paymentGatewayCredentials, paymentGatewayProvider, paymentMethod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentSettings {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    intent: ").append(toIndentedString(intent)).append("\n");
    sb.append("    isPaymentEnabled: ").append(toIndentedString(isPaymentEnabled)).append("\n");
    sb.append("    noteToPayer: ").append(toIndentedString(noteToPayer)).append("\n");
    sb.append("    paymentGatewayCredentials: ").append(toIndentedString(paymentGatewayCredentials)).append("\n");
    sb.append("    paymentGatewayProvider: ").append(toIndentedString(paymentGatewayProvider)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
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


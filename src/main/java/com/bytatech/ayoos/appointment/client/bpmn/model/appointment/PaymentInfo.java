package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import org.activiti.engine.impl.variable.ByteArrayType;

public class PaymentInfo extends ByteArrayType {
/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"PaymentInfo [isPaymentEnabled=%s,\n amount=%s,\n paymentMethod=%s,\n currency=%s,\n intent=%s,\n note_to_payer=%s,\n paymentGatewayProvider=%s,\n paymentGatewayCredentials=%s]",
				isPaymentEnabled, amount, paymentMethod, currency, intent, note_to_payer, paymentGatewayProvider,
				paymentGatewayCredentials);
	}
private Boolean isPaymentEnabled;
private Double amount;
private String paymentMethod;
private String currency;
private String intent;
private String note_to_payer;
private String paymentGatewayProvider;
private String paymentGatewayCredentials;
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getPaymentMethod() {
	return paymentMethod;
}
public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public String getIntent() {
	return intent;
}
public void setIntent(String intent) {
	this.intent = intent;
}
public String getNote_to_payer() {
	return note_to_payer;
}
public void setNote_to_payer(String note_to_payer) {
	this.note_to_payer = note_to_payer;
}

public String getPaymentGatewayCredentials() {
	return paymentGatewayCredentials;
}
public void setPaymentGatewayCredentials(String paymentGatewayCredentials) {
	this.paymentGatewayCredentials = paymentGatewayCredentials;
}
public Boolean getIsPaymentEnabled() {
	return isPaymentEnabled;
}
public void setIsPaymentEnabled(Boolean isPaymentEnabled) {
	this.isPaymentEnabled = isPaymentEnabled;
}
public String getPaymentGatewayProvider() {
	return paymentGatewayProvider;
}
public void setPaymentGatewayProvider(String paymentGatewayProvider) {
	this.paymentGatewayProvider = paymentGatewayProvider;
}


}

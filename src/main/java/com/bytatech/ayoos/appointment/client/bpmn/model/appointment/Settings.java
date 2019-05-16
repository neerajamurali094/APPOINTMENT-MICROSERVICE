package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import org.activiti.engine.impl.variable.ByteArrayType;

public class Settings extends ByteArrayType {
	
	public String getApprovalType() {
		return approvalType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"Settings [approvalType=%s,\n isMailNotificationsEnabled=%s,\n isSMSNotificationsEnabled=%s,\n paymentSettings=%s]",
				approvalType, isMailNotificationsEnabled, isSMSNotificationsEnabled, paymentSettings);
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	public Boolean getIsMailNotificationsEnabled() {
		return isMailNotificationsEnabled;
	}
	public void setIsMailNotificationsEnabled(Boolean isMailNotificationsEnabled) {
		this.isMailNotificationsEnabled = isMailNotificationsEnabled;
	}
	public Boolean getIsSMSNotificationsEnabled() {
		return isSMSNotificationsEnabled;
	}
	public void setIsSMSNotificationsEnabled(Boolean isSMSNotificationsEnabled) {
		this.isSMSNotificationsEnabled = isSMSNotificationsEnabled;
	}
	public PaymentInfo getPaymentSettings() {
		return paymentSettings;
	}
	public void setPaymentSettings(PaymentInfo paymentSettings) {
		this.paymentSettings = paymentSettings;
	}
	private String approvalType;
	private Boolean isMailNotificationsEnabled;
	private Boolean isSMSNotificationsEnabled;
	private PaymentInfo paymentSettings;
	
}

package com.bytatech.ayoos.appointment.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Appointment entity.
 */
public class AppointmentDTO implements Serializable {

    private Long id;

    private String trackingId;

    private String appointmentId;

    private String chronicDiseaseRef;

    private ZonedDateTime appointmentDateAndTime;

    private String note;

    private String patientId;

    private String doctorId;

    private Long consultationInfoId;

    private Long timingId;

    private Long statusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getChronicDiseaseRef() {
        return chronicDiseaseRef;
    }

    public void setChronicDiseaseRef(String chronicDiseaseRef) {
        this.chronicDiseaseRef = chronicDiseaseRef;
    }

    public ZonedDateTime getAppointmentDateAndTime() {
        return appointmentDateAndTime;
    }

    public void setAppointmentDateAndTime(ZonedDateTime appointmentDateAndTime) {
        this.appointmentDateAndTime = appointmentDateAndTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Long getConsultationInfoId() {
        return consultationInfoId;
    }

    public void setConsultationInfoId(Long consultationInfoId) {
        this.consultationInfoId = consultationInfoId;
    }

    public Long getTimingId() {
        return timingId;
    }

    public void setTimingId(Long timingId) {
        this.timingId = timingId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentDTO appointmentDTO = (AppointmentDTO) o;
        if (appointmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
            "id=" + getId() +
            ", trackingId='" + getTrackingId() + "'" +
            ", appointmentId='" + getAppointmentId() + "'" +
            ", chronicDiseaseRef='" + getChronicDiseaseRef() + "'" +
            ", appointmentDateAndTime='" + getAppointmentDateAndTime() + "'" +
            ", note='" + getNote() + "'" +
            ", patientId='" + getPatientId() + "'" +
            ", doctorId='" + getDoctorId() + "'" +
            ", consultationInfo=" + getConsultationInfoId() +
            ", timing=" + getTimingId() +
            ", status=" + getStatusId() +
            "}";
    }
}

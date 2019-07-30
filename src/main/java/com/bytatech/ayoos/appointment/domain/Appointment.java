package com.bytatech.ayoos.appointment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Appointment.
 */
@Entity
@Table(name = "appointment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name = "chronic_disease_ref")
    private String chronicDiseaseRef;

    @Column(name = "appointment_date_and_time")
    private ZonedDateTime appointmentDateAndTime;

    @Column(name = "note")
    private String note;

    @Column(name = "patient_id")
    
    private String patientId;

    @Column(name = "doctor_id")
    private String doctorId;

	@OneToOne(/* cascade=CascadeType.PERSIST */)    @JoinColumn(unique = true)
    private ConsultationInfo consultationInfo;

	@OneToOne(/* cascade=CascadeType.PERSIST */)    @JoinColumn(unique = true)
    private Timing timing;

	@ManyToOne(/* cascade=CascadeType.PERSIST */) 
    @JsonIgnoreProperties("")
    private Status status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Appointment trackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Appointment appointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getChronicDiseaseRef() {
        return chronicDiseaseRef;
    }

    public Appointment chronicDiseaseRef(String chronicDiseaseRef) {
        this.chronicDiseaseRef = chronicDiseaseRef;
        return this;
    }

    public void setChronicDiseaseRef(String chronicDiseaseRef) {
        this.chronicDiseaseRef = chronicDiseaseRef;
    }

    public ZonedDateTime getAppointmentDateAndTime() {
        return appointmentDateAndTime;
    }

    public Appointment appointmentDateAndTime(ZonedDateTime appointmentDateAndTime) {
        this.appointmentDateAndTime = appointmentDateAndTime;
        return this;
    }

    public void setAppointmentDateAndTime(ZonedDateTime appointmentDateAndTime) {
        this.appointmentDateAndTime = appointmentDateAndTime;
    }

    public String getNote() {
        return note;
    }

    public Appointment note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatientId() {
        return patientId;
    }

    public Appointment patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Appointment doctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public ConsultationInfo getConsultationInfo() {
        return consultationInfo;
    }

    public Appointment consultationInfo(ConsultationInfo consultationInfo) {
        this.consultationInfo = consultationInfo;
        return this;
    }

    public void setConsultationInfo(ConsultationInfo consultationInfo) {
        this.consultationInfo = consultationInfo;
    }

    public Timing getTiming() {
        return timing;
    }

    public Appointment timing(Timing timing) {
        this.timing = timing;
        return this;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    public Status getStatus() {
        return status;
    }

    public Appointment status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment appointment = (Appointment) o;
        if (appointment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Appointment{" +
            "id=" + getId() +
            ", trackingId='" + getTrackingId() + "'" +
            ", appointmentId='" + getAppointmentId() + "'" +
            ", chronicDiseaseRef='" + getChronicDiseaseRef() + "'" +
            ", appointmentDateAndTime='" + getAppointmentDateAndTime() + "'" +
            ", note='" + getNote() + "'" +
            ", patientId='" + getPatientId() + "'" +
            ", doctorId='" + getDoctorId() + "'" +
            "}"+consultationInfo+"----"+timing;
    }
}

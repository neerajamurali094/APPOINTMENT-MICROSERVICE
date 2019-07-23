package com.bytatech.ayoos.appointment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Symptom.
 */
@Entity
@Table(name = "symptom")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "symptom")
public class Symptom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_ref")
    private String ref;

    @Column(name = "number_of_days_suffering")
    private Integer numberOfDaysSuffering;

    @ManyToOne
    @JsonIgnoreProperties("symptoms")
    private ConsultationInfo consultationInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public Symptom ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getNumberOfDaysSuffering() {
        return numberOfDaysSuffering;
    }

    public Symptom numberOfDaysSuffering(Integer numberOfDaysSuffering) {
        this.numberOfDaysSuffering = numberOfDaysSuffering;
        return this;
    }

    public void setNumberOfDaysSuffering(Integer numberOfDaysSuffering) {
        this.numberOfDaysSuffering = numberOfDaysSuffering;
    }

    public ConsultationInfo getConsultationInfo() {
        return consultationInfo;
    }

    public Symptom consultationInfo(ConsultationInfo consultationInfo) {
        this.consultationInfo = consultationInfo;
        return this;
    }

    public void setConsultationInfo(ConsultationInfo consultationInfo) {
        this.consultationInfo = consultationInfo;
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
        Symptom symptom = (Symptom) o;
        if (symptom.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), symptom.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Symptom{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", numberOfDaysSuffering=" + getNumberOfDaysSuffering() +
            "}";
    }
}

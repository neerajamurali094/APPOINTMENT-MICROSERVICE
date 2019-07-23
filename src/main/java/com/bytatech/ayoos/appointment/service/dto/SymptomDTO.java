package com.bytatech.ayoos.appointment.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Symptom entity.
 */
public class SymptomDTO implements Serializable {

    private Long id;

    private String ref;

    private Integer numberOfDaysSuffering;

    private Long consultationInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getNumberOfDaysSuffering() {
        return numberOfDaysSuffering;
    }

    public void setNumberOfDaysSuffering(Integer numberOfDaysSuffering) {
        this.numberOfDaysSuffering = numberOfDaysSuffering;
    }

    public Long getConsultationInfoId() {
        return consultationInfoId;
    }

    public void setConsultationInfoId(Long consultationInfoId) {
        this.consultationInfoId = consultationInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SymptomDTO symptomDTO = (SymptomDTO) o;
        if (symptomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), symptomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SymptomDTO{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", numberOfDaysSuffering=" + getNumberOfDaysSuffering() +
            ", consultationInfo=" + getConsultationInfoId() +
            "}";
    }
}

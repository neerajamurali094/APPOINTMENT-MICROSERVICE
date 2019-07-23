package com.bytatech.ayoos.appointment.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConsultationInfo entity.
 */
public class ConsultationInfoDTO implements Serializable {

    private Long id;

    private Float height;

    private Float weight;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsultationInfoDTO consultationInfoDTO = (ConsultationInfoDTO) o;
        if (consultationInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consultationInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsultationInfoDTO{" +
            "id=" + getId() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", age=" + getAge() +
            "}";
    }
}

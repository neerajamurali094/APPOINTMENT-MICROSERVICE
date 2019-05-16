package com.bytatech.ayoos.appointment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ConsultationInfo.
 */
@Entity
@Table(name = "consultation_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "consultationinfo")
public class ConsultationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height")
    private Float height;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "consultationInfo",cascade=CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Symptom> symptoms = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getHeight() {
        return height;
    }

    public ConsultationInfo height(Float height) {
        this.height = height;
        return this;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public ConsultationInfo weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public ConsultationInfo age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public ConsultationInfo symptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
        return this;
    }

    public ConsultationInfo addSymptoms(Symptom symptom) {
        this.symptoms.add(symptom);
        symptom.setConsultationInfo(this);
        return this;
    }

    public ConsultationInfo removeSymptoms(Symptom symptom) {
        this.symptoms.remove(symptom);
        symptom.setConsultationInfo(null);
        return this;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
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
        ConsultationInfo consultationInfo = (ConsultationInfo) o;
        if (consultationInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consultationInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsultationInfo{" +
            "id=" + getId() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", age=" + getAge() +
            "}";
    }
}

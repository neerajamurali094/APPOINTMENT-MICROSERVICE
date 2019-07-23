package com.bytatech.ayoos.appointment.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Timing.
 */
@Entity
@Table(name = "timing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "timing")
public class Timing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "start_from")
    private ZonedDateTime startFrom;

    @Column(name = "end_to")
    private ZonedDateTime endTo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public Timing day(LocalDate day) {
        this.day = day;
        return this;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public ZonedDateTime getStartFrom() {
        return startFrom;
    }

    public Timing startFrom(ZonedDateTime startFrom) {
        this.startFrom = startFrom;
        return this;
    }

    public void setStartFrom(ZonedDateTime startFrom) {
        this.startFrom = startFrom;
    }

    public ZonedDateTime getEndTo() {
        return endTo;
    }

    public Timing endTo(ZonedDateTime endTo) {
        this.endTo = endTo;
        return this;
    }

    public void setEndTo(ZonedDateTime endTo) {
        this.endTo = endTo;
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
        Timing timing = (Timing) o;
        if (timing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), timing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Timing{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", startFrom='" + getStartFrom() + "'" +
            ", endTo='" + getEndTo() + "'" +
            "}";
    }
}

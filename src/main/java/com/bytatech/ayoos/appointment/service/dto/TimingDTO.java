package com.bytatech.ayoos.appointment.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Timing entity.
 */
public class TimingDTO implements Serializable {

    private Long id;

    private LocalDate day;

    private ZonedDateTime startFrom;

    private ZonedDateTime endTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public ZonedDateTime getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(ZonedDateTime startFrom) {
        this.startFrom = startFrom;
    }

    public ZonedDateTime getEndTo() {
        return endTo;
    }

    public void setEndTo(ZonedDateTime endTo) {
        this.endTo = endTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimingDTO timingDTO = (TimingDTO) o;
        if (timingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), timingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TimingDTO{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", startFrom='" + getStartFrom() + "'" +
            ", endTo='" + getEndTo() + "'" +
            "}";
    }
}

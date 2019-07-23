import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAppointment } from 'app/shared/model/AyoosAppointment/appointment.model';
import { AppointmentService } from './appointment.service';
import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';
import { ConsultationInfoService } from 'app/entities/AyoosAppointment/consultation-info';
import { ITiming } from 'app/shared/model/AyoosAppointment/timing.model';
import { TimingService } from 'app/entities/AyoosAppointment/timing';
import { IStatus } from 'app/shared/model/AyoosAppointment/status.model';
import { StatusService } from 'app/entities/AyoosAppointment/status';

@Component({
  selector: 'jhi-appointment-update',
  templateUrl: './appointment-update.component.html'
})
export class AppointmentUpdateComponent implements OnInit {
  appointment: IAppointment;
  isSaving: boolean;

  consultationinfos: IConsultationInfo[];

  timings: ITiming[];

  statuses: IStatus[];
  appointmentDateAndTime: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private appointmentService: AppointmentService,
    private consultationInfoService: ConsultationInfoService,
    private timingService: TimingService,
    private statusService: StatusService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ appointment }) => {
      this.appointment = appointment;
      this.appointmentDateAndTime =
        this.appointment.appointmentDateAndTime != null ? this.appointment.appointmentDateAndTime.format(DATE_TIME_FORMAT) : null;
    });
    this.consultationInfoService.query({ filter: 'appointment-is-null' }).subscribe(
      (res: HttpResponse<IConsultationInfo[]>) => {
        if (!this.appointment.consultationInfoId) {
          this.consultationinfos = res.body;
        } else {
          this.consultationInfoService.find(this.appointment.consultationInfoId).subscribe(
            (subRes: HttpResponse<IConsultationInfo>) => {
              this.consultationinfos = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.timingService.query({ filter: 'appointment-is-null' }).subscribe(
      (res: HttpResponse<ITiming[]>) => {
        if (!this.appointment.timingId) {
          this.timings = res.body;
        } else {
          this.timingService.find(this.appointment.timingId).subscribe(
            (subRes: HttpResponse<ITiming>) => {
              this.timings = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.statusService.query().subscribe(
      (res: HttpResponse<IStatus[]>) => {
        this.statuses = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.appointment.appointmentDateAndTime =
      this.appointmentDateAndTime != null ? moment(this.appointmentDateAndTime, DATE_TIME_FORMAT) : null;
    if (this.appointment.id !== undefined) {
      this.subscribeToSaveResponse(this.appointmentService.update(this.appointment));
    } else {
      this.subscribeToSaveResponse(this.appointmentService.create(this.appointment));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IAppointment>>) {
    result.subscribe((res: HttpResponse<IAppointment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackConsultationInfoById(index: number, item: IConsultationInfo) {
    return item.id;
  }

  trackTimingById(index: number, item: ITiming) {
    return item.id;
  }

  trackStatusById(index: number, item: IStatus) {
    return item.id;
  }
}

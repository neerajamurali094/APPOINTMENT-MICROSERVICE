import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITiming } from 'app/shared/model/AyoosAppointment/timing.model';
import { TimingService } from './timing.service';

@Component({
  selector: 'jhi-timing-update',
  templateUrl: './timing-update.component.html'
})
export class TimingUpdateComponent implements OnInit {
  timing: ITiming;
  isSaving: boolean;
  dayDp: any;
  startFrom: string;
  endTo: string;

  constructor(private timingService: TimingService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ timing }) => {
      this.timing = timing;
      this.startFrom = this.timing.startFrom != null ? this.timing.startFrom.format(DATE_TIME_FORMAT) : null;
      this.endTo = this.timing.endTo != null ? this.timing.endTo.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.timing.startFrom = this.startFrom != null ? moment(this.startFrom, DATE_TIME_FORMAT) : null;
    this.timing.endTo = this.endTo != null ? moment(this.endTo, DATE_TIME_FORMAT) : null;
    if (this.timing.id !== undefined) {
      this.subscribeToSaveResponse(this.timingService.update(this.timing));
    } else {
      this.subscribeToSaveResponse(this.timingService.create(this.timing));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<ITiming>>) {
    result.subscribe((res: HttpResponse<ITiming>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}

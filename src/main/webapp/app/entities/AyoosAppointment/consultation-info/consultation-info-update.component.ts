import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';
import { ConsultationInfoService } from './consultation-info.service';

@Component({
  selector: 'jhi-consultation-info-update',
  templateUrl: './consultation-info-update.component.html'
})
export class ConsultationInfoUpdateComponent implements OnInit {
  consultationInfo: IConsultationInfo;
  isSaving: boolean;

  constructor(private consultationInfoService: ConsultationInfoService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ consultationInfo }) => {
      this.consultationInfo = consultationInfo;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.consultationInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.consultationInfoService.update(this.consultationInfo));
    } else {
      this.subscribeToSaveResponse(this.consultationInfoService.create(this.consultationInfo));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IConsultationInfo>>) {
    result.subscribe((res: HttpResponse<IConsultationInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}

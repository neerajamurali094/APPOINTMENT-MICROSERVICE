import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISymptom } from 'app/shared/model/AyoosAppointment/symptom.model';
import { SymptomService } from './symptom.service';
import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';
import { ConsultationInfoService } from 'app/entities/AyoosAppointment/consultation-info';

@Component({
  selector: 'jhi-symptom-update',
  templateUrl: './symptom-update.component.html'
})
export class SymptomUpdateComponent implements OnInit {
  symptom: ISymptom;
  isSaving: boolean;

  consultationinfos: IConsultationInfo[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private symptomService: SymptomService,
    private consultationInfoService: ConsultationInfoService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ symptom }) => {
      this.symptom = symptom;
    });
    this.consultationInfoService.query().subscribe(
      (res: HttpResponse<IConsultationInfo[]>) => {
        this.consultationinfos = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.symptom.id !== undefined) {
      this.subscribeToSaveResponse(this.symptomService.update(this.symptom));
    } else {
      this.subscribeToSaveResponse(this.symptomService.create(this.symptom));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<ISymptom>>) {
    result.subscribe((res: HttpResponse<ISymptom>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}

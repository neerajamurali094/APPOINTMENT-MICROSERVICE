import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';

@Component({
  selector: 'jhi-consultation-info-detail',
  templateUrl: './consultation-info-detail.component.html'
})
export class ConsultationInfoDetailComponent implements OnInit {
  consultationInfo: IConsultationInfo;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ consultationInfo }) => {
      this.consultationInfo = consultationInfo;
    });
  }

  previousState() {
    window.history.back();
  }
}

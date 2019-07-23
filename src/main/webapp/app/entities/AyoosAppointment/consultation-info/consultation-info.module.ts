import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AyoosAppointmentSharedModule } from 'app/shared';
import {
  ConsultationInfoComponent,
  ConsultationInfoDetailComponent,
  ConsultationInfoUpdateComponent,
  ConsultationInfoDeletePopupComponent,
  ConsultationInfoDeleteDialogComponent,
  consultationInfoRoute,
  consultationInfoPopupRoute
} from './';

const ENTITY_STATES = [...consultationInfoRoute, ...consultationInfoPopupRoute];

@NgModule({
  imports: [AyoosAppointmentSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ConsultationInfoComponent,
    ConsultationInfoDetailComponent,
    ConsultationInfoUpdateComponent,
    ConsultationInfoDeleteDialogComponent,
    ConsultationInfoDeletePopupComponent
  ],
  entryComponents: [
    ConsultationInfoComponent,
    ConsultationInfoUpdateComponent,
    ConsultationInfoDeleteDialogComponent,
    ConsultationInfoDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AyoosAppointmentConsultationInfoModule {}

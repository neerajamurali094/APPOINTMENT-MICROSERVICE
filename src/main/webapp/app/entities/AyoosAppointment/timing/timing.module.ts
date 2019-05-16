import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AyoosAppointmentSharedModule } from 'app/shared';
import {
  TimingComponent,
  TimingDetailComponent,
  TimingUpdateComponent,
  TimingDeletePopupComponent,
  TimingDeleteDialogComponent,
  timingRoute,
  timingPopupRoute
} from './';

const ENTITY_STATES = [...timingRoute, ...timingPopupRoute];

@NgModule({
  imports: [AyoosAppointmentSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TimingComponent, TimingDetailComponent, TimingUpdateComponent, TimingDeleteDialogComponent, TimingDeletePopupComponent],
  entryComponents: [TimingComponent, TimingUpdateComponent, TimingDeleteDialogComponent, TimingDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AyoosAppointmentTimingModule {}

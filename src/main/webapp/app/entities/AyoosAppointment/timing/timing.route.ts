import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Timing } from 'app/shared/model/AyoosAppointment/timing.model';
import { TimingService } from './timing.service';
import { TimingComponent } from './timing.component';
import { TimingDetailComponent } from './timing-detail.component';
import { TimingUpdateComponent } from './timing-update.component';
import { TimingDeletePopupComponent } from './timing-delete-dialog.component';
import { ITiming } from 'app/shared/model/AyoosAppointment/timing.model';

@Injectable({ providedIn: 'root' })
export class TimingResolve implements Resolve<ITiming> {
  constructor(private service: TimingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Timing> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Timing>) => response.ok),
        map((timing: HttpResponse<Timing>) => timing.body)
      );
    }
    return of(new Timing());
  }
}

export const timingRoute: Routes = [
  {
    path: 'timing',
    component: TimingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentTiming.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'timing/:id/view',
    component: TimingDetailComponent,
    resolve: {
      timing: TimingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentTiming.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'timing/new',
    component: TimingUpdateComponent,
    resolve: {
      timing: TimingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentTiming.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'timing/:id/edit',
    component: TimingUpdateComponent,
    resolve: {
      timing: TimingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentTiming.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const timingPopupRoute: Routes = [
  {
    path: 'timing/:id/delete',
    component: TimingDeletePopupComponent,
    resolve: {
      timing: TimingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentTiming.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

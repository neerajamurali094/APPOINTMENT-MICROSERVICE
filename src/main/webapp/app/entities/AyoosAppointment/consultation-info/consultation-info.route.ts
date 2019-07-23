import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';
import { ConsultationInfoService } from './consultation-info.service';
import { ConsultationInfoComponent } from './consultation-info.component';
import { ConsultationInfoDetailComponent } from './consultation-info-detail.component';
import { ConsultationInfoUpdateComponent } from './consultation-info-update.component';
import { ConsultationInfoDeletePopupComponent } from './consultation-info-delete-dialog.component';
import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';

@Injectable({ providedIn: 'root' })
export class ConsultationInfoResolve implements Resolve<IConsultationInfo> {
  constructor(private service: ConsultationInfoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ConsultationInfo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConsultationInfo>) => response.ok),
        map((consultationInfo: HttpResponse<ConsultationInfo>) => consultationInfo.body)
      );
    }
    return of(new ConsultationInfo());
  }
}

export const consultationInfoRoute: Routes = [
  {
    path: 'consultation-info',
    component: ConsultationInfoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentConsultationInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'consultation-info/:id/view',
    component: ConsultationInfoDetailComponent,
    resolve: {
      consultationInfo: ConsultationInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentConsultationInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'consultation-info/new',
    component: ConsultationInfoUpdateComponent,
    resolve: {
      consultationInfo: ConsultationInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentConsultationInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'consultation-info/:id/edit',
    component: ConsultationInfoUpdateComponent,
    resolve: {
      consultationInfo: ConsultationInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentConsultationInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const consultationInfoPopupRoute: Routes = [
  {
    path: 'consultation-info/:id/delete',
    component: ConsultationInfoDeletePopupComponent,
    resolve: {
      consultationInfo: ConsultationInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ayoosAppointmentApp.ayoosAppointmentConsultationInfo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

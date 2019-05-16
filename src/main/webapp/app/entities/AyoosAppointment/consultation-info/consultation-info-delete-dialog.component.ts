import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';
import { ConsultationInfoService } from './consultation-info.service';

@Component({
  selector: 'jhi-consultation-info-delete-dialog',
  templateUrl: './consultation-info-delete-dialog.component.html'
})
export class ConsultationInfoDeleteDialogComponent {
  consultationInfo: IConsultationInfo;

  constructor(
    private consultationInfoService: ConsultationInfoService,
    public activeModal: NgbActiveModal,
    private eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.consultationInfoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'consultationInfoListModification',
        content: 'Deleted an consultationInfo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-consultation-info-delete-popup',
  template: ''
})
export class ConsultationInfoDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ consultationInfo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ConsultationInfoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.consultationInfo = consultationInfo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}

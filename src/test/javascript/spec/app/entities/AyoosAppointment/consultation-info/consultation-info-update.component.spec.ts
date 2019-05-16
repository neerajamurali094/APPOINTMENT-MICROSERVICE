/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AyoosAppointmentTestModule } from '../../../../test.module';
import { ConsultationInfoUpdateComponent } from 'app/entities/AyoosAppointment/consultation-info/consultation-info-update.component';
import { ConsultationInfoService } from 'app/entities/AyoosAppointment/consultation-info/consultation-info.service';
import { ConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';

describe('Component Tests', () => {
  describe('ConsultationInfo Management Update Component', () => {
    let comp: ConsultationInfoUpdateComponent;
    let fixture: ComponentFixture<ConsultationInfoUpdateComponent>;
    let service: ConsultationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AyoosAppointmentTestModule],
        declarations: [ConsultationInfoUpdateComponent]
      })
        .overrideTemplate(ConsultationInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsultationInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultationInfoService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new ConsultationInfo(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.consultationInfo = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.update).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );

      it(
        'Should call create service on save for new entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new ConsultationInfo();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.consultationInfo = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.create).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );
    });
  });
});

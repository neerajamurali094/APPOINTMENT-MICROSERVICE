/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AyoosAppointmentTestModule } from '../../../../test.module';
import { ConsultationInfoDetailComponent } from 'app/entities/AyoosAppointment/consultation-info/consultation-info-detail.component';
import { ConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';

describe('Component Tests', () => {
  describe('ConsultationInfo Management Detail Component', () => {
    let comp: ConsultationInfoDetailComponent;
    let fixture: ComponentFixture<ConsultationInfoDetailComponent>;
    const route = ({ data: of({ consultationInfo: new ConsultationInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AyoosAppointmentTestModule],
        declarations: [ConsultationInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConsultationInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsultationInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consultationInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

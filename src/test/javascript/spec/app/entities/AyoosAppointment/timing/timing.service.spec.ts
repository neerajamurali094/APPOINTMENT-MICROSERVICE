/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TimingService } from 'app/entities/AyoosAppointment/timing/timing.service';
import { ITiming, Timing } from 'app/shared/model/AyoosAppointment/timing.model';

describe('Service Tests', () => {
  describe('Timing Service', () => {
    let injector: TestBed;
    let service: TimingService;
    let httpMock: HttpTestingController;
    let elemDefault: ITiming;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      injector = getTestBed();
      service = injector.get(TimingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Timing(0, currentDate, currentDate, currentDate);
    });

    describe('Service methods', async () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_FORMAT),
            startFrom: currentDate.format(DATE_TIME_FORMAT),
            endTo: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should create a Timing', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            day: currentDate.format(DATE_FORMAT),
            startFrom: currentDate.format(DATE_TIME_FORMAT),
            endTo: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            day: currentDate,
            startFrom: currentDate,
            endTo: currentDate
          },
          returnedFromService
        );
        service
          .create(new Timing(null))
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should update a Timing', async () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_FORMAT),
            startFrom: currentDate.format(DATE_TIME_FORMAT),
            endTo: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate,
            startFrom: currentDate,
            endTo: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should return a list of Timing', async () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_FORMAT),
            startFrom: currentDate.format(DATE_TIME_FORMAT),
            endTo: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            day: currentDate,
            startFrom: currentDate,
            endTo: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => expect(body).toContainEqual(expected));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(JSON.stringify([returnedFromService]));
        httpMock.verify();
      });

      it('should delete a Timing', async () => {
        const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

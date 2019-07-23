import { Moment } from 'moment';

export interface IAppointment {
  id?: number;
  trackingId?: string;
  appointmentId?: string;
  chronicDiseaseRef?: string;
  appointmentDateAndTime?: Moment;
  note?: string;
  patientId?: string;
  doctorId?: string;
  consultationInfoId?: number;
  timingId?: number;
  statusId?: number;
}

export class Appointment implements IAppointment {
  constructor(
    public id?: number,
    public trackingId?: string,
    public appointmentId?: string,
    public chronicDiseaseRef?: string,
    public appointmentDateAndTime?: Moment,
    public note?: string,
    public patientId?: string,
    public doctorId?: string,
    public consultationInfoId?: number,
    public timingId?: number,
    public statusId?: number
  ) {}
}

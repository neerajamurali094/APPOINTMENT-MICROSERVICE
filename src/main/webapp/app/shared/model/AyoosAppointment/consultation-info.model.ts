import { ISymptom } from 'app/shared/model/AyoosAppointment/symptom.model';

export interface IConsultationInfo {
  id?: number;
  height?: number;
  weight?: number;
  age?: number;
  symptoms?: ISymptom[];
}

export class ConsultationInfo implements IConsultationInfo {
  constructor(public id?: number, public height?: number, public weight?: number, public age?: number, public symptoms?: ISymptom[]) {}
}

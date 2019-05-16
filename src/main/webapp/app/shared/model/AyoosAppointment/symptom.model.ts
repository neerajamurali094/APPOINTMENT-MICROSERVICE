export interface ISymptom {
  id?: number;
  ref?: string;
  numberOfDaysSuffering?: number;
  consultationInfoId?: number;
}

export class Symptom implements ISymptom {
  constructor(public id?: number, public ref?: string, public numberOfDaysSuffering?: number, public consultationInfoId?: number) {}
}

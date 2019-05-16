import { Moment } from 'moment';

export interface ITiming {
  id?: number;
  day?: Moment;
  startFrom?: Moment;
  endTo?: Moment;
}

export class Timing implements ITiming {
  constructor(public id?: number, public day?: Moment, public startFrom?: Moment, public endTo?: Moment) {}
}

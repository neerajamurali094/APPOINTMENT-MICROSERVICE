import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConsultationInfo } from 'app/shared/model/AyoosAppointment/consultation-info.model';

type EntityResponseType = HttpResponse<IConsultationInfo>;
type EntityArrayResponseType = HttpResponse<IConsultationInfo[]>;

@Injectable({ providedIn: 'root' })
export class ConsultationInfoService {
  public resourceUrl = SERVER_API_URL + 'api/consultation-infos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/consultation-infos';

  constructor(private http: HttpClient) {}

  create(consultationInfo: IConsultationInfo): Observable<EntityResponseType> {
    return this.http.post<IConsultationInfo>(this.resourceUrl, consultationInfo, { observe: 'response' });
  }

  update(consultationInfo: IConsultationInfo): Observable<EntityResponseType> {
    return this.http.put<IConsultationInfo>(this.resourceUrl, consultationInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConsultationInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConsultationInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConsultationInfo[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}

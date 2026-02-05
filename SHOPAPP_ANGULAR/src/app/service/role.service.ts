import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpUtilService } from './http.util.service';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private apiGetRoles = `${environment.apiBaseUrl}/roles`;

  private apiConfig = {
    headers: this.httpUtilService.createHeaders(),
  };

  constructor(
    private http: HttpClient,
    private httpUtilService: HttpUtilService,
  ) {}

  getRoles(): Observable<any> {
    return this.http.get(this.apiGetRoles, this.apiConfig);
  }
}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Product } from '../components/models/product';
import { HttpUtilService } from './http.util.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiGetProducts = `${environment.apiBaseUrl}/products`;

  private apiConfig = {
    headers: this.httpUtilService.createHeaders(),
  };

  constructor(
    private http: HttpClient,
    private httpUtilService: HttpUtilService,
  ) {}

  getProducts(page: number, limit: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('limit', limit.toString());
    return this.http.get<Product[]>(this.apiGetProducts, {
      ...this.apiConfig,
      params,
    });
  }
}

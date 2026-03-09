import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly TOKEN_KEY = 'access_token';
  private jwtHelperService = new JwtHelperService();
  constructor() {}

  getToken(): string {
    return localStorage.getItem(this.TOKEN_KEY) ?? '';
  }

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getUserId(): number {
    const token = this.getToken();
    if (!token) {
      return 0;
    }

    try {
      const userObject = this.jwtHelperService.decodeToken(token);
      if (!userObject || typeof userObject !== 'object') {
        return 0;
      }

      const userId = (userObject as { userId?: string | number }).userId;
      const parsedUserId = Number(userId);
      return Number.isFinite(parsedUserId) ? parsedUserId : 0;
    } catch {
      return 0;
    }
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) {
      return true;
    }

    try {
      return this.jwtHelperService.isTokenExpired(token);
    } catch {
      return true;
    }
  }
}

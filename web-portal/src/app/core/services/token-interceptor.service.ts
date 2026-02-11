import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let token = localStorage.getItem('token');
    const lang = localStorage.getItem('lang') || 'en';

    let headers: any = { 'Accept-Language': lang };

    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }

    const modifiedReq = req.clone({ setHeaders: headers });

    return next.handle(modifiedReq);
  }
}

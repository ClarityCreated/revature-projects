import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginUserService } from './login-user.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private loginUserService: LoginUserService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const isLoggedIn = this.loginUserService.isLoggedIn();
    if (isLoggedIn) {
      const token = this.loginUserService.getToken();
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    return next.handle(request);
  }
}

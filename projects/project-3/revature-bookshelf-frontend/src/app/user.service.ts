import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  endpoint: string = 'http://localhost:9001';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentUser = {};


  userStream: BehaviorSubject<any> = new BehaviorSubject({})
  userName: string | null = null

  decodeToken() {
    let token = localStorage.getItem("token");
    if (token) {
      const decoded: any = jwt_decode(token);
      this.userName = decoded.sub;
      return this.userName;
    }
  }

  doLogin(credentials: any) {
    console.log(credentials);
    const sendCreds = {
      username : credentials.email,
      password : credentials.password
    }
    this.httpClient.post(`${this.endpoint}/login`, sendCreds)
      .subscribe({
        next: (response: any) => {
          localStorage.setItem("token", response.jwt);
          const decoded: any = jwt_decode(response.jwt);
          this.userName = decoded.sub;
          this.userStream.next({
            action: "LOGIN_SUCCESS",
          })
        },
        error: (error => {
          this.userStream.next({
            action: "LOGIN_FAILED",
            error
          })
        })
      })
  }
  

  doLogout() {
    localStorage.removeItem('token')
    this.userStream.next({
      action: "LOGOUT_SUCCESS"
    });
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token) {
      return true;
    }
    else {
      return false;
    }
  }

  getToken() {
    let token = localStorage.getItem("token") || null
    return token;
  }

  doRegister(formData: any) {
    formData.authorities = [{
      authority:
        'ROLE_USER'
    }
    ]
    this.httpClient.post(`${this.endpoint}/api/users`, formData)
      .subscribe({
        next: (response: any) => {
          console.log(response)
          this.userStream.next({
            action: "REGISTER_SUCCESS",
          })
        }
      })
  }

  constructor(private httpClient: HttpClient) { }

    getUser() {
      return this.httpClient.get(`${this.endpoint}/api/users`); 
    }

}
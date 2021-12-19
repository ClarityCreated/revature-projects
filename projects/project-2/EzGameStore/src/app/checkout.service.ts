import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  apiUrl= "http://localhost:8080/api/account";

  constructor(private httpClient: HttpClient) { }
  saveAccount(account:any){
    return this.httpClient.post(this.apiUrl, account)
  }
  updateAccount(account_number: number) {
    return this.httpClient.put(this.apiUrl, account_number)
  }
  
}


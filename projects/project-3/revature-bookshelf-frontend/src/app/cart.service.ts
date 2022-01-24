import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  apiUrl = "http://localhost:9001/api/cart";

  constructor(private httpClient: HttpClient) { }

  cart: any = []
  items: any[] = [];
  cartCount = Object.keys(this.cart).length

  getCart() {
    return this.httpClient.get(`${this.apiUrl}/view`);
  }

  addToCart(bookId: number) {
    return this.httpClient.post(`${this.apiUrl}/add/${bookId}`, []);
  }

  deleteItems(bookId: number) {
    return this.httpClient.delete(`${this.apiUrl}/delete/${bookId}`);
  }

  // clearCart() {
  //   return this.httpClient.delete(`${this.apiUrl}/delete/{bookId}`);
  // }
}

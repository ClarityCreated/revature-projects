import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from "rxjs";

import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  apiUrl = "http://localhost:9001/api/books";



  private books: Array<any> = []


  constructor(private httpClient: HttpClient) { }

  booksStream = new BehaviorSubject({
    books: this.books
  });

  getAllBooks() {
    return this.httpClient.get(this.apiUrl)
  }

  getAvailableBooks() {
    return this.httpClient.get(`${this.apiUrl}/available`)
  }
}

import { Component, OnInit } from '@angular/core';
import { BooksService } from '../books.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  books: Array<any> = []
  spreadBooks: Array<any> = [];
  constructor(private booksService: BooksService, private router: Router) {
  }

  selectedValue = "available";


  getBooks() {
    if (this.selectedValue == "available") {
      this.booksService.getAvailableBooks().subscribe({
        next: (response: any) => {
          console.log(...response)
          this.spreadBooks.push(
            {
              ...response.book,
              quantity : response.quantity,
              price : response.price
            })
          this.books = this.spreadBooks;
        }
      })
    } else if (this.selectedValue == "all") {
      this.booksService.getAllBooks().subscribe({
        next: (response: any) => {
          this.books = response
        }
      })
    }
  }

  ngOnInit(): void {
    this.getBooks();
  }

}


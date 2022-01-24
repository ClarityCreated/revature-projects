import { Component, Input, OnInit } from '@angular/core';
// import { userInfo } from 'os';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-view',
  templateUrl: './book-view.component.html',
  styleUrls: ['./book-view.component.scss']
})
export class BookViewComponent implements OnInit {

  @Input("value") book: any = {}
  constructor(
    private cartService: CartService,
    private userService: UserService,
    private router: Router
  ) { }

  addToCart(bookId) {
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/login'])
    } else {
      this.cartService.addToCart(bookId).subscribe({
        next: (response: any) => {
          console.log(response)
        }
      });
    }
  }
  ngOnInit(): void {
  }

}

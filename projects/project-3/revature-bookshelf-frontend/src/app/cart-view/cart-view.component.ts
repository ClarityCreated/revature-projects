import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.scss']
})
export class CartViewComponent {

  cart: any = []

  constructor(private cartService: CartService) { }

  deleteBook(bookId) {
    this.cartService.deleteItems(bookId).subscribe({
      next: (response: any) => {
        console.log(response)
      }
    });
  }

  ngOnInit() {
    this.cart = this.cartService.getCart().subscribe({
      next: (response: any) => {
        this.cart = response
      }
    });
  }

}

import { Component } from '@angular/core';
import { CartService } from '../cart.service';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-cart-access',
  templateUrl: './cart-access.component.html',
  styleUrls: ['./cart-access.component.scss']
})
export class CartAccessComponent {
  fashoppingcart = faShoppingCart;

  count = 0;

  constructor(private cartService: CartService) { }

  ngDoCheck() {
    this.count = this.cartService.cartCount;
  }


}

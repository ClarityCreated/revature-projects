import { Component, OnInit,Input } from '@angular/core';
import {ProductService} from '../product.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {


  @Input("value") product: any;

  reviews: Array<any> = []

  currentTab: number = 1

  products: Array<any> = []
  constructor(private productService: ProductService, private cartService: CartService) { }

  handleTabChange(e: Event, tabIndex: number, productId: number) {
    e.preventDefault();
    this.currentTab = tabIndex;
    if (this.currentTab === 2) {
      this.productService.getReviews(productId)
        .subscribe({
          next: (response: any) => {
            this.reviews = response;
          }
        })
    }
  }
  handleBuy(event:Event){
  return this.cartService.addToCart(this.products)}

  ngOnInit() {
    this.productService.getAllProducts().subscribe({
      next: (response: any) => {
        this.products = response
      }
    });
  }
}

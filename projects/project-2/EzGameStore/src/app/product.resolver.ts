import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {ProductService} from './product.service';

@Injectable({
  providedIn: 'root'
})
export class ProductResolver {
  constructor(private productService: ProductService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const productId=route.params['productId'];
   return this.productService.getProduct(productId)
  }
}

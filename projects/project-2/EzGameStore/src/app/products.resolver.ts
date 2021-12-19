import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {ProductService} from './product.service';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsResolver {

   constructor(private productService: ProductService) { }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      return this.productService.getAllProducts();
    }
}

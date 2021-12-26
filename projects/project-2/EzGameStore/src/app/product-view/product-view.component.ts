import { Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.scss']
})
export class ProductViewComponent implements OnInit {
  product: any = {
    id: '',
    name: '',
    price:'', 
    image_path: '',
    _available: true,
    description:''
  };
  products:Array<any>=[];
  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
  }
  
  handleDelete(event: Event,productId:number){
     this.productService.deleteProduct(productId)
      .subscribe(response =>{
        this.router.navigate(["add-delete-product"])
      })
  
  }
  

  ngOnInit(){
    this.productService.getAllProducts().subscribe({
      next: (response:any) => {
      this.products=response}
    })
  }

}

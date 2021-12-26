import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import {ProductService} from '../product.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product: any = {
    name: '',
    price:'', 
    image_path: '',
    _available: true,
    description:''
  };

  isEditMode=false;
  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
   }

  ngOnInit(): void {
    this.route.data.subscribe((data:any)=>{
      if(data.product){
      this.product=data.product;
      this.isEditMode = true;
      }
    });
  }
  cancel=false;

  handleSubmit(event: Event, productFormGroup: NgForm){
    const productFormModel=productFormGroup.value
    if (this.isEditMode){
      this.productService.updateProduct(this.product.id, productFormModel)
      .subscribe(response =>{
        this.router.navigate(["add-delete-product"])
        productFormGroup.reset();
      });
    }else
    this.productService.saveProduct(productFormModel)
    .subscribe(response =>{
      this.router.navigate(["add-delete-product"])
      productFormGroup.reset();
    });
  }



 doCancel() {
   this.cancel=true;
   this.router.navigate(['add-delete-product'])
 }

}
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.scss']
})
export class ProductViewComponent implements OnInit {
  products: any = {};
  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.data.subscribe((data:any)=>{
      this.products=data.product;
    })
  }

}

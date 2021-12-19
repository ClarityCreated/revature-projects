import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, NgForm } from '@angular/forms';


@Component({
  selector: 'app-write-review',
  templateUrl: './write-review.component.html',
  styleUrls: ['./write-review.component.scss']
})
export class WriteReviewComponent implements OnInit {


  review: any = {
    author: '',
    stars: '',
    body: ''
  }

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
  }

  handleSubmit(event: Event, reviewFormGroup: NgForm) {
    const reviewFormModel = reviewFormGroup.value
    this.productService.saveReview(reviewFormModel)
      .subscribe(response => {
        this.router.navigate(["/review"])
      });
  }

}

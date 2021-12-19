import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {

  @Input("value")
  reviews: any = {}

  starsArray: Array<number> = []

  ngOnChanges() {
    this.starsArray = Array(this.reviews.stars).fill(0);
  }
}

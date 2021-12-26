import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FormGroup, NgForm } from '@angular/forms';
import {CheckoutService} from '../checkout.service'
@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.scss']
})
export class CheckoutFormComponent implements OnInit {
 account: any = {
    card_holder: '',
    type:'', 
    account_number: '',
    expiration_date: '',
    cvc:'', 
    address: ''
  };

  isEditMode=false;
  constructor(private checkoutService: CheckoutService, private route: ActivatedRoute, private router: Router) {
   }

  ngOnInit(): void {
    this.route.data.subscribe((data:any)=>{
      if(data.account){
      this.account=data.account;
      this.isEditMode = true;
      }
    });
  }

 handleSubmit(event: Event, accountFormGroup: NgForm){
  
  
    const accountFormModel=accountFormGroup.value
  if (this.isEditMode){
      this.checkoutService.updateAccount(accountFormModel)
      .subscribe(response =>{
        this.router.navigate(["app-checkout-form"])
      });
    }else
    this.checkoutService.saveAccount(accountFormModel)
    .subscribe(response =>{
      this.router.navigate(["app-checkout-form"])
      
    });
  }

 cancel=false;

 doCancel() {
   this.cancel=true;
   this.router.navigate(['checkout-form'])
 }




}

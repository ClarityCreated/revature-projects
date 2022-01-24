import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators, FormArray } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
import { AddressService } from '../address.service';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.scss']
})
export class AddressFormComponent implements OnInit {
  addressTypes: Array<any> = ["BILLING","SHIPPING"];

  addressForm: FormGroup = this.formBuilder.group({
    streetName: ["",Validators.required],
    city: ["",Validators.required],
    state: ["",Validators.required],
    postalCode: ["",[Validators.required, Validators.pattern("^[0-9]*$")]],
    type: ["",Validators.required]
    // BEFORE SENDING REQUEST RECAST type into types: Array<any> = [{type:}]
  })
    floatLabelControl = new FormControl('auto');

  //{
// 	"streetName" : "6305 Martin Luther King Jr Way",
// 	"city" : "Seattle",
// 	"state" : "Washington",
// 	"postalCode" : 98118,
// 	"types" : [
//         {
//             "type" : "SHIPPING"
//         },
//         {
//             "type" : "BILLING"
//         }
//     ]
// }
  constructor(
    private formBuilder: FormBuilder,
    private addressService: AddressService,
    private router: Router
    ) { 
      this.formBuilder.group({
        floatLabel: this.floatLabelControl})
    }

  ngOnInit(): void {
    this.addressService.addressStream
    .subscribe({
      next: (e: any) => {
        if (e.action === 'ADDRESS_ADDED')
        this.router.navigate(["/user-profile"]);
        //this.goToPage(`/user-profile`);

      }
    })
  }

  handleSubmit() {
    if (this.addressForm.valid) {
      let submission = this.addressForm.value;
      console.log(submission);
      this.addressService.addAddress(submission)
      // 
      // TODO: userService Login
      // code outline but not exact
      //this.userService.doLogin(loginSubmission)
      // userService.userStream streams out actions
    }
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

}

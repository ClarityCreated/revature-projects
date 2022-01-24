import { Component, Input, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { AddressService } from '../address.service';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';


@Component({
  selector: 'app-address-card',
  templateUrl: './address-card.component.html',
  styleUrls: ['./address-card.component.scss']
})
export class AddressCardComponent implements OnInit {
  
  addressForm = this.fb.group({
    streetName: ['', Validators.required],
    city: ['', Validators.required],
    state: ['', Validators.required],
    postalCode: ['', Validators.required],
  });


  @Input("addressInput") address: any = {};

  constructor(
    private addressService: AddressService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  // addAddress() {
  //   let formData = this.addressForm.value;
  //   this.addressService.addAddress(formData);    
  //   console.log(formData)
  // }

 
  ngOnInit(): void {}

}

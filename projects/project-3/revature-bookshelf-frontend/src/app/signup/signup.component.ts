import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  registerForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    firstName: [''],
    lastName: ['']

  });

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userService.userStream.subscribe({
      next: (e: any) => {
        if (e.action === "REGISTER_SUCCESS") {
          this.router.navigate(["/login"])
        }
      }
    })
  }

  handleSubmit(event: Event) {
    if (this.registerForm.valid) {
      let formData = this.registerForm.value;
      this.userService.doRegister(formData)

    }
  }
}

import { ConditionalExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import { LoginUserService } from '../login-user.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  message: string = "";

  loginForm: FormGroup = this.fb.group({
    username: [''],
    password: ['']
  });

  handleSubmit(event: Event) {
    let credentials = this.loginForm.value;
    this.userService.doLogin(credentials)
  }

  constructor(private fb: FormBuilder, private router: Router, private userService: LoginUserService) { }

  ngOnInit(): void {
    this.userService.userStream
      .subscribe({
        next: (e: any) => {
          if (e.action === "LOGIN_SUCCESS")
            this.router.navigate(["/products"])
          if (e.action === "LOGIN_FAILED") {
            console.log(e);
            this.message = "Login failed"
          }
        }
      })
  }

}


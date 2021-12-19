import { Component } from '@angular/core';
import {NgModule} from '@angular/core';
import { LoginUserService } from './login-user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'NG-EZgoGames';
  userName: string | null = null
  isLoggedIn = false;

  handleLogout() {
    this.userService.doLogout()
  }

  ngOnInit() {
    this.userService.decodeToken();
    this.isLoggedIn = this.userService.isLoggedIn()
    this.userName = this.userService.userName;
    this.userService.userStream
      .subscribe((e: any) => {
        if (e.action === "LOGIN_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
          this.userName = this.userService.userName;
        }
        if (e.action === "LOGOUT_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
          this.userName = null;
          this.router.navigate(['/login-form'])
        }
      })
  }

  constructor(
    private userService: LoginUserService,
    private router: Router) { }

}


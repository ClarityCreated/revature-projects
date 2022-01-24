import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'revature-bookshelf-front';
  userName: string | null = null
  isLoggedIn = false;

  handleLogout() {
    this.userService.doLogout()
  }

  ngOnInit() {
    this.userService.decodeToken();
    this.isLoggedIn = this.userService.isLoggedIn()
    this.userName = this.userService.userName;
    this.userService.userStream.subscribe((e: any) => {
      if(e.action === "LOGIN_SUCCESS") {
        this.isLoggedIn = this.userService.isLoggedIn()
        this.userName = this.userService.userName;
      }
      if(e.action === "LOGOUT_SUCCESS") {
        this.isLoggedIn = this.userService.isLoggedIn()
        this.userName = null;
        this.router.navigate(['/login'])
      }
    })
  }

  constructor(private userService: UserService, private router: Router) {}
  
}

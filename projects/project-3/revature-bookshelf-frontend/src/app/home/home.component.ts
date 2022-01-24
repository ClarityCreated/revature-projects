import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  isLoggedIn = false;


  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
      this.userService.decodeToken();
      this.isLoggedIn = this.userService.isLoggedIn()
      // this.userName = this.userService.userName;
      this.userService.userStream.subscribe((e: any) => {
        if(e.action === "LOGIN_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
          // this.userName = this.userService.userName;
        }
        if(e.action === "LOGOUT_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
          // this.userName = null;
          this.router.navigate([''])
        }
      })
  
  }

}

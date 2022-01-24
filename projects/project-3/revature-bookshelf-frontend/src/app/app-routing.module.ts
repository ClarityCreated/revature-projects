import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AddressCardComponent } from './address-card/address-card.component';
import { AddressFormComponent } from './address-form/address-form.component';
import { BookListComponent } from './book-list/book-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { CartViewComponent } from './cart-view/cart-view.component';
import { CartAccessComponent } from './cart-access/cart-access.component';
import { SearchComponent } from './search/search.component';
import { BookViewComponent } from './book-view/book-view.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'user-profile/add', component: AddressFormComponent, canActivate: [AuthGuard] },
  { path: 'address-card', component: AddressCardComponent },
  { path: 'search', component: SearchComponent },
  { path: 'cart-view', component: CartViewComponent },
  { path: 'cart-access', component: CartAccessComponent },
  { path: 'books', component: BookListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


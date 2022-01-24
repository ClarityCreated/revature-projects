import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';
import { BookListComponent } from './book-list/book-list.component';
import { BookViewComponent } from './book-view/book-view.component';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { SearchComponent } from './search/search.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CartViewComponent } from './cart-view/cart-view.component';
import { CartAccessComponent } from './cart-access/cart-access.component';
import { JwtInterceptorInterceptor } from './jwt-interceptor.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { AddressCardComponent } from './address-card/address-card.component';
import { AddressFormComponent } from './address-form/address-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    CartViewComponent,
    UserProfileComponent,
    SearchComponent,
    CartAccessComponent,
    BookListComponent,
    BookViewComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    PagenotfoundComponent,
    AddressCardComponent,
    AddressFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

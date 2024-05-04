import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { NoteHomeComponent } from './note-home/note-home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NoteAddComponent } from './note-add/note-add.component';
import { FormsModule } from '@angular/forms';
import { RegistrationService } from './Service/registration.service';
import { LoginService } from './Service/login.service';

@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    LoginUserComponent,
    NoteHomeComponent,
    NavbarComponent,
    NoteAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [RegistrationService, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }

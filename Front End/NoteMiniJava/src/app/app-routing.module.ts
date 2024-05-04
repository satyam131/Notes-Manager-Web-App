import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginUserComponent } from './login-user/login-user.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NoteAddComponent } from './note-add/note-add.component';
import { NoteHomeComponent } from './note-home/note-home.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { AuthGuard } from './authGuard/auth.guard';

const routes: Routes = [
  {path: 'login', component: LoginUserComponent},
  {path: 'register', component: RegisterUserComponent},
  {path: '', redirectTo:'login',pathMatch:'full'},
  {path: 'home', canActivate: [AuthGuard] ,  component:NoteHomeComponent},
  {path: 'noteAdd', canActivate: [AuthGuard] ,  component: NoteAddComponent},
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

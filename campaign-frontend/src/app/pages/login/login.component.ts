import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [FormsModule]
})
export class LoginComponent {
  email = '';
  password = '';
  loginError = false;
  errorMessage: string = '';


  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    if (this.authService.login(this.email, this.password)) {
      this.router.navigate(['/admin']);
    } else {
      this.errorMessage = 'Geçersiz e-posta veya şifre';
    }
  }

}

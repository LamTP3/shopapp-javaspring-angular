import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginDTO } from '../dtos/user/login.dto';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  phoneNumber: string;
  password: string;

  constructor(
    private router: Router,
    private userService: UserService,
  ) {
    this.phoneNumber = '33445566';
    this.password = '123456';
  }

  onPhoneNumberChange() {
    console.log('Phone changed:', this.phoneNumber);
  }

  login() {
    // Xử lý đăng nhập ở đây
    const loginDTO: LoginDTO = {
      phone_number: this.phoneNumber,
      password: this.password,
    };
    this.userService.login(loginDTO).subscribe({
      next: (_response: any) => {
        debugger;
        // Xử lý kết quả trả về khi đăng nhập thành công
        this.router.navigate(['/home']);
      },
      complete: () => {
        debugger;
      },
      error: (error: any) => {
        alert('Đăng nhập thất bại: ' + error?.error);
      },
    });
  }
}

import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  // Khai báo các biến tương ứng vơi các trường trong form đăng ký
  phone: string;
  password: string;
  retypePassword: string;
  fullName: string;
  address: string;
  isAccepted: boolean;
  dateOfBirth: Date;

  constructor() {
    this.phone = '';
    this.password = '';
    this.retypePassword = '';
    this.fullName = '';
    this.address = '';
    this.isAccepted = false;
    this.dateOfBirth = new Date();
  }

  onPhoneChange() {
    console.log('Phone changed:', this.phone);
  }

  register() {
    alert(`Đăng ký thành công cho số điện thoại: ${this.dateOfBirth}`);
  }
}

import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginDTO } from '../../dtos/user/login.dto';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { LoginResponse } from '../../responses/user/login.response';
import { TokenService } from 'src/app/service/token.service';
import { RoleService } from 'src/app/service/role.service';
import { Role } from '../../models/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  @ViewChild('loginForm') loginForm!: NgForm;
  phoneNumber: string = '33445566';
  password: string = '123456';

  roles: Role[] = [];
  rememberMe: boolean = true;
  selectedRole: Role | undefined;

  onPhoneNumberChange() {
    console.log('Phone changed:', this.phoneNumber);
  }

  constructor(
    private router: Router,
    private userService: UserService,
    private tokenService: TokenService,
    private roleService: RoleService,
  ) {}

  ngOnInit() {
    this.roleService.getRoles().subscribe({
      next: (roles: Role[]) => {
        debugger;
        this.roles = roles;
        this.selectedRole = roles.length > 0 ? roles[0] : undefined;
      },
      error: (error: any) => {
        debugger;
        console.error('Lấy danh sách vai trò thất bại: ' + error?.error);
      },
    });
  }

  login() {
    // Xử lý đăng nhập ở đây
    const loginDTO: LoginDTO = {
      phone_number: this.phoneNumber,
      password: this.password,
      role_id: this.selectedRole?.id ?? 1,
    };
    this.userService.login(loginDTO).subscribe({
      next: (response: LoginResponse) => {
        debugger;
        const { token } = response;
        this.tokenService.setToken(token);
        // Xử lý kết quả trả về khi đăng nhập thành công
        // this.router.navigate(['/home']);
      },
      complete: () => {
        debugger;
      },
      error: (error: any) => {
        alert(error?.error?.message);
      },
    });
  }
}

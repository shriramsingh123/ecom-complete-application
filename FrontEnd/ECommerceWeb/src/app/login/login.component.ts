import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  loginform!: FormGroup;
  hidePassword = true;

  constructor(
    private formBuider : FormBuilder,
    private authService : AuthService,
    private storageService : UserStorageService,
    private snackBar : MatSnackBar,
    private router : Router
  ){}
  ngOnInit(): void {
    this.loginform = this.formBuider.group({
      username : [null , [Validators.required]],
      password : [null , [Validators.required]]
    })
  }
  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }
  // onSubmit() : void{
  //   const username = this.loginform.get('email').value;
  //   const password = this.loginform.get('password').value;

  //   console.log(username +" : " + password)

  //   const data = this.authService.login(username,password);

  //   console.log("***********Login Successfully******");
  //   console.log(data.value);
  //   console.log("***********Login Successfully******");
  // }

  onSubmit(){
    this.authService.login(this.loginform.value).subscribe(
      (res)=>{
      if(res.userId != null){
        const user ={
          id : res.userId,
          role:res.userRole
        }
        this.storageService.saveUser(user);
        this.storageService.saveToken(res.jwt);

      if(UserStorageService.isAdminLoggedIn()){
        this.router.navigateByUrl('admin/dashboard');
      }else if(UserStorageService.isCustomerLoggedIn()){
        this.router.navigateByUrl('customer/dashboard');
      }
    }
      },
      (error) => {
        console.log(error);
        this.snackBar.open('Bad Credentials','ERROR',{duration:5000});
      }
    );
  }
}

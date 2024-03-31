import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { Dialog } from '@angular/cdk/dialog';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.scss']
})
export class PlaceOrderComponent implements OnInit{

  orderForm!:FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private customerService: CustomerService,
    private router: Router,
    private dialog: Dialog
  ){}

  ngOnInit(): void {
    this.orderForm = this.fb.group({
      address: [null,[Validators.required]],
      orderDescription: [null]
    })
  }

  placeOrder(){
    this.customerService.placeOrder(this.orderForm.value).subscribe(res => {
      if(res.id != null){
        this.snackBar.open("Order placed successfully ", "Close", {duration:5000})
        this.router.navigateByUrl("/customer/my-orders");
        this.closeForm();
      }else{
        this.snackBar.open("Something went wrong","Close", {duration:5000})
      }
    })
  }

  closeForm(){
    this.dialog.closeAll();
  }

}

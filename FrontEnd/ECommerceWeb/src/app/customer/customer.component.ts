import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from './services/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent {

  products : any =[];
  searchProductForm! : FormGroup;

  constructor(private customerService : CustomerService,
    private fb : FormBuilder,
    private sanckBar : MatSnackBar){}

  ngOnInit(): void {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title: [null , [Validators.required]]
    })
  }

  getAllProducts(){
    this.products = [];
    this.customerService.getAllProducts().subscribe(res => {
      console.log(res);
      res.forEach((element : any)=> {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      });
    })
  }

  submitForm(){
    this.products = [];
    const title = this.searchProductForm.get('title').value;
    this.customerService.getAllProductsByName(title).subscribe(res => {
      console.log(res);
      res.forEach((element : any)=> {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      });
    })
  }

}

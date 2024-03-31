import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  products : any =[];
  searchProductForm! : FormGroup;

  constructor(private adminService : AdminService,
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
    this.adminService.getAllProducts().subscribe(res => {
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
    this.adminService.getAllProductsByName(title).subscribe(res => {
      console.log(res);
      res.forEach((element : any)=> {
        element.processedImg = 'data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      });
    })
  }

  deleteProduct(productId : any){
    this.adminService.deleteProduct(productId).subscribe(res=>{
      if(res.body == null) {
        this.sanckBar.open('Product deleted Successfully !','Close',{
          duration:5000
        });
        this.getAllProducts();
      }else {
        this.sanckBar.open(res.message,'Close', {
          duration:5000,
          panelClass: 'error-snackbar'
        });
      }
    })
  }


}

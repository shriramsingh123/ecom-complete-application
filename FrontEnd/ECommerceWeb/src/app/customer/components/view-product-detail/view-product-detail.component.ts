import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-view-product-detail',
  templateUrl: './view-product-detail.component.html',
  styleUrls: ['./view-product-detail.component.scss']
})
export class ViewProductDetailComponent implements OnInit{

  productId: number = this.activatedRoute.snapshot.params['productId'];

  product:any;
  FAQS: any[] = [];
  reviews: any[] = [];

  constructor(private snackBar : MatSnackBar,
    private customerService : CustomerService,
    private activatedRoute : ActivatedRoute){}

    ngOnInit(){
      this.getProductDetailById();
    }

  // getProductDetailById(){
  //   this.customerService.getProductDetailById(this.productId).subscribe(res=>{
  //     this.product = res.productDto;
  //     this.product.processedImg = 'data:image/png;base64,' + res.productDto.byteImg;

  //     this.FAQS = res.faqDtoList;

  //     res.reviewDtoList.foreach(element => {
  //       element.processedImg = 'data:image/png;base64;,'+element.returnedImg;
  //       this.reviews.push(element);
  //       console.log(res);
  //     })
  //   })
  // }

  getProductDetailById() {
    this.customerService.getProductDetailById(this.productId).subscribe(res => {
      this.product = res.productDto;
      this.product.processedImg = 'data:image/png;base64,' + res.productDto.byteImg;
  
      this.FAQS = res.faqDtoList;
  
      if (Array.isArray(res.reviewDtoList)) {
        res.reviewDtoList.forEach(element => {
          element.processedImg = 'data:image/png;base64;,' + element.returnedImg;
          this.reviews.push(element);
        });
      } else {
        console.warn("reviewDtoList is not an array");
      }
    });
  }


  addToWishlist(){
    const wishlistDto = {
      productId : this.productId,
      userId : UserStorageService.getUserId()
    }

    this.customerService.addProductToWishlist(wishlistDto).subscribe(res=>{
      if(res.id != null){
        this.snackBar.open("Product Addded To Wishlist Successfully!",'Close',{
          duration: 5000
        })
      }else{
        this.snackBar.open("Already in Wishlist",'ERROR',{
          duration:5000
        })
      }
    })
  }
  

}

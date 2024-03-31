import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-view-ordered-products',
  templateUrl: './view-ordered-products.component.html',
  styleUrls: ['./view-ordered-products.component.scss']
})
export class ViewOrderedProductsComponent {

  orderId:any = this.activatedroute.snapshot.params['orderId'];
  orderedProductDetailsList = [];
  totalAmount: any;

  constructor(private activatedroute: ActivatedRoute,
    private customerService: CustomerService){}

    ngOnInit(){
      this.getOrderedProductsDetailsByOrderId();
    }

    // getOrderedProductsDetailsByOrderId(){
    //   this.customerService.getOrderedProducts(this.orderId).subscribe(res=>{
    //     res.productDtoList.foreach((element)=>{
    //       element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
    //       this.orderedProductDetailsList.push(element);
    //     });
    //     this.totalAmount = res.orderAmount;
    //   })
    // }

    getOrderedProductsDetailsByOrderId() {
      this.customerService.getOrderedProducts(this.orderId).subscribe(res => {
        if (Array.isArray(res.productDtoList)) {
          res.productDtoList.forEach((element) => {
            element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
            this.orderedProductDetailsList.push(element);
          });
        } else {
          console.error("productDtoList is not an array!");
        }
        this.totalAmount = res.orderAmount;
      });
    }
    
}

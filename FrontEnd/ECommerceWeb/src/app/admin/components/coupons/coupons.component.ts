import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss']
})
export class CouponsComponent implements OnInit{

  coupons: any;

  constructor(private adminService: AdminService){}

  ngOnInit(): void {
    this.getCoupons();
  }


  getCoupons(){
    this.adminService.getCoupons().subscribe(res=> {
      console.log(res);
      this.coupons = res;
    })
  }

}

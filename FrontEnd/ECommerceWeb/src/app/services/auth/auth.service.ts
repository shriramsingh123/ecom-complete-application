import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient,
    private userStorageService : UserStorageService) { }

  register(signupRequest:any) : Observable<any>{
    return this.http.post(BASIC_URL+"sign-up",signupRequest);
  }

  // login(username : string , password : string) : any {
  //   const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  //   const body = {username,password};

  //   console.log("in login 1");
  //   return this.http.post(BASIC_URL+'authenticate',body,{headers , observe:'response'}).pipe(
  //     map( (res) => {
  //   console.log("in login 2");
  //       const token = res.headers.get('authorization').substring(7);
  //       const user = res.body;

  //       if(token && user) {
  //   console.log("in login 3");
  //         this.userStorageService.saveToken(token);
  //         this.userStorageService.saveUser(user);
  //   console.log("in login 4");
  //         return true;
  //       }
  //   console.log("in login 5");
  //       return false;
  //     })
  //   )
  // }

  login(loginRequest : any):Observable<any> {
    return this.http.post(BASIC_URL + "authenticate",loginRequest);
  }

  getOrderByTrackingId(trackingId: number): Observable<any>{
    return this.http.get(BASIC_URL + `order/${trackingId}`);
  }
}

import { Component, OnInit, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http'
import { Observable } from 'rxjs/Observable'
import { User } from '../models/index';
import { DashboardComponent } from '../dashboard/index';
import 'rxjs/Rx'
import { UserDetails } from '../bo/userDetails'
import { UserService } from '../services/user.service'

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
})

export class LoginComponent {

    model: any = {};
    loading = false;
    returnUrl: string;
    error = '';
    userDetails: UserDetails[] = [];


    constructor(
        private http: Http,
        private route: ActivatedRoute,
        private router: Router,
        private _userService: UserService) { }


    login() {
        console.log('Login pressed!')
        let userDetails = {
            'username': this.model.username,
            'password': this.model.password
        }
        this._userService.setUserName(this.model.username)
        if (userDetails.username === "admin") {
            this.http.post('/api' + '/user/login', userDetails).subscribe(
                (res: any) => {
                    this.router.navigate(['deal']);
                }
            )
        } else {
            /*this.http.post( '/api'+'/user/otherUsers', userDetails)
              .map((res:Response) => res.json())
       .subscribe(
           data => {
           const text = data;  
           text.forEach(element => {            
               this.userDetails.push(element) 
           });         
           this._userService.setUserDetails(this.userDetails)
           this._userService.setUserName(this.model.username)
           this.router.navigate(['dashboard']);
        });*/
           // this._userService.getUserDetails(userDetails)



            
            this._userService.setUserName(this.model.username)
            this.router.navigate(['dashboard']);
        }
    }

    private handError(error: any) {
        console.error('post error: ', error)
        return Observable.throw(error.statusText)
    }
}

import { Component } from '@angular/core' 
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserDetails } from '../bo/userdetails';

import { Http, Response, Headers, RequestOptions } from '@angular/http';

@Component({
    moduleId: module.id,
    templateUrl: 'review.component.html',
})
export class ReviewComponent{
    reviewCompleted:string=""
    claimedDetail:UserDetails;
    username:string;
    public userDetails: UserDetails[];
    

     constructor(private _userService: UserService,private http: Http,
        private route: ActivatedRoute,
        private router: Router) {
        this.claimedDetail=_userService.getClaimedDetail();
        

        }

    submit():void{
console.log(this.claimedDetail.dealId +" "+ this.claimedDetail.reviewType);
        
        let dealDetail ={

      "dealId": this.claimedDetail.dealId,
    "reviewType": this.claimedDetail.reviewType,
    "sla": this.claimedDetail.sla,
    "taskName": this.claimedDetail.taskName,
    "priority": this.claimedDetail.priority,
    "status": this.claimedDetail.status,
      "activityTaskId": this.claimedDetail.activityTaskId,
      "username":this._userService.getUserName()
  }
       
        //                    let userDetails = {
        //     'username': "userC",
        //     'password': "userC"
        // }

      this.http.post( '/api'+'/user/completeClaim',dealDetail).subscribe(
            (res: any) => {
                console.log("successfully submited")
            this.reviewCompleted="Review successfully submited"
              this.router.navigate(['dashboard']);  
             })
          
     
    }
    logout(): void {
        console.log('Login pressed!')

        this.router.navigate(['login']);
    }


   
}
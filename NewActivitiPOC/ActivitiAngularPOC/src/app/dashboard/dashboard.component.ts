import { Component } from '@angular/core';
import { UserDetails } from '../bo/userdetails';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginComponent } from '../login/index';
import { Http,Response } from '@angular/http';
import { UserService } from '../services/user.service';

@Component({
    moduleId: module.id,
    templateUrl: 'dashboard.component.html'
})

export class DashboardComponent {
    public dashModel: any = {};
        model: any = {};
    public dashDetails: string[] = [];
    public userDetails: UserDetails[];
    public isClaimed: boolean = true;
    public claimDetail:UserDetails
    //public username:string

    constructor(private _userService: UserService, private http: Http,
        private route: ActivatedRoute,
        private router: Router) {
                    let userDetails = {
            'username': _userService.getUserName(),
            'password': _userService.getUserName()
        }
        console.log("DashBoard Username "+userDetails.username);
        this.userDetails = this._userService.getUserDetails(userDetails);
    }
    toggleClaim(details: UserDetails): void {

  let dealDetail={
      "dealId": details.dealId,
    "reviewType": details.reviewType,
    "sla": details.sla,
    "taskName": details.taskName,
    "priority": details.priority,
    "status": details.status,
    "activityTaskId": details.activityTaskId,
    "username": this._userService.getUserName()
  }
 this._userService.setClaimedDetail(details);

  console.log("deal message")
  console.log(dealDetail)

      this.http.post('/api'+'/user/userClaim', dealDetail).subscribe(
            (res: any) => {
                console.log("claim successfully submited")
                 this.router.navigate(['review']);
            })

    }

    logout(): void {
        console.log('Login pressed!')

        this.router.navigate(['login']);
    }

}








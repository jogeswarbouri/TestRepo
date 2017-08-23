import { Injectable } from '@angular/core'
import { UserDetails } from '../bo/userDetails'
import { Router, ActivatedRoute } from '@angular/router';
import { LoginComponent } from '../login/index';
import { Http,Response } from '@angular/http';

@Injectable()
export class UserService{
    userDetailsMap: Map<string, {}> = new Map<string, {}>()
    userDetailsList: UserDetails[] = []
    claimedDetail:UserDetails;
    username:string;
      constructor(private http: Http,
        private route: ActivatedRoute,
        private router: Router) {
            
        }

      getUserDetails(userDetails: any) {
          // this.userDetailsList = userDetails
          this.http.post('/api' + '/user/otherUsers', userDetails)
              .map((res: Response) => res.json())
              .subscribe(
              data => {
                  const text = data;
                  text.forEach(element => {
                      this.userDetailsList.push(element)
                  });
                  // this._userService.setUserDetails(this.userDetails)
                  // this._userService.setUserName(this.model.username)
              })
          return this.userDetailsList
      }

    setUserDetails( userDetails :UserDetails[]){
        return this.userDetailsList = userDetails
    }
    setClaimedDetail(claimedDetail:UserDetails){
       this.claimedDetail=claimedDetail
    }
    getClaimedDetail(){
        return this.claimedDetail
    }
   setUserName(username:string){
       this.username=username;
   }
   getUserName(){
       return this.username;
   }



    storeUserDetails(id: string, userDetails: {}){
        this.userDetailsMap.set(id, userDetails)
    }

    retrieveUserDetails(id:string): {}{
        return this.userDetailsMap.get(id)
    }

}
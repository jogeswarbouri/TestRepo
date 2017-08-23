import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { DealSetupComponent } from './deal/index';
 
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { LoginComponent } from './login/index';
import { DashboardComponent } from './dashboard/index';
import { UserService } from './services/user.service'
import { ReviewComponent} from './review/index'

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
       ],
      
    declarations: [
        AppComponent,
        LoginComponent,
        DealSetupComponent,
        DashboardComponent,
        ReviewComponent
        
    ],  
    providers: [UserService],
    bootstrap: [AppComponent]
})
 
export class AppModule { }

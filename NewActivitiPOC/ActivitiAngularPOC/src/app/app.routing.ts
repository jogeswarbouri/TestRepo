import { Routes, RouterModule } from '@angular/router';
import { DealSetupComponent } from './deal/index';
import { LoginComponent } from './login/index';
import { DashboardComponent } from './dashboard/index';
import { ReviewComponent} from './review/index';

const appRoutes: Routes = [
       { path: 'login', component: LoginComponent },       
         { path: '', redirectTo: '/login', pathMatch: 'full' },
         { path: 'deal', component: DealSetupComponent },
         { path: 'dashboard', component: DashboardComponent },
         { path: 'review', component: ReviewComponent }

   ];
export const routing = RouterModule.forRoot(appRoutes);
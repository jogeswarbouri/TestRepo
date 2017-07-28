import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { DealSetupComponent } from './dealSetup/deal_setup.component';
import { DealDisplayComponent } from './dealdisplay/deal_display.component';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import {DealDisplayService} from './dealdisplay/deal_display.service';
import {ModalModule} from 'ng2-modal';
import {KeysPipe} from './pipe/deal_display_keyValue.pipe';



@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    DealSetupComponent,
    DealDisplayComponent,
    FileSelectDirective,
    KeysPipe

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ModalModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'dealsetup', component: DealSetupComponent },
      { path: 'dealdisplay', component: DealDisplayComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' }
    ]
    )
  ],
  providers: [DealDisplayService],
  bootstrap: [AppComponent]
})
export class AppModule { }

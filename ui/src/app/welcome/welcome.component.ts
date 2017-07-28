import {Component} from '@angular/core';
@Component({
    selector : 'welcome',
    moduleId : module.id,
    templateUrl : 'welcome.component.html',
    styleUrls : ['welcome.component.css']
})
export class WelcomeComponent{
    pageTitle : string = "Welcome to Deal SetUp Page";
}
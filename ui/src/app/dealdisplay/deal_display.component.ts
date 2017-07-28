import {Component,OnInit,ViewChild} from '@angular/core';
import { DealDisplayService} from './deal_display.service';
import {NguiPopupComponent} from '@ngui/popup';
//import { ModalService } from '../_services/index';
//import {Router} from '@angular/router';
@Component({
    selector : 'deal-display',
    moduleId : module.id,
    templateUrl : 'deal_display.component.html'
})
export class DealDisplayComponent implements OnInit{
    //pageTitle : string = 'Deal Display';
    loanData : any[];
    modalData : any[];
    modalCount : number = 10;
    errorMessage : any;
    constructor(private _dealDisplayService : DealDisplayService){}
    ngOnInit() : void{
        this._dealDisplayService.getData().subscribe(loanData => {this.loanData = loanData,console.log(this.loanData);console.log(this.loanData.length)},
       error => this.errorMessage = <any>error)
    }

    view(event,loan,myModal):any{
        this.modalData = JSON.parse(loan.result);
        myModal.open();
        event.preventDefault();
    }

}
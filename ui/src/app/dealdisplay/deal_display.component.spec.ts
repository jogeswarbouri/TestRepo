import {TestBed,inject,fakeAsync,tick,async} from '@angular/core/testing';
import {DealDisplayComponent} from './deal_display.component';
import {KeysPipe} from '../pipe/deal_display_keyValue.pipe';
import {Pipe,PipeTransform} from '@angular/core';
import {NguiPopupComponent} from '@ngui/popup';
import {ModalModule} from 'ng2-modal';
import {HttpModule,Http,Response,ResponseOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import {Observable,ReplaySubject} from 'rxjs';
import {DealDisplayService} from './deal_display.service';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
//import {RouterModule,Router} from '@angular/router';


describe('Component : DealDisplayComponent',()=>{
    let component : DealDisplayComponent;
    let de : DebugElement;
    let el:      HTMLElement;
    
   beforeEach(()=>{
       TestBed.configureTestingModule({
           declarations:[DealDisplayComponent,KeysPipe],
           imports : [ModalModule,HttpModule],
           providers :[DealDisplayService]
           
       });

        let fixture = TestBed.createComponent(DealDisplayComponent);
        component = fixture.componentInstance;
       
        de = fixture.debugElement.query(By.css('table'));
        console.log(de);
        el = de.nativeElement;
         console.log("SDFsd");
        console.log(el);
        

   });

    it('should have component defined',inject([DealDisplayService] , (service: DealDisplayService)=>{
        expect(component).toBeDefined;
    }));
   
  it('click view should open modal',fakeAsync(()=>{
      component.ngOnInit();
        let subject = new ReplaySubject();
        let dealDisplayService = TestBed.get(DealDisplayService);
        var loanData=[{"loanId":"4193","jData":null,"result":"{\"Primary Loan ID\":\"4193\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"9556","jData":null,"result":"{\"Primary Loan ID\":\"9556\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"9708","jData":null,"result":"{\"Primary Loan ID\":\"9708\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10036","jData":null,"result":"{\"Primary Loan ID\":\"10036\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10372","jData":null,"result":"{\"Primary Loan ID\":\"10372\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"1\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10713","jData":null,"result":"{\"Primary Loan ID\":\"10713\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"1\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10916","jData":null,"result":"{\"Primary Loan ID\":\"10916\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"}];
        subject.next(loanData);
        spyOn(dealDisplayService,"getData").and.returnValue(subject);
		
		let myfixture = TestBed.createComponent(DealDisplayComponent);
		
		myfixture.detectChanges();

        var link = <HTMLElement>myfixture.nativeElement.getElementsByClassName('view-link')[0];
       
        link.click();
		tick();
        myfixture.detectChanges();
        var temp = <HTMLElement>myfixture.nativeElement.getElementsByClassName("modal")[0];
        expect(temp.style.display).toContain("block");
    }))
    

})


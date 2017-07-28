import  {TestBed} from '@angular/core/testing';
import {FormGroup,ReactiveFormsModule} from '@angular/forms';
import {DealSetupComponent} from './deal_setup.component';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import {NguiPopupComponent} from '@ngui/popup';
import {ModalModule} from 'ng2-modal';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';


describe('Component : DealSetupComponent',()=>{
    let component :DealSetupComponent;
        let de : DebugElement;
        let el: HTMLElement;

    beforeEach(()=>{
        TestBed.configureTestingModule({
            declarations : [DealSetupComponent,FileSelectDirective],
            imports:[ReactiveFormsModule,ModalModule]
        });

        const fixture = TestBed.createComponent(DealSetupComponent);
        component = fixture.componentInstance;
          de = fixture.debugElement.query(By.css('*'));
        console.log(de);
        el = de.nativeElement;
         console.log("SDFsd");
        console.log(el);
    })

    it('should have component defined',()=>{
        expect(component).toBeDefined;
    })

    it('should create a `FormGroup` comprised of `FormControl`s', () => {
        component.ngOnInit();
        expect(component.dealSetupForm instanceof FormGroup).toBe(true);
    });

    it('should test the defaultvalue of dealname input ',()=>{
        component.ngOnInit(); 
        expect(component.dealSetupForm.controls['dealName'].value).toBe('');
    })

    it('should test the defaultvalue of clientContact input ',()=>{
        component.ngOnInit();
        expect(component.dealSetupForm.controls['clientContact'].value).toBeNull;
    })

    it('should  set the payload to the values',()=>{
         var event = new Event('click'); 
         var errMsgModal = {
             open : jasmine.createSpy('modal.open').and.returnValue({ result: { then: jasmine.createSpy('modal.result.then') } })
         }

        component.ngOnInit();
        component.dealSetupForm.controls['dealName'].setValue('First deal');
        component.dealSetupForm.controls['clientContact'].setValue('0987654321');
        component.dealSetupForm.controls['fileName'].setValue('C:\Users\twinkle.puri\Downloads\PFExemptedEsttList.xlsx');
        component.onUploadAll(event,errMsgModal);
        expect(component.payload).toEqual(JSON.stringify({dealName: 'First deal',fileName : 'C:\Users\twinkle.puri\Downloads\PFExemptedEsttList.xlsx', clientContact: '0987654321'}));

    })

})

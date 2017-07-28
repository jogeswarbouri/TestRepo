import { TestBed, async } from '@angular/core/testing';
//import {MockRouter} from '@angular/core/testing';
import { AppComponent } from './app.component';
//import {RouterModule,Router} from '@angular/router';
import { Location, CommonModule } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { RouterLinkStubDirective } from '../shared/router_link_directive_stub';


describe('AppComponent', () => {
    let comp: AppComponent;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                AppComponent
            ],
            imports: [RouterTestingModule],
            providers: [{ provide: RouterTestingModule, useValue: RouterLinkStubDirective }
            ]
        }).compileComponents().then(() => {
            let fixture = TestBed.createComponent(AppComponent);
            comp = fixture.componentInstance;
        });
    }));


    it('can a component be defined', () => {
        let myfixture = TestBed.createComponent(AppComponent);
        comp = myfixture.componentInstance;
        expect(comp).toBeDefined;
    });



    it(`should have as title 'app works!'`, async(() => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.debugElement.componentInstance;
        expect(app.title).toEqual('app works!');
    }));

});

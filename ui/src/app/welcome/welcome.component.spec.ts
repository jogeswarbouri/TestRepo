import {TestBed} from '@angular/core/testing';
import {WelcomeComponent} from '../welcome/welcome.component';

describe('Component: Welcome Component',()=>{
    let component : WelcomeComponent
    beforeEach(()=>{
        TestBed.configureTestingModule({
            declarations : [WelcomeComponent],
            imports:[]
        });

        const fixture = TestBed.createComponent(WelcomeComponent);
        component = fixture.componentInstance;
})

it('is the component defined',()=>{
    expect(component).toBeDefined;
})

it('is the page title set correct',()=>{
    expect(component.pageTitle).toBe("Welcome to Deal SetUp Page");
})

});
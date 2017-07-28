import {MockBackend} from '@angular/http/testing';
import { inject, TestBed,fakeAsync ,tick} from '@angular/core/testing';
import { Http, ConnectionBackend, BaseRequestOptions, Response, ResponseOptions, Headers } from '@angular/http';
import {DealDisplayService} from './deal_display.service';
import { Router } from '@angular/router';

describe('Service : DealDisplayService',()=>{
    beforeEach(()=>{
        TestBed.configureTestingModule({
            providers : [{
                provide : Http , useFactory:(backend:ConnectionBackend,defaultOptions:BaseRequestOptions)=>{
                    return new Http(backend,defaultOptions)},deps:[MockBackend,BaseRequestOptions]
                },
                {provide:DealDisplayService,useClass:DealDisplayService},
                {provide : MockBackend,useClass : MockBackend },
                {provide : BaseRequestOptions,useClass : BaseRequestOptions},
                
            ]
        })
    });

it('should retrieve Deal',
        inject([DealDisplayService, MockBackend], fakeAsync((dealDisplayService: DealDisplayService, mockBackend: MockBackend) => {
            let res: Object;
            mockBackend.connections.subscribe(c => {
                console.log(c.request.url);
                expect(c.request.url).toBe('http://localhost:9090/dealsetup/display');
                let output = new ResponseOptions({ body: [{"loanId":"4193","jData":null,"result":"{\"Primary Loan ID\":\"4193\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"9556","jData":null,"result":"{\"Primary Loan ID\":\"9556\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"9708","jData":null,"result":"{\"Primary Loan ID\":\"9708\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10036","jData":null,"result":"{\"Primary Loan ID\":\"10036\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10372","jData":null,"result":"{\"Primary Loan ID\":\"10372\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"1\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10713","jData":null,"result":"{\"Primary Loan ID\":\"10713\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"1\",\"Title Interest\":\"Y\"}","count":"0"},{"loanId":"10916","jData":null,"result":"{\"Primary Loan ID\":\"10916\",\"Postal Code\":\"x\",\"Property Type\":\"SFR\",\"Address\":\"x\",\"Number of Units\":\"1\",\"State\":\"x\",\"Borrowing Entity Name\":\"x\",\"City\":\"x\",\"Number of Guarantors \":\"2\",\"Title Interest\":\"Y\"}","count":"0"}]});
                c.mockRespond(new Response(output));
            });
             dealDisplayService.getData().subscribe((response:Response[]) => {
                 res = response;
                  expect(response.length).toBe(7);
             });
           
        }))
    );


    it('deal throws error',
        inject([DealDisplayService, MockBackend], fakeAsync((dealDisplayService: DealDisplayService, mockBackend: MockBackend) => {
            let res: Response;
            mockBackend.connections.subscribe(c => {
                expect(c.request.url).toBe('http://localhost:9090/dealsetup/display');
                let output = new ResponseOptions({ body: [{
        "loanId": 1,
        "productName": "Leaf Rake",
        "productCode": "GDN-0011",
        "releaseDate": "March 19, 2016",
        "description": "Leaf rake with 48-inch wooden handle.",
        "price": 19.95,
        "starRating": 3.2,
        "imageUrl": "http://www.pexels.com/photo/white-and-yellow-flower-with-green-stems-36764/",
          "status" : 500
    }]});
                c.mockError(new Response(output));
            });
            dealDisplayService.getData().subscribe(null, () => {
                expect(true).toBeTruthy();
            });
            tick();
        }))
    );

})

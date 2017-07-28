import {Injectable} from '@angular/core';
import {Http,Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class DealDisplayService{
    private _loanUrl = 'http://localhost:9090/dealsetup/display';
    constructor(private _http : Http){}
    getData():Observable<any[]>{
        return this._http.get(this._loanUrl).map((response:Response)=>response.json())
    }
}
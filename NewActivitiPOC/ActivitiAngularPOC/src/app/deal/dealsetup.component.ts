import { Component, OnInit } from '@angular/core';
import { User, Task, Review, Priorty } from '../models/index';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

@Component( {
    moduleId: module.id,
    templateUrl: 'dealsetup.component.html'
} )

export class DealSetupComponent {
    public reviewTypes: string[] = [];
    public taskTypes: string[] = [];
    public taskList: string[];
    submitResult: string = "swdsf";
    isSuccess : boolean = false;
    public hasReviewTypeError = false;
    public hasTaskTypeError = false;
    public hasPriorityError = false;

    priorityList: string[] = ["High", "Midium"];
    priortyModel = new Priorty( "dafault", "0" );
    public reviewId: number;
    priorities = Array<Priorty>();

    constructor( private http: Http,
        private route: ActivatedRoute,
        private router: Router
    ) {
        this.getReviewTypes()
            .subscribe(
            data => { this.reviewTypes = data },
            err => console.log( 'get error: ', err )
            )
        this.priorities = Array<Priorty>();
        this.priorities.push( new Priorty( "High", "100" ) );
        this.priorities.push( new Priorty( "Medium", "50" ) );
    }

    reviewModel = new Review( "Credit Review" );
    taskModel = new Task( "dafault" );

    submitDeal() : string{
        let dealDetails = {
            'reviewType': this.reviewModel.reviewType,
            'task': this.taskModel.task,
            'priority': this.priortyModel.pid
        }
        this.http.post( '/api' + '/deal/createDeal', dealDetails ).subscribe(
            (res:any)=>{
               this.isSuccess = true; 
               this.submitResult = res._body;
               console.log("string " + this.submitResult)
               console.log("Deal successful submited")
           }  
        )
        return this.submitResult;
    }


    getReviewTypes(): Observable<any> {
        return this.http.get( '/api' + '/deal/reviewType' )
            .map( this.extractReviewType )
            .catch( this.handError )
    }

    private extractReviewType( res: Response ) {
        let body = res.json();
        return body || {}
    }

    private handError( error: any ) {
        console.error( 'post error: ', error )
        return Observable.throw( error.statusText )
    }


    validateReviewType( value ) {
        if ( value === 'default' )
            this.hasReviewTypeError = true
        else {
            this.hasReviewTypeError = false
        }
        if ( value === 'Credit Review' ) {
            this.reviewId = 1;
            this.subscribeTaskType();

        }
        if ( value === 'Compliance Review' )
            this.reviewId = 2;
        this.subscribeTaskType();
        if ( value === 'Asset Review' )
            this.reviewId = 3
        this.subscribeTaskType();
    }
    validateTaskType( value ) {
        if ( value === 'default' )
            this.hasTaskTypeError = true
        else
            this.hasTaskTypeError = false
    }
    validatePriority( value ) {
        if ( value === "default" )
            this.hasPriorityError = true;
        else
            this.hasPriorityError = false;
    }
    getTaskTypes(): Observable<any> {
        return this.http.get( '/api' + '/deal/taskByName/' + this.reviewId )
            .map( this.extractTaskType )
            .catch( this.handError )
    }

    private extractTaskType( res: Response ) {
        let body = res.json();
        return body || {}
    }
    subscribeTaskType() {
        this.getTaskTypes().subscribe(
            data => { this.taskTypes = data, console.log( data ) },
            err => console.log( 'get error: ', err ) );
    }

    logout(): void {
        console.log( 'Login pressed!' )

        this.router.navigate( ['login'] );
    }

}








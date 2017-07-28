import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { Observable } from 'rxjs/Observable';
import { NguiPopupComponent } from '@ngui/popup';
import 'rxjs/add/operator/map';

@Component({
    selector: 'deal-setup',
    moduleId: module.id,
    templateUrl: 'deal_setup.component.html'
})
export class DealSetupComponent implements OnInit {
    //uploadFile: any;
    public uploader: FileUploader;
    options = { headers: [] };
    headers = [{ 'Content-Type': 'multipart/form-data' }];
    pageTitle: string = "Deal Setup Page";
    payload: string;
    dealSetupForm: FormGroup;
    file: File
    value: any;
    fileExtension: any;
    validExtensions: any = ['xlsx', 'csv'];
    fileExtensionValid: boolean = false;
    fileExtensionMessage: any;
    errorMsg: string;
    uploadFailed: string = "";
    constructor(private _fb: FormBuilder) { }

    ngOnInit(): void {
        this.dealSetupForm = this._fb.group({
            dealName: ['', Validators.required],
            fileName: ['',Validators.required],
            clientContact: ['', Validators.required]
        })

        this.options.headers = this.headers;
        this.uploader = new FileUploader({ url: 'http://localhost:9090/dealsetup/create' });
        this.uploader.setOptions(this.options);
        this.uploader.onBuildItemForm = (item, form) => {
            console.log('inonbuilditemform');
            let desc = Object.assign({});
            desc['dealName'] = this.dealSetupForm.get('dealName').value;
            desc['clientContact'] = this.dealSetupForm.get('clientContact').value;
            form.append("desc", JSON.stringify(desc));
            console.log('inonbuilditemform' + form);
        }

        this.uploader.onAfterAddingFile = (fileItem) => {
            console.log(fileItem);
            this.uploadFailed = "";
            return fileItem;
        };

       /* this.uploader.onCompleteAll = () => {
            if (this.uploadFailed == "true") {
                this.errorMsg = "Form Submission Failed";
            } else {
                this.errorMsg = "Form Submission Successful";
            }
            return void 0;
        }*/

        this.uploader.onSuccessItem = (item, response, status, headers) => {
            if (response = "Success") {
                this.uploadFailed = "false"
                this.errorMsg = "Upload Success";
            } else {
                this.uploadFailed = "true"
                this.errorMsg = "Upload Failed";
            }
        }

    }

    onUploadAll(event,errMsgModal) {
        console.log('helloinuploadall');
        errMsgModal.open();
        event.preventDefault();
        this.payload = JSON.stringify(this.dealSetupForm.value);
        this.uploader.uploadAll();
    }

    fileChange(event): any {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            this.file = fileList[0];
            this.fileExtension = this.file.name.split('.').pop();
            // this.extensionCheck();
        }
    }


    extensionCheck(): boolean {
        if(this.fileExtension == null || this.fileExtension == undefined){
             return true;
        }
        if (this.isInArray(this.validExtensions, this.fileExtension)) {
            this.fileExtensionValid = true;
            this.fileExtensionMessage = ""
        } else {
            this.fileExtensionMessage = "Only Excel formats allowed!!"
            this.fileExtensionValid = false;
           
        }
         console.log(this.dealSetupForm.valid);
         if(this.fileExtensionValid && this.dealSetupForm.get('dealName').valid && this.dealSetupForm.get('clientContact').valid){
            return false
         }else{
            return true
            
    }

    }

    isInArray(array, word) {
        return array.indexOf(word.toLowerCase()) > -1;
    }


}



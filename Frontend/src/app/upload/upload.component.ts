import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  constructor(private dataService :DataService) { }

  ngOnInit() {
  }

  uploadResponse:any = { status: '', message: '', filePath: '' };
  file;
  onSubmit(form){
    
    let name:string = form.value.filename;
    this.dataService.uploadFile("/upload",this.file,this.file.name).subscribe(
      (res)=>{
          this.uploadResponse=res;
      }
    );
  }
  changeListener(fileList){
    this.file = fileList[0];
  }

}

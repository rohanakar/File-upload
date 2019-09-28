import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { map } from  'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  
  constructor(private http: HttpClient) { }

  private url = "http://localhost:9090"
  
  getFileList(endPoint:String ){
    return this.http.get(this.url+endPoint);
  }

  uploadFile(endPoint: String, body,name:string){

    console.log(this.url+endPoint+"?filename="+name);
    console.log(body);
    return this.http.post(this.url+endPoint+"?filename="+name,body,{
      reportProgress: true,
      observe: 'events'
    }).pipe(map((event) => {

      switch (event.type) {

        case HttpEventType.UploadProgress:
          const progress = Math.round(100 * event.loaded / event.total);
          return { status: 'progress', message: progress };

        case HttpEventType.Response:
          return event.body;
        default:
          return `Unhandled event: ${event.type}`;
      }
    })
    );
  }
  
}

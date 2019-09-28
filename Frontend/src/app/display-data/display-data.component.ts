import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-display-data',
  templateUrl: './display-data.component.html',
  styleUrls: ['./display-data.component.css']
})
export class DisplayDataComponent implements OnInit {

  items ;

  constructor(private dataService :DataService) { }

  ngOnInit() {
      this.dataService.getFileList("/viewDataSetList").subscribe((response)=>{
        this.items = response;
      });
  }
}

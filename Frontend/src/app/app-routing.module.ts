import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisplayDataComponent } from './display-data/display-data.component';
import { UploadComponent } from './upload/upload.component';


const routes: Routes = [{
  path:'display-data',component:DisplayDataComponent
  },
  {
  path:'',component:UploadComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

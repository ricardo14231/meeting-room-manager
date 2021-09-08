import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './modules/page-not-found/not-found.component';
import { FormComponent } from './modules/room/form/form.component';
import { ListComponent } from './modules/room/list/list.component';
import { DetailsComponent } from './modules/room/details/details.component';
import { MainComponent } from './modules/room/main/main.component';

const routes: Routes = [
  {path: '', redirectTo: 'room', pathMatch: 'full'},
  {path: 'room', component: MainComponent, 
    children: [
      {path: '', component: ListComponent},
      {path: 'create', component: FormComponent},
      {path: 'edit', component: FormComponent},
      {path: 'details', component: DetailsComponent}    
    ]
  },
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

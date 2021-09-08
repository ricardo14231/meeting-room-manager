import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { NavBarComponent } from './modules/header/nav-bar/nav-bar.component';
import { FormComponent } from './modules/room/form/form.component';
import { FooterComponent } from './modules/footer/footer.component';
import { NotFoundComponent } from './modules/page-not-found/not-found.component';
import { MainComponent } from './modules/room/main/main.component';
import { ListComponent } from './modules/room/list/list.component';
import { DetailsComponent } from './modules/room/details/details.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    FormComponent,
    FooterComponent,
    NotFoundComponent,
    MainComponent,
    ListComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

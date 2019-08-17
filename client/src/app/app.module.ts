import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HttpClientModule } from '@angular/common/http';
import { TrainWordsComponent } from './train-words/train-words.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Component, HostBinding } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';
import { TrainWords1Component } from './train-words1/train-words1.component';
import { TrainWords2Component } from './train-words2/train-words2.component';
import { TrainWords3Component } from './train-words3/train-words3.component';
import { TrainWords4Component } from './train-words4/train-words4.component';
import { TrainWords5Component } from './train-words5/train-words5.component';
import { TopMenuComponent } from './top-menu/top-menu.component';
import { SimpleTopMenuComponent } from './simple-top-menu/simple-top-menu.component';
import { ResultTrainingWordsComponent } from './result-training-words/result-training-words.component';
import { BottomMenuComponent } from './bottom-menu/bottom-menu.component';
import { ReadingBookComponent } from './reading-book/reading-book.component';
import { ReviewBooksComponent } from './review-books/review-books.component';
import { DictionaryComponent } from './dictionary/dictionary.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
	TrainWordsComponent,
	TrainWords1Component,
	TrainWords2Component,
	TrainWords3Component,
	TrainWords4Component,
	TrainWords5Component,
	TopMenuComponent,
	SimpleTopMenuComponent,
	ResultTrainingWordsComponent,
	BottomMenuComponent,
	ReadingBookComponent,
	ReviewBooksComponent,
	DictionaryComponent,
	LoginComponent,
	RegistrationComponent,
	
  ],
  imports: [
    BrowserModule,
	AppRoutingModule,
	HttpClientModule,
	BrowserAnimationsModule,
	FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

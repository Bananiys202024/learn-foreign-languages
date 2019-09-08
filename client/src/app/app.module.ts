import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
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
import { TopMenuComponent } from './top-menu/top-menu.component';
import { SimpleTopMenuComponent } from './simple-top-menu/simple-top-menu.component';
import { ResultTrainingWordsComponent } from './result-training-words/result-training-words.component';
import { BottomMenuComponent } from './bottom-menu/bottom-menu.component';
import { ReadingBookComponent } from './reading-book/reading-book.component';
import { ReviewBooksComponent } from './review-books/review-books.component';
import { DictionaryComponent } from './dictionary/dictionary.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { TrainingWordsSliderComponent } from './training-words-slider/training-words-slider.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { CabinetComponent } from './cabinet/cabinet.component';
import { LoaderComponent } from './loader/loader.component';
import { LoaderInterceptorService } from './service/loader-interceptor.service';
import { RecaptchaModule } from 'ng-recaptcha';
import { Globals } from './classes/globals';
import { TokenInterceptor } from './Interceptors/token.interceptor';
import { ProgressComponent } from './progress/progress.component';


@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
	DashboardComponent,
	TopMenuComponent,
	SimpleTopMenuComponent,
	ResultTrainingWordsComponent,
	BottomMenuComponent,
	ReadingBookComponent,
	ReviewBooksComponent,
	DictionaryComponent,
	LoginComponent,
	RegistrationComponent,
	TrainingWordsSliderComponent,
	CabinetComponent,
	LoaderComponent,
	ProgressComponent,
	
  ],
  imports: [
    BrowserModule,
	AppRoutingModule,
	HttpClientModule,
	BrowserAnimationsModule,
	FormsModule,
	NgbModule,
	RecaptchaModule.forRoot(),
  ],
  providers: [
	{
	  provide: HTTP_INTERCEPTORS,
	  useClass: LoaderInterceptorService,
	  multi: true
	},
	{
		provide: HTTP_INTERCEPTORS,
		useClass: TokenInterceptor,
		multi: true
	},

	Globals,
],
  bootstrap: [AppComponent]
})
export class AppModule { }

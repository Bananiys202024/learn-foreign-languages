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
import { ReadingBookComponent } from './reading-stories/reading-stories.component';
import { ReviewBooksComponent } from './review-stories/review-stories.component';
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
import { PermissionDeniedComponent } from './security/permission-denied/permission-denied.component';
import { TopMenuBeforeAutheticationComponent } from './parts/top-menu-before-authetication/top-menu-before-authetication.component';
import { TVComponent } from './tv/tv.component';
import { CabinetAdminComponent } from './admin/cabinet-admin/cabinet-admin.component';
import { ConfirmCodeComponent } from './registration/confirm-code/confirm-code.component';
import { SuccessComponent } from './registration/success/success.component';
import { InputEmailCodeComponent } from './registration/restore-password/input-email-code/input-email-code.component';
import { InputNewPasswordComponent } from './registration/restore-password/input-new-password/input-new-password.component';
import { MessagesComponent } from './cabinet/messages/messages.component';



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
	PermissionDeniedComponent,
	TopMenuBeforeAutheticationComponent,
	TVComponent,
	CabinetAdminComponent,
	ConfirmCodeComponent,
	SuccessComponent,
	InputEmailCodeComponent,
	InputNewPasswordComponent,
	MessagesComponent,
	
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

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { ResultTrainingWordsComponent } from './result-training-words/result-training-words.component';
import { ReviewBooksComponent } from './review-stories/review-stories.component';
import { ReadingBookComponent } from './reading-stories/reading-stories.component';
import { DictionaryComponent } from './dictionary/dictionary.component';
import { RegistrationComponent } from './registration/registration.component';
import { TrainingWordsSliderComponent } from './training-words-slider/training-words-slider.component';
import { CabinetComponent } from './cabinet/cabinet.component';
import { LoaderComponent } from './loader/loader.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { AuthGuardAnonymouse } from './auth/auth-guard-anonymouse';
import { ProgressComponent } from './progress/progress.component';
import { PermissionDeniedComponent } from './security/permission-denied/permission-denied.component';


const routes: Routes = [
	  { path: '', component: WelcomeComponent, data: {animation: 'StandartAnimation1', title: 'Dolphi'}},

	  { path: 'dashboard', canActivate: [AuthGuard], component: DashboardComponent, data: {animation: 'StandartAnimation2'} },

	  { path: 'reviewBooks', canActivate: [AuthGuard], component: ReviewBooksComponent, data: {animation: 'StandartAnimation3'}},
	  { path: 'readBook/:title', canActivate: [AuthGuard], component: ReadingBookComponent, data: {animation: 'StandartAnimation4'}},
	  { path: 'dictionary', canActivate: [AuthGuard], component: DictionaryComponent, data: {animation: 'StandartAnimation5'}},
	  { path: 'progress', canActivate: [AuthGuard], component: ProgressComponent, data: {animation: 'StandartAnimation6'}},

	  { path: 'registration', canActivate: [AuthGuardAnonymouse], component: RegistrationComponent, data: {animation: 'StandartAnimation7'}},
	  { path: 'login', canActivate: [AuthGuardAnonymouse], component: LoginComponent, data: {animation: 'StandartAnimation8'}},

	  { path: 'adminSecretPage', canActivate: [AuthGuard], component: CabinetComponent, data: {animation: 'StandartAnimation10'}},

	  { path: 'training', canActivate: [AuthGuard], component: TrainingWordsSliderComponent, data: {animation: 'StandartAnimation11'}},
	  { path: 'cabinet', canActivate: [AuthGuard], component: CabinetComponent, data: {animation: 'StandartAnimation12'}},

	  { path: 'result-training-words/:wrong/:right', canActivate: [AuthGuard], component: ResultTrainingWordsComponent, data: {animation: 'StandartAnimation13'} },


	//   security
		{ path: 'permission-denied', component: PermissionDeniedComponent, data: {animation: 'StandartAnimation14'} },
	// ....
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

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
import { TVComponent } from './tv/tv.component';
import { CabinetAdminComponent } from './admin/cabinet-admin/cabinet-admin.component';
import { SuccessComponent } from './registration/success/success.component';
import { ConfirmCodeComponent } from './registration/confirm-code/confirm-code.component';
import { InputNewPasswordComponent } from './registration/restore-password/input-new-password/input-new-password.component';
import { InputEmailCodeComponent } from './registration/restore-password/input-email-code/input-email-code.component';


const routes: Routes = [
	  { path: '', component: WelcomeComponent, data: {animation: 'StandartAnimation1', title: 'Dolphi'}},

	  { path: 'dashboard', canActivate: [AuthGuard], component: DashboardComponent, data: {animation: 'StandartAnimation2'} },

	  { path: 'reviewBooks', canActivate: [AuthGuard], component: ReviewBooksComponent, data: {animation: 'StandartAnimation3'}},
	  { path: 'readBook/:title', canActivate: [AuthGuard], component: ReadingBookComponent, data: {animation: 'StandartAnimation4'}},
	  { path: 'dictionary', canActivate: [AuthGuard], component: DictionaryComponent, data: {animation: 'StandartAnimation5'}},
	  { path: 'progress', canActivate: [AuthGuard], component: ProgressComponent, data: {animation: 'StandartAnimation6'}},


	  { path: 'registration', canActivate: [AuthGuardAnonymouse], component: RegistrationComponent, data: {animation: 'StandartAnimation7'}},
	  { path: 'registration/success', canActivate: [AuthGuardAnonymouse], component: SuccessComponent, data: {animation: 'StandartAnimation18'}},
	  { path: 'registration/confirmCode', canActivate: [AuthGuardAnonymouse], component: ConfirmCodeComponent, data: {animation: 'StandartAnimation19'}},
	  { path: 'registration/restore-password/email', canActivate: [AuthGuardAnonymouse], component: InputEmailCodeComponent, data: {animation: 'StandartAnimation20'}},
	  { path: 'registration/restore-password/new/password', canActivate: [AuthGuardAnonymouse], component: InputNewPasswordComponent, data: {animation: 'StandartAnimation21'}},


	  { path: 'login', canActivate: [AuthGuardAnonymouse], component: LoginComponent, data: {animation: 'StandartAnimation8'}},

	  { path: 'adminSecretPage', canActivate: [AuthGuard], component: CabinetComponent, data: {animation: 'StandartAnimation10'}},
	  { path: 'admin/cabinet', canActivate: [AuthGuard], component: CabinetAdminComponent, data: {animation: 'StandartAnimation16'} },

	  
	  { path: 'training', canActivate: [AuthGuard], component: TrainingWordsSliderComponent, data: {animation: 'StandartAnimation11'}},
	  { path: 'tv', canActivate: [AuthGuard], component: TVComponent, data: {animation: 'StandartAnimation15'}},

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

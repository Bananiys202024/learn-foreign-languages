import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { ResultTrainingWordsComponent } from './result-training-words/result-training-words.component';
import { ReviewBooksComponent } from './review-books/review-books.component';
import { ReadingBookComponent } from './reading-book/reading-book.component';
import { DictionaryComponent } from './dictionary/dictionary.component';
import { RegistrationComponent } from './registration/registration.component';
import { TrainingWordsSliderComponent } from './training-words-slider/training-words-slider.component';
import { CabinetComponent } from './cabinet/cabinet.component';
import { LoaderComponent } from './loader/loader.component';


const routes: Routes = [
	  { path: '', component: WelcomeComponent, data: {animation: 'Welcome'}},
	  { path: 'train-words', component: DashboardComponent, data: {animation: 'HomePage'} },

	  { path: 'reviewBooks', component: ReviewBooksComponent, data: {animation: 'ReviewBooks'}},
	  { path: 'readBook/:title', component: ReadingBookComponent, data: {animation: 'readBook'}},
	  { path: 'dictionary', component: DictionaryComponent, data: {animation: 'Dictionary'}},

	  { path: 'registration', component: RegistrationComponent, data: {animation: 'Registration'}},
	  
	  { path: 'training', component: TrainingWordsSliderComponent, data: {animation: 'train-words-slider'}},
	  { path: 'cabinet', component: CabinetComponent, data: {animation: 'cabinet'}},

	  { path: 'result-training-words/:wrong/:right', component: ResultTrainingWordsComponent, data: {animation: 'ResultTrainWords'} },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

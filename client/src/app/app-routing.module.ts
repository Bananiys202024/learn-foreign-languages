import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { TrainWordsComponent } from './train-words/train-words.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { TrainWords1Component } from './train-words1/train-words1.component';
import { TrainWords3Component } from './train-words3/train-words3.component';
import { TrainWords5Component } from './train-words5/train-words5.component';
import { TrainWords4Component } from './train-words4/train-words4.component';
import { TrainWords2Component } from './train-words2/train-words2.component';
import { ResultTrainingWordsComponent } from './result-training-words/result-training-words.component';
import { ReviewBooksComponent } from './review-books/review-books.component';
import { ReadingBookComponent } from './reading-book/reading-book.component';
import { DictionaryComponent } from './dictionary/dictionary.component';
import { RegistrationComponent } from './registration/registration.component';
import { TrainingWordsSliderComponent } from './training-words-slider/training-words-slider.component';


const routes: Routes = [
	  { path: '', component: WelcomeComponent, data: {animation: 'Welcome'}},
	  { path: 'train-words', component: TrainWordsComponent, data: {animation: 'HomePage'} },
	  { path: 'train-words2', component: TrainWords2Component, data: {animation: 'TrainWords2'}},

	  { path: 'reviewBooks', component: ReviewBooksComponent, data: {animation: 'ReviewBooks'}},
	  { path: 'readBook/:title', component: ReadingBookComponent, data: {animation: 'readBook'}},
	  { path: 'dictionary', component: DictionaryComponent, data: {animation: 'Dictionary'}},

	  { path: 'registration', component: RegistrationComponent, data: {animation: 'Registration'}},
	  
	  { path: 'train-words-slider', component: TrainingWordsSliderComponent, data: {animation: 'train-words-slider'}},


	  { path: 'train-words1/:element/:countWords/:countProcess', component: TrainWords1Component, data: {animation: 'TrainWords1'}},
	  { path: 'train-words2/:element/:countWords/:countProcess', component: TrainWords2Component, data: {animation: 'TrainWords2'}},
	  { path: 'train-words3/:element/:countWords/:countProcess', component: TrainWords3Component, data: {animation: 'TrainWords3'}},
	  { path: 'train-words4/:element/:countWords/:countProcess', component: TrainWords4Component, data: {animation: 'TrainWords4'}},
	  { path: 'train-words5/:element/:countWords/:countProcess', component: TrainWords5Component, data: {animation: 'TrainWords5'}},
	  { path: 'result-training-words', component: ResultTrainingWordsComponent, data: {animation: 'ResultTrainWords'} },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

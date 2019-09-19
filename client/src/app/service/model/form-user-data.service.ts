import { Injectable } from '@angular/core';
import { User } from 'src/app/classes/user';

@Injectable({
  providedIn: 'root'
})
export class FormUserDataService {

  public storage: User;

  public constructor() { }

}

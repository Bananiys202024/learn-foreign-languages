import { Preference } from './preference';

export class User {

    constructor(
        public username: string,
        public password: string,
        public confirmPassword: string,
        public email: string,
        public role:string,
        public preference:Preference,
      ) {  }

}


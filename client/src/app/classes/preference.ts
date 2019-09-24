export class Preference {

    constructor(
        public date_of_registration:Date,
        public status:string,
        public enabled:boolean,
        public experience:number,
        public timezone:string,
        public country:string,
        public native_language:string,
        public target_learning_language:string,
      ) {  }

}

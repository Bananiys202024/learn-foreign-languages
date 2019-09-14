export class BanchProgressData {
    
    constructor(
        public learned_words:number,
        public detected_words:number,
        public all_words_dictionary:number,
        public learning_words:number,
        public current_level_experience:number,
        public experience_before_next_level:number,

        public experience_earned_today:number,
        public current_level:number,
        public before_next_level:number,
        public how_do_many_days_persistence_of_learning:number,
        
      ) {}

}

import { TestBed } from '@angular/core/testing';

import { FormUserDataService } from './form-user-data.service';

describe('FormUserDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FormUserDataService = TestBed.get(FormUserDataService);
    expect(service).toBeTruthy();
  });
});

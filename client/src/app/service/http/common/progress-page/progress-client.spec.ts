import { TestBed } from '@angular/core/testing';
import { HttpClientService } from '../others/http-client.service';
import { ProgressClient } from './progress-client';

describe('ProgressClient', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should create an instance', () => {
    const service: HttpClientService = TestBed.get(HttpClientService);
    expect(new ProgressClient()).toBeTruthy();
  });
});

import { ProgressClient } from './progress-client';
import { TestBed } from '@angular/core/testing';
import { HttpClientService } from './http-client.service';

describe('ProgressClient', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should create an instance', () => {
    const service: HttpClientService = TestBed.get(HttpClientService);
    expect(new ProgressClient()).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovComponent } from './nov.component';

describe('NovComponent', () => {
  let component: NovComponent;
  let fixture: ComponentFixture<NovComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NovComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NovComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

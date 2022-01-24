import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartAccessComponent } from './cart-access.component';

describe('CartAccessComponent', () => {
  let component: CartAccessComponent;
  let fixture: ComponentFixture<CartAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartAccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

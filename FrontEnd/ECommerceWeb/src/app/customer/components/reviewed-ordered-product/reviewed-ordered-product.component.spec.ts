import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewedOrderedProductComponent } from './reviewed-ordered-product.component';

describe('ReviewedOrderedProductComponent', () => {
  let component: ReviewedOrderedProductComponent;
  let fixture: ComponentFixture<ReviewedOrderedProductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewedOrderedProductComponent]
    });
    fixture = TestBed.createComponent(ReviewedOrderedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

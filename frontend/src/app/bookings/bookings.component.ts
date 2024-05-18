import {Component, inject, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BookingsService} from "./bookings.service";
import {BookingResponse} from "./bookingsResponse.interface";

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [
    NgForOf,
    ReactiveFormsModule,
    FormsModule,
    NgIf
  ],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css'
})
export class BookingsComponent implements OnInit{
  response?: any;
  number?: number;
  bookingResponse?:BookingResponse[];
  bookingsService=inject(BookingsService);
  ngOnInit() {
    this.bookingsService.getAllBookings().subscribe(data => {
      this.bookingResponse = data;
    });
  }
  cancelBooking(bookingId: number) {
    this.bookingsService.deleteBooking(bookingId).subscribe(data => {
      this.response = data;
      this.ngOnInit();
    });
  }
}

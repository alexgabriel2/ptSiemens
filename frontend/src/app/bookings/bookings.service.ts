import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BookingResponse} from "./bookingsResponse.interface";

@Injectable({
  providedIn: 'root',
})
export class BookingsService{

  http = inject(HttpClient);


  getAllBookings() {
    return this.http.get<BookingResponse[]>('http://localhost:8080/booking/all');
  }
  deleteBooking(bookingId: number) {
    return this.http.delete('http://localhost:8080/booking/cancel?bookingId='+bookingId,{responseType: 'text' as 'json'});
  }
}

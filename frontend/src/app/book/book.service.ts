import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BookInterface} from "./book.interface";
import {BookingResponse} from "./bookingResponse";


@Injectable({
  providedIn: 'root',
})
export class BookService{

  http = inject(HttpClient);

  book(bookDetails: BookInterface) {
    return this.http.post('http://localhost:8080/booking/book',bookDetails,{responseType: 'text' as 'json'});
  }
  getBookings(roomId: number) {
    return this.http.get<BookingResponse[]>('http://localhost:8080/booking/room/'+roomId);
  }

}

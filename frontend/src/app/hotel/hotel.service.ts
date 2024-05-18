import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {hotelResponse} from "../search/hotelResponse.interface";
import {roomResponse} from "./roomResponse.interface";

@Injectable({
  providedIn: 'root',
})
export class HotelService{

  http = inject(HttpClient);

  getHotel(hotelId:number){
    return this.http.get<hotelResponse>('http://localhost:8080/hotels/'+hotelId);
  }
  getRooms(hotelId:number){
    return this.http.get<roomResponse[]>('http://localhost:8080/rooms/'+hotelId);
  }

}

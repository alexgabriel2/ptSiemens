import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {SearchComponent} from "./search.component";
import { UserDetails} from "./search.interface";
import {hotelResponse} from "./hotelResponse.interface";

@Injectable({
  providedIn: 'root',
})
export class SearchService{

  http = inject(HttpClient);



  search(userDetails:UserDetails) {
    return this.http.post<hotelResponse[]>('http://localhost:8080/hotels/distance',userDetails);
  }


}

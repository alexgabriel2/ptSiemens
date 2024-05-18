import {Component, inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {SearchService} from "./search.service";
import {hotelResponse} from "./hotelResponse.interface";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  lat?:number;
  lng?:number;
  distance?:number;
  foundHotels?: hotelResponse[];
  searchService=inject(SearchService);

  getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
      });
    } else {
      console.log( "Geolocation is not supported by this browser.");
    }
  }
  search(): void {
  if (!this.lat || !this.lng || !this.distance) {
    console.log("Please enter all fields");

  }
      this.searchService.search({latitude: this.lat!, longitude: this.lng!, distance: this.distance!})
        .subscribe(data => {
          this.foundHotels = data;
          console.log(this.foundHotels);
        });

  }
}

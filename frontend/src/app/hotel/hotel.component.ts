import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {HotelService} from "./hotel.service";
import {hotelResponse} from "../search/hotelResponse.interface";
import {roomResponse} from "./roomResponse.interface";
import {NgForOf} from "@angular/common";
import {FeedbacksComponent} from "../feedbacks/feedbacks.component";

@Component({
  selector: 'app-hotel',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink,
    FeedbacksComponent
  ],
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.css'
})
export class HotelComponent implements OnInit {
  id?:number;
  hotel?:hotelResponse;
  rooms?:roomResponse[];
  hotelService=inject(HotelService)
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
   this.id= this.route.snapshot.params['id'];
   this.hotelService.getHotel(this.id!).subscribe(data=>{
      this.hotel=data;
   });
    this.hotelService.getRooms(this.id!).subscribe(data=>{
      this.rooms=data;
      console.table(this.rooms)
    });
  }


}

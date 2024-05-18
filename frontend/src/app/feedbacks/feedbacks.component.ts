import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FeedBackResponse} from "./feedBackResponse";
import {HotelService} from "../hotel/hotel.service";
import {FeedbackService} from "./feedback.service";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-feedbacks',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './feedbacks.component.html',
  styleUrl: './feedbacks.component.css'
})
export class FeedbacksComponent implements OnInit{

  id?:number;
  name?:string;
  message?:string;
  feedbacks?:FeedBackResponse[];
  response?:any;
  feedbackService=inject(FeedbackService);
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.id= this.route.snapshot.params['id'];
    this.feedbackService.getFeedbacks(this.id!).subscribe(data=>{
      this.feedbacks=data;
    });
  }

  sendFeedback(){
    if(!this.name || !this.message){
      console.log("Please enter all fields");
    }
    this.feedbackService.addFeedback({name: this.name!, message: this.message!, hotelId: this.id!})
      .subscribe(data=>{
        this.response=data;
      });
  }
}

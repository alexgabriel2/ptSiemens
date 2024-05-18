import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Data} from "@angular/router";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {BookService} from "./book.service";
import {BookInterface} from "./book.interface";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {BookingResponse} from "./bookingResponse";
@Component({
  selector: 'app-book',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, NgIf, NgForOf],
  templateUrl: './book.component.html',
  styleUrl: './book.component.css'
})
export class BookComponent implements OnInit{
  roomId?:number;
  checkIn?:Date;
  checkOut?:Date;
  result?:Object;
  bookService=inject(BookService);
  bookingResponse?:BookingResponse[];
  constructor(private route: ActivatedRoute) {
  }
  ngOnInit() {
    this.roomId=this.route.snapshot.params['id'];
    this.viewBookings();
  }
  onSubmit() {
    this.bookService.book({roomId:this.roomId,checkIn: this.checkIn,checkOut: this.checkOut
    }).subscribe((response) => {
        this.result = response;
        this.viewBookings();
    });

  }
  viewBookings() {
    this.bookService.getBookings(this.roomId!).subscribe(data=>{
      this.bookingResponse=data;
    });
  }


}

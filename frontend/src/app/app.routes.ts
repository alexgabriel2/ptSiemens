import { Routes } from '@angular/router';
import {MenuComponent} from "./menu/menu.component";
import {ParserComponent} from "./parser/parser.component";
import {InfoComponent} from "./info/info.component";
import {SearchComponent} from "./search/search.component";
import {HotelComponent} from "./hotel/hotel.component";
import {BookingsComponent} from "./bookings/bookings.component";
import {BookComponent} from "./book/book.component";
import {FeedbacksComponent} from "./feedbacks/feedbacks.component";

export const routes: Routes = [
  {
    path:"menu",
    component: MenuComponent,
  },
  {
    path:"parser",
    component: ParserComponent,
  },
  {
    path:"info",
    component:InfoComponent

  },
  {
    path:"search",
    component:SearchComponent
  },
  {
    path:"hotel/:id",
    component:HotelComponent
  },
  {
    path:"bookings",
    component:BookingsComponent
  },
  {
    path:"book/:id",
    component:BookComponent
  },
  {
    path:"feedbacks/:id",
    component:FeedbacksComponent
  },
  {
    path:"",
    redirectTo:"info",
    pathMatch:"full"
  }
];

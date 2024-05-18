import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FeedbackDetails} from "./feedbackDetails";
import {FeedBackResponse} from "./feedBackResponse";


@Injectable({
  providedIn: 'root',
})
export class FeedbackService{

  http = inject(HttpClient);

  getFeedbacks(hotelId:number){
    return this.http.get<FeedBackResponse[]>('http://localhost:8080/feedback/'+hotelId);
  }
  addFeedback(feedback:FeedbackDetails){
    return this.http.post('http://localhost:8080/feedback/add',feedback,{responseType: 'text' as 'json'});
  }

}

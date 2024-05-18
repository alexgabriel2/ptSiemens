import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class ParserService{

  http = inject(HttpClient);


  getAllHotels() {
    return this.http.get<string[]>('http://localhost:8080/populate/jsons');
  }

  sendSelectedFile(selectedFile: string) {
    return this.http.post('http://localhost:8080/populate/parse?name='+selectedFile,null,{responseType: 'text' as 'json'});
  }

  uploadFile(formData: FormData) {
    return this.http.post('http://localhost:8080/populate/add', formData,{responseType: 'text' as 'json'});
  }

}

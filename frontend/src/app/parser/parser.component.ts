import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ParserService} from "./parser.service";
import {NgForOf} from "@angular/common";
@Component({
  selector: 'app-parser',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './parser.component.html',
  styleUrl: './parser.component.css'
})
export class ParserComponent implements OnInit{
  fileNames?: string[];
  selectedFile?: string;
  file?: File;

  constructor(private parserService: ParserService) { }

  onSend(): void {
    if (this.selectedFile) {
      this.parserService.sendSelectedFile(this.selectedFile).subscribe(data => {
        console.log(data);
      });
    }
  }

  ngOnInit(): void {
    this.parserService.getAllHotels().subscribe(data => {
      this.fileNames = data;
      if (this.fileNames && this.fileNames.length > 0) {
        this.selectedFile = this.fileNames[0];
      }
    });
  }
  onFileChange(event: any): void {
    this.file = event.target.files[0];
  }
  upload() {
    if (!this.file) {
      return;
    }
    const formData = new FormData();
    formData.append('file', this.file, this.file.name);
    this.parserService.uploadFile(formData).subscribe(data => {

    });
  }
}

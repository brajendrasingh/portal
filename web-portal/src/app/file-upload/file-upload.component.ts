import { Component } from '@angular/core';
import { HttpEvent, HttpEventType } from '@angular/common/http';
import { FileUploadService } from '../core/services/file-upload.service';

@Component({
  selector: 'app-file-upload',
  standalone: false,
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.css'
})
export class FileUploadComponent {
  selectedFile: File | null = null;

  uploadProgress = -1;
  uploading = false;
  uploadSuccess = false;
  uploadError = false;

  constructor(private fileUploadService: FileUploadService) { }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.resetStatus();
    }
  }

  uploadFile(): void {
    if (!this.selectedFile) {
      return;
    }

    this.uploading = true;
    this.uploadProgress = 0;

    this.fileUploadService.uploadFile(this.selectedFile)
      .subscribe({
        next: (event: HttpEvent<any>) => {
          if (event.type === HttpEventType.UploadProgress && event.total) {
            this.uploadProgress = Math.round(
              (100 * event.loaded) / event.total
            );
          }

          if (event.type === HttpEventType.Response) {
            this.uploadSuccess = true;
            this.uploading = false;
          }
        },
        error: () => {
          this.uploadError = true;
          this.uploading = false;
          this.uploadProgress = -1;
        }
      });
  }

  private resetStatus(): void {
    this.uploadProgress = -1;
    this.uploadSuccess = false;
    this.uploadError = false;
  }
}

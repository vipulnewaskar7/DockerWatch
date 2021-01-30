import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class HTTPService {
    constructor(private http: HttpClient) {}

    get<T>(url: string) {
        return this.http.get<T>(url);
    }

    post<T>(url: string) {
        return this.http.get<T>(url);
        
    }
}
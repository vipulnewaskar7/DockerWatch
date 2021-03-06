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

    async post<T>(url: string, body: any) {
        return this.http.post<T>(url, body).toPromise();
    }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ProtocoloServico {
    
    constructor(private httpClient: HttpClient) {}
}
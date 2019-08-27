/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

//componentes
import { AppComponent } from './componentes/app/app.component';
import { HomeComponent } from './componentes/home/home.component';

//servicos
import { DocenteServico } from './docente/docente.servico';

//modulos
import { DocenteModule } from './docente/docente.module';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

//interceptadores
import { HttpInterceptorCore } from './interceptor/http.interceptor';
import { ErrorInterceptor } from './interceptor/error.interceptor';

//rotas
import { ROTAS } from './app.router';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    Ng4LoadingSpinnerModule.forRoot(),
    RouterModule.forRoot(ROTAS),
    BrowserModule,
    HttpClientModule,
    DocenteModule
  ],
  providers: [
    DocenteServico,
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorCore, multi: true },
    { provide: ErrorHandler, useClass: ErrorInterceptor, multi: false }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

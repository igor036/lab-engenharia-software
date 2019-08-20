/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

//componentes
import { AppComponent } from './componentes/app/app.component';
import { HomeComponent } from './componentes/home/home.component';

//servicos
import { DocenteServico } from './docente/docente.servico';

//modulos
import { DocenteModule } from './docente/docente.module';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

//rotas
import { ROTAS } from './app.router';
import { HttpInterceptorCore } from './interceptor/http.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    Ng4LoadingSpinnerModule.forRoot(),
    RouterModule.forRoot(ROTAS),
    BrowserModule,
    DocenteModule,
    HttpClientModule
  ],
  providers: [
    DocenteServico,
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorCore, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

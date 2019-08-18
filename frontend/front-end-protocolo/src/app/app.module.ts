/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

//componentes
import { AppComponent } from './componentes/app/app.component';
import { HomeComponent } from './componentes/home/home.component';

//servicos
import { DocenteServico } from './docente/docente.servico';

//modulos
import { DocenteModule } from './docente/docente.module';

//rotas
import { ROTAS } from './app.router';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forRoot(ROTAS),
    BrowserModule,
    DocenteModule
  ],
  providers: [DocenteServico],
  bootstrap: [AppComponent]
})
export class AppModule { }

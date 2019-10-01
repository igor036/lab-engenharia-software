import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//terceiros
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

//componentes
import { CadastroProtocoloComponent } from './paginas/cadastro-protocolo/cadastro-protocolo.component';

//servico
import { ProtocoloServico } from './protocolo.servico';

//modulo compartilhado
import { CompartilhadoModule } from '../compartilhado/compartilhado.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

const COMPONENTES = [
  CadastroProtocoloComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  providers: [ProtocoloServico],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule,
    CompartilhadoModule,
    BrowserAnimationsModule,
    BsDatepickerModule,
  ]
})
export class ProtocoloModule { }

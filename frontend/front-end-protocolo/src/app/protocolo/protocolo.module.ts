import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

//componentes
import { CadastroProtocoloComponent } from './paginas/cadastro-protocolo/cadastro-protocolo.component';

//servico
import { ProtocoloServico } from './protocolo.servico';

const COMPONENTES = [
  CadastroProtocoloComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  providers: [ProtocoloServico],
  imports: [
    CommonModule
  ]
})
export class ProtocoloModule { }

/**
 * Author: Kelvin Murilo Araújo Santos
 * Data: 25/10/2019
 */

import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  forwardRef
} from '@angular/core';

import {
  FormControl,
  NG_VALUE_ACCESSOR,
  ControlValueAccessor
} from '@angular/forms';
import { } from 'events';

import { ListarSugestoesInput } from '../../compartilhado.modelo';

@Component({
  selector: 'app-input-auto-complete',
  templateUrl: './input-auto-complete.component.html',
  styleUrls: ['./input-auto-complete.component.scss'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => InputAutoCompleteComponent),
    multi: true
  }]
})
export class InputAutoCompleteComponent implements OnInit, ControlValueAccessor {

  @Input() formControl: FormControl;
  @Input() iconeFa?: string;
  @Input() placeholder: string;
  @Input() type: string = 'text';
  @Input() listaSugestoes: Array<ListarSugestoesInput> = [];

  @Output() mudarValor: EventEmitter<string> = new EventEmitter<string>();
  @Output() valorSelecionado: EventEmitter<any> = new EventEmitter<any>();

  public clicado: boolean = false;

  public valor: string = '';
  private onChange: Function;
  private onTouched: Function;

  constructor() { }

  ngOnInit() {
    if (!this.formControl) {
      throw Error("Informe o form control do componente input");
    }
  }

  isInvalido(): boolean {
    return !this.formControl.valid && !this.formControl.pristine;
  }

  writeValue(valor: string): void {
    this.valor = valor;
    this.mudarValor.emit(this.valor);
  }

  registerOnChange(funcao: Function): void {
    this.onChange = funcao;
  }

  registerOnTouched(funcao: Function): void {
    this.onTouched = funcao;
  }

  exibirCampoSugestao(): boolean {
    return this.listaSugestoes.length > 0;
  }

  selecionarValorSugestao(sugestao: ListarSugestoesInput): void {
    //this.formControl.setValue(sugestao.descricao);
    this.valor = sugestao.descricao;
    this.valorSelecionado.emit(sugestao);
    this.listaSugestoes = [];
  }

}

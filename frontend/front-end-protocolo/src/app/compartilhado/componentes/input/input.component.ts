/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */
import { 
  Component, 
  OnInit, 
  Input, 
  forwardRef, 
  Output, 
  EventEmitter 
} from '@angular/core';

import { 
  FormControl, 
  NG_VALUE_ACCESSOR, 
  ControlValueAccessor 
} from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => InputComponent),
    multi: true
  }]
})

/**
 * Componente de input de texto feito para ser utilizado com
 * reactive forms.
 * 
 * Exemplo de como utilizar:
 * <app-input 
      [formControl]="form.controls['email']"
      msgErro="Informe um e-mail válido."
      placeholder="Email"
      classeIcone="fa-envelope">
  </app-input> 
 * 
 * @Input formControl a referencia de um control do FormGroup que esta sendo tratado.
 * @Input classeIcone classe do icone do fontwasome que sera exibida.
 * @Input placeholder texto do placeholder.
 * @Input msgErro mensagem de erro para quando o campo  estiver inválido.
 * @Input isSenha informa se o campo é do tipo  senha.
 * @Output alterarValor evento que emit o valor do campo toda vez que o mesmo é alterado.
 */
export class InputComponent implements OnInit, ControlValueAccessor {

  @Input() public formControl: FormControl;
  @Input() public classeIcone: string;
  @Input() public placeholder: string;
  @Input() public msgErro: string;
  @Input() public isSenha: boolean = false;
  
  @Output() public alterarValor: EventEmitter<string> = new EventEmitter<string>();

  private valor: string;
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

  getTipoInput(): string {
    return this.isSenha ? "password" : "text";
  }

  writeValue(valor: string): void {
    this.valor = valor;
    this.alterarValor.emit(this.valor);
  }
  registerOnChange(funcao: Function): void {
    this.onChange = funcao;
  }
  registerOnTouched(funcao: Function): void {
    this.onTouched = funcao;
  }
}

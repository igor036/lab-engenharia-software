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
  selector: 'app-input-area-texto',
  templateUrl: './input-area-texto.component.html',
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => InputAreaTextoComponent),
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
 * @Output alterarValor evento que emit o valor do campo toda vez que o mesmo é alterado.
 */
export class InputAreaTextoComponent implements OnInit, ControlValueAccessor {

  @Input() public formControl: FormControl;
  @Input() public classeIcone: string;
  @Input() public placeholder: string;
  @Input() public msgErro: string;
  
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

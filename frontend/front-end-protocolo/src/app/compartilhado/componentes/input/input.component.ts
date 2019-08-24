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

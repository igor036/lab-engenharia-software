/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */
import { 
  Component, 
  Input, 
  Output, 
  EventEmitter 
} from '@angular/core';

@Component({
  selector: 'app-botao',
  templateUrl: './botao.component.html',
  styleUrls: ['./botao.component.scss']
})
export class BotaoComponent {

  @Input() public texto: string;
  @Input() public liberado: boolean = true;
  @Output() public click: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  bindClick(): void {
    this.click.emit();
  }

  isBloqueado(): boolean {
    return !this.liberado;
  }
}

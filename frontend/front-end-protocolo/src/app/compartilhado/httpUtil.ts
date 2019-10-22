/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/10/2019
 */
import { HttpParams } from '@angular/common/http';

export class HttpUtil {

  /**
   * Transforma um objeto em parametros de requisicao. 
   * 
   * @param objeto - opjeto que deve ser convertido em parametros {@link any}
   * @return parametros - uma instancia de {@link  HttpParams} com as propriedades do
   * objeto informado.
   */
  public static converterObjetoParaHttpParametros(objeto: any): HttpParams {
    let parametros = new HttpParams();
    for (let propriedade in objeto) {
      parametros = parametros.set(propriedade, objeto[propriedade]);
    }
    return parametros;
  }
}
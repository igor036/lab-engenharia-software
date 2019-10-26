import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

//terceiros
import { Observable, of } from 'rxjs';

//modelos
import { QUANTIDADE_REGISTROS_PAGINA_PADRAO } from 'src/app/app.constante';
import { Paginacao } from 'src/app/compartilhado/compartilhado.modelo';
import { CadastrarProtocolo, ConsultaListaProtocolo, DetalheProtocolo, AtribuirParecerista, ListarSugestoesDePareceristas } from './protocolo.modelo';

//utilitarios
import { HttpUtil } from 'src/app/compartilhado/httpUtil';

const URL_CONTROLADOR: string = "protocolo";

@Injectable()
export class ProtocoloServico {

    constructor(private httpClient: HttpClient) { }

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }

    getListaProtocolo(filtro: ConsultaListaProtocolo, paginaAtual: number): Observable<Paginacao> {
        return this.httpClient.get<Paginacao>(
            `${URL_CONTROLADOR}/lista-protocolo/pagina-atual/${paginaAtual}/quantidade-registros-pagina/${QUANTIDADE_REGISTROS_PAGINA_PADRAO}`,
            { params: HttpUtil.converterObjetoParaHttpParametros(filtro) }
        );
    }

    getDetalheProtocolo(idProtocolo: number): Observable<DetalheProtocolo> {
        return this.httpClient.get<DetalheProtocolo>(`${URL_CONTROLADOR}/detalhe/${idProtocolo}`);
    }

    atribuirParecerista(parecerista: AtribuirParecerista): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/atribuir-parecerista`, parecerista, {
            responseType: 'text'
        });
    }

    listarSugestoesDePareceristas(valor: string): Observable<Array<ListarSugestoesDePareceristas>> {
        let listaSugestoesFiltradas: Array<ListarSugestoesDePareceristas> = []
        let listaSugestoes: Array<ListarSugestoesDePareceristas> = [
            {
                matriculaParecerista: 1,
                descricao:'Ubuntu'
            },
            {
                matriculaParecerista: 2,
                descricao:'Fedora'
            },
            {
                matriculaParecerista: 3,
                descricao:'Manjaro'
            },
            {
                matriculaParecerista: 4,
                descricao:'Mangaba'
            },
            {
                matriculaParecerista: 5,
                descricao:'Manjericão Palmito'
            },
            {
                matriculaParecerista: 3,
                descricao:'Feijão'
            }
        ];

        if (valor !== '') {
            listaSugestoes.filter(item => {
                if (item.descricao.toLocaleLowerCase().includes(valor.toLocaleLowerCase())) {
                    listaSugestoesFiltradas.push(item);
                }
            });
        }
        
        return of(listaSugestoesFiltradas);
    }


}

/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */

 
/**
 * Interface utilizada em conjunto com {@link SelecaoItemComponent}
 * que representa as opcoes do componente.
 * 
 * Pode ser utilizado em situações parecidas com a que foi
 * sitada a cima. 
 */
interface Opcao {
    descricao: string;
    valor: any;
}

interface Paginacao {
    paginaAtual: number;
    qtdRegistrosPagina: number;
    qtdTotalRegistros: number;
    lista?: Array<any>;
}

interface Filtro<T> {
    status: string;
    tipo: string;
    idProtocolo: number;
}

export {
    Opcao,
    Paginacao,
    Filtro
}
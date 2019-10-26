import { Paginacao } from './compartilhado/compartilhado.modelo';

/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
export const PROPRIEDADES = {
    TOKEN_DOCENTE: 'token-docente',
    DADOS_DOCENTE_LOGADO: 'dados-docente-logado',
    URL_BASE: 'http://localhost:8383/'
};

export const URLS_NAMES = {
    home: 'home',
    login: 'login',
    perfil: 'perfil',
    cadastroDocente: 'cadastro-docente',
    cadastroProtocolo: 'cadastro-protocolo',
    consultaProtocolo: 'consulta-protocolo',
    detalheProtocolo: 'detalhe-protocolo'
};

export const REGEXS = {
    email: "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
    nome: "/^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/"
};

export const QUANTIDADE_REGISTROS_PAGINA_PADRAO: number = 5;
export const OPCAO_SELECIONAR_INPUT_SELECT = 'SELECIONAR';

export const PAGINACAO_PADRAO: Paginacao = {
    paginaAtual: 1,
    qtdRegistrosPagina: QUANTIDADE_REGISTROS_PAGINA_PADRAO,
    qtdTotalRegistros: 0,
    lista: []
};

export enum Perfil {
    ADMIN = 'ADMIN',
    PROFESSOR = 'PROFESSOR',
    SECRETARIA = 'SECRETARIA',
    COORDENADOR = 'COORDENADOR'
}
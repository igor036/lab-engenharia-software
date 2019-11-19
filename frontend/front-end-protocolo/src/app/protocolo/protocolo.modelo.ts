import { Opcao } from '../compartilhado/compartilhado.modelo';

interface CadastrarProtocolo {
    justificativa: string;
    resumoPt: string;
    resumoEn: string;
    dataInicio: Date;
    dataFim: Date;
    litaPedidoProtocolo: Array<PedidoProtocolo>;
}

interface PedidoProtocolo {
    idEspecie: number;
    quantidade: number;
    idBioterio: number;
}

interface PedidoProtocoloVisualizar {
    especie: Opcao;
    quantidade: number;
    bioterio: Opcao;
}

interface ConsultaListaProtocolo {
    tipo: string;
    status: string;
    idProtocolo: number;
}

interface DetalhePedidoProtocolo {
    especie: string;
    quantidade: number;
    bioterio: string;
}

interface DetalheProtocolo {
    id: number;
    matriculaDocente: number;
    nomeDoscente: string;
    matriculaAvaliador: number;
    nomeAvaliador: string;
    resumoPt: string;
    resumoEn: string;
    justificativa: string;
    permitido: Boolean;
    observacaoParecer: String;
    pedidos: Array<DetalhePedidoProtocolo>;
}

interface AtribuirParecerista {
    idAvaliador: number;
    idProtocolo: number;
}

interface Parecerista {
    valor: number;
    descricao: string;
}

interface AvaliarProtocolo {
    deferido: boolean;
    descricao: string;
    idProtocolo: number;
}


enum TipoConsultaListaProtocolo {
    OPCAO_TODOS = 'T',
    OPCAO_CODIGO = 'C',
    OPCAO_STATUS = 'S'
}

enum CategoriaProtocoloConsultado {
    DOCENTE_LOGADO = 'DL',
    AVALIAR = 'A',
    OUTROS_DOCENTES = 'OD'
}


export {
    CadastrarProtocolo,
    PedidoProtocolo,
    PedidoProtocoloVisualizar,
    TipoConsultaListaProtocolo,
    ConsultaListaProtocolo,
    DetalhePedidoProtocolo,
    DetalheProtocolo,
    CategoriaProtocoloConsultado,
    AtribuirParecerista,
    Parecerista,
    AvaliarProtocolo
}
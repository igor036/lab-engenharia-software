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

enum TipoConsultaListaProtocolo {
    OPCAO_TODOS = 'T',
    OPCAO_CODIGO = 'C',
    OPCAO_STATUS = 'S'
}

export {
    CadastrarProtocolo,
    PedidoProtocolo,
    PedidoProtocoloVisualizar,
    TipoConsultaListaProtocolo,
    ConsultaListaProtocolo
}
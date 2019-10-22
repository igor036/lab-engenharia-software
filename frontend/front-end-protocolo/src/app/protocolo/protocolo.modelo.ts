import { Opcao } from '../compartilhado/compartilhado.modelo';

interface CadastrarProtocolo {
    justificativa: string;
    resumoPt: string;
    resumoEn: string;
    dataInicio: Date;
    dataFim: Date;
    litaPedidoProtocolo: Array<PedidoProtocolo>;
}

interface ConsultarProtocolo {
    idProtocolo: number;
    docente: string;
    data: string;
    status: string;
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

enum StatusProtocolo {
    STATUS_ABERTO = 'ABERTO',
    STATUS_DEFERIDO = 'DEFERIDO',
    STATUS_INDEFERIDO = 'INDEFERIDO'
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
    ConsultarProtocolo,
    StatusProtocolo,
    TipoConsultaListaProtocolo
}
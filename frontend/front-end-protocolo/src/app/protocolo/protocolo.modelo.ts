import { Opcao } from '../compartilhado/compartilhado.modelo';

interface CadastrarProtocolo {
    justificativa: string;
    resumoPt: string;
    resumoEn: string;
    dataInicio: Date;
    dataFim: Date;
    litaPedidoProtocolo:  Array<PedidoProtocolo>;
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

export {
    CadastrarProtocolo,
    PedidoProtocolo,
    PedidoProtocoloVisualizar
}
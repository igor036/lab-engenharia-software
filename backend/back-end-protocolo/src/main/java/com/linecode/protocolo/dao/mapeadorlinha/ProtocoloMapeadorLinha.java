package com.linecode.protocolo.dao.mapeadorlinha;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.linecode.protocolo.dto.DetalhePedidoProtocoloDto;
import com.linecode.protocolo.dto.DetalheProtocoloDto;

public class ProtocoloMapeadorLinha {

    private static final DetalheProtocoloDtoMapeadorLinha DETALHE_PROTOCOLO_DTO_MAPEADOR_LINHA = new DetalheProtocoloDtoMapeadorLinha();

    public static DetalheProtocoloDtoMapeadorLinha getDetalheProtocoloDtoMapeadorLinha() {
        return DETALHE_PROTOCOLO_DTO_MAPEADOR_LINHA;
    }

    private static class DetalheProtocoloDtoMapeadorLinha implements ResultSetExtractor<DetalheProtocoloDto> {

        @Override
        public DetalheProtocoloDto extractData(ResultSet rs) throws SQLException {

            if (rs.next()) {

                DetalheProtocoloDto detalheProtocolo = new DetalheProtocoloDto(rs.getLong("ID_PROTOCOLO"),
                        rs.getLong("MATRICULA_DOCENTE"), rs.getString("NOME_DOCENTE"),
                        rs.getLong("MATRICULA_AVALIADOR"), rs.getString("NOME_AVALIADOR"),
                        rs.getString("RESUMO_PT"), rs.getString("RESUMO_EN"), rs.getString("JUSTIFICATIVA"),
                        ((Boolean) rs.getObject("PERMITIDO")), rs.getString("OBS_PARECER"));

                do {
                    detalheProtocolo.getPedidos().add(new DetalhePedidoProtocoloDto(rs.getString("ESPECIE"),
                            rs.getInt("QUANTIDADE"), rs.getString("BIOTERIO")));
                } while (rs.next());

                return detalheProtocolo;

            }

            return null;
        }
    }
}

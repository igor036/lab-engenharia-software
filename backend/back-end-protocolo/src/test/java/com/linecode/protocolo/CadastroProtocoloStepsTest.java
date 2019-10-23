package com.linecode.protocolo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.linecode.compartilhado.enumerador.PerfilEnumerador;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloTesteCmd;
import com.linecode.protocolo.cmd.PedidoProtocoloCmd;
import com.linecode.protocolo.dao.ProtocoloDao;
import com.linecode.protocolo.servico.ProtocoloServico;
import com.linecode.util.servico.UtilServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CadastroProtocoloStepsTest {

    private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();

    @InjectMocks
    private ProtocoloServico protocoloServico = new ProtocoloServico();

    @Mock(name = "protocoloDao")
    private ProtocoloDao protocoloDaoMock;

    @Mock(name = "utilServico")
    private UtilServico utilServicoMock;

    @Mock(name = "docenteServico")
    private DocenteServico docenteServicoMock;

    @Mock(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplateMock;

    @Mock(name = "env")
    private Environment envMock;

    @Spy
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private List<CadastroProtocoloTesteCmd> dadosCadastro = new ArrayList<>();

    public CadastroProtocoloStepsTest() {
        MockitoAnnotations.initMocks(this);
        ApplicationContext applicationContextMock = mock(ApplicationContext.class);
        contextoAplicacao.setApplicationContext(applicationContextMock);
    }

    @Quando("^desejar efetuar um cadastro com pedidos invalidos, sendo que os dados do cadastro estao validos, informando os seguintes dados$")
    public void desejar_efetuar_um_cadastro_com_pedidos_invalidos_sendo_que_os_dados_do_cadastro_estao_validos_informando_os_seguintes_dados(
            List<Map<String, String>> dataTable) {
        dataTable.forEach(mapa -> dadosCadastro.add(mapaParaCadastroProtocoloTesteCmd(mapa)));
    }

    @Quando("^desejar efetuar um cadastro de um determinado protocolo informando um cadastro invalido com pedidos validos, informando os seguintes dados$")
    public void desejar_efetuar_um_cadastro_de_um_determinado_protocolo_informando_um_cadastro_invalido_com_pedidos_validos_informando_os_seguintes_dados(
            List<CadastroProtocoloTesteCmd> dadosCadastro) {
        this.dadosCadastro.addAll(dadosCadastro);
    }

    @Então("^devo receber uma Excecao Negocio exception\\$\\.$")
    public void devo_receber_uma_Excecao_Negocio_exception$() {

        when(utilServicoMock.getIdStatusInicialProtocolo()).thenReturn(1L);
        when(protocoloDaoMock.cadastrarProtocolo(Mockito.any(CadastroProtocoloCmd.class), Mockito.anyLong(),
                Mockito.anyLong())).thenReturn(1L);
        when(docenteServicoMock.getDadosDocenteLogado())
                .thenReturn(new DocenteDto(1L, "Docente", "docente@gmail.com", PerfilEnumerador.PROFESSOR));

        dadosCadastro.forEach(cmd -> {
            try {
                
                if (cmd.getLitaPedidoProtocolo() == null) {
                    cmd = new CadastroProtocoloTesteCmd(cmd.getJustificativa(), cmd.getResumoPt(), cmd.getResumoEn(),
                            cmd.getDataInicio(), cmd.getDataFim(), getListaPedidoProtocoloCmdValida(),
                            cmd.getMsgEsperada());
                }
                
                protocoloServico.cadastrarProtocolo(cmd);
            } catch (ExcecaoNegocio e) {
                assertEquals(e.getMessage(), cmd.getMsgEsperada());
            }
        });
    }

    private List<PedidoProtocoloCmd> getListaPedidoProtocoloCmdValida() {
        return Arrays.asList(new PedidoProtocoloCmd(1, 1, 1));
    }

    private CadastroProtocoloTesteCmd mapaParaCadastroProtocoloTesteCmd(Map<String, String> mapa) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String justificativa = mapa.get("justificativa");
        String resumoPt = mapa.get("resumoPt");
        String resumoEn = mapa.get("resumoEn");
        LocalDate dataInicio = LocalDate.parse(((String) mapa.get("dataInicio")), formatter);
        LocalDate dataFim = LocalDate.parse(((String) mapa.get("dataFim")), formatter);

        int idEspecie = Integer.parseInt(mapa.get("idEspecie"));
        int quantidade = Integer.parseInt(mapa.get("quantidade"));
        int idBioterio = Integer.parseInt(mapa.get("idBioterio"));

        String msgEsperada = mapa.get("msgEsperada");

        PedidoProtocoloCmd pedido = new PedidoProtocoloCmd(idEspecie, quantidade, idBioterio);

        return new CadastroProtocoloTesteCmd(justificativa, resumoPt, resumoEn, dataInicio, dataFim,
                Arrays.asList(pedido), msgEsperada);
    }
}

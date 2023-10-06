package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import med.voll.api.domain.medico.Especialidade;

public record DadosDetalhamentoConsulta(
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        Especialidade especialidade) {
}
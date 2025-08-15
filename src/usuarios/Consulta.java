
package usuarios;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String status;
    private Paciente paciente;
    private Medico medico;

    public Consulta(int id, LocalDate data, LocalTime hora, String status, Paciente paciente, Medico medico) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Consulta [id=" + id + ", data=" + data + ", hora=" + hora + ", status=" + status +
                ", paciente=" + paciente.getNome() + ", medico=" + medico.getNome() + "]";
    }
}

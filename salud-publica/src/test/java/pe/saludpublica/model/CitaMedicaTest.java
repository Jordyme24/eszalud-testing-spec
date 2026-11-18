package pe.saludpublica.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CitaMedicaTest {

    private static final LocalDateTime FECHA = LocalDateTime.of(2026, 12, 1, 9, 30);

    @Test
    @DisplayName("CIT-001: Crear una cita médica con datos válidos")
    void shouldCreateAppointmentWithValidData() {
        CitaMedica cita = new CitaMedica(
                "pac-1",
                "doc-1",
                "esp-1",
                FECHA,
                "Dolor de cabeza",
                EstadoCita.PROGRAMADA
        );

        assertNotNull(cita);
        assertEquals("pac-1", cita.getPaciente());
        assertEquals("doc-1", cita.getDoctor());
        assertEquals("esp-1", cita.getEspecialidad());
        assertEquals(FECHA, cita.getFechaHora());
        assertEquals("Dolor de cabeza", cita.getMotivo());
        assertEquals(EstadoCita.PROGRAMADA, cita.getEstado());
    }

    @Test
    @DisplayName("CIT-002: Rechazar cita sin paciente")
    void shouldRejectAppointmentWithoutPatient() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CitaMedica(
                        null,
                        "doc-1",
                        "esp-1",
                        FECHA,
                        "Dolor de cabeza",
                        EstadoCita.PROGRAMADA
                )
        );
    }

    @Test
    @DisplayName("CIT-003: Rechazar cita sin doctor")
    void shouldRejectAppointmentWithoutDoctor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CitaMedica(
                        "pac-1",
                        null,
                        "esp-1",
                        FECHA,
                        "Dolor de cabeza",
                        EstadoCita.PROGRAMADA
                )
        );
    }

    @Test
    @DisplayName("CIT-004: Rechazar cita sin especialidad")
    void shouldRejectAppointmentWithoutSpecialty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CitaMedica(
                        "pac-1",
                        "doc-1",
                        null,
                        FECHA,
                        "Dolor de cabeza",
                        EstadoCita.PROGRAMADA
                )
        );
    }

    @Test
    @DisplayName("CIT-005: Rechazar cita sin fecha y hora")
    void shouldRejectAppointmentWithoutDateTime() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CitaMedica(
                        "pac-1",
                        "doc-1",
                        "esp-1",
                        null,
                        "Dolor de cabeza",
                        EstadoCita.PROGRAMADA
                )
        );
    }

    @Test
    @DisplayName("CIT-006: Asignar PROGRAMADA cuando el estado es null")
    void shouldDefaultToScheduledWhenStatusIsNull() {
        CitaMedica cita = new CitaMedica(
                "pac-1",
                "doc-1",
                "esp-1",
                FECHA,
                "Dolor de cabeza",
                null
        );

        assertEquals(EstadoCita.PROGRAMADA, cita.getEstado());
    }

}
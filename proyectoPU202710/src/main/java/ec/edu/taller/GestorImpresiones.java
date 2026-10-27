package ec.edu.taller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
public class GestorImpresiones {
    private Queue<TrabajoImpresion> colaTrabajos;
    private int capacidadMaxima;
    public GestorImpresiones(int capacidadMaxima) {
        //TODO: guardar la capacidad y crear la cola de prioridad.
        if (capacidadMaxima < 0) {
            throw new IllegalArgumentException(
                    "La capacidad no puede ser negativa."
            );
        }
        this.capacidadMaxima = capacidadMaxima;
        // Menor número significa mayor prioridad.
        this.colaTrabajos = new PriorityQueue<>(
                Comparator.comparingInt(
                        TrabajoImpresion::getPrioridad
                )
        );
    }
    public boolean registrarTrabajo(TrabajoImpresion trabajo) {
        // TODO: validar, comprobar duplicados y agregar a la cola.
        if (trabajo == null) {
            return false;
        }
        String codigo = trabajo.getCodigo();
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        if (trabajo.getSolicitante() == null
                || trabajo.getSolicitante().trim().isEmpty()) {
            return false;
        }
        if (trabajo.getDocumento() == null || trabajo.getDocumento().trim().isEmpty()) {
            return false;
        }
        if (trabajo.getPrioridad() < 1 || trabajo.getPrioridad() > 10) {
            return false;
        }
        if (colaTrabajos.size() >= capacidadMaxima) {
            return false;
        }
        if (existeTrabajo(codigo)) {
            return false;
        }
        trabajo.setEstado("PENDIENTE");
        colaTrabajos.offer(trabajo);
        return true;
    }
    public boolean existeTrabajo(String codigo) {
        //TODO: buscar por codigo entre los trabajos pendientes.
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        for (TrabajoImpresion trabajo : colaTrabajos) {
            if (codigo.equals(trabajo.getCodigo())) {
                return true;
            }
        }
        return false;
    }
    public TrabajoImpresion consultarSiguienteTrabajo() {
        // TODO: consultar el trabajo de mayor prioridad sin retirarlo de la cola.
        return colaTrabajos.peek();
    }
    public TrabajoImpresion procesarSiguienteTrabajo() {
        // TODO: retirar el de mayor prioridad de la cola y actualizar su estado a "PROCESADO".
        TrabajoImpresion trabajo = colaTrabajos.poll();
        if (trabajo != null) {
            trabajo.setEstado("PROCESADO");
        }
        return trabajo;
    }
    public int contarTrabajosPendientes() {
        //TODO: devolver la cantidad de trabajos pendientes en la cola.
        return colaTrabajos.size();
    }
    public int consultarEspaciosDisponibles() {
        //TODO: devolver la cantidad maxima menos trabajos pendientes.

        return capacidadMaxima - colaTrabajos.size();
    }
    public List<TrabajoImpresion> listarTrabajos() {
        // TODO: devolver una lista ordenada por prioridad sin alterar la cola.
        List<TrabajoImpresion> lista = new ArrayList<>(colaTrabajos);
        lista.sort(Comparator.comparingInt(TrabajoImpresion::getPrioridad)
        );
        return lista;
    }
}
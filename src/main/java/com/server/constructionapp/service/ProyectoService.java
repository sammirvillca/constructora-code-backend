package com.server.constructionapp.service;

import com.server.constructionapp.DTO.*;
import com.server.constructionapp.model.*;
import com.server.constructionapp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepo proyectoRepo;
    @Autowired
    private CronogramaRepo cronogramaRepo;
    @Autowired
    private DibujoPlanoRepo dibujoPlanoRepo;
    @Autowired
    private TrabajadorRepo trabajadorRepo;
    @Autowired
    private OrdenPedidoMaterialRepo ordenPedidoMaterialRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private CatalogoProveedorRepo catalogoProveedorRepo;

    public ProyectoDTO crearProyecto(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = mapToEntity(proyectoDTO);

        Long cronogramaId = proyectoDTO.getCronogramaId();
        if (cronogramaId != null) {
            Cronograma cronograma = cronogramaRepo.findById(cronogramaId)
                    .orElseThrow(() -> new EntityNotFoundException("Cronograma no encontrado con ID: " + cronogramaId));
            proyecto.setCronograma(cronograma);
        }

        Long encargadoId = proyectoDTO.getEncargadoId();
        if (encargadoId != null) {
            Trabajador encargado = trabajadorRepo.findById(encargadoId)
                    .orElseThrow(() -> new EntityNotFoundException("Encargado no encontrado con ID: " + encargadoId));
            proyecto.setEncargado(encargado);
        }

        Long dibujoPlanoId = proyectoDTO.getDibujoPlanoId();
        if (dibujoPlanoId != null) {
            DibujoPlano dibujoPlano = dibujoPlanoRepo.findById(dibujoPlanoId)
                    .orElseThrow(() -> new EntityNotFoundException("Dibujo Plano no encontrado con ID: " + dibujoPlanoId));
            proyecto.setDibujoPlano(dibujoPlano);
        }

        Proyecto nuevoProyecto = proyectoRepo.save(proyecto);
        return mapToDTO(nuevoProyecto);
    }

    public ProyectoDTO obtenerProyecto(Long id) {
        Proyecto proyecto = proyectoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + id));
        return mapToDTO(proyecto);
    }

    public List<ProyectoDTO> obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = proyectoRepo.findAll();
        return proyectos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProyectoDTO actualizarProyecto(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = mapToEntity(proyectoDTO);
        Proyecto proyectoActualizado = proyectoRepo.save(proyecto);
        return mapToDTO(proyectoActualizado);
    }

    public void eliminarProyecto(Long id) {
        proyectoRepo.deleteById(id);
    }

    public void asignarTrabajadoresAProyecto(Long proyectoId, List<Long> trabajadoresIds) {
        Proyecto proyecto = proyectoRepo.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));

        List<Trabajador> trabajadores = trabajadorRepo.findAllById(trabajadoresIds);
        proyecto.setTrabajadores(trabajadores);

        proyectoRepo.save(proyecto);
    }

    public void asignarOrdenesPedidoAProyecto(Long proyectoId, List<Long> ordenesPedidoIds) {
        Proyecto proyecto = proyectoRepo.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));

        List<OrdenPedidoMaterial> ordenesPedido = ordenPedidoMaterialRepo.findAllById(ordenesPedidoIds);
        proyecto.setOrdenesPedidoMaterial(ordenesPedido);

        proyectoRepo.save(proyecto);
    }

    public List<ProyectoDTO> obtenerProyectosNoVinculados() {
        List<Proyecto> proyectos = proyectoRepo.findProyectosNoVinculados();
        return proyectos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void desvincularTrabajadoresDeProyecto(Long proyectoId, List<Long> trabajadoresIds) {
        Proyecto proyecto = proyectoRepo.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));

        List<Trabajador> trabajadoresADesvincular = trabajadorRepo.findAllById(trabajadoresIds);
        proyecto.getTrabajadores().removeAll(trabajadoresADesvincular);

        proyectoRepo.save(proyecto);
    }

    private ProyectoDTO mapToDTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setDescription(proyecto.getDescription());
        dto.setCodProyecto(proyecto.getCodProyecto());

        DibujoPlano dibujoPlano = proyecto.getDibujoPlano();
        if (dibujoPlano != null) {
            dto.setDibujoPlanoId(dibujoPlano.getId());
        }

        Cronograma cronograma = proyecto.getCronograma();
        if (cronograma != null) {
            dto.setCronogramaId(cronograma.getId());
        }

        Trabajador encargado = proyecto.getEncargado();
        if (encargado != null) {
            dto.setEncargadoId(encargado.getId());
        }

        DibujoPlano dibujoPlano1 = proyecto.getDibujoPlano();
        if (dibujoPlano1 != null && dibujoPlano1.getPrerequisitoPlano() != null) {
            PrerequisitoPlano prerequisitoPlano = dibujoPlano1.getPrerequisitoPlano();
            Cliente cliente = prerequisitoPlano.getCliente();
            if (cliente != null) {
                dto.setClienteId(cliente.getId());
                dto.setClienteCodCliente(cliente.getCodCliente());
                dto.setClienteFullName(cliente.getFullName());
            }
        }


        return dto;
    }

    private Proyecto mapToEntity(ProyectoDTO dto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(dto.getId());
        proyecto.setDescription(dto.getDescription());
        proyecto.setCodProyecto(dto.getCodProyecto());

        Long dibujoPlanoId = dto.getDibujoPlanoId();
        if (dibujoPlanoId != null) {
            DibujoPlano dibujoPlano = dibujoPlanoRepo.findById(dibujoPlanoId)
                    .orElseThrow(() -> new EntityNotFoundException("DibujoPlano no encontrado con ID: " + dibujoPlanoId));
            proyecto.setDibujoPlano(dibujoPlano);
        }

        Long cronogramaId = dto.getCronogramaId();
        if (cronogramaId != null) {
            Cronograma cronograma = cronogramaRepo.findById(cronogramaId)
                    .orElseThrow(() -> new EntityNotFoundException("Cronograma no encontrado con ID: " + cronogramaId));
            proyecto.setCronograma(cronograma);
        }

        Long encargadoId = dto.getEncargadoId();
        if (encargadoId != null) {
            Trabajador encargado = trabajadorRepo.findById(encargadoId)
                    .orElseThrow(() -> new EntityNotFoundException("Encargado no encontrado con ID: " + encargadoId));
            proyecto.setEncargado(encargado);
        }

        return proyecto;
    }

    public List<TrabajadorDTO> obtenerTrabajadoresAsignadosAProyecto(Long proyectoId) {
        Proyecto proyecto = proyectoRepo.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));

        List<Trabajador> trabajadores = proyecto.getTrabajadores();
        return trabajadores.stream()
                .map(this::mapTrabajadorToDTO)
                .collect(Collectors.toList());
    }

    // ...

    private TrabajadorDTO mapTrabajadorToDTO(Trabajador trabajador) {
        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setId(trabajador.getId());
        dto.setFullName(trabajador.getFullName());
        dto.setIdentityCard(trabajador.getIdentityCard());
        dto.setRol(trabajador.getRol());
        dto.setAddress(trabajador.getAddress());
        dto.setEmail(trabajador.getEmail());
        dto.setPhone(trabajador.getPhone());
        return dto;
    }

    public List<OrdenPedidoMaterialDTO> obtenerOrdenesPedidoMaterialPorProyecto(Long proyectoId) {
        List<OrdenPedidoMaterial> ordenesPedidoMaterial = ordenPedidoMaterialRepo.findByProyectoId(proyectoId);
        return ordenesPedidoMaterial.stream()
                .map(ordenPedidoMaterial -> {
                    OrdenPedidoMaterialDTO dto = new OrdenPedidoMaterialDTO();
                    dto.setId(ordenPedidoMaterial.getId());
                    dto.setOrderDate(ordenPedidoMaterial.getOrderDate());
                    dto.setAmountMaterial(ordenPedidoMaterial.getAmountMaterial());
                    dto.setProyectoId(ordenPedidoMaterial.getProyecto().getId());
                    dto.setCatalogoProveedorId(ordenPedidoMaterial.getCatalogoProveedor().getId());
                    dto.setProveedorNombre(ordenPedidoMaterial.getCatalogoProveedor().getProveedor().getName());
                    dto.setMaterialNombre(ordenPedidoMaterial.getCatalogoProveedor().getMaterial());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private OrdenPedidoMaterialDTO mapToOrdenPedidoMaterialDTO(OrdenPedidoMaterial ordenPedidoMaterial) {
        OrdenPedidoMaterialDTO dto = new OrdenPedidoMaterialDTO();
        dto.setId(ordenPedidoMaterial.getId());
        dto.setOrderDate(ordenPedidoMaterial.getOrderDate());
        dto.setAmountMaterial(ordenPedidoMaterial.getAmountMaterial());
        return dto;
    }
    public List<CronogramaDTO> obtenerCronogramasDisponibles() {
        List<Cronograma> cronogramas = cronogramaRepo.findByProyectoIsNull();
        return cronogramas.stream()
                .map(this::mapCronogramaToDTO)
                .collect(Collectors.toList());
    }

    private CronogramaDTO mapCronogramaToDTO(Cronograma cronograma) {
        CronogramaDTO dto = new CronogramaDTO();
        dto.setId(cronograma.getId());
        dto.setCodCronograma(cronograma.getCodCronograma());
        return dto;
    }

    public List<TrabajadorDTO> obtenerTrabajadoresIngCivil() {
        List<Trabajador> trabajadores = trabajadorRepo.findByRol("Ing. Civil");
        return trabajadores.stream()
                .map(this::mapTrabajadorToDTO)
                .collect(Collectors.toList());
    }
    public List<DibujoPlanoDTO> obtenerPlanosSinVincular() {
        List<DibujoPlano> planos = dibujoPlanoRepo.findByProyectoIsNull();
        return planos.stream()
                .map(this::mapDibujoPlanoToDTO)
                .collect(Collectors.toList());
    }

    private DibujoPlanoDTO mapDibujoPlanoToDTO(DibujoPlano dibujoPlano) {
        DibujoPlanoDTO dto = new DibujoPlanoDTO();
        dto.setId(dibujoPlano.getId());
        dto.setCodDiseño(dibujoPlano.getCodDiseño());
        return dto;
    }

    public List<ProyectoDTO> obtenerProyectosSinEntrega() {
        List<Proyecto> proyectos = proyectoRepo.findProyectosSinEntrega();
        return proyectos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrdenPedidoMaterialDTO actualizarOrdenPedidoMaterial(Long id, OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        OrdenPedidoMaterial ordenPedidoMaterial = ordenPedidoMaterialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden de Pedido de Material no encontrada con ID: " + id));

        ordenPedidoMaterial.setOrderDate(ordenPedidoMaterialDTO.getOrderDate());
        ordenPedidoMaterial.setAmountMaterial(ordenPedidoMaterialDTO.getAmountMaterial());

        CatalogoProveedor catalogoProveedor = catalogoProveedorRepo.findById(ordenPedidoMaterialDTO.getCatalogoProveedorId())
                .orElseThrow(() -> new EntityNotFoundException("Catálogo de Proveedor no encontrado con ID: " + ordenPedidoMaterialDTO.getCatalogoProveedorId()));
        ordenPedidoMaterial.setCatalogoProveedor(catalogoProveedor);

        OrdenPedidoMaterial ordenPedidoMaterialActualizada = ordenPedidoMaterialRepo.save(ordenPedidoMaterial);
        return mapToOrdenPedidoMaterialDTO(ordenPedidoMaterialActualizada);
    }

    public void eliminarOrdenPedidoMaterial(Long id) {
        ordenPedidoMaterialRepo.deleteById(id);
    }


}

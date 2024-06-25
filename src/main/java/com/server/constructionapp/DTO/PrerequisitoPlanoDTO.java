package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrerequisitoPlanoDTO {
    private Long id;
    private Integer roomsQuantity;
    private Integer floorsQuantity;
    private Integer bathroomsQuantity;
    private String size;
    private String tastes;
    private String dislikes;
    private Long clienteId;
    private Long trabajadorId;
    private String codReq;
    private String clienteFullName;


}

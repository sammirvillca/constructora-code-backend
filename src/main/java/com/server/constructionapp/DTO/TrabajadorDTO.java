package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrabajadorDTO {
    private Long id;
    private String fullName;
    private String identityCard;
    private String rol;
    private String address;
    private String email;
    private Long phone;
    private String codTrabajador;
}

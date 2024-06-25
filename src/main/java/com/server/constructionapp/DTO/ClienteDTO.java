package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String codCliente;
    private String fullName;
    private String email;
    private Long phone;
    private String address;
}

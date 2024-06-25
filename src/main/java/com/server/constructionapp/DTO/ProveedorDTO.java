package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProveedorDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private Long phone;
    private String email;
    private String codProveedor;
}

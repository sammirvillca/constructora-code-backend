package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DepositoClienteDTO {
    private Long id;
    private String payMethod;
    private byte[] payPhoto;
    private String payDetails;
    private LocalDate payDate;
    private Long datosClienteId;
}

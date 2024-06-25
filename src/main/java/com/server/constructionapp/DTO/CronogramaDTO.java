package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CronogramaDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String codCronograma;
}

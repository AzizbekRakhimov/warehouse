package uz.azizbek.payload;

import lombok.Data;

@Data
public class OutputDto {
    private Long currencyId;
    private Long warehouseId;
    private Long clientId;
    private String factureNumber;
}

package uz.azizbek.payload;

import lombok.Data;

@Data
public class InputDto {
    private Long currencyId;
    private Long warehouseId;
    private Long supplierId;
    private String factureNumber;
}

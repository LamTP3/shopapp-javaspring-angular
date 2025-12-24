package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order ID must be greater than or equal to 1")
    private long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product ID must be greater than or equal to 1")
    private long productId;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private long price;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "Number of products must be greater than or equal to 1")
    private int numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be greater than or equal to 0")
//    @NotNull(message = "Total money is required")
//    @DecimalMin(value = "0.0", inclusive = true, message = "Total money must be greater than or equal to 0")
    private Float totalMoney;

    private String color;
}

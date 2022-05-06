package mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductDTO {
    private String code;
    private String name;
    private int qty;
    private int price;
    private String detail;

}

package mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSearchDTO {
    private SearchBy searchBy;
    private String searchKeyword;

    private SortBy sortBy;

}


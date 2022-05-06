package main;

import mvc.dao.ProductDAO;
import mvc.dto.ProductDTO;
import mvc.dto.ProductSearchDTO;
import mvc.dto.SearchBy;
import mvc.dto.SortBy;

import java.util.Arrays;

public class ProductMainView {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        /**
         * [1]. 레코드 삽입
         * */
        /*
        System.out.println("********** [1] 상품 등록하기 ******************");
        productDAO.insertProduct( new ProductDTO("B01", "몽쉘", 15, 5000, "초코의 달달함이 어우러진 고급스런 풍미의 디저트 스낵") );
        productDAO.insertProduct( new ProductDTO("B02", "가짜몽쉘", 30, 4200, "초코의 달달함이 어우러진 고급스런 풍미의 디저트 스낵") );
        */


        /**
         * [2].   ex) select * from productList
         *         ex)  select * from productList where 검색필드 like  검색단어
         *          ex) select * from productList order by 정렬대상
         *          ex) select * from productList where 검색필드 like  검색단어  order by 정렬대상
         * */
        System.out.println("********** [2] 동적 쿼리 - 모든 게시물 검색 또는 조건에 따른 검색 및 정렬 ******************");
        System.out.println("------ Search Type : KEYWORD ------");
        productDAO.selectProduct(
            new ProductSearchDTO(SearchBy.NAME, "몽", null)
        );

        System.out.println("------ Search TYPE : SORT ------");
        productDAO.selectProduct(
                new ProductSearchDTO(null, null, SortBy.QTY)
        );

        System.out.println("------ Search Type : BOTH ------");
        productDAO.selectProduct(
                new ProductSearchDTO(SearchBy.NAME, "몽", SortBy.PRICE)
        );


        /**
         * [3].  parameter 정보에 따라 레코드 수정한다.
         *     상품코드에 해당하는 상품의
         *     상품이름, 상품수량, 상품가격, 상품상세정보를 수정하데 parameter 의 정보가 있는 데이터만 수정한다.
         *
         * */
        System.out.println("********** [3] 동적 쿼리 - 상품 수정하기 ******************");
        productDAO.updateProduct(
            new ProductDTO("B01", "오리지날몽쉘", 0, 0, null)
        );


        /**
         * [4].상품코드에 해당하는 레코드 삭제
         * */
        System.out.println("********** [4] 상품 삭제하기 ******************");
        productDAO.deleteProduct("B02");

        /**
         * [5]. 모든 상품의 개수를 출력한다.

         select sum(p_qty) from product -- 모든상품의 qty의총합계
         * */
        System.out.println("********** [5] 등록된 총 상품 개수 ******************");
        productDAO.getTotalProductCount();


        /**
         * [6]. parameter의 상품코드에 해당하는 레코드 검색
         * ex)select * from productList where in 상품코드 in (?,?,...)
         * */
        System.out.println("********** [6] 상품코드에 해당하는 레코드 검색 ******************");
        productDAO.selectByCodes(Arrays.asList("A01", "A02", "B01"));

    }
}

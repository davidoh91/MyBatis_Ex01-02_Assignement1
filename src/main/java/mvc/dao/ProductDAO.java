package mvc.dao;

import mvc.dto.ProductDTO;
import mvc.dto.ProductSearchDTO;
import org.apache.ibatis.session.SqlSession;
import util.DbUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {

    ///////////////////////// [1] insert a product /////////////////////////
    public void insertProduct(ProductDTO productDTO) {
        System.out.println("ProductDAO insertProduct(ProductDTO productDTO) method invoked..");

        SqlSession session = null;
        boolean state = false;
        try {
            session = DbUtil.getSession();
            state = session.insert("productMapper.insertProduct", productDTO)>0 ? true : false;
        }finally {
            DbUtil.sessionClose(session, state);
        }
    }

    //////////////////// [2] dynamic query - select all by dynamic conditions ///////////////
    public void selectProduct(ProductSearchDTO productSearchDTO) {
        System.out.println("ProductDAO selectProduct(ProductSearchDTO productSearchDTO) method invoked..");
        System.out.println("productSearchDTO.getSearchBy() : " + productSearchDTO.getSearchBy());
        System.out.println("productSearchDTO.getSearchKeyword() : " + productSearchDTO.getSearchKeyword());
        System.out.println("productSearchDTO.getSortBy() : " + productSearchDTO.getSortBy());

        /* failed attempt ..
        SqlSession session = null;
        try {
            session = DbUtil.getSession();
            List<ProductDTO> list = session.selectList("productMapper.selectProduct", productSearchDTO);

            for(ProductDTO p : list) {
                System.out.println(p);
            }
        }finally {
            DbUtil.sessionClose(session);
        }
        */

        Map<String, Object> map = new HashMap<>();
        map.put("searchBy", productSearchDTO.getSearchBy());
        map.put("searchKeyword", productSearchDTO.getSearchKeyword());
        map.put("sortBy", productSearchDTO.getSortBy());

        SqlSession session = null;
        try {
            session = DbUtil.getSession();
            List<ProductDTO> list = session.selectList("productMapper.selectProduct", map);

            for(ProductDTO p : list) {
                System.out.println(p);
            }
            System.out.println();
        }finally {
            DbUtil.sessionClose(session);
        }

    }


    ///////////////////////// [3] update product /////////////////////////
    public void updateProduct(ProductDTO productDTO) {
        System.out.println("ProductDAO updateProduct(ProductDTO productDTO) method invoked..");

        SqlSession session = null;
        boolean state = false;
        try {
            session = DbUtil.getSession();
            state = session.delete("productMapper.updateProduct", productDTO)>0 ? true : false;
        }finally {
            DbUtil.sessionClose(session, state);
        }
    }

    ///////////////////////// [4] delete product /////////////////////////
    public void deleteProduct(String code) {
        System.out.println("ProductDAO deleteProduct(String code) method invoked..");

        SqlSession session = null;
        boolean state = false;
        try {
            session = DbUtil.getSession();
            state = session.update("productMapper.deleteProduct", code)>0 ? true : false;
        }finally {
            DbUtil.sessionClose(session, state);
        }
    }

    ///////////////////////// [5] total qty of all products /////////////////////////
    public void getTotalProductCount() {
        System.out.println("ProductDAO getTotalProductCount() method invoked..");

        SqlSession session = null;
        try {
            session = DbUtil.getSession();
            int count = session.selectOne("productMapper.getTotalProductCount");

            System.out.println("모든 상품의 총 갯수 : " + count + " 개");
        }finally {
            DbUtil.sessionClose(session);
        }
    }

    ///////////////////////// [6] select products from param product codes /////////////////////////
    public void selectByCodes(List<String> codes) {
        System.out.println("ProductDAO selectByCodes(List codes) method invoked..");

        SqlSession session = null;
        try {
            session = DbUtil.getSession();
            List<ProductDTO> list = session.selectList("productMapper.selectByCodes", codes);

            for(ProductDTO p : list) {
                System.out.println(p);
            }
            System.out.println();
        }finally {
            DbUtil.sessionClose(session);
        }

    }

}

package cn.itcast.babasport.service.product;

import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Product;

public interface ProductService {
	
	
	/**
	 * 商品列表带,分页对象
	 * @param name
	 * @param isShow
	 * @param pageNo
	 * @return
	 */
	public Pagination selectProductListHavaPage(String name ,Boolean isShow,Long brandId,Integer pageNo);
	
	/**
	 * 添加商品
	 * @param product
	 */
	public void insertSelective(Product product);
	
	/**
	 *商品上架
	 * @param ids
	 */
	public void updateProductToSale(Long[] ids) throws Exception;;
	
	/**
	 * 商品下架
	 * @param ids
	 */
	public void updateProductToUnsale(Long[] ids) throws Exception;
}

package cn.itcast.babasport.service.product;

import java.util.List;

import cn.itcast.babasport.pojo.product.Sku;

public interface SkuService {
	
	/**
	 * 根据商品id查询库存信息
	 * @param id
	 * @return
	 */
	public List<Sku> selectSkuListByProductId(Long id);
	
	/**
	 * 更新
	 * @param sku
	 */
	public void updateSku(Sku sku);

}

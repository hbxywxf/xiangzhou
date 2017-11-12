package cn.itcast.babasport.service.product;

import java.util.List;

import cn.itcast.babasport.pojo.product.Color;
import cn.itcast.babasport.pojo.product.ColorQuery;

public interface ColorService {
	
	/**
	 * 商品颜色列表
	 * @param example
	 * @return
	 */
	 List<Color> selectByExample();

}

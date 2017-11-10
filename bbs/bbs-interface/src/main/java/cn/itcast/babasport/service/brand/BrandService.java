package cn.itcast.babasport.service.brand;

import java.util.List;

import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Brand;

public interface BrandService {

	/**
	 * 品牌列表查询  不分页
	 * @param brandQuery
	 * @return
	 */
	public List<Brand> selectBrandListNoPage(String name ,Integer isDisplay);
	/**
	 * 品牌列表查询  分页
	 * @param brandQuery
	 * @return
	 */
	public Pagination selectBrandListHavePage(String name ,Integer isDisplay,Integer pageNo);
	/**
	 * id查询品牌对象
	 */
	public Brand selectBrandById(Integer id);
	/**
	 * 根据id更新品牌信息
	 */
	public void updateBrandById(Brand brand);
	/**
	 * 添加品牌
	 */
	public void insertBrand(Brand brand);
	/**
	 * 批量删除品牌
	 */
	public void deleteBatchBrandByIds(Integer[] ids);
}

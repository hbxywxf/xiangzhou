package cn.itcast.babasport.mapper.product;

import java.util.List;

import cn.itcast.babasport.pojo.product.Brand;
import cn.itcast.babasport.pojo.product.BrandQuery;

/**
 * 品牌管理的mapper接口
 *<p>title:BrandMapper
 *
 * @author zk
 *
 * @Date 2017年11月9日
 */
public interface BrandMapper {

	/**
	 * 品牌列表查询  不分页
	 * @param brandQuery
	 * @return
	 */
	public List<Brand> selectBrandListNoPage(BrandQuery brandQuery);
	/**
	 * 品牌列表查询  分页
	 * @param brandQuery
	 * @return
	 */
	public List<Brand> selectBrandListHavePage(BrandQuery brandQuery);
	/**
	 * 品牌的总条数
	 */
	public int selectBrandCount(BrandQuery brandQuery);
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

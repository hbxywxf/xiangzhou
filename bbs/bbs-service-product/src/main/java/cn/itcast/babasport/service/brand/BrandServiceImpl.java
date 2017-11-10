package cn.itcast.babasport.service.brand;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.babasport.mapper.product.BrandMapper;
import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Brand;
import cn.itcast.babasport.pojo.product.BrandQuery;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandMapper brandMapper;
	
	/**
	 * 品牌列表查询  不分页
	 */
	@Override
	public List<Brand> selectBrandListNoPage(String name, Integer isDisplay) {
		BrandQuery brandQuery = new BrandQuery();
		if(name !=null){
			brandQuery.setName(name);
		}
		if(isDisplay !=null){
			brandQuery.setIsDisplay(isDisplay);
		}
		List<Brand> brands = brandMapper.selectBrandListNoPage(brandQuery);
		return brands;
	}
	/**
	 * 品牌列表查询  分页
	 */
	@Override
	public Pagination selectBrandListHavePage(String name, Integer isDisplay, Integer pageNo) {
		BrandQuery brandQuery = new BrandQuery();
		StringBuilder params = new StringBuilder();
		if(name !=null && !"".equals(name)){
			brandQuery.setName(name);
			params.append("name=").append(name);
		}
		if(isDisplay !=null){
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay = ").append(isDisplay);
		}
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		List<Brand> brands = brandMapper.selectBrandListHavePage(brandQuery);
		int totalCount = brandMapper.selectBrandCount(brandQuery);
		Pagination pagination = new Pagination(brandQuery.getPageNo(), brandQuery.getPageSize(), totalCount, brands);
		//传入地址栏
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	/**
	 * 根据id查询品牌信息
	 */
	@Override
	public Brand selectBrandById(Integer id) {
		Brand brand = brandMapper.selectBrandById(id);
		return brand;
	}
	/**
	 * 修改品牌信息
	 */
	@Override
	public void updateBrandById(Brand brand) {
		brandMapper.updateBrandById(brand);
	}
	/**
	 * 添加品牌
	 */
	@Override
	public void insertBrand(Brand brand) {
		brandMapper.insertBrand(brand);
	}
	/**
	 * 批量删除品牌
	 */
	@Override
	public void deleteBatchBrandByIds(Integer[] ids) {
		 brandMapper.deleteBatchBrandByIds(ids);
	}


}

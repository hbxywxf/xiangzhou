package cn.itcast.babasport.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.babasport.mapper.product.ColorMapper;
import cn.itcast.babasport.mapper.product.SkuMapper;
import cn.itcast.babasport.pojo.product.ColorQuery;
import cn.itcast.babasport.pojo.product.Sku;
import cn.itcast.babasport.pojo.product.SkuQuery;

/**
 * 库存管理的业务实现类
 * <p>Title:SkuServiceImpl</p>
 * <p>Description:</p>
 * @author lenovo
 * @Date 2017年11月11日
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {

	@Resource
	private SkuMapper skuMapper;
	@Resource
	private  ColorMapper colorMapper;
	
	/*根据商品id查询库存信息
	 * (non-Javadoc)
	 * @see cn.itcast.babasport.service.product.SkuService#selectSkuByProductId(java.lang.Long)
	 */
	@Override
	public List<Sku> selectSkuListByProductId(Long id) {
		// 创建sKuQuery对象,封装查询条件
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(id);
		// 查询结果
		List<Sku> skus = skuMapper.selectByExample(skuQuery);
		
		for (Sku sku : skus) {
			sku.setColor(colorMapper.selectByPrimaryKey(sku.getColorId()));//设置颜信息
		}
		return skus;
	}

	/**
	 * 更新
	 */
	@Transactional
	@Override
	public void updateSku(Sku sku) {
		// 该方法sql里面有非空判断
		skuMapper.updateByPrimaryKeySelective(sku);
		
	}

}

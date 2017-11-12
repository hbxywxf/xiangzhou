package cn.itcast.babasport.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.babasport.mapper.product.ColorMapper;
import cn.itcast.babasport.pojo.product.Color;
import cn.itcast.babasport.pojo.product.ColorQuery;
import cn.itcast.babasport.pojo.product.ColorQuery.Criteria;

/**
 * 商品颜色的业务实现类
 * 事务加在增删改方法上
 * <p>Title:ColorServiceImpl</p>
 * <p>Description:</p>
 * @author lenovo
 * @Date 2017年11月10日
 */

@Service("colorService")
public class ColorServiceImpl implements ColorService{

	@Resource
	private ColorMapper colorMapper ;
	@Override
	public List<Color> selectByExample() {
		//创建colorQuery对象封装查询条件
		ColorQuery colorQuery = new ColorQuery();
		Criteria criteria = colorQuery.createCriteria();
		criteria.andParentIdNotEqualTo(0L);
		List<Color> colors = colorMapper.selectByExample(colorQuery);
		return colors;
	}

}

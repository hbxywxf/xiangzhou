package cn.itcast.babasport.service.product;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.babasport.mapper.product.ProductMapper;
import cn.itcast.babasport.mapper.product.SkuMapper;
import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Product;
import cn.itcast.babasport.pojo.product.ProductQuery;
import cn.itcast.babasport.pojo.product.ProductQuery.Criteria;
import cn.itcast.babasport.pojo.product.Sku;
import cn.itcast.babasport.pojo.product.SkuQuery;
import redis.clients.jedis.Jedis;
/**
 * 商品业层实现类
 * 
 * 事务注解只在增删改上
 * <p>Title:ProductServiceImpl</p>
 * <p>Description:</p>
 * @author lenovo
 * @Date 2017年11月10日
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductMapper productMapper ;
	@Resource
	private SkuMapper skuMapper ;
	
	@Resource
	private Jedis jedis;
	@Resource
	private SolrServer solrServer;
	
	/*商品列表
	 * (non-Javadoc)
	 * @see cn.itcast.babasport.service.product.ProductService#selectProductListHavaPage(java.lang.String, java.lang.Boolean, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public Pagination selectProductListHavaPage(String name, Boolean isShow, Long brandId, Integer pageNo) {
		//1.创建ProductQuery对象封装查询对象
		ProductQuery productQuery = new ProductQuery();
		Criteria criteria = productQuery.createCriteria();
		StringBuffer params = new StringBuffer();
		if (name != null && !"".equals(name)) {
			criteria.andNameLike("%" + name + "%");
			params.append("name=").append(name);
		}
		if (isShow != null) {
			criteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{//否则设置查询默认值(根据业务需求定)
			criteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		if (brandId != null) {
			criteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		productQuery.setPageNo(Pagination.cpn(pageNo));//当前页码
		productQuery.setPageSize(5);//每页显示的条数
		productQuery.setOrderByClause("id desc");//按id降序排,这样新添加的就在最前面
		//2.查询结果
		int totalCount = productMapper.countByExample(productQuery);
		List<Product> list = productMapper.selectByExample(productQuery);
		//3.创建Pagination分页对象,并将查询结果
		Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), totalCount, list);
		
		//4.构建分页工具栏
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	/**
	 * 添加商品
	 */
	@Transactional
	@Override
	public void insertSelective(Product product) {
		
		jedis.incr("pno");
		Long id = Long.parseLong(jedis.get("pno"));
		product.setId(id);
		// 1.保存商品,需要返回主键(商品主键是做为库存表的外键使用的)
		
		product.setIsShow(false);//默认下架
		product.setIsDel(false);//是否删除  不删除---可用商品
		product.setCreateTime(new Date());//商品的创建日期
		
		productMapper.insertSelective(product);//必须先保存商品
		
		// 2.初始化商品对应的库存信息
		String[] colors = null;
		String[] sizes  = null;
		if (product.getColors() != null) {
			
			colors = product.getColors().split(",");//选择的颜色结果集
		}
		if (product.getSizes() != null) {
			sizes = product.getSizes().split(",");//选择的尺码结果集
		}
		for (String size : sizes) {
			for (String colorId : colors) {
				Sku sku = new Sku();
				sku.setColorId(Long.parseLong(colorId));
				sku.setCreateTime(new Date());
				sku.setDeliveFee(0f);
				sku.setMarketPrice(0f);
				sku.setPrice(0f);
//				sku.setProductId(product.getId());
				sku.setProductId(id);
				sku.setSize(size);
				sku.setStock(0);
				sku.setUpperLimit(200);
				skuMapper.insertSelective(sku);
			}
		}
		
		
	}

	
	/*
	 * 商品上架
	 * (non-Javadoc)
	 * @see cn.itcast.babasport.service.product.ProductService#updateProductToSale(java.lang.Long[])
	 */
	@Override
	public void updateProductToSale(Long[] ids) throws Exception {
		//创建product对象,封装数据
		Product product = new Product();
		for (Long id : ids) {
			product.setId(id);
			product.setIsShow(true);//设置上架
			productMapper.updateByPrimaryKeySelective(product);
			
			// 将上架商品保存到solr中
			Product goods = productMapper.selectByPrimaryKey(id);
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", id); //商品id
			doc.addField("name_ik",goods.getName());//商品名称
			doc.addField("brandId", goods.getBrandId());//商品所属品牌
			doc.addField("url", goods.getAllUrls()[0]);//商品图片
			//商品价格分析 一款商品对应的库存信息恩多,那么价格也很多,晒我们需要取出最低的价格
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.createCriteria().andProductIdEqualTo(id);
			skuQuery.setFields("price");
			skuQuery.setOrderByClause("price asc");
			skuQuery.setPageNo(1);
			skuQuery.setPageSize(1);
			Sku sku = skuMapper.selectByExample(skuQuery).get(0);
			doc.addField("price", sku.getPrice());
			solrServer.add(doc);
			solrServer.commit();
		}
		
		
		
		
	}

	@Override
	public void updateProductToUnsale(Long[] ids) {
		//创建Product的对象,封装数据
		Product product = new Product();
		for (Long id : ids) {
			product.setId(id);
			product.setIsShow(false);//商品下架
			productMapper.updateByPrimaryKeySelective(product);
		}
		
	}

}

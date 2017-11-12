package cn.itcast.babasport.controller.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Brand;
import cn.itcast.babasport.pojo.product.Color;
import cn.itcast.babasport.pojo.product.Product;
import cn.itcast.babasport.service.brand.BrandService;
import cn.itcast.babasport.service.product.ColorService;
import cn.itcast.babasport.service.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService ;
	@Resource
	private ColorService colorService;
	
	
	/**
	 * 商品列表
	 * @param name
	 * @param isShow
	 * @param brandId
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(String name ,Boolean isShow,Long brandId,Integer pageNo,Model model){
		//查询分页对象
		Pagination pagination = productService.selectProductListHavaPage(name, isShow, brandId, pageNo);
		model.addAttribute("pagination", pagination);
		//查询条件回显
		model.addAttribute("name", name);
		model.addAttribute("isShow", isShow);
		model.addAttribute("brandId", brandId);
		model.addAttribute("pageNo", pageNo);
		//查询品牌列表,初始化页面
		List<Brand> brands = brandService.selectBrandListNoPage(null, 1);
		model.addAttribute("brands", brands);
		
		return "product/list";
	}
	
	/**
	 * 前往添加页面
	 * @return
	 */
	@RequestMapping("/add.do")
	public String add(Model model){
		//商品品牌,初始化页面
		List<Brand> brands = brandService.selectBrandListNoPage(null, 1);
		model.addAttribute("brands", brands);
		
		//商品颜色,初始化页面数据
		List<Color> colors = colorService.selectByExample();
		model.addAttribute("colors", colors);
		return "product/add";
	}
	/**
	 * 添加商品
	 * @param product
	 * @return
	 */
	@RequestMapping("/save.do")
	public String save(Product product){
		
		productService.insertSelective(product);
		
		return "redirect:list.do";
	}
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/saleBathProduct.do")
	public String saleBathProduct(Long[] ids) throws Exception{
		productService.updateProductToSale(ids);
		
		return "redirect:list.do";
	}
	
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/unsaleBathProduct.do")
	public String unsaleBathProduct(Long[] ids) throws Exception{
		productService.updateProductToUnsale(ids);
		return "redirect:list.do";
	}

}

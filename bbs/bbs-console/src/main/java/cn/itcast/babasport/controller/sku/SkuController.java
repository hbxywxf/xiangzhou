package cn.itcast.babasport.controller.sku;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.babasport.pojo.product.Sku;
import cn.itcast.babasport.service.product.SkuService;

/**
 * 库存管理
 * <p>Title:SkuController</p>
 * <p>Description:</p>
 * @author lenovo
 * @Date 2017年11月11日
 */
@Controller
@RequestMapping("/sku")
public class SkuController {
	
	
	@Resource
	private SkuService skuService ;
	
	/**
	 * 根据商品id查询商品库存
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(Long productId,Model model){
		if (productId != null) {
			List<Sku> skus = skuService.selectSkuListByProductId(productId);
			model.addAttribute("skus", skus);
		}
		return "sku/list";
	}
	
	/**
	 * 跟新
	 * @param sku
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void save(Sku sku,HttpServletResponse response) throws Exception{
		skuService.updateSku(sku);
		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("msg", "保存成功");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonObject.toString());
		
	}

}

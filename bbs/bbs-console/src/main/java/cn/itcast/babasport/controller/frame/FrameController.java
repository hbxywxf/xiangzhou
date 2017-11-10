package cn.itcast.babasport.controller.frame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品tab下页面间的跳转
 *<p>title:FrameController
 *
 * @author zk
 *
 * @Date 2017年11月7日
 */
@Controller
@RequestMapping("/frame")
public class FrameController {

	/**
	 * 跳转到商品tab下product_main的页面
	 */
	@RequestMapping("product_main.do")
	public String product_main(){
		return "frame/product_main";
	}
	/**
	 * 跳转到商品tab下product_left的页面
	 */
	@RequestMapping("product_left.do")
	public String product_left(){
		return "frame/product_left";
	}
}

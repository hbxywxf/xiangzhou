package cn.itcast.babasport.controller.center;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制后台页面之间的跳转
 *<p>title:CenterController
 *
 * @author zk
 *
 * @Date 2017年11月7日
 */
@Controller
@RequestMapping("/center")
public class CenterController {

	/**
	 * 跳转到后台的主页
	 */
	@RequestMapping("/index.do")
	public String index(){
		return "index";
	}
	/**
	 * 跳转到后台的top页
	 */
	@RequestMapping("/top.do")
	public String top(){
		return "top";
	}
	/**
	 * 跳转到后台的main页
	 */
	@RequestMapping("/main.do")
	public String main(){
		return "main";
	}
	/**
	 * 跳转到后台的left页
	 */
	@RequestMapping("/left.do")
	public String left(){
		return "left";
	}
	/**
	 * 跳转到后台的right页
	 */
	@RequestMapping("/right.do")
	public String right(){
		return "right";
	}
}

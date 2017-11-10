package cn.itcast.babasport.controller.brand;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.babasport.pojo.page.Pagination;
import cn.itcast.babasport.pojo.product.Brand;
import cn.itcast.babasport.service.brand.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Resource
	private BrandService brandService;
	/**
	 * 品牌列表查询 分页
	 * @param name
	 * @param isDisplay
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("list.do")
	public String list(String name,Integer isDisplay,Integer pageNo,Model model){
		if(isDisplay==null){
			isDisplay = 1;
		}
		//List<Brand> brands = brandService.selectBrandListNoPage(name, isDisplay);
		Pagination pagination = brandService.selectBrandListHavePage(name, isDisplay, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay);
		model.addAttribute("pageNo",pageNo);//带条件删除
		return "brand/list";
	}
	/**
	 * 前往修改页面 准备回显数据
	 */
	@RequestMapping("edit.do")
	public String edit(Integer id,Model model){
		Brand brand = brandService.selectBrandById(id);
		model.addAttribute("brand",brand);
		return "/brand/edit";
	}
	/**
	 * 更新操作 返回列表页面
	 */
	@RequestMapping("/update.do")
	public String update(Brand brand){
		brandService.updateBrandById(brand);
		return "redirect:list.do";
	}
	/**
	 * 跳转到品牌添加页面
	 */
	@RequestMapping("/add.do")
	public String add(){
		return "brand/add";
	}
	/**
	 * 保存品牌信息
	 */
	@RequestMapping("/save.do")
	public String save(Brand brand){
		brandService.insertBrand(brand);
		return "redirect:list.do";
	}
	/**
	 * 批量删除品牌
	 */
	@RequestMapping("/deleteBatchBrand.do")
	public String deleteBatchBrand(Integer[] ids){
		brandService.deleteBatchBrandByIds(ids);
		return "forward:list.do";
	}
}

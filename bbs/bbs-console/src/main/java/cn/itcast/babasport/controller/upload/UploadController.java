package cn.itcast.babasport.controller.upload;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.itcast.babasport.service.upload.UploadService;

/**
 * 附件上传
 *<p>title:UploadController
 *
 * @author zk
 *
 * @Date 2017年11月9日
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	@Resource
	private UploadService uploadService;
	/**
	 * 品牌管理附件上传
	 */
	@RequestMapping("/uploadPic.do")
	public void uploadPic(MultipartFile pic,HttpServletRequest request,HttpServletResponse response)throws Exception{
		/*//1将附件上传到upload目录下
		if(pic !=null && pic.getSize()>0){
			//附件改名
			String filename = pic.getOriginalFilename();
			String uuid =UUID.randomUUID().toString().replace("-","");
			//后缀
			String suffix = FilenameUtils.getExtension(filename);
			String newName = uuid+"."+suffix;
			//附件上传
			String realPath = request.getServletContext().getRealPath("");
			String pathname="\\upload\\"+newName;
			String path = realPath+pathname;
			File file = new File(path);
			pic.transferTo(file);
			//将结果响应
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("allUrl", pathname); //图片回显
			jsonObject.put("imgUrl", pathname); //图片回显
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonObject.toString());
		}*/
		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename());
		String allUrl="http://192.168.200.128/"+path;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allUrl", allUrl);//图片完整url
		jsonObject.put("imgUrl", path);//图片部分url
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jsonObject.toString());
		
	}
}

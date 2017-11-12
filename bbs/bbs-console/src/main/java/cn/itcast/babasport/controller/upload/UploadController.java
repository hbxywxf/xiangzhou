package cn.itcast.babasport.controller.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;



import cn.itcast.babasport.service.upload.UploadService;
import cn.itcast.babasport.utils.constant.BbsConstant;

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
		String allUrl = BbsConstant.IMG_URL + path;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allUrl", allUrl);//图片完整url
		jsonObject.put("imgUrl", path);//图片部分url
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jsonObject.toString());
		
	}
	
	/**
	 * 批量上传图片
	 * @param pics
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadPics.do")
	@ResponseBody//将对象转成json串
	public Map<String,List<String>> uploadPics(@RequestParam MultipartFile[] pics ) throws Exception{
		
		List<String> allUrls = new  ArrayList<String>();
		List<String> imgUrls = new  ArrayList<String>();
		
		Map<String,List<String>> urls = new HashMap<String, List<String>>();
		for (MultipartFile pic : pics) {
			if (pic != null) {
				String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename());
				String allUrl  = BbsConstant.IMG_URL + path;
				allUrls.add(allUrl);
				imgUrls.add(path);
				urls.put("allUrls", allUrls);
				urls.put("imgUrls", imgUrls);
			}
		}
		
		return urls;
	}
	
	@RequestMapping("/uploadFck.do")
	public void uploadFck(HttpServletRequest request , HttpServletResponse response) throws Exception{
		// 所有的请求消息都在request中,因此可以从reqquest中获取附件信息,但是需要将request进行强转
		MultipartRequest multipartRequest = (MultipartRequest) request;
		//获取附件
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//遍历所有的附件并上传之FastDFD上
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile multipartFile = entry.getValue();
			String path = uploadService.uploadPic(multipartFile.getBytes(), multipartFile.getOriginalFilename());
			String allUrl = BbsConstant.IMG_URL + path;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", allUrl);	// key的名吃必须叫urL,kenderditor根据url取值
			jsonObject.put("error", 0);		//kindeditor根据该error的值表名是否上传成功
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		}
	}
}

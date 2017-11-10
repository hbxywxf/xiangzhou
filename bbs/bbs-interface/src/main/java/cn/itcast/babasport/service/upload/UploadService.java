package cn.itcast.babasport.service.upload;

/**
 * 附件上传的service接口
 *<p>title:UploadService
 *
 * @author zk
 *
 * @Date 2017年11月9日
 */
public interface UploadService {

	/**
	 * 附件上传
	 */
	public String uploadPic(byte[]file_buff,String filename) throws Exception;
}

package cn.itcast.babasport.service.upload;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import cn.itcast.babasport.utils.fdfs.FastDFSUtil;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	/**
	 * 附件上传至FastDFS上
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override
	public String uploadPic(byte[] file_buff, String filename) throws FileNotFoundException, IOException, Exception {
		String path = FastDFSUtil.uploadPicToFDFS(file_buff, filename);

		return path;
	}

}

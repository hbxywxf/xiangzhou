package cn.itcast.babasport.utils.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class FastDFSUtil {

	public static String uploadPicToFDFS(byte[] file_buff,String filename) throws FileNotFoundException, IOException, Exception {
		//创建ClassPathResource对象,用于加载配置文件
		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		//初始化配置文件
		ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
		//获取跟踪服务器的客户端
		TrackerClient trackerClient = new TrackerClient();
		//通过客户端 获取服务端
		TrackerServer trackerServer = trackerClient.getConnection();
		//通过跟踪服务器的服务端 获取存储服务器的客户端
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
		//将附件上传至FastDFS
		//file_buff 附件的字节数大小  file_ext_name:附件 后缀名 meta_list 附件的说明信息
		String file_ext_name = FilenameUtils.getExtension(filename);
		//path:返回的附件url不是一个完整的url 即:group1/M00/00/01/*****.jpg
		String path = storageClient1.upload_file1(file_buff, file_ext_name, null);
		return path;
	}
}

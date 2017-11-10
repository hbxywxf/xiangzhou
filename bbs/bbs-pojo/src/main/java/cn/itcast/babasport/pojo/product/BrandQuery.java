package cn.itcast.babasport.pojo.product;

import java.io.Serializable;

/**
 * 
 * @ClassName: BrandQuery
 * @Company: http://www.itcast.cn/
 * @Description: 品牌信息
 * @author JD 
 * @date 2016年3月31日 上午10:56:56
 */
@SuppressWarnings("serial")
public class BrandQuery implements Serializable{

		private String name; 		// 品牌名称
		private Integer isDisplay; 	// 是否可用   0 不可用 1 可用
		
		private Integer pageNo = 1;//页码  默认为1
		private Integer pageSize = 5; // 每页显示的条数  默认为5
		private Integer startRow;
		
		public Integer getPageNo() {
			return pageNo;
		}
		public void setPageNo(Integer pageNo) {
			this.startRow = (pageNo - 1)* pageSize;
			this.pageNo = pageNo;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.startRow = (pageNo - 1)* pageSize;
			this.pageSize = pageSize;
		}
		public Integer getStartRow() {
			return startRow;
		}
		public void setStartRow(Integer startRow) {
			this.startRow = startRow;
		}
		
		
		public Integer getIsDisplay() {
			return isDisplay;
		}
		public void setIsDisplay(Integer isDisplay) {
			this.isDisplay = isDisplay;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		 
}

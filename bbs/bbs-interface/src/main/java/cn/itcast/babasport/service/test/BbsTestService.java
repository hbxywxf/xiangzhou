package cn.itcast.babasport.service.test;

import java.util.List;

import cn.itcast.babasport.pojo.test.BbsTest;

public interface BbsTestService {

	public void insertBbsTest(BbsTest bbsTest);
	
	public List<BbsTest> selectBbsTests();
}

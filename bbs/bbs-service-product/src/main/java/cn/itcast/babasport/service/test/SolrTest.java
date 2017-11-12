package cn.itcast.babasport.service.test;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SolrTest {
	
	@Resource
	private SolrServer solrServer;
	
	@Test
	public void testSolr() throws Exception {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "1");
		doc.addField("name_ik", "spring整合solr");
		solrServer.add(doc);
		solrServer.commit();
	}

}

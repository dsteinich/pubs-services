package gov.usgs.cida.pubs.springinit;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@Configuration
public class TestSpringConfig {

	@Value("classpath:testResult/getMpPub1.json")
	private Resource mpPub1;
	@Bean
	public String expectedGetMpPub1() throws IOException {
		return new String(FileCopyUtils.copyToByteArray(mpPub1.getInputStream()));
	}

	@Value("classpath:testResult/getRssPub.xml")
	private Resource rssPub;
	@Bean
	public String expectedGetRssPub() throws IOException {
		return new String(FileCopyUtils.copyToByteArray(rssPub.getInputStream()));
	}

	@Value("classpath:testResult/testOneUnNumberedSeriesPub.xml")
	private Resource testOneUnNumberedSeries;
	@Bean
	public String testOneUnNumberedSeriesPubXml() throws IOException{
		return new String(FileCopyUtils.copyToByteArray(testOneUnNumberedSeries.getInputStream()));
	}

	@Value("classpath:testResult/testOneNumberedSeriesPub.xml")
	private Resource testOneNumberedSeries;
	@Bean
	public String testOneNumberedSeriesPubXml() throws IOException{
		return new String(FileCopyUtils.copyToByteArray(testOneNumberedSeries.getInputStream()));
	}
}

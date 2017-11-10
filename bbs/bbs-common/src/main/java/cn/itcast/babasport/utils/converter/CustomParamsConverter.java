package cn.itcast.babasport.utils.converter;

import org.springframework.core.convert.converter.Converter;

public class CustomParamsConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		if(source != null && !"".equals(source)){
			source = source.trim(); // 去空格
			// 如果输入的一组空格，""
			if(!"".equals(source)){
				return source;
			}
		}
		return null;
	}


}

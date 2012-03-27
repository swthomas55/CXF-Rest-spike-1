package org.ithaka.sthomas.restCxf1.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.DigestUtils;

public class StubETagService implements ETagService {

	private Map<String, String> db = new ConcurrentHashMap<String, String>();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public String generate(String url, Object entity) {
		String tag = createTag(entity);
		db.put(url, tag);
		return tag;
	}

	private String createTag(Object entity) {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DigestUtils.md5DigestAsHex(writer.toString().getBytes());
	}

	public String get(String url) {
		String tag = db.get(url);
		if (tag == null)
			throw new InvalidTagException("No tag found for URL: " + url);
		return tag;
	}

	public void remove(String url) {
		db.remove(url);
	}

	public String update(String url, Object entity) {
		String tag = createTag(entity);
		db.put(url, tag);
		return null;
	}

}

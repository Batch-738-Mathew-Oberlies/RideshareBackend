package com.revature.rideshare.addressservice.util;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {
	private byte[] cachedBody;

	public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		InputStream reqInput = request.getInputStream();
		this.cachedBody = StreamUtils.copyToByteArray(reqInput);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CachedBodyServletInputStream(this.cachedBody);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(this.cachedBody);
		return new BufferedReader(new InputStreamReader(bais));
	}
}

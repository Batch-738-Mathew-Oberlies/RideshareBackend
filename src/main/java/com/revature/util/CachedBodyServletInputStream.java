package com.revature.util;

import lombok.SneakyThrows;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachedBodyServletInputStream extends ServletInputStream {
	private InputStream inputStream;

	public CachedBodyServletInputStream(byte[] cachedBody) {
		this.inputStream = new ByteArrayInputStream(cachedBody);
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

	@SneakyThrows
	@Override
	public boolean isFinished() {
		return inputStream.available() == 0;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		throw new UnsupportedOperationException();
	}

}

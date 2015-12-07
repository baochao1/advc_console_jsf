package com.cdel.advc.sms.handler;

/**
 * <p>
 * Title: Champion ip load balance system
 * </p>
 * <p>
 * Description: Load Balance for Stream Media Services
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: Beijing Champion Hi-Tech Co.,Ltd.
 * </p>
 * 
 * @author Jason Qin
 * @version 1.0
 */

public class ProtocolDecoder {

	byte[] buf;
	int offset;
	int bufsize;

	public ProtocolDecoder(byte[] b) {
		this(b, 0, b.length);
	}

	public ProtocolDecoder(byte[] b, int start, int end) {
		buf = b;
		offset = start;
		bufsize = end;
	}

	public boolean isEof() {
		return (bufsize - offset < 1) ? true : false;
	}

	public int getInt() throws ProtocolException {
		// int type
		int i = -1;
		if ((i = parseByte()) != 2) {
			// Debug.println("Len = "+bufsize+" Offset = "+offset);
			throw new ProtocolException("Int type error: " + i);
		}
		// int value
		return parseInt();

	}

	private int parseInt() throws ProtocolException {
		int i = 4;
		int j;
		for (j = buf[offset++]; --i > 0; j = j << 8 | buf[offset++] & 0xff) {
			;
		}
		return j;

	}

	public long getLong() throws ProtocolException {
		// int type
		int i = -1;
		if ((i = parseByte()) != 6) {
			throw new ProtocolException("Long type error: " + i);
		}
		// int value
		return parseLong();

	}

	private long parseLong() throws ProtocolException {
		int i = 8;
		long j;
		for (j = buf[offset++]; --i > 0; j = j << 8 | buf[offset++] & 0xff) {
			;
		}
		return j;

	}

	public String getString() throws ProtocolException {
		@SuppressWarnings("unused")
		int k;
		// string type
		if (parseByte() != 4) {
			throw new ProtocolException("String type error.");
		}
		// String length
		int len = parseInt();
		if (len > bufsize - offset) {
			throw new ProtocolException("Insufficient data");
		}
		// String values
		byte bytes[] = new byte[len];
		if (len > 0) {
			System.arraycopy(buf, offset, bytes, 0, len);
			offset += len;
		}

		return new String(bytes);
	}

	private int parseByte() throws ProtocolException {
		if (bufsize - offset < 1) {
			throw new ProtocolException("Insufficient data");
		} else {
			return buf[offset++] & 0xff;
		}
	}

	public static void main(String args[]) throws Exception {
		/*
		 * ProtocolEncoder encoder = new ProtocolEncoder();
		 * encoder.setLong(chinaacc
		 * .balance.util.AddressUtils.getLongAddress("192.9.201.20"));
		 * ProtocolDecoder decoder = new ProtocolDecoder(
		 * encoder.getDataBytes()); long add = decoder.getLong();
		 */
	}
}
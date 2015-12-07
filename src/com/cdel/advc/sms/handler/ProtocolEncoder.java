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

public class ProtocolEncoder {
	byte[] buf;
	int offset;
	int bufsize;

	public ProtocolEncoder() {
		this(1024);
	}

	public ProtocolEncoder(int i) {
		buf = new byte[i];
		offset = 0;
		bufsize = i;
	}

	public byte[] getBuf() {
		return buf;
	}

	protected void encodeByte(int i) {
		ensureFreeBytes(1);
		buf[offset++] = (byte) i;
	}

	private void ensureFreeBytes(int i) {
		if (bufsize - offset < i) {
			int j = bufsize * 8;
			if (j - offset < i) {
				j += i;
			}
			byte abyte0[] = new byte[j];
			System.arraycopy(buf, 0, abyte0, 0, offset);
			buf = abyte0;
			bufsize = j;
		}
	}

	public void setInt(int value) {
		// int type
		encodeByte(2);
		// int value
		encodeInt(value);
	}

	private void encodeInt(int v) {
		ensureFreeBytes(4);
		buf[offset++] = (byte) ((v >>> 24) & 0xFF);
		buf[offset++] = (byte) ((v >>> 16) & 0xFF);
		buf[offset++] = (byte) ((v >>> 8) & 0xFF);
		buf[offset++] = (byte) ((v >>> 0) & 0xFF);
	}

	public void setLong(long value) {
		// int type
		encodeByte(6);
		// int value
		encodeLong(value);
	}

	private void encodeLong(long v) {
		//
		ensureFreeBytes(8);
		//
		buf[offset++] = (byte) ((v >>> 56) & 0xFF);
		buf[offset++] = (byte) ((v >>> 48) & 0xFF);
		buf[offset++] = (byte) ((v >>> 40) & 0xFF);
		buf[offset++] = (byte) ((v >>> 32) & 0xFF);
		buf[offset++] = (byte) ((v >>> 24) & 0xFF);
		buf[offset++] = (byte) ((v >>> 16) & 0xFF);
		buf[offset++] = (byte) ((v >>> 8) & 0xFF);
		buf[offset++] = (byte) ((v >>> 0) & 0xFF);

	}

	public void setString(String values) throws Exception {
		byte[] bytes = values.getBytes("gb2312");
		int k = bytes.length;
		// String type
		encodeByte(4);
		// String length
		encodeInt(k);
		// String value
		if (k > 0) {
			ensureFreeBytes(k);
			System.arraycopy(bytes, 0, buf, offset, k);
			offset += k;
		}
	}

	public byte[] getDataBytes() {
		int i = offset;
		byte bf[] = new byte[i];
		System.arraycopy(buf, 0, bf, 0, i);
		return bf;
	}

}
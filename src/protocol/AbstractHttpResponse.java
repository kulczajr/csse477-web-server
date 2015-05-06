/*
 * IHttpResponse.java
 * Apr 24, 2015
 *
 * Simple Web Server (SWS) for EE407/507 and CS455/555
 * 
 * Copyright (C) 2011 Chandan Raj Rupakheti, Clarkson University
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either 
 * version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 * 
 * Contact Us:
 * Chandan Raj Rupakheti (rupakhcr@clarkson.edu)
 * Department of Electrical and Computer Engineering
 * Clarkson University
 * Potsdam
 * NY 13699-5722
 * http://clarkson.edu/~rupakhcr
 */
 
package protocol;

import impl.HttpDeleteResponse;
import impl.HttpGetResponse;
import impl.HttpPostResponse;
import impl.HttpPutResponse;
import impl.Protocol;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public abstract class AbstractHttpResponse {
	protected String version = "version";
	protected int status = 0;
	protected String phrase = "phrase";
	protected Map<String, String> header = new HashMap<String, String>();
	public File file = null;
	protected char[] body = new char[0];
	protected OutputStream out;

	public AbstractHttpResponse(int status, String phrase, File file, char[] body) {
		this.version = Protocol.VERSION;
		this.status = status;
		this.phrase = phrase;
		this.header = new HashMap<String, String>();
		this.file = file;
		this.body = body;
	}
	
	public static AbstractHttpResponse getResponse(String responseType, int status, String phrase, File file, char[] body) {
		if (responseType.equalsIgnoreCase(Protocol.GET)) {
			return new HttpGetResponse(status, phrase, file, body);
		} else if (responseType.equalsIgnoreCase(Protocol.POST)) {
			return new HttpPostResponse(status, phrase, file, body);
		} else if (responseType.equalsIgnoreCase(Protocol.PUT)) {
			return new HttpPutResponse(status, phrase, file, body);
		} else if (responseType.equalsIgnoreCase(Protocol.DELETE)) {
			return new HttpDeleteResponse(status, phrase, file, body);
		} else {
			return new HttpGetResponse(status, phrase, file, body);
		}
		
	}
	
	/**
	 * Gets the version of the HTTP.
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Gets the status code of the response object.
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Gets the status phrase of the response object.
	 * 
	 * @return the phrase
	 */
	public String getPhrase() {
		return phrase;
	}
	
	/**
	 * The file to be sent.
	 * 
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Returns the header fields associated with the response object.
	 * @return the header
	 */
	public Map<String, String> getHeader() {
		// Lets return the unmodifable view of the header map
		return Collections.unmodifiableMap(header);
	}

	/**
	 * Maps a key to value in the header map.
	 * @param key A key, e.g. "Host"
	 * @param value A value, e.g. "www.rose-hulman.edu"
	 */
	public void put(String key, String value) {
		header.put(key, value);
	}
	
	public abstract void write(OutputStream outStream) throws Exception;

	/**
	 * @param outStream
	 */
	public void setWriter(OutputStream outStream) {
		out = outStream;
	}
	
	public OutputStream getWriter() {
		return out;
	}
	
	public void closeWriter() throws IOException {
		out.flush();
	}

	/**
	 * @return
	 */
	public char[] getBody() {
		return body;
	}
}

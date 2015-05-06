/*
 * MyGetServlet.java
 * May 4, 2015
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
 
package servlets;

import impl.HttpRequest;
import impl.Protocol;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import protocol.AbstractHttpResponse;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public abstract class MyGetServlet {
	public static void doGet(HttpRequest request, AbstractHttpResponse response) throws IOException {
		// We are reading a file
				if(response.getStatus() == Protocol.OK_CODE) {
					
					File pluginFile = new File("temp");
		            FileOutputStream is = new FileOutputStream(pluginFile);
		            OutputStreamWriter osw = new OutputStreamWriter(is);    
		            Writer w = new BufferedWriter(osw);
		            w.write("This is the new plugin page!");
		            w.close();
					
					// Process text documents
					FileInputStream fileInStream = new FileInputStream(pluginFile);
					BufferedInputStream inStream = new BufferedInputStream(fileInStream, Protocol.CHUNK_LENGTH);
					
					byte[] buffer = new byte[Protocol.CHUNK_LENGTH];
					int bytesRead = 0;
					// While there is some bytes to read from file, read each chunk and send to the socket out stream
					OutputStream out = response.getWriter();
					while((bytesRead = inStream.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					
					// Close the file input stream, we are done reading
					inStream.close();
				}
	}
}

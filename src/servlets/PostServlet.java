/*
 * PostServlet.java
 * May 3, 2015
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import protocol.AbstractHttpResponse;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class PostServlet {
	public static void doPost(HttpRequest request, AbstractHttpResponse response) throws IOException {
		// Write a blank line
				response.getWriter().write(Protocol.CRLF.getBytes());
				
				String s = new String(response.getBody());
				int i = s.indexOf("filename");
				String f = s.substring(i + 10, s.indexOf('"', i + 10));
				File file = new File(f);
				// Write to the file
				if(response.getStatus() == Protocol.OK_CODE) {
		            FileWriter f2;
		            
		            //Append is false, so it overwrites
		            f2 = new FileWriter(file, false);
		            f2.write(response.getBody());
		            f2.close();
				}
				file.createNewFile();
	}
	
}

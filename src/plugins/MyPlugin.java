/*
 * MyPlugin.java
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
 
package plugins;

import impl.HttpRequest;
import impl.Protocol;

import java.io.IOException;

import protocol.AbstractHttpResponse;
import protocol.IPlugin;
import servlets.DeleteServlet;
import servlets.GetServlet;
import servlets.PostServlet;
import servlets.PutServlet;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class MyPlugin implements IPlugin {

	/* (non-Javadoc)
	 * @see protocol.IPlugin#doGet(impl.HttpRequest, protocol.AbstractHttpResponse)
	 */
	@Override
	public void doGet(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		GetServlet.doGet(request, response);
	}

	/* (non-Javadoc)
	 * @see protocol.IPlugin#doPost(impl.HttpRequest, protocol.AbstractHttpResponse)
	 */
	@Override
	public void doPost(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		PostServlet.doPost(request, response);
	}

	/* (non-Javadoc)
	 * @see protocol.IPlugin#doPut(impl.HttpRequest, protocol.AbstractHttpResponse)
	 */
	@Override
	public void doPut(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		PutServlet.doPut(request, response);
	}

	/* (non-Javadoc)
	 * @see protocol.IPlugin#doDelete(impl.HttpRequest, protocol.AbstractHttpResponse)
	 */
	@Override
	public void doDelete(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		DeleteServlet.doDelete(request, response);
	}

	/* (non-Javadoc)
	 * @see protocol.IPlugin#doResponse(java.lang.String)
	 */
	@Override
	public void doResponse(HttpRequest request, AbstractHttpResponse response, String resource) throws IOException {
		if (resource.equalsIgnoreCase("GetServlet")) {
			doGet(request, response);
		} else if (resource.equalsIgnoreCase("PostServlet")) {
			doPost(request, response);
		} else if (resource.equalsIgnoreCase("PutServlet")) {
			doPut(request, response);
		} else if (resource.equalsIgnoreCase("DeleteServlet")) {
			doDelete(request, response);
		} else {
			if (request.getMethod().equalsIgnoreCase(Protocol.GET)) {
				doGet(request, response);
			}else if (request.getMethod().equalsIgnoreCase(Protocol.POST)) {
				doPost(request, response);
			} else if (request.getMethod().equalsIgnoreCase(Protocol.PUT)) {
				doPut(request, response);
			} else if (request.getMethod().equalsIgnoreCase(Protocol.DELETE)) {
				doDelete(request, response);
			} else {
				doGet(request, response);
			}
		}
	}
	
}

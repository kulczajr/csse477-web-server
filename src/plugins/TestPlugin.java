package plugins;
import impl.HttpRequest;

import java.io.IOException;

import protocol.AbstractHttpResponse;
import protocol.IPlugin;
import servlets.MyGetServlet;


public class TestPlugin implements IPlugin {

	@Override
	public void doGet(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		MyGetServlet.doGet(request, response);
	}

	@Override
	public void doPost(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPut(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(HttpRequest request, AbstractHttpResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doResponse(HttpRequest request, AbstractHttpResponse response,
			String resource) throws IOException {
		if (resource.equalsIgnoreCase("MyGetServlet")) {
			doGet(request, response);
		}
	}


}

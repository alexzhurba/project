package client_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
* Echo the body of an HTTP request back as the HTTP response. This is merely
* a simple exercise of the Secret Sun Web Server. As configured, the URL to
* access it is http://localhost:8000/echo.
* 
* @author Andrew Cowie
*/
public final class NewServer
{
public static void main(Al[] args) throws IOException {
    final InetSocketAddress addr;
    final HttpServer server;

    addr = new InetSocketAddress(8000);

    server = HttpServer.create(addr, 10);
    server.createContext("/echo", new EchoHandler());
    server.start();
}
}

class EchoHandler implements HttpHandler
{
public void handle(HttpExchange t) throws IOException {
    final InputStream is;
    final OutputStream os;
    StringBuilder buf;
    int b;
    String request;
	final String response;

    buf = new StringBuilder();

    /*
     * Get the request body and decode it. Regardless of what you are
     * actually doing, it is apparently considered correct form to consume
     * all the bytes from the InputStream. If you don't, closing the
     * OutputStream will cause that to occur
     */

    is = t.getRequestBody();

    while ((b = is.read()) != -1) {
        buf.append((char) b);
    }

    is.close();

    if (buf.length() > 0) {
        request = URLDecoder.decode(buf.toString(), "UTF-8");
    } else {
        request = null;
    }

    /*
     * Construct our response:
     */

    buf = new StringBuilder();
    //buf.append("<html><head><title>HTTP echo server</title></head><body>");
    //buf.append("<p><pre>");
    //buf.append(t.getRequestMethod() + " " + t.getRequestURI() + " " + t.getProtocol() + "\n");

    /*
     * Process the request headers. This is a bit involved due to the
     * complexity arising from the fact that headers can be repeated.
     */

    Headers headers = t.getRequestHeaders();
    t.getResponseHeaders().add("Access-Control-Allow-Methods","GET");
    t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    for (String name : headers.keySet()) {
        List<String> values = headers.get(name);

        for (String value : values) {
            //buf.append(name + ": " + value + "\n");
        }
    }

    /*
     * If there was an actual body to the request, add it:
     */

    if (request != null) {
        //buf.append("\n");
        buf.append(request);
    }

    //buf.append("</pre></p>");
    //buf.append("</body></html>\n");

    response = buf.toString();
    System.out.println(response);

    /*
     * And now send the response. We could have instead done this
     * dynamically, using 0 as the response size (forcing chunked
     * encoding) and writing the bytes of the response directly to the
     * OutputStream, but building the String first allows us to know the
     * exact length so we can send a response with a known size. Better :)
     */

    t.sendResponseHeaders(200, response.length());

    os = t.getResponseBody();

    os.write(response.getBytes());

    /*
     * And we're done!
     */

    os.close();
    t.close();
}
}

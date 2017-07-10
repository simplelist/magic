package redkale;

import org.redkale.convert.json.JsonConvert;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.HttpServlet;
import org.redkale.net.http.WebServlet;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/4.
 */
@WebServlet({"/hello/*"})
public class HelloWorldServlet extends HttpServlet {

    @Resource
    private HelloWorld service;
    public void execute(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        httpResponse.finishJson(JsonConvert.root().convertTo(service));
    }
}

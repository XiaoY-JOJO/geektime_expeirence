package server;

import com.hero.herocat.DefaultHeroServlet;
import com.hero.herocat.HttpHeroRequest;
import com.hero.herocat.HttpHeroResponse;
import com.hero.servlet.HeroRequest;
import com.hero.servlet.HeroResponse;
import com.hero.servlet.HeroServlet;

import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

public class CustomHandler extends Thread{
    private  Socket socket;
    private Map<String, HeroServlet> servletMap;

    public CustomHandler(Socket socket, Map<String, HttpServlet> servletMap) {
        this.socket = socket;
        this.servletMap = servletMap;
    }

    @Override
    public void run() {
        try{
            InputStream inputStream = socket.getInputStream();
            //封装Resuest对象和Response对象
            HeroRequest request = new HttpHeroRequest(inputStream);
            HeroResponse response = new HttpHeroResponse(socket.getOutputStream());
            String url = request.getUrl();
            //静态资源处理
            if (servletMap.get(url)==null){
                response.outputHtml(request.getUrl());
            }else{
                //动态资源处理
                DefaultHeroServlet httpServlet = servletMap.get(url);
                httpServlet.service(request,response);
            }
            socket.close();
        }catch (Exception e){

        }
    }
}

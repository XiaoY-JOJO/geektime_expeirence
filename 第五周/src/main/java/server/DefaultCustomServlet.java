package server;


import com.hero.servlet.HeroRequest;
import com.hero.servlet.HeroResponse;
import com.hero.servlet.HeroServlet;

public abstract class DefaultCustomServlet extends HeroServlet {

    public abstract void doGet(HeroRequest request, HeroResponse response);
    public abstract void doPost(HeroRequest request,HeroResponse response);


    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void service(HeroRequest request, HeroResponse response) throws Exception {
        if ("GET".equals(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }
    }
}

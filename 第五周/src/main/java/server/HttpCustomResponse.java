package server;

import com.hero.servlet.HeroResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 封装Response对象，需要依赖于OutputStream
 *
 */
public class HttpCustomResponse implements HeroResponse {
    private OutputStream outputStream;

    public HttpCustomResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public HttpCustomResponse() {
    }

    /**
     * @param path 指的就是 Request中的url ，随后要根据url来获取到静态资源的绝对路径，进一步根据绝对路径读取该静态资源文件，最终通过输出流输出
     */
    public void outputHtml(String path) throws IOException {
        //获取静态资源的绝对路径
        String absoluteResourcePath = StaticResourceUtil.getAbsolutePath(path);

        //输出静态资源文件
        File file = new File(absoluteResourcePath);
        if (file.exists()&& file.isFile()){
            //读取静态资源文件，输出静态资源
            StaticResourceUtil.outputStaticResource(new FileInputStream(file),outputStream);
        }else{
            //输出404
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }


    public void write(String context) throws IOException {
        outputStream.write(context.getBytes());
    }


}

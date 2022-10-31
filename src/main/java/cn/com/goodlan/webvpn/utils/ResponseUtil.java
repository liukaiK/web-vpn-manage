package cn.com.goodlan.webvpn.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class ResponseUtil {

    public static void write(HttpServletResponse response, String content, String contentType) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write(content);
        writer.close();
    }

}

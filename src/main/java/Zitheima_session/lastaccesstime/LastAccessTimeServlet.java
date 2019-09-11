package Zitheima_session.lastaccesstime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet("/LastAccessTimeServlet")
public class LastAccessTimeServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得当前时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        System.out.println (localTime );
		String currentTime = URLEncoder.encode (localTime,"UTF-8");
		
		//1、创建Cookie 记录当前的最新的访问时间
		Cookie cookie = new Cookie("lastAccessTime",currentTime);
		cookie.setMaxAge(60*10*500);
		response.addCookie(cookie);
		
		//2、获得客户端携带cookie ---- lastAccessTime
		String lastAccessTime = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie coo : cookies){
				if("lastAccessTime".equals(coo.getName())){
					lastAccessTime = coo.getValue();
				}
			}
		}
		
		response.setContentType("text/html;charset=UTF-8");
		if(lastAccessTime==null){
			response.getWriter().write("您是第一次访问");
		}else{
			response.getWriter().write("您上次的访问的时间是："+ URLDecoder.decode (lastAccessTime,"UTF-8"));
		}
		
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
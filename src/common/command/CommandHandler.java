package common.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {//컨트롤러에 사용되는 인터페이스
	public String process(HttpServletRequest req, HttpServletResponse res)
	throws Exception;
}
package standardgame.server;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author Steven Weston
 */
public class DebugFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;

		//Get the IP address of client machine.
		String ipAddress = request.getRemoteAddr();

		//Log the IP address and current timestamp.
		System.out.println(
				"IP: " + ipAddress + ", "
						+ "Time: " + new Date().toString() + ", "
						+ "URI: \"" + request.getRequestURI() + "\"");

		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}

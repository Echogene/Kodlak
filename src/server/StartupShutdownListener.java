package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Steven Weston
 */
public class StartupShutdownListener extends HttpServlet {
	public void init() {
		System.out.println("I just listened to the startup.");
	}

	public void destroy() {
		System.out.println("I just listened to the shutdown.");
	}

	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response)
			throws ServletException, IOException {

	}
}

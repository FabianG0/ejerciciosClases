package howManyBurgers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Servlet implementation class Hmb
 */
public class Hmb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Random rand;
	public int maximo;
	public int comidas;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hmb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		maximo = Integer.parseInt(config.getInitParameter("max"));
		this.rand = new Random();
		this.comidas = this.rand.nextInt(maximo);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter salida;
		String cadena;
		String respuesta;
		int guess;
		guess = Integer.parseInt(request.getParameter("numero"));
		if (this.comidas == guess) {
			cadena = "<h2>G A N A S T E </h2>";
		} else if (this.comidas < guess) {
			cadena = "<p>No inventes, no como tanto!!</p>";
		} else {
			cadena = "<p>subestimas el poder de las cangreburgers!</p>";
		}
		salida = response.getWriter();
		response.setContentType("text/html");
		respuesta = "<!DOCTYPE html> \r\n" + "<html lang='en'> \r\n" + "<head>\r\n" + "<title>hmb - Resultados</title>\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "<h1>Y los resultados son ...</h1>";
		salida.println(respuesta);
		respuesta = "<img src=\"images/bob.png\" alt=\"bob\">\r\n"
				+ cadena +"\r\n"
				+ "</body>\r\n"
				+ "</html>";
		salida.println(respuesta);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

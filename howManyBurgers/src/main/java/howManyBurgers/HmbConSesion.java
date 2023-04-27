package howManyBurgers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Servlet implementation class HbConSesion
 */
public class HmbConSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Random rand;
	public int maximo;
	public int intentosMaximos;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmbConSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.maximo = Integer.parseInt(config.getInitParameter("max"));
		this.intentosMaximos = Integer.parseInt(config.getInitParameter("intentosMaximos"));
		this.rand = new Random();
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
		int comidas = -1;
		HttpSession sesion;
		Integer guess;

		// Recuperar sesion
		sesion = request.getSession();
		guess = (Integer) sesion.getAttribute("objetivo");

		String respuesta;
		// Primera vez
		if (guess == null) {
			guess = this.rand.nextInt(this.maximo) + 1;
			sesion.setAttribute("objetivo", guess);
		}
		// Leemos los parametros
		comidas = Integer.parseInt(request.getParameter("numero"));

		// Procesa respuesta
		if (comidas == guess) {
			cadena = "<h2>G A N A S T E </h2>";
			guess = this.rand.nextInt(maximo) + 1;
			sesion.setAttribute("objetivo", guess);
		} else if (guess < comidas) {
			cadena = "<p>No inventes, no como tanto!!</p>";
		} else {
			cadena = "<p>subestimas el poder de las cangreburgers!</p>";
		}
		salida = response.getWriter();
		response.setContentType("text/html");
		respuesta = "<!DOCTYPE html> \r\n" + "<html lang='en'> \r\n" + "<head>\r\n"
				+ "<title>hmb - Resultados</title>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<h1>Y los resultados son ...</h1>" + "<img src=\"images/bob.png\" alt=\"bob\">\r\n" + cadena + "\r\n"
				+ "</body>\r\n" + "</html>";
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

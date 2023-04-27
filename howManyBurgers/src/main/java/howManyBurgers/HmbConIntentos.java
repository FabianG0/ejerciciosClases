package howManyBurgers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Servlet implementation class  Fabian Garcia Moreno
 */
public class HmbConIntentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Random rand;
	public int maximo;
	public int intentosMaximos;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HmbConIntentos() {
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
		Integer intentos;
		// Recuperar sesion
		sesion = request.getSession();
		guess = (Integer) sesion.getAttribute("objetivo");
		intentos = (Integer) sesion.getAttribute("numeroIntento");
		String respuesta;
		// Primera vez
		if (guess == null) {
			guess = generateNewNumber();
			sesion.setAttribute("objetivo", guess);
		}
		if (intentos == null) {
			intentos = 1;
			sesion.setAttribute("numeroIntento", intentos);
		}
		// Leemos los parametros
		comidas = Integer.parseInt(request.getParameter("numero"));
		// verificar el numero de intentos
		if (intentos <= this.intentosMaximos) {
			// Procesa respuesta
			if (comidas == guess) {
				cadena = "<h2>G A N A S T E </h2>";
				guess = generateNewNumber();
				sesion.setAttribute("objetivo", guess);
				intentos = 0;
			} else if (guess < comidas) {
				cadena = "<p>No inventes, no como tanto!!</p>";
			} else {
				cadena = "<p>subestimas el poder de las cangreburgers!</p>";
			}
			respuesta = "<!DOCTYPE html> \r\n" + "<html lang='en'> \r\n" + "<head>\r\n"
					+ "<title>hmb - Resultados</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "<h1>Numero de intentos: "
					+ intentos.toString() + "</h1>\r\n" + "<h1>Y los resultados son ...</h1>"
					+ "<img src=\"images/bob.png\" alt=\"bob\">\r\n" + cadena + "<p>Resultado para probar " + guess.toString() + "</p>\r\n"
					+ "</body>\r\n" + "</html>";
		} else {
			intentos = 0;
			respuesta = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
					+ "	<title>Numero de intentos superado</title>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "	<h1 style=\"color: red;\">El numero de intentos se ha superado, se generara un nuevo numero.</h1>\r\n"
					+ "</body>\r\n" + "</html>";
			guess = generateNewNumber();
			sesion.setAttribute("objetivo", guess);
		}
		intentos++;
		sesion.setAttribute("numeroIntento", intentos);
		salida = response.getWriter();
		response.setContentType("text/html");
		salida.println(respuesta);
	}

	/**
	 * Generate new number random
	 */
	int generateNewNumber() {
		return this.rand.nextInt(maximo) + 1;
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

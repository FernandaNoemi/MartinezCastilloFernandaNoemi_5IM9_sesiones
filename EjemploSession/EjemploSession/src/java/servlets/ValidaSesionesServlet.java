/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidaSesionesServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html");
      HttpSession sesion = request.getSession();
      String titulo = null;
      ResultSet resultado=null;
      Statement sentencia=null;

      //Pedimos el atributo, y verificamos si existe
      String claveSesion = (String) sesion.getAttribute("claveSesion");
      try{
          String user= request.getParameter("user");
          String pass= request.getParameter("password");
      }
      catch(Exception e){
          System.out.println("no c");
      }
      
      resultado= sentencia.executeQuery("select * from usuarios where user = '"+user+"' and password= '"+pass+"'");
      int contador=0;
      while(resultado.next()){
          contador++;
      }
      
        if (contador==1) {
            sesion.setAttribute("user", user);
            response.sendRedirect("catalogo.jsp");
        }
        else{
            try (PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servet Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div>Pos no ue</div>");
                out.println("</body>");
                out.println("</html>");
            }
            
            sesion=request.getSession(false);
        }
    }catch (Exception e) {
                    System.out.println("error");
            }
     /* if(claveSesion.equals("emmanueloropeza")){
        titulo = "llave correcta continua la sesion";
      }
      else
      {
        titulo = "llave incorrecta inicie nuevamente sesion";
      }


      //Mostramos los  valores en el cliente
      PrintWriter out = response.getWriter();
      out.println("¿Continua la Sesion y es valida?: " + titulo);
      out.println("<br>");
      out.println("ID de la sesi&oacute;n JSESSIONID: " + sesion.getId());
  */
    }

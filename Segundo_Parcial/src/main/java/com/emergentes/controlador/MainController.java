package com.emergentes.controlador;
import com.emergentes.dao.AvisoDAO;
import com.emergentes.dao.AvisoDAO1;
import com.emergentes.modelo.Seminario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            AvisoDAO dao = new AvisoDAO1();
            
            int id;
            
            Seminario avi = new Seminario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action"): "view";
            switch (action){
                case "add":
                    
                    request.setAttribute("seminario", avi);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "edit":
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    avi = dao.getById(id);
                    request.setAttribute("aviso", avi);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "delete":
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    
                    response.sendRedirect("Controller");
                    break;
                default:
                    
                    List<Seminario> lista = dao.getAll();
                    request.setAttribute("seminario", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
            }
           
        
        }catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        AvisoDAO dao = new AvisoDAO1();
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String exposicion = request.getParameter("exposicion");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        int cupo = Integer.parseInt(request.getParameter("cupo"));
        
        Seminario avi = new Seminario();
        
        avi.setId(id);
        avi.setTitulo(titulo);
        avi.setExpositor(exposicion);
        avi.setFecha(fecha);
        avi.setHora(hora);
        avi.setCupo(cupo);
        
        if(id == 0){
            try{
                dao.insert(avi);
                response.sendRedirect("Controller");
            }catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }
        else{
            try{
                    dao.update(avi);
                    response.sendRedirect("Controller");
                }catch (Exception e){
                    System.out.println("Error " + e.getMessage());
                }
        }
            
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Factory.StrategyFactory;
import Strategy.PaymentStrategy;
import entidades.Category;
import entidades.Film;
import entidades.Inventory;
import entidades.Payment;
import entidades.Rental;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloControl.AccesoDatos;
import modeloControl.ConexionSingleton;

/**
 *
 * @author LABO08
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private AccesoDatos conexion;
    Connection con = ConexionSingleton.getConexion("sakila", "localhost", "root", "");
    List<Film> listaFilms;
    List<Inventory> listaInventory;
    String categoria;
    String metodoPago;
    Film film = null;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            if (con == null) {
                System.out.println("nullos");
            }
            conexion = new AccesoDatos(con);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String instruccion = request.getParameter("Instruccion");
        System.out.println(instruccion + "instruccion");
        if (instruccion == null) {
            ListarCategory(request, response);
        } else {
            switch (instruccion) {
                case "peliculas":
                    listarPeliculas(request, response);
                    break;
                case "pagar":
                    pagarPelicula(request, response);
                    break;
                case "inventario":
                       inventarioPay(request,response);break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void ListarCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String categoria = request.getParameter("Categoria");
//            System.out.println(categoria);
            List<Category> lista = conexion.getCategorias();
            request.setAttribute("lista", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPeliculas(HttpServletRequest request, HttpServletResponse response) {
        categoria = request.getParameter("Categoria");
        int id_store = 1;
        System.out.println(categoria);
        Category category = new Category(Integer.parseInt(categoria));
        try {
//            String categoria = request.getParameter("Categoria");
//            System.out.println(categoria);
            listaFilms = conexion.getFilmsByCategory(category, id_store);
            request.setAttribute("lista", listaFilms);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPelis.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pagarPelicula(HttpServletRequest request, HttpServletResponse response) {
        //String []pelis=request.getParameterValues("check");
        String pelis = request.getParameter("check");
        System.out.println(pelis);
        float paga = 0;
        for (Film f : listaFilms) {

            if (f.getId() == Integer.parseInt(pelis)) {
                paga += f.getPrecio();
                film = f;
                System.out.println(film.getId() + "aca");
            }
        }
        try {
            listaInventory = conexion.getInventoryByFilm(film, 1);
            System.out.println(listaInventory);
            request.setAttribute("lista", listaInventory);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarInventario.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println(paga+metodoPago+getHora());
        
    }

    private Timestamp getHora() {
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        Timestamp date = new Timestamp(javaTime);
        return date;
    }

    private void inventarioPay(HttpServletRequest request, HttpServletResponse response) {
         String inventario = request.getParameter("check");
         System.out.println(inventario);
          metodoPago = request.getParameter("pagar");
          Inventory inventory = new Inventory(film,Integer.parseInt(inventario));
          System.out.println(conexion.getRental()+1);
          int rental_id=conexion.getRental()+1;
          Rental rental = new Rental(rental_id,getHora(),inventory,getHora(),getHora());
          rental.setId(rental_id);
          Payment pay=new Payment(0,getHora(),getHora(),rental);
          
          StrategyFactory strategyF = new StrategyFactory(pay);
          PaymentStrategy payment= strategyF.getStrategy(metodoPago);
          payment.pay(film.getPrecio());
         try {
            request.setAttribute("film",film);
            request.setAttribute("metodoPago",metodoPago);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Confirmacion.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

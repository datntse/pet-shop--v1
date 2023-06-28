/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.datnt.datbook.core.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import org.datnt.datbook.core.cart.Cart;
import org.datnt.datbook.core.food.FoodDAO;
import org.datnt.datbook.core.food.FoodDTO;
import org.datnt.datbook.core.item.Item;

/**
 *
 * @author datnt
 */
@WebServlet(name = "FoodListServlet", urlPatterns = {"/FoodListServlet"})
public class FoodListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            FoodDAO dao = new FoodDAO();
            List<FoodDTO> listFood = dao.getAll();
            List<FoodDTO> dogFood = dao.getDogFood();
            List<FoodDTO> catFood = dao.getCatFood();
            Cart cart = new Cart();
            List<Item> listItem = cart.getItems();
            int size;
            if (listItem != null) {
                size = listItem.size();
            } else {
                size = 0;
            }
            HttpSession session = request.getSession();
            session.setAttribute("dogFoodList", dogFood);
            session.setAttribute("catFoodList", catFood);

            session.setAttribute("size", size);
            session.setAttribute("dataList", listFood);
            response.sendRedirect("foodlist.jsp");
        } catch (SQLException e) {
            log("FoodlistServlet _ SQLExceiption _" + e.getMessage());
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

}

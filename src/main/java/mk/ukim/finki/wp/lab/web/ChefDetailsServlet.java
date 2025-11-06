package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(SpringTemplateEngine templateEngine, ChefService chefService, DishService dishService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        Long chefId = Long.parseLong(request.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());
        context.setVariable("chefBio", chef.getBio());
        context.setVariable("dishes", chef.getDishes());

        templateEngine.process("chefDetails.html", context, response.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long chefId = Long.parseLong(request.getParameter("chefId"));
        String dishId = request.getParameter("dishId");

        Chef chef = chefService.addDishToChef(chefId, dishId);

        response.sendRedirect("/chefDetails?chefId=" + chefId);
    }

}
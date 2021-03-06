package com.brianfair.javagroupproject.controllers;

import java.lang.reflect.Array;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brianfair.javagroupproject.models.Order;
import com.brianfair.javagroupproject.models.Prices;
import com.brianfair.javagroupproject.models.StringArrayFunctions;
import com.brianfair.javagroupproject.models.User;
import com.brianfair.javagroupproject.services.OrderService;
import com.brianfair.javagroupproject.services.UserService;





@Controller
public class OrderController
{

	@Autowired
    private UserService userService;
	@Autowired
	private OrderService orderService;

	
	
  @RequestMapping("/order/details/{id}")
  public String orderDetails(@PathVariable("id") Long order_id,
  						Model model, 
  						HttpSession session)
  {
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
	  	model.addAttribute("user", usr);
	  	
	  	Order this_order = this.orderService.findOrderById(order_id);
	  	model.addAttribute("order", this_order);

	  	boolean isTakingOut = false;
	  	if (this_order.getMethod().equals("Delivery")) isTakingOut = true;
	  	model.addAttribute("isTakingOut", isTakingOut);

	  	return "orderdetails.jsp";
 }

  
    
    @RequestMapping("/make/order")
    public String makeOrder(@ModelAttribute("order") Order order, 
    			HttpSession session,
    			Model model) 
    {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
    	model.addAttribute("user", usr);
        return "makeorder.jsp";
    }
    @RequestMapping(value="/making/order", method=RequestMethod.POST)
    public String makingOrder(@Valid @ModelAttribute("order") Order order,
    							BindingResult result,
    							HttpSession session,
    							Model model)
    {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
        if (result.hasErrors())
        {
            return "makeorder.jsp";
        }
        else
        {
        	if ( order.getToppings() != null )
        	{
            	if (StringArrayFunctions.containsEmptyStr(order.getToppings()))
            	{
            		order.setToppings(null);
            	}
        	}

    		String price = Prices.calculatePrice(order.getToppings(), order.getSize(), order.getQuantity());
        	order.setPrice(price);
        	System.out.println("str_toppings: "+order.getToppings());
        	System.out.println(price);
        	Order this_order = orderService.save(order);
        	return "redirect:/order/details/"+this_order.getId();
        }
    }
    
    
    //Like order 
    @RequestMapping("/likeorder/{orderId}")
    public String likeorder(HttpSession session, Model model, @PathVariable("orderId") Long orderId) {
    	if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
    	Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		Order order = orderService.findOrderById(orderId);
    	orderService.like(usr, order);
    
    	return "redirect:/order/history";
    }
    
    //Unlike Order
    @RequestMapping("/unlikeorder/{orderId}")
    public String unlikeorder(HttpSession session, Model model, @PathVariable("orderId")Long orderId) {
    	Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		Order order = orderService.findOrderById(orderId);
		orderService.unlike(usr, order);
		return "redirect:/order/history";
    }

    
    @RequestMapping("/edit/order/{id}")
    public String editOrder(@PathVariable("id") Long order_id, 
    			HttpSession session,
    			Model model) 
    {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
    	model.addAttribute("user", usr);
    	

		Order this_order = this.orderService.findOrderById(order_id);
		model.addAttribute("order", this_order);
        return "editorder.jsp";
    }
    @RequestMapping(value="/editing/order/{id}", method=RequestMethod.POST)
    public String editingOrder(@Valid @ModelAttribute("order") Order order,
    							BindingResult result,
    							@PathVariable("id") Long order_id,
    							HttpSession session,
    							Model model)
    {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		Order this_order = this.orderService.findOrderById(order_id);

//    	model.addAttribute("user", usr);
        if (result.hasErrors()) {
        	System.out.println(result);
            return "editorder.jsp";
        }
        else
        {
        	Order edited_order = orderService.save(Prices.finalizeOrderEdit(order, this_order));
        	return "redirect:/order/details/"+edited_order.getId();
        }
    }
    
   

	//Order History Page 
	@RequestMapping("/order/history")
	public String orderHistory(@ModelAttribute("order") Order order,
						HttpSession session,
						Model model)
	{
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		model.addAttribute("user", usr);
		
		List<Order> orders = usr.getOrders();
		model.addAttribute("orders", orders);
		
		return "orderhistory.jsp";
	}
    @RequestMapping(value="/reordering", method=RequestMethod.POST)
    public String reodering(@ModelAttribute("order") Order order,
    							HttpSession session,
    							Model model)
    {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
    	String price = Prices.calculatePrice(order.getToppings(), order.getSize(), order.getQuantity());
    	order.setPrice(price);
    	System.out.println("str_toppings: "+order.getToppings());
    	System.out.println(price);
    	Order this_order = orderService.save(order);
    	return "redirect:/order/details/"+this_order.getId();
    }

    
 	@RequestMapping("/delete/order/{id}")
  	public String deleteIdea(@PathVariable("id") Long order_id, HttpSession session)
  	{
  		if (session.getAttribute("user_id") == null)
  		{
  			return "redirect:/";
  		}
  		orderService.delete(order_id);
  		return "redirect:/order/history";
  	}
	
	
}
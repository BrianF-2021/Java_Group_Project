package com.brianfair.javagroupproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brianfair.javagroupproject.models.Prices;
import com.brianfair.javagroupproject.models.StringArrayFunctions;
import com.brianfair.javagroupproject.models.User;
import com.brianfair.javagroupproject.services.UserService;
import com.brianfair.javagroupproject.validators.UserValidation;





@Controller
public class UsersController
{
	
	@Autowired
    private UserService userService;
	@Autowired
	private UserValidation validation;



	@RequestMapping("/")
	public String registerForm(@ModelAttribute("user") User user) {
		System.out.println(user.getDateFormatted());
	    return "main.jsp";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpSession session)
	{
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		long user_id = (Long) session.getAttribute("user_id");
		User this_user = userService.findUserById(user_id);
		model.addAttribute("user", this_user);

		
		return "home.jsp";
	}
	
	@RequestMapping(value="/registering", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,
								BindingResult result, HttpSession session)
	{
		validation.validate(user, result);
		if(result.hasErrors())
		{
			return "main.jsp";
		}
		
		
		User newUser = this.userService.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/home";
	}
	
	
    @RequestMapping(value="/loggingIn", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email,
    					@RequestParam("password") String password,
    					Model model,
    					HttpSession session,
    					RedirectAttributes redirectAttr) {
    	if(!this.userService.authenticateUser(email, password))
    	{
    		redirectAttr.addFlashAttribute("loginError", "Invalid Credentials!");
    		return "redirect:/";
    		
    	}
    	User this_user = this.userService.findByEmail(email);
  	  	session.setAttribute("user_id", this_user.getId());
  	  	return "redirect:/home";
    }

    
	@RequestMapping(value="/edit/user")
	public String editComment(Model model, 
	  						HttpSession session)
	{
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		model.addAttribute("user", usr);
		return "edituser.jsp";
	}
	@RequestMapping(value="/editing/user/{id}", method=RequestMethod.POST)
	public String editingComment(@Valid @ModelAttribute("user") User user,
	  							BindingResult result,
	  							@PathVariable("id") Long userId,
	  							HttpSession session,
	  							Model model)
	{
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
		Long user_id = (Long)session.getAttribute("user_id");
		User usr = this.userService.findUserById(user_id);
		System.out.println("usr email: "+usr.getEmail()+" user email: "+user.getEmail());
		

		if ((usr.getEmail().equals(user.getEmail())))
		{
			validation.editValidate(false, user, result);
		}
		else
		{
			validation.editValidate(true, user, result);
		}
		
        if (result.hasErrors())
        {
            return "edituser.jsp";
        }
        else
        {
//        	System.out.println("usr pw: "+usr.getPassword());
//        	System.out.println("user pw: "+user.getPassword());
//        	System.out.println("user pwc: "+user.getPasswordConfirmation());
//        	System.out.println("BCrypt: "+BCrypt.checkpw(user.getPassword(), usr.getPassword()));
    		if ( (usr.getPassword().equals(user.getPassword())) && 
    				(user.getPassword().equals(user.getPasswordConfirmation())) )
			{
    			this.userService.save(user);
        		return "redirect:/home";
    		}
			this.userService.registerUser(user);
    		return "redirect:/home";
        }
	}
	  

  @RequestMapping("/logout")
  public String logout(HttpSession session)
  {
		if (session.getAttribute("user_id") == null)
		{
			return "redirect:/";
		}
	  	session.invalidate();
	  	return "redirect:/";
  }

	
	
}

//@RequestMapping("/register")
//public String registerForm(@ModelAttribute("user") User user) {
//    return "registration.jsp";
//}
//
//@RequestMapping(value="/registering", method=RequestMethod.POST)
//public String registerUser(@Valid @ModelAttribute("user") User user,
//							BindingResult result, HttpSession session) {
//validation.validate(user, result);
//if(result.hasErrors())
//{
//	return "registration.jsp";
//}
//User newUser = this.userService.registerUser(user);
//session.setAttribute("user_id", newUser.getId());
//return "redirect:/home";
//}
//
//
//@RequestMapping("/login")
//public String login() {
//    return "login.jsp";
//}
//@RequestMapping(value="/loggingIn", method=RequestMethod.POST)
//public String loginUser(@RequestParam("email") String email,
//					@RequestParam("password") String password,
//					Model model,
//					HttpSession session,
//					RedirectAttributes redirectAttr) {
//	if(!this.userService.authenticateUser(email, password))
//	{
//		redirectAttr.addFlashAttribute("loginError", "Invalid Credentials!");
//		return "redirect:/login";
//		
//	}
//	User this_user = this.userService.findByEmail(email);
//	  	session.setAttribute("user_id", this_user.getId());
//	  	return "redirect:/home";
//}
//
//
//	
//@RequestMapping("/home")
//public String home(Model model, HttpSession session)
//{
//	if (session.getAttribute("user_id") == null)
//	{
//		return "redirect:/";
//	}
//	long user_id = (Long) session.getAttribute("user_id");
//	User this_user = userService.findUserById(user_id);
//	List<Show> shows = showService.getAllShows();
//	List<Double> avgs = new ArrayList<>();
//	for (Show show : shows)
//	{
//		int show_size = show.getRatings().size();
//		int sum = 0;
//		for (Rate rate : show.getRatings())
////		for (int i=0; i<show.getRatings().size(); i++)
//		{
//			sum += rate.getRating();
//			System.out.println(sum);
//		}
//		if (show_size > 0)
//		{
//			Double avg = ((double) sum/ (double) show_size);
//			System.out.println(avg);
//			avgs.add(avg);
//		}
//
//	}
//	
////	List<Idea> ideasLikersOrderAsc = ideaService.getAllLikersSizeAsc();
////	model.addAttribute("ideas", ideasLikersOrderAsc);
//	model.addAttribute("avgs", avgs);
//	model.addAttribute("user", this_user);
//	model.addAttribute("shows", shows);
//	return "home.jsp";
//}
//

//	
//    
//    @RequestMapping("/create/show")
//    public String createShow(@ModelAttribute("show") Show show, 
//    			HttpSession session,
//    			Model model) 
//    {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
//    	model.addAttribute("user", usr);
////		model.addAttribute("categories", this.categoryService.getAllCategories());
//        return "createshow.jsp";
//    }
//    @RequestMapping(value="/creating/show", method=RequestMethod.POST)
//    public String creatingShow(@Valid @ModelAttribute("idea") Show show,
//    							BindingResult result,
//    							HttpSession session)
//    {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//        if (result.hasErrors()) {
//            return "createshow.jsp";
//        } else{
//        	showService.save(show);
//            return "redirect:/home";
//        }
//    }
//    
//    
//    @GetMapping("/edit/show/{id}")
//    public String editShow(@PathVariable("id") Long show_id, 
//  		  				HttpSession session,
//  		  				Model model) 
//    {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
//		model.addAttribute("user", usr);
//		model.addAttribute("showEdit", this.showService.findShowById(show_id));
//        return "editshow.jsp";
//    }
//    @PostMapping("/editing/show/{id}")
//    public String updateShow(@Valid @ModelAttribute("showEdit") Show show,
//  		  					BindingResult result,
//  							Model model,
//  							@PathVariable("id") Long show_id,
//  							HttpSession session)
//    {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//        if (result.hasErrors()) {
////      	  model.addAttribute("ideaEdit", ideaService.findIdeaById(idea_id));
//            return "editshow.jsp";
//        } else{
//   //   	  System.out.println(idea.getContent());
//      	  this.showService.update(show);
//      	  return "redirect:/home";
//        }
    
    
  //################################################################################################3
  //######################################## - Exam Code Below - ############################################33	
//  @GetMapping("/show/details/{id}")
//  public String IdeaDetails(@ModelAttribute("rating") Rate rate, 
//		  				@PathVariable("id") Long show_id,
//  						Model model, 
//  						HttpSession session)
//  {
//	if (session.getAttribute("user_id") == null)
//	{
//		return "redirect:/";
//	}
//	Long user_id = (Long)session.getAttribute("user_id");
//	
//	boolean haveRated = false;
//	session.setAttribute("haveRated", haveRated);
//	Rate thisUsersRating = null;
//	long thisUsersRatingId = 0;
//	List<Rate> thisShowRatings = showService.findShowById(show_id).getRatings();
//	for(Rate rating : thisShowRatings)
//	{
//		if (rating.getUser().getId() == user_id)
//		{
//			haveRated = true;
//			session.setAttribute("haveRated", haveRated);
//			thisUsersRating = rating;
//			thisUsersRatingId = thisUsersRating.getId();
//			session.setAttribute("ratingId", thisUsersRatingId);		
//		} 
////		else { haveRated = false;}
//	}
////	//	model.addAttribute("haveRated", haveRated); 
//	model.addAttribute("usersRating", thisUsersRating);
//	User usr = this.userService.findUserById(user_id);
//  	Show show = this.showService.findShowById(show_id);
//  	model.addAttribute("show", show);
//  	model.addAttribute("user", usr);
//  	return "showdetails.jsp";
// 	}
    
//    
//    @PostMapping("/rate/{id}")
//    public String rateShow(@Valid @ModelAttribute("rating") Rate rate,
//  		  					BindingResult result,
//  		  					@PathVariable("id") Long show_id,
//  							Model model,
//  							HttpSession session)
//    {
//  		if (session.getAttribute("user_id") == null)
//  		{
//  			return "redirect:/";
//  		}
//    		Long user_id = (Long)session.getAttribute("user_id");
//    		User usr = this.userService.findUserById(user_id);
//    	  	Show show = this.showService.findShowById(show_id);
//    	  	model.addAttribute("show", show);
//    	  	model.addAttribute("user", usr);
//        if (result.hasErrors()) {
////      	  model.addAttribute("ideaEdit", ideaService.findIdeaById(idea_id));
//            return "showdetails.jsp";
//        }
////        } else{
////      	  
////  			boolean haveRated = false;
////  			Rate usersRating = null;
////  			List<Rate> thisShowRatings = showService.findShowById(show_id).getRatings();
////  			for(Rate rating : thisShowRatings)
////  			{
////  				if (rating.getUser().getId() == user_id)
////  				{
////  					haveRated = true;
////  					usersRating = rating;
//////  					usersRating.setRating(rate.getRating());
//////  					this.rateService.createRating(usersRating);
//////  					this.rateService.updateRating(usr, show, rate.getRating());
////  					
////  				} 
////  				else 
////  				{ 
////  					haveRated = false;
////  			    	this.rateService.createRating(rate);
////  				}
////  				
////  			}
//  	 //   	  System.out.println(idea.getContent());
////  	    	  System.out.println(rate.getRating());
//  				this.rateService.createRating(rate);
//  				Long showId = rate.getShow().getId();
//  			
//  	    	  return "redirect:/show/details/"+showId;
//        }
//    
//    
    
//  @PostMapping("/rate/update/{id}")
//  public String updatShowRate(@Valid @ModelAttribute("rating") Rate rate,
//		  					BindingResult result,
//		  					@PathVariable("id") Long rating_id,
//							Model model,
//							HttpSession session)
//  {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//		boolean haveRated = false;
//		Rate usersRating = null;
//		Long user_id = (Long)session.getAttribute("user_id");
//		List<Rate> thisShowRatings = showService.findShowById(rating_id).getRatings();
//		for(Rate rating : thisShowRatings)
//		{
//			if (rating.getUser().getId() == user_id)
//			{
//				haveRated = true;
//				usersRating = rating;
//			} 
//			else { haveRated = false;}
//		}
//      if (result.hasErrors()) {
////    	  model.addAttribute("ideaEdit", ideaService.findIdeaById(idea_id));
//	  		User usr = this.userService.findUserById(user_id);
//	  		Rate rating = this.rateService.findRateById(rating_id);
//	  	  	Show show = this.showService.findShowById(rating.getShow().getId());
//	  	  	model.addAttribute("show", show);
//	  	  	model.addAttribute("user", usr);
//          return "showdetails.jsp";
//      } else{
// //   	  System.out.println(idea.getContent());
//    	  System.out.println(rate.getRating());
//    	  this.rateService.createRating(rate);
//    	  Long showId = rate.getShow().getId();
//    	  return "redirect:/show/details/"+showId;
//      }
//  }
   
  
//################################################################################################3
//##############################################################################################33
//  @GetMapping("/show/details/{id}")
//  public String IdeaDetails(@PathVariable("id") Long show_id,
//  						Model model, 
//  						HttpSession session)
//  {
//	if (session.getAttribute("user_id") == null)
//	{
//		return "redirect:/";
//	}
//	Long user_id = (Long)session.getAttribute("user_id");
//	
//	boolean haveRated = false;
//	if (session.getAttribute("haveRated") == null)
//	{
//		session.setAttribute("haveRated", false);
//	}
//
//	Rate thisUsersRating = null;
//	long thisUsersRatingId = 0;
//  	Show show = this.showService.findShowById(show_id);
//	List<Rate> thisShowRatings = showService.findShowById(show_id).getRatings();
//	model.addAttribute("usersRating", thisUsersRating);
//	User usr = this.userService.findUserById(user_id);
//  	model.addAttribute("show", show);
//  	model.addAttribute("user", usr);
//	for(Rate rating : thisShowRatings)
//	{
//		if (rating.getUser().getId() == user_id)
//		{
//			haveRated = true;
//			session.setAttribute("haveRated", haveRated);
//			thisUsersRating = rating;
//			thisUsersRatingId = thisUsersRating.getId();
//			session.setAttribute("ratingId", thisUsersRatingId);
//			return "redirect:/details/update/rate/"+show_id;
//		}
//	}
//  	return "redirect:/details/rate/"+show_id;
//  }
//  
//  
//  @GetMapping("/details/update/rate/{id}")
//  public String detailsUpdateRating(@PathVariable("id") Long show_id,
//  						Model model, 
//  						HttpSession session)
//  {
//	if (session.getAttribute("user_id") == null)
//	{
//		return "redirect:/";
//	}
//	Long user_id = (Long)session.getAttribute("user_id");
//	Long thisUserRatingId = (Long)session.getAttribute("ratingId");
//	boolean haveRated = (boolean)session.getAttribute("haveRated");
//	Show show = this.showService.findShowById(show_id);
//	User usr = this.userService.findUserById(user_id);
//	model.addAttribute("usersRating", this.rateService.findRateById(thisUserRatingId));
//  	model.addAttribute("show", show);
//  	model.addAttribute("user", usr);
//  	return "showdetails.jsp";
//	
//  }
//  
//  
//  
//  @GetMapping("/details/rate/{id}")
//  public String detailsRating(@RequestParam 
//						@PathVariable("id") Long show_id,
//  						Model model, 
//  						HttpSession session)
//  {
//	if (session.getAttribute("user_id") == null)
//	{
//		return "redirect:/";
//	}
//	Long user_id = (Long)session.getAttribute("user_id");
//	Long thisUserRatingId = (Long)session.getAttribute("ratingId");
//	boolean haveRated = (boolean)session.getAttribute("haveRated");
//	Show show = this.showService.findShowById(show_id);
//	User usr = this.userService.findUserById(user_id);
//	model.addAttribute("usersRating", this.rateService.findRateById(thisUserRatingId));
//  	model.addAttribute("show", show);
//  	model.addAttribute("user", usr);
//  	return "showdetails.jsp";
//	
//  }
//  
//
////################################################################################################3
////##############################################################################################33
//
//  @PostMapping("/rate/{id}")
//  public String rateShow(@RequestParam 
//		  					@PathVariable("id") Long show_id,
//							Model model,
//							HttpSession session)
//  {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//  		Long user_id = (Long)session.getAttribute("user_id");
//  		User usr = this.userService.findUserById(user_id);
//  	  	Show show = this.showService.findShowById(show_id);
//  	  	model.addAttribute("show", show);
//  	  	model.addAttribute("user", usr);
////   	  System.out.println(idea.getContent());
////	    	  System.out.println(rate.getRating());
//				this.rateService.createRating(rate);
//				Long showId = rate.getShow().getId();
//			
//	    	  return "redirect:/show/details/"+showId;
//      }
// 
//  
//  
//  
//	@GetMapping("/delete/show/{id}")
//	public String deleteIdea(@PathVariable("id") Long show_id, HttpSession session)
//	{
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//		showService.delete(show_id);
//		return "redirect:/home";
//	}
//	
//    @RequestMapping("/logout")
//    public String logout(HttpSession session)
//    {
//		if (session.getAttribute("user_id") == null)
//		{
//			return "redirect:/";
//		}
//    	session.invalidate();
//    	return "redirect:/";
//    }
//  
//	
//}
//	
//	
////DONT FORGET TO CHECK TO MAKE SURE USER IS LOGGED IN!!!!!!!!!!!!!!!!!!!!!!!!!
//    
//    @GetMapping("/idea/details/{idea_id}")
//    public String IdeaDetails(@PathVariable("idea_id") Long idea_id,
//    						Model model, 
//    						HttpSession session)
//    {
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
//    	Show this_idea = this.ideaService.findIdeaById(idea_id);
//    	model.addAttribute("idea", this_idea);
//    	model.addAttribute("user", usr);
//    	return "ideadetails.jsp";
//   	}
//    
//    
//    @RequestMapping("/create/idea")
//    public String createIdea(@ModelAttribute("idea") Show idea, 
//    			HttpSession session,
//    			Model model) 
//    {
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
//    	model.addAttribute("user", usr);
////		model.addAttribute("categories", this.categoryService.getAllCategories());
//        return "createidea.jsp";
//    }
//    @RequestMapping(value="/creating/idea", method=RequestMethod.POST)
//    public String creatingIdea(@Valid @ModelAttribute("idea") Show idea,
//    							BindingResult result)
//    {
//        if (result.hasErrors()) {
//            return "createidea.jsp";
//        } else{
//        	ideaService.save(idea);
//            return "redirect:/home";
//        }
//    }
//    
//    //TRY #5
//  @GetMapping("/edit/idea/{id}")
//  public String editIdea(@PathVariable("id") Long idea_id, 
//		  				HttpSession session,
//		  				Model model) 
//  {
//	  Long user_id = (Long)session.getAttribute("user_id");
//	  User usr = this.userService.findUserById(user_id);
//	  model.addAttribute("user", usr);
//  	  model.addAttribute("ideaEdit", this.ideaService.findIdeaById(idea_id));
//      return "editidea.jsp";
//  }
//  @PostMapping("/editing/idea/{id}")
//  public String updateIdea(@Valid @ModelAttribute("ideaEdit") Show idea,
//		  					BindingResult result,
//							Model model,
//							@PathVariable("id") Long idea_id)
//  {
//      if (result.hasErrors()) {
////    	  model.addAttribute("ideaEdit", ideaService.findIdeaById(idea_id));
//          return "editidea.jsp";
//      } else{
// //   	  System.out.println(idea.getContent());
//    	  this.ideaService.update(idea);
//    	  return "redirect:/home";
//      }
//  }
//  
//  	@GetMapping("/delete/idea/{idea_id}")
//  	public String deleteIdea(@PathVariable("idea_id") Long idea_id)
//  	{
//  		ideaService.deleteIdea(idea_id);
//  		return "redirect:/home";
//  	}
//  	
//    @RequestMapping("/logout")
//    public String logout(HttpSession session)
//    {
//    	session.invalidate();
//    	return "redirect:/";
//    }
//    TRY #1
//    @RequestMapping("/edit/idea/{idea_id}")
//    public String editIdea(HttpSession session,
//    			Model model,
//    			@PathVariable("idea_id") Long idea_id) 
//    {
// //   	session.setAttribute("idea_id", idea_id);
//		Long user_id = (Long)session.getAttribute("user_id");
//		Idea this_idea = ideaService.findIdeaById(idea_id);
//		User usr = this.userService.findUserById(user_id);
//    	model.addAttribute("user", usr);
//    	model.addAttribute("this_idea", this_idea);
//      return "editidea.jsp";
//    }
//    @RequestMapping("/editing/idea/{idea_id}")
//    public String updateIdea(@Valid @ModelAttribute("this_idea") Idea idea,
//    							@PathVariable("idea_id") Long idea_id,
//    							BindingResult result)
//    {
//        if (result.hasErrors()) {
//            return "editidea.jsp";
//        } else{
//        	System.out.println(idea_id);
//        	ideaService.update(idea);
//          return "redirect:/home";
//        }
//    }
//    
    
    
//    TRY #2
//    @GetMapping("/edit/idea/{idea_id}")
//    public String editIdea(@PathVariable("idea_id") Long idea_id,
//    					@ModelAttribute("idea") Idea idea,
//    					Model model) 
//    {
// //   	session.setAttribute("idea_id", idea_id);
////		Long user_id = (Long)session.getAttribute("user_id");
////		User usr = this.userService.findUserById(user_id);
////    	model.addAttribute("user", usr);
//    	model.addAttribute("idea", ideaService.findIdeaById(idea_id));
////		model.addAttribute("categories", this.categoryService.getAllCategories());
//        return "editidea.jsp";
//    }
//    @PostMapping("/edit/idea/{idea_id}")
//    public String updateIdea(@Valid @ModelAttribute("idea") Idea idea, 
//    							BindingResult result,
//    							@PathVariable("idea_id") Long idea_id,
//    							Model model
//    							)
//    {
//        if (result.hasErrors()) {
//        	model.addAttribute("idea", ideaService.findIdeaById(idea_id));
//            return "editidea.jsp";
//        } else{
//        	System.out.println(idea_id);
//        	ideaService.update(idea);
//            return "redirect:/home";
//        }
//    }
    
////  TRY #3
//  @RequestMapping("/edit/idea/{idea_id}")
//  public String editIdea(@PathVariable("idea_id") Long idea_id, Model model, HttpSession session) 
//  {
////   	session.setAttribute("idea_id", idea_id);
//		Long user_id = (Long)session.getAttribute("user_id");
//		User usr = this.userService.findUserById(user_id);
//  	model.addAttribute("user", usr);
//  	model.addAttribute("idea", ideaService.findIdeaById(idea_id));
////		model.addAttribute("categories", this.categoryService.getAllCategories());
//      return "editidea.jsp";
//  }
//  @RequestMapping(value="/editing/idea/{idea_id}", method=RequestMethod.PUT)
//  public String updateIdea(@Valid @ModelAttribute("idea") Idea idea,
//		  					BindingResult result)
//  					
//  {
//      if (result.hasErrors()) {
//          return "editidea.jsp";
//      } else{
//      	ideaService.update(idea);
//          return "redirect:/home";
//      }
//  }

    
//    //TRY #4
//  @GetMapping("/edit/idea/{idea_id}")
//  public String editIdea(@PathVariable("idea_id") Long idea_id, Model model) 
//  {
//  	  model.addAttribute("ideaEdit", this.ideaService.findIdeaById(idea_id));
//      return "editidea.jsp";
//  }
//  @PostMapping("/editing/idea/{idea_id}")
//  public String updateIdea(@Valid @ModelAttribute("ideaEdit") Idea idea,
//		  					BindingResult result, 
//							@PathVariable("idea_id") Long idea_id,
//							Model model)
//  {
//      if (result.hasErrors()) {
//          return "editidea.jsp";
//      } else{
//    	  System.out.println(idea.getContent());
//    	  this.ideaService.update(idea);
//    	  return "redirect:/home";
//      }
//  }
  
//    @RequestMapping("/logout")
//    public String logout(HttpSession session) {
//    	session.invalidate();
//    	return "redirect:/";
//    }




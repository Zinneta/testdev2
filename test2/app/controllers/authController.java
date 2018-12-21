package controllers;

import play.*;
import play.mvc.*;
import views.html.auth.*;
import play.mvc.Security.Authenticated;
import models.*;
import play.data.Form;
import static play.data.Form.form;
import play.data.*;
import java.util.*;


public class authController extends Controller {
	// ログインフォーム
	public static class UserLogin {
	    public String name;
	    public String password;
		public String email;
	}
	public static class OfficeLogin {
	    public String name;
	    public String password;
		public String email;
	}
	
//リザルト 
	public static Result login() {
		return ok(login.render("ログインしてください。", form(UserLogin.class)));
	}

	public static Result officelogin() {
        return ok(officelogin.render("ログインしてください。",form(OfficeLogin.class)));
    }
/*	public String validate() {
	    if (User.authenticate(name,email, password) == null) {
	      return "Invalid user or password";
	    }
	    return null;
	}
	*/
	public static Result auth() {
	    Form<UserLogin> loginForm = form(UserLogin.class).bindFromRequest();
	    String password = loginForm.get().password;
	    String mail = loginForm.get().email;
	    List<Muser> result = null;
	    if (loginForm.hasErrors()) {
	        return badRequest(login.render("ログインしてください。", form(UserLogin.class)));
	    } else {
		     result = Muser.find.where().eq("password",password).eq("mail",mail).orderBy("id").findList();
		     if(result.size() == 0){
		        return badRequest(login.render("ログインできませんでした", form(UserLogin.class)));
		    }else{
		    	session().clear();
				session("email", loginForm.get().email);
		        return redirect(routes.UserController.index());
		    }
	    }
	}
	public static Result officeAuth() {
	    Form<OfficeLogin> loginForm = form(OfficeLogin.class).bindFromRequest();
	    String password = loginForm.get().password;
	    String mail = loginForm.get().email;
	    List<OfficeModel> result = null;
	    if (loginForm.hasErrors()) {
	        return badRequest(login.render("ログインしてください。", form(UserLogin.class)));
	    } else {
		     result = OfficeModel.find.where().eq("password",password).eq("mail",mail).orderBy("id").findList();
		     if(result.size() == 0){
		        return badRequest(login.render("ログインできませんでした", form(UserLogin.class)));
		    }else{
		    	session().clear();
				session("email", loginForm.get().email);
		        return redirect(routes.OfficeController.index());
		    }
	    }
	}
	public static Result logout() {
		session().clear();
		return redirect(routes.authController.login());
	}
}

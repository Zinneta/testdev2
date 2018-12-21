package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.user.*;

import java.util.*;
import models.*;

import play.data.validation.Constraints.Required;



public class UserController extends Controller {
	//orderForm
	public static class WordForm {
    	@Required(message= "注文するワード数")
    	public String wordnum;
       	@Required    
       	public Integer id;
       	@Required    
       	public Integer ofiiceid;
       	@Required    
       	public String name;

	}
    public static class IdForm {
    	@Required
    	public Long id;
    }

//リザルト
    @Security.Authenticated(Secured.class)
	public static Result index() {
		List<Artist> datas = Artist.find.all();
		return ok(userindex.render("操作の完了",datas));
	}
	// ユーザ情報登録用フォーム
	public static Result userAdd(){
		Form<Muser> f = new Form(Muser.class);
		return ok(useradd.render("ユーザ情報を登録してください",f));
	}
	// ゆーざ登録
	public static Result userCreate(){
		Form<Muser> f = new Form(Muser.class).bindFromRequest();
		if (!f.hasErrors()){
			Muser data = f.get();
			data.save();
			return redirect("/result");
		} else {
			return badRequest(useradd.render("ERROR", f));
		}
	}
	public static Result test(int id){
		return ok(test.render("test:" + id));
	}
	// 発注
/*	public static Result testuserOreder(){
		Form<WordForm> f = new Form(WordForm.class);
		if (!f.hasErrors()){
			return ok(testuserorder.render("注文確認",f));
		} else {
			return badRequest(useradd.render("ERROR", f));
		}
	}
	public static Result userOreder(){
		Form<WordForm> f = new Form(WordForm.class).bindFromRequest();
	    List<OfficeModel> result = null;

		if (f.hasErrors()){
			return badRequest(useradd.render("ERROR", f));
		} else {
        	res = Artist.find.where().eq("name",name).eq("officeid",officeid).eq("id").findList();
		}
	}
*/

}

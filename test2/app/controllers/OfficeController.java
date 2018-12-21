package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

import java.util.*;
import models.*;

import play.data.validation.Constraints.Required;



public class OfficeController extends Controller {
    public static class FindForm {
    	@Required
        public String name;
       	@Required    
    	public Integer officeid;
    }
    public static class IdForm {
    	@Required
    	public Long id;
    }

//リザルト
	public static Result index() {
		List<OfficeModel> datas = OfficeModel.find.all();
		return ok(officeindex.render("操作の完了",datas));
	}
	// 事務所情報登録用フォーム
	public static Result officeAdd(){
		Form<OfficeModel> f = new Form(OfficeModel.class);
		return ok(officeadd.render("事務所の情報を登録してください",f));
	}
	// 事務所情報登録
	public static Result officeCreate(){
		Form<OfficeModel> f = new Form(OfficeModel.class).bindFromRequest();
		if (!f.hasErrors()){
			OfficeModel data = f.get();
			data.save();
			return redirect("/result");

		} else {
			return badRequest(officeadd.render("ERROR", f));
		}
	}
/*	
	// アーティスト情報更新
	public static Result artistModify(){
		Form<Artist> f = new Form(Artist.class);
		return ok(artistmodify.render("アーティストの情報を登録してください",f));
	}
	// アーティスト情報更新
	public static Result artistedit(){
		Form<IdForm> f = new Form(IdForm.class).bindFromRequest();
System.out.println("here" +f);
		if (!f.hasErrors()){
			Long id = f.get().id;
		Artist	datas = Artist.find.byId(id);
			if(datas != null){
				Form<Artist> r = new Form(Artist.class).bindFromRequest();
				return ok(artistupdate.render( "変更する情報を編集してください",r));
			}else{
				return ok(artisttestdup.render("ERROR: IDが見つかりません。",f ));
			}
		}else{
			return ok(artisttestdup.render("ERROR: 入力に問題があります。",f ));
		}
	}
	// アーティスト情報更新
	public static Result artistupdate(){
		Form<Artist> f = new Form(Artist.class).bindFromRequest();
		if (!f.hasErrors()){
			Artist data = f.get();
			data.update();
			return redirect("/result");
		}else{
			return ok(artistupdate.render("ERROR: 再度入力してください",f ));
		}
	}

    public static Result artistSerch() {
		Form<FindForm> f = form(FindForm.class).bindFromRequest();
        List<Artist> datas = null;
        System.out.println("here1:" +f);

        if (!f.hasErrors()){
        	String name = f.get().name;
        	Integer officeid = f.get().officeid;
        	String[] arr = name.split(",");
        	datas = Artist.find.where().eq("name",name).eq("officeid",officeid).orderBy("id").findList();
        	Integer count = datas.size();
   System.out.println("here:" +datas.getClass());
			if(count != 0){
				return ok(artistserchresult.render("検索結果", datas));
			}else{
				return ok(artistserchresult.render("データなし", datas));
			}
        }else{
        return ok(artistserch.render("検索", f, datas));
        }
    }
    
    //削除
    public static Result artistDelete() {
		Form<IdForm> f = form(IdForm.class).bindFromRequest();
//        List<Artist> datas = null;
        System.out.println("here1:" +f);

        if (!f.hasErrors()){
        	Long id = f.get().id;
        Artist	datas = Artist.find.byId(id);
        			//.where().eq("id",id).findList();
System.out.println("here:" +datas);

        	if(datas != null){
        		datas.delete();
        		return redirect("/result");
        	}else{
    			return ok(artisttestdeli.render("ERROR: IDが見つかりません。",f ));
        	}
        }else{
			return ok(artisttestdeli.render("ERROR: 再度入力してください",f ));
        }
    }
    public static Result artisttestDelete() {
		Form<IdForm> f = new Form(IdForm.class).bindFromRequest();;
		return ok(artisttestdeli.render("Idを記入してください",f));
    }

    public static Result artisttestUpdate() {
		Form<IdForm> f = new Form(IdForm.class).bindFromRequest();
		return ok(artisttestdup.render("Idを記入してください",f));
    }

 /*   public static Result artistSerchResult(datas) {
		Form<Artist> f = new Form(Artist.class).bindFromRequest();

    	return ok(artistserchresult.render("検索結果", f, datas));
    }  
*/
}

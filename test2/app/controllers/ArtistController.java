package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.artist.*;

import java.util.*;
import models.*;

import play.data.validation.Constraints.*;



public class ArtistController extends Controller {
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
    public static class NumForm {
       	@Required
    	public Long id;
    	@Required
    	public Integer num;
       	@Required
       	public boolean rateFlg;

    }

	// Form用の内部クラス
	public static class ArtistForm {
		public String name;
//		public Integer sex;
//		public Integer age;
//		public String officename;

	}

//リザルト
	public static Result index() {
		List<Artist> datas = Artist.find.all();
		return ok(artindex.render("操作の完了",datas));
	}
	// アーティスト情報登録用フォーム
	public static Result artistAdd(){
		Form<Artist> f = new Form(Artist.class);
		return ok(artistadd.render("アーティストの情報を登録してください",f));
	}
	// アーティスト情報登録
	public static Result artistCreate(){
		Form<Artist> f = new Form(Artist.class).bindFromRequest();
		if (!f.hasErrors()){
			Artist data = f.get();
			data.save();
			return redirect("/result");
		} else {
			return badRequest(artistadd.render("ERROR", f));
		}
	}
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
    //金額計算
    public static Result artisttestCalcPrice() {
		Form<NumForm> f = new Form(NumForm.class).bindFromRequest();
		return ok(artisttestprice.render("Idを記入してください",f));
    }
    public static Result artistCalcPrice() {
		Form<NumForm> f = form(NumForm.class).bindFromRequest();
		System.out.println("here:" +f);

		if (!f.hasErrors()){
        	Long id = f.get().id;
        	Integer num = f.get().num;
        	boolean isRate = f.get().rateFlg;
			Integer calc = 0;

        	System.out.println("here:" +f);

        	Artist result = Artist.find.byId(id);

        	System.out.println("here:" +result);

        	if(result != null){
        		//割合なし
        		if(isRate == false){
					Integer wordprice = result.wordprice;
					Integer calc = wordprice * num;
        		//割合
        		}else{
					Double	Rate	= result.rate;
					Integer wordprice = result.wordprice;
					Double wordpricerate = wordprice * Rate;
					double tem = Math.floor(wordpricerate * num);
					Integer calc = (int)tem;
        		}
        		//キャンペーン
        		return ok(artistprice.render("合計金額", calc));

        	}else{
      		   System.out.println("here:+error" );

                return ok(artisttestprice.render("ERROR: データが見つかりません。",f));
            }
        }else{
        	return ok(artisttestprice.render("記入してください",f));
        }
    }
    //詳細
    public static Result artistdetail(int id) {
		Form<NumForm> f = new Form(NumForm.class).bindFromRequest();
		List<Artist> datas = Artist.find.where().eq("id",id).findList();
		if(datas.size() ==0){
	        return redirect(routes.UserController.index());
		} else {
			return ok(artistdetail.render(datas,f));
		}
    }


 /*   public static Result artistorder() {
		//値をフォームhiddenなら
		Form<order> f = new Form(order.class).bindFromRequest();
		//値をフォームhiddenなら
		Form<orderLog> l = new Form(orderLog.class).bindFromRequest();
		//
		//haserror
		if(f.hasError || l.hasError){
			return
		}
		if(!f.hasError){
			//	Long userid = f.get().userid;
			//	Long officeid = f.get().userid;
			//	Integer orderflg = 1;
			    Order datas = f.get();
			    datas.save();
		}
		if(!l.hasError){
				orderLog logs = l.get();
				logs.save();
		}
		//キャンペーン割引
		List<camp> datas = Camp.find().where().eq('start_date', reqdata)findLst();
		if(!datas.size() == 0){
			//キャンペーン計算
			 calc * datas.rate/100;
			 return　ok
		}
    	return ok(artistorder.render("申請完了", f, datas));
    }
*/
}

package models;
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import com.avaje.ebean.annotation.*;
import play.data.validation.*;

@Entity

public class OfficeModel extends Model {
	  @Id 
	  public Long id;

	  @Column(name = "name")
	  @Required(message = "必須項目です。")
	  public String name;

	  @Column(name = "password")
	  @Required(message = "必須項目です")
	  @MinLength(8)
	  @MaxLength(100)
//	  @validtewith(value = PasswordCheck.class)
	  public String password;

	  @Column(name = "mail")
	  @Required(message = "必須項目です。")
	  @Email (message = "正しいメールアドレスを入力してください")
	  public String mail;
	  
	  @Column(name = "url")
//	  @validtewith(value = CheckUrl.class, message = "URLで始まる文字列を入れてください")
	  public String url;

	  @Column(name = "created")
	  @CreatedTimestamp
	  public Date created;

	  @Column(name = "modify")
	  @Version
	  public Date modify;
 
  public static Finder<Long,OfficeModel> find = new Finder(Long.class, OfficeModel.class);

/*  @Override
	public String toString(){
		return ("[id:" + id + ", name:" + name + ",password: " + password +
				",mail:" + mail + ",url:" + url +",created:" + created + "," + "modify:" + modify + "]");

  }
/*	public static class CheckUrl extends Validator<String> {
		public boolean isValid(String s){
			return s.toLowerCase().startsWith("http://");

			@Override
			public F.Tuple<String, Object[]> getErrorMessageKey(){
				return	new F.Tuple<String, Object[]>("error.invaild",new String[]{});
			}
		}
	}
	 static class PasswordCheck extends Validator<String> {
        public boolean isValid(Object validatedObject, Object value) {
            OfficeModel office = (OfficeModel)validatedObject;
            if(office.password.length() < 6){
                setMessage("パスワードが短すぎます。");
                return false;
            }
            return true;
        }
    }

 /*		
		public static OfficeModel findByName(String input) {
			return OfficeModel.find.where()
					.eq("name", input ).findList().get(0);
		}
*/
}

/*アノテーション一覧
@min(10(最小値))  @max(100(100))
@minLength(10(最小文字数))		@maxLength(10(最大文字数))
*/
package models;
import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import com.avaje.ebean.annotation.*;


@Entity

public class Artist extends Model {
	  @Id 
	  public Long id;

	  @Column(name = "name")
	  @Required(message = "必須項目です。")
	  public String name;

	  @Column(name = "sex")
	  @Required(message = "必須項目です。1.男性,2.女性")
	  @Min(1)
	  @Max(2)
	  public Integer sex;

	  @Column(name = "age")
	  @Required(message = "必須項目です。")
	  @Min(1)
	  @Max(100)
	  public Integer age;
	  
	  @Column(name = "officeid")
	  @Required(message = "必須項目です。")
	  public Integer officeid;

	  @Column(name = "officename")
	  @Required(message = "必須項目です。")
	  public String officename;

	  @Column(name = "typeid")
	  @Required(message = "必須項目です。")  
	  public Integer typeid;

	  @Column(name = "wordprice")
	  @Required(message = "必須項目です。")  
	  public Integer wordprice;
	
	  @Column(name = "rate")
	  public double rate;

	  @Column(name = "created")
	  @CreatedTimestamp
	  public Date created;

	  @Column(name = "modify")
	  @Version
	  public Date modify;
 
  public static Finder<Long,Artist> find = new Finder(Long.class, Artist.class);

  @Override
	public String toString(){
		return ("[id:" + id + ", name:" + name + ",sex: " + sex +
				",age:" + age + ",officeid:" + officeid + ",officename:" + officename + 
				",typeid:" + typeid + ",wordprice:" + wordprice + ",rate:" + rate +
				",created:" + created + "," + "modify:" + modify + "]");

  }

 		
		public static Message findByName(String input) {
			return Message.find.where()
					.eq("name", input ).findList().get(0);
		}

}

/*アノテーション一覧
@min(10(最小値))  @max(100(100))
@minLength(10(最小文字数))		@maxLength(10(最大文字数))
*/
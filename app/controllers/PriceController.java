package controllers;

import java.util.List;

import models.Price;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import forms.PriceForm;

import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.ebean.Model;
import views.html.price.*;


public class PriceController extends Controller {

	final static Form<PriceForm> priceForm =  form (PriceForm.class);
	
	/**
	 * Return List of all Prices in JSON format.
	 * 
	 * @return
	 */
	
	public static Result all() {
		List<Price> prices = Price.all();
		return ok(Json.toJson(prices));
	}

	/**
	 * Return Price by id.
	 * @return 
	 * 
	 * @return
	 */
	
	public static Result index(){
		return ok(index.render());
	}
	
	public static Result create_new(){
		return ok(create.render(priceForm));
	}
	
	public static Result create_save(){
		Form<PriceForm> filledForm = priceForm.bindFromRequest();
			
		if(filledForm.hasErrors()){
			return badRequest(views.html.price.create.render(filledForm));
		}
		
		PriceForm priceForm=filledForm.get();
		if(priceForm==null){
			return badRequest(views.html.price.create.render(filledForm));
		}
		
		Price price=new Price();
		price.amount=priceForm.amount;
		price.save();
		
		return  index();//ok(views.html.welcome.render());		
	}
	
	public Result delete_record(){
		return ok();
	}
	
	public static Result get(Long id) {
		if (id == null) {
			return badRequest("Expecting Price Id");
		}

		Price price = Price.get(id);

		if (price == null) {
			return notFound("Price with id [" + id + "] does not exists.");
		}
		JsonNode result = Json.toJson(price);
		return ok(result);
	}

	/**
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create() {
		JsonNode jsonNode = request().body().asJson();
		if (jsonNode == null) {
			return badRequest("Expecting Json data");
		}
		Price price = Json.fromJson(jsonNode, Price.class);
		if (price == null) {
			return badRequest("Expecting Price Json data");
		}
		price.save();
		ObjectNode result = Json.newObject();
		result.put("id", price.id);
		return created(result);
	}

	/**
	 * @param id
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result update(Long id) {
		if (id == null) {
			return badRequest("Expecting Price Id");
		}

		JsonNode jsonNode = request().body().asJson();
		if (jsonNode == null) {
			return badRequest("Expecting Json data");
		}
		Price price = Json.fromJson(jsonNode, Price.class);
		if (price == null) {
			return badRequest("Expecting Price Json data");
		}
		price.id = id;
		price.update();
		return ok();
	}
	
}

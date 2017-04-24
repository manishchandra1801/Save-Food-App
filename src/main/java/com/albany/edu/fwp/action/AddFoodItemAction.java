/**
 * Copyright CodeJava.net To Present
 * All rights reserved.
 */
package com.albany.edu.fwp.action;
 
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.albany.edu.fwp.dao.ConfigDAO;
import com.albany.edu.fwp.dao.FoodDateDAO;
import com.albany.edu.fwp.dao.FeedBackDAO;
import com.albany.edu.fwp.dao.FoodItemsDAO;
import com.albany.edu.fwp.dao.FoodSelectedDAO;
import com.albany.edu.fwp.dao.ImagesDAO;
import com.albany.edu.fwp.dao.MealCourseDAO;
import com.albany.edu.fwp.dao.QuadInfoDAO;
import com.albany.edu.fwp.dao.StudentDAO;
import com.albany.edu.fwp.model.Config;
import com.albany.edu.fwp.model.FeedBack;
import com.albany.edu.fwp.model.FoodDate;
import com.albany.edu.fwp.model.FoodItems;
import com.albany.edu.fwp.model.FoodSelected;
import com.albany.edu.fwp.model.Images;
import com.albany.edu.fwp.model.MealCourse;
import com.albany.edu.fwp.model.QuadInfo;
import com.albany.edu.fwp.model.Student;
import com.opensymphony.xwork2.ActionSupport;
 
public class AddFoodItemAction extends ActionSupport {
    private FoodItemsDAO foodItemsDAO; 
    private ImagesDAO imagesDAO;
    private MealCourseDAO mealCourseDAO;
    private QuadInfoDAO quadInfoDAO;
    private FoodSelectedDAO foodSelectedDAO;
    private FeedBackDAO feedBackDAO;
    private HttpSession session;
    private List<List<String>> foodList;
    private List<String> foodItemIdList;
    private List<String> foodItemNameList;
    private List<String> foodItemMealCourseList;

    private List<String> foodItemImagePathList; 
    private List<String> foodItemIdListAlreadyInMenu;
    private List<List<String>> foodListAlreadyInMenu;
	private String quadName;
	private String dateTimeVal;
    private Boolean isDeadlinePassed;
    private HttpServletRequest request;
    private FoodDateDAO foodDateDAO;
    private ConfigDAO configDAO;
    private List<String> feedbackString;
    private List<List<String>> reportData;
    private List<List<String>> mealCourseList;
    
    private File foodImage;
    private String foodName;
    private String relativeServingPlates;
    private String calories;
    private String mealType;
	
   
	public void setFoodDateDAO(FoodDateDAO foodDateDAO) {
		this.foodDateDAO = foodDateDAO;
	}

    public void setFoodItemsDAO(FoodItemsDAO foodItemsDAO) {
        this.foodItemsDAO = foodItemsDAO;
    }
    public void setImagesDAO(ImagesDAO imagesDAO) {
        this.imagesDAO = imagesDAO;
    }
    public void setMealCourseDAO(MealCourseDAO mealCourseDAO) {
        this.mealCourseDAO = mealCourseDAO;
    }
    public void setQuadInfoDAO(QuadInfoDAO quadInfoDAO) {
        this.quadInfoDAO = quadInfoDAO;
    }
    public void setFoodSelectedDAO(FoodSelectedDAO foodSelectedDAO) {
        this.foodSelectedDAO = foodSelectedDAO;
    }
    public void setFeedBackDAO(FeedBackDAO feedBackDAO) {
        this.feedBackDAO = feedBackDAO;
        
    }
    public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}

    public String execute() {
    	try
   	    {	
    		session = ServletActionContext.getRequest().getSession();
    		String quadId=session.getAttribute("quadID").toString();
	    	if(session.getAttribute("quadID")==null){    		
	    		return "logout";
	    	}
	    	
    		List<Images> imageList=imagesDAO.list();
    		String foodImageFileName=Integer.toString(imageList.get(imageList.size()-1).getImageId()+1);
    		
    		String destPath = ServletActionContext.getServletContext().getRealPath("/")+"resources/img/";
    		System.out.println("Selected Food Image: " + foodImage);
	     	String myFileFileName=foodImageFileName+".jpg";
	     	System.out.println("Dst File name: " + myFileFileName);
	     	System.out.println(destPath);
	     	File destFile  = new File(destPath, myFileFileName);
	    	FileUtils.copyFile(foodImage, destFile);
	    	
	    	
	    	
	    	
	    	imagesDAO.insert("resources/img/"+myFileFileName);
	    	
	    	System.out.println("Food Name------>"+foodName);
	    	System.out.println("Quad Id------>"+quadId);
	    	System.out.println("Relative ammount------>"+1);
	    	System.out.println("Relative serving plates------>"+relativeServingPlates);
	    	System.out.println("Image_Id------>"+foodImageFileName);
	    	System.out.println("Calories------>"+calories);
	    	System.out.println("Meal Type------>"+mealType);
	    	System.out.println("IsSelectedInMenu------>"+1);
	    	
	    	
	    	
	    	foodItemsDAO.insert(foodName, quadInfoDAO.getQuadInfo(Integer.parseInt(quadId)) ,Integer.parseInt("1"), Integer.parseInt(relativeServingPlates), imagesDAO.getImages(Integer.parseInt(foodImageFileName)), Integer.parseInt(calories), mealCourseDAO.getMealCourse(Integer.parseInt(mealType)), true);
    		
   	    }
    	catch (Exception e){		
       		e.printStackTrace();
       		return SUCCESS;
       	}
    	  	
    	   	
        return SUCCESS;
    }
    

	public List<String> getFeedbackString() {
		return feedbackString;
	}
    public List<List<String>> getReportData() {
		return reportData;
	}
    public List<List<String>> getFoodList() {
		return foodList;
	}
	public List<String> getFoodItemIdList() {
		return foodItemIdList;
	}
	public List<String> getFoodItemNameList() {
		return foodItemNameList;
	}	
	public List<String> getFoodItemMealCourseList() {
		return foodItemMealCourseList;
	}
	public List<String> getFoodItemImagePathList() {
		return foodItemImagePathList;
	}
	public String getQuadName() {
		return quadName;
	}
	public List<String> getFoodItemIdListAlreadyInMenu() {
		return foodItemIdListAlreadyInMenu;
	}
	public List<List<String>> getFoodListAlreadyInMenu() {
		return foodListAlreadyInMenu;
	}
	public String getDateTimeVal() {
		return dateTimeVal;
	}
	public Boolean getIsDeadlinePassed() {
		return isDeadlinePassed;
	}

	public List<List<String>> getMealCourseList() {
		return mealCourseList;
	}

	public File getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(File foodImage) {
		this.foodImage = foodImage;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getRelativeServingPlates() {
		return relativeServingPlates;
	}

	public void setRelativeServingPlates(String relativeServingPlates) {
		this.relativeServingPlates = relativeServingPlates;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
}
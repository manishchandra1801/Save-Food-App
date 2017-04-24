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
import com.albany.edu.fwp.model.MealCourse;
import com.albany.edu.fwp.model.QuadInfo;
import com.albany.edu.fwp.model.Student;
import com.opensymphony.xwork2.ActionSupport;
 
public class ManagerAction extends ActionSupport {
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
    private List<String> foodItemCalList;

    private List<String> foodItemImagePathList; 
    private List<String> foodItemIdListAlreadyInMenu;
    private List<List<String>> foodListAlreadyInMenu;
	private String quadName;
	private String dateTimeVal;
    private Boolean isDeadlinePassed;
    private HttpServletRequest request;
    private FoodDateDAO foodDateDAO;
    private ConfigDAO configDAO;
    private List<List<String>> feedbackString;
    private List<List<String>> reportData;
    private List<List<String>> mealCourseList;
	
   
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
	    	//session.setAttribute("quadID", 10);
	    	if(session.getAttribute("quadID")==null){    		
	    		return "logout";
	    	}
	    	
	    	
	    	
	    	
	    	List <MealCourse> mealCourses = mealCourseDAO.list();
	    	mealCourseList = new ArrayList<List<String>>();
	    	for (MealCourse mealCourse : mealCourses){
	    		List<String> mealCourseDetails = new ArrayList();
	    		mealCourseDetails.add(Integer.toString(mealCourse.getMealCourseId()));
	    		mealCourseDetails.add(mealCourse.getMealCourseName()); 
	    		mealCourseList.add(mealCourseDetails);
	    	}
	
	    	 request = ServletActionContext.getRequest();   
	    	 String dateString="";
	     	 String datetimeDeadline="";
	     	 String datetimeSession="";
	     	 
	     		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
	     		Date date = new Date();
	     		Calendar c = Calendar.getInstance();
	     		try {
	 				c.setTime(dmyFormat.parse(date.toString()));				
	 			} catch (ParseException e) {
	 				e.printStackTrace();
	 			}
	     		c.add(Calendar.DATE, 0);
	     		datetimeDeadline=dmyFormat.format(c.getTime()).toString();
	     		c.add(Calendar.DATE, 1);
	     		datetimeSession=dmyFormat.format(c.getTime()).toString();
	 	    	if(session.getAttribute("dateManager")==null){
	 	    		session.setAttribute("dateManager", dmyFormat.format(c.getTime()).toString());
	 	    		dateString=session.getAttribute("dateManager").toString();
	 	    	}
	 	    	else{
	 	    		dateString=session.getAttribute("dateManager").toString();
	 	    	}
	       	 
	       	 
		       	int quadId=Integer.parseInt(session.getAttribute("quadID").toString());
		    	quadName=quadInfoDAO.getQuadInfo(quadId).getQuadName();
		    	List <FoodItems> foodItems = foodItemsDAO.listByQuadId(quadId);
		    	List <FoodDate> allFoodItemsByDate = foodDateDAO.listFoodByDate(dateString);
		    	foodItemIdListAlreadyInMenu = new ArrayList<String>();
		    	for (FoodItems foodItem : foodItems){
			    	for (FoodDate allFoodItemByDate : allFoodItemsByDate){
			    		if(Integer.toString(allFoodItemByDate.getFoodItems().getFoodItemId()).equals(Integer.toString(foodItem.getFoodItemId()))){
			    			foodItemIdListAlreadyInMenu.add(Integer.toString(foodItem.getFoodItemId()));
			    		}
			    		
			    	}
		    	}
	       	 
		    	if(request.getParameter("action")!=null){		    		
		    		for (String id : foodItemIdListAlreadyInMenu){
			       		foodDateDAO.deleteFoodSelected(id, dateString);
			       		}		    		
		    	}
		    		
		    	
	       	 if( !(request.getParameterMap().isEmpty())){
	       		 
	       		for (String id : foodItemIdListAlreadyInMenu){
	       		foodDateDAO.deleteFoodSelected(id, dateString);
	       		}
	       		 
	        	Map<String, String[]> map = request.getParameterMap();
	     		Iterator<Entry<String,String[]>> iterator = map.entrySet().iterator();
	     		while (iterator.hasNext()) {
	     			Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>) iterator.next();
	     			System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue()[0]);
	     			System.out.println(dateString);
	     			if(!(entry.getValue()[0].equals("clear"))){
	     			FoodItems foodItem= foodItemsDAO.getFoodItem(entry.getValue()[0]);
	     			foodDateDAO.insertFoodSelected(foodItem, dateString);
	     			}
	     			
	     			//foodItems=foodItemsDAO.getFoodItem(entry.getKey());
	     			//foodSelectedDAO.insertFoodSelected(Integer.parseInt(entry.getValue()[0]), foodItems, student, dateString);
	     			//foodSelectedDAO.insertFoodSelected(Integer.parseInt(entry.getValue()[0]), foodItems, student, dateFormat.format(date).toString());
	     		}
	       	 }
	 		    	
		    	
		    	foodItems = foodItemsDAO.listByQuadId(quadId);
		    	allFoodItemsByDate = foodDateDAO.listFoodByDate(dateString);
		    	foodItemIdListAlreadyInMenu = new ArrayList<String>();
		    	for (FoodItems foodItem : foodItems){
			    	for (FoodDate allFoodItemByDate : allFoodItemsByDate){
			    		if(Integer.toString(allFoodItemByDate.getFoodItems().getFoodItemId()).equals(Integer.toString(foodItem.getFoodItemId()))){
			    			foodItemIdListAlreadyInMenu.add(Integer.toString(foodItem.getFoodItemId()));
			    		}
			    		
			    	}
		    	}
		    	 	
		    	
		    	foodList = new ArrayList<List<String>>();
		    	foodItemIdList = new ArrayList<String>();
		    	foodItemNameList = new ArrayList<String>();
		    	foodItemMealCourseList = new ArrayList<String>();
		    	foodItemImagePathList = new ArrayList<String>();
		    	foodItemCalList = new ArrayList<String>();
		    	for (FoodItems foodItem : foodItems){
		    		foodItemIdList.add(Integer.toString(foodItem.getFoodItemId()));
		    		foodItemNameList.add(foodItem.getFoodItemName());
		    		foodItemMealCourseList.add(mealCourseDAO.getMealCourse(foodItem.getMealCourse().getMealCourseId()).getMealCourseName());
		    		foodItemImagePathList.add(imagesDAO.imagePath(foodItem.getImages().getImageId()));
		    		foodItemCalList.add(Integer.toString(foodItem.getCalories()));
		    		List<String> foodItemDetails = new ArrayList();    		
		    		foodItemDetails.add(Integer.toString(foodItem.getFoodItemId()));
		    		foodItemDetails.add(foodItem.getFoodItemName());
		    		foodItemDetails.add(imagesDAO.imagePath(foodItem.getImages().getImageId()));
		    		foodItemDetails.add(mealCourseDAO.getMealCourse(foodItem.getMealCourse().getMealCourseId()).getMealCourseName()); 
		    		foodItemDetails.add(Integer.toString(foodItem.getRelativeServingPlates()));
		    		foodItemDetails.add(Integer.toString(foodItem.getCalories()));
		    		foodList.add(foodItemDetails);
		    	}
		    	
		    	foodListAlreadyInMenu=new ArrayList<List<String>>();	
		    	
		       	for (List<String> menu : foodList){
			    	for (String id : foodItemIdListAlreadyInMenu){
			    		if(menu.get(0).equals(id)){
			    			foodListAlreadyInMenu.add(menu);
			    		}
			    		
			    	}
		    	}
		       	
		       	
		      //Getting the feedback for Quad ID = 10
		    	//Needs to be updated to get from session
		    	List<FeedBack> feedBackText = feedBackDAO.getFeedback(quadId);    	
		    	List<FoodSelected> fetchReportData = foodSelectedDAO.fetchReportData(dateString);
		    	
		    	reportData = new ArrayList<List<String>>();
		    	for (FoodSelected foodSelected : fetchReportData){
		    		List<String> eachRow = new ArrayList();
		    		eachRow.add(foodItemsDAO.getFoodItem(Integer.toString(foodSelected.getFoodItems().getFoodItemId())).getFoodItemName());
		    		eachRow.add(mealCourseDAO.getMealCourse(foodItemsDAO.getFoodItem(Integer.toString(foodSelected.getFoodItems().getFoodItemId())).getMealCourse().getMealCourseId()).getMealCourseName());
		    		
		    		Integer totalPlates = 0;
		    		Integer relativePlates = 0;
		    		
		    		relativePlates = foodItemsDAO.getFoodItem(Integer.toString(foodSelected.getFoodItems().getFoodItemId())).getRelativeServingPlates();
		    		totalPlates += foodSelected.getNumberOfPlates();
		    		eachRow.add(Integer.toString(totalPlates/relativePlates));
		    		reportData.add(eachRow);
		    	}
		    	
		    	feedbackString = new ArrayList<List<String>>();
		    	for (FeedBack feedBack : feedBackText){
		    		List<String> eachRow = new ArrayList();
		    		eachRow.add(feedBack.getDescription());
					//Converting date format
					Date formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(feedBack.getTimestamp());
					String newstring = new SimpleDateFormat("MMMMM dd, yyyy 'at' hh:mm aaa").format(formattedDate);
		    		eachRow.add(newstring);
		    		feedbackString.add(eachRow);
		    	}
		       	
		       	
		       	List <Config> configs = configDAO.list();
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     	
		    	try {
		    		Date date1 = sdf.parse(datetimeDeadline);
					Date date2 = sdf.parse(session.getAttribute("dateManager").toString());
					if(date1.before(date2)){
						c.setTime(date2);
						c.add(Calendar.DATE, -1);
						isDeadlinePassed=false;
						datetimeDeadline=dmyFormat.format(c.getTime()).toString();
						dateTimeVal=datetimeDeadline.split("-")[1]+"/"+datetimeDeadline.split("-")[2]+"/"+datetimeDeadline.split("-")[0].replace("20", "")+"/"+configs.get(0).getManagerTime()+":59";
			    	}
					else{
						isDeadlinePassed=true;
						dateTimeVal=datetimeDeadline.split("-")[1]+"/"+datetimeDeadline.split("-")[2]+"/"+datetimeDeadline.split("-")[0].replace("20", "")+"/"+configs.get(0).getManagerTime()+":59";
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
		    	
		    	
		    	System.out.println("isDeadlinePassed --------------------> "+isDeadlinePassed);
		    	
		    	System.out.println("Date --------------------> "+session.getAttribute("dateManager"));
		    	System.out.println("dateTimeVal --------------------> "+dateTimeVal);
		    	
		    	
		     	       	 
   	    }
    	catch (Exception e){		
       		e.printStackTrace();
       	}
    	  	
    	   	
        return SUCCESS;
    }
    /*public String insertdateid(){
    	try
   	    {	  	
   	    	
    		   
   	    request = ServletActionContext.getRequest();   
   	 String dateString="";
   	 if(session.getAttribute("date")==null){ 
 		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
 		Date date = new Date();
 		Calendar c = Calendar.getInstance();
 		try {
				c.setTime(dmyFormat.parse(date.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
 		c.add(Calendar.DATE, 1);
 		session.setAttribute("date", dmyFormat.format(c.getTime()).toString());
 		dateString=session.getAttribute("date").toString();
   	 }
   	 else{
 		dateString=session.getAttribute("date").toString();
   	 }
 	
   	 if( !(request.getParameterMap().isEmpty())){
   		Map<String, String[]> map = request.getParameterMap();
		Iterator<Entry<String,String[]>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>) iterator.next();
			System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue()[0]);
			System.out.println(dateString);
			FoodItems foodItems= foodItemsDAO.getFoodItem(entry.getValue()[0]);
			fooddateDAO.insertFoodSelected(foodItems, dateString);
			//foodItems=foodItemsDAO.getFoodItem(entry.getKey());
			//foodSelectedDAO.insertFoodSelected(Integer.parseInt(entry.getValue()[0]), foodItems, student, dateString);
			//foodSelectedDAO.insertFoodSelected(Integer.parseInt(entry.getValue()[0]), foodItems, student, dateFormat.format(date).toString());
		}
   	 }
   	    
   	    	
   	      	    	
       	}
       	catch (Exception e){		
       		e.printStackTrace();
       	}
    	return "edit";
    }*/
	/*public List<List<String>> getFoodList() {

    	//Getting the feedback for Quad ID = 10
    	//Needs to be updated to get from session
    	List<FeedBack> feedBackText = feedBackDAO.getFeedback(10);    	
    	List<FoodSelected> fetchReportData = foodSelectedDAO.fetchReportData();
    	
    	reportData = new ArrayList<List<String>>();
    	for (FoodSelected foodSelected : fetchReportData){
    		List<String> eachRow = new ArrayList();
    		eachRow.add(foodItemsDAO.getFoodItem(foodSelected.getFoodItems().getFoodItemId()).getFoodItemName());
    		eachRow.add(mealCourseDAO.getMealCourse(foodItemsDAO.getFoodItem(foodSelected.getFoodItems().getFoodItemId()).getMealCourse().getMealCourseId()).getMealCourseName());
    		
    		Integer totalPlates = 0;
    		Integer relativePlates = 0;
    		
    		relativePlates = foodItemsDAO.getFoodItem(foodSelected.getFoodItems().getFoodItemId()).getRelativeServingPlates();
    		totalPlates += foodSelected.getNumberOfPlates();
    		eachRow.add(Integer.toString(totalPlates/relativePlates));
    		reportData.add(eachRow);
    	}
    	
    	feedbackString = new ArrayList<String>();
    	for (FeedBack feedBack : feedBackText){
    		feedbackString.add(feedBack.getDescription());
    	}
    	
        return SUCCESS;
    }*/

	public List<List<String>> getFeedbackString() {
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

	public List<String> getFoodItemCalList() {
		return foodItemCalList;
	}
	
}
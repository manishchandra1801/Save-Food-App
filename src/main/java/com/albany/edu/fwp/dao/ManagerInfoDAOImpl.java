package com.albany.edu.fwp.dao;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.albany.edu.fwp.model.FoodSelected;
import com.albany.edu.fwp.model.ManagerInfo;
import com.albany.edu.fwp.model.QuadInfo;


public class ManagerInfoDAOImpl implements ManagerInfoDAO{
	private SessionFactory sessionFactory;
	
    public ManagerInfoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
	public void insertManagerInfo(String managerID, String Manager_Name, String Manager_EmailID, String Manager_PhoneNumber, QuadInfo managerQuad) {
		ManagerInfo newManager = new ManagerInfo();
		newManager.setManagerID(managerID);
		newManager.setManagerName(Manager_Name);
		newManager.setManagerPhoneNumber(Manager_PhoneNumber);
		newManager.setManagerEmailID(Manager_EmailID);
		newManager.setQuad(managerQuad);
    	sessionFactory.getCurrentSession().save(newManager);
	}
    
    @Transactional
	public List<ManagerInfo> searchManagerInfo(String managerID, String Manager_Name, QuadInfo managerQuad, String emailID) {
    	ManagerInfo m = new ManagerInfo();
		m.setQuad(managerQuad);
		m.setManagerID(managerID);
		m.setManagerEmailID(emailID);
		m.setManagerName(Manager_Name);
    	String hql = "FROM ManagerInfo F WHERE F.Quad.quadId ="+managerQuad.getQuadId()+" OR F.ManagerID = '"+managerID+"' OR F.ManagerName='"+Manager_Name+"' OR F.ManagerEmailID='"+emailID+"'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<ManagerInfo> listManagerInfo = (List<ManagerInfo>) query.list();  
        return listManagerInfo;
    }
    
    @Transactional
    public List<Integer> getManagerQuad(String managerID) {
    	ManagerInfo m = new ManagerInfo();
    	m.setManagerID(managerID);
    	String hql = "SELECT F.Quad.quadId FROM ManagerInfo F WHERE F.ManagerID = '"+managerID+"'";
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	List<Integer> quadid = (List<Integer>) query.list();
    	return quadid;
    }
    
    @Transactional
	public void updateManager(String managerID, String Manager_Name, String Manager_EmailID, String Manager_PhoneNumber, QuadInfo managerQuad) {
    	ManagerInfo m = new ManagerInfo();
    	m.setManagerID(managerID);
    	m.setManagerName(Manager_Name);
    	m.setManagerEmailID(Manager_EmailID);
    	m.setManagerPhoneNumber(Manager_PhoneNumber);
    	String hql ="UPDATE ManagerInfo F SET F.Quad.quadId ="+managerQuad.getQuadId()+", F.ManagerName='"+Manager_Name+"', F.ManagerEmailID='"+Manager_EmailID+"', F.ManagerPhoneNumber='"+Manager_PhoneNumber+"' WHERE F.ManagerID = '"+managerID+"'";
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	query.executeUpdate();
    }
    
    @Transactional
    public void deleteManager(String managerID) {
    	ManagerInfo m = new ManagerInfo();
    	m.setManagerID(managerID);
    	String hql ="DELETE FROM ManagerInfo F where F.ManagerID='"+managerID+"'";
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	query.executeUpdate();
    }
}

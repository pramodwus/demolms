package com.qbis.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.AppFunctions;
import com.qbis.model.Roles;
import com.qbis.model.SubAppFunctions;

/**
 * This class is related with role functionality.
 * 
 * @author Kuldeep
 * 
 */
@Component
public class RoleService

{
	/**
	 * Get Application Menu Access Based On role
	 * 
	 * @param roleId
	 * @return List of Application Menu
	 * */
	public static ArrayList<AppFunctions> getApplicationMenu(int roleId) {
		ArrayList<AppFunctions> listFunctions = new ArrayList<AppFunctions>();
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_APPLICATION_FUNCTION_BASED_ON_ROLE,
					new Object[] { roleId });
			for (QueryData.Row row : data.getRows()) {
				AppFunctions appfunction = new AppFunctions();
				appfunction.setFunctionId(row.getRowItem(0) != null ? Integer
						.parseInt(row.getRowItem(0)) : null);
				appfunction.setFunctionName(row.getRowItem(1) != null ? row
						.getRowItem(1) : "");
				appfunction.setFunctionService(row.getRowItem(2) != null ? row
						.getRowItem(2) : "");
				appfunction.setMenuId(row.getRowItem(3) != null ? row
						.getRowItem(3) : "");
				appfunction.setFunctionClass(row.getRowItem(5) != null ? row
						.getRowItem(5) : "");
	            ArrayList<SubAppFunctions> subAppfunctionList=getSubAppfunctions(roleId,Integer.parseInt(row.getRowItem(4)));
				if((subAppfunctionList!=null)&&(subAppfunctionList.size()!=0)){appfunction.setSubAppFuntion(subAppfunctionList);listFunctions.add(appfunction);}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFunctions;
	}
	/**
	 * Get All Subfunction based On function Id,roleId
	 * @param roleId,functionId
	 **/
	public static ArrayList<SubAppFunctions> getSubAppfunctions(int roleId,int functionId)
	{
		ArrayList<SubAppFunctions> sublist=new ArrayList<SubAppFunctions>();
		try
		{
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SUB_FUNCTION_BASED_ON_FUNCTION_ID,
					new Object[] {roleId,functionId});
			for (QueryData.Row row : data.getRows())
			{
				SubAppFunctions subApp=new SubAppFunctions();
				subApp.setSubFunctionName(row.getRowItem(0));
				subApp.setSubFunctionService(row.getRowItem(1));
				subApp.setSubMenuId(row.getRowItem(2));
				subApp.setSubFunctionId(row.getRowItem(3) != null ? Integer
						.parseInt(row.getRowItem(3)) : null);
				subApp.setSubfunctionClass(row.getRowItem(4) != null ?row.getRowItem(4) : null);
				sublist.add(subApp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sublist;
	}
	/**
	 * Get All Subfunction based On function Id,roleId
	 * @param roleId,functionId
	 **/
	public static ArrayList<SubAppFunctions> getSubAppfunctions(int functionId)
	{
		ArrayList<SubAppFunctions> sublist=new ArrayList<SubAppFunctions>();
		try
		{
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SUB_FUNCTIONS,
					new Object[] {functionId});
			for (QueryData.Row row : data.getRows())
			{
				SubAppFunctions subApp=new SubAppFunctions();
				subApp.setSubFunctionName(row.getRowItem(1));
				subApp.setSubFunctionService(row.getRowItem(2));
				subApp.setSubMenuId(row.getRowItem(3));
				subApp.setSubFunctionId(row.getRowItem(4) != null ? Integer
						.parseInt(row.getRowItem(4)) : null);
				sublist.add(subApp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sublist;
	}
	/**
	 * Get All Application Menu
	 * 
	 * @param roleId
	 * @return List of Application Menu
	 * */
	public static ArrayList<AppFunctions> getAllApplicationMenu() {
		ArrayList<AppFunctions> listFunctions = new ArrayList<AppFunctions>();
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_ALL_APPLICATION_FUNCTIONS,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				AppFunctions appfunction = new AppFunctions();
				appfunction.setFunctionId(row.getRowItem(0) != null ? Integer
						.parseInt(row.getRowItem(0)) : null);
				appfunction.setFunctionName(row.getRowItem(1) != null ? row
						.getRowItem(1) : "");
				appfunction.setFunctionService(row.getRowItem(2) != null ? row
						.getRowItem(2) : "");
				appfunction.setMenuId(row.getRowItem(3) != null ? row
						.getRowItem(3) : "");
				 ArrayList<SubAppFunctions> subAppfunctionList=getSubAppfunctions(Integer.parseInt(row.getRowItem(0)));
					if((subAppfunctionList!=null)&&(subAppfunctionList.size()!=0)){appfunction.setSubAppFuntion(subAppfunctionList);listFunctions.add(appfunction);}
			//	listFunctions.add(appfunction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFunctions;
	}

	/**
	 * This is used for getting all list of all created role by a particular
	 * organization.
	 * 
	 * @param orgId
	 * @return List of Roles.
	 */
	public List<Roles> getRoleListByOrgId(int orgId) {
		List<Roles> roleList = new ArrayList<Roles>();
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_ROLE_LIST_BY_ORG_ID,
					new Object[] { orgId });
			for (QueryData.Row row : data.getRows()) {
				Roles role = new Roles();
				role.setRoleId(Integer.parseInt(row.getRowItem(0)));
				role.setRoleName(row.getRowItem(1));
				role.setRoleDesc(row.getRowItem(2));
				role.setRoleCreatorId(Integer.parseInt(row.getRowItem(3)));
				roleList.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}
	/**
	 * Create New Role 
	 * @param role
	 **/
	public Integer saveRole(Roles role)
	{ 
		int roleId=0;
		try
		{
		roleId = QueryManager
					.execuateUpdate(
							QueryStrings.SAVE_ROLE_DETAILS,
							new Object[] {role.getRoleName(),role.getRoleDesc(),role.getRoleCreatorId()});
					if(roleId>0)
					{
								for(int functionIndex=0;functionIndex<role.getAppfunctions().size();functionIndex++)
						        {
									ArrayList<SubAppFunctions> subAppfunctionList=role.getAppfunctions().get(functionIndex).getSubAppFuntion();
										for(int subfunctionIndex=0;subfunctionIndex<subAppfunctionList.size();subfunctionIndex++)
										{
											 QueryManager
												.execuateUpdate(
														QueryStrings.MAP_ROLEID_AND_APPFUNCTION,
														new Object[] {roleId,role.getAppfunctions().get(functionIndex).getFunctionId(),subAppfunctionList.get(subfunctionIndex).getSubFunctionId()});
										}
									
								}
					}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return roleId;
	}
	/**
	 * 
	 * 
	 * */
	public ArrayList<Roles> getRolesList(Integer userId)
	{
		ArrayList<Roles> roleList=new ArrayList<Roles>();
			try
			{
				QueryData data = QueryManager.execuateQuery(
						QueryStrings.GET_ROLE_DETAILS_BASED_ON_ADMIN_ID,
						new Object[] {userId});
				for (QueryData.Row row : data.getRows()) {
					Roles role = new Roles();
					role.setRoleId(Integer.parseInt(row.getRowItem(0)));
					role.setRoleName(row.getRowItem(1));
					role.setRoleDesc(row.getRowItem(2));
					String appFunctionList=getAppFunctionNamesList((Integer.parseInt(row.getRowItem(0))));
					if(appFunctionList!=null)role.setFunctionsName(appFunctionList);
					roleList.add(role);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return roleList;
	}
	/**
	 * Method to get Function name array mapped to a particular role.
	 * 
	 * @param functionId
	 * @return
	 */
	public String getAppFunctionNamesList(Integer roleId){
		List<String> functionNames = new ArrayList<String>();
		try
		{
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_APPLICATION_FUNCTION_BASED_ON_ROLE,
					new Object[] { roleId });
			for (QueryData.Row row : data.getRows()) {
				functionNames.add(row.getRowItem(1));
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return functionNames.toString().replace("[", "").replace("]","");
	}
}

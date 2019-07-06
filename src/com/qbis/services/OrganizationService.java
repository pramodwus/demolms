package com.qbis.services;

import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;

public class OrganizationService 
{
  /**
   * Check Organization Is Exist Or Not 
   * @param organization
   * @return boolean
   * */
   public static boolean checkOrganizationExist(String orgId)
   {
	   boolean orgStatus=false;
			   try
			   {
				   QueryData data = QueryManager.execuateQuery(
							QueryStrings.CHECK_ORGANIZATION, new Object[] {orgId});
						if (data.getRows().size() > 0) 
						{
							orgStatus=true;
						}
			   }
			   catch(Exception e)
			   {
				   e.printStackTrace();
			   }
	  return orgStatus;
   }
   /**
    * GET Organization Id  
    * @param organization
    * @return boolean
    * */
    public static int getOrganizationId(String orgName)
    {
 	   int orgId=0;
 			   try
 			   {
 				   QueryData data = QueryManager.execuateQuery(
 							QueryStrings.GET_ORGANIZATION_ID, new Object[] {orgName});
 						if (data.getRows().size() > 0) 
 						{
 							orgId=Integer.parseInt(data.getRows().get(0).getRowItem(0));
 						}
 			   }
 			   catch(Exception e)
 			   {
 				   e.printStackTrace();
 			   }
 	  return orgId;
    }

}

package close.gxph.bunny.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
 
public class ConvertResultSetToEntity{
 
  /**
   * 实现结果集到实体对象/值对象/持久化对象转换
   * @param rsResult ResultSet
   * @param strEntity String
   * @throws Exception
   * @return Object[]
   */
  public static Object[] parseDataEntityBeans(ResultSet rsResult, String strEntity)
      throws Exception{
      DataTableEntity dataTable = null;
      java.util.List listResult = new java.util.ArrayList();

      //注册实体,strEntity指定的实体类名称字符串
      Class classEntity = Class.forName(strEntity);
      //获取实体中定义的方法
      HashMap hmMethods = new HashMap();
      System.out.println(classEntity.getDeclaredMethods().length);
      for (int i = 0; i < classEntity.getDeclaredMethods().length; i++){
          MethodEntity methodEntity = new MethodEntity();
          //方法的名称
          String methodName = classEntity.getDeclaredMethods()[i].getName();
          String methodKey = methodName.toUpperCase();
          //方法的参数
          Class[] paramTypes = classEntity.getDeclaredMethods()[i].getParameterTypes();
         
          methodEntity.setMethodName(methodName);
          methodEntity.setMethodParamTypes(paramTypes);
         
          //处理方法重载
          if(hmMethods.containsKey(methodKey))
          {
              methodEntity.setRepeatMethodNum(methodEntity.getRepeatMethodNum()+1);
              methodEntity.setRepeatMethodsParamTypes(paramTypes);
          }
          else
          {
            hmMethods.put(methodKey, methodEntity);
          }
      }
     
      //处理ResultSet结构体信息
      if(rsResult != null)
      {
        ResultSetMetaData rsMetaData = rsResult.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        dataTable = new DataTableEntity(columnCount);
        //获取字段名称，类型
        for (int i = 0; i < columnCount; i++)
        {
          String columnName = rsMetaData.getColumnName(i + 1);
          int columnType = rsMetaData.getColumnType(i + 1);
         
          dataTable.setColumnName(columnName, i);
          dataTable.setColumnType(columnType, i);
        }
      }
     
      //处理ResultSet数据信息
      while(rsResult.next())
      {
        //调用方法，根据字段名在hsMethods中查找对应的set方法
        Object objResult = ParseObjectFromResultSet(rsResult, dataTable, classEntity, hmMethods);
        listResult.add(objResult);
      }
     
      //以数组方式返回
      Object objResutlArray = Array.newInstance(classEntity, listResult.size());
      listResult.toArray((Object[]) objResutlArray);

      return (Object[])objResutlArray;
  }
  public static Object ParseObjectFromResultSet(ResultSet rs,DataTableEntity dataTable,Class classEntity,java.util.HashMap hsMethods)
	      throws Exception{
	      Object objEntity = classEntity.newInstance();
	      Method method = null;
	     
	      int nColumnCount = dataTable.getColumnCount();
	      String[] strColumnNames = dataTable.getColumnNames();
	     
	      for (int i = 0; i < nColumnCount; i++){
	          //获取字段值
	          Object objColumnValue = rs.getObject(strColumnNames[i]);
	          System.out.println("objColumnValue:"+objColumnValue);
	          //HashMap中的方法名key值
	          String strMethodKey = null;
	         
	          //获取set方法名
	          if (strColumnNames[i] != null){
	              strMethodKey = String.valueOf("SET" + strColumnNames[i].toUpperCase());
	              strMethodKey=strMethodKey.replace("_",""); 
	              System.out.println("strMethodKey:"+strMethodKey);
	          }
	          //值和方法都不为空,这里方法名不为空即可,值可以为空的
	          if (strMethodKey!=  null){
	              //判断字段的类型,方法名，参数类型
	             try{  
	                MethodEntity methodEntity = (MethodEntity)hsMethods.get(strMethodKey);
	               
	                String methodName = methodEntity.getMethodName();
	               // System.out.println("正常情况的methodName:"+methodName);
	                int repeatMethodNum = methodEntity.getRepeatMethodNum();
	               //System.out.println("正常情况的repeatMethodNum:"+repeatMethodNum);
	                Class[] paramTypes = methodEntity.getMethodParamTypes();
	              //  System.out.println("正常情况的paramTypes："+paramTypes);
	                method = classEntity.getMethod(methodName, paramTypes);
	              //  System.out.println("正常情况的method："+method);
	                //如果重载方法数 > 1，则判断是否有java.lang.IllegalArgumentException异常，循环处理
	                try {
	                    //设置参数,实体对象，实体对象方法参数
	                    method.invoke(objEntity, new Object[]{objColumnValue});
	                }
	                catch(java.lang.IllegalArgumentException e){
	                	
	                	/*Method setMethod = dto.getClass().getMethod(    
                                setMethodName, String.class); */
	                	System.out.println(classEntity.getClass().getName()+"......................");
	                	method = classEntity.getClass().getMethod(methodName, String.class);
	                	method.invoke(objEntity, new Object[]{String.valueOf(objColumnValue)});  
	                	//value.toString()
	                    //处理重载方法
	                    /*for(int j = 1; j < repeatMethodNum; j++){
	                         try{
	                        	Class[] repeatParamTypes = methodEntity.getRepeatMethodsParamTypes(j - 1);
	                        	method = classEntity.getMethod(methodName, repeatParamTypes);
	                            method.invoke(objEntity, new Object[]{objColumnValue});
	                        	 break;    
	                        }
	                        catch(java.lang.IllegalArgumentException ex){
	                            continue;
	                         }
	                    }*/
	                }
	             }
	             catch (NoSuchMethodException e){
	               throw new NoSuchMethodException();
	             }
	             catch(Exception ex){
	               ex.printStackTrace();
	             }
	          }
	      }
	      return objEntity;
	  }
	}
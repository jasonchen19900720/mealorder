package mealorder.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jason.mealorder.common.JsonUtil;
import com.jason.mealorder.entity.SCarItem;
import com.jason.mealorder.mapper.SCarItemMapper;
import com.jason.mealorder.viewmodel.GoodsItem;


public class SpringMybatisTest {

	public static void jsonTest(){
		String json="[{\"goodsName\":\"回锅肉\",\"amount\":2,\"price\":54},{\"goodsName\":\"清蒸鱼\",\"amount\":1,\"price\":26}]";
    	System.out.println(json);
    	List<GoodsItem> list=(List<GoodsItem>)JsonUtil.jsonStrToList(json, GoodsItem.class);
    	System.out.println(list.get(0).getPrice()); 
    	System.out.println(list.get(0).getGoodsName());
    	System.out.println(list.get(0).getPrice());
   
    	List<GoodsItem> list0=new ArrayList<GoodsItem>();
    	GoodsItem item0=new GoodsItem();
    	item0.setAmount(1);
    	item0.setPrice(5);
    	item0.setGoodsName("aaa");
    	GoodsItem item1=new GoodsItem();
    	item1.setAmount(2);
    	item1.setPrice(6);
    	item1.setGoodsName("bbb");
    	list0.add(item0);
    	list0.add(item1);
    	String jsonArrayStr=JsonUtil.listToJsonStr(list0);
    	System.out.println(jsonArrayStr); 
	} 
	
    public static void addSCarItemTest(SCarItemMapper testDao){	
    	
    	SCarItem item=new SCarItem();
    	item.setAmount(1);
    	item.setGoodsName("dddd");
    	item.setPrice(3);
    	item.setUserUuid("2w2w2w");
    	try {
    		testDao.addSCarItem(item); 			
    		System.out.println("addSCarItemTest success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addSCarItemTest failed");
		}
	}
	public static void deleteSCarItemTest(SCarItemMapper testDao){
		System.out.println("deleteSCarItemTest enter");
		String userUuid="06d51ebff36e477f9ac93fb6f7530a93";
		String goodsName="回锅肉";
		try {
			testDao.deleteSCarItem(userUuid, goodsName);	
			System.out.println("deleteSCarItemTest success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteSCarItemTest failed");
		}
	}
    @SuppressWarnings("resource")
	public static void main(String[] args) {  
    	String xmlPath="/mealorder/test/applicationContext.xml";
    	ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);	
    	SCarItemMapper testDao=(SCarItemMapper)context.getBean("testDao");
    	System.out.println(testDao.getClass().getSimpleName());
    	deleteSCarItemTest(testDao);
    }
                
}

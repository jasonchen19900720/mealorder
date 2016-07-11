package mealorder.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jason.mealorder.mapper.SCarItemMapper;

public class MybatisTest {

	public static void deleteSCarItemTest(String cfg) throws IOException{
		
		System.out.println("deleteSCarItemTest enter");
		Reader reader = Resources.getResourceAsReader(cfg);
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
			SqlSession sqlSession=sqlSessionFactory.openSession();
			SCarItemMapper sCarItemMapper=sqlSession.getMapper(SCarItemMapper.class);
			String userUuid="06d51ebff36e477f9ac93fb6f7530a93";
			String goodsName="»Ø¹øÈâ";
			sCarItemMapper.deleteSCarItem(userUuid, goodsName);
			sqlSession.commit();		
			sqlSession.close();			
	}
	public static void main(String[] args) throws IOException{
		String cfg="mealorder/test/mybatisConfig.xml";
		deleteSCarItemTest(cfg);		
	}

}

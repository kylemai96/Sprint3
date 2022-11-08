package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.entity.RestaurantPassword;

@Repository
public class RestaurantPasswordRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public RestaurantPassword save(RestaurantPassword restaurantPassword) {
		dynamoDBMapper.save(restaurantPassword);
		return restaurantPassword;
	}
	
	public PaginatedScanList<RestaurantPassword> getRestaurantPasswordByPassword(String password) {
		Map<String, AttributeValue> map = new HashMap<>();
		map.put(":val1", new AttributeValue().withS(password));
		return dynamoDBMapper.scan(RestaurantPassword.class, new DynamoDBScanExpression().withFilterExpression("password = :val1").withExpressionAttributeValues(map));
	}
	
	public RestaurantPassword getRestaurantPasswordById(String id) {
		return dynamoDBMapper.load(RestaurantPassword.class, id);
	}
	
	public String delete(String id) {
		RestaurantPassword r = dynamoDBMapper.load(RestaurantPassword.class, id);
		dynamoDBMapper.delete(r);
		return "id " + id + " deleted";
	}
	
	public String update(String id, RestaurantPassword restaurantPassword) {
		dynamoDBMapper.save(restaurantPassword, 
					new DynamoDBSaveExpression()
					.withExpectedEntry("id",
							new ExpectedAttributeValue(
									new AttributeValue().withS(id)
									)
							)
				);
		return id;
	}
}

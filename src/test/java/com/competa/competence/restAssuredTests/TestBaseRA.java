package com.competa.competence.restAssuredTests;

import com.competa.competence.DataBase;
import com.competa.competence.fwRA.UserHelperRA;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBaseRA {

    final static Logger logger = LoggerFactory.getLogger(TestBaseRA.class);
    protected static DataBase db;
    protected static UserHelperRA user = new UserHelperRA();
   // protected static LessonHelperRA lesson = new LessonHelperRA();

    @BeforeMethod
    public void precondition(Method method, Object[] parameters){
        RestAssured.baseURI = "http://localhost:5173";
       // RestAssured.basePath = "api";
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
    }

    @AfterMethod
    public void quit(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: " + result.getMethod().getMethodName());
        }
        logger.info("Stop test");
        logger.info("==============================");
    }

}

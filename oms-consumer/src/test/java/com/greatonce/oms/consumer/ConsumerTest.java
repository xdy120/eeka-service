package com.greatonce.oms.consumer;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-15
 */
@ActiveProfiles("dev,test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ConsumerTest {

}

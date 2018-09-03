package com.solutionladder.ethearts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EtheartsApplicationTests {

	@Test
	public void contextLoads() {
	    Predicate<Object> obj = null;
	    assertThat(obj).isNull();
	}

}

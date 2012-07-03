package ch.sample.project;

import java.nio.charset.IllegalCharsetNameException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", "/applicationContext-eh-aspects.xml",
		"/applicationContext-eh.xml", "/applicationContext-eh-parents.xml" })
public class WrapExceptionAspectTestCase {

	@Autowired
	private SampleBean sampleBean;

	@Test(expected = IllegalCharsetNameException.class)
	public void throwIllegalArgumentAndCatchIllegalCharsetNameException() {
		sampleBean.throwIllegalArgumentExceptionAndWrapToIllegalCharsetNameException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwIllegalCharsetNameExceptionAndCatchIllegalArgumentException() {
		sampleBean.throwIllegalCharsetNameExceptionAndWrapToIllegalArgumentException();
	}
}

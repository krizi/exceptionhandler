package ch.sample.project;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", "/applicationContext-eh-aspects.xml",
		"/applicationContext-eh.xml", "/applicationContext-eh-parents.xml" })
public class SuppressExceptionAspectTestCase {

	@Autowired
	private SampleBean sampleBean;

	@Test
	public void catchedByAspect() {
		try {
			sampleBean.throwAndSuppressException();
		} catch (IllegalArgumentException e) {
			Assert.fail("exception should be suppressed by aspect");
		}
	}

	@Test
	public void catchedByAspectAndReturnEmptyList() {
		try {
			List<?> list = sampleBean.throwAndSuppressExceptionReturnEmptyList();

			Assert.assertNotNull(list);
			Assert.assertTrue(list.isEmpty());
		} catch (IllegalArgumentException e) {
			Assert.fail("exception should be suppressed by aspect");
		}
	}

	@Test(expected = RuntimeException.class)
	public void throwButNotCatchedByAspect() {
		sampleBean.throwRuntimeExceptionSuppressIllegalArgumentException();
	}

	@Test
	public void notCatchedByAspect() {
		sampleBean.throwAndSuppressException();
	}
}

package ch.sample.project;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-eh-aspects.xml", "/applicationContext-eh.xml",
		"/applicationContext-eh-parents.xml", "/applicationContext-test.xml" })
public class HandleExceptionAspectTestCase {

	@Autowired
	private SampleBean sampleBean;

	@Resource(name = "exceptionBag")
	private List<Exception> exceptionBag;

	@Before
	public void checkBean() {
		Assert.assertNotNull(sampleBean);
	}

	@Test
	public void handledByAspect() {
		try {
			sampleBean.throwAndHandleException();
		} catch (IllegalArgumentException e) {
			// expected exception
		}

		Assert.assertNotNull(exceptionBag);
		Assert.assertEquals(1, exceptionBag.size());
	}

}

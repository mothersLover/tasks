import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {

    @Before
    public void beforeTest() {
        System.out.println("Before test!");
    }

    @Test
    public void test() {
        String helloWorld = new HelloWorld().getHelloWorld();
        Assert.assertEquals("Hello World!", helloWorld);
    }
}

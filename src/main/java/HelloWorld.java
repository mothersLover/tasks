import org.junit.Assert;
import org.junit.Test;

public class HelloWorld {
    private static final String HELLO_WORLD = "Hello World!";

    @Test
    public void test() {
        Assert.assertEquals("Hello World!", HELLO_WORLD);
    }
}

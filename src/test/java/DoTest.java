import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 最简单的测试案例
 *
 * @author nwj
 */
public class DoTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");

    }


}

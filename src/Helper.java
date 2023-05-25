import java.util.TimerTask;

/**
 * write description
 *
 * @author Aikam Singh
 * @version 5/24/23
 */

public class Helper extends TimerTask {
    static int time = 60;
    @Override
    public void run() {
        time--;
    }
}

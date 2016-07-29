import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 95han on 2016/7/29.
 */
public class TranslateTask {

    interface Callback {
        void onSuccess(ResultBean resultBean);

        void onFailed(String errorMsg);
    }

    public static void start(String text, Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = HttpUtils.getInputStream("http://dict.youdao.com/fsearch?&q=" + text);
                    callback.onSuccess(XmlParseUtils.parse(is));
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailed(e.toString());
                }
            }
        }).start();
    }

}

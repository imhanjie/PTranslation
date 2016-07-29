import org.apache.http.Header;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {

    public static InputStream getInputStream(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        String data = "";
        InputStream is = null;
        if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
        }
//        if (is != null) {
//            is.close();
//        }
        return is;
    }

    /**
     * @param is 输入流
     * @return 返回流中获取的字符串
     * @throws IOException
     */
    public static String readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            bos.write(buff, 0, len);
        }
        is.close();
        byte[] lens = bos.toByteArray();
        String result = new String(lens, "utf-8");
        bos.close();
        return result;
    }

}

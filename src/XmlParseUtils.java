import org.apache.xerces.parsers.XMLParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 95han on 2016/7/29.
 */
public class XmlParseUtils {

    public static ResultBean parse(InputStream is) {
        ResultBean resultBean = new ResultBean();
//        ByteArrayInputStream is = new ByteArrayInputStream(xmlText.getBytes());
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, "utf-8");
            int eventType = parser.getEventType();
            StringBuffer customBuffer = new StringBuffer();
            StringBuffer webBuffer = new StringBuffer();
            boolean trans = true;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equals("return-phrase")) {
                            resultBean.word = parser.nextText();
                        } else if (name.equals("content")) {
                            customBuffer.append(parser.nextText() + "\n");
                        } else if (name.equals("key")) {
                            webBuffer.append(parser.nextText() + ": ");
                            trans = true;
                        } else if (name.equals("value")) {
                            if (trans) {
                                webBuffer.append(parser.nextText() + "\n");
                            }
                            trans = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        break;
                }
                eventType = parser.next();
            }

            resultBean.custom = customBuffer.toString();
            resultBean.web = webBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultBean;
    }

}

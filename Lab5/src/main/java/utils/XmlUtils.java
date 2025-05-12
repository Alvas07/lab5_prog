package utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class XmlUtils {
  public static String getTextValue(Element element, String tagName) {
    NodeList nodeList = element.getElementsByTagName(tagName);
    if (nodeList.getLength() == 0) {
      return "";
    }
    return nodeList.item(0).getTextContent().trim();
  }

  public static Integer getIntValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Integer.parseInt(text);
  }

  public static Long getLongValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Long.parseLong(text);
  }

  public static Float getFloatValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Float.parseFloat(text);
  }

  public static Double getDoubleValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Double.parseDouble(text);
  }
}

package utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Класс, предоставляющий вспомогательные методы для работы с форматом XML.
 *
 * @author Alvas
 * @since 1.0
 */
public final class XmlUtils {
  /**
   * Возвращает строку из указанного тега XML-элемента.
   *
   * <p>Возвращает пустую строку при пустом значении.
   *
   * @param element XML-элемент {@link Element}.
   * @param tagName имя XML-тега.
   * @return Строка.
   * @see Element
   * @author Alvas
   * @since 1.0
   */
  public static String getTextValue(Element element, String tagName) {
    NodeList nodeList = element.getElementsByTagName(tagName);
    if (nodeList.getLength() == 0) {
      return "";
    }
    return nodeList.item(0).getTextContent().trim();
  }

  /**
   * Возвращает целое число типа {@code Integer} из указанного тега XML-элемента.
   *
   * <p>Возвращает {@code null} при пустом значении.
   *
   * @param element XML-элемент {@link Element}.
   * @param tagName имя XML-тега.
   * @return Целое число типа {@code Integer}.
   * @see Element
   * @author Alvas
   * @since 1.0
   */
  public static Integer getIntValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Integer.parseInt(text);
  }

  /**
   * Возвращает целое число типа {@code Long} из указанного тега XML-элемента.
   *
   * <p>Возвращает {@code null} при пустом значении.
   *
   * @param element XML-элемент {@link Element}.
   * @param tagName имя XML-тега.
   * @return Целое число типа {@code Long}.
   * @see Element
   * @author Alvas
   * @since 1.0
   */
  public static Long getLongValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Long.parseLong(text);
  }

  /**
   * Возвращает дробное число типа {@code Float} из указанного тега XML-элемента.
   *
   * <p>Возвращает {@code null} при пустом значении.
   *
   * @param element XML-элемент {@link Element}.
   * @param tagName имя XML-тега.
   * @return Дробное число типа {@code Float}.
   * @see Element
   * @author Alvas
   * @since 1.0
   */
  public static Float getFloatValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Float.parseFloat(text);
  }

  /**
   * Возвращает дробное число типа {@code Double} из указанного тега XML-элемента.
   *
   * <p>Возвращает {@code null} при пустом значении.
   *
   * @param element XML-элемент {@link Element}.
   * @param tagName имя XML-тега.
   * @return Дробное число типа {@code Double}.
   * @see Element
   * @author Alvas
   * @since 1.0
   */
  public static Double getDoubleValue(Element element, String tagName) {
    String text = getTextValue(element, tagName);
    if (text.isEmpty()) {
      return null;
    }
    return Double.parseDouble(text);
  }
}

package managers;

import data.Ticket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс, отвечающий за взаимодействие с {@code id} объектов класса {@link Ticket}.
 *
 * <p>Хранит список всех использованных {@code id}.
 *
 * <p>Для представления {@code id} используется класс {@link AtomicInteger}.
 *
 * @see Ticket
 * @see AtomicInteger
 * @author Alvas
 * @since 1.0
 */
public final class IdManager {
  private static final AtomicInteger counter = new AtomicInteger(1);
  private static final ArrayList<Integer> idList = new ArrayList<>();

  /**
   * Возвращает свободное значение {@code id} и переходит на следующее свободное значение.
   *
   * <p>Полученное значение записывает в список всех {@code id}.
   *
   * @return Свободное значение {@code id}.
   * @author Alvas
   * @since 1.0
   */
  public static int getAndIncrement() {
    int nextId = counter.getAndIncrement();
    while (!idIsUnique(nextId)) {
      nextId = counter.getAndIncrement();
    }
    addId(nextId);
    return nextId;
  }

  /**
   * Удаляет из списка последнее использованное значение {@code id} и переходит на него.
   *
   * @author Alvas
   * @since 1.0
   */
  public static void removeLastId() {
    int prevId = counter.decrementAndGet();
    remove(prevId);
  }

  /**
   * Проверяет значение {@code id} на уникальность.
   *
   * @param id значение для проверки.
   * @return {@code true} - если значение {@code id} еще не использовалось, {@code false} - если уже
   *     использовалось.
   * @author Alvas
   * @since 1.0
   */
  public static boolean idIsUnique(int id) {
    return !idList.contains(id);
  }

  /**
   * Добавляет заданное значение {@code id} в общий список.
   *
   * @param id значение для добавления.
   * @author Alvas
   * @since 1.0
   */
  public static void addId(int id) {
    idList.add(id);
  }

  /**
   * Удаляет заданное значение {@code id} из общего списка.
   *
   * @param id значение для удаления.
   * @author Alvas
   * @since 1.0
   */
  public static void remove(int id) {
    idList.removeIf(x -> (x == id));
  }
}

package com.greatonce.oms.web.controller;

import com.greatonce.core.CaptionEnum;
import com.greatonce.core.ValueEnum;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.web.controller.ao.EnumAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 枚举Controller.
 *
 * @author buer
 * @version 2017-10-24 9:55
 */
@RestController
@CrossOrigin
public class EnumController {

  private static final Map<Class<?>, List<EnumAO>> enumCache = new ConcurrentHashMap<>();

  /**
   * 枚举转为Map.
   *
   * @param clazz 枚举类名
   */
  private static List<EnumAO> valueEnumToMap(Class<? extends CaptionEnum> clazz)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    List<EnumAO> list = enumCache.get(clazz);
    if (list == null) {
      Method method = clazz.getMethod("values");
      ValueEnum[] values = (ValueEnum[]) method.invoke(null);
      list = new ArrayList<>(values.length);
      for (ValueEnum value : values) {
        list.add(new EnumAO(value.toString(), value.caption(), value.value()));
      }
      enumCache.put(clazz, list);
    }
    return list;
  }

  private static Reflections REFLECTIONS = new Reflections("com.greatonce.oms.domain.enums");

  /**
   * 枚举转为Map.
   *
   * @param enumName 枚举类名，需要在com.greatonce.oms.domain.enums
   */
  private static List<EnumAO> valueEnumToMap(String enumName) {
    try {
      Optional<Class<? extends ValueEnum>> ls = REFLECTIONS
          .getSubTypesOf(ValueEnum.class)
          .stream()
          .filter(p -> p.getSimpleName().equals(enumName))
          .findFirst();
      if (ls.isPresent()) {
        return valueEnumToMap(ls.get());
      }
      throw SysExceptions.ENUM_CONVERT_FAILED;
    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
      throw SysExceptions.ENUM_CONVERT_FAILED;
    }
  }

  @RequestMapping(path = "/enum/{name}")
  public List<EnumAO> getEnumMap(@PathVariable("name") String name) {
    return valueEnumToMap(name);
  }
}

package com.greatonce.oms.web.converter;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.StringUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

/**
 * 逗号隔开的字符串转List.
 *
 * @author buer
 * @version 2018-01-25 19:51
 */
public class String2ListConverter implements ConditionalGenericConverter {

  private final ConversionService conversionService;

  public String2ListConverter(ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    return Collections.singleton(new ConvertiblePair(String.class, Collection.class));
  }

  @Override
  public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
    return this.conversionService.canConvert(sourceType, targetType.getElementTypeDescriptor());
  }

  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    String string = (String) source;
    if (Assert.isEmpty(string)) {
      return null;
    }
    List<String> fields = StringUtil.words(string);
    TypeDescriptor elementDesc = targetType.getElementTypeDescriptor();
    Collection<Object> target = CollectionFactory
        .createCollection(targetType.getType(), elementDesc.getType(), fields.size());
    for (String field : fields) {
      Object targetElement = this.conversionService
          .convert(field.trim(), sourceType, elementDesc);
      target.add(targetElement);
    }
    return target;
  }
}

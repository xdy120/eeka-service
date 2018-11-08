package com.greatonce.oms.api.qimen;

import com.greatonce.core.Constants;
import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenRequest;
import com.qimen.api.QimenRequest;
import com.qimen.api.QimenResponse;
import com.taobao.api.internal.util.XmlWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * 奇门内容转换.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/31
 */
public class QimenMessageConverter extends AbstractHttpMessageConverter<Object> {

  static final Logger LOGGER = LoggerFactory.getLogger(QimenMessageConverter.class);

  @Override
  protected boolean supports(Class<?> clazz) {
    return OmsQimenRequest.class.isAssignableFrom(clazz) || QimenRequest.class
        .isAssignableFrom(clazz) || QimenResponse.class.isAssignableFrom(clazz);
  }

  @Override
  protected Object readInternal(Class<?> clazz, HttpInputMessage httpInputMessage)
      throws IOException, HttpMessageNotReadableException {
    String xml = IOUtils.toString(httpInputMessage.getBody(), Constants.CHARSET_UTF8);
    LOGGER.info("接收到奇门请求：{}", xml);

    try {
      return convertToJavaBean(xml, clazz);
    } catch (JAXBException e) {
      LOGGER.error("奇门Xml转换异常！", e);
      throw new HttpMessageNotReadableException(e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T convertToJavaBean(String xml, Class<T> c) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(c);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    try (StringReader reader = new StringReader(xml)) {
      return (T) unmarshaller.unmarshal(reader);
    }
  }


  @Override
  protected void writeInternal(Object qimenRequest, HttpOutputMessage httpOutputMessage)
      throws HttpMessageNotWritableException {
    XmlWriter writer = new XmlWriter(true, "response", Object.class);
    String apiBody = writer.write(qimenRequest);
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      outputStream.write(apiBody.getBytes(StandardCharsets.UTF_8));
      outputStream.writeTo(httpOutputMessage.getBody());
    } catch (IOException e) {
      LOGGER.error("奇门写入Response失败！", e);
      throw new HttpMessageNotReadableException(e.getMessage());
    }
  }
}

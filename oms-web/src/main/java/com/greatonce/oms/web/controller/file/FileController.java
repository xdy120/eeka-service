package com.greatonce.oms.web.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-05-30
 * Time: 14:25
 * Description:
 */
@RestController
@RequestMapping(value = "/file")
@CrossOrigin
public class FileController {


  @RequestMapping("/{type}/{file}/{fileName}")
  public void download(@PathVariable String type, @PathVariable String file,
      @PathVariable String fileName, HttpServletResponse response) throws IOException {
    OutputStream out;

    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-disposition",
        "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xlsx");
    response.setCharacterEncoding("utf-8");
    out = response.getOutputStream();
    String path = ResourceUtils.getURL("").getPath();
    File outFile = new File(path + "file/" + type + "/" + file);

    if (outFile.exists()) {
      try (InputStream inputStream = new FileInputStream(outFile)) {
        int readLength;
        byte[] buff = new byte[1024];
        while ((readLength = inputStream.read(buff)) != -1) {
          out.write(buff, 0, readLength);
        }
        out.flush();
      }
    }

  }
}

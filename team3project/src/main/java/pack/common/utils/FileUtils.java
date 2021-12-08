package pack.common.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pack.obfile.domain.ObFile;

@Component("fileUtils")
public class FileUtils {

    private static final String uploadPath = "C:\\work\\wsou\\team3project\\src\\main\\webapp\\resources\\upload\\";

    private final Log log = LogFactory.getLog(this.getClass());
	
    public List<ObFile> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception {

        final MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        final Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        final List<ObFile> list = new ArrayList<>();
        final String ob_no = String.valueOf(map.get("ob_no"));

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        while (iterator.hasNext()) {
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if (!Objects.requireNonNull(multipartFile).isEmpty()) {
                final String originalFileName = multipartFile.getOriginalFilename();
                final String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                final String storedFileName = FileUtils.getRandomString() + originalFileExtension;
                log.debug("-----------------------------------------");
                log.debug("original_file_name" + originalFileName);
                log.debug("file_size" + multipartFile.getSize());
                log.debug("------------------------------------------");

                file = new File(uploadPath + storedFileName);
                multipartFile.transferTo(file);

                list.add(ObFile.builder()
                    .obNo(Integer.parseInt(ob_no))
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .fileSize((int) multipartFile.getSize())
                    .obRdate(LocalDateTime.now())
                    .delGb("N")
                    .build());
            }
        }
        return list;
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}


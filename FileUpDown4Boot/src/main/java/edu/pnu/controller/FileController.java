package edu.pnu.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class FileResponse {
	private String status;
	private String message;
}

@RestController
public class FileController {

	@Value("${spring.servlet.multipart.location}")
	private String location;
	
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		
		if (file.isEmpty()) {
			return ResponseEntity.ok(new FileResponse("OK", "File is Empty!"));
		}
		try {
			// File의 메소드를 이용하는 방법
			file.transferTo(new File(location + file.getOriginalFilename()));
			
			// FileOutputStream 객체를 이용하는 방법
			//FileOutputStream fos = new FileOutputStream(location + file.getOriginalFilename());
			//fos.write(file.getBytes());
			//fos.close();
		} catch(Exception e) {
			return ResponseEntity.ok(new FileResponse("Exception", e.getMessage()));
		}		
		return ResponseEntity.ok(new FileResponse("OK", "업로드되었습니다."));
	}

	@PostMapping("/api/uploads")
	public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		int cnt = 0;
		for (MultipartFile file : files) {
			if (file.isEmpty())	continue;

			try {
				file.transferTo(new File(location + file.getOriginalFilename()));
				cnt++;
			} catch(Exception e) {}
		}
		return ResponseEntity.ok(new FileResponse("OK", files.length + "개 파일 중 " + cnt +"개 파일이 업로드되었습니다."));
	}
	
	@GetMapping("/api/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) {
        // 다운로드 대상 파일
        String filePath = location + filename;
        File file = new File(filePath);

        if (!file.exists())	return ResponseEntity.notFound().build();

        // Resource 객체 생성
        Resource resource = new FileSystemResource(file);

        // 파일 전송 ==> 파일 이름만 제외하고 고정
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }	
}

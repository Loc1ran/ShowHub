package loctran.showhub.s3;

import loctran.showhub.dto.UploadResponse;
import loctran.showhub.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
@AllArgsConstructor
public class FileUploadController {
    private S3Service s3Service;
    private UserService userService;

    @PostMapping("/profile-picture")
    public ResponseEntity<UploadResponse> uploadProfilePicture(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal UserDetails user
    ){
        String pictureUrl = s3Service.uploadFile("profiles", file);

        userService.updateProfilePicture(user, pictureUrl);

        return ResponseEntity.ok(new UploadResponse(true, "Profile picture updated", pictureUrl));
    }
}

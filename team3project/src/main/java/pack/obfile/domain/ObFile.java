package pack.obfile.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ObFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "ob_no")
    private int obNo;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "stored_file_name")
    private String storedFileName;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "ob_rdate")
    private LocalDateTime obRdate;

    @Column(name = "del_gb")
    private String delGb;

    @Builder
    public ObFile(int obNo, String originalFileName, String storedFileName, int fileSize,
        LocalDateTime obRdate, String delGb) {
        this.obNo = obNo;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.obRdate = obRdate;
        this.delGb = delGb;
    }
}

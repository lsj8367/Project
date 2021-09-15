package pack.domain.entity;

import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NewBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nb_no")
    private Long id;

    @Column(name = "nb_name")
    private String nbName;

    @Column(name = "nb_author")
    private String nbAuthor;

    @Column(name = "nb_inter")
    private String nbInter;

    @Column(name = "nb_genre")
    private String nbGenre;

    @Column(name = "nb_comp")
    private String nbComp;

    @Column(name = "nb_bdate")
    private String nbBdate;

    @Column(name = "nb_stock")
    @ColumnDefault("0")
    private int nbStock;

    @Column(name = "nb_price")
    @ColumnDefault("0")
    private int nbPrice;

    @Column(name = "nb_scount")
    @ColumnDefault("0")
    private int nbScount;

    @Column(name = "nb_readcnt")
    @ColumnDefault("0")
    private int nbReadcnt;

    @Column(name = "nb_plot")
    private String nbPlot;

    @Column(name = "nb_image")
    private String nbImage;

    @Builder
    public NewBook(final String nbName, final String nbAuthor, final String nbInter, final String nbGenre, final String nbComp, final LocalDateTime nbBdate, final int nbStock, final int nbPrice, final int nbScount, final int nbReadcnt, final String nbPlot, final String nbImage) {
        this.nbName = nbName;
        this.nbAuthor = nbAuthor;
        this.nbInter = nbInter;
        this.nbGenre = nbGenre;
        this.nbComp = nbComp;
        this.nbBdate = nbBdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.nbStock = nbStock;
        this.nbPrice = nbPrice;
        this.nbScount = nbScount;
        this.nbReadcnt = nbReadcnt;
        this.nbPlot = nbPlot;
        this.nbImage = nbImage;
    }
}
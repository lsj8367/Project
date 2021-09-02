package pack.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
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
    private LocalDateTime nbBdate;

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
}
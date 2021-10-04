package pack.newbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewBookDto {

	private String nb_name,nb_author,nb_inter,nb_genre,nb_comp,nb_bdate,nb_plot,nb_image, sql;
	private int nb_no,nb_stock,nb_price,nb_scount,nb_readcnt, rn, scount, smonth, plusstock;
}

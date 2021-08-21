package pack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChartPrintDto {
	
	private int nbprofit, obprofit, rprofit;
	private String cmonth;
}

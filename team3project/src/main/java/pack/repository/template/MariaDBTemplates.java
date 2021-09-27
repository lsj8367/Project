package pack.repository.template;

import com.querydsl.core.types.Ops;
import com.querydsl.jpa.HQLTemplates;

public class MariaDBTemplates extends HQLTemplates {
    public static final MariaDBTemplates DEFAULT = new MariaDBTemplates();

    protected MariaDBTemplates() {
        this(DEFAULT_ESCAPE);
        add(Ops.MathOps.RANDOM, "rand()");
        add(Ops.MathOps.RANDOM2, "rand({0})");
    }

    public MariaDBTemplates(char escape) {
        super(escape);
    }
}

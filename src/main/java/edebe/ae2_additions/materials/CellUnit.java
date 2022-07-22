package edebe.ae2_additions.materials;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.getPower;

public enum CellUnit {
    KB("k", 1),MB("m", 2);

    private final String name;
    private final int scalar;
    private final int type;

    CellUnit(String name, int scalar) {
        this.name = name;
        this.scalar = getPower(1024, scalar);
        this.type = getPower(8, scalar);
    }

    public String getName() {
        return this.name;
    }

    public int getScalar() {
        return this.scalar;
    }

    public int getType() {
        return this.type;
    }
}
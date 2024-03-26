public class msgjson1 {
    Poly polynom;
    byte[] seed;

    public msgjson1(Polynomial x, byte[] seed) {
        this.polynom = new Poly(x);
        this.seed = seed;
    }

    public Polynomial getPoly() {
        return new Polynomial(polynom.coef);
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
public class jsonRequest {
    polynom polynom;
    byte[] seed;

    public jsonRequest(Polynomial x, byte[] seed) {
        this.polynom = new polynom(x.GetCoef());
        this.seed = seed;
    }

    public Polynomial getPoly() {
        return new Polynomial(polynom.coef());
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
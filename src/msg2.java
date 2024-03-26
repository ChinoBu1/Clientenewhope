public class msg2 {
    Poly polynom;
    int[][] hint;

    public msg2(Polynomial x, int[][] hint) {
        this.polynom = new Poly(x);
        this.hint = hint;
    }

    public Polynomial getPoly() {
        return new Polynomial(polynom.coef);
    }

    public int[][] getHint() {
        return this.hint;
    }

}

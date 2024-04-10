public class jsonResponse {
    polynom polynom;
    int[][] hint;

    public jsonResponse(Polynomial x, int[][] hint) {
        this.polynom = new polynom(x.GetCoef());
        this.hint = hint;
    }

    public Polynomial getPoly() {
        return new Polynomial(polynom.coef());
    }

    public int[][] getHint() {
        return this.hint;
    }

}

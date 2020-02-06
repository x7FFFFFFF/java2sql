public class Test {
    /**
     *
     *
     * @param x
     * @return
     */
    public static int func1(int x){ // comment
        int y = func2(x);
        return x*x+ y;
    }

    private static int func2(int x) {
        return x*x+1;
    }

}

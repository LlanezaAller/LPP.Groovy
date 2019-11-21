
public class Fib {
	static Object fibStaticTernary (Object n) {
        return n >= 2 ? fibStaticTernary(n-1) + fibStaticTernary(n-2) : 1;
    }

    static Object fibStaticIf (Object n) {
        if(n >= 2) 
			return fibStaticIf(n-1) + fibStaticIf(n-2); 
		else
			return 1;
    }

    Object fibTernary (Object n) {
        return n >= 2 ? fibTernary(n-1) + fibTernary(n-2) : 1;
    }

    Object fibIf (Object n) {
        if(n >= 2)
			return fibIf(n-1) + fibIf(n-2);
		else 
			return 1;
    }

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        Fib.fibStaticTernary(40);
        //println("Java(static ternary): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        Fib.fibStaticIf(40);
        //println("Java(static if): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        new Fib().fibTernary(40);
        //println("Java(instance ternary): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        new Fib().fibIf(40);
        //println("Java(instance if): " + (System.currentTimeMillis() - start) +" ms");
    }
}

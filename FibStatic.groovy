import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
public class FibStatic {
	static int fibStaticTernary (int n) {
        return n >= 2 ? fibStaticTernary(n-1) + fibStaticTernary(n-2) : 1;
    }

    static int fibStaticIf (int n) {
        if(n >= 2) 
			return fibStaticIf(n-1) + fibStaticIf(n-2); 
		else
			return 1;
    }

    int fibTernary (int n) {
        return n >= 2 ? fibTernary(n-1) + fibTernary(n-2) : 1;
    }

    int fibIf (int n) {
        if(n >= 2)
			return fibIf(n-1) + fibIf(n-2);
		else 
			return 1;
    }

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        FibStatic.fibStaticTernary(40);
        //println("Java(static ternary): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        FibStatic.fibStaticIf(40);
        //println("Java(static if): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        new FibStatic().fibTernary(40);
        //println("Java(instance ternary): " + (System.currentTimeMillis() - start) +" ms");

        start = System.currentTimeMillis();
        new FibStatic().fibIf(40);
        //println("Java(instance if): " + (System.currentTimeMillis() - start) +" ms");
    }
}


package designpattern.strategy;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executor(int a, int b){
       return strategy.doOperation(a,b);
    }

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println( context.executor(1,2));

        Context context2 = new Context(new OperationSubstract());
        System.out.println( context2.executor(8,2));

    }

}

class Hyper { char h = 'h'; } 
class Super extends Hyper { char s = 's'; }
class Test extends Super {
    public static void main(String[] args) {
        Hyper h = new Super();
        System.out.println(h.h);
    }
}

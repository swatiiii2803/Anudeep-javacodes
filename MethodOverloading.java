
class Addition{

    public int add(int a , int b){
        return a+b;
    }

        public int add(int a , int b , int c){
        return a+b+c;
    }

}
class MethodOverloading{
    public static void main(String[] args){
        Addition a = new Addition();
        int result1 = a.add(80, 76);
        int result2 = a.add(55, 87, 76);
        System.out.println("Addition of 2 numbers: "+result1);
        System.out.println("Addition of 3 numbers: "+result2);
    }
}

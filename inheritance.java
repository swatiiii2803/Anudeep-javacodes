  class parent  {

    void show()

    {
        System.out.println("parent ");

    }
}
class child extends parent{
    @Override
    void show()
    {
        System.out.println("child");
    }
    
}
  class inheritance{
    public static void main(String args[])
    {
        child p=new child();

    p.show();
    
  }
}
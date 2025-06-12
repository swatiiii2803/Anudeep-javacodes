
class EncapsulationProgram{

    private int id;
    private String name;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "encapsulation{" +"id=" + id +", name='" + name + '\'' +'}';
    }


    public static void main(String[] args){
        EncapsulationProgram ep = new EncapsulationProgram();
        ep.setId(03);
        ep.setName("SWATI");
        ep.getId();
        ep.getName();
        System.out.println(ep);        
    }
}

import java.util.ArrayList;

class Membru{
    private String nume,prenume;
    private int salariu,varsta,aniExperienta;
    public Membru(String nume,String prenume,int varsta,int salariu,int aniExperienta) {
        this.nume=nume;
        this.prenume=prenume;
        this.varsta=varsta;
        this.salariu=salariu;
        this.aniExperienta=aniExperienta;
    }
    public int getAniExperienta(){
        return this.aniExperienta;
    }
    public String toString(){
        return this.nume+" "+this.prenume;
    }
    public boolean equals(Object o) {
        if(o instanceof Membru) {
            Membru aux = (Membru) o;
            if(this.nume.equals(aux.nume)&&
            this.prenume.equals(aux.prenume)&&
            this.varsta==aux.varsta&&
            this.salariu==aux.salariu&&
            this.aniExperienta==aux.aniExperienta){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
abstract class Echipa {
    protected String name;
    protected int numarMaximMembrii;
    protected int count=0;
    protected Echipa(String name,int nrMax) {
        this.name=name;
        this.numarMaximMembrii=nrMax;
    }
    protected Membru lider;
    protected ArrayList<Membru> lista=new ArrayList<>(numarMaximMembrii);
    protected boolean addMember(Membru member) {
        if(count<numarMaximMembrii&& !lista.contains(member)) {
            lista.add(count, member);
            count++;
            return true;
        }
        return false;
    }
    protected boolean setLeader(Membru liderNou) {
        if(liderNou.getAniExperienta()>=5) {
            lider=liderNou;
            return true;
        }
        return false;
    }
    protected Membru removeMember(Membru member) {
        Membru aux=null;
        if(lista.contains(member)){
            aux=member;
            lista.remove(member);
            count--;
            return aux;
        }
        return aux;
    }
    public String toString() {
        String s="Lider echipa: <"+lider.toString()+">, ";
        for(int i=0;i<count;i++) {
            s+=lista.get(i).toString()+" , ";
        }
        return s;
    }
    protected abstract double getCost();
    protected void setName(Membru membru,String s) {
        if(membru.equals(lider)){
            this.name=s;
        }
        else{
            System.out.println("You don't have permission to change that!");
        }
    }
    protected void setNumarMaximMembrii(Membru membru,int number) {
        if(membru.equals(lider)){
            this.numarMaximMembrii=number;
        }
        else{
            System.out.println("You don't have permission to change that!");
        }
    }
    public Membru getLider(){
        return this.lider;
    }
    public String getName(){
        return this.name;
    }
}

class DevTeam extends Echipa {

    public DevTeam(String name,int nrMax) {
        super(name,nrMax);
    }
    public double getCost(){
        double liderCost=0;
        double membruCost=0;
        liderCost=2500+lider.getAniExperienta()*250;
        for(int i=0;i<count;i++) {
            int aux=lista.get(i).getAniExperienta();
            if(aux<2) {
                membruCost+=1500;
            }
            if(aux>=2&&aux<=5) {
                membruCost+=(1500+1500*25/100);
            }
            if(aux>5) {
                membruCost+=(1500+1500*50/100);
            }
        }
        return membruCost+liderCost;
    }
}
class HR extends Echipa {
    public HR(String name,int nrMax) {
        super(name,nrMax);
    }
    public double getCost() {
        int liderCost=1350+ lider.getAniExperienta()*300;
        int membruCost=0;
        for(int i=0;i<count;i++) {
            int aux=lista.get(i).getAniExperienta();
            if(aux<2) {
                membruCost+=1000;
            }
            if(aux>=2&&aux<=5) {
                membruCost+=(1000+1000*25/100);
            }
            if(aux>5) {
                membruCost+=(1000+1000*50/100);
            }
        }
        return liderCost+membruCost;
    }
}
public class Main {
    public static void main(String[] args) {
        DevTeam devTeam = new DevTeam("Development Team",10);
        HR hr =new HR("Human resources",12);
        System.out.println(devTeam.setLeader(new Membru("Paraschiv","Ion",25,3500,4)));
        devTeam.setLeader(new Membru("Toc","Laurentiu",34,4000,7));
        Membru m1 = new Membru("Dud","Denisa",26,3000,3);
        Membru m2 = new Membru("Paraschiv","Ion",20,2800,4);
        Membru m3 = new Membru("Cioltea","Mihai",30,4500,6);
        Membru m4 = new Membru("Polvrea","Ana",25,2560,3);
        Membru m5 = new Membru("Tonta","Razvan",45,4700,5);
        Membru m6 = new Membru("Leucian","Darius",27,5100,8);
        Membru m7 = new Membru("Stefan","Denisa",29,3590,7);
        Membru m8 = new Membru("Perta","Ariana",26,3000,3);
        Membru m9 = new Membru("Hanes","Denis",33,2400,10);
        Membru m10 = new Membru("Sim","Alexandra",21,2560,1);
        hr.setLeader(new Membru("Memetea","Diana",34,28970,7));
        devTeam.addMember(m1);
        devTeam.addMember(m2);
        devTeam.addMember(m3);
        devTeam.addMember(m4);
        devTeam.addMember(m5);
        hr.addMember(m6);
        hr.addMember(m7);
        hr.addMember(m8);
        hr.addMember(m9);
        hr.addMember(m10);
        System.out.println(devTeam);
        System.out.println("Cost devTeam: " + devTeam.getCost());
        System.out.println(hr);
        System.out.println("Cost hr: " + hr.getCost());
        System.out.println(hr.removeMember(m8));
        System.out.println(hr);
        System.out.println("Cost hr: " + hr.getCost());
        System.out.println(hr.getName());
        devTeam.setName(hr.getLider(),"Dev Team");
        System.out.println(devTeam.getName());
        devTeam.setName(m3,"Dev MAMA");
    }
}

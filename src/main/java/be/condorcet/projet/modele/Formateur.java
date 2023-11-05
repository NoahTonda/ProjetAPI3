package be.condorcet.demosb1.modele;

public class Client {
    private String nom,prenom,localite,rue ,num,tel;
    private int idclient,cp ;

    public Client() {
    }

    public Client(int idclient,String nom,String prenom,int cp,
                  String localite,String rue,String num,String tel){
        this.idclient=idclient;
        this.nom=nom;
        this.prenom=prenom;
        this.cp=cp;
        this.localite=localite;
        this.rue=rue;
        this.num=num;
        this.tel=tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdclient() {
        return idclient;
    }

    public int getCp() {
        return cp;
    }

    public String getLocalite() {
        return localite;
    }

    public String getRue() {
        return rue;
    }


    public String getNum() {
        return num;
    }


    public String getTel() {
        return tel;
    }

    public void setIdclient(int idclient){
        this.idclient=idclient;
    }

    public void setCp(int cp) {
        this.cp=cp;
    }


    public void setLocalite(String localite) {
        this.localite=localite;
    }


    public void setRue(String rue) {
        this.rue=rue;
    }


    public void setNum(String num) {
        this.num=num;
    }


    public void setTel(String tel) {
        this.tel=tel;
    }

    @Override
    public String toString() {
        return "Client{" +" idclient=" + idclient + ",nom=" + nom + ", prenom=" + prenom + ", localite=" + localite + ", rue=" + rue + ", num=" + num + ", tel=" + tel + ", cp=" + cp + '}';
    }
}

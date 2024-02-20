package nLayeredCommercialDemo.entities.concretes;

public
class User {
    String password;
    String nick ;
    String email;

    public
    User ( String password, String nick, String email ) {
        this.password = password;
        this.nick = nick;
        this.email = email;
        System.out.println ("nickiniz "+nick+" mailiniz: "+email );
    }

    public
    String getPassword () {
        return password;
    }

    public
    void setPassword ( String password ) {
        this.password = password;
    }

    public
    String getNick () {
        return nick;
    }

    public
    String getEmail () {
        return email;
    }

    public
    void setEmail ( String email ) {
        this.email = email;
    }
}

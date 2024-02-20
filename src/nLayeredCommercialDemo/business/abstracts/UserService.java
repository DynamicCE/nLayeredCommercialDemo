package nLayeredCommercialDemo.business.abstracts;

public
interface UserService {
    void register ( String password, String nick, String email );

    boolean validateEmail ( String email );

    boolean validatePassword ( String password );

    boolean validateNick ( String nick );

    void sendVerificationMail ( String email );

}
